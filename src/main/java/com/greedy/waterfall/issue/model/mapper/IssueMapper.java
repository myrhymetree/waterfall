package com.greedy.waterfall.issue.model.mapper;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueNotificationDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;

public interface IssueMapper {

	List<ProjectIssueCountDTO> selectAllProjectList(Map<String, Integer> managerNo);

	List<IssueDTO> selectIssuesOfTask(int projectNo);

	List<IssueDTO> selectIssueList(int taskNo);

	int registIssue(IssueDTO issue);

	int registIssueFile(IssueFileDTO issueFileDTO);

	IssueDTO selectIssueDetail(int no);

	List<ProjectMemberDTO> selectProjectMember(int projectNo);

	IssueFileDTO findFile(int no);

	int deleteIssueFile(int fileNumber);

	int updateIssue(IssueDTO issue);

	int deleteIssue(IssueDTO issue);

	void registIssueHistory(IssueDTO issue);

	void updateIssueHistory(IssueDTO issue);

	void deleteIssueHistory(IssueDTO issue);

	void writeRegistedIssueHistory(IssueDTO issue);

	void writeUpdatedIssueHistory(IssueDTO issue);

	void writeDeletedIssueHistory(IssueDTO issue);

	List<IssueNotificationDTO> notifyIssueList(int loginMemberNo);

	int notifyCount(int loginMemberNo);

	void removeCheck(Map<String, Integer> condition);

}
