package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtRentalAgreementApprovalDao {
	
	boolean updateStatusApproval(LtRentalAgrApprovalHistory approvalHistory) throws ServiceException;
	
	List<LtRentalAgrApprovalHistory> getRentalAgrApprovalHistoryByAgreementHeaderId(Long agreementHeaderId) throws ServiceException;
	
	LtRentalAgreementApproval getRentalAgreementApproval(Long agreementHeaderId, Long apprId) throws ServiceException;
	
	boolean updateAllStatusApproval(LtRentalAgrApprovalHistory approvalHistory) throws ServiceException;
	
	 List<LtRentalAgreementApproval> chkAgreementEmpApproval(Long employeeId, Long agreementId) throws ServiceException;
	 
	boolean submitForApproval(Date submissionDate, Long agreementHeaderId, String status, Date approvedDate, Long lastLogin) throws ServiceException;
	
	String getCurrLevelByAgreementApprovalId(Long agreementApprovalId) throws ServiceException;

	boolean upDateStatus(Long agreementHeaderId, String noAction, String currentLevel) throws ServiceException;







}
