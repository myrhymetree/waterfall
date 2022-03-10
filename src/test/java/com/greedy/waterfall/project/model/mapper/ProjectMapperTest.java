package com.greedy.waterfall.project.model.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

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
public class ProjectMapperTest {

	@Autowired
	private ProjectMapper mapper;
	
	@Test
	public void test() {
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("searchCondition", "projectName");
		searchMap.put("searchValue", "sdfafdsafdas");
		
		Integer i = mapper.findAllManageProjectCount(searchMap);
		
		Integer nulltest = null;
		if(nulltest == null) {
			nulltest = 0;
		}
		int i2 = nulltest;
		System.out.println(i2);
		
		assertThat(i, is(0));
	}

}
