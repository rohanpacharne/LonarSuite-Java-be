package com.lonar.asn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.CustomeDataTable;
import com.lonar.asn.service.LtShipmentInboxService;

@RestController
@RequestMapping("/API/asninbox")
public class LtShipmentInboxController implements CodeMaster {

	@Autowired
	LtShipmentInboxService ltShipmentInboxService;
	
	@RequestMapping(value = "/dataTable/{approvalId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getInvoiceDataTableByApprId(AsnApproval input,
			@PathVariable("approvalId") String approvalId,@PathVariable("logTime") String logTime) 
	{
		String status= PENDING ;
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try  {
				Long count=ltShipmentInboxService.getShipmentCount(status, approvalId,input);
				
			    customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    
				List<AsnApproval> asnApprovalList= ltShipmentInboxService.getShipmentByStatus(status, approvalId,input);
				customeDataTable.setData(asnApprovalList);
			
		} 
		catch (Exception e) 
		{
			throw new BusinessException(INTERNAL_SERVER_ERROR, null, e);
		}
		return customeDataTable;
	}
	
	
}
