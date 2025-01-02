package com.lonar.vendor.vendorportal.controller;

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

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.InboxBulkInput;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtCustomerApprovalHistoryService;
import com.lonar.vendor.vendorportal.service.LtCustomerApprovalService;
import com.lonar.vendor.vendorportal.service.LtMastCustomerInfoService;

@RestController
@RequestMapping("/API/approval")
public class LtCustomerApprovalRestController implements CodeMaster
{

	@Autowired
	LtMastCustomerInfoService ltMastCustomerInfoService;
	
	@Autowired
	LtCustomerApprovalService ltCustomerApprovalService;
	
	@Autowired
	LtCustomerApprovalHistoryService ltCustomerApprovalHistoryService;
	
	@RequestMapping(value = "/chkForApprovalByCustomerIdAppID/{customerId}/{appId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> chkForApprovalByCustomerIdAppID(@PathVariable("customerId") Long customerId,
    		@PathVariable("appId") Long appId,@PathVariable("logTime") String logTime)
    {
           Status status = new Status();
           LtCustomerApproval ltCustomerApproval=null;
            try{
            	ltCustomerApproval=ltCustomerApprovalService.getCustomerApproval(customerId, appId);
            	if(ltCustomerApproval!=null){
            		status.setCode(0);
            		status.setMessage("Approval present");
            	}else{
            		status.setCode(1);
            		status.setMessage("Approval absent");
            	}
            }catch(Exception e)
            {
            	throw new BusinessException(0, null, e);
            }
          
            return new ResponseEntity<Status>(status, HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/submitforapproval/{customerId}/{logTime}", method = RequestMethod.GET)
	public ResponseEntity<Status> submitForApproval( @PathVariable("customerId") Long customerId,@PathVariable("logTime") String logTime)
	{
		Status status=new Status();
		try
		{		
			String stat =checkforApprovals(customerId);
			if(stat.equals("null")){
				status.setCode(0);
				status.setMessage("No approvers found for employee's division.");
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}else {
			
			status=ltCustomerApprovalService.submitForApproval(new Date(),customerId,INPROCESS,null);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	
	private String checkforApprovals(Long customerId) throws ServiceException 
	{
		String stat = ltCustomerApprovalService.checkforApprovals(customerId);
		return stat;
	}
	
	
	@RequestMapping(value = "/saveBulk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveBulk(@RequestBody InboxBulkInput inboxBulkInput) throws ServiceException {
		Status status = new Status();
		status = ltCustomerApprovalService.saveBulk(inboxBulkInput);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/updateStatusApproval", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Status> updateStatusApproval( @RequestBody LtCustomerApprovalHistory approvalHistory )
	{
		Status status=new Status();
		try {
			status=ltCustomerApprovalService.updateStatusApproval(approvalHistory);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCustomerApproval/{customerId}/{approvalId}/{logTime}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Status> getCustomerApproval(@PathVariable("customerId") Long customerId, @PathVariable("approvalId") Long apprId,@PathVariable("logTime") String logTime)
	{
		Status status = new Status();
		LtCustomerApproval ltCustomerApproval=null;
		try{
			ltCustomerApproval=ltCustomerApprovalService.getCustomerApproval(customerId, apprId);
			status.setData(ltCustomerApproval);
			if(ltCustomerApproval != null) {
				status.setCode(1);
			}
			status.setCode(2);
		}catch(Exception e){
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getApprovalByCustomerId/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtCustomerApproval>> getCustomerApprovalByCustomerId(@PathVariable("id") Long vendorId,@PathVariable("logTime") String logTime) throws ServiceException
    {
            List<LtCustomerApproval> ltCustomerApproval=
            		ltCustomerApprovalService.getCustomerApprovalByCustomerId(vendorId);
            return new ResponseEntity<List<LtCustomerApproval>>(ltCustomerApproval, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getApprovalHistoryByCustomerId/{customerId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtCustomerApprovalHistory>> getExpenseApprovalHistoryByExpHeaderId(@PathVariable("customerId") Long customerId,@PathVariable("logTime") String logTime) throws ServiceException
    {
          List<LtCustomerApprovalHistory> ltCustomerApprovalHistory=
        		  ltCustomerApprovalHistoryService.getCustomerApprovalHistoryByCustomerId(customerId);

          return new ResponseEntity<List<LtCustomerApprovalHistory>>(ltCustomerApprovalHistory, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody LtCustomerApprovalHistory ltCustomerApprovalHistory) throws ServiceException
	{
		return ltCustomerApprovalHistoryService.save(ltCustomerApprovalHistory);
	}
	
	
	
}
