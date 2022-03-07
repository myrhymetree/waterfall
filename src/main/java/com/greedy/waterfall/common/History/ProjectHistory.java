package com.greedy.waterfall.common.History;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;

@Component("projectHistory")
public class ProjectHistory implements History{

	@Override
	public List<ProjectHistoryDTO> registHistory(MemberDTO findAdminInfo, Object info) {
		RegistProjectDTO newProject = (RegistProjectDTO) info;
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
	public List<ProjectHistoryDTO> modifyHistory(MemberDTO member, Object info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectHistoryDTO> deleteHistory(MemberDTO member, Object info) {
		// TODO Auto-generated method stub
		return null;
	}

}
