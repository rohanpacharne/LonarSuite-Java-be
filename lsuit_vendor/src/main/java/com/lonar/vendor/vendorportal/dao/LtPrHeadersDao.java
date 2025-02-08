package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPrHeadersDao {
	
	List<LtPrHeaders> getLtPrHeadersDataTable(LtPrHeaders input, Long companyId) throws ServiceException;
	
	Integer save(LtPrHeaders ltPrHeaders) throws ServiceException;
	
	Long getLtPrHeadersDataTableCount(LtPrHeaders input, Long companyId) throws ServiceException;
	
	boolean delete(Long prHeaderId) throws ServiceException;




}
