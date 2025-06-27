package com.kh.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@RequestMapping("/board")
@Controller
public class BoardController {
	@GetMapping("/list")
	public String list() {
		log.info("list : 모두가 접근 가능");
		return "board/list";
	}

	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String registerForm() {
		log.info("registerForm : 로그인한 회원만 접근 가능");
		return "board/register";
	}
}