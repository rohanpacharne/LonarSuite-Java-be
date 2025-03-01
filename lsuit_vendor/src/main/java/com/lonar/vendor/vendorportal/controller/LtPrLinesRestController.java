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
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtPrLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtPrHeadersService;
import com.lonar.vendor.vendorportal.service.LtPrLinesService;

@RestController
@RequestMapping("/API/prlines")
public class LtPrLinesRestController {
	
	@Autowired
	LtPrLinesService ltPrLinesService;
	
	@RequestMapping(value = "/dataTable/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getLtPrLinesDataTable(LtPrLines input,@PathVariable("id") Long id,@PathVariable("logTime") String logTime) 
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try 
		{
				Long count=ltPrLinesService.getLtPrLinesDataTableCount(input,id);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtPrLines> ltMastCommonMessageList= 
			    		ltPrLinesService.getLtPrLinesDataTable(input,id);
				customeDataTable.setData(ltMastCommonMessageList);
		} 
		catch (Exception e) 
		{	
			 e.printStackTrace();
		}
		return customeDataTable;
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status save(@RequestBody LtPrLines input) 
	{
		try {
			return ltPrLinesService.save(input);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status update(@RequestBody LtPrLines input) 
	{
		try {
			return ltPrLinesService.update(input);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long prLineId,@PathVariable("logTime") String logTime) throws ServiceException
	{
			Status status=new Status();
			status =  ltPrLinesService.delete(prLineId);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPrLineById/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtPrLines> LtPrLines(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException
	{
		LtPrLines prList=  ltPrLinesService.getPrLineById(id);
			return new ResponseEntity<LtPrLines>(prList, HttpStatus.OK);
	}

}
