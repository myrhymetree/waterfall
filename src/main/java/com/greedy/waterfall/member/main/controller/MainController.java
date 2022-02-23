package com.greedy.waterfall.member.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

	@GetMapping(value= {"/"})
	public String main(HttpSession session) {	
		if(session.getAttribute("loginMember") != null){
			return "redirect:/member/login2";
		}
		return "member/memberLogin";
	}
	
	
}
