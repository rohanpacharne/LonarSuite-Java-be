package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.ExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtExpExpenseHeadersDao extends CodeMaster {
	
	public Long getCount(LtExpExpenseHeaders input,Long empId, String category) throws Exception;
	
	public Long getEmployeeDataTableCount(LtExpExpenseHeaders input, Long empId, String category) throws Exception;
	
	public Long getAllDataTableCount(LtExpExpenseHeaders input, Long empId, String category) throws Exception;
	
	public List<LtExpExpenseHeaders> getExpenseRecords(LtExpExpenseHeaders input,Long empId, String category) throws Exception;
	
	public List<LtExpExpenseHeaders> getEmployeeDataTableExpenseRecords(LtExpExpenseHeaders input, Long empId,
			String category) throws Exception;
	
	public List<LtExpExpenseHeaders> getAllExpenseRecords(LtExpExpenseHeaders input, Long empId, String category) throws Exception;
	
	public boolean updateExpenseAmount( Long expenseHeaderId) throws Exception;
	
	public Long save(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception;
	
	public boolean update(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception;
	
	public LtExpExpenseHeaders findOne(Long headerId) throws Exception;
	
	public boolean delete(Long expId) throws Exception;
	
	LtExpExpenseHeaders getExpenseStatusById(Long expenseHeaderId) throws ServiceException;
	
	public List<LtExpExpenseHeaders> getExpenseList(String status) throws Exception;
	
	public ExpenseApproval getApprovalLevel( Long expHeaderId) throws Exception;
	
	public List<ExpenseApproval> getApprovalList(Long expHeaderId, String level) throws Exception;
	
	String getNextApprovalLevel(Long expHeaderId, String currentApprovalLevel) throws Exception;
	
	public boolean upDateStatus(Long expHeaderId, String status, String currentLevel) throws Exception;
	
	public void updateCurrentApprovalLevel(Long expHeaderId,String approvalLevel) throws Exception;
	
	public LtExpExpenseHeaders findEmpDetails(Long expHeaderId) throws Exception;
	
	boolean checkStatusIsPending(Long invoiceHeaderId, Long approvalId) throws ServiceException;
	
	public boolean deleteExpenseHeaderAttachments(Long expAttachId) throws Exception;










}
