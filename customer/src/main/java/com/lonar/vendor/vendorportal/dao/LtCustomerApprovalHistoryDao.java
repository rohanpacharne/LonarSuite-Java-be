package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtCustomerApprovalHistoryDao {

	boolean save(LtCustomerApprovalHistory approvalHistory) throws ServiceException;

	List<LtCustomerApprovalHistory> getCustomerApprovalHistoryByCustomerId(Long customerId) throws ServiceException;

}
