package com.lonar.vendor.vendorportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.config.ExceptionMessage;
import com.lonar.vendor.vendorportal.dao.LtMastUsersDao;
import com.lonar.vendor.vendorportal.model.LtMastUsers;
import com.lonar.vendor.vendorportal.model.LtPrAttachments;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastSysVariablesService;
import com.lonar.vendor.vendorportal.service.LtPrAttchmentService;

@RestController
@RequestMapping("/API/prAttachment")
@PropertySource(value = "classpath:serverurl.properties", ignoreResourceNotFound = true)
public class LtPrAttachmentController {
	
final String restBaseUrl = "/API/agreementAttachment";
	
	@Autowired
	LtMastUsersDao ltMastUsersDao;
	
	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;
	
	private String saveDirectory;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtPrAttchmentService ltPrAttachmentService;
	
	static final Logger logger = Logger.getLogger(LtInvoiceAttachmentController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@RequestMapping(value = "/getallfilesbyprheaderid/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtPrAttachments>> getAllFilesByPrHeaderId(@PathVariable("id") Long prHeaderId,@PathVariable("logTime") String logTime)
	{
		List<LtPrAttachments> ltPrAttachmentsList = new ArrayList<LtPrAttachments>();
		try 
		{
			if (prHeaderId != null) {
				ltPrAttachmentsList = ltPrAttachmentService.getAllFilesByPrHeaderId(prHeaderId);
				return new ResponseEntity<List<LtPrAttachments>>(ltPrAttachmentsList, HttpStatus.OK);
			}
		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<List<LtPrAttachments>>(ltPrAttachmentsList, HttpStatus.OK);
		}
		return new ResponseEntity<List<LtPrAttachments>>(ltPrAttachmentsList, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/MultipalUpload", method = RequestMethod.POST)
	public ResponseEntity<Status> savePrFile(@RequestParam("file") MultipartFile[] files,
		 @RequestParam("prHeaderId") Long prHeaderId,  @RequestParam("userId") Long userId,
		 @RequestParam("attachmentTypeId") Long attachmentTypeId) {

		List<LtPrAttachments> ltPrAttachmentsList = new ArrayList<LtPrAttachments>();

		Status status = new Status();
		String fileName = null;
		String msg = "";	
		Date currDate = new Date();

		try {
			LtMastUsers ltMastUsers = ltMastUsersDao.getLtMastUsersByID(userId);
			if (files != null && files.length > 0) {
				SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService
						.getBySysVariableName("FILE_UPLOAD_FOLDER_PATH",ltMastUsers.getCompanyId());

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

						return new ResponseEntity<Status>(status, HttpStatus.OK);
					}
				}

				
				for (int i = 0; i < files.length; i++) 
				{
					fileName = files[i].getOriginalFilename();

					try {

						ltPrAttachmentsList = ltPrAttachmentService.getAllFilesByPrHeaderId(prHeaderId);

						if (ltPrAttachmentsList.size() > 0) 
						{
							for (LtPrAttachments ltPrAttachments : ltPrAttachmentsList) 
							{

								if (!ltPrAttachments.getFileName().equals(files[i].getOriginalFilename())) 
								{
									int res=jdbcTemplate.update("INSERT INTO lt_pr_attachments  "
											+ "  (PR_HEADER_ID, FILE_NAME, FILE_PATH, LAST_UPDATED_BY,LAST_UPDATE_DATE, ATTACHMENT_TYPE_ID)  "
											+ " VALUES (?, ? ,?, ?, ?, ?) ",
											+  prHeaderId,fileName,saveDirectory,userId, currDate, attachmentTypeId );		
									
									if(res==1)
									// For Update Fil
									break;

								} else {
									status.setCode(0);
									status.setMessage("File with same name already exists!");
									return new ResponseEntity<Status>(status, HttpStatus.OK);

								}

							}
						} else {
							int res=jdbcTemplate.update("INSERT INTO lt_pr_attachments  "
									+ "  (PR_HEADER_ID, FILE_NAME, FILE_PATH, LAST_UPDATED_BY,LAST_UPDATE_DATE, ATTACHMENT_TYPE_ID)  "
									+ " VALUES (?, ? ,?, ?, ?, ?) ",
									+  prHeaderId,fileName,saveDirectory,userId, currDate, attachmentTypeId );
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
						return new ResponseEntity<Status>(status, HttpStatus.OK);

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

		}

		catch (Exception e) {
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
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/deleteFile/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long prAttachmentId,@PathVariable("logTime") String logTime)
	{
			String apiUrl = "/deleteFile"+prAttachmentId;
			Status status=new Status();
			try
			{
			 status =  ltPrAttachmentService.deletePrAttachmentFile(prAttachmentId);
			}
			catch (org.springframework.dao.DataIntegrityViolationException e)
			{
				try
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("Error in finding message! The action was unsuccessful");
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				logger.info("Output " + restBaseUrl + apiUrl + " "
						+ messageSource.getMessage("cannotdelete", null, "Default", Locale.getDefault())
						+ " HttpStatus.OK");
				return new ResponseEntity<Status>(status,HttpStatus.OK);
			} 
			catch(Exception e)
			{
				ExceptionMessage expMsg=new ExceptionMessage();
				status=expMsg.getExceptionMessage();
				e.printStackTrace();
			}
			logger.info("Output " + apiUrl + prAttachmentId +status+ " HttpStatus.OK ");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
