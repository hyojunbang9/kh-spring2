package com.member.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.member.domain.Member;

@Repository
public class MemberDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate; // 자바에서 connecting 역할
	private String selectAllMember = "SELECT * FROM MEMBER WHERE MEMBER_NO > 0 ORDER BY MEMBER_NO DESC";
	private String selectByNoMember = "SELECT * FROM MEMBER WHERE MEMBER_NO = ?";
	private String insertMember = "INSERT INTO MEMBER VALUES(MEMBER_SEQ.NEXTVAL, ?, ?, ?)";
	private String updateMember = "UPDATE MEMBER SET MEMBER_NAME= ?, MEMBER_ID = ?, MEMBER_PW = ? WHERE MEMBER_NO = ?";
	private String deleteMember = "DELETE FROM MEMBER WHERE MEMBER_NO = ?";

	// 게시판 삽입
	public void insert(Member member) throws Exception {
		jdbcTemplate.update(insertMember, member.getName(), member.getId(), member.getPwd());
	}

	// 게시판 출력(하나만 출력)
	public Member select(Member member) throws Exception {
		List<Member> results = jdbcTemplate.query(selectByNoMember, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setNo(rs.getInt("MEMBER_NO"));
				member.setName(rs.getString("MEMBER_NAME"));
				member.setId(rs.getString("MEMBER_ID"));
				member.setPwd(rs.getString("MEMBER_PW"));

				return member;
			}
		}, member.getNo());

		return results.isEmpty() ? null : results.get(0);
	}

	// 게시판 수정
	public void update(Member member) throws Exception {

		jdbcTemplate.update(updateMember, member.getName(), member.getId(),

				member.getPwd());
	}

	// 게시판 삭제
	public void delete(Member member) throws Exception {
		jdbcTemplate.update(deleteMember, member.getNo());
	}

	// 게시판 출력(전체 출력)
	public List<Member> selectAll() throws Exception {
		List<Member> results = jdbcTemplate.query(selectAllMember, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member();
				member.setNo(rs.getInt("MEMBER_NO"));
				member.setName(rs.getString("MEMBER_NAME"));
				member.setId(rs.getString("MEMBER_ID"));
				member.setPwd(rs.getString("MEMBER_PW"));

				return member;
			}
		});
		return results.isEmpty() ? null : results;
	}
}