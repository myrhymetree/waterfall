package com.greedy.waterfall.company.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.exception.company.JobModifyException;
import com.greedy.waterfall.common.exception.company.JobRegistException;
import com.greedy.waterfall.common.exception.company.JobRemoveException;
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
	
	/* 직급 등록 */
	@Override
	public void registJob(JobDTO job) throws JobRegistException {

		int result = mapper.insertJob(job);
		
		if(!(result > 0)) {
			throw new JobRegistException("직급 등록에 실패하셨습니다.");
		}
	}
	
	/* 직급 상세 */
	@Override
	public JobDTO detailJob(String code) {

		JobDTO jobDetail = null;
		
		jobDetail = mapper.detailJob(code);
		
		return jobDetail;
	}
	
	/* 직급 수정 */
	@Override
	public void modifyJob(JobDTO job) throws JobModifyException {

		int result = mapper.updateJob(job);
		
		if(!(result > 0)) {
			throw new JobModifyException("직급 수정에 실패하셨습니다.");
		}
	}
	
	/* 직급 삭제 */
	@Override
	public void removeJob(String code) throws JobRemoveException {

		int result = mapper.deleteJob(code);
		
		if(!(result > 0)) {
			throw new JobRemoveException("직급 삭제에 실패하셨습니다.");
		}
	}

}
