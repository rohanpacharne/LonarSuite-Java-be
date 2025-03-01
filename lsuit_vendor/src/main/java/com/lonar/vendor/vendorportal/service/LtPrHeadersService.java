package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPrHeadersService {
	
	List<LtPrHeaders> getLtPrHeadersDataTable(LtPrHeaders input, Long companyId) throws ServiceException;
	
	Status save(LtPrHeaders input) throws ServiceException;
	
	Long getLtPrHeadersDataTableCount(LtPrHeaders input, Long companyId) throws ServiceException;
	
	Status delete(Long prHeaderId) throws ServiceException;
	
	LtPrHeaders getPrById(Long prHeaderId) throws ServiceException;
	
	Status update(LtPrHeaders ltPrHeaders) throws ServiceException;
	
	LtPrHeaders getPrStatusById(Long id) throws ServiceException;
	
	boolean checkStatusIsPending(Long prHeaderId, Long approvalId) throws ServiceException;
	
	String checkforApprovals(Long prHeaderId) throws ServiceException;
	
	Status submitForApproval(Date date, Long agreementHeaderId, String status, Object object,Long userId, Long companyId) throws ServiceException;









}
