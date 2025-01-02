package com.lonar.vendor.vendorportal.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.dao.LtMastCustomerAttachmentsDao;
import com.lonar.vendor.vendorportal.dao.LtMastSysVariablesDao;
import com.lonar.vendor.vendorportal.dao.LtMastUsersDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastCustomerAttachment;
import com.lonar.vendor.vendorportal.model.LtMastUsers;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;

@Service
public class LtMastCustomerAttachmentServiceImpl implements LtMastCustomerAttachmentService,CodeMaster{

	@Autowired
	LtMastSysVariablesDao  ltMastSysVariablesDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastCustomerAttachmentsDao ltCustomerAttachmentDao;
	
	@Autowired
	LtMastUsersDao ltMastUsersDao;
	
	@Override
	public List<LtMastCustomerAttachment> getAllFilesByCustomerId(Long customerId) throws ServiceException {
		
		return ltCustomerAttachmentDao.getAllFilesByCustomerId(customerId);
	}

	@Override
	public Status deleteLtMastCustomerAttachmentFile(Long customerAttachmentId) throws ServiceException {
		Status status = new Status();
		if(ltCustomerAttachmentDao.deleteLtMastCustomerAttachmentFile(customerAttachmentId))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(ATTACHEMENT_DELETED_SUCCESS);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_SUCCESS").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(ATTACHEMENT_DELETED_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return status;
	}

	@Override
	public Status saveCustomerFile(MultipartFile[] files, Long customerId, Long userId, Long attachmentTypeId)
			throws ServiceException {
		Status status = new Status();
		String saveDirectory = null;
		String fileName = null;
		List<LtMastCustomerAttachment> ltMastCustomerAttachmentList = null;
		try {
			LtMastUsers ltMastUsers = ltMastUsersDao.getLtMastUsersByID(userId);
			System.out.println("ltMastUsers = "+ltMastUsers);
			if (files != null && files.length > 0) {
				SysVariableWithValues sysVariableWithValues = ltMastSysVariablesDao
						.getBySysVariableName("FILE_UPLOAD_FOLDER_PATH",ltMastUsers.getCompanyId()).get(0);

				if (sysVariableWithValues != null) {
					if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
						saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();

					} else {
						saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
					}
				}

				File dir = new File(saveDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
					if (!dir.isDirectory()) {
//						status = ltMastCommonMessageService.getCodeAndMessage(NO_DIRECTIVE_EXISTS);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("NO_DIRECTIVE_EXISTS").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (status.getMessage() == null) {
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}

						return status;
					}
				}

				for (int i = 0; i < files.length; i++) 
				{
					fileName = files[i].getOriginalFilename();
					System.out.println("fileName = "+fileName);
					try {
						ltMastCustomerAttachmentList = ltCustomerAttachmentDao.getAllFilesByCustomerId(customerId);
						System.out.println("ltMastCustomerAttachmentList = "+ltMastCustomerAttachmentList);
						if (ltMastCustomerAttachmentList.size() > 0) 
						{
							for (LtMastCustomerAttachment ltMastCustomerAttachment : ltMastCustomerAttachmentList) 
							{
								if (!ltMastCustomerAttachment.getFileName().equals(files[i].getOriginalFilename())) 
								{
									if(ltCustomerAttachmentDao.saveCustomerFile(customerId,fileName,saveDirectory,userId, new Date(), attachmentTypeId)) {
										break;
									}
								}else {
									status.setCode(0);
									status.setMessage("File with same name already exists!");
									return status;
								} 
							}
						} else {
							ltCustomerAttachmentDao.saveCustomerFile(customerId,fileName,saveDirectory,userId, new Date(), attachmentTypeId);
						}

						byte[] bytes = files[i].getBytes();

						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(saveDirectory + fileName)));
						buffStream.write(bytes);
						buffStream.close();
						status.setMessage("You have successfully uploaded " + fileName );
						status.setCode(1);

						if (status.getMessage() == null) {
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}
						return status;

					} catch (Exception e) {
						e.printStackTrace();
//						status = ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (status.getMessage() == null) {
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}

					}
				}
			} else {
//				status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (status.getMessage() == null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
			try {
//				status = ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (status.getMessage() == null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
				status.setMessage(e.getMessage());
			} catch (Exception o) {
				o.printStackTrace();
			}
		}
		return status;
	}

}
