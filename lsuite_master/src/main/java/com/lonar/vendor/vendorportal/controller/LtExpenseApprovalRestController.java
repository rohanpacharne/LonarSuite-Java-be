package com.lonar.vendor.vendorportal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtExpExpenseHeadersService;
import com.lonar.vendor.vendorportal.service.LtExpenseApprovalService;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;

@RestController
@RequestMapping("/API/ExpenseApproval")
public class LtExpenseApprovalRestController implements CodeMaster{
	
	@Autowired
	LtExpenseApprovalService ltExpenseApprovalService;
	
	@Autowired
	LtExpExpenseHeadersService ltExpExpenseHeadersService;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@PreAuthorize("hasPermission(null, '#/expenses/expenseDemo', 'View') or hasPermission(null, '#/myAdvance/advanceDemo', 'View')")
	@RequestMapping(value = "/getApprovalByExpensehederId/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtExpenseApproval>> getExpenseApprovalByExpHeaderId(@PathVariable("id") Long expHeaderId,@PathVariable("logTime") Long logTime)
    {
            List<LtExpenseApproval> ltExpenseApproval=new ArrayList<LtExpenseApproval>();
            try
            {
            	ltExpenseApproval =  ltExpenseApprovalService.getExpenseApprovalByExpHeaderId(expHeaderId);
            }
            catch(Exception e)
            {
            	throw new BusinessException(0, null, e);
            }
          
            return new ResponseEntity<List<LtExpenseApproval>>(ltExpenseApproval, HttpStatus.OK);
    }
	
	@PreAuthorize("hasPermission(null, '#/expenses/expenseDemo', 'View') or hasPermission(null, '#/myAdvance/advanceDemo', 'View')")
	@RequestMapping(value = "/submitforapproval/{expHeaderId}/{logTime}", method = RequestMethod.GET)
	public ResponseEntity<Status> submitForApproval( @PathVariable("expHeaderId") Long expHeaderId,@PathVariable("logTime") String logTime)
	{
		Status status=new Status();
		try
		{
			LtExpExpenseHeaders expExpenseHeaders=ltExpExpenseHeadersService.findOne((expHeaderId));
		
			if(expExpenseHeaders.getExpenseAmount() == null || expExpenseHeaders.getExpenseAmount() < 1)
			{
				if(expExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
						expExpenseHeaders.getExpenseCategory().equals(MILEAGE))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_AMOUNT_SHOULD_GREATER_THAN_ZERO);
					status.setCode(0);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_AMOUNT_SHOULD_GREATER_THAN_ZERO").getMessageName());
					if(status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("Expense Amount Should be greater than Zero");
					}
				}
				else if(expExpenseHeaders.getExpenseCategory().equals(ADVANCE))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_AMOUNT_SHOULD_GREATER_THAN_ZERO);
					status.setCode(0);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_AMOUNT_SHOULD_GREATER_THAN_ZERO").getMessageName());
					if(status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("TRF/Advance Amount Should be greater than Zero");
					}
				}
				return new ResponseEntity<Status>(status, HttpStatus.OK);
				
			}
			String stat =checkforApprovals(expHeaderId);
			if(stat!=null)
			{
				status.setCode(0);
				status.setMessage(stat);
				return new ResponseEntity<Status>(status, HttpStatus.EXPECTATION_FAILED);
			}
			if(expExpenseHeaders.getExpenseAmount() != null ||expExpenseHeaders.getExpenseAmount()>0)
			{
				status=ltExpenseApprovalService.submitForApproval(new Date(),expHeaderId,INPROGRESS,null);
			}
			else
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_AMOUNT_SHOULD_GREATER_THAN_ZERO);
				status.setCode(0);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_AMOUNT_SHOULD_GREATER_THAN_ZERO").getMessageName());

				if(status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Expense Amount Should be greater than Zero");
				}
			}
		}
		catch(Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	public String checkforApprovals(Long expHeaderId ) throws Exception
	{
		String stat = ltExpenseApprovalService.checkforApprovals(expHeaderId);
		return stat;
	}
	
	@RequestMapping(value = "/updateStatusApproval", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Status> updateStatusApproval( @RequestBody LtExpenseApprovalHistory approvalHistory )
	{
		Status status=new Status();
		try {
			status=ltExpenseApprovalService.updateStatusApproval(approvalHistory);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getExpenseApproval/{expHeaderId}/{approvalId}/{logTime}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<LtExpenseApproval> getExpenseApproval(@PathVariable("expHeaderId") Long expId, @PathVariable("approvalId") Long apprId,@PathVariable("logTime") String logTime)
	{
		System.out.println("oooo");
		LtExpenseApproval ltExpenseApproval=null;
		try
		{
			 ltExpenseApproval=ltExpenseApprovalService.getExpenseApproval(expId, apprId);
		}
		catch(Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<LtExpenseApproval>(ltExpenseApproval, HttpStatus.OK);
	}
	

}
