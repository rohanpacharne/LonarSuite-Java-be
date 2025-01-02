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
import com.lonar.vendor.vendorportal.model.LtMastCustSiteCommercials;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastCustomerSiteCommercialsService;

@RestController
@RequestMapping("/API/sitecommercials")
public class LtMastCustomerSitesCommercialsController implements CodeMaster {

	@Autowired
	LtMastCustomerSiteCommercialsService ltMastCustomerSiteCommercialsService;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@RequestMapping(value = "/list/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtMastCustSiteCommercials>> getAllVendorBanks(@PathVariable("logTime") String logTime) throws ServiceException
	{
			List<LtMastCustSiteCommercials> custSiteCommercialsList=  ltMastCustomerSiteCommercialsService.getAllCustSiteCommercials();
			return new ResponseEntity<List<LtMastCustSiteCommercials>>(custSiteCommercialsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getByCustomerId/{customerId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtMastCustSiteCommercials>> getByCustomerId(@PathVariable("customerId") Long customerId,@PathVariable("logTime") String logTime) throws ServiceException
    {
            List<LtMastCustSiteCommercials> custSiteCommercialsList= ltMastCustomerSiteCommercialsService.getByCustomerId(customerId);
            return new ResponseEntity<List<LtMastCustSiteCommercials>>(custSiteCommercialsList, HttpStatus.OK);
    }
  
	@RequestMapping(value = "/getByCustomerSiteId_OLD/{customerSiteId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtMastCustSiteCommercials>> getByCustomerSiteId(@PathVariable("customerSiteId") Long customerSiteId,@PathVariable("logTime") String logTime) throws ServiceException
	{
		 List<LtMastCustSiteCommercials> vendorBank= ltMastCustomerSiteCommercialsService.getByCustomerSiteId(customerSiteId);
			return new ResponseEntity< List<LtMastCustSiteCommercials>>(vendorBank, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getByCustomerSiteId/{customerSiteId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getCustomerCommercialsDataTable(@PathVariable("customerSiteId") Long customerSiteId,@PathVariable("logTime") String logTime,LtMastCustSiteCommercials input) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastCustomerSiteCommercialsService.getCustomerCommercialsDataTableCount(customerSiteId,input);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastCustSiteCommercials> ltMastCustSiteCommercials = ltMastCustomerSiteCommercialsService.getCustomerCommercialsDataTable(customerSiteId,input);
			customeDataTable.setData(ltMastCustSiteCommercials);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customeDataTable;
	}
	
	@RequestMapping(value = "/getById/{siteCommercialId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtMastCustSiteCommercials> getBySiteCommercialId(@PathVariable("siteCommercialId") Long siteCommercialId,@PathVariable("logTime") String logTime) throws ServiceException
	{
		LtMastCustSiteCommercials vendorBank=  ltMastCustomerSiteCommercialsService.getBySiteCommercialId(siteCommercialId);
			return new ResponseEntity<LtMastCustSiteCommercials>(vendorBank, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/save", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> save(@RequestBody LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException
	{
		Status status = new Status();
		status =  ltMastCustomerSiteCommercialsService.save(ltMastCustSiteCommercials);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> update(@RequestBody LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException
	{
		Status status = new Status();
		status =  ltMastCustomerSiteCommercialsService.update(ltMastCustSiteCommercials);
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("id") Long vendorBankId,@PathVariable("logTime") String logTime)
	{
		Status status = new Status();
			try
			{
			 status =  ltMastCustomerSiteCommercialsService.delete(vendorBankId);
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
