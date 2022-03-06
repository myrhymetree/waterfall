package com.greedy.waterfall.menu.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringReader;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.greedy.waterfall.menu.model.service.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class MenuControllerTest {

//	@Autowired
//	protected WebApplicationContext context;
	
	@Autowired
	private MenuService menuService;
	private MockMvc mockMvc;
	private MenuController menuController;
	@Before
	public void setUp() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mockMvc = MockMvcBuilders.standaloneSetup(new MenuController(menuService)).build();
	}
	
	@Test
	public void testMenuController() throws Exception {
		mockMvc.perform(get("/menu/main"))
				.andExpect(forwardedUrl("/main/mainPage"))
				.andExpect(model().attributeExists("projectList"))
				.andExpect(model().attributeExists("selectCriteria"))
				.andExpect(model().attribute("intent", "/menu/main"))
				.andExpect(status().isOk());
	
	
		
	}

}
