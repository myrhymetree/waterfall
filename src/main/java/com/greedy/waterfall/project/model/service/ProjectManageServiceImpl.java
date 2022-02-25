package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;
import com.greedy.waterfall.project.model.mapper.ProjectManageMapper;

@Service
public class ProjectManageServiceImpl implements ProjectManageService{

	private final ProjectManageMapper mapper;
	
	@Autowired
	public ProjectManageServiceImpl(ProjectManageMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public Map<String, Object> findProjectMember(int projectNo) {

		List<ProjectManageMemberDTO> memberList = mapper.findProjectMember(projectNo); 
		List<ProjectRoleDTO> allRole = mapper.findAllRole();
		List<DeptDTO> allDept = mapper.findAllDept();
		Map<String, Object> manageProjectMemberInfo = new HashedMap();
		
		manageProjectMemberInfo.put("memberList" , memberList);
		manageProjectMemberInfo.put("allRole", allRole);
		manageProjectMemberInfo.put("allDept", allDept);
		
		return manageProjectMemberInfo;
	}

}
