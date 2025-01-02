package com.lonar.vendor.vendorportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtRentalAgreementApprovalHistoryService;
import com.lonar.vendor.vendorportal.service.LtRentalAgreementApprovalService;

@RestController
@RequestMapping("/API/agreementapproval")
public class LtRentalAgreementApprovalRestController {
	
	@Autowired
	LtRentalAgreementApprovalService ltRentalAgreementApprovalService;
	
	@Autowired
	LtRentalAgreementApprovalHistoryService ltRentalAgreementApprovalHistoryService;
	
	@RequestMapping(value = "/getRentalAgrApprovalHistoryByAgreementId/{agreementHeaderId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtRentalAgrApprovalHistory>> getRentalAgrApprovalHistoryByAgreementId(@PathVariable("agreementHeaderId") Long agreementHeaderId,@PathVariable("logTime") String logTime) throws ServiceException
    {
          List<LtRentalAgrApprovalHistory> ltRentalAgrApprovalHistory=
        		  ltRentalAgreementApprovalService.getRentalAgrApprovalHistoryByAgreementId(agreementHeaderId);

          return new ResponseEntity<List<LtRentalAgrApprovalHistory>>(ltRentalAgrApprovalHistory, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getRentalAgreementApproval/{agreementHeaderId}/{approvalId}/{logTime}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Status> getRentalAgreementApproval(@PathVariable("agreementHeaderId") Long agreementHeaderId, @PathVariable("approvalId") Long apprId,@PathVariable("logTime") String logTime)
	{
		Status status = new Status();
		LtRentalAgreementApproval ltRentalAgreementApproval=null;
		try
		{
			ltRentalAgreementApproval=ltRentalAgreementApprovalService.getRentalAgreementApproval(agreementHeaderId, apprId);
			status.setData(ltRentalAgreementApproval);
			if(ltRentalAgreementApproval != null) {
				status.setCode(1);
			}
			status.setCode(0);
		}
		catch(Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody LtRentalAgrApprovalHistory ltRentalAgrApprovalHistory) throws ServiceException
	{
		return ltRentalAgreementApprovalHistoryService.save(ltRentalAgrApprovalHistory);
	}
	
	@RequestMapping(value = "/updateAgreementStatusApproval", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Status> updateAgreementStatusApproval( @RequestBody LtRentalAgrApprovalHistory approvalHistory )
	{
		Status status=new Status();
		try {
			status=ltRentalAgreementApprovalService.updateStatusApproval(approvalHistory);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
