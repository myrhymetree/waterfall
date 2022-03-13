package com.greedy.waterfall.project.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.waterfall.board.controller.MeetingController;
import com.greedy.waterfall.project.model.dto.RegistProjectDTO;
import com.greedy.waterfall.project.model.service.ProjectService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/servlet-context.xml",
        "classpath:spring/root-context.xml",
        "classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class ProjectControllerTest implements ProjectControllerView {

    @Autowired 
    @Qualifier("stubProjectService")
    private ProjectService service;
    private MockMvc mvc;
    
    @Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new ProjectController(service)).build();
	}
    
    @Test
    public void testSendRegistPage() throws Exception {
        mvc.perform(get("/regist"))
        	.andExpect(forwardedUrl(PROJECT_REGIST_VIEW))
        	.andExpect(model().size(2))
        	.andExpect(model().attributeExists("statusList"))
        	.andDo(print());
    }
    
    

	@Test
	public void testProjectController() {
	}

	@Test
	public void testRegistProject() {
		 
	}

	@Test
	public void testSendManageProjectList() {
		 
	}

	@Test
	public void testFindManageProjectList() {
		 
	}

	@Test
	public void testFindProjectList() {
		 
	}

	@Test
	public void testFindTeam() {
		 
	}

	@Test
	public void testFindProjectMainBoard() {
		 
	}

	@Test
	public void testFindTeamMember() {
		 
	}

	@Test
	public void testFindProjectDetail() {
		 
	}

	@Test
	public void testModifyProject() {
		 
	}

	@Test
	public void testSendProjectDetail() {
		 
	}

	@Test
	public void testRemoveProject() {
		 
	}

	@Test
	public void testRestoreProject() {
		 
	}

	@Test
	public void testDeleteProject() {
		 
	}

}
