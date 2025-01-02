package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.InboxBulkInput;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtCustomerApprovalService 
{

	Status updateStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException;

	LtCustomerApproval getCustomerApproval(Long customerId, Long appId) throws ServiceException;

	List<LtCustomerApproval> getCustomerApprovalByCustomerId(Long customerId) throws ServiceException;

	String checkforApprovals(Long customerId) throws ServiceException;

	Status submitForApproval(Date date, Long customerId, String inprocess, Object object) throws ServiceException;

	Status saveBulk(InboxBulkInput inboxBulkInput) throws ServiceException;


	
}
