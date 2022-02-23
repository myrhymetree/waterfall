package com.greedy.waterfall.project.model.service;

import java.util.ArrayList;
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
	public MyProjectDTO findMyProject(MemberDTO member) {
		List<ProjectDTO> manageProject = new ArrayList<ProjectDTO>();
		List<ProjectDTO> joinProject = new ArrayList<ProjectDTO>();
		
		if(member != null) {
			System.out.println("session에 member가 저장돼있어서 프로젝트를 조회중입니다....");
			System.out.println("현재 session member의 역할 : " + member.getRole());
			if("1".equals(member.getRole())) {
				System.out.println("관리자 입니다.");
				manageProject = mapper.findAllProject();
			} else {
				System.out.println("관리자가 아닙니다.");
				manageProject = mapper.findManagaProject(member.getNo());
				joinProject = mapper.findJoinProject(member.getNo());
				
			}
		}
		MyProjectDTO projectList = new MyProjectDTO().builder()
													.manageProject(manageProject)
													.joinProject(joinProject)
													.build();
		System.out.println("조회된 프로젝트 리스트");
		System.out.println("projectList : " + projectList);
		
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

	/**
	 * findPmNumber : 프로젝트 목록페이지에서 프로젝트 메인페이지로 이동할 떄 해당 프로젝트의 pm번호를 조회한다.
	 * @param 프로젝트번호를 전달받는다.
	 * @return 프로젝트 pm의 회원번호를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public int findPmNumber(int projectNo) {
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		System.out.println("projectNo : " + projectNo);
		return mapper.finePmNumber(projectNo);
	}
}

































