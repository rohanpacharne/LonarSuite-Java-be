package com.lonar.vendor.vendorportal.dao;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;

public interface LtExpenseModuleApprovalsDao extends CodeMaster{
	
	boolean chkIsAprovedByAnyOne(LtExpenseApprovalHistory approvalHistory) throws Exception;


}
