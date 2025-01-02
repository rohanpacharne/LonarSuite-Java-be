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
import com.lonar.vendor.vendorportal.model.LtMastCustSiteGenInfo;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastCustomerSiteGenInfoService;

@RestController
@RequestMapping("/API/customersitegen")
public class LtMastCustomerSiteGenInfoController {

	@Autowired
	private LtMastCustomerSiteGenInfoService ltMastCustomerSiteGenInfoService;
	
	@RequestMapping(value = "/getCustomerSitegenInfoById/{siteGenInfoId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<LtMastCustSiteGenInfo> getBySiteGenInfoId(@PathVariable("siteGenInfoId") Long siteGenInfoId, @PathVariable("logTime") String logTime) throws ServiceException{
		
		LtMastCustSiteGenInfo ltMastCustSiteGenInfo = ltMastCustomerSiteGenInfoService.getBySiteGenInfoId(siteGenInfoId);
			return new ResponseEntity<LtMastCustSiteGenInfo>(ltMastCustSiteGenInfo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCustSiteGenInfoByCustomerSiteId_OLD/{customerSiteId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LtMastCustSiteGenInfo>> getBycustomerSiteId(@PathVariable("customerSiteId") Long customerSiteId,@PathVariable("logTime") String logTime) throws ServiceException{
		List<LtMastCustSiteGenInfo> ltMastCustSiteGenInfoList = ltMastCustomerSiteGenInfoService.getBycustomerSiteId(customerSiteId);
		return new ResponseEntity<List<LtMastCustSiteGenInfo>>(ltMastCustSiteGenInfoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCustSiteGenInfoByCustomerSiteId/{customerSiteId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getCustomerSitesDataTable(@PathVariable("customerSiteId") Long customerSiteId,@PathVariable("logTime") String logTime,LtMastCustSiteGenInfo input) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastCustomerSiteGenInfoService.getByCustomerSiteDataTableCount(customerSiteId,input);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastCustSiteGenInfo> ltMastCustSiteGenInfoList = ltMastCustomerSiteGenInfoService.getByCustomerSiteDataTable(customerSiteId,input);
			customeDataTable.setData(ltMastCustSiteGenInfoList);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return customeDataTable;
	}
	
	
	@RequestMapping(value = "/getCustSiteGenInfoByCustomerid/{customerId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LtMastCustSiteGenInfo>> getBycustomerId(@PathVariable("customerId") Long customerId,@PathVariable("logTime") String logTime) throws ServiceException{
		List<LtMastCustSiteGenInfo> ltMastCustSiteGenInfoList =  ltMastCustomerSiteGenInfoService.getBycustomerId(customerId);
		return new ResponseEntity<List<LtMastCustSiteGenInfo>>(ltMastCustSiteGenInfoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Status> save(@RequestBody LtMastCustSiteGenInfo ltMastCustSiteGenInfo)  {
		Status status = new Status();
		try {
			status = ltMastCustomerSiteGenInfoService.save(ltMastCustSiteGenInfo);
		} catch (com.lonar.vendor.vendorportal.model.ServiceException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Status> update(@RequestBody LtMastCustSiteGenInfo ltMastCustSiteGenInfo) {
		Status status = new Status();
		try {
			status = ltMastCustomerSiteGenInfoService.update(ltMastCustSiteGenInfo);
		} catch (com.lonar.vendor.vendorportal.model.ServiceException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	@RequestMapping(value = "/delete/{sitegeninfoid}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Status> delete(@PathVariable("sitegeninfoid") Long customerId,@PathVariable("logTime") String logTime) {
		Status status = new Status();
		try {
			status = ltMastCustomerSiteGenInfoService.delete(customerId);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
}
//LtMastCustSiteGenInfo
