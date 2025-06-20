package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.domain.MybatisBoard;
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
		return bd.select(board);
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
