package com.lonar.vendor.vendorportal.fileupload;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpenseAttachments;
import com.lonar.vendor.vendorportal.model.Mail;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
import com.lonar.vendor.vendorportal.repository.LtMastFileUploadRepository;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastSysVariablesService;

@RestController
@RequestMapping("/API/upload")
@PropertySource(value = "classpath:serverurl.properties", ignoreResourceNotFound = true)
public class LtMastFileUploadController implements CodeMaster {
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Autowired
	LtMastFileUploadService ltMastFileUploadService;

	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;
	
	@Autowired
	LtMastFileUploadRepository ltMastFileUploadRepository;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@RequestMapping(value = "/getAllFilesByHeaderId/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtExpenseAttachments>> getAllFilesUploadedByHeaderId(@PathVariable("id") Long expenceHeaderId,@PathVariable("logTime") String logTime)
	{
		List<LtExpenseAttachments> ltExpenseAttachments = new ArrayList<LtExpenseAttachments>();
		try 
		{
			if (expenceHeaderId != null) {
				ltExpenseAttachments = ltMastFileUploadService.getAllFilesByHeaderId(expenceHeaderId);
				return new ResponseEntity<List<LtExpenseAttachments>>(ltExpenseAttachments, HttpStatus.OK);
			}
		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<List<LtExpenseAttachments>>(ltExpenseAttachments, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/MultipalFileUpload/{companyId}", method = RequestMethod.POST)
	public ResponseEntity<Status> saveFiles(@RequestParam("file") MultipartFile[] files,
		 @RequestParam("expenceHeaderId") Long expenceHeaderId,@RequestParam("attachmentType") String[] attachmentType,
			@PathVariable("companyId") Long companyId) {
		
		List<LtExpExpenseHeaders> list = (List<LtExpExpenseHeaders>) 
				jdbcTemplate.query("Select * from lt_exp_expense_headers where exp_header_id = ?" , new Object[]{ expenceHeaderId},
			 new  BeanPropertyRowMapper<LtExpExpenseHeaders>(LtExpExpenseHeaders.class));

		System.out.println("list = "+list);
		System.out.println("attachmentType = "+attachmentType[0]);
		List<LtExpenseAttachments> ltMastFileUploadList = new ArrayList<LtExpenseAttachments>();

		Status status = new Status();
		String fileName = "";
		String msg = "";
		
		String[] attachType  = null;
		String[] filenm  = null;
		String saveDirectory = "";

		/*for (int i = 0; i < attachmentType.length; i++) {
			System.out.println( expenceHeaderId + " " + files + " " + attachmentType[i]);
		}*/

		try {

			if (files != null && files.length > 0) {
				SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService
						.getBySysVariableName("FILE_UPLOAD_FOLDER_PATH",companyId);

				if (sysVariableWithValues != null) {
					if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
						saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();

					} else {
						saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
					}
				}

				// saveDirectory="D:/eExpence/Upload/";
				System.out.println("saveDirectory = " + saveDirectory);

				File dir = new File(saveDirectory);
				if (!dir.exists()) {
					dir.mkdirs();
					if (!dir.isDirectory()) {
//						status = ltMastCommonMessageService.getCodeAndMessage(NO_DIRECTIVE_EXISTS);
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("NO_DIRECTIVE_EXISTS").getMessageName());
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
						Long expenceHeaderIdLong = expenceHeaderId;

						ltMastFileUploadList = ltMastFileUploadService.getAllFilesByHeaderId(expenceHeaderIdLong);
						System.out.println(ltMastFileUploadList.size());
						if (ltMastFileUploadList.size() > 0) 
						{
							for (LtExpenseAttachments ltMastFileUpload : ltMastFileUploadList) 
							{

								if (!ltMastFileUpload.getFileName().equals(files[i].getOriginalFilename() ) 
										&& !ltMastFileUpload.getAttachmentType().equals(attachmentType[i])
										) {
									System.out.println(expenceHeaderId);
									System.out.println(fileName);
									System.out.println(saveDirectory);
									System.out.println(ltMastFileUpload);
									
									if (expenceHeaderId == null) {
										System.out.println("in exp header id");
									    throw new NullPointerException("expenceHeaderId is null");
									}
									if (fileName == null) {
										System.out.println("in exp fileName id");

									    throw new NullPointerException("fileName is null");
									}
									if (saveDirectory == null) {
										System.out.println("in exp saveDirectory id");
									    throw new NullPointerException("saveDirectory is null");
									}
									// For Update File
									System.out.println("attachmentType[i] = "+attachmentType[i]);
									int res = jdbcTemplate.update(
											"INSERT INTO LT_EXP_EXPENSE_ATTACHMENTS(expence_header_id,file_name,file_path,attachment_type,created_by,creation_date,last_update_login,last_updated_by,last_update_date) VALUES (?, ?, ?, ?,?,?,?,?,?)",
											expenceHeaderId, fileName, saveDirectory+fileName, attachmentType[i],list.get(0).getCreatedBy(),new Date(),list.get(0).getLastUpdateLogin(),list.get(0).getLastUpdatedBy(),new Date());
									
//									int res=jdbcTemplate.update("INSERT INTO LT_EXP_EXPENSE_ATTACHMENTS  "
//											+ "  (expence_header_id,EXPENCE_LINE_ID, FILE_NAME, FILE_PATH,ATTACHMENT_TYPE,CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,LAST_UPDATED_BY,LAST_UPDATE_DATE,THUMBNAIL_FILE_PATH,FILE_TEXT)  "
//											+ " VALUES (?, ? ,?, ?, ? ,?, ?, ? ,?, ?, ? ,?) ",
//											+  expenceHeaderId,ltMastFileUpload.getExpenceLineId(),fileName,saveDirectory,
//											ltMastFileUpload.getAttachmentType(),ltMastFileUpload.getCreatedBy(),ltMastFileUpload.getCreationDate(),
//											ltMastFileUpload.getLastUpdateLogin(),ltMastFileUpload.getLastUpdatedBy(),ltMastFileUpload.getLastUpdateDate(),
//											ltMastFileUpload.getThumbnailFilePath(),ltMastFileUpload.getFileText());
									
//									ltMastFileUpload.setExpenceHeaderId(expenceHeaderId);
//									ltMastFileUpload.setFileName(fileName);
//									ltMastFileUpload.setFilePath(saveDirectory+fileName);
//									ltMastFileUpload.setAttachmentType(attachmentType[i]);
//									ltMastFileUploadRepository.save(ltMastFileUpload);
									System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>11111111111111..............................");
									break;

								} else {
									status.setCode(0);
									status.setMessage("File with same name already exists!");
									System.out.println(
											"files[i].getOriginalFilename()>>>>>>>" + files[i].getOriginalFilename());
									return new ResponseEntity<Status>(status, HttpStatus.OK);

								}

							}
						} else {
							
							

							int res = jdbcTemplate.update(
									"INSERT INTO LT_EXP_EXPENSE_ATTACHMENTS(expence_header_id,file_name,file_path,attachment_type,created_by,creation_date,last_update_login,last_updated_by,last_update_date) VALUES (?, ?, ?, ?,?,?,?,?,?)",
									expenceHeaderId, fileName,  saveDirectory+fileName, attachmentType[i],list.get(0).getCreatedBy(),new Date(),list.get(0).getLastUpdateLogin(),list.get(0).getLastUpdatedBy(),new Date());
							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>2222222222222222..............................");

							
						}

						byte[] bytes = files[i].getBytes();

						BufferedOutputStream buffStream = new BufferedOutputStream(
								new FileOutputStream(new File(saveDirectory + fileName)));
						buffStream.write(bytes);

						buffStream.close();
						msg += "You have successfully uploaded " + fileName + "<br/>";
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("FILE_UPLOADED_SUCESSFULLY").getMessageName());

						if (status.getMessage() == null) {
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}

					} catch (Exception e) {
						e.printStackTrace();
//						status = ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
						System.out.println("status" + status);
						if (status.getMessage() == null) {
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}

					}
				}

				
			} else {
//				status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
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
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
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
	
	@RequestMapping(value = "/UploadMasterFile", method = RequestMethod.POST)
	public String uploadFile(
            @RequestParam String TC,
            @RequestParam String Module,
            @RequestParam("UploadFile") MultipartFile file, // Use MultipartFile instead of File
            @RequestParam String UserID,
            @RequestParam String CompanyId) {

        String urlString;
        if(Module.equals("POHEADER")) {
        	urlString = String.format("http://174.138.187.142:8016/api/FileUpload/UploadPurchaseOrders");
        }else {
        	urlString = String.format("http://174.138.187.142:8016/api/FileUpload/UploadMasterFile?TC=%s&Module=%s", TC, Module);
        }
        String boundary = "Boundary-" + System.currentTimeMillis();
        String lineEnd = "\r\n";

        try {
            // Create the connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("accept", "text/plain");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

            // Add UserID as form data
            outputStream.writeBytes("--" + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"UserID\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(UserID + lineEnd);

            // Add CompanyId as form data
            outputStream.writeBytes("--" + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"CompanyId\"" + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(CompanyId + lineEnd);

            // Add the file as form data
            outputStream.writeBytes("--" + boundary + lineEnd);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"UploadFile\"; filename=\"" + file.getOriginalFilename() + "\"" + lineEnd);
            outputStream.writeBytes("Content-Type: " + file.getContentType() + lineEnd);
            outputStream.writeBytes(lineEnd);

            // Write the file content to the output stream
            outputStream.write(file.getBytes()); // Use getBytes() from MultipartFile

            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes("--" + boundary + "--" + lineEnd);
            outputStream.flush();
            outputStream.close();

            // Read the response
            StringBuilder response = new StringBuilder();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            } else {
                return "Error: " + responseCode;
            }

            // Return the response body as a string
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
	
}
