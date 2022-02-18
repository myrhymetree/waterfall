package com.greedy.waterfall.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.board.model.dto.MeetingDTO;
import com.greedy.waterfall.board.model.service.MeetingService;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;

@Controller
@RequestMapping("/meeting/*")
public class MeetingController {

	private final MeetingService meetingService;
	
	@Autowired
	public MeetingController(MeetingService meetingService) {
		this.meetingService = meetingService;
	}
	
	
	@GetMapping("/list")
	public ModelAndView findMeetingBoardList(ModelAndView mv, HttpServletRequest request) {
		
		String currentPage = request.getParameter("currentPage");
		
		int pageNo = 1;
		
		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		Map<String, String> searchMap = new HashMap<>();
		
		int totalCount = meetingService.findMeetingTotalCount(searchMap);
		
		int limit = 10;
		
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		List<MeetingDTO> meetingList = meetingService.findMeetingBoardList(selectCriteria);
		
		for(int i = 0; i < 10; i ++) {System.out.println("ddddd");}for(int i = 0; i < 10; i ++) {System.out.println("ddddd");}
		for(MeetingDTO meeting : meetingList) { System.out.println(meeting);}for(MeetingDTO meeting : meetingList) { System.out.println(meeting);}for(MeetingDTO meeting : meetingList) { System.out.println(meeting);}for(MeetingDTO meeting : meetingList) { System.out.println(meeting);}
		for(int i = 0; i < 10; i ++) {System.out.println("ddddd");}for(int i = 0; i < 10; i ++) {System.out.println("ddddd");}
		
		mv.addObject("meetingList", meetingList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/meeting/list");

		mv.setViewName("/board/meeting/meetingList");
		
		return mv;
	}
	
	@GetMapping("/detail/{meetingNo}")
	public ModelAndView findMeetingBoardDetail(ModelAndView mv, @PathVariable int meetingNo) {
		
		MeetingDTO meeting = meetingService.findMeetingBoardDetail(meetingNo);
		
		
		
		mv.addObject("meeting", meeting);
		mv.setViewName("/board/meeting/meetingDetail");
		
		return mv;
	}
}
