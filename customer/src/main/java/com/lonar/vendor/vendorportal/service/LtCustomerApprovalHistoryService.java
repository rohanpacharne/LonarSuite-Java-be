package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtCustomerApprovalHistoryService 
{

	void saveApprovalHistory(LtCustomerApprovalHistory ltExpenseApprovalHistory) throws ServiceException;

	List<LtCustomerApprovalHistory> getCustomerApprovalHistoryByCustomerId(Long vendorId) throws ServiceException;

	ResponseEntity<Status> save(LtCustomerApprovalHistory ltVendorApprovalHistory) throws ServiceException;

}

