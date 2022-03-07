package com.greedy.waterfall.menu.model.service;

import java.util.Map;

import com.greedy.waterfall.menu.model.dto.MainInfoDTO;
import com.greedy.waterfall.project.model.dto.ProjectDTO;

public interface MenuService {

	public Map<String, Object> findMainProjectList(MainInfoDTO mainInfo);

	public Map<String, Object> findAdminPageInfo(int projectNo);

	public ProjectDTO findJoinProjectInfo(Map<String, Integer> searchMap);

}
