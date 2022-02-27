package com.greedy.waterfall.member.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.exception.member.MemberRegistException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.DeptJobDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;

public interface MemberService {

	MemberDTO findMember(MemberDTO member) throws LoginFailedException;

	List<AdminMemberDTO> findAdminMember(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	Map<String, Object> findDeptJobService();

	List<TeamDTO> findTeamList(String deptCode);

	void adminMemberRegist(AdminMemberDTO adminMember) throws MemberRegistException;


}
