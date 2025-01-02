package com.lonar.vendor.vendorportal.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.dao.LtMastProjectTaskDao;
import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;

@Service
public class LtMastProjectTaskServiceImpl implements LtMastProjectTaskService {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LtMastProjectTaskDao ltMastProjectTaskDao;
	
	@Transactional
	@Override
	public String checkProjectTaskValuesDetails(LtMastProjectTasks ltMastProjectTasks) {
			String status = null;
	 
			List<LtMastProjectTasks> list = ltMastProjectTaskDao.checkProjectTaskValuesDetails(ltMastProjectTasks);
			for (LtMastProjectTasks projectTasks : list) {
	 
				if (projectTasks.getTaskCode().equals(ltMastProjectTasks.getTaskCode())) {
	 
					status = messageSource.getMessage("Task Code already Exists", null,
							"Task Code already Exists", Locale.getDefault());
				}
			}
			return status;
		}

	@Transactional
	@Override
	public List<LtMastProjectTasks> findByProjectId(Long projectId) throws Exception {
		return ltMastProjectTaskDao.findByProjectId(projectId);
	}
}
