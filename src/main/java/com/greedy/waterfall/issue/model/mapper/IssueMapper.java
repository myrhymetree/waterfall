package com.greedy.waterfall.issue.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;

public interface IssueMapper {

	List<ProjectIssueCountDTO> selectAllProjectList(Map<String, Integer> managerNo);

	List<IssueDTO> selectIssuesOfTask(int projectNo);

	List<IssueDTO> selectIssueList(int taskNo);

	List<IssueDTO> selectAllIssue();

	IssueDTO selectTasks(int no);

	List<IssueDTO> selectTask(int taskNo);

	int registIssue(IssueDTO issue);

	int registIssueFile(IssueFileDTO issueFileDTO);

	IssueDTO selectIssueDetail(int no);

}
