package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.board.model.dto.TodoDTO;
import com.greedy.waterfall.common.paging.SelectCriteria;

public interface TodoMapper {
	
	/* 게시글 전체 목록 조회 */
	List<TodoDTO> findTodo(SelectCriteria selectCriteria);
	
	/* 게시글 전체 갯수 조회 */
	int selectTotalCount(Map<String, String> searchMap);

}
