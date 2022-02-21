package com.greedy.waterfall.board.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;

public interface IssueMapper {

	int selectTotalCount(Map<String, String> searchMap);

	List<IssueDTO> selectAllIssueList(SelectCriteria selectCriteria);

}
