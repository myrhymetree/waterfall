package com.greedy.waterfall.issue.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.issue.IssueRegistException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.issue.model.dto.IssueDTO;
import com.greedy.waterfall.issue.model.dto.IssueFileDTO;
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
	public List<IssueDTO> selectAllIssue() {
		List<IssueDTO> allIssueList = mapper.selectAllIssue();
		return allIssueList;
	}
	@Override
	public IssueDTO selectTasks(int no) {
		IssueDTO issue = mapper.selectTasks(no);
		
		return issue;
	}
	@Override
	public List<IssueDTO> selectTask(int taskNo) {
		List<IssueDTO> taskList = mapper.selectTask(taskNo);
		
		return taskList;
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
		}
		return result;
	}

	@Override
	public IssueDTO selectIssueDetail(int no) {
		
		IssueDTO issueDetail = mapper.selectIssueDetail(no);
		
		return issueDetail;
	}
}
//			issueFileDTO.setRefIssueNo(issue.getNo());
//			mapper.insertIssueFile(issueFileDTO);
//		if(!(result > 0)) {
//			throw new IssueRegistException("이슈 등록에 실패하셨습니다.");
//		}
		
		
