package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;

public interface ProjectManageService {

	Map<String, Object> findProjectMember(int projectNo);

}
