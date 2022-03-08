package com.greedy.waterfall.task.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.greedy.waterfall.output.controller.OutputController;
import com.greedy.waterfall.output.model.service.OutputService;
import com.greedy.waterfall.task.model.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml",
})
@WebAppConfiguration
public class TaskControllerTest {
	
	
	@Autowired
	private TaskService tasktService;
	private TaskController controller;
	private MockMvc mvc;
	
	@org.junit.Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new TaskController(tasktService)).build();
	}
	
	//주소를 제대로 보내는지, 원하는 값을 제대로 보내는지
	
	@Test
	public void 경로() throws Exception {
		mvc.perform(get("/task/timeline" ))
		.andExpect(forwardedUrl("task/taskTimeLine"))
		.andExpect(status().isOk());
	}
}
