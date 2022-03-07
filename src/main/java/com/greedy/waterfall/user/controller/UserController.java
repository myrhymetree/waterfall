package com.greedy.waterfall.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.service.MemberService;

@Controller
@RequestMapping("/user")
@SessionAttributes("loginMember")
public class UserController {
	
	private final MemberService memberService;
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserController(MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("info")
	public ModelAndView info(ModelAndView mv, HttpServletRequest request) {
		
		/* 세션에 뭐가 들었는지 확인하기 */
//		MemberDTO member = (MemberDTO) request.getSession().getAttribute("loginMember");
//		
//		System.out.println("확인용" + "" + member);
		
		
		mv.setViewName("/user/userInfoModify");
		
		return mv;
	}
	
	@RequestMapping(value="/pwCheck" , method=RequestMethod.POST)
	@ResponseBody
	public int pwCheck(@ModelAttribute MemberDTO member) {
		
		String memberPwd = memberService.pwCheck(member.getId());
		
		return 1;
	}
}
