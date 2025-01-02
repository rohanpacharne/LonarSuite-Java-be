package com.lonar.vendor.vendorportal.fileupload;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtExpenseAttachments;

public interface LtMastFileUploadDao {
	
	public List<LtExpenseAttachments> getAllFilesByHeaderId(Long expenceHeaderId) throws Exception;


}
