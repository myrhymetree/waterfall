package com.greedy.waterfall.menu.model.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNotNull;

import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.greedy.waterfall.project.model.dto.ProjectDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/servlet-context.xml",
		"classpath:spring/root-context.xml",
		"classpath:mybatis/mybatis-config.xml"
})
@WebAppConfiguration
public class MenuMapperTest {
	
	@Autowired
	private MenuMapper mapper;
	
	@Test
	public void 관리자_메인화면에_출력용_갯수가_제대로_출력되는지_확인() {
		int result = mapper.findProjectCount();
		
		assertThat(result, is(2));
	}
	
	@Test
	public void 없는_프로젝트_번호가_입력됐을때_NULL값이_반환되는지_확인() {
		ProjectDTO projectResult = mapper.findProjectInfo(1);
		
		 assertThat(projectResult, is(nullValue()));
	}
	
	@Test
	public void 프로젝트의_정보가_제대로_조회되는지_확인() {
		ProjectDTO projectInfo = new ProjectDTO().builder()
									.no(55)
									.name("새로운프로젝트")
									.progression(9)
									.build();
		ProjectDTO projectResult = mapper.findProjectInfo(55);
		
		assertThat(projectResult.getNo(), is(projectInfo.getNo()));
		assertThat(projectResult.getName(), is(projectInfo.getName()));
	}
	

}
