package com.greedy.waterfall.issue.model.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class IssueMapperTest {
	
	@Autowired
	private IssueMapper mapper;
	
	@Test
	public void test() {
		
		int result = mapper.selectAllIssue();
		
		assertThat(result, is(6));
	}
	
}