package com.greedy.waterfall.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.board.model.service.TodoService;
import com.greedy.waterfall.common.paging.Pagenation;
import com.greedy.waterfall.common.paging.SelectCriteria;

/**
 * <pre>
 * Class : TodoController
 * Comment : 클라이언트의 요청을 처리한 뒤, 결과를 view페이지 todoList.jsp에게 리턴
 * 
 * History
 * 2022. 2. 19.  (차화응) 처음 작성
 * </pre>
 * @version 1
 * @author 차화응
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	/**
	 * TodoSelectList : 메소드 설명 작성 부분
	 * @param request : 클라이언트로부터 서버로 들어온 요청정보를 전달인자로 받음
	 * @param mv : 컨트롤러가 처리한 결과 정보 및 뷰 선택에 필요한 정보를 담은 매개변수를 전달인자로 받음
	 * @return mv : ModelAndView 타입으로 반환
	 * 
	 * @author 차화응
	 */
	@GetMapping("/list")
	public ModelAndView TodoSelectList(HttpServletRequest request, ModelAndView mv) {
		
		/* 
		 * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
		 * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
		 */
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
		
		System.out.println("컨트롤러에서 검색조건 확인하기 : " + searchMap);
		
		/* 
		 * 전체 게시물 수가 필요하다.
		 * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
		 * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
		 */
		int totalCount = todoService.selectTotalCount(searchMap);
		
		System.out.println("totalTodoCount : " + totalCount);
		
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 20;		//얘도 파라미터로 전달받아도 된다.
		
		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		
		if(searchCondition != null && !"".equals(searchCondition)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		
		System.out.println(selectCriteria);
		
		/* 조회해 온다 */
		List<TodoDTO> todoList = todoService.findTodo(selectCriteria);
		
		System.out.println("todoList : " + todoList);
		
		mv.addObject("todoList", todoList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("/board/todo/todoList");
		
		return mv;
	}
	
}
