package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;

public interface LtExpenseApprovalHistoryService extends CodeMaster{
	
	List<LtExpenseApprovalHistory> getExpenseApprovalHistoryByExpHeaderId(Long expenseHeaderId) throws Exception;
	
	void saveApprovalHistory(LtExpenseApprovalHistory ltExpenseApprovalHistory) throws Exception;


}
