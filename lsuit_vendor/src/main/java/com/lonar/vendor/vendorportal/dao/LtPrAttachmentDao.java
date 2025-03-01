package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPrAttachments;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPrAttachmentDao {
	
	public List<LtPrAttachments> getAllFilesByPrHeaderId(Long prHeaderId) throws ServiceException;
	
	public boolean deletePrAttachmentFile(Long prAttachmentId);


}
