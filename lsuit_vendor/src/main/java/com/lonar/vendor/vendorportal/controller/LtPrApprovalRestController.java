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
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtPrApprovalService;

@RestController
@RequestMapping("/API/prapproval")
public class LtPrApprovalRestController {
	
	@Autowired
	LtPrApprovalService ltPrApprovalService;
	
	@RequestMapping(value = "/getPrApprovalHistoryByPrHeaderId/{prHeaderId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtPrApprovalHistory>> getPrApprovalHistoryByPrHeaderId(@PathVariable("prHeaderId") Long prHeaderId,@PathVariable("logTime") String logTime) throws ServiceException
    {
          List<LtPrApprovalHistory> ltPrApprovalHistoryList=
        		  ltPrApprovalService.getPrApprovalHistoryByPrHeaderId(prHeaderId);

          return new ResponseEntity<List<LtPrApprovalHistory>>(ltPrApprovalHistoryList, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/updatePrStatusApproval", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Status> updatePrStatusApproval( @RequestBody LtPrApprovalHistory approvalHistory )
	{
		Status status=new Status();
		try {
			status=ltPrApprovalService.updateStatusApproval(approvalHistory);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPrApproval/{prHeaderId}/{approvalId}/{logTime}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Status> getRentalAgreementApproval(@PathVariable("prHeaderId") Long prHeaderId, @PathVariable("approvalId") Long apprId,@PathVariable("logTime") String logTime)
	{
		Status status = new Status();
		LtPrApproval ltPrApproval=null;
		try
		{
			ltPrApproval=ltPrApprovalService.getPrApproval(prHeaderId, apprId);
			status.setData(ltPrApproval);
			if(ltPrApproval != null) {
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

}
