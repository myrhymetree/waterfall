package com.greedy.waterfall.common.History;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.stereotype.Component;

import com.greedy.waterfall.common.result.Result;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;

@Component("projectHistory")
public class ProjectHistory implements History{

	@Override
	public List<ProjectHistoryDTO> registHistory(Object info) {
		 
		MemberDTO findAdminInfo = (MemberDTO) ((Map<String, Object>) info).get("findAdminInfo");
		RegistProjectDTO newProject = (RegistProjectDTO) ((Map<String, Object>) info).get("newProject");
		List<ProjectHistoryDTO> projectRegistHistory = new ArrayList<ProjectHistoryDTO>();
		/* 프로젝트가 생성되었습니다. */
		/* 누가 프로젝트에 참여했습니다.*/
		/* 누가 pm역할을 배정받았습니다. */
		projectRegistHistory.add(new ProjectHistoryDTO().builder()
				.projectNo(newProject.getProjectNo())
				.managerNo(newProject.getAdminNo())
				.contentType(1)
				.content("[" + findAdminInfo.getName() + "]님이 [" + newProject.getProjectName() + "]프로젝트를 [생성]했습니다.")
				.build());

		projectRegistHistory.add(new ProjectHistoryDTO().builder()
				.projectNo(newProject.getProjectNo())
				.managerNo(newProject.getAdminNo())
				.contentType(1)
				.content("[" + findAdminInfo.getName() + "]님이 [" + newProject.getProjectName() + "]에 [" + newProject.getPmName() + "]님을 PM으로 등록했습니다.")
						.build());
		return projectRegistHistory;
	}

	@Override
	public List<ProjectHistoryDTO> modifyHistory(Object info) {
		RegistProjectDTO oldProject = (RegistProjectDTO) ((Map<String, Object>) info).get("oldProject"); 
		RegistProjectDTO newProject = (RegistProjectDTO) ((Map<String, Object>) info).get("newProject");
		List<ProjectHistoryDTO> projectHistoryList = new ArrayList<>();
		
		if(!oldProject.getProjectName().equals(newProject.getProjectName())) {
			projectHistoryList.add(new ProjectHistoryDTO().builder()
										.projectNo(newProject.getProjectNo())
										.managerNo(newProject.getAdminNo())
										.content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 이름을 [" + newProject.getProjectName() + "](으)로 수정했습니다.")
										.build());
		}
		if(oldProject.getPmNumber() != newProject.getPmNumber()) {
			projectHistoryList.add(new ProjectHistoryDTO().builder()
					.projectNo(newProject.getProjectNo())
					.managerNo(newProject.getAdminNo())
					.content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 PM을 [" + oldProject.getPmName() + "]님 에서 [" + newProject.getPmName() + "]님으로 변경했습니다.")
					.build());
		}
		if(!oldProject.getStartDate().equals(newProject.getStartDate())) {
			projectHistoryList.add(new ProjectHistoryDTO().builder()
					.projectNo(newProject.getProjectNo())
					.managerNo(newProject.getAdminNo())
					.content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 시작일을 [" + oldProject.getStartDate() + "]에서 [" + newProject.getStartDate() +"]로 변경했습니다.")
					.build());
		}
		if(!oldProject.getDeadLine().equals(newProject.getDeadLine())) {
			projectHistoryList.add(new ProjectHistoryDTO().builder()
					.projectNo(newProject.getProjectNo())
					.managerNo(newProject.getAdminNo())
					.content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 마감일을 [" + oldProject.getDeadLine() + "]에서 [" + newProject.getDeadLine() +"]로 변경했습니다.")
					.build());
		}
		if(!oldProject.getProjectStatusCode().equals(newProject.getProjectStatusCode())) {
			projectHistoryList.add(new ProjectHistoryDTO().builder()
					.projectNo(newProject.getProjectNo())
					.managerNo(newProject.getAdminNo())
					.content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 상태를 [" + oldProject.getProjectStatusName() + "]에서 [" + newProject.getProjectStatusName() +"]로 변경했습니다.")
					.build());
		}
		if(oldProject.getProgression() != newProject.getProgression()) {
			projectHistoryList.add(new ProjectHistoryDTO().builder()
					.projectNo(newProject.getProjectNo())
					.managerNo(newProject.getAdminNo())
					.content("[" + newProject.getAdminName() + "]님이 프로젝트 [" + oldProject.getProjectName() + "]의 진행률을 [" + oldProject.getProgression() + "%]에서 [" + newProject.getProgression() +"%]로 변경했습니다.")
					.build());
		}
		
		

		
		for(int i = 0; i < projectHistoryList.size(); i++) {
			System.out.println("projectHistory at modify : "+ projectHistoryList.get(i));
		}
		
		return null;
	}

	@Override
	public List<ProjectHistoryDTO> removeHistory(Object info) {
		RegistProjectDTO projectInfo = (RegistProjectDTO) info;
		
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		
		ProjectHistoryDTO history = new ProjectHistoryDTO().builder()
										.projectNo(projectInfo.getProjectNo())
										.managerNo(projectInfo.getno)
										.contentType(1)
										.content("[" + )
										.build();
		
		RegistProjectDTO(projectNo=56, projectName=워터폴ㅇl, startDate=2022-01-01, 
				deadLine=2022-04-03, pmNumber=46, pmName=홍성원, 
				projectStatusCode=PROGRESSING, projectStatusName=진행 중, 
				progression=0, dept=null, team=null, adminNo=0, adminName=null)
		
		projectRegistHistory.add(new ProjectHistoryDTO().builder()
				.projectNo(newProject.getProjectNo())
				.managerNo(newProject.getAdminNo())
				.contentType(1)
				.content("[" + findAdminInfo.getName() + "]님이 [" + newProject.getProjectName() + "]프로젝트를 [생성]했습니다.")
				.build());
		
		return null;
	}

	@Override
	public List<ProjectHistoryDTO> recoveryHistory(Object info) {

		return null;
	}

}
 