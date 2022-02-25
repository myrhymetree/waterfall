package com.greedy.waterfall.project.model.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

@Repository
public interface ProjectManageMapper {

	List<ProjectManageMemberDTO> findProjectMember(int projectNo);

	List<ProjectRoleDTO> findAllRole();
	
	List<DeptDTO> findAllDept();

}
