package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPrApprovalService {
	
	List<LtPrApprovalHistory> getPrApprovalHistoryByPrHeaderId(Long prHeaderId) throws ServiceException;
	
	Status updateStatusApproval(LtPrApprovalHistory approvalHistory) throws ServiceException;
	
	LtPrApproval getPrApproval(Long prHeaderId, Long apprId) throws ServiceException;




}
