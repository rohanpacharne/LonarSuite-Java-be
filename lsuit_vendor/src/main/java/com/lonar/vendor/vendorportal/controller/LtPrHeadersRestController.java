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
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtPrHeadersService;

@RestController
@RequestMapping("/API/prheaders")
public class LtPrHeadersRestController implements CodeMaster {
	
	@Autowired
	LtPrHeadersService ltPrHeadersService;
	
	@RequestMapping(value = "/dataTable/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getLtPrHeadersDataTable(LtPrHeaders input,@PathVariable("id") Long id,@PathVariable("logTime") String logTime) 
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try 
		{
				Long count=ltPrHeadersService.getLtPrHeadersDataTableCount(input,id);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtPrHeaders> ltMastCommonMessageList= 
			    		ltPrHeadersService.getLtPrHeadersDataTable(input,id);
				customeDataTable.setData(ltMastCommonMessageList);
		} 
		catch (Exception e) 
		{	
			 e.printStackTrace();
		}
		return customeDataTable;
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status save(@RequestBody LtPrHeaders input) 
	{
		try {
			return ltPrHeadersService.save(input);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long prHeaderId,@PathVariable("logTime") String logTime) throws ServiceException
	{
			Status status=new Status();
			status =  ltPrHeadersService.delete(prHeaderId);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPrById/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtPrHeaders> getInvoiceById(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException
	{
		LtPrHeaders prList=  ltPrHeadersService.getPrById(id);
			return new ResponseEntity<LtPrHeaders>(prList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status update(@RequestBody LtPrHeaders input) 
	{
		try {
			return ltPrHeadersService.update(input);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value = "/getPrStatusById/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtPrHeaders> geRentalAgreementStatusById(@PathVariable("id") Long id) throws ServiceException
	{
		LtPrHeaders status=new LtPrHeaders();
	        status =  ltPrHeadersService.getPrStatusById(id);
			return new ResponseEntity<LtPrHeaders>(status,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/checkStatusIsPending/{prHeaderId}/{appid}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public boolean checkStatusIsPending(@PathVariable("prHeaderId") Long prHeaderId, @PathVariable("appid") Long appid,@PathVariable("logTime") String logTime) throws ServiceException
	{
		return  ltPrHeadersService.checkStatusIsPending(prHeaderId, appid);
	}
	
	@RequestMapping(value = "/submitforapproval/{prheaderid}/{logTime}", method = RequestMethod.GET)
	public ResponseEntity<Status> submitForApproval( @PathVariable("prheaderid") Long prheaderid,@PathVariable("logTime") String logTime)
	{
		Status status=new Status();
		try
		{		
			String stat =checkforApprovals(prheaderid);
			if(stat.equals("null"))
			{
				status.setCode(0);
				status.setMessage("No approvers found for employee's division.");
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}else {
				
				LtPrHeaders ltPrHeaders = ltPrHeadersService.getPrById(prheaderid);
				Integer userId = ltPrHeaders.getCreatedBy();
				Integer companyId = ltPrHeaders.getCompanyId();
				System.out.println("companyId in controller = "+companyId);
				System.out.println("ltPrHeaders = "+ltPrHeaders);
//				if(  ltInvoiceHeaders.getInvoiceAmount()!=null &&  ltInvoiceHeaders.getInvoiceAmount()>0) {
//					System.out.println("in if");
					status=ltPrHeadersService.submitForApproval(new Date(),prheaderid,INPROCESS,null,userId.longValue(),companyId.longValue());
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
	
	private String checkforApprovals(Long prHeaderId) throws ServiceException 
	{
		String stat = ltPrHeadersService.checkforApprovals(prHeaderId);
		return stat;
	}

}
