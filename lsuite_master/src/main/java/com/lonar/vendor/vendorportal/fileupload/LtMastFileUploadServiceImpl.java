package com.lonar.vendor.vendorportal.fileupload;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.model.LtExpenseAttachments;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastSysVariablesService;

@Service
public class LtMastFileUploadServiceImpl implements LtMastFileUploadService 
{
	
	@Autowired
	LtMastFileUploadDao ltMastFileUploadDao;

	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Transactional
	@Override
	public List<LtExpenseAttachments> getAllFilesByHeaderId(Long expenceHeaderId) throws Exception 
	{
		return ltMastFileUploadDao.getAllFilesByHeaderId(expenceHeaderId);
	}

}
