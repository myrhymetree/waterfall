package com.greedy.waterfall.project.model.mapper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.waterfall.common.paging.SelectCriteria;
import com.greedy.waterfall.member.model.dto.MemberDTO;
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
	public void testFindOneProjectInfo() {
		 
	}
	

	@Test
	public void testKickOldPm() {
		 
	}
	

	@Test
	public void testProjectRoleRemove() {
		 
	}
	

	@Test
	public void testAssignPmRole() {
		 
	}
	

	@Test
	public void testFindMemberInProject() {
		 
	}


	@Test
	public void testJoinPmInProject() {
		 
	}
	@Test
	public void testRegistEntireHistoryProjectRegist() {
		 
	}

	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testFindAllManageProject() {
		SelectCriteria sc = new SelectCriteria().builder().startRow(1).endRow(100).build();
		List<ProjectDTO> projectResultList = mapper.findAllManageProject(sc);
		
		for(int i = 0; i < projectResultList.size(); i++) {
//			System.out.println(i + " : " + projectResultList.get(i));
			System.out.println(projectResultList.get(i).getProjectInfo());
		}	
		
	}

}
