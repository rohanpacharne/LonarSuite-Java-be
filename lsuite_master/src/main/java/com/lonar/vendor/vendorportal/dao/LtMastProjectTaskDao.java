package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;

public interface LtMastProjectTaskDao {
	
	public List<LtMastProjectTasks> checkProjectTaskValuesDetails(LtMastProjectTasks ltMastProjectTasks);
	
	public List<LtMastProjectTasks> findByProjectId(Long projectId) throws Exception;

}
