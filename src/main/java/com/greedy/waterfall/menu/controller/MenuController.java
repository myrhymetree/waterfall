package com.greedy.waterfall.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.menu.model.service.MenuService;

/**
 * <pre>
 * Class : MenuController
 * Comment : 로그인 후 계정의 종류별로 출력되는 메인화면에 필요한 정보들을 조회 한다. 
 * 
 * History
 * 2022. 3. 3.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Controller
@RequestMapping("/menu/*")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("/main")
	public ModelAndView sendToMainpage(ModelAndView mv) {
		
		mv.setViewName("/main/mainPage");
		
		return mv;
	}
	
	
	
	
}
