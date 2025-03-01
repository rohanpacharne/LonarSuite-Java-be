package com.lonar.vendor.vendorportal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BulkIdWithName;
import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.InboxBulkInput;
import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPoHeaders;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtInvoiceApprovalService;
import com.lonar.vendor.vendorportal.service.LtInvoiceHeadersService;
import com.lonar.vendor.vendorportal.service.LtPoApprovalHistoryService;
import com.lonar.vendor.vendorportal.service.LtPoApprovalService;
import com.lonar.vendor.vendorportal.service.LtPoHeadersService;
import com.lonar.vendor.vendorportal.service.LtVendorApprovalHistoryService;

@RestController
@RequestMapping("/API/poApproval")
public class LtPoApprovalRestController implements CodeMaster {
	
	
	@Autowired
	LtPoHeadersService ltPoHeadersService;

	@Autowired
	LtPoApprovalService ltPoApprovalService;
	
	
	@Autowired
	LtVendorApprovalHistoryService ltVendorApprovalHistoryService;
	
	@Autowired
	LtPoApprovalHistoryService poApprovalHistoryService;
	
	
	@RequestMapping(value = "/updatePoStatusApproval", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Status> updatePoStatusApproval( @RequestBody LtPoApprovalHistory approvalHistory )
	{
		Status status=new Status();
		try {
			status=ltPoApprovalService.updatePoStatusApproval(approvalHistory);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPoApprovalHistoryByPoId/{poHeaderId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtPoApprovalHistory>> getExpenseApprovalHistoryByExpHeaderId(@PathVariable("poHeaderId") Long poHeaderId,@PathVariable("logTime") String logTime) throws ServiceException
    {
          List<LtPoApprovalHistory> ltPoApprovalHistory=
        		  poApprovalHistoryService.getPoApprovalHistoryByPoId(poHeaderId);

          return new ResponseEntity<List<LtPoApprovalHistory>>(ltPoApprovalHistory, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody LtPoApprovalHistory poApprovalHistory) throws ServiceException
	{
		return poApprovalHistoryService.save(poApprovalHistory);
	}

	@RequestMapping(value = "/getPoApproval/{poId}/{approvalId}/{logTime}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Status> getPoApproval(@PathVariable("poId") Long poId, @PathVariable("approvalId") Long apprId,@PathVariable("logTime") String logTime)
	{
		Status status = new Status();
		PoApproval poApproval=null;
		try
		{
			poApproval=ltPoApprovalService.getPoApproval(poId, apprId);
			status.setData(poApproval);
			if(poApproval != null) {
				status.setCode(1);
			}
			status.setCode(2);
		}
		catch(Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	
//	@RequestMapping(value = "/saveBulkPo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Status> saveBulkPo(@RequestBody InboxBulkInput inboxBulkInput) {
//		Status status = new Status();
//		List<String> responseMessage = new ArrayList<String>();
//		try {
//			List<BulkIdWithName> poIdList = inboxBulkInput.getExpenseList();
//
//			LtPoApprovalHistory ltPoApprovalHistory = new LtPoApprovalHistory();
//			for (BulkIdWithName bulkIdWithName : poIdList) {
//				ltPoApprovalHistory.setLastUpdateDate(new Date());
//				// ltExpenseApprovalHistory.setNote(inboxBulkInput.getNote());
//				ltPoApprovalHistory.setStatus(inboxBulkInput.getAction());
//				ltPoApprovalHistory.setPoApprovalId(bulkIdWithName.getPoApprovalId());
//				ltPoApprovalHistory.setPoHeaderId(bulkIdWithName.getPoHeaderId());
//				ltPoApprovalHistory.setRemark(inboxBulkInput.getNote());
//				ltPoApprovalHistory.setEmployeeId(inboxBulkInput.getApprovalId());
//
//				if (inboxBulkInput.getAction().equals(APPROVED)) {
//					ltPoApprovalHistory.setStatus(APPROVED);
//				} else if (inboxBulkInput.getAction().equals(REJECTED)) {
//					ltPoApprovalHistory.setStatus(REJECTED);
//				} else if (inboxBulkInput.getAction().equals(FEEDBACK_AWAITED)) {
//					ltPoApprovalHistory.setStatus(FEEDBACK_AWAITED);
//				}
//
//				//LtMastVendors ltMastVendors = ltMastVendorsService.getVendorById(bulkIdWithName.getVendorId());
//				LtPoHeaders ltPoHeader =  ltPoHeadersService.getPoHeaderById(bulkIdWithName.getPoHeaderId());
//				status = ltPoApprovalService.updatePoStatusApproval(ltPoApprovalHistory);
//				responseMessage.add(ltPoHeader.getPoNumber() + " " + status.getMessage());
//				
//			}
//			status.setData(responseMessage);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new BusinessException(0, null, e);
//		}
//		return new ResponseEntity<Status>(status, HttpStatus.OK);
//	}


}
