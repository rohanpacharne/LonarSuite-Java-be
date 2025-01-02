package com.lonar.vendor.vendorportal.controller;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastCustomerInfoService;

@RestController
@RequestMapping("/API/customer")
@PropertySource(value = "classpath:serverurl.properties", ignoreResourceNotFound = true)
public class LtMastCustomerInfoController implements CodeMaster {

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Autowired
	LtMastCustomerInfoService ltMastCustomerInfoService;

	@RequestMapping(value = "/dataTable/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getLtMastCustomerInfoDataTable(@PathVariable("companyId") Long companyId, @PathVariable("logTime") String logTime,
			LtMastCustomer input) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastCustomerInfoService.getLtMastCustomerInfoCount(companyId, input);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastCustomer> ltMastCommonMessageList = ltMastCustomerInfoService.getLtMastCustomerDataTable(companyId,input);
			customeDataTable.setData(ltMastCommonMessageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customeDataTable;
	}
 

	@RequestMapping(value = "/allCustomerInfo/{companyId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LtMastCustomer>> getAllCustomerInfo(@PathVariable("companyId") long companyId,@PathVariable("logTime") String logTime)
			throws ServiceException {
		List<LtMastCustomer> customerInfoList = null;
		try {
			customerInfoList = ltMastCustomerInfoService.getAllCustomerInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<LtMastCustomer>>(customerInfoList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllActiveCustomerInfo/{companyId}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LtMastCustomer>> getAllActiveCustomerInfo(@PathVariable("companyId") long companyId,@PathVariable("logTime") String logTime)
			throws ServiceException {
		List<LtMastCustomer> customerInfoList = null;
		try {
			customerInfoList = ltMastCustomerInfoService.getAllActiveCustomerInfo(companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<LtMastCustomer>>(customerInfoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllCustomerInfoByInitiator/{id}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LtMastCustomer>> getAllCustomerByInitiator(@PathVariable("id") Long initiatorId,
			@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastCustomer> customerInfoList = null; 
		return new ResponseEntity<List<LtMastCustomer>>(customerInfoList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<LtMastCustomer> getCustomerById(@PathVariable("id") Long customerId,
			@PathVariable("logTime") String logTime) throws ServiceException {
		LtMastCustomer customer = null;
		try {
			customer = ltMastCustomerInfoService.getCustomerById(customerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<LtMastCustomer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Status> save(@RequestBody LtMastCustomer customerInfo) throws ServiceException {
		Status status = new Status();
		try {
			status = ltMastCustomerInfoService.save(customerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Status> update(@RequestBody LtMastCustomer customerInfo) throws ServiceException {
		Status status = new Status();
		try {
			status =  ltMastCustomerInfoService.update(customerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}/{logTime}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Status> delete(@PathVariable("id") Long customerId, @PathVariable("logTime") String logTime) {
		Status status = new Status();
		try {
			status = ltMastCustomerInfoService.delete(customerId);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
