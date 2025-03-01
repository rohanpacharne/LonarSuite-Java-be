package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtPrLines;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPrLinesDao {
	
	List<LtPrLines> getLtPrLinesDataTable(LtPrLines input, Long prHeaderId) throws ServiceException;
	
	Integer save(LtPrLines ltPrLines) throws ServiceException;
	
	boolean update(LtPrLines ltPrLines) throws ServiceException;
	
	boolean delete(Long prLineId) throws ServiceException;
	
	LtPrLines getPrLineById(Long prLineId);
	
	Long getLtLinesDataTableCount(LtPrLines input, Long prHeaderId) throws ServiceException;








}
