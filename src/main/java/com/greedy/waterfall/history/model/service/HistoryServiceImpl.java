package com.greedy.waterfall.history.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.history.model.dto.HistoryDTO;
import com.greedy.waterfall.history.model.mapper.HistoryMapper;
import com.greedy.waterfall.issue.model.dto.IssueDTO;


@Service
public class HistoryServiceImpl implements HistoryService{

private final HistoryMapper mapper;
	
	@Autowired
	public HistoryServiceImpl(HistoryMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<HistoryDTO> selectEntireHistoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HistoryDTO> selectIssueHistoryList(int projectNo) {
		
		List<HistoryDTO> issueHistoryList = mapper.selectIssueHistoryList(projectNo);
		
		return issueHistoryList;
	}

}
