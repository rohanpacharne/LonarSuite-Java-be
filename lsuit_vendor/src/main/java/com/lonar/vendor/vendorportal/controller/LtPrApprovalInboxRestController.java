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
import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;
import com.lonar.vendor.vendorportal.service.LtPrApprovalInboxService;
import com.lonar.vendor.vendorportal.service.LtRentalAgreementApprovalInboxService;

@RestController
@RequestMapping("/API/PrApprovalInbox")
public class LtPrApprovalInboxRestController implements CodeMaster{
	
	@Autowired
	LtPrApprovalInboxService ltPrApprovalInboxService;
	
	@PreAuthorize("hasPermission(null, '#/inbox/inbox', 'View') ")
	@RequestMapping(value = "/dataTable/{approvalId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getExpenseApprovalVDataTableByExpHeaderId(@Valid PrApproval input,
			@PathVariable("approvalId") String approvalId,@PathVariable("logTime") String logTime)
	{
		String status1= PENDING ;
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try  {
//				Long count=ltPrApprovalInboxService.getCount(status1, approvalId,input);
				
			    customeDataTable.setRecordsTotal(0l);
			    customeDataTable.setRecordsFiltered(0l);
			    
				List<PrApproval> prApprovalList= ltPrApprovalInboxService.getByStatus(status1, approvalId,input);
			    //List<ExpenseApprovalV> expenseApprovalList = new ArrayList<>();
				customeDataTable.setData(prApprovalList);
			
		}
		catch (Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
	}

}
