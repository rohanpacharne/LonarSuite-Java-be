package com.lonar.vendor.vendorportal.service;

import org.springframework.http.ResponseEntity;

import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtRentalAgreementApprovalHistoryService {
	
	ResponseEntity<Status> save(LtRentalAgrApprovalHistory ltRentalAgrApprovalHistory) throws ServiceException;

}
