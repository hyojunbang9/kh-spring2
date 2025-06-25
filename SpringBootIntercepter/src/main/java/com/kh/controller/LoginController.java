package com.kh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.kh.domain.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping("/login")
	// 사용자 요청 정보 받기
	public String loginForm(Model model) {
		log.info("No_2, LoginController loginForm start");

		// DB연동(Mapper인터페이스를 통해서!) => 다형성 구현을 이용

		// DB연동을 통해 받은 리턴 값을 화면에 전달(4가지-view resolver, redirect, forward, responsBody:json)
		return "login/loginForm";
	}

	@PostMapping("/login")
	public String login(Member member, Model model) {
		log.info("LoginController login start");
		log.info("login userId = " + member.getUserId());
		log.info("login userPw = " + member.getUserPw());
		model.addAttribute("result", "로그인 되었습니다.");
		return "login/success";
	}
}
