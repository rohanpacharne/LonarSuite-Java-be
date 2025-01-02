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
import com.lonar.vendor.vendorportal.model.LtMastCustomerSites;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastCustomerSitesService;

@RestController
@RequestMapping("/API/customersites")
public class LtMastCustomerSitesController {

	@Autowired
	private LtMastCustomerSitesService ltMastCustomerSitesService;
	
	@RequestMapping(value = "/dataTable/{customerId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getCustomerSitesDataTable(@PathVariable("customerId") Long customerId,@PathVariable("logTime") String logTime,LtMastCustomerSites input) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastCustomerSitesService.getCustomerSitesCount(customerId,input);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastCustomerSites> ltMastCustomerSitesList = ltMastCustomerSitesService.getCustomerSitesDataTable(customerId,input);
			customeDataTable.setData(ltMastCustomerSitesList);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return customeDataTable;

	}
 
	@RequestMapping(value = "/list/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LtMastCustomerSites>> getAllCustomerSites(@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastCustomerSites> customerSiteList = ltMastCustomerSitesService.getAllCustomerSites();
		return new ResponseEntity<List<LtMastCustomerSites>>(customerSiteList, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getCustomerSiteById/{customerSiteId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<LtMastCustomerSites> getCustomerSiteById(@PathVariable("customerSiteId") Long customerSiteId,
			@PathVariable("logTime") String logTime) throws ServiceException {
		LtMastCustomerSites customerSiteList = ltMastCustomerSitesService.getCustomerSiteById(customerSiteId);
		return new ResponseEntity<LtMastCustomerSites>(customerSiteList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCustomerSiteByCustomerId/{customerId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LtMastCustomerSites>> getCustomerSiteByCustomerId(@PathVariable("customerId") Long customerId,
			@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastCustomerSites> customerSiteList =  ltMastCustomerSitesService.getCustomerSiteByCustomerId(customerId);
		return new ResponseEntity<List<LtMastCustomerSites>>(customerSiteList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Status> save(@RequestBody LtMastCustomerSites ltMastCustomerSites)  {
		Status status = new Status();
		try {
			status = ltMastCustomerSitesService.save(ltMastCustomerSites);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
		 
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Status> update(@RequestBody LtMastCustomerSites ltMastCustomerSites)   {
		Status status = new Status();
		try {
			status =  ltMastCustomerSitesService.update(ltMastCustomerSites);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	@RequestMapping(value = "/delete/{customerSiteId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Status> delete(@PathVariable("customerSiteId") Long customerSiteId,@PathVariable("logTime") String logTime) {
		Status status = new Status();
		try {
			status = ltMastCustomerSitesService.delete(customerSiteId);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
}
