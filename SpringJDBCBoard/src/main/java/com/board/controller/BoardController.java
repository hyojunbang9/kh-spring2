package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.domain.Board;
import com.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

//게시판에서 공통의 주소는 /board 가진다.
//Ajax 방식이다. -> @Controller 말고 @RestController 사용
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	// 디비 연동
	@Autowired
	private BoardService boardService;

	// 게시판 입력 화면 요청
	@GetMapping(value = "/registerForm")
	public String registerForm(Board board) throws Exception {
		log.info("registerForm");
		return "board/register";
	}

	// DB에 게시판 내용 입력
	@PostMapping(value = "/register")
	public String register(Board board, Model model) throws Exception {
		log.info("register");
		boardService.insert(board);
		model.addAttribute("msg", "등록이 완료되었습니다.");
		return "board/success";
	}

	// 게시판 내용 출력(하나만 출력)
	@GetMapping(value = "/list")
	public String list(Model model) throws Exception {
		log.info("list");
		model.addAttribute("list", boardService.selectAll());
		return "board/list";
	}

	// 게시판 내용 출력(전체 출력)
	@GetMapping(value = "/read")
	public String read(Board board, Model model) throws Exception {
		log.info("read");
		model.addAttribute(boardService.select(board));
		return "board/read";
	}

	// 게시판 삭제
	@PostMapping(value = "/remove")
	public String remove(Board board, Model model) throws Exception {
		log.info("remove");
		boardService.delete(board);
		model.addAttribute("msg", "삭제가 완료되었습니다.");
		return "board/success";
	}

	// 게시판 수정 화면 요청
	@GetMapping(value = "/modify")
	public String modifyForm(Board board, Model model) throws Exception {
		log.info("modifyForm");
		model.addAttribute(boardService.select(board));
		return "board/modify";
	}

	// 게시판 수정 내용 DB에 저장
	@PostMapping(value = "/modify")
	public String modify(Board board, Model model) throws Exception {
		log.info("modify");
		boardService.update(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "board/success";
	}
}
