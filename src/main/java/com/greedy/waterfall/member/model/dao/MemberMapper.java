package com.greedy.waterfall.member.model.dao;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;

public interface MemberMapper {

	String selectEncryptedPwd(MemberDTO member);

	MemberDTO selectMember(MemberDTO member);

	List<AdminMemberDTO> findAdminMemberList(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

}
