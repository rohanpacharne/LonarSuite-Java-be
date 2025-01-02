package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Component
public interface LtRentalAgreementAttachmentDao {
	
	public List<LtRentalAgreementAttachments> getAllFilesByAgreementHeaderId(Long agreementHeaderId) throws ServiceException;
	
	public boolean deleteLtAgreementAttachmentFile(Long agreementAttachmentId);



}
