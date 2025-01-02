package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtRentalAgreementHeadersService {
	
	List<LtRentalAgreementHeaders> getLtRentalAgreementHeadersDataTable(LtRentalAgreementHeaders input,Long companyId) throws ServiceException;
	
	Long getLtRentalAgreementHeadersCount(LtRentalAgreementHeaders input,Long companyId) throws ServiceException;
	
	Status save(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException;
	
	LtRentalAgreementHeaders getRentalAgreemenById(Long id) throws ServiceException;
	
	Status update(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException;

	LtRentalAgreementHeaders getRentalAgreementStatusById(Long id)throws ServiceException;
	
	Status delete(Long agreementHeaderId) throws ServiceException;
	
	boolean checkStatusIsPending(Long agreementHeaderId, Long approvalId) throws ServiceException;
	
	String checkforApprovals(Long agreementHeaderId) throws ServiceException;
	
	Status submitForApproval(Date date, Long agreementHeaderId, String status, Object object,Long userId, Long companyId) throws ServiceException;










}
