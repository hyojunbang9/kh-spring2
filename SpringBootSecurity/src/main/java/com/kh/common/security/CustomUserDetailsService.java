package com.kh.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kh.common.security.domain.CustomUser;
import com.kh.domain.Member;
import com.kh.mapper.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.info("Load User By UserName : " + username);
		// userName은 사용자명이 아니라 사용자 아이디이다.
		Member member = new Member();
		member.setId(username);
		Member _member = null;
		try {
			_member = memberDAO.selectJoin(member);
			log.info("queried by member mapper: " + _member);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return _member == null ? null : new CustomUser(_member);

	}

}
