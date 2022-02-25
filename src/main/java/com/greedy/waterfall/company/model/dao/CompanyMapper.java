package com.greedy.waterfall.company.model.dao;

import java.util.List;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;

public interface CompanyMapper {

	List<DeptDTO> findDept(SelectCriteria selectCriteria);

	List<JobDTO> findJob(SelectCriteria selectCriteria);

	int insertJob(JobDTO job);

}
