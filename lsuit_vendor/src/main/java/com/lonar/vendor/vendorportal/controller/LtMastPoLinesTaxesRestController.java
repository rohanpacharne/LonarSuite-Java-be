package com.lonar.vendor.vendorportal.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.service.LtMastPoLinesTaxesService;
 
@RestController
@RequestMapping("/API/polinetax")
public class LtMastPoLinesTaxesRestController {
	@Autowired	
	LtMastPoLinesTaxesService  polineTaxesService;

	@RequestMapping(value = "/getPoLineTaxesByLineId/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtPoLineTaxes>> getAllPoLinesByLineId(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException
	{
			List<LtPoLineTaxes> poTaxList=  polineTaxesService.getAllPoLinesByLineId(id);
			return new ResponseEntity<List<LtPoLineTaxes>>(poTaxList, HttpStatus.OK);
	}
}