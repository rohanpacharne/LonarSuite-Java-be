package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtRentalAgreementHeadersDao {
	
	List<LtRentalAgreementHeaders> getLtRentalAgreementHeadersDataTable(LtRentalAgreementHeaders input,Long companyId) throws ServiceException;
	
	Long getLtRentalAgreementHeadersCount(LtRentalAgreementHeaders input,Long companyId) throws ServiceException;
	
	Long save(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException;
	
	LtRentalAgreementHeaders getRentalAgreementById(Long id) throws ServiceException;
	
	boolean update(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException;

	LtRentalAgreementHeaders getRentalAgreementStatusById(Long id)throws ServiceException;
	
	boolean delete(Long agreementHeaderId) throws ServiceException;
	
	boolean checkStatusIsPending(Long invoiceHeaderId, Long approvalId) throws ServiceException;
	
	String checkforApprovals(Long agreementHeaderId) throws ServiceException;
	
	boolean submitForApproval(Date date, Long agreementHeaderId, String state, Object object) throws ServiceException;
	
	boolean upDateStatus(Long agreementHeaderId, String pending, String currentApprovalLavel) throws ServiceException;
	
	boolean chkForApprovers(Long agreementHeaderId) throws ServiceException;
	
	List<LtRentalAgreementHeaders> getInprocessAgreementList(String inprogressStr) throws ServiceException;
	
	LtRentalAgreementApproval getApprovalLevel(Long agreementHeaderId) throws ServiceException;
	
	List<LtRentalAgreementApproval> getApprovalList(Long agreementHeaderId, String currentApprovalLevel) throws ServiceException;
	
	String getNextApprovalLevel(Long expHeaderId, String currentApprovalLevel) throws Exception;
	
	void updateCurrentApprovalLevel(Long agreementHeaderId, String currentApprovalLavel) throws ServiceException;











	
	




}
