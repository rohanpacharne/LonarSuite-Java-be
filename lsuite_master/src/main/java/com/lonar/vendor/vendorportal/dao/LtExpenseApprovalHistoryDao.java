package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;

public interface LtExpenseApprovalHistoryDao {
	
	List<LtExpenseApprovalHistory> getExpenseApprovalHistoryByExpHeaderId(Long expHeaderId) throws Exception;
	
	boolean save(LtExpenseApprovalHistory ltExpenseApprovalHistory) throws Exception;


}
