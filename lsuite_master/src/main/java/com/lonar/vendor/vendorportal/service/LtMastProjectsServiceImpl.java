package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.dao.LtMastProjectDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.Customer;
import com.lonar.vendor.vendorportal.model.Employee;
import com.lonar.vendor.vendorportal.model.LtMastCustomersDto;
import com.lonar.vendor.vendorportal.model.LtMastDepartments;
import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ProjectWithTaskValues;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtMastProjectRepository;
import com.lonar.vendor.vendorportal.repository.LtMastProjectTasksRepository;

@Service
public class LtMastProjectsServiceImpl implements LtMastProjectsService,CodeMaster {
	
	@Autowired
	LtMastProjectDao ltMastProjectDao;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastProjectTasksRepository ltMastProjectTasksRepository;
	
	@Autowired
	LtMastProjectRepository ltMastProjectRepository;

	@Override
	public List<LtMastProjects> listAllActiveLtMastProjects() throws Exception {
		// TODO Auto-generated method stub
		return ltMastProjectDao.listAllActiveLtMastProjectDao();
	}
	
	@Override
	public Long getCount(LtMastProjects input,long companyId) throws Exception {
		// TODO Auto-generated method stub
		return ltMastProjectDao.getCount(input,companyId);
	}
	
	@Override
	public List<LtMastProjects> getLtMastProjectsDataTable(LtMastProjects input, long companyId) throws Exception {
		
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{ input.setColumnNo(12); }
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{ input.setColumnNo(13); }
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{ input.setColumnNo(14); }
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{ input.setColumnNo(15); }
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{ input.setColumnNo(16); }
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{ input.setColumnNo(17); }
		return ltMastProjectDao.getLtMastProjectsDataTable(input,companyId);
	}
	
	@Transactional
	@Override
	public String isFeildsExists(LtMastProjects ltMastProjects)
			throws Exception {
		
		String status = null;
		{
			List<LtMastProjects> projectList = ltMastProjectDao.isFeildsExists(ltMastProjects);
			for (LtMastProjects project : projectList)
			{
				
				if (ltMastProjects.getProjectNumber() == null
						|| ltMastProjects.getProjectNumber().equals(project.getProjectNumber()))
				{
					if (ltMastProjects.getProjectId() != project.getProjectId())
						status = messageSource.getMessage("Project Number already Exists", null,
								"Project Number already Exists", Locale.getDefault());
					else
						return status;
				}
				else if(ltMastProjects.getProjectName().equals(project.getProjectName()))
				{
					if (ltMastProjects.getProjectId() != project.getProjectId())
						status ="Project Name already Exists";
						else
							return status;
				}
			}
		}
		return status;
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return ltMastProjectDao.getAllEmployee();
	}
	
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return ltMastProjectDao.getAllCustomer();
	}
	
	@Override
	public List<LtMastCustomersDto> getCustomersLikeName(String name, Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltMastProjectDao.getCustomersLikeName(name, companyId);
	}
	
	@Override
	public List<LtMastProjects> getProjectsLikeName(String name,long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltMastProjectDao.getProjectsLikeName(name,companyId);
	}
	
	@Override
	public ProjectWithTaskValues getById(Long projectId) throws Exception {
		return ltMastProjectDao.getById(projectId);
	}
 
//	@Transactional
//	@Override
//	public Status updateMasterWithValue(ProjectWithTaskValues projectWithTaskValues) throws Exception
//	{
//		Status status = new Status();
//		LtMastProjects ltMastProjects = projectWithTaskValues.getLtMastProjects();
//		if(ltMastProjects.getStartDate()==null || ltMastProjects.getProjectName()==null ||
//				ltMastProjects.getLastUpdateLogin()==null )
//		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
//			if(status.getMessage()==null)
//			{
//				status.setCode(EXCEPTION);
//				status.setMessage("Error in finding message! The action was unsuccessful");
//			}
//			return status;
//		}
//		else
//		{
//			ltMastProjects.setLastUpdatedBy(ltMastProjects.getLastUpdateLogin());
//			ltMastProjects.setLastUpdateDate(new Date());
//			ltMastProjects = ltMastProjectRepository.save(ltMastProjects);
//			
//			List<LtMastProjectTasks> ltMastProjectTaskList = projectWithTaskValues.getLtMastProjectTaskValues();
//			if(!ltMastProjectTaskList.isEmpty())
//			{
//				for (LtMastProjectTasks ltMastProjectTasks : ltMastProjectTaskList)
//				{
//					if(ltMastProjectTasks.getProjectId()!=null)
//					{
//						
//						ltMastProjectTasks.setLastUpdateDate(new Date());
//						ltMastProjectTasks.setLastUpdateLogin(ltMastProjects.getLastUpdateLogin());
//						ltMastProjectTasks.setLastUpdatedBy(ltMastProjects.getLastUpdateLogin());
//						ltMastProjectTasksRepository.save(ltMastProjectTasks);
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
//						if(status.getMessage()==null)
//						{
//							status.setCode(EXCEPTION);
//							status.setMessage("Error in finding message! The action is completed successfully.");
//						}
//					}
//					else if(ltMastProjectTasks.getTaskId() == null )
//					{
//						ltMastProjectTasks.setCreationDate(new Date());
//						ltMastProjectTasks.setProjectId(ltMastProjects.getProjectId());
//						ltMastProjectTasks.setCreatedBy(ltMastProjectTasks.getLastUpdateLogin());
//						ltMastProjectTasks.setLastUpdateDate(new Date());
//						ltMastProjectTasks.setLastUpdateLogin(ltMastProjects.getLastUpdateLogin());
//						ltMastProjectTasks.setLastUpdatedBy(ltMastProjects.getLastUpdateLogin());
//						System.out.println("Above save task");
//						System.out.println("ltMastProjectTasks = "+ltMastProjectTasks);
//						ltMastProjectTasksRepository.save(ltMastProjectTasks);
//						System.out.println("Below save task");
//
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
//						if(status.getMessage()==null)
//						{
//							status.setCode(EXCEPTION);
//							status.setMessage("Error in finding message! The action is completed successfully.");
//						}
//					}
//					else
//					{
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
//						if(status.getMessage()==null)
//						{
//							status.setCode(EXCEPTION);
//							status.setMessage("Error in finding message! The action is completed unsuccessfully.");
//						}
//					}
//			
//					
//				}
//			}
//			status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
//			if(status.getMessage()==null)
//			{
//				status.setCode(EXCEPTION);
//				status.setMessage("Error in finding message! The action is completed successfully.");
//			}
//			
//		}
//		return status;
//	}
	
	@Transactional
	@Override
	public Status updateMasterWithValue(ProjectWithTaskValues projectWithTaskValues) {
	    Status status = new Status();
	    try {
	        LtMastProjects ltMastProjects = projectWithTaskValues.getLtMastProjects();
	        if (ltMastProjects.getStartDate() == null || ltMastProjects.getProjectName() == null ||
	            ltMastProjects.getLastUpdateLogin() == null) {
//	            status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
	        	status.setCode(0);		
	        	status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
	            if (status.getMessage() == null) {
	                status.setCode(0);
	                status.setMessage("Error in finding message! The action was unsuccessful");
	            }
	            return status;
	        }
	        
	        ltMastProjects.setLastUpdatedBy(ltMastProjects.getLastUpdateLogin());
	        ltMastProjects.setLastUpdateDate(new Date());
	        ltMastProjects = ltMastProjectRepository.save(ltMastProjects);
	        
	        List<LtMastProjectTasks> ltMastProjectTaskList = projectWithTaskValues.getLtMastProjectTaskValues();
	        for (LtMastProjectTasks ltMastProjectTasks : ltMastProjectTaskList) {
	            if (ltMastProjectTasks.getProjectId() != null) {
	                ltMastProjectTasks.setLastUpdateDate(new Date());
	                ltMastProjectTasks.setLastUpdateLogin(ltMastProjects.getLastUpdateLogin());
	                ltMastProjectTasks.setLastUpdatedBy(ltMastProjects.getLastUpdateLogin());
	            } else if (ltMastProjectTasks.getTaskId() == null) {
	                ltMastProjectTasks.setCreationDate(new Date());
	                ltMastProjectTasks.setProjectId(ltMastProjects.getProjectId());
	                ltMastProjectTasks.setCreatedBy(ltMastProjects.getLastUpdateLogin());
	                ltMastProjectTasks.setLastUpdateDate(new Date());
	                ltMastProjectTasks.setLastUpdateLogin(ltMastProjects.getLastUpdateLogin());
	                ltMastProjectTasks.setLastUpdatedBy(ltMastProjects.getLastUpdateLogin());
	            } else {
//	                status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
	            	status.setCode(0);		
		        	status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
	                if (status.getMessage() == null) {
	                    status.setCode(0);
	                    status.setMessage("Error in finding message! The action is completed unsuccessfully.");
	                }
	                return status; // Exit early on failure
	            }
	            ltMastProjectTasksRepository.save(ltMastProjectTasks);
	        }
	        
//	        status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
	        status.setCode(1);		
        	status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
	        if (status.getMessage() == null) {
	            status.setCode(1);
	            status.setMessage("Error in finding message! The action is completed successfully.");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions and set appropriate status
	        status.setCode(0);
	        status.setMessage("Exception occurred: " + e.getMessage());
	        e.printStackTrace(); // Log the exception for debugging
	    }
	    return status;
	}

	
	@Transactional
	@Override
	public Status delete(Long projectId) throws Exception {
	Status status=new Status();
		
		if(ltMastProjectDao.delete(projectId))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			status.setCode(1);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
	
	return status;
	}
	
	
	@Transactional
	@Override
	public Status deleteTask(Long taskId, Long projectId) throws Exception {
	Status status=new Status();
		
		if(ltMastProjectDao.deleteTask(taskId,projectId))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			status.setCode(1);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
	
	return status;
	}

}
