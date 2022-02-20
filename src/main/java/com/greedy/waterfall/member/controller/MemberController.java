package com.greedy.waterfall.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("loginMember")
public class MemberController {

	private final MemberService memberService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "/member/memberLogin";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDTO member, Model model) throws LoginFailedException {
		model.addAttribute("loginMember", memberService.findMember(member));
		
		return "redirect:/";
	}
}
