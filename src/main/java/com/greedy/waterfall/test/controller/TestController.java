package com.greedy.waterfall.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.test.model.dto.TestDTO;
import com.greedy.waterfall.test.model.service.TestService;

@Controller
@RequestMapping("/*")
public class TestController {

	private final TestService ts;

	public TestController(TestService ts) {
		this.ts = ts;
	}

	@RequestMapping("/")
	public String indexPage() {
		System.out.println("dddddd");
		return "index";
	}
	
	@RequestMapping("test")
	public ModelAndView testConnectToDb(ModelAndView mv) {
		List<TestDTO> testList = ts.findTest();
		
		mv.addObject("testList", testList);
		mv.setViewName("/main/result");
		return mv;
	}
}
