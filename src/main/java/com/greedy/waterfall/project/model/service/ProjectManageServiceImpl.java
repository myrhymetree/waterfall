package com.greedy.waterfall.project.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.History.History;
import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.common.result.Result;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectHistoryDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;
import com.greedy.waterfall.project.model.mapper.ProjectManageMapper;

@Service
public class ProjectManageServiceImpl implements ProjectManageService{

	@Autowired
	@Qualifier("memberHistory")
	private History history;
	
	private final ProjectManageMapper mapper;
	private final Paging paging;

	@Autowired
	public ProjectManageServiceImpl(ProjectManageMapper mapper, Paging paging) {
		this.mapper = mapper;
		this.paging = paging;

	}
	
	@Override
	public Map<String, Object> findProjectMember(Map<String, String> searchMap) {

		Map<String, Object> manageProjectMemberInfo = new HashedMap();
		
		searchMap.put("totalCount", Integer.toString(mapper.findProjectMemberCount(searchMap)));
		SelectCriteria selectCriteria = paging.setPagingCondition(searchMap);
		
		List<ProjectManageMemberDTO> memberList = mapper.findProjectMember(selectCriteria); 
		List<ProjectRoleDTO> allRole = mapper.findAllRole();
		List<DeptDTO> allDept = mapper.findAllDept();
		
		manageProjectMemberInfo.put("memberList" , memberList);
		manageProjectMemberInfo.put("allRole", allRole);
		manageProjectMemberInfo.put("allDept", allDept);
		manageProjectMemberInfo.put("selectCriteria", selectCriteria);
		return manageProjectMemberInfo;
	}

	/**
	 * registProjectMember : 개발자를 역할을 부여해 프로젝트에 등록한다.
	 * @param 개발자의 정보와 역할 정보를 전달받는다.
	 * @return 등록 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	@Override
	public boolean registProjectMember(ProjectManageMemberDTO registInfo) {
		/* 프로젝트 인원배정내역에 등록한다. */
		/* 프로젝트 역할배정내역에 등록한다. */ 
		/* 인원배정과 역할배정내역 등록에 성공하면 성공값을 반환한다. */
		Result registProjectMember = new Result()
									.perform(mapper.registMemberToProject(registInfo))
									.and(registRoleToProject(registInfo));

		if(registProjectMember.result()) {
			ProjectManageMemberDTO historyInfo = mapper.findMemberInfo(registInfo);
			
		List<ProjectHistoryDTO> memberResigtHistory = history.registHistory(historyInfo);
			if(registHistoryResult(memberResigtHistory)) {
				
				return true;
			}
		
		}
		
		/* 인원배정과 역할배정내역 등록에 실패하면 실패값을 반환한다. */
		return false;
	}
	
	private boolean registHistoryResult(List<ProjectHistoryDTO> projectHistoryList) {
		Result registHistoryResult = new Result();
		for(int i = 0; i < projectHistoryList.size(); i++) {
			
			registHistoryResult.and(mapper.registEntireHistoryProjectRegist(projectHistoryList.get(i)));
		}
			
			return registHistoryResult.result();
	}
	
	
	
	
	private boolean isSuccess(int result) {
		boolean isSuccess = false;
		
		if(result > 0) {
			return true;
		}

		return isSuccess;
	}
	
	/**
	 * registRoleToProject : 개발자가 배정받은 프로젝트 내 역할을 프로젝트 역할배정 내역에 등록한다.
	 * @param 개발자의 번호와 역할번호, 프로젝트 번호를 전달받는다.
	 * @return 배정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	private boolean registRoleToProject(ProjectManageMemberDTO registInfo) {
		/* 역할 부여받은 갯수만큼 반복문을 돌려서 , 그만큼 insert 해야 성공 */
		Result registProjectRole = new Result();
		for(int i = 0; i < registInfo.getRole().size(); i++) {
			registProjectRole.perform(mapper.registRoleToProject(parsingRegistInfo(registInfo, i)));
		}
		
		return registProjectRole.result();
	}

	private Map<String, Integer> parsingRegistInfo(ProjectManageMemberDTO registInfo, int i) {

		Map<String, Integer> registRoleInfo  = new HashMap<String, Integer>();
		registRoleInfo.put("memberNo",  registInfo.getMemberNo());
		registRoleInfo.put("roleNo",  registInfo.getRole().get(i).getRoleNo());
		registRoleInfo.put("projectNo",  registInfo.getProjectNo());
		registRoleInfo.put("managerNo",  registInfo.getManagerNo());
		
		
		return registRoleInfo;
	}

	@Override
	public List<MemberDTO> findTeamMember(Map<String, String> parameter) {
		List<MemberDTO> list = mapper.findNewMember(parameter);
		
		return mapper.findNewMember(parameter);
	}

	@Override
	public List<ProjectRoleDTO> findMemberRole(Map<String, Integer> memberInfo) {

		return mapper.findMemberRole(memberInfo);
	}

	@Override
	public boolean modifyProjectMember(ProjectManageMemberDTO modifyInfo) {
		
		if(removeOldRole(modifyInfo) && registRoleToProject(modifyInfo)) {
			return true;
		}
		
		return false;
	}

	private boolean removeOldRole(ProjectManageMemberDTO modifyInfo) {
		
		return mapper.removeOldRole(modifyInfo) > 0 ? true: false;
	}

	@Override
	public boolean removeMemberInProject(Map<String, Integer> removeInfo) {

		/* 멤버의 역할내역 모두 삭제 && 멤버의 하차일 업데이트 */
		if(isSuccess(mapper.removeMemberRole(removeInfo)) && isSuccess(mapper.removeMemberJoinHistory(removeInfo))) {
			
			return true;
		}
		
		
		return false;
	}

}


























































