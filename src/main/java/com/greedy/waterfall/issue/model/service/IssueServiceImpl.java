package com.greedy.waterfall.issue.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.board.model.mapper.IssueMapper;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;

@Service
public class IssueServiceImpl implements IssueService {

	private final IssueMapper mapper;
	
	@Autowired
	public IssueServiceImpl(IssueMapper mapper) {
		this.mapper = mapper;
	}
	@Override
	public int selectTotalCount(Map<String, String> searchMap) {
		int result = mapper.selectTotalCount(searchMap);
		
		return result;
	}

	@Override
	public List<IssueDTO> selectAllIssueList(SelectCriteria selectCriteria) {
		List<IssueDTO> issueList = mapper.selectAllIssueList(selectCriteria);
		
		
		return issueList;
	}

}
