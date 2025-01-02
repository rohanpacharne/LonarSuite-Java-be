package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.model.LtMastCustomerAttachment;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastCustomerAttachmentService {

	List<LtMastCustomerAttachment> getAllFilesByCustomerId(Long customerId) throws ServiceException;

	Status saveCustomerFile(MultipartFile[] files, Long customerId, Long userId, Long attachmentTypeId) throws ServiceException;

	Status deleteLtMastCustomerAttachmentFile(Long customerAttachmentId) throws ServiceException;

}
