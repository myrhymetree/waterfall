package com.greedy.waterfall.member.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.DeptDTO;
import com.greedy.waterfall.member.model.dto.DeptJobDTO;
import com.greedy.waterfall.member.model.dto.JobDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;
import com.greedy.waterfall.member.model.dto.TeamDTO;

public interface MemberMapper {

	String selectEncryptedPwd(MemberDTO member);

	MemberDTO selectMember(MemberDTO member);

	List<AdminMemberDTO> findAdminMemberList(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

	List<DeptDTO> findDept();

	List<JobDTO> findJob();

	List<TeamDTO> findTeamList(String deptCode);

	int adminMemberRegist(AdminMemberDTO adminMember);

	int deptMemberRegist(AdminMemberDTO adminMember);

	int teamMemberRegist(AdminMemberDTO adminMember);

	int jobMemberRegist(AdminMemberDTO adminMember);

	int memberRegist(AdminMemberDTO adminMember);


}
