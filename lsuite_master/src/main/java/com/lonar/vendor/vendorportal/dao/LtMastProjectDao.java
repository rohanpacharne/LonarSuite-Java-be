package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.Customer;
import com.lonar.vendor.vendorportal.model.Employee;
import com.lonar.vendor.vendorportal.model.LtMastCustomersDto;
import com.lonar.vendor.vendorportal.model.LtMastDepartments;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ProjectWithTaskValues;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastProjectDao {
	
	public List<LtMastProjects> listAllActiveLtMastProjectDao() throws Exception;
	
	public Long getCount(LtMastProjects input, long companyId) throws Exception;
	
	public List<LtMastProjects> getLtMastProjectsDataTable(LtMastProjects input,long companyId) throws Exception;
	
	public List<LtMastProjects> isFeildsExists(LtMastProjects ltMastProjects) throws Exception;
	
	public List<Employee> getAllEmployee();
	
	public List<Customer> getAllCustomer();
	
	public List<LtMastCustomersDto > getCustomersLikeName(String name, Long companyId) throws ServiceException;
	
	public List<LtMastProjects > getProjectsLikeName(String name,long companyId) throws ServiceException;

	public ProjectWithTaskValues getById(Long projectId) throws Exception;
	
	boolean delete(Long projectId) throws Exception;
	
	boolean deleteTask(Long taskId,Long projectId) throws Exception;

}
