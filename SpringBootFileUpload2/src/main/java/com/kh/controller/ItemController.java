package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.kh.domain.Item;
import com.kh.service.ItemMapperService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ItemController {
	@Autowired
	private ItemMapperService service;

	@GetMapping(value = "/register")
	public String registerForm(Item item, Model model) throws Exception {
		log.info("registerForm");
		model.addAttribute("item", item);
		return "item/register";
	}

	// 게시판 DB입력 요청
	@PostMapping(value = "/register")
	public String register(Item item, Model model) throws Exception {
		service.insert(item);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "item/success";
	}

	// 게시판 list 화면 요청
	@GetMapping(value = "/list")
	public String list(Model model) throws Exception {
		log.info("list");
		model.addAttribute("list", service.selectAll());
		return "item/list";
	}

	// 게시판 상세 list 화면 요청
	@GetMapping(value = "/read")
	public String read(Item item, Model model) throws Exception {
		model.addAttribute("item", service.select(item));
		return "item/read";
	}

	// 게시판 삭제 요청
	@PostMapping(value = "/remove")
	public String remove(Item item, Model model) throws Exception {
		service.delete(item);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "item/success";
	}

	// 게시판 수정 화면 요청
	@GetMapping(value = "/modify")
	public String modifyForm(Item item, Model model) throws Exception {
		model.addAttribute("item", service.select(item));
		return "item/modify";
	}

	@PostMapping(value = "/modify")
	public String modify(Item item, Model model) throws Exception {
		service.update(item);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "item/success";
	}
}
