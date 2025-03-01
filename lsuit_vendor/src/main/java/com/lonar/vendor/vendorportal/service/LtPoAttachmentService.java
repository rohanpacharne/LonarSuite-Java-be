package com.lonar.vendor.vendorportal.service;
 
import java.util.List;
 
import com.lonar.vendor.vendorportal.model.LtPoAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
 
public interface LtPoAttachmentService {
 
	List<LtPoAttachments> getAllFilesByPoHeaderId(Long poHeaderId) throws ServiceException;
 
	Status deleteLtPoAttachmentFile(Long poAttachmentId);
 
}