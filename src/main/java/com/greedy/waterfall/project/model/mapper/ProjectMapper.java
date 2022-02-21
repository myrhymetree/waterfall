package com.greedy.waterfall.project.model.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.greedy.waterfall.project.model.dto.ProjectDTO;

/**
 * <pre>
 * Class : ProjectMapper
 * Comment : 클래스 설명 작성부분
 * 
 * History
 * 2022. 2. 20.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
@Repository
public interface ProjectMapper {

	List<ProjectDTO> findManagaProject(int no);

	List<ProjectDTO> findJoinProject(int no);

}
