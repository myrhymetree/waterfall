package com.greedy.waterfall.common.History;

import java.util.ArrayList;
import java.util.List;

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
		
		String roleName = "[ ";
				
		if(roleList != null) {
			for(int i = 0; i < roleList.size(); i++) {
				roleName += roleList.get(i).getRoleName() + " ";
			}
		}
		roleName += "]";
		System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());System.out.println("historyInfo.getManagerNo():"+historyInfo.getManagerNo());
		
		
		List<ProjectHistoryDTO> historyList = new ArrayList<>();
		
		historyList.add(new ProjectHistoryDTO().builder()
				.projectNo(historyInfo.getProjectNo())
				.managerNo(historyInfo.getManagerNo())
				.contentType(5)
				.content("[" + historyInfo.getManagerName() + "]님이 [" + historyInfo.getProjectName() + "]프로젝트에 [" + historyInfo.getMemberName() +"]님을 " + roleName + "역할로 배정했습니다.")
				.build());
		
		return historyList;
	}

	@Override
	public List<ProjectHistoryDTO> modifyHistory(Object info) {
		/* [홍성원]님이 [김진후]님을 [A B C]에서 [A B C D E]로 역할을 변경했습니다. */
		return null;
	}

	@Override
	public List<ProjectHistoryDTO> removeHistory(Object info) {
		/* [홍성원]님이 [김진후]님을 [프로젝트]에서 내보냈습니다. */
		return null;
	}

	@Override
	public List<ProjectHistoryDTO> recoveryHistory(Object info) {

		return null;
	}

}
