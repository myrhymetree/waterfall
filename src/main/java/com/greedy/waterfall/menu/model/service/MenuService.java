package com.greedy.waterfall.menu.model.service;

import java.util.Map;

public interface MenuService {

	public Map<String, Object> findMainProjectList(Map<String, String> searchMap);

	public Map<String, Object> findAdminPageInfo(int projectNo);

}
