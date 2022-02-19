package com.greedy.waterfall.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		mv.addObject("meetingList", meetingList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.addObject("intent", "/meeting/list");
		
		mv.setViewName("/board/meeting/meetingList");
		
		return mv;
	}
	
	@GetMapping("/detail/{meetingNo}")
	public ModelAndView findMeetingBoardDetail(ModelAndView mv, @PathVariable("meetingNo") int meetingNo) {
		
		System.out.println("dddddddddd");
		System.out.println("dddddddddd");
		System.out.println("dddddddddd");
		System.out.println("dddddddddd");
		System.out.println("dddddddddd");
		System.out.println("dddddddddd");
		System.out.println("dddddddddd");
		MeetingDTO meeting = meetingService.findMeetingBoardDetail(meetingNo);
		
		mv.addObject("meeting", meeting);
		mv.setViewName("/board/meeting/meetingDetail");
		
		return mv;
	}
	@GetMapping("/remove/{meetingNo}")
	public ModelAndView removeMeetingBoard(ModelAndView mv, @PathVariable("meetingNo") int meetingNo) {
		
		String message = "";
		if(meetingService.removeMeetingBoard(meetingNo)) {
			message = "게시물을 삭제했습니다.";
		} else {
			message = "게시물 삭제에 실패했습니다.";
		}

		mv.setViewName("redirect:/meeting/list");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public ModelAndView registMeetingBoard(ModelAndView mv, @RequestParam Map<String, String> parameter, RedirectAttributes rttr) throws UnsupportedEncodingException {
		
		String message = "";
		if(meetingService.registMeetingBoard(parameter)) {
			message = "게시글을 등록했습니다.";
		} else {
			message = "게시글등록에 실패했습니다.";
		}
		rttr.addFlashAttribute("message",  message);
		mv.setViewName("redirect:/meeting/list");
		
		return mv;
	}
	
	@GetMapping("/modify/{meetingNo}")
	public ModelAndView modifyBoard(ModelAndView mv, @PathVariable("meetingNo") int meetingNo) {
		
		
		MeetingDTO meeting = meetingService.findOneMeetingBoard(meetingNo);		
		
		
		mv.addObject("meeting", meeting);
		mv.setViewName("/board/meeting/meetingModify");
		
		return mv;
	}
	
	@PostMapping("/modify/{meetingNo}")
	public ModelAndView modifyMeetingBoard(ModelAndView mv, @RequestParam Map<String, String> meeting) {
		
		
		
		System.out.println("@PostMapping(\"/modify/{meetingNo}\")");
		System.out.println("content : " + meeting.get("content"));
		
		for(String key : meeting.keySet()) {
			String value = (String) meeting.get(key);
			System.out.println(key + " : " + value);
		}
		
		
		
		String message = "";
		if(meetingService.modifyMeetingBoard(meeting)) {
			message = "게시물 업데이트에 성공했습니다.";
		} else {
			message = "게시물 업데이트에 실패했습니다.";
		}
		
		mv.addObject("meeting", meeting);
		mv.setViewName("redirect:/meeting/detail/" + meeting.get("no"));
		
		return mv;
	}
	
	
	
}








































