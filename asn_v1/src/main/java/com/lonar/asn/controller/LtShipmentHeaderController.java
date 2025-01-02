package com.lonar.asn.controller;


import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.CustomeDataTable;
import com.lonar.asn.model.LtPoShipment;
import com.lonar.asn.model.LtShipmentApprovalHistory;
import com.lonar.asn.model.LtShipmentHeaders;
import com.lonar.asn.model.LtShipmentLines;
import com.lonar.asn.model.ShipmenntSources;
import com.lonar.asn.model.Status;
import com.lonar.asn.model.SubmitAsn;
import com.lonar.asn.service.LtShipmentHeaderService;

@RestController
@RequestMapping("/API/asnheader")
public class LtShipmentHeaderController implements CodeMaster{

	@Autowired
	LtShipmentHeaderService asnHeaderService;
	
	@RequestMapping(value = "/asnheaderdataTable/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getAsnHeaderDataTableDetails(LtShipmentHeaders shipmentHeaders,@PathVariable("logTime") String logTime) 
	{
		try {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		Long count= asnHeaderService.getAsnHeaderDataCount(shipmentHeaders);
		customeDataTable.setRecordsTotal(count);
	    customeDataTable.setRecordsFiltered(count);
	    List<LtShipmentHeaders> shipmentHeadersList= 
	    		asnHeaderService.getAsnHeaderDataTableDetails(shipmentHeaders);
		customeDataTable.setData(shipmentHeadersList);	
		return customeDataTable;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	@RequestMapping(value = "/asnheaderdataTableByLocation/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getAsnHeaderDataTableByLocation(LtShipmentHeaders shipmentHeaders,@PathVariable("id") Long locationId,@PathVariable("logTime") String logTime) 
	{
		try {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		Long count= asnHeaderService.getAsnHeaderDataCountByLocation(shipmentHeaders,locationId);
		customeDataTable.setRecordsTotal(count);
	    customeDataTable.setRecordsFiltered(count);
	    List<LtShipmentHeaders> shipmentHeadersList= 
	    		asnHeaderService.getAsnHeaderDataTableDetailsByLocation(shipmentHeaders,locationId);
		customeDataTable.setData(shipmentHeadersList);	
		return customeDataTable;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping(value="/getbyasnheaderid/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LtShipmentHeaders> getByAsnHeaderId(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) {
		try {
		LtShipmentHeaders ltShipmentHeaders = asnHeaderService.getByAsnHeaderId(id);
		return new ResponseEntity<LtShipmentHeaders>(ltShipmentHeaders, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/submitasn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> submitAsn(@RequestBody SubmitAsn submitAsn) {
		return asnHeaderService.submitAsn(submitAsn);
	}
	
	@RequestMapping(value="/getasnstatusbyheaderid/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubmitAsn> getAsnStatusByAsnHeaderId(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) {
		SubmitAsn submitAsn = asnHeaderService.getAsnStatusByAsnHeaderId(id);
		return new ResponseEntity<SubmitAsn>(submitAsn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateasnheader", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateAsnHeader(@RequestBody LtShipmentHeaders ltShipmentHeaders,
			BindingResult bindingResult) throws BusinessException {
		return asnHeaderService.updateAsnHeader(ltShipmentHeaders);
	}
	
	@RequestMapping(value="/getasnlinesbyasnheaderid/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtShipmentLines>> getAsnLinesByAsnHeaderId(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) {
		List<LtShipmentLines> ltShipmentLinesList = asnHeaderService.getAsnLinesByAsnHeaderId(id);
		return new ResponseEntity<List<LtShipmentLines>>(ltShipmentLinesList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateasnline", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateAsnLine(@RequestBody List<LtShipmentLines> listOfShipmentLines,
			BindingResult bindingResult) throws BusinessException {
		return asnHeaderService.updateAsnLine(listOfShipmentLines);
	}
	
	@RequestMapping(value = "/delete/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteAsn(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws BusinessException {
		return asnHeaderService.deleteAsn(id);
	}
	
	@RequestMapping(value = "/poshipmentdatatablebyvendorid/{vendorid}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable poShipmentDataTableByVendorId(LtPoShipment poShipment, @PathVariable("vendorid") Long vendorid,@PathVariable("logTime") String logTime) 
	{
		try {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		Long count= asnHeaderService.getCountpoShipmentDataTableByVendorId(poShipment,vendorid);
		customeDataTable.setRecordsTotal(count);
	    customeDataTable.setRecordsFiltered(count);
	    List<LtPoShipment> poShipmentList= 
	    		asnHeaderService.getPoShipmentDataTableByVendorId(poShipment,vendorid);
		customeDataTable.setData(poShipmentList);	
		return customeDataTable;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/saveshipmentsource", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveShipmentSource(@RequestBody List<ShipmenntSources> shipmenntSources,
			BindingResult bindingResult) throws BusinessException {
		return asnHeaderService.saveShipmentSource(shipmenntSources);
	}
	

	@RequestMapping(value = "/deletelineattch/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteAsnLineAttachment(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws BusinessException {
		return asnHeaderService.deleteAsnLineAttachment(id);
	}
	
	@RequestMapping(value = "/createinvoicefromasn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> createInvoiceFromAsn(@RequestBody LtShipmentHeaders ltShipmentHeaders,
			BindingResult bindingResult) throws BusinessException {
		return asnHeaderService.createInvoiceFromAsn(ltShipmentHeaders);
	}
		
	@RequestMapping(value = "/getApprovalByShipmentId/{shipmentId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<AsnApproval>> getShipmentApprovalByShipmentId(@PathVariable("shipmentId") Long shipmentId,@PathVariable("logTime") String logTime) throws BusinessException
    {
            List<AsnApproval> asnApproval=
            		asnHeaderService.getShipmentApprovalByShipmentId(shipmentId);
            return new ResponseEntity<List<AsnApproval>>(asnApproval, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/updateShipmentStatusApproval", method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Status> updateShipmentStatusApproval( @RequestBody LtShipmentApprovalHistory approvalHistory )
	{
		Status status=new Status();
		try {
			status=asnHeaderService.updateShipmentStatusApproval(approvalHistory);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveRemark", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> saveRemark(@RequestBody LtShipmentApprovalHistory ltShipmentApprovalHistory) throws ServiceException
	{
		return asnHeaderService.saveRemark(ltShipmentApprovalHistory);
	}
	
	@RequestMapping(value = "/checkStatusIsPending/{shipmentId}/{approvalId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public boolean checkStatusIsPending(@PathVariable("shipmentId") Long shipmentId, @PathVariable("approvalId") Long approvalId,@PathVariable("logTime") String logTime) throws BusinessException
	{
		return  asnHeaderService.checkStatusIsPending(shipmentId, approvalId);
	}
	
	@RequestMapping(value = "/getShipmentApproval/{shipmentId}/{approvalId}/{logTime}", method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<Status> getShipmentApproval(@PathVariable("shipmentId") Long shipmentId, @PathVariable("approvalId") Long apprId,@PathVariable("logTime") String logTime)
	{
		Status status = new Status();
		AsnApproval asnApproval=null;
		try{
			asnApproval=asnHeaderService.getShipmentApproval(shipmentId, apprId);
			status.setData(asnApproval);
			if(asnApproval != null) {
				status.setCode(1);
			}
			status.setCode(2);
		}catch(Exception e){
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getApprovalHistoryByShipmentId/{shipmentHeaderId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtShipmentApprovalHistory>> getApprovalHistoryByShipmentId(@PathVariable("shipmentHeaderId") Long shipmentHeaderId,@PathVariable("logTime") String logTime) throws ServiceException
    {
          List<LtShipmentApprovalHistory> ltShipmentApprovalHistory=
        		  asnHeaderService.getApprovalHistoryByShipmentId(shipmentHeaderId);
          return new ResponseEntity<List<LtShipmentApprovalHistory>>(ltShipmentApprovalHistory, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getAsnApprovalByAsnHeaderId/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> getAsnApprovalByAsnHeaderId(@PathVariable("id") Long asnHeaderId,@PathVariable("logTime") String logTime) throws ServiceException
	{
		Status status = new Status();
			 status =  asnHeaderService.getAsnApprovalByAsnHeaderId(asnHeaderId);
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
}
