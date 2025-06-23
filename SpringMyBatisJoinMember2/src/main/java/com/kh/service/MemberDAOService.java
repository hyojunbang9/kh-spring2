package com.kh.service;

import java.util.List;

import com.kh.domain.Member;
import com.kh.domain.MemberAuth;
import com.kh.domain.MemberEmail;

public interface MemberDAOService {

	// 회원 등록
	public void insert(Member member) throws Exception;

	// 회원 권한 생성
	public void insertAuth(MemberAuth memberAuth) throws Exception;

	// 회원 이메일 생성
	public void insertEmail(MemberEmail memberEmail) throws Exception;

	// 회원 정보 전체 출력
	public List<Member> selectAll() throws Exception;

	// 회원 정보 및 권한 출력(회원&권한 조인)
	public Member selectJoin(Member member) throws Exception;

	// 회원 정보 수정
	public void update(Member member) throws Exception;

	// 회원 정보 삭제
	public void delete(Member member) throws Exception;

	// 회원 권한 삭제
	public void deleteAuth(Member member) throws Exception;

	// 회원 이메일 삭제
	public void deleteEmail(Member member) throws Exception;
}
