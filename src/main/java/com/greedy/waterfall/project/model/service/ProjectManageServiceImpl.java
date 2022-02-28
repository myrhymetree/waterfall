package com.greedy.waterfall.project.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.project.model.dto.DeptDTO;
import com.greedy.waterfall.project.model.dto.ProjectManageMemberDTO;
import com.greedy.waterfall.project.model.dto.ProjectRoleDTO;
import com.greedy.waterfall.project.model.mapper.ProjectManageMapper;

@Service
public class ProjectManageServiceImpl implements ProjectManageService{

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
		if(registMemberToProject(registInfo) && registRoleToProject(registInfo)) {
			return true;
		}
		
		/* 인원배정과 역할배정내역 등록에 실패하면 실패값을 반환한다. */
		return false;
	}
	
	/**
	 * registMemberToProject : 개발자를 프로젝트 배정내역에 추가한다.
	 * @param 개발자의 번호와 프로젝트의 번호를 전달받는다.
	 * @return 배정 성공여부를 반환한다.
	 * 
	 * @author 홍성원
	 */
	private boolean registMemberToProject(ProjectManageMemberDTO registInfo) {
		boolean isSuccess = false;
		
		if(mapper.registMemberToProject(registInfo)> 0) {
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
		boolean isSuccess = false;
		
		/* 역할 부여받은 갯수만큼 반복문을 돌려서 , 그만큼 insert 해야 성공 */
		int resultCount = 0;
		for(int i = 0; i < registInfo.getRole().size(); i++) {
			resultCount += mapper.registRoleToProject(parsingRegistInfo(registInfo, i));
		}
		
		return resultRegistRole(registInfo.getRole().size(), resultCount);
	}

	private Map<String, Integer> parsingRegistInfo(ProjectManageMemberDTO registInfo, int i) {

		Map<String, Integer> registRoleInfo  = new HashMap<String, Integer>();
		registRoleInfo.put("memberNo",  registInfo.getMemberNo());
		registRoleInfo.put("roleNo",  registInfo.getRole().get(i).getRoleNo());
		registRoleInfo.put("projectNo",  registInfo.getProjectNo());
		registRoleInfo.put("managerNo",  registInfo.getManagerNo());
		
		
		return registRoleInfo;
	}

	/**
	 * resultRegistRole : 메소드 설명 작성 부분
	 * @param 매개변수의 설명 작성 부분
	 * @return 리턴값의 설명 작성 부분
	 * 
	 * @author 홍성원
	 */
	private boolean resultRegistRole(int result, int count) {
		if(result == count) {
			
			return true;
		}
		
		return false;
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

}


























































