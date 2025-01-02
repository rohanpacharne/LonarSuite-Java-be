package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;

public interface LtExpenseApprovalDao extends CodeMaster{
	
//	boolean loadApprovers(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception;
	
	List<LtExpenseApproval> getExpenseApprovalByExpHeaderId(Long expenseHeaderId) throws Exception;
	
	String checkforApprovals(Long expHeaderId) throws Exception;
	
	boolean submitForApproval(Date submissionDate,Long expHeaderId,String status,Date approvedDate) throws Exception;
	
	List<LtExpenseApproval> chkEmpApproval(Long empId, Long expHeaderId);
	
	public boolean updateStatusApproval(LtExpenseApprovalHistory approvalHistory) throws Exception ;
	
	public boolean updateAllStatusApproval(LtExpenseApprovalHistory approvalHistory) throws Exception ;
	
	String getCurrLevelByexpenseApprovalId(Long expenseApprovalId) throws Exception;
	
	List<LtExpenseApproval> chkCurrentApprover(Long expHeaderId) throws Exception;
	
	public LtExpenseApproval getExpenseApproval(Long expId, Long apprId) throws Exception;






}
