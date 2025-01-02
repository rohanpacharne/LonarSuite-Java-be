package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtCustomerInboxDao {

	Long getCount(String status1, String approvalId, LtCustomerApproval input) throws ServiceException;

	List<LtCustomerApproval> getByStatus(String status1, String approvalId, LtCustomerApproval input) throws ServiceException;
	
	List<LtCustomerApproval> getVendorApprovalNotification(String status, Long empId) throws ServiceException;

	
}
