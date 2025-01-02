package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.ExpenseApprovalV;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;

public interface LtExpAdvanceService {
	
	Long getCount(String status1, String approvalId, ExpenseApprovalV input) throws Exception;
	
	List<ExpenseApprovalV> getByStatus(String status,String approvalId,ExpenseApprovalV input) throws Exception;


}
