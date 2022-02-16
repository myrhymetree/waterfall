package com.greedy.waterfall.test.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.test.model.dto.TestDTO;

@Repository
public interface TestMapper {

	public List<TestDTO> findList();

}
