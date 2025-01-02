package com.lonar.vendor.vendorportal.dao;

import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastModuleApprovalsDao 
{

	String checkforApprovals(Long vendorId) throws ServiceException;

	boolean chkIsAprovedByAnyOne(LtCustomerApprovalHistory approvalHistory) throws ServiceException;
	
	
	

}
