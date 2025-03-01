package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtVendorApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtVendorApprovalHistoryService 
{

	void saveApprovalHistory(LtVendorApprovalHistory ltExpenseApprovalHistory) throws ServiceException;

	List<LtVendorApprovalHistory> getVendorApprovalHistoryByVendorId(Long vendorId) throws ServiceException;

	ResponseEntity<Status> save(LtVendorApprovalHistory ltVendorApprovalHistory) throws ServiceException;
	
	//Invoice
	void saveInvoiceApprovalHistory(LtInvoiceApprovalHistory ltInvoiceApprovalHistory) throws ServiceException;
	
	List<LtInvoiceApprovalHistory> getInvoiceApprovalHistoryByInvoiceId(Long invoiceHeaderId) throws ServiceException;
	
	void saveAgreementApprovalHistory(LtRentalAgrApprovalHistory ltRentalAgrApprovalHistory) throws ServiceException;
	
	void savePrApprovalHistory(LtPrApprovalHistory ltPrApprovalHistory) throws ServiceException;

	void savePoApprovalHistory(LtPoApprovalHistory ltPoApprovalHistory) throws ServiceException;



}

