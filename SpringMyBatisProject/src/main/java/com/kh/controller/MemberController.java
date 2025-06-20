package com.kh.controller;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "com.kh.mapper")
@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberDAOService service;

	// 회원 가입 화면 요청
	@GetMapping(value = "/register")
	public String registerForm(Member member, Model model) throws Exception {
		log.info("UserRegisterForm");
		return "member/register";
	}

	// 회원 가입 DB처리 요청
	@PostMapping(value = "/register")
	public String register(Member member, Model model) throws Exception {
		service.insert(member);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "member/success";
	}

	// 회원 정보 전체 리스트 출력
	@GetMapping(value = "/list")
	public String list(Model model) throws Exception {
		model.addAttribute("list", service.selectAll());

		return "member/list";
	}

	// 회원 정보, 권한 출력(조인)
	@GetMapping(value = "/read")
	public String read(Member member, Model model) throws Exception {
		model.addAttribute(service.selectJoin(member));

		return "member/read";
	}

	@PostMapping(value = "/remove")
	public String remove(Member member, Model model) throws Exception {
		// 삭제 안 될 때 로그파일 활용해보기
		log.info("remove member =" + member.toString());
		service.delete(member);
		model.addAttribute("msg", "삭제가 완료되었습니다.");

		return "member/success";
	}

	@GetMapping(value = "/modify")
	public String modifyForm(Member member, Model model) throws Exception {
		model.addAttribute(service.selectJoin(member));
		return "member/modify";
	}

	@PostMapping(value = "/modify")
	public String modify(Member member, Model model) throws Exception {
		service.update(member);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "member/success";
	}

}
