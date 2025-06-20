package com.board.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.board.domain.Board;

@Repository
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate; // 자바에서 connecting 역할
	
	private String insertBoard = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?)";
	private String selectByNoBoard = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, REG_DATE FROM BOARD WHERE BOARD_NO = ?";
	private String updateBoard = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_NO = ?";
	private String deleteBoard = "DELETE FROM BOARD WHERE BOARD_NO = ?";
	private String selectAllBoard = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, REG_DATE FROM BOARD WHERE BOARD_NO > 0 ORDER BY BOARD_NO DESC, REG_DATE DESC";

	// 게시판 삽입
	public void insert(Board board) throws Exception {
		jdbcTemplate.update(insertBoard, board.getTitle(), board.getContent(), board.getWriter());
	}

	// 게시판 출력(하나만 출력)
	public Board select(Board board) throws Exception {
		List<Board> results = jdbcTemplate.query(selectByNoBoard, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getDate("reg_date"));

				return board;
			}
		}, board.getBoardNo());

		return results.isEmpty() ? null : results.get(0);
	}

	// 게시판 수정
	public void update(Board board) throws Exception {

		jdbcTemplate.update(updateBoard, board.getTitle(), board.getContent(),

				board.getBoardNo());
	}

	// 게시판 삭제
	public void delete(Board board) throws Exception {
		jdbcTemplate.update(deleteBoard, board.getBoardNo());
	}

	// 게시판 출력(전체 출력)
	public List<Board> selectAll() throws Exception {
		List<Board> results = jdbcTemplate.query(selectAllBoard, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getDate("reg_date"));

				return board;
			}
		});
		return results.isEmpty() ? null : results;
	}
}