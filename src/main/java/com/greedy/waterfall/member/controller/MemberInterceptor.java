//package com.greedy.waterfall.member.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//public class MemberInterceptor implements HandlerInterceptor {
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//		
//		1. 로그인이 되어있고 로그인 권한부여를 여기서 한다 ?  
//		그렇다면 필터에서 처음 로그인할때 url을 줘야하지 않나?
// 		
//				
//		2. 로그인 하자마자 인터셉터가 뺏을경우		
//		2-1. 해당 아이디랑 비밀번호가 맞는지 서비스에서 디비까지 갔다오겠지		
//		2-2. 맞다면 권한에 대한 것을 넣기 위해 또 가야한다.! 권한 부여 그렇다면 권한 부여는 로직으로 여기서 작성할 것인지 디비에 담겨져있는 권한을 꺼내올건지 선택해야한다. 
//		
//		
//		return true;
//	}
//	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			    ModelAndView modelAndview) {
//	
//		
//	}
//	
//	
//}
