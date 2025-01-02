package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustomerAttachment;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastCustomerAttachmentsDao {

	List<LtMastCustomerAttachment> getAllFilesByCustomerId(Long customerId) throws ServiceException;

	boolean saveCustomerFile(Long customerId, String fileName, String saveDirectory, Long userId, Date date,
			Long attachmentTypeId) throws ServiceException;

	boolean deleteLtMastCustomerAttachmentFile(Long customerAttachmentId) throws ServiceException;

	boolean deleteByCustomerId(Long customerId) throws ServiceException;

}
