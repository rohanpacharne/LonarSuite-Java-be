package com.lonar.vendor.vendorportal.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastCustomerAttachment;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastCustomerAttachmentService;

@RestController
@RequestMapping("/API/customerAttachment")
@PropertySource(value = "classpath:serverurl.properties", ignoreResourceNotFound = true)
public class LtMastCustomerAttachmentController implements CodeMaster {


	
	static final Logger logger = Logger.getLogger(LtMastCustomerAttachmentController.class);

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Autowired
	LtMastCustomerAttachmentService ltMastCustomerAttachmentService;



	@RequestMapping(value = "/show/{logTime}", method = RequestMethod.GET)
	public String displayForm(@PathVariable("logTime") String logTime) {
		return "file_upload_form";
	}

	@RequestMapping(value = "/MultipalUpload", method = RequestMethod.POST)
	public ResponseEntity<Status> saveCustomerFile(@RequestParam("file") MultipartFile[] files,
		 @RequestParam("customerId") Long customerId,  @RequestParam("userId") Long userId,
		 @RequestParam("attachmentTypeId") Long attachmentTypeId) {

		Status status = new Status();
		try {
		status = ltMastCustomerAttachmentService.saveCustomerFile(files,customerId,userId,attachmentTypeId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getallfilesbycustomerid/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastCustomerAttachment>> getAllFilesByCustomerId(@PathVariable("id") Long customerId,@PathVariable("logTime") String logTime)
	{
		List<LtMastCustomerAttachment> customerAttachment = null;
		try 
		{
			if (customerId != null) {
				customerAttachment = ltMastCustomerAttachmentService.getAllFilesByCustomerId(customerId);
				return new ResponseEntity<List<LtMastCustomerAttachment>>(customerAttachment, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<LtMastCustomerAttachment>>(customerAttachment, HttpStatus.OK);

	}

		@RequestMapping(value = "/deleteFile/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
	    public ResponseEntity<Status> delete(@PathVariable("id") Long customerAttachmentId,@PathVariable("logTime") String logTime)
		{
				Status status=new Status();
				try
				{
				 status =  ltMastCustomerAttachmentService.deleteLtMastCustomerAttachmentFile(customerAttachmentId);
				}
				catch (org.springframework.dao.DataIntegrityViolationException e)
				{
					try
					{
						status=ltMastCommonMessageService.getCodeAndMessage(0);
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
					
					return new ResponseEntity<Status>(status,HttpStatus.OK);
				} 
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return new ResponseEntity<Status>(status, HttpStatus.OK);
		}


//==================================================================================================================================
	
	
}