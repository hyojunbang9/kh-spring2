package com.kh.service;

import java.util.List;

import com.kh.domain.MybatisBoard;

public interface BoardDAOService {

	// 게시판 삽입
	public void insert(MybatisBoard board) throws Exception;

	// 게시판 출력(하나만 출력)
	public MybatisBoard select(MybatisBoard board) throws Exception;

	// 게시판 수정
	public void update(MybatisBoard board) throws Exception;

	// 게시판 삭제
	public void delete(MybatisBoard board) throws Exception;

	// 게시판 출력(전체 출력)
	public List<MybatisBoard> selectAll() throws Exception;
}
