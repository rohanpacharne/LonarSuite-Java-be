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

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtMastCustSiteContacts;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastCustomerSiteContactService;

@RestController
@RequestMapping("/API/sitecontacts")
public class LtMastCustomerSiteContactsController implements CodeMaster {

	@Autowired
	LtMastCustomerSiteContactService ltMastCustomerSiteContactService;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@RequestMapping(value = "/list/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtMastCustSiteContacts>> getAllCustSiteContacts(@PathVariable("logTime") String logTime) throws ServiceException
	{
			List<LtMastCustSiteContacts> custSiteCommercialsList=  ltMastCustomerSiteContactService.getAllCustSiteContacts();
			return new ResponseEntity<List<LtMastCustSiteContacts>>(custSiteCommercialsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getContactsByCustomerId/{customerId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtMastCustSiteContacts>> getContactsByCustomerId(@PathVariable("customerId") Long customerId,@PathVariable("logTime") String logTime) throws ServiceException
    {
            List<LtMastCustSiteContacts> custSiteCommercialsList= ltMastCustomerSiteContactService.getContactsByCustomerId(customerId);
            return new ResponseEntity<List<LtMastCustSiteContacts>>(custSiteCommercialsList, HttpStatus.OK);
    }
  
	@RequestMapping(value = "/getContactsByCustomerSiteId_OLD/{customerSiteId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtMastCustSiteContacts>> getContactsByCustomerSiteId(@PathVariable("customerSiteId") Long customerSiteId,@PathVariable("logTime") String logTime) throws ServiceException
	{
		 List<LtMastCustSiteContacts> vendorBank= ltMastCustomerSiteContactService.getContactsByCustomerSiteId(customerSiteId);
			return new ResponseEntity< List<LtMastCustSiteContacts>>(vendorBank, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getContactsByCustomerSiteId/{customerSiteId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getCustomerContactsDataTable(@PathVariable("customerSiteId") Long customerSiteId,@PathVariable("logTime") String logTime,LtMastCustSiteContacts input) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastCustomerSiteContactService.getByCustomerSiteContactsDataTableCount(customerSiteId,input);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastCustSiteContacts> ltMastCustSiteGenInfoList = ltMastCustomerSiteContactService.getByCustomerSiteContactsDataTable(customerSiteId,input);
			customeDataTable.setData(ltMastCustSiteGenInfoList);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return customeDataTable;
	}
	
	
	@RequestMapping(value = "/getCustSiteContactsById/{siteContactId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtMastCustSiteContacts> getCustSiteContactsById(@PathVariable("siteContactId") Long siteContactId,@PathVariable("logTime") String logTime) throws ServiceException
	{
		LtMastCustSiteContacts vendorBank=  ltMastCustomerSiteContactService.getCustSiteContactsById(siteContactId);
			return new ResponseEntity<LtMastCustSiteContacts>(vendorBank, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException
	{
		Status status = new Status();
		status =  ltMastCustomerSiteContactService.save(ltMastCustSiteContacts);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> update(@RequestBody LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException
	{
		Status status = new Status();
		status =  ltMastCustomerSiteContactService.update(ltMastCustSiteContacts);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long siteContactId,@PathVariable("logTime") String logTime)
	{
		Status status = new Status();
			try
			{
			 status =  ltMastCustomerSiteContactService.delete(siteContactId);
			}
			catch (org.springframework.dao.DataIntegrityViolationException e)
			{
				e.printStackTrace();
//				status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ResponseEntity<Status>(status,HttpStatus.OK);
			} 
			catch(Exception e)
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				e.printStackTrace();
			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
}
