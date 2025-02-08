package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPrHeadersService {
	
	List<LtPrHeaders> getLtPrHeadersDataTable(LtPrHeaders input, Long companyId) throws ServiceException;
	
	Status save(LtPrHeaders input) throws ServiceException;
	
	Long getLtPrHeadersDataTableCount(LtPrHeaders input, Long companyId) throws ServiceException;
	
	Status delete(Long prHeaderId) throws ServiceException;




}
