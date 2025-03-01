package com.lonar.vendor.vendorportal.dao;

import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtVendorApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastModuleApprovalsDao 
{

	String checkforApprovals(Long vendorId) throws ServiceException;

	boolean chkIsAprovedByAnyOne(LtVendorApprovalHistory approvalHistory) throws ServiceException;
	
	boolean chkInvoiceIsAprovedByAnyOne(LtInvoiceApprovalHistory approvalHistory) throws ServiceException;
	
	boolean chkRentalAgreementIsAprovedByAnyOne(LtRentalAgrApprovalHistory approvalHistory) throws ServiceException;
	
	boolean chkPrIsAprovedByAnyOne(LtPrApprovalHistory approvalHistory) throws ServiceException;

	boolean chkPoIsAprovedByAnyOne(LtPoApprovalHistory approvalHistory) throws ServiceException;


	
	

}
