package com.greedy.waterfall.issue.controller;

import static org.junit.Assert.*;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import junit.framework.TestCase;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/servlet-context.xml"
		"classpath:spring/root-context.xml"
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class IssueControllerTest {

	@Autowired
	private IssueService service;
	
	private IssueController controller;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new IssueController(service)).build();
	}
	
	@Test
	public void 경로_제대로_찾아가는지() {
		mvc.perform(get("/issue/list"))
			.andExpect(forwardedUrl("/issue/list"))
			.andExpect(status().isOk());
	}
	

}
