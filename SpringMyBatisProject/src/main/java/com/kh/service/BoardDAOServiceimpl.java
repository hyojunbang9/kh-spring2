package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.domain.MybatisBoard;
import com.kh.exception.BoardRecordNotFoundException;
import com.kh.mapper.BoardDAO;

@Service
public class BoardDAOServiceimpl implements BoardDAOService {
	@Autowired
	private BoardDAO bd;

	@Override
	public void insert(MybatisBoard board) throws Exception {
		bd.insert(board);
	}

	@Override
	public MybatisBoard select(MybatisBoard board) throws Exception {
		MybatisBoard _board = bd.select(board);
		if (_board == null) {
			// 사용자가 정의한 예외처리함수(BoardRecordNotFoundException)
			throw new BoardRecordNotFoundException(board.getNo() + " 번 게시글은 존재하지 않습니다.");
		}
		return _board;
	}

	@Override
	public void update(MybatisBoard board) throws Exception {
		bd.update(board);
	}

	@Override
	public void delete(MybatisBoard board) throws Exception {
		bd.delete(board);
	}

	@Override
	public List<MybatisBoard> selectAll() throws Exception {
		return bd.selectAll();
	}

}
