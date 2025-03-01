package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPrAttachments;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPrAttchmentService {
	
	public List<LtPrAttachments> getAllFilesByPrHeaderId(Long prHeaderId) throws ServiceException;
	
	public Status deletePrAttachmentFile(Long prAttachmentId);



}
