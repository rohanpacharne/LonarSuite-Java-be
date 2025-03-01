package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtPrLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPrLinesService {
	
	List<LtPrLines> getLtPrLinesDataTable(LtPrLines input, Long prHeaderId) throws ServiceException;
	
	Status save(LtPrLines input) throws ServiceException;
	
	Status update(LtPrLines ltPrLines) throws ServiceException;
	
	Status delete(Long prLineId) throws ServiceException;
	
	LtPrLines getPrLineById(Long prLineId) throws ServiceException;
	
	Long getLtPrLinesDataTableCount(LtPrLines input, Long prHeaderId) throws ServiceException;







}
