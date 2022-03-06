package com.greedy.waterfall.menu.model.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.menu.model.mapper.MenuMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class MenuServiceImplTest {

	@Mock
	private MenuMapper mapper;
	
	@Autowired
	private MenuService service;
	
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testMenuServiceImpl() {
		fail("Not yet implemented");
	}

	@Test
	public final void testFindMainProjectList() {
		fail("Not yet implemented");
	}

	@Test
	public final void testFindAdminPageInfo() {
		fail("Not yet implemented");
	}

}
