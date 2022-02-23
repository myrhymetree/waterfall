package com.greedy.waterfall.member.model.service;

import java.util.List;
import java.util.Map;

import com.greedy.waterfall.common.exception.member.LoginFailedException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.AdminMemberDTO;
import com.greedy.waterfall.member.model.dto.MemberDTO;

public interface MemberService {

	MemberDTO findMember(MemberDTO member) throws LoginFailedException;

	List<AdminMemberDTO> findAdminMember(SelectCriteria selectCriteria);

	int selectTotalCount(Map<String, String> searchMap);

}
