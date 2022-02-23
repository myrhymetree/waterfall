package com.greedy.waterfall.issue.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.mapper.IssueMapper;

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

	@Override
	public List<ProjectIssueCountDTO> selectAllProjectList() {
		List<ProjectIssueCountDTO> allProject = mapper.selectAllProjectList();
		return allProject;
	}
	@Override
	public List<IssueDTO> selectIssuesOfTask(int projectNo) {
		List<IssueDTO> taskIssueList = mapper.selectIssuesOfTask(projectNo);
		return taskIssueList;
	}


}
