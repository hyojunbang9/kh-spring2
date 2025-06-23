package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.domain.AopMember;
import com.kh.mapper.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardDAOServiceimpl implements BoardDAOService {
	@Autowired
	private BoardDAO bd;

	@Override
	@Transactional
	public void insert(AopMember board) throws Exception {

		log.info("start Log insert");
		bd.insert(board);
	}

	@Override
	public AopMember select(AopMember board) throws Exception {
		log.info("select Log insert");
		return bd.select(board);
	}

	@Override
	@Transactional
	public void update(AopMember board) throws Exception {
		log.info("update Log insert");
		bd.update(board);
	}

	@Override
	@Transactional
	public void delete(AopMember board) throws Exception {
		log.info("delete Log insert");
		bd.delete(board);
	}

	@Override
	public List<AopMember> selectAll() throws Exception {
		log.info("selectAll Log insert");
		return bd.selectAll();
	}

}
