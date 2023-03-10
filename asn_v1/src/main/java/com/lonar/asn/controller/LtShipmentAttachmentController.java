package com.lonar.asn.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.asn.model.AttachmentDTO;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.LtShipmentAttachment;
import com.lonar.asn.model.Status;
import com.lonar.asn.service.LtShipmentAttachmentService;


@RestController
@RequestMapping("/API/asnattach")
public class LtShipmentAttachmentController {

	@Autowired
	LtShipmentAttachmentService asnAttachmentService;
	
	@RequestMapping(value = "/lineLevelUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveAsnLineAttachments(@RequestParam("file") MultipartFile[] files,
		 @RequestParam("asnHeaderId") Long asnHeaderId,  @RequestParam("userId") Long userId,
		 @RequestParam("asnLineId") String asnLineId) throws BusinessException, FileNotFoundException, IOException {

		Status status = new Status();
		status = asnAttachmentService.saveAsnLineAttachments(files,asnHeaderId,userId,asnLineId);
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/MultipalUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveAsnAttachments(@RequestParam("file") MultipartFile[] files,
		 @RequestParam("asnHeaderId") Long asnHeaderId,  @RequestParam("userId") Long userId,
		 @RequestParam("attachmentTypeId") Long attachmentTypeId) throws BusinessException, FileNotFoundException, IOException {

		Status status = new Status();
		status = asnAttachmentService.saveAsnAttachments(files,asnHeaderId,userId,attachmentTypeId);
		return new ResponseEntity<Status>(status, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getAllByHeaderId/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtShipmentAttachment>> getAllByHeaderId(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws BusinessException {
		List<LtShipmentAttachment> list =   asnAttachmentService.getAllByHeaderId(id);
		return new ResponseEntity<List<LtShipmentAttachment>>(list, HttpStatus.OK);
	}
	
	// -------------------Delete Single commonmasterValue details----------------------------
	@RequestMapping(value = "/deleteByID/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteByID(@PathVariable("id") String id,@PathVariable("logTime") String logTime) {
	
		Status status=new Status();
		status = asnAttachmentService.deleteByID(id);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@RequestMapping(value = "/getfilepathbylineid/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AttachmentDTO getfilepathbylineidgetfilepathbylineid(@PathVariable("id") String id,@PathVariable("logTime") String logTime) {
		return asnAttachmentService.getfilepathbylineid(id);
	}
}
