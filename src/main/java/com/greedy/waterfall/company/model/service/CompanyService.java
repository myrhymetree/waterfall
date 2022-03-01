package com.greedy.waterfall.company.model.service;

import java.util.List;

import com.greedy.waterfall.common.exception.company.JobModifyException;
import com.greedy.waterfall.common.exception.company.JobRegistException;
import com.greedy.waterfall.common.exception.company.JobRemoveException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;

public interface CompanyService {

	List<DeptDTO> findDept(SelectCriteria selectCriteria);

	List<JobDTO> findJob(SelectCriteria selectCriteria);

	void registJob(JobDTO job) throws JobRegistException;

	JobDTO detailJob(String code);

	void modifyJob(JobDTO job) throws JobModifyException;

	void removeJob(String code) throws JobRemoveException;

}
