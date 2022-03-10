package com.greedy.waterfall.project.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.service.ProjectService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/servlet-context.xml",
        "classpath:spring/root-context.xml",
        "classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class ProjectControllerTest {

    @Autowired 
    ProjectService service;
    private MockMvc mvc;
    private RegistProjectDTO project;
    
    @Before
	public void setUp() throws Exception {
		
		project = new RegistProjectDTO().builder()
                .projectName("테스트코드작성")
                .startDate(java.sql.Date.valueOf("2012-03-15"))
                .deadLine(java.sql.Date.valueOf("2012-03-16"))
                .pmNumber(43)
                .projectStatusCode("PROGRESSING")
                .progression(1)
                .adminNo(1)
                .build(); 
		
	}
    
    @Test
    @Transactional
    public void controller_test_test() throws Exception {
        this.mvc.perform(
                post("/project/regist")
                .param("projectName", "테스트코드작성")
//                .param("startDate", java.sql.Date.valueOf("2012-03-15") )
                .param("pmNumber", "43")
                .param("projectStatusCode", "PROGRESSING")
                .param("progression", "1")
                .param("adminNo", "1"))
        .andExpect(redirectedUrl("managelist"))
        .andExpect(model().attribute("newProject", project));
                
    }
    
    

	@Test
	public void testProjectController() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendRegistPage() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistProject() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendManageProjectList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindManageProjectList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindProjectList() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindTeam() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindProjectMainBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindTeamMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindProjectDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyProject() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendProjectDetail() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveProject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestoreProject() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProject() {
		fail("Not yet implemented");
	}

}
