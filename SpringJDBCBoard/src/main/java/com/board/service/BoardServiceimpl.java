package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.Board;
import com.board.repository.BoardDAO;

@Service
public class BoardServiceimpl implements BoardService {
	// 추가로 의존성 주입
	// 바로 DAO 주입
	@Autowired // 인터페이스를 .............. DAO를 바로 주입 시키는?
	// NEW 역할을 대신함
	private BoardDAO boardDAO;

	@Override
	public void insert(Board board) throws Exception {
		boardDAO.insert(board);
	}

	@Override
	public Board select(Board board) throws Exception {
		return boardDAO.select(board);
	}

	@Override
	public void update(Board board) throws Exception {
		boardDAO.update(board);
	}

	@Override
	public void delete(Board board) throws Exception {
		boardDAO.delete(board);
	}

	@Override
	public List<Board> selectAll() throws Exception {
		return boardDAO.selectAll();
	}

}
