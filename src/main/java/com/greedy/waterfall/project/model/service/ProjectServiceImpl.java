package com.greedy.waterfall.project.model.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectStatusDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.dto.TeamDTO;
import com.greedy.waterfall.project.model.mapper.ProjectMapper;

/**
 * <pre>
 * Class : ProjectServiceImpl
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectMapper mapper;
	
	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public MyProjectDTO findMyProject(int no) {
		
		List<ProjectDTO> manageProject = mapper.findManagaProject(no);
		List<ProjectDTO> joinProject = mapper.findJoinProject(no);
		
		MyProjectDTO projectList = new MyProjectDTO().builder()
				.no(no)
				.manageProject(manageProject)
				.joinProject(joinProject)
				.build();
		
		
		return projectList;
	}

	@Override
	public Map<String, Object> findRegistForm() {

		List<ProjectStatusDTO> statusList = mapper.findAllProjectStatus();

		List<DeptDTO> deptList = mapper.findAllDept();
		
		Map<String, Object> projectForm = new HashedMap();
		
		projectForm.put("statusList", statusList);
		projectForm.put("deptList", deptList);
		
		return projectForm;
	}
	
	@Override
	public List<TeamDTO> findTeam(String deptCode){
		
		return mapper.findTeam(deptCode);
	}

	@Override
	public List<MemberDTO> findTeamMember(String teamCode) {
		
		return mapper.findTeamMember(teamCode);
	}

	@Override
	public boolean registProject(RegistProjectDTO newProject) {

		int registProjectdResult = mapper.registProject(newProject);
		int pmRegistResult = mapper.registPm(newProject);
		int memberRegistResult = mapper.registMemberProject(newProject);

		if(registProjectdResult > 0 && pmRegistResult > 0 && memberRegistResult > 0) {

			return true;
		}
		
		return false;
	}
}

































