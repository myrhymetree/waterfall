package com.greedy.waterfall.member.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
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
	
	/* 로그인 메인화면 */
	@GetMapping("/login")
	public String login() {
		
		return "/member/memberLogin";
	}
	
	/* 관리자 이외 멤버회원 화면 */
	@GetMapping("/login2") 
	public String login2() {
		
		return "/main/memberMain";
	}
	
	/* 관리자 화면으로 가기 */
	@GetMapping("/login3") 
	public String login3() {
		
		return "/main/adminMain";
	}
	
	@PostMapping("/login2")
	public String login(@ModelAttribute MemberDTO member, Model model) throws LoginFailedException {
		
		if(member.getId().equals("admin")) {
			model.addAttribute("loginMember", memberService.findMember(member));
			
			return "redirect:/member/login3"; 
		
		} else {			
			model.addAttribute("loginMember", memberService.findMember(member));
		}
		
		return "redirect:/member/login2";
	}
		
	@GetMapping("logout")
	public String logout(SessionStatus sessionStatus) {
	
		sessionStatus.setComplete();
		
		return "redirect:/member/login";
	}
	
	/*
	 * @GetMapping("/list") public ModelAndView MemberSelectList(HttpServletRequest
	 * request, ModelAndView mv) {
	 * 
	 * String currentPage = request.getParameter("currentPage");
	 * 
	 * return mv; }
	 */
	
	@GetMapping("list")
	public ModelAndView findAdminMemberList(HttpServletRequest request ,ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", searchCondition);
		searchMap.put("searchValue", searchValue);
		
		int totalCount = memberService.selectTotalCount(searchMap);
		
		int limit = 10;
		
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		List<AdminMemberDTO> adminMemberList = memberService.findAdminMember(selectCriteria); 
		
		mv.addObject("adminMemberList", adminMemberList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/member/list");
		mv.setViewName("/member/memberList");
		
		return mv;
	}
	
}
