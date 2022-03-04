package com.greedy.waterfall.company.model.dao;

import java.util.List;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;
import com.greedy.waterfall.company.model.dto.TeamDTO;

public interface CompanyMapper {

	List<DeptDTO> findDept(SelectCriteria selectCriteria);

	int insertDept(DeptDTO dept);

	List<TeamDTO> findTeam(SelectCriteria selectCriteria);

	int insertTeam(TeamDTO team);
	
	List<JobDTO> findJob(SelectCriteria selectCriteria);

	int insertJob(JobDTO job);

	JobDTO detailJob(String code);

	int updateJob(JobDTO job);

	int deleteJob(String code);

}
