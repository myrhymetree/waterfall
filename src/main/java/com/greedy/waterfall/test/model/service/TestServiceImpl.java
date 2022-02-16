package com.greedy.waterfall.test.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.test.model.dao.TestMapper;
import com.greedy.waterfall.test.model.dto.TestDTO;

@Service
public class TestServiceImpl implements TestService {

	private final TestMapper mapper;
	
	@Autowired
	public TestServiceImpl(TestMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<TestDTO> findTest() {
		
		List<TestDTO> testList = mapper.findList();
		
		return testList;
	}
	
}
