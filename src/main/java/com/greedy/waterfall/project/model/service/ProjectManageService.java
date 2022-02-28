package com.greedy.waterfall.project.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

public interface ProjectManageService {

	Map<String, Object> findProjectMember(Map<String, String> searchMap);

	boolean registProjectMember(ProjectManageMemberDTO registInfo);

	List<MemberDTO> findTeamMember(Map<String, String> parameter);

	List<ProjectRoleDTO> findMemberRole(Map<String, Integer> memberInfo);

	boolean modifyProjectMember(ProjectManageMemberDTO modifyInfo);

}
