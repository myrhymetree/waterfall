package com.greedy.waterfall.issue.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;

public interface IssueService {

	List<ProjectIssueCountDTO> selectAllProjectList(Map<String, Integer> managerNo);

	List<IssueDTO> selectIssuesOfTask(int projectNo);

	List<IssueDTO> selectIssueList(int taskNo);

	List<IssueDTO> selectAllIssue();

	IssueDTO selectTasks(int no);

	List<IssueDTO> selectTask(int taskNo);

	boolean registIssue(IssueDTO issue);

	IssueDTO selectIssueDetail(int no);

	List<ProjectMemberDTO> selectProjectMember(int projectNo);

	IssueFileDTO findFile(int no);

	IssueFileDTO removeGuideFile(int fileNumber);

}
