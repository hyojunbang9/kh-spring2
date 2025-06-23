package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.domain.AopBoard;
import com.kh.mapper.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardDAOServiceimpl implements BoardDAOService {
	@Autowired
	private BoardDAO bd;

	@Override
	@Transactional
	public void insert(AopBoard board) throws Exception {

		log.info("start Log insert");
		bd.insert(board);
	}

	@Override
	public AopBoard select(AopBoard board) throws Exception {
		log.info("select Log insert");
		return bd.select(board);
	}

	@Override
	@Transactional
	public void update(AopBoard board) throws Exception {
		log.info("update Log insert");
		bd.update(board);
	}

	@Override
	@Transactional
	public void delete(AopBoard board) throws Exception {
		log.info("delete Log insert");
		bd.delete(board);
	}

	@Override
	public List<AopBoard> selectAll() throws Exception {
		log.info("selectAll Log insert");
		return bd.selectAll();
	}

}
