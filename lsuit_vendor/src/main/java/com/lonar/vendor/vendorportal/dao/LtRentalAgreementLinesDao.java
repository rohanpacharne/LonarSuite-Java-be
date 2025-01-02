package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtInvoiceLines;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementLines;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtRentalAgreementLinesDao {
	
	Long getLtRentalAgreementLinesCount(LtRentalAgreementLines input, Long id) throws ServiceException;

	List<LtRentalAgreementLines> getLtRentalAgreementLinesDataTable(LtRentalAgreementLines input,Long id) throws ServiceException;
	
	Long save(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException;
	
	LtRentalAgreementLines getRentalAgreementLineById(Long id) throws ServiceException;
	
	List<LtRentalAgreementLines> getAllRentalAgreementLinesByHeaderId(Long id) throws ServiceException;
	
	boolean deleteByAgreementHeaderId(Long agreementHeaderId) throws ServiceException;
	
	boolean deleteByAgreementLineId(Long agreementLineId);
	
	boolean update(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException;






}
