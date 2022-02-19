package com.greedy.waterfall.board.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface TodoService {

	/* 게시글 전체 목록 */
	List<TodoDTO> findTodo(SelectCriteria selectCriteria);
	
	/* 게시글 전체 갯수 */
	int selectTotalCount(Map<String, String> searchMap);

}
