package com.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.member.domain.Member;
import com.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

//게시판에서 공통의 주소는 /member 가진다.
//Ajax 방식이다. -> @Controller 말고 @RestController 사용
@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	// 디비 연동
	@Autowired
	private MemberService memberService;

	// 게시판 입력 화면 요청
	@GetMapping(value = "/registerForm")
	public String registerForm(Member member) throws Exception {
		log.info("registerForm");
		return "member/register";
	}

	// DB에 게시판 내용 입력
	@PostMapping(value = "/register")
	public String register(Member member, Model model) throws Exception {
		log.info("register");
		memberService.insert(member);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "member/success";
	}

	// 게시판 내용 출력(하나만 출력)
	@GetMapping(value = "/list")
	public String list(Model model) throws Exception {
		log.info("list");
		model.addAttribute("list", memberService.selectAll());
		return "member/list";
	}

	// 게시판 내용 출력(전체 출력)
	@GetMapping(value = "/read")
	public String read(Member member, Model model) throws Exception {
		log.info("read");
		model.addAttribute(memberService.select(member));
		return "member/read";
	}

	// 게시판 삭제
	@PostMapping(value = "/remove")
	public String remove(Member member, Model model) throws Exception {
		log.info("remove");
		memberService.delete(member);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "member/success";
	}

	// 게시판 수정 화면 요청
	@GetMapping(value = "/modify")
	public String modifyForm(Member member, Model model) throws Exception {
		log.info("modifyForm");
		model.addAttribute(memberService.select(member));
		return "member/modify";
	}

	// 게시판 수정 내용 DB에 저장
	@PostMapping(value = "/modify")
	public String modify(Member member, Model model) throws Exception {
		log.info("modify");
		memberService.update(member);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "member/success";
	}
}
