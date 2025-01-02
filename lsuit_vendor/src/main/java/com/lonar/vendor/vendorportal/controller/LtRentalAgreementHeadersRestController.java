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
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtInvoiceHeadersService;
import com.lonar.vendor.vendorportal.service.LtRentalAgreementHeadersService;

@RestController
@RequestMapping("/API/rentalagreementheader")
public class LtRentalAgreementHeadersRestController  implements CodeMaster {
	
	@Autowired
	LtRentalAgreementHeadersService ltRentalAgreementHeadersService;
	
	@RequestMapping(value = "/dataTable/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getLtRentalAgreementHeadersDataTable(LtRentalAgreementHeaders input,@PathVariable("companyId") Long companyId,
			@PathVariable("logTime") String logTime) 
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try 
		{
				Long count=ltRentalAgreementHeadersService.getLtRentalAgreementHeadersCount(input,companyId);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
				
			    List<LtRentalAgreementHeaders> LtRentalAgreementHeadersList= 
			    		ltRentalAgreementHeadersService.getLtRentalAgreementHeadersDataTable(input,companyId);
				customeDataTable.setData(LtRentalAgreementHeadersList);	
				
		} 
		catch (Exception e) 
		{	
			 e.printStackTrace();
		}
		return customeDataTable;
		
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException
	{
			Status status=new Status();
			status =  ltRentalAgreementHeadersService.save(ltRentalAgreementHeaders);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRentalAgreementById/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtRentalAgreementHeaders> getRentalAgreementById(@PathVariable("id") Long id) throws ServiceException
	{
		System.out.println("id = "+id);
		LtRentalAgreementHeaders ltRentalAgreementHeaders=  ltRentalAgreementHeadersService.getRentalAgreemenById(id);
			return new ResponseEntity<LtRentalAgreementHeaders>(ltRentalAgreementHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> update(@RequestBody LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException
	{
			System.out.println("in controller");
			Status status=new Status();
			status =  ltRentalAgreementHeadersService.update(ltRentalAgreementHeaders);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/getRentalAgreementStatusById/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtRentalAgreementHeaders> geRentalAgreementStatusById(@PathVariable("id") Long id) throws ServiceException
	{
		LtRentalAgreementHeaders status=new LtRentalAgreementHeaders();
	        status =  ltRentalAgreementHeadersService.getRentalAgreementStatusById(id);
			return new ResponseEntity<LtRentalAgreementHeaders>(status,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long agreementHeaderId,@PathVariable("logTime") String logTime) throws ServiceException
	{
			Status status=new Status();
			status =  ltRentalAgreementHeadersService.delete(agreementHeaderId);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/checkStatusIsPending/{agrid}/{appid}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public boolean checkStatusIsPending(@PathVariable("agrid") Long agrid, @PathVariable("appid") Long appid,@PathVariable("logTime") String logTime) throws ServiceException
	{
		return  ltRentalAgreementHeadersService.checkStatusIsPending(agrid, appid);
	}
	
	@RequestMapping(value = "/submitforapproval/{agreementheaderid}/{logTime}", method = RequestMethod.GET)
	public ResponseEntity<Status> submitForApproval( @PathVariable("agreementheaderid") Long agreementheaderid,@PathVariable("logTime") String logTime)
	{
		Status status=new Status();
		try
		{		
			String stat =checkforApprovals(agreementheaderid);
			if(stat.equals("null"))
			{
				status.setCode(0);
				status.setMessage("No approvers found for employee's division.");
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}else {
				
				LtRentalAgreementHeaders ltRentalAgreementHeaders = ltRentalAgreementHeadersService.getRentalAgreemenById(agreementheaderid);
				Long userId = ltRentalAgreementHeaders.getCreatedBy();
				Long companyId = ltRentalAgreementHeaders.getCompanyId();
				System.out.println("companyId in controller = "+companyId);
				System.out.println("ltRentalAgreementHeaders = "+ltRentalAgreementHeaders);
//				if(  ltInvoiceHeaders.getInvoiceAmount()!=null &&  ltInvoiceHeaders.getInvoiceAmount()>0) {
//					System.out.println("in if");
					status=ltRentalAgreementHeadersService.submitForApproval(new Date(),agreementheaderid,RA_INPROCESS,null,userId,companyId);
//					System.out.println("status = "+status);
//				}else {
//					System.out.println("in else");
//					status.setCode(0);
//					status.setMessage("Invoice with zero amount can not be sent for approval.");
//					return new ResponseEntity<Status>(status, HttpStatus.OK);
//				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println("e = "+e.getMessage());
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		System.out.println("status 1 = "+status);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	private String checkforApprovals(Long agreementheaderid) throws ServiceException 
	{
		String stat = ltRentalAgreementHeadersService.checkforApprovals(agreementheaderid);
		return stat;
	}
	
	
}
