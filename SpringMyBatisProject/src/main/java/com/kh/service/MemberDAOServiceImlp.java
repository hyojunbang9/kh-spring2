package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.domain.Member;
import com.kh.domain.MemberAuth;
import com.kh.mapper.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberDAOServiceImlp implements MemberDAOService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	@Transactional
	// 회원 등록 및 권한 등록
	public void insert(Member member) throws Exception {
		// 회원 등록 처리
		memberDAO.insert(member);
		// 회원 권한 객체 생성
		MemberAuth memberAuth = new MemberAuth();
		// 회원 권한 설정
		memberAuth.setNo(member.getNo());
		memberAuth.setAuth("ROLE_USER");
		// 회원 권한 처리
		memberDAO.insertAuth(memberAuth);
	}

	@Override
	@Transactional
	// 회원 권한 입력
	public void insertAuth(MemberAuth memberAuth) throws Exception {
		memberDAO.insertAuth(memberAuth);
	}

	// 회원 정보 전체 출력
	@Override
	public List<Member> selectAll() throws Exception {

		return memberDAO.selectAll();
	}

	// 회원 정보 및 권한 출력(회원&권한 조인)
	@Override
	public Member selectJoin(Member member) throws Exception {

		return memberDAO.selectJoin(member);
	}

	// 회원 정보 수정
	@Override
	@Transactional
	public void update(Member member) throws Exception {
		// 회원 정보 수정 입력
		memberDAO.update(member);
		// 회원 권한 삭제
		memberDAO.deleteAuth(member);
		// 수정된 권한 입력
		List<MemberAuth> authList = member.getAuthList();
		for (int i = 0; i < authList.size(); i++) {
			MemberAuth memberAuth = authList.get(i);
			String auth = memberAuth.getAuth();
			if (auth == null) {
				continue;
			}
			if (auth.trim().length() == 0) {
				continue;
			}
			memberAuth.setNo(member.getNo());
			memberDAO.insertAuth(memberAuth);
		}
	}

	// 회원 정보 삭제
	@Override
	@Transactional
	public void delete(Member member) throws Exception {
		// 오류 날 때 확인 해보기
		log.info("MemberDAOServiceImlp  delete start");
		memberDAO.deleteAuth(member);
		log.info("MemberDAOServiceImlp  deleteAuth end");
		memberDAO.delete(member);
		log.info("MemberDAOServiceImlp  delete end");
	}

	// 회원 권한 삭제
	@Override
	@Transactional
	public void deleteAuth(Member member) throws Exception {
		memberDAO.deleteAuth(member);
	}

}
