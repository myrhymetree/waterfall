package com.greedy.waterfall.project.model.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greedy.waterfall.common.paging.Paging;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.mapper.ProjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class ProjectServiceImplTest {

	@Autowired
	private ProjectMapper mapper;

	
	@Test
	public void test() {
		
		
		RegistProjectDTO test =  mapper.findOneProjectInfo(56);
		
		System.out.println(test);
	
		assertNotNull("d");
		
		
	}
	
	
	
	
	
	
	
	
}
