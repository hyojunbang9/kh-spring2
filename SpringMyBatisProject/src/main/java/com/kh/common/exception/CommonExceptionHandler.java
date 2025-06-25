package com.kh.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {
	@ExceptionHandler(Exception.class)
	public String handle(Exception ex, Model model) {
		log.info(ex.toString());
		String message = ex.toString();
		model.addAttribute("message", message);
		return "error/errorCommon";
	}

}