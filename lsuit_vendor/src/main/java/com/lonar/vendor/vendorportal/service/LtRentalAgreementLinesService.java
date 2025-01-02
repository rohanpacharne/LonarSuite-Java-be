package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtInvoiceLines;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtRentalAgreementLinesService {
	
	Long getLtRentalAgreementLinesCount(LtRentalAgreementLines input, Long id) throws ServiceException;

	List<LtRentalAgreementLines> getLtRentalAgreementLinesDataTable(LtRentalAgreementLines input, Long id) throws ServiceException;
	
	Status save(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException;
	
	LtRentalAgreementLines getRentalAgreementLineById(Long id) throws ServiceException;
	
	Status deleteByAgreementLineId(Long agreementLineId);
	
	Status update(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException;




}
