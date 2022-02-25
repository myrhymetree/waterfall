package com.greedy.waterfall.issue.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;

public interface IssueService {

	int selectTotalCount(Map<String, String> searchMap);

//	List<IssueDTO> selectAllIssueList(SelectCriteria selectCriteria);

	List<ProjectIssueCountDTO> selectAllProjectList();

	List<IssueDTO> selectIssuesOfTask(int projectNo);

	List<IssueDTO> selectIssueList(int taskNo);

	List<IssueDTO> selectAllIssue();

	IssueDTO selectTasks(int no);

	List<IssueDTO> selectTask(int taskNo);

	void registIssue(IssueDTO issue);

}
