package com.greedy.waterfall.project.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.waterfall.project.model.dto.MyProjectDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;
import com.greedy.waterfall.project.model.mapper.ProjectMapper;

/**
 * <pre>
 * Class : ProjectServiceImpl
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectMapper mapper;
	
	@Autowired
	public ProjectServiceImpl(ProjectMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public MyProjectDTO findMyProject(int no) {
		
		List<ProjectDTO> manageProject = mapper.findManagaProject(no);
		List<ProjectDTO> joinProject = mapper.findJoinProject(no);
		
		MyProjectDTO projectList = new MyProjectDTO().builder()
				.no(no)
				.manageProject(manageProject)
				.joinProject(joinProject)
				.build();
		
		
		return projectList;
	}

}
