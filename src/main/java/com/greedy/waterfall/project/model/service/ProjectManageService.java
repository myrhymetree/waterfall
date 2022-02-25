package com.greedy.waterfall.project.model.service;

import java.util.List;

import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;

public interface ProjectManageService {

	List<ProjectManageMemberDTO> findProjectMember(int projectNo);

}
