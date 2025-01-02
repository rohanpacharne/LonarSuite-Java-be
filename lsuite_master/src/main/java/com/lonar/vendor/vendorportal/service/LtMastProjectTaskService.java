package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;

public interface LtMastProjectTaskService {
	
	
	public String checkProjectTaskValuesDetails(LtMastProjectTasks ltMastProjectTasks);
	
	public List<LtMastProjectTasks> findByProjectId(Long projectId) throws Exception;

}
