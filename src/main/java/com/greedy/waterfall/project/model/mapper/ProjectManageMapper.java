package com.greedy.waterfall.project.model.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;

@Repository
public interface ProjectManageMapper {

	List<ProjectManageMemberDTO> findProjectMember(int projectNo);
	
}
