package com.greedy.waterfall.common.paging;

import java.util.Map;

public interface Paging {

	SelectCriteria setPagingCondition(Map<String, String> searchMap);
	
	SelectCriteria setPagingCondition(Map<String, String> searchMap, PagingDTO pageSetting);

	SelectCriteria setSubPagingCondition(Map<String, String> searchMap, PagingDTO pagingSetting);


}
