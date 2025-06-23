package com.kh.mapper;

import java.util.List;

import com.kh.domain.AopMember;

public interface BoardDAO {

	// 게시판 삽입
	public void insert(AopMember board) throws Exception;

	// 게시판 출력(하나만 출력)
	public AopMember select(AopMember board) throws Exception;

	// 게시판 수정
	public void update(AopMember board) throws Exception;

	// 게시판 삭제
	public void delete(AopMember board) throws Exception;

	// 게시판 출력(전체 출력)
	public List<AopMember> selectAll() throws Exception;
}
