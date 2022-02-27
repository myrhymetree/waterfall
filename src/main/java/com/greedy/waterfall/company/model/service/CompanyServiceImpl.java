package com.greedy.waterfall.company.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.company.JobRegistException;
import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dao.CompanyMapper;
import com.greedy.waterfall.company.model.dto.DeptDTO;
import com.greedy.waterfall.company.model.dto.JobDTO;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyMapper mapper;
	
	@Autowired
	public CompanyServiceImpl(CompanyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<DeptDTO> findDept(SelectCriteria selectCriteria) {

		List<DeptDTO> deptList = mapper.findDept(selectCriteria);

		return deptList;
	}

	@Override
	public List<JobDTO> findJob(SelectCriteria selectCriteria) {

		List<JobDTO> jobList = mapper.findJob(selectCriteria);
		
		return jobList;
	}

	@Override
	public void registJob(JobDTO job) throws JobRegistException {

		int result = mapper.insertJob(job);
		
		if(!(result > 0)) {
			throw new JobRegistException("직급 등록에 실패하셨습니다.");
		}
	}

}
