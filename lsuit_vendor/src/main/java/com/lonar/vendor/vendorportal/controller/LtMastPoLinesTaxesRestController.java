package com.lonar.vendor.vendorportal.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastPoLinesTaxesService;
 
@RestController
@RequestMapping("/API/polinetax")
public class LtMastPoLinesTaxesRestController {
	@Autowired	
	LtMastPoLinesTaxesService  ltPoLineTaxesService;

	@RequestMapping(value = "/getPoLineTaxesByLineId/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtPoLineTaxes>> getAllPoLinesByLineId(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException
	{
			List<LtPoLineTaxes> poTaxList=  ltPoLineTaxesService.getAllPoLinesByLineId(id);
			return new ResponseEntity<List<LtPoLineTaxes>>(poTaxList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody List<LtPoLineTaxes> ltPoLineTaxesList) throws ServiceException
	{
			Status status=new Status();
			status =  ltPoLineTaxesService.save(ltPoLineTaxesList);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> update(@RequestBody LtPoLineTaxes ltPoLineTaxes) throws ServiceException
	{
			Status status=new Status();
			status =  ltPoLineTaxesService.update(ltPoLineTaxes);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long poLineTaxId,@PathVariable("logTime") String logTime)
	{
			Status status=new Status();
			status =  ltPoLineTaxesService.delete(poLineTaxId);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
}