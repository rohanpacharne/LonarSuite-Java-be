package com.lonar.vendor.vendorportal.fileupload;

import java.util.List;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpenseAttachments;

public interface LtMastFileUploadService extends CodeMaster {
	
	public List<LtExpenseAttachments> getAllFilesByHeaderId(Long expenceHeaderId) throws Exception;


}
