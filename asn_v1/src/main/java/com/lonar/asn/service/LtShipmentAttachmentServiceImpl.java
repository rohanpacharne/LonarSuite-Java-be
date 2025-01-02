package com.lonar.asn.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.asn.dao.LtMastSysVariablesDao;
import com.lonar.asn.dao.LtShipmentAttachmentDao;
import com.lonar.asn.model.AttachmentDTO;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.LtShipmentAttachment;
import com.lonar.asn.model.Status;
import com.lonar.asn.model.SysVariableWithValues;

@Service
public class LtShipmentAttachmentServiceImpl implements LtShipmentAttachmentService,CodeMaster{

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastSysVariablesDao ltMastSysVariablesDao;
	
	@Autowired
	LtShipmentAttachmentDao ltShipmentAttachmentDao;
	
	@Override
	public Status saveAsnAttachments(MultipartFile[] files, Long asnHeaderId, Long userId, Long attachmentTypeId,Long companyId)
			throws BusinessException, IOException {
		
		String saveDirectory=null;;
		Status status = new Status();
		String fileName = null;
		String msg = "";	
		Date currDate = new Date();
		SysVariableWithValues sysVariableWithValues;
		

			if (files != null && files.length > 0) {
				List<SysVariableWithValues> sysVariableWithValuesList=
						ltMastSysVariablesDao.getBySysVariableName("GLOBAL_FILE_PATH",companyId);
				/*SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService
						.getBySysVariableName("FILE_UPLOAD_FOLDER_PATH");*/

				if (sysVariableWithValuesList != null) {
					sysVariableWithValues = sysVariableWithValuesList.get(0);
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

					

						List<LtShipmentAttachment> ltShipmentAttachmentList = ltShipmentAttachmentDao.getAllFilesByShipmentHeaderId(asnHeaderId);

						if (ltShipmentAttachmentList.size() > 0) 
						{
							for (LtShipmentAttachment ltShipmentAttachment : ltShipmentAttachmentList) 
							{

								if (!ltShipmentAttachment.getFileName().equals(files[i].getOriginalFilename())) 
								{	

									if(ltShipmentAttachmentDao.save(asnHeaderId,fileName,saveDirectory,userId, currDate, attachmentTypeId)) {
										break;
									}
								

								} else {
									status.setCode(0);
									status.setMessage("File with same name already exists!");
									return status;

								}
							}
							
						} else {
							ltShipmentAttachmentDao.save(asnHeaderId,fileName,saveDirectory,userId, currDate, attachmentTypeId);
							
						}

						byte[] bytes = files[i].getBytes();

						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(saveDirectory + fileName)));
						buffStream.write(bytes);

						buffStream.close();
						msg += "You have successfully uploaded " + fileName ;
						//status = ltMastCommonMessageService.getCodeAndMessage(FILE_UPLOADED_SUCESSFULLY);
						
						status.setMessage(msg);
						status.setCode(1);
						//status.setData(data);

						if (status.getMessage() == null) {
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}
						return status;

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
		
		return status;
	}

	@Override
	public List<LtShipmentAttachment> getAllByHeaderId(Long id) throws BusinessException {
		List<LtShipmentAttachment> ltShipmentAttachmentList = ltShipmentAttachmentDao.getAllFilesByShipmentHeaderId(id);
		return ltShipmentAttachmentList;
	}

	@Override
	public Status saveAsnLineAttachments(MultipartFile[] files, Long asnHeaderId, Long userId, String asnLineId)
			throws BusinessException, IOException {
		String saveDirectory=null;;
		Status status = new Status();
		String fileName = null;
		String msg = "";	
		Date currDate = new Date();
		SysVariableWithValues sysVariableWithValues;
		

			if (files != null && files.length > 0) {
				List<SysVariableWithValues> sysVariableWithValuesList=
						ltMastSysVariablesDao.getBySysVariableName1("FILE_UPLOAD_FOLDER_PATH");
				
				if (sysVariableWithValuesList != null) {
					sysVariableWithValues = sysVariableWithValuesList.get(0);
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

					

						List<LtShipmentAttachment> ltShipmentAttachmentList = ltShipmentAttachmentDao.getAllFilesByShipmentHeaderId(asnHeaderId);

						if (ltShipmentAttachmentList.size() > 0) 
						{
							for (LtShipmentAttachment ltShipmentAttachment : ltShipmentAttachmentList) 
							{

								if (!ltShipmentAttachment.getFileName().equals(files[i].getOriginalFilename())) 
								{	

									if(ltShipmentAttachmentDao.saveLineAttachment(asnHeaderId,fileName,saveDirectory,userId, currDate, asnLineId)) {
										break;
									}
								

								} else {
									status.setCode(0);
									status.setMessage("File with same name already exists!");
									return status;

								}
							}
							
						} else {
							ltShipmentAttachmentDao.saveLineAttachment(asnHeaderId,fileName,saveDirectory,userId, currDate, asnLineId);
							
						}

						byte[] bytes = files[i].getBytes();

						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(saveDirectory + fileName)));
						buffStream.write(bytes);

						buffStream.close();
						msg += "You have successfully uploaded " + fileName ;
						//status = ltMastCommonMessageService.getCodeAndMessage(FILE_UPLOADED_SUCESSFULLY);
						
						status.setMessage(msg);
						status.setCode(1);
						//status.setData(data);

						if (status.getMessage() == null) {
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}
						return status;

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
		
		return status;
	}

	@Override
	public Status deleteByID(String id) throws BusinessException {
		Status status = new Status();
		if( ltShipmentAttachmentDao.deleteByID(id)){
			status.setCode(1);
			status.setMessage("The attachment has been deleted successfully.");
		}else {
			status.setCode(0);
			status.setMessage("Error in finding message! The action was unsuccessful");
		}
		return status;
	}

	@Override
	public AttachmentDTO getfilepathbylineid(String id) throws BusinessException {
		return ltShipmentAttachmentDao.getfilepathbylineid(id);
	}

}
