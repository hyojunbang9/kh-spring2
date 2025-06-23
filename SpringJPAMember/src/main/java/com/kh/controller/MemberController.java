package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.domain.Member;
import com.kh.service.MemberDAOService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberDAOService service;

	@GetMapping(value = "/register")
	public String registerForm(Member member, Model model) throws Exception {
		log.info("registerForm");
		model.addAttribute("member", member);
		return "member/register";
	}

	// 게시판 DB입력 요청
	@PostMapping(value = "/register")
	public String register(Member member, Model model) throws Exception {
		service.insert(member);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "member/success";
	}

	// 게시판 list 화면 요청
	@GetMapping(value = "/list")
	public String list(Model model) throws Exception {
		log.info("list");
		model.addAttribute("list", service.selectAll());
		return "member/list";
	}

	// 게시판 상세 list 화면 요청
	@GetMapping(value = "/read")
	public String read(Member member, Model model) throws Exception {
		model.addAttribute("member", service.select(member));
		return "member/read";
	}

	// 게시판 삭제 요청
	@PostMapping(value = "/remove")
	public String remove(Member member, Model model) throws Exception {
		service.delete(member);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "member/success";
	}

	// 게시판 수정 화면 요청
	@GetMapping(value = "/modify")
	public String modifyForm(Member member, Model model) throws Exception {
		model.addAttribute("member", service.select(member));
		return "member/modify";
	}

	@PostMapping(value = "/modify")
	public String modify(Member member, Model model) throws Exception {
		service.update(member);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "member/success";
	}
}
