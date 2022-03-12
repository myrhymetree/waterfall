package com.greedy.waterfall.project.model.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.project.model.dto.ProjectDTO;

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
	
	@Test
	public void findAllManageProject_test() {
		SelectCriteria sc = new SelectCriteria().builder().startRow(1).endRow(100).build();
		List<ProjectDTO> projectResultList = mapper.findAllManageProject(sc);
		
		for(int i = 0; i < projectResultList.size(); i++) {
//			System.out.println(i + " : " + projectResultList.get(i));
			System.out.println(projectResultList.get(i).getProjectInfo());
		}
		
	}
	
	
	

}
