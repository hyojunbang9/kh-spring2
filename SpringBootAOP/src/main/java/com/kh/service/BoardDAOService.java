package com.kh.service;

import java.util.List;

import com.kh.domain.AopBoard;

public interface BoardDAOService {

	// 게시판 삽입
	public void insert(AopBoard board) throws Exception;

	// 게시판 출력(하나만 출력)
	public AopBoard select(AopBoard board) throws Exception;

	// 게시판 수정
	public void update(AopBoard board) throws Exception;

	// 게시판 삭제
	public void delete(AopBoard board) throws Exception;

	// 게시판 출력(전체 출력)
	public List<AopBoard> selectAll() throws Exception;
}
