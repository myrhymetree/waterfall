package com.greedy.waterfall.common.History;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;

@Component("memberHistory")
public class ProjectMemberHistory implements History{

	@Override
	public List<ProjectHistoryDTO> registHistory(Object info) {
		/* 프로젝트에 인원배정됐을때 등록될 히스토리 */
		ProjectManageMemberDTO historyInfo = (ProjectManageMemberDTO) info;

		List<ProjectRoleDTO> roleList = historyInfo.getRole();
		
		String roleName = roleString(roleList);

		System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());
		
		
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		
		historyList.add(new ProjectHistoryDTO().builder().content("[" + historyInfo.getManagerName() + "]님이 [" + historyInfo.getProjectName() + "]프로젝트에 [" + historyInfo.getMemberName() +"]님을 " + roleName + "역할로 배정했습니다.")
				.projectNo(historyInfo.getProjectNo()).managerNo(historyInfo.getManagerNo()).contentType(MEMBER_HISTORY).build());
		
		return historyList;
	}

	@Override
	public List<ProjectHistoryDTO> modifyHistory(Object info) {
		Map<String, Object> historyInfo = (Map<String, Object>) info;
		ProjectManageMemberDTO oldInfo = (ProjectManageMemberDTO) historyInfo.get("oldInfo"); 
		ProjectManageMemberDTO newInfo = (ProjectManageMemberDTO) historyInfo.get("newInfo"); 
		
		String oldRole = roleString(oldInfo.getRole());
		String newRole = roleString(newInfo.getRole());
		
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		
		historyList.add(new ProjectHistoryDTO().builder().content("[" + oldInfo.getManagerName() + "]님이 [" + oldInfo.getMemberName() + "]님의 역할을 " + oldRole +" 에서  " + newRole + "로 수정했습니다.")
				.projectNo(oldInfo.getProjectNo()).managerNo(oldInfo.getManagerNo()).contentType(MEMBER_HISTORY).build());
		
		return historyList;
	}

	@Override
	public List<ProjectHistoryDTO> removeHistory(Object info) {
		ProjectManageMemberDTO historyInfo = (ProjectManageMemberDTO) info;
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		
		historyList.add(new ProjectHistoryDTO().builder().content("[" + historyInfo.getManagerName() + "]님이 [" + historyInfo.getProjectName() + "]프로젝트에서 [" + historyInfo.getMemberName() +"]님을 내보냈습니다.")
				.projectNo(historyInfo.getProjectNo()).managerNo(historyInfo.getManagerNo()).contentType(MEMBER_HISTORY).build());
		
		return historyList;
	}

	@Override
	public List<ProjectHistoryDTO> recoveryHistory(Object info) {

		return null;
	}
	
	private String roleString(List<ProjectRoleDTO> roleList) {
		String roleName = "[ ";
		
		if(roleList != null) {
			for(int i = 0; i < roleList.size(); i++) {
				roleName += roleList.get(i).getRoleName() + " ";
			}
		}
		roleName += "]";
		
		return roleName;
	}
}