package com.greedy.waterfall.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.service.MemberService;
import com.greedy.waterfall.project.model.dto.ProjectAuthorityDTO;

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
	
	@PostMapping(value="/pmCheck", produces="application/json; charset=UTF-8")
	@ResponseBody
	public int pwCheck(@RequestParam Map<String, String> parameter) {

		String id = parameter.get("id");
		String pwd = parameter.get("pwd");
		
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPwd(pwd);
		
		System.out.println("확인" + "" + member);
		
		String memberPwd = memberService.pwCheck(member.getId());
		
		System.out.println("확인 2번째" + memberPwd);
				
		if(member == null || !BCrypt.checkpw(member.getPwd(), memberPwd)) {
			System.out.println("확인 비번용" + "" + member.getPwd());
			System.out.println("확인 비번용2" + "" + memberPwd);
			return 0;
		}
		
		return 1;
	}
	
	@RequestMapping(value="/pwUpdate", method=RequestMethod.POST)
	public String pwUpdate(String id, String pwd1, RedirectAttributes rttr, HttpSession session) {
		
		System.out.println("업데이트아이디 " + " " + id);
		System.out.println("업데이트비밀번호 " + " " + pwd1);
		String hashedPw = BCrypt.hashpw(pwd1, BCrypt.gensalt());
		memberService.pwUpdate(id, hashedPw);
		session.invalidate();
		rttr.addFlashAttribute("message", "정보 수정이 완료되었습니다. 다시 로그인해주세요.");
		
		return "redirect:/member/login";
	}
	
	@PostMapping("memberInfo")
	public ModelAndView memberInfo(ModelAndView mv,@RequestParam Map<String, String> parameter , RedirectAttributes rttr,
			HttpSession session) {
		
		MemberDTO member = new MemberDTO();
		
		member.setId(((MemberDTO) session.getAttribute("loginMember")).getId());
		String email = parameter.get("email");
		String phone = parameter.get("phone");

		member.setEmail(email);
		member.setPhone(phone);
		System.out.println("실험 정보" + "" + member);
		System.out.println("실험 정보" + "" + member);
		memberService.memberInfo(member);
		
		rttr.addFlashAttribute("message", "정보 수정이 완료되었습니다.");
		mv.setViewName("redirect:/user/info");
		
		return mv;
	}
}
