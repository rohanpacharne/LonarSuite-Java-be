package com.lonar.vendor.vendorportal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BulkIdWithName;
import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.ExpenseApprovalV;
import com.lonar.vendor.vendorportal.model.InboxBulkInput;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.ExpenseApprovalVservice;
import com.lonar.vendor.vendorportal.service.LtExpExpenseHeadersService;
import com.lonar.vendor.vendorportal.service.LtExpenseApprovalService;

@RestController
@RequestMapping("/API/ExpenseApprovalInbox")
public class LtExpenseApprovalInboxRestController implements CodeMaster{
	
	@Autowired
	ExpenseApprovalVservice expenseApprovalVservice;
	
	@Autowired
	LtExpExpenseHeadersService ltExpExpenseHeadersService;
	
	@Autowired
	LtExpenseApprovalService ltExpenseApprovalService;
	
	@PreAuthorize("hasPermission(null, '#/inbox/inbox', 'View') ")
	@RequestMapping(value = "/dataTable/{approvalId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getExpenseApprovalVDataTableByExpHeaderId(@Valid ExpenseApprovalV input,
			@PathVariable("approvalId") String approvalId,@PathVariable("logTime") String logTime)
	{
		String status1= PENDING ;
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try  {
				Long count=expenseApprovalVservice.getCount(status1, approvalId,input);
				
			    customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    
				List<ExpenseApprovalV> expenseApprovalList= expenseApprovalVservice.getByStatus(status1, approvalId,input);
			    //List<ExpenseApprovalV> expenseApprovalList = new ArrayList<>();
				customeDataTable.setData(expenseApprovalList);
			
		}
		catch (Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
	}
	
	@PreAuthorize("hasPermission(null, '#/inbox/inbox', 'Add') ")
	@RequestMapping(value = "/saveBulk", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveLtP2pInvoiceApprovalHistory(@RequestBody @Valid InboxBulkInput inboxBulkInput) 
	{
		System.out.println("in save bulk");
		System.out.println("inboxBulkInput = "+inboxBulkInput);
		Status status = new Status();
		List<String> responseMessage = new ArrayList<String>();
		try
		{
			List<BulkIdWithName> expenseIdList = inboxBulkInput.getExpenseList();
	
			for (BulkIdWithName bulkIdWithName : expenseIdList) 
			{
				LtExpenseApprovalHistory ltExpenseApprovalHistory = new LtExpenseApprovalHistory();
				ltExpenseApprovalHistory.setLastUpdateDate(new Date());
				//ltExpenseApprovalHistory.setNote(inboxBulkInput.getNote());
				ltExpenseApprovalHistory.setStatus(inboxBulkInput.getAction());
				ltExpenseApprovalHistory.setExpenseApprovalId(bulkIdWithName.getExpenseApprovalId());
				ltExpenseApprovalHistory.setExpenseHeaderId(bulkIdWithName.getExpenseHeaderId());
				ltExpenseApprovalHistory.setRemark(inboxBulkInput.getNote());
				ltExpenseApprovalHistory.setEmployeeId(inboxBulkInput.getApprovalId());
						
				
				if(inboxBulkInput.getAction().equals(APPROVED))
				{
					ltExpenseApprovalHistory.setStatus(APPROVED);
				}
				else if(inboxBulkInput.getAction().equals(REJECTED))
				{
					ltExpenseApprovalHistory.setStatus(REJECTED);
				}
				else if(inboxBulkInput.getAction().equals(FEEDBACK_AWAITED))
				{
					ltExpenseApprovalHistory.setStatus(FEEDBACK_AWAITED);
				}
				
				LtExpExpenseHeaders ltExpExpenseHeaders=ltExpExpenseHeadersService.findOne(bulkIdWithName.getExpenseHeaderId());
				System.out.println("ltExpenseApprovalHistory = "+ltExpenseApprovalHistory);
				status=ltExpenseApprovalService.updateStatusApproval(ltExpenseApprovalHistory);
				responseMessage.add(ltExpExpenseHeaders.getExpenseNumber()+" "+status.getMessage());
				
			}	
			status.setData(responseMessage);
		}
		catch(Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
