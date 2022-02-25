package com.greedy.waterfall.project.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.mapper.ProjectManageMapper;

@Service
public class ProjectManageServiceImpl implements ProjectManageService{

	private final ProjectManageMapper mapper;
	
	@Autowired
	public ProjectManageServiceImpl(ProjectManageMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<ProjectManageMemberDTO> findProjectMember(int projectNo) {

		return mapper.findProjectMember(projectNo);
	}

}
