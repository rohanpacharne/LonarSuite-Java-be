package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtExpenseApprovalService extends CodeMaster {
	
//	List<LtExpenseApproval> getAllExpenseApproval() throws Exception;
	
	List<LtExpenseApproval> getExpenseApprovalByExpHeaderId(Long expenseHeaderId) throws Exception;
	
	String checkforApprovals(Long expHeaderId) throws Exception;
	
	Status submitForApproval(Date submissionDate,Long expHeaderId,String status,Date approvedDate) throws Exception;
	
	Status updateStatusApproval(LtExpenseApprovalHistory approvalHistory  ) throws Exception;
	
	public LtExpenseApproval getExpenseApproval(Long expId, Long apprId) throws Exception;



}
