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
import com.lonar.vendor.vendorportal.model.LtInvoiceLines;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtRentalAgreementLinesService;

@RestController
@RequestMapping("/API/rentalagreementline")
public class LtRentalAgreementLinesRestController {
	
	@Autowired
	LtRentalAgreementLinesService ltRentalAgreementLinesService;
	
	@RequestMapping(value = "/dataTableByHeader/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getInvoiceLineDataTableByHeader(LtRentalAgreementLines input,@PathVariable("id") Long id,@PathVariable("logTime") String logTime) 
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try 
		{
				Long count=ltRentalAgreementLinesService.getLtRentalAgreementLinesCount(input,id);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtRentalAgreementLines> ltRentalAgreementLinesList = 
			    		ltRentalAgreementLinesService.getLtRentalAgreementLinesDataTable(input,id);
				customeDataTable.setData(ltRentalAgreementLinesList);	
		} 
		catch (Exception e) 
		{	
			 e.printStackTrace();
		}
		return customeDataTable;
		
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException
	{
		System.out.println("ltRentalAgreementLines = "+ltRentalAgreementLines);
			Status status=new Status();
			status =  ltRentalAgreementLinesService.save(ltRentalAgreementLines);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRentalAgreementLineById/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtRentalAgreementLines> getRentalAgreementLineById(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException
	{
		LtRentalAgreementLines ltRentalAgreementLines=  ltRentalAgreementLinesService.getRentalAgreementLineById(id);
			return new ResponseEntity<LtRentalAgreementLines>(ltRentalAgreementLines, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long agreementLineId,@PathVariable("logTime") String logTime)
	{
			Status status=new Status();
			status =  ltRentalAgreementLinesService.deleteByAgreementLineId(agreementLineId);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> update(@RequestBody LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException
	{
			System.out.println("in controller");
			Status status=new Status();
			status =  ltRentalAgreementLinesService.update(ltRentalAgreementLines);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
