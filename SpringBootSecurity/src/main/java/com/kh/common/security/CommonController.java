package com.kh.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.java.Log;

@Log
@Controller
public class CommonController {
	@GetMapping("/accessError")
	public String accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		model.addAttribute("msg", "접근 불가");

		return "accessError";
	}
}
