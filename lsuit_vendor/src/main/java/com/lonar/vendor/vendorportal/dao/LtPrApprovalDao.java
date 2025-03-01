package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPrApprovalDao {
	
	List<LtPrApprovalHistory> getPrApprovalHistoryByPrHeaderId(Long prHeaderId) throws ServiceException;
	
	List<PrApproval> chkPrEmpApproval(Long employeeId, Long invoiceId) throws ServiceException;
	
	boolean updateStatusApproval(LtPrApprovalHistory approvalHistory) throws ServiceException;
	
	boolean updateAllStatusApproval(LtPrApprovalHistory approvalHistory) throws ServiceException;
	
	boolean submitForApproval(Date submissionDate, Long prHeaderId, String status, Date approvedDate, Long lastLogin) throws ServiceException;
	
	String getCurrLevelByPrApprovalId(Long prApprovalId) throws ServiceException;
	
	boolean upDateStatus(Long prHeaderId, String noAction, String currentLevel) throws ServiceException;
	
	LtPrApproval getPrApproval(Long prHeaderId, Long apprId) throws ServiceException;









}
