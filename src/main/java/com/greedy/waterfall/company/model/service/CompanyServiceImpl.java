package com.greedy.waterfall.company.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dao.CompanyMapper;
import com.greedy.waterfall.company.model.dto.CompanyDTO;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyMapper mapper;
	
	@Autowired
	public CompanyServiceImpl(CompanyMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<CompanyDTO> findCompany(SelectCriteria selectCriteria) {

		List<CompanyDTO> companyList = mapper.findCompany(selectCriteria);

		return companyList;
	}
}
