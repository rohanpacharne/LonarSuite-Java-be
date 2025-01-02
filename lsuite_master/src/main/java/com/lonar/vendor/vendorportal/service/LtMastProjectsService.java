package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.Customer;
import com.lonar.vendor.vendorportal.model.Employee;
import com.lonar.vendor.vendorportal.model.LtMastCustomersDto;
import com.lonar.vendor.vendorportal.model.LtMastDepartments;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ProjectWithTaskValues;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastProjectsService {
	
	public List<LtMastProjects> listAllActiveLtMastProjects() throws Exception;
	
	public Long getCount(LtMastProjects input, long companyId) throws Exception;
	
	public List<LtMastProjects> getLtMastProjectsDataTable(LtMastProjects input, long companyId) throws Exception;
	
	public String isFeildsExists(LtMastProjects ltMastProjects) throws Exception;
	
	public List<Employee> getAllEmployee();
	
	public List<Customer> getAllCustomer();
	
	public List<LtMastCustomersDto> getCustomersLikeName(String name, Long companyId) throws ServiceException;
	
	public List<LtMastProjects> getProjectsLikeName(String name,long companyId) throws ServiceException;

	public ProjectWithTaskValues getById(Long id) throws Exception;
	
	public Status updateMasterWithValue(ProjectWithTaskValues projectWithTaskValues) throws Exception;
	
	Status delete(Long projectId) throws Exception;
	
	Status deleteTask(Long taskId, Long projectId) throws Exception;


}
