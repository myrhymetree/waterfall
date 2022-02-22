package com.greedy.waterfall.project.model.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;

/**
 * <pre>
 * Class : ProjectMapper
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Repository
public interface ProjectMapper {

	List<ProjectDTO> findManagaProject(int no);

	List<ProjectDTO> findJoinProject(int no);
	
	List<ProjectStatusDTO> findAllProjectStatus();

	List<DeptDTO> findAllDept();
	
	List<TeamDTO> findTeam(String deptCode);

	List<MemberDTO> findTeamMember(String teamCode);

	//프로젝트 생성관련 
	int registProject(RegistProjectDTO newProject);

	int registPm(RegistProjectDTO newProject);

	int registMemberProject(RegistProjectDTO newProject);
	
}
