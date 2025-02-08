package com.lonar.vendor.vendorportal.controller;

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

import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtPrHeadersService;

@RestController
@RequestMapping("/API/prheaders")
public class LtPrHeadersRestController {
	
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
	public Status getLtPrHeadersDataTable(@RequestBody LtPrHeaders input) 
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

}
