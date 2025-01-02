package com.lonar.vendor.vendorportal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.ExpenseApprovalV;
import com.lonar.vendor.vendorportal.service.LtExpAdvanceService;

@RestController
@RequestMapping("/API/advance")
public class LtExpAdvanceRestController implements CodeMaster{
	
	@Autowired
	LtExpAdvanceService ltExpAdvanceService;
	
	@PreAuthorize("hasPermission(null, '#/inbox/inbox', 'View') ")
	@RequestMapping(value = "/dataTable/{approvalId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getExpenseApprovalVDataTableByExpHeaderId(@Valid ExpenseApprovalV input,
			@PathVariable("approvalId") String approvalId,@PathVariable("logTime") String logTime)
	{
		String status1= PENDING ;
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try  {
				Long count=ltExpAdvanceService.getCount(status1, approvalId,input);
				
			    customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    
				List<ExpenseApprovalV> expenseApprovalList= ltExpAdvanceService.getByStatus(status1, approvalId,input);
			    //List<ExpenseApprovalV> expenseApprovalList = new ArrayList<>();
				customeDataTable.setData(expenseApprovalList);
			
		}
		catch (Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
	}


}
