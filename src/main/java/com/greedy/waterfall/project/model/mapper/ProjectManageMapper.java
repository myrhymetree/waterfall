package com.greedy.waterfall.project.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

@Repository
public interface ProjectManageMapper {

	List<ProjectManageMemberDTO> findProjectMember(SelectCriteria selectCriteria);

	int findProjectMemberCount(Map<String, String> searchMap);
	
	List<ProjectRoleDTO> findAllRole();
	
	List<DeptDTO> findAllDept();

	int registMemberToProject(ProjectManageMemberDTO registInfo);

	int registRoleToProject(Map<String, Integer> map);

	List<MemberDTO> findNewMember(Map<String, String> parameter);

	List<ProjectRoleDTO> findMemberRole(Map<String, Integer> memberInfo);

	int removeOldRole(ProjectManageMemberDTO modifyInfo);

	int removeMemberRole(Map<String, Integer> removeInfo);

	int removeMemberJoinHistory(Map<String, Integer> removeInfo);

	
}
