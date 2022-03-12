package com.greedy.waterfall.issue.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.GuideModifyException;
import com.greedy.waterfall.common.exception.GuideRemoveException;
import com.greedy.waterfall.common.exception.issue.IssueRegistException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
import com.greedy.waterfall.issue.model.dto.IssueHistoryDTO;
import com.greedy.waterfall.issue.model.dto.IssueNotificationDTO;
import com.greedy.waterfall.issue.model.dto.ProjectIssueCountDTO;
import com.greedy.waterfall.issue.model.dto.ProjectMemberDTO;
import com.greedy.waterfall.issue.model.mapper.IssueMapper;
import com.greedy.waterfall.member.model.dto.MemberDTO;

@Service
public class IssueServiceImpl implements IssueService {

	private final IssueMapper mapper;
	
	@Autowired
	public IssueServiceImpl(IssueMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<ProjectIssueCountDTO> selectAllProjectList(Map<String, Integer> managerNo) {
		List<ProjectIssueCountDTO> allProject = mapper.selectAllProjectList(managerNo);
		return allProject;
	}
	
	@Override
	public List<IssueDTO> selectIssuesOfTask(int projectNo) {
		List<IssueDTO> taskIssueList = mapper.selectIssuesOfTask(projectNo);
		return taskIssueList;
	}
	
	@Override
	public List<IssueDTO> selectIssueList(int taskNo) {
		List<IssueDTO> issueList = mapper.selectIssueList(taskNo);
		return issueList;
	}
	
	@Override
	public boolean registIssue(IssueDTO issue) {
		
		boolean result = false;
		
		
		List<IssueFileDTO> files = issue.getFile();
		System.out.println("IssueServiceImpl의 registIssue의 files 는  " + files);
		if(mapper.registIssue(issue) > 0) {
			result = true;
		
			if(files != null) {
				int count = 0;
				for(int i = 0; i < files.size(); i++) {
					
					files.get(i).setRefIssueNo(issue.getNo());
					count += mapper.registIssueFile(files.get(i));
				}
				if(count != files.size()) {
					result = false;
				} 
			}
			
			
			
			/* 이슈 등록 시 히스토리 반영하기 위해서 mapper로 보내줌 */
			
			int registerNo = issue.getRegisterNo();
			
			int managerNo = issue.getManagerNo();
			
			Map<String, Integer> condition = new HashMap<>();
			
			condition.put("registerNo", registerNo);
			condition.put("managerNo", managerNo);
			
			IssueHistoryDTO history = new IssueHistoryDTO();
			
			mapper.registIssueHistory(issue);
			
			history.setIssue(issue);
			
			mapper.writeRegistedIssueHistory(issue);
		}
		return result;
	}

	@Override
	public IssueDTO selectIssueDetail(int no) {
		
		IssueDTO issueDetail = mapper.selectIssueDetail(no);
		
//		projectMemberMap = mapper.selectProjectMember();
		
		return issueDetail;
	}

	@Override
	public List<ProjectMemberDTO> selectProjectMember(int projectNo) {
		
		List<ProjectMemberDTO> projectMemberList= mapper.selectProjectMember(projectNo);
		
		return projectMemberList;
	}

	@Override
	public IssueFileDTO findFile(int no) {
		
		return mapper.findFile(no);
	}

	@Override
	public IssueFileDTO removeGuideFile(int fileNumber) {
		IssueFileDTO issueFileDTO = mapper.findFile(fileNumber);
		
		int result = mapper.deleteIssueFile(fileNumber);
				
		//		if(!(result > 0)) {
		//			throw new GuideRemoveException("가이드 게시글 삭제에 실패하셨습니다.");
		//		}
		
		return issueFileDTO;
	}

	@Override
	public void modifyIssue(IssueDTO issue, int loginMember) {
		
		int result = mapper.updateIssue(issue);
		
		List<IssueFileDTO> files = issue.getFile();
		System.out.println("IssueServiceImpl의 updateIssue의 files 는  " + files);
		if(mapper.updateIssue(issue) > 0) {
//			result = true;
		
			if(files != null) {
				int count = 0;
				for(int i = 0; i < files.size(); i++) {
					
					files.get(i).setRefIssueNo(issue.getNo());
					count += mapper.registIssueFile(files.get(i));
				}
				if(count != files.size()) {
//					result = false;
				} 
				
			}
			issue.setLoginMemberNo(loginMember);
			
			mapper.updateIssueHistory(issue);
			
			mapper.writeUpdatedIssueHistory(issue);
			
		}
		
//		if(!(result > 0)) {
//			throw new GuideModifyException("가이드 게시글 수정에 실패하셨습니다.");
//		}
	}

	@Override
	public int removeIssue(int issueNo, int loginMemberNo) {
		
		System.out.println("int no의 정보는 :" + issueNo);
		
		IssueDTO issue = mapper.selectIssueDetail(issueNo);
		
		int taskNo = issue.getTaskNo();
		
		System.out.println("removeIssue의 DTO 정보는 : " + issue);

		int result = mapper.deleteIssue(issue);
		
//		if(!(result > 0)) {
//			throw new GuideRemoveException("가이드 게시글 삭제에 실패하셨습니다.");
//		}
		
		issue.setLoginMemberNo(loginMemberNo);
		System.out.println("로그인멤버 : " + loginMemberNo);
		
		mapper.deleteIssueHistory(issue);
		
		mapper.writeDeletedIssueHistory(issue);
		
		return taskNo;
	}

	@Override
	public Map<String, Object> notifyIssueList(Map<String, Integer> identification) {
		
		int loginMemberNo = identification.get("loginMember");

		int issueHistoryNo = identification.get("issueHistoryNo");
		
		Map<String, Integer> condition = new HashMap<>();
		
		condition.put("loginMemberNo", loginMemberNo);
		condition.put("issueHistoryNo", issueHistoryNo);
		
		mapper.removeCheck(condition);
		
		List<IssueNotificationDTO> notificationList = mapper.notifyIssueList(loginMemberNo);
		
		int count = mapper.notifyCount(loginMemberNo);
		
		Map<String, Object> notification = new HashMap<>();
		
		notification.put("notificationList", notificationList);
		notification.put("count", count);
		
		return notification;
	}
}

		
		
