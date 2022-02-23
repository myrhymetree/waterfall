package com.greedy.waterfall.company.model.dao;

import java.util.List;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.company.model.dto.CompanyDTO;

public interface CompanyMapper {

	List<CompanyDTO> findCompany(SelectCriteria selectCriteria);

}
