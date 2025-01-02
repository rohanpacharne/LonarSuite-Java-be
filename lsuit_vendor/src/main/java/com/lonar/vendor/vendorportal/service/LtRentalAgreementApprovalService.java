package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtRentalAgreementApprovalService {
	
	Status updateStatusApproval(LtRentalAgrApprovalHistory approvalHistory) throws ServiceException;
	
	List<LtRentalAgrApprovalHistory> getRentalAgrApprovalHistoryByAgreementId(Long agreementHeaderId) throws ServiceException;
	
	LtRentalAgreementApproval getRentalAgreementApproval(Long agreementHeaderId, Long apprId) throws ServiceException;




}
