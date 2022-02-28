package com.greedy.waterfall.member.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.exception.member.MemberRegistException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;
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
		
		return "/main/mainPage";
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
	
	@GetMapping("regist")
	public ModelAndView regist(ModelAndView mv) {
		
		Map<String, Object> allList = memberService.findDeptJobService(); 

		List<DeptDTO> deptList  = (List<DeptDTO>)allList.get("deptDTO");
		List<JobDTO> jobList = (List<JobDTO>)allList.get("jobDTO");
		
		mv.addObject("deptList", deptList);
		mv.addObject("jobList", jobList);
		mv.setViewName("/member/memberRegist");
		
		return mv;
	}
	
	@GetMapping("regist2/{deptCode}")
	/* @ResponseBody */
	public ModelAndView findTeam(ModelAndView mv, @PathVariable("deptCode") String deptCode ,HttpServletResponse response) throws JsonProcessingException {

//		response.setContentType("application/json; charset=UTF-8");
		
		List<TeamDTO> teamList = memberService.findTeamList(deptCode);
		
		response.setContentType("application/json; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		
		mv.addObject("teamList", mapper.writeValueAsString(teamList));
		mv.setViewName("jsonView"); // 매퍼 안에 담긴 
		
//		Gson gson = new GsonBuilder()
//			      .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
//			      .setPrettyPrinting()
//			      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
//			      .serializeNulls()
//			      .disableHtmlEscaping()																			
//			      .create();
                                                                                                        	
		//		mv.addObject("teamList", gson.toJson(teamList));										
//		                                                                                                		 
//		System.out.println("왜 안될까?" + mv);																	
//		System.out.println("왜 안될까?" + mv);																	
//		System.out.println("왜 안될까?" + mv);																		
//		mv.setViewName("jsonView");																				
//																										
																										
		return mv;                                                                                            	
	}                                                                     										
																													
	@PostMapping("/regist3")                                                                                	
	public ModelAndView registMember(ModelAndView mv, @RequestParam Map<String, String> parameter, HttpServletRequest request,
			RedirectAttributes rttr) throws MemberRegistException {       																	
	
		String name = parameter.get("name");
		String dept = parameter.get("dept");
		String team = parameter.get("team");
		String job = parameter.get("job");
	
		AdminMemberDTO adminMember = new AdminMemberDTO();
		DeptDTO deptDTO = new DeptDTO();
		TeamDTO teamDTO = new TeamDTO();
		JobDTO jobDTO = new JobDTO();
		
		deptDTO.setDeptCode(dept);
//		deptDTO.setDeptName(deptName);
		
		teamDTO.setTeamCode(team);
		jobDTO.setJobCode(job);
		
		adminMember.setName(name);
		adminMember.setDept(deptDTO);
		adminMember.setTeam(teamDTO);
		adminMember.setJob(jobDTO);
				
		memberService.adminMemberRegist(adminMember);
		String message = "등록에 성공하셨습니다.";
		
		
		rttr.addFlashAttribute("message", message);
		mv.setViewName("redirect:/member/regist");
		
		return mv;                                                                                      					
	}
	
	@GetMapping("/modify")
	@ResponseBody
	public ModelAndView findMemberModify(HttpServletRequest request, HttpServletResponse response,
			ModelAndView mv) {
		
		String id = request.getParameter("id");
		AdminMemberDTO modify = memberService.findMemberModify(id);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
				.setPrettyPrinting()
				.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
				.serializeNulls()
				.disableHtmlEscaping()
				.create();
		
		mv.addObject("modify", gson.toJson(modify));
		mv.setViewName("jsonView");
		
		return mv;
	}
	
	
}
