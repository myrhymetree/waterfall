package com.greedy.waterfall.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greedy.waterfall.board.model.dto.EduDTO;
import com.greedy.waterfall.board.model.service.EduService;
import com.greedy.waterfall.common.exception.board.BoardModifyException;
import com.greedy.waterfall.common.exception.board.BoardRegistException;
import com.greedy.waterfall.common.exception.board.BoardRemoveException;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;

@Controller
@RequestMapping("/edu")
public class EduController {

	private final EduService eduService;
	
	@Autowired
	public EduController(EduService eduService) {
		System.out.println("확인용");
		System.out.println("확인용");
		System.out.println("확인용");
		System.out.println("확인용2");
		this.eduService = eduService;
	}
	
	@GetMapping("/list")
	public ModelAndView eduSelectList(HttpServletRequest request, ModelAndView mv) {
		
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
		
		int totalCount = eduService.selectTotalCount(searchMap);		
		
		/* 게시물 수*/
		int limit = 10;
		
		/* 버튼 수*/
		int buttonAmount = 5;
		/* 페이징 관한 정보 인스턴스 */
		SelectCriteria selectCriteria = null;
		
		/* 검색 결과가 담겨져 있을 경우 담을 곳*/
		if(searchCondition != null && !"".equals(searchCondition) ) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		/* 전체 조회*/
		List<EduDTO> eduList = eduService.selectEduList(selectCriteria);
		
		System.out.println("확인용3 " + eduList);	

		mv.addObject("eduList", eduList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("/board/edu/eduList");
		
		return mv;
 	}
	
	@GetMapping("edu/eduList")
	public void registEduBoard() {
		
	}
	
	@PostMapping("/regist")
	public String registEduBoard(@ModelAttribute EduDTO eduBoard, HttpServletRequest request,
			RedirectAttributes rttr) throws BoardRegistException {
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		eduBoard.setTitle(title);
		eduBoard.setContent(body);
		
		eduService.registEdu(eduBoard);
		
		return "redirect:/edu/list";
	}
	
	@GetMapping(value="eduDetail")
    @ResponseBody
    public ModelAndView findNoticeDetail(HttpServletRequest request,
    HttpServletResponse response ,ModelAndView mv) {
    
    int no = Integer.parseInt(request.getParameter("no"));
		EduDTO eduDetail = eduService.findEduDetail(no);
		
		response.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new GsonBuilder()
		      .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
		      .setPrettyPrinting()
		      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
		      .serializeNulls()
		      .disableHtmlEscaping()
		      .create();
		
		mv.addObject("eduDetail", gson.toJson(eduDetail));
		mv.setViewName("jsonView");
		
		return mv; 
	}
	
	@GetMapping("/delete")
	public String removeEduBoard(HttpServletRequest request, RedirectAttributes rttr) throws BoardRemoveException {
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
			System.out.println("확인용" + request.getParameter("no"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		eduService.removeEduBoard(no);
		
		rttr.addFlashAttribute("message", "공지사항 삭제에 성공하셨습니다.!");
		
		return "redirect:/edu/list";
	}
	
	@PostMapping("/update")
	public String modifyEduBoard(@ModelAttribute EduDTO edu, HttpServletRequest request,
			RedirectAttributes rttr) throws BoardModifyException {
		eduService.modifyEduBoard(edu);
		
		rttr.addFlashAttribute("message", "게시판 수정에 성공하셨습니다.");
		
		return "redirect:/edu/list";
		
	}
	
}
