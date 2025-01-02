package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtInvoiceAttachment;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtRentalAgreementAttachmentService {
	
	public List<LtRentalAgreementAttachments> getAllFilesByAgreementHeaderId(Long agreementHeaderId) throws ServiceException;
	
	public Status deleteLtAgreementAttachmentFile(Long agreementAttachmentId);



}
