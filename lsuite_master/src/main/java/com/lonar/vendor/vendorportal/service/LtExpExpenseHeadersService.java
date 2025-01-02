package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtExpExpenseHeadersService extends CodeMaster {
	
	public Long getCount(LtExpExpenseHeaders input, Long empId, String category, String role) throws Exception;
	
	public List<LtExpExpenseHeaders> getExpenseRecords(LtExpExpenseHeaders input,Long empId, String category, String role) throws Exception;
	
	public Status save(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception;
	
	public LtExpExpenseHeaders findOne(Long parseLong) throws Exception;
	
	public Status delete(Long parseLong) throws Exception;
	
	LtExpExpenseHeaders getExpenseStatusById(Long expenseHeaderId) throws ServiceException;
	
	boolean checkStatusIsPending(Long expHeaderId, Long approvalId) throws ServiceException;
	
	public Status deleteExpenseHeaderAttachments(Long expAttachId);



}
