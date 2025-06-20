package com.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.domain.Member;
import com.member.repository.MemberDAO;

@Service
public class MemberServiceimpl implements MemberService {
	// 추가로 의존성 주입
	// 바로 DAO 주입
	@Autowired // 인터페이스를 .............. DAO를 바로 주입 시키는?
	// NEW 역할을 대신함
	private MemberDAO memberDAO;

	@Override
	public void insert(Member member) throws Exception {
		memberDAO.insert(member);
	}

	@Override
	public Member select(Member member) throws Exception {
		return memberDAO.select(member);
	}

	@Override
	public void update(Member member) throws Exception {
		memberDAO.update(member);
	}

	@Override
	public void delete(Member member) throws Exception {
		memberDAO.delete(member);
	}

	@Override
	public List<Member> selectAll() throws Exception {
		return memberDAO.selectAll();
	}

}
