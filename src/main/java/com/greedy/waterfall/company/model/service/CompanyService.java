package com.greedy.waterfall.company.model.service;

import java.util.List;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.CompanyDTO;

public interface CompanyService {

	List<CompanyDTO> findCompany(SelectCriteria selectCriteria);

}
