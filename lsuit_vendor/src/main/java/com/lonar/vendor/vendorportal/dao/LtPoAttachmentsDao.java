package com.lonar.vendor.vendorportal.dao;
 
import java.util.List;
 
import com.lonar.vendor.vendorportal.model.LtPoAttachments;
 
public interface LtPoAttachmentsDao {
 
	List<LtPoAttachments> getAllFilesByPoHeaderId(Long poHeaderId);
 
	boolean deleteLtPoAttachmentFile(Long poAttachmentId);
 
}