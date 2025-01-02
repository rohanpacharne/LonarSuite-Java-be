package com.lonar.vendor.vendorportal.service;

import java.util.List;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastCustomerInfoService {

	List<LtMastCustomer> getAllCustomerInfo() throws ServiceException;

	LtMastCustomer getCustomerById(Long customerId) throws ServiceException;

	Status save(LtMastCustomer vendors) throws ServiceException;

	Status update(LtMastCustomer customer) throws ServiceException;

	Status delete(Long customerId) throws ServiceException;

	List<LtMastCustomer> getLtMastCustomerDataTable(Long customerId,LtMastCustomer input) throws ServiceException;
	
	Long getLtMastCustomerCountByInitiatorId(LtMastCustomer input, Long initiatorId) throws ServiceException;
	
	Long getLtMastCustomerInfoCount(Long companyId, LtMastCustomer input) throws ServiceException;

	List<LtMastCustomer> getAllActiveCustomerInfo(Long companyId) throws ServiceException;

}
