package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastCustomerInfoDao {

	List<LtMastCustomer> getAllCustomerInfo() throws ServiceException;

	LtMastCustomer getCustomerById(Long customerId) throws ServiceException;
	
	Long save(LtMastCustomer vendors) throws ServiceException;

	boolean update(LtMastCustomer customer) throws ServiceException;

	boolean delete(Long customerId) throws ServiceException;

	List<LtMastCustomer> getLtMastCustomerDataTable(Long customerId,LtMastCustomer input) throws ServiceException;
	
	Long getLtMastCustomerCountByInitiatorId(LtMastCustomer input, Long initiatorId) throws ServiceException;
	
	Long getLtMastCustomerInfoCount(Long companyId, LtMastCustomer input) throws ServiceException;
	
	boolean getByCustomerNumber(LtMastCustomer ltMastCustomer) throws ServiceException;
	
	boolean getByCustomerName(LtMastCustomer ltMastCustomer) throws ServiceException;
	
	boolean getByPanNo(LtMastCustomer ltMastCustomer) throws ServiceException;

	List<LtMastCustomer> getAllActiveCustomerInfo(Long companyId);
	 
}
