package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.board.model.mapper.TodoMapper;
import com.greedy.waterfall.common.paging.SelectCriteria;

@Service
public class TodoServiceImpl implements TodoService {
	
	private final TodoMapper mapper;
	
	@Autowired
	public TodoServiceImpl(TodoMapper mapper) {
		this.mapper = mapper;
	}
	
	/* 게시글 전체 목록 조회 메소드 */
	@Override
	public List<TodoDTO> findTodo(SelectCriteria selectCriteria) {
		
		List<TodoDTO> todoList = mapper.findTodo(selectCriteria);
		
		return todoList;
	}
	
	/* 게시글 전체 갯수 조회 메소드 */
	@Override
	public int selectTotalCount(Map<String, String> searchMap) {
		
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}

}
