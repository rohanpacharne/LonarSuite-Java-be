package com.lonar.asn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import com.lonar.asn.dao.LtMastEmailtokenDao;
import com.lonar.asn.dao.LtMastEmployeesDao;
import com.lonar.asn.dao.LtMastUsersDao;
import com.lonar.asn.dao.LtMastVendorsDao;
import com.lonar.asn.dao.LtShipmentApprovalHistoryDao;
import com.lonar.asn.dao.LtShipmentAttachmentDao;
import com.lonar.asn.dao.LtShipmentHeaderDao;
import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.LtMastAttachmentType;
import com.lonar.asn.model.LtMastEmailtoken;
import com.lonar.asn.model.LtMastEmployees;
import com.lonar.asn.model.LtMastUsers;
import com.lonar.asn.model.LtMastVendors;
import com.lonar.asn.model.LtPoShipment;
import com.lonar.asn.model.LtShipmentApprovalHistory;
import com.lonar.asn.model.LtShipmentAttachment;
import com.lonar.asn.model.LtShipmentHeaders;
import com.lonar.asn.model.LtShipmentLines;
import com.lonar.asn.model.ProcedureCall;
import com.lonar.asn.model.ShipmenntSources;
import com.lonar.asn.model.Status;
import com.lonar.asn.model.SubmitAsn;
import com.lonar.asn.repository.LtShipmentHeadersRepository;
import com.lonar.asn.repository.LtShipmentLinesRepository;
import com.lonar.asn.repository.ShipmenntSourcesRepository;

@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class LtShipmentHeaderServiceImpl implements LtShipmentHeaderService , CodeMaster{

	@Autowired
	private Environment env;
	
	@Autowired
	LtShipmentHeaderDao asnHeaderDao;
	
	@Autowired
	LtShipmentHeadersRepository ltShipmentHeadersRepository;
	
	@Autowired
	LtShipmentLinesRepository ltShipmentLinesRepository;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	ShipmenntSourcesRepository shipmenntSourcesRepository;
	
	@Autowired
	LtShipmentApprovalHistoryDao ltShipmentApprovalHistoryDao;
	
	@Autowired
	LtMastUsersDao ltMastUsersDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtMastEmailtokenDao ltMastEmailtokenDao;
	
	@Autowired
	LtShipmentAttachmentDao ltShipmentAttachmentDao;
	
	@Autowired
	LtMastVendorsDao ltMastVendorsDao;
	
	private static AtomicInteger counter = new AtomicInteger(0);

	
	@Override
	public List<String> getListOfStr() throws BusinessException {
		return asnHeaderDao.getListOfStr();
	}

	@Override
	@Transactional
	public ResponseEntity<Status> updateAsnHeader(LtShipmentHeaders ltShipmentHeaders) throws BusinessException {
		Status status = new Status();
		ltShipmentHeaders.setCreatedBy(ltShipmentHeaders.getLastUpdateLogin());
		ltShipmentHeaders.setLastUpdatedBy(ltShipmentHeaders.getLastUpdateLogin());
		ltShipmentHeaders.setLastUpdateLogin(ltShipmentHeaders.getLastUpdateLogin());
		ltShipmentHeaders.setCreationDate(new Date());
		ltShipmentHeaders.setLastUpdateDate(new Date());
		ltShipmentHeaders = ltShipmentHeadersRepository.save(ltShipmentHeaders);
	    if(ltShipmentHeaders.getShipmentHeaderId()!=null)
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
	    	try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null) {
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
	    return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> updateAsnLine(List<LtShipmentLines> listOfShipmentLines) throws BusinessException {
		Status status = new Status();
		for (LtShipmentLines ltShipmentLines : listOfShipmentLines) {
			
			LtShipmentLines ltShipmentLinesObj = ltShipmentLinesRepository.findOne(ltShipmentLines.getShipmentLineId());
			LtShipmentLines shipmentLine = asnHeaderDao.getQuantityByPoHeaderId(ltShipmentLinesObj.getPoHeaderId());
			System.out.println(shipmentLine.getQuantityShipped()+" "+ltShipmentLines.getQuantityShipped()+" "+ltShipmentLines.getQuantityOrdered() );
			//if(( ltShipmentLinesObj.getQuantityOrdered() >= shipmentLine.getQuantityShipped() )   ) {
				ltShipmentLinesObj.setQuantityReceived(ltShipmentLines.getQuantityReceived());
				ltShipmentLinesObj.setQuantityShipped(ltShipmentLines.getQuantityShipped());
			
				//updateRecivedQuantity(ltShipmentLinesObj);
				if(asnHeaderDao.updateRecivedQuantity(ltShipmentLinesObj)) {
					status.setMessage("Data updated successfully");
				} else {
					status.setMessage("Updation failed!!!!");
				}
			/*}else {
				status.setCode(UPDATE_FAIL);
				status.setMessage("Quantity Shipped should be less than or equal to Quantity Ordered");
				
			}*/
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Transactional
	private void updateRecivedQuantity(LtShipmentLines ltShipmentLines) {
		ltShipmentLinesRepository.save(ltShipmentLines);
	}

	@Override
	public Long getAsnHeaderDataCount(LtShipmentHeaders input) throws BusinessException {
		return asnHeaderDao.getAsnHeaderDataCount(input);
	}

	@Override
	public List<LtShipmentHeaders> getAsnHeaderDataTableDetails(LtShipmentHeaders input) throws BusinessException {

		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{
			input.setColumnNo(16);
		}
		return asnHeaderDao.getAsnHeaderDataTableDetails(input);
	}

	@Override
	public LtShipmentHeaders getByAsnHeaderId(Long id) throws BusinessException {
		return asnHeaderDao.getByAsnHeaderId(id);
	}

	@Override
	public List<LtShipmentLines> getAsnLinesByAsnHeaderId(Long id) throws BusinessException {
		return asnHeaderDao.getAsnLinesByAsnHeaderId(id);
	}

	@Override
	public Long getAsnHeaderDataCountByLocation(LtShipmentHeaders shipmentHeaders, Long locationId)
			throws BusinessException {
		return asnHeaderDao.getAsnHeaderDataCountByLocation(shipmentHeaders,locationId);
	}

	@Override
	public List<LtShipmentHeaders> getAsnHeaderDataTableDetailsByLocation(LtShipmentHeaders input,
			Long locationId) throws BusinessException {
		System.out.println(input.getColumnNo());
		System.out.println(input.getSort());
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{
			input.setColumnNo(16);
		}
		if(input.getColumnNo()==7 && input.getSort().equals("asc"))
		{
			input.setColumnNo(17);
		}
		return asnHeaderDao.getAsnHeaderDataTableDetailsByLocation(input,locationId);
	}

	@Override
	public ResponseEntity<Status> deleteAsn(Long id) throws BusinessException {
		Status status = new Status();
		try {
			ProcedureCall procedureCall = asnHeaderDao.asnDeletePkgCall(id);
			if(procedureCall.getStatusCode().equals("SUCCESS")) {
				ltShipmentAttachmentDao.deleteAttachmentByShipmentHeaderId(id);
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else {
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
//			status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> submitAsn(SubmitAsn submitAsn) throws BusinessException {
		System.out.println("in submit asn method");
		Status status= new Status();
		LtShipmentHeaders ltShipmentHeaders = asnHeaderDao.getByAsnHeaderId(submitAsn.getShipmentHeaderId());
		System.out.println("ltShipmentHeaders "+ltShipmentHeaders);
		System.out.println("recepted date "+ltShipmentHeaders.getExpectedReceiptDate()==null);
		if(ltShipmentHeaders.getExpectedReceiptDate()==null) {
			System.out.println("in recept date date condition");
			status.setCode(0);
			status.setMessage("Please select Expected Receipt Date");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		
		if(submitAsn.getStatus().equals("NEW")) {
			System.out.println("in status condition");
			List<LtMastAttachmentType> attachmentList = asnHeaderDao.getAsnAttachmentList(ltShipmentHeaders);
			System.out.println("attachment List ="+attachmentList);
			if(!attachmentList.isEmpty()) {
				System.out.println("in not empty condition");
				List<LtShipmentAttachment> ltShipmentAttachmentList = ltShipmentAttachmentDao.getAllFilesByShipmentHeaderId(ltShipmentHeaders.getShipmentHeaderId());
				System.out.println("ltShipmentAttachmentList"+ltShipmentAttachmentList);
				if(ltShipmentAttachmentList.isEmpty() ) {
					System.out.println("in ltShipmentAttachmentList empty");
					status.setCode(0);
					status.setMessage("Please attach mandatory attachments");
					return new ResponseEntity<Status>(status, HttpStatus.OK);
				}
			}
		}
		
		
		if(submitAsn.getStatus().equals("NEW")) {
			System.out.println("in status NEW");
			ProcedureCall procedureCall = asnHeaderDao.asnValidationPkgCall(ltShipmentHeaders.getShipmentHeaderId());
			status.setData(procedureCall.getShipmentHeaderId());
			System.out.println("procedureCall "+procedureCall);
			if(procedureCall.getStatusCode().equals("SUCCESS")) {
				if(asnHeaderDao.submitAsn(submitAsn)) {
					System.out.println("in status submitasn");
					if(submitAsn.getStatus().equals("NEW")) {
						System.out.println("in status new");
						sendAcknowledgeMail(ltShipmentHeaders,submitAsn.getStatus());
//						status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_SUBMIT);
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_SUBMIT").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println("return status = "+status);
					}else if(submitAsn.getStatus().equals("ASN_INPROGRESS")){
						System.out.println("in status asn progress");
//						status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_SUBMIT_FOR_APPROVAL);
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_SUBMIT_FOR_APPROVAL").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(submitAsn.getStatus().equals("ASN_ACCEPTED")) {
						System.out.println("in status asn accepted");
					sendAcknowledgeMail(ltShipmentHeaders,submitAsn.getStatus());
//					status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_ACCEPT);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_ACCEPT").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}else {
//					status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("status = "+status);
					System.out.println("status message "+status.getMessage());
					if( status.getMessage()==null) {
						status.setCode(0);
						status.setMessage("Error in finding message! The action is completed unsuccessfully.");
					}
				}
			}else {
				status.setCode(0);
				status.setMessage(procedureCall.getStatusMessage());
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}
		}else {
			if(asnHeaderDao.submitAsn(submitAsn)) {
				
				if(submitAsn.getStatus().equals("ASN_INPROGRESS")){
//					status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_SUBMIT_FOR_APPROVAL);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_SUBMIT_FOR_APPROVAL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else if(submitAsn.getStatus().equals("ASN_ACCEPTED")) {
					sendAcknowledgeMail(ltShipmentHeaders,submitAsn.getStatus());
//					status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_ACCEPT);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_ACCEPT").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else {
//				status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
			}
		}
		
		
	return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	
	
	private void sendAcknowledgeMail(LtShipmentHeaders ltShipmentHeaders, String status) {
		System.out.println("status = "+status);
		LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
		if(status.equals("ASN_ACCEPTED")) {
			System.out.println("in status equals ASN_ACCEPTED");
			LtMastVendors ltMastVendors = ltMastVendorsDao.getVendorById(ltShipmentHeaders.getVendorId());
			//LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
			String vendorName = ltMastVendors.getVendorName()+" ( "+ltMastVendors.getVendorCode()+" )";
			String shipmentNum = ltShipmentHeaders.getShipmentNum()+" ";
			ltMastEmailtoken.setEmailObject(
					"#$#$shipmentNumber="+shipmentNum+
					"#$#$VendorName="+vendorName+
					"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
			ltMastEmailtoken.setSendTo(ltMastVendors.getPrimaryEmail());
			ltMastEmailtoken.setEmailStatus("SENDING");
			ltMastEmailtoken.setEmailTitle("Shipment No : "+ltShipmentHeaders.getShipmentNum() +
					"  has been acknowledged");
	
			ltMastEmailtoken.setEmailTemplate("shipmentAcknowledge");
			ltMastEmailtoken.setExpiredWithin(1296000L);
			ltMastEmailtoken.setSendDate(new Date());
			ltMastEmailtoken.setEmailStatus("SENDING");
			ltMastEmailtoken.setExpiredWithin(1296000L);
			ltMastEmailtoken.setSendDate(new Date());
		}else {
			System.out.println("in else condition");
			StringBuffer emails = new StringBuffer();
			List<LtShipmentLines> lines =  asnHeaderDao.getAsnLinesByAsnHeaderId(ltShipmentHeaders.getShipmentHeaderId());
			for(LtShipmentLines ltShipmentLines : lines) {
				List<LtMastEmployees> employee = ltMastEmployeesDao.getByEmpIdForEmail(ltShipmentLines.getContactPersonId());
				if(!employee.isEmpty()) {
					//emails.concat(employee.get(0).getOfficialEmail()+",");
					emails.append(employee.get(0).getOfficialEmail()+",");
				}else {
					emails.append(ltShipmentLines.getContactNumber()+",");
				}
			}
			
			LtMastVendors ltMastVendors = ltMastVendorsDao.getVendorById(ltShipmentHeaders.getVendorId());
			String vendorName = ltMastVendors.getVendorName()+" ( "+ltMastVendors.getVendorCode()+" )";
			String shipmentNum = ltShipmentHeaders.getShipmentNum()+" ";
			ltMastEmailtoken.setEmailObject(
					"#$#$shipmentNumber="+shipmentNum+
					"#$#$VendorName="+vendorName+
					"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
			ltMastEmailtoken.setSendTo(emails+"");
			ltMastEmailtoken.setEmailStatus("SENDING");
			ltMastEmailtoken.setEmailTitle("Shipment No. "+ltShipmentHeaders.getShipmentNum() +
					"  pending for acknowledgement");
			
			ltMastEmailtoken.setEmailTemplate("shipmentAcknowledgeRequest");
			ltMastEmailtoken.setExpiredWithin(1296000L);
			ltMastEmailtoken.setSendDate(new Date());
			ltMastEmailtoken.setEmailStatus("SENDING");
			ltMastEmailtoken.setExpiredWithin(1296000L);
			ltMastEmailtoken.setSendDate(new Date());
		}
		ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
		
	}

	@Override
	public SubmitAsn getAsnStatusByAsnHeaderId(Long id) throws BusinessException {
		return asnHeaderDao.getAsnStatusByAsnHeaderId(id);
	}

	@Override
	public Long getCountpoShipmentDataTableByVendorId(LtPoShipment poShipment, Long vendorId) throws BusinessException {
		return asnHeaderDao.getCountpoShipmentDataTableByVendorId(poShipment,vendorId);
	}

	@Override
	public List<LtPoShipment> getPoShipmentDataTableByVendorId(LtPoShipment input, Long vendorId)
			throws BusinessException {
		// TODO Auto-generated method stub
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{
			input.setColumnNo(16);
		}
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{
			input.setColumnNo(17);
		}
		if(input.getColumnNo()==8 && input.getSort().equals("desc"))
		{
			input.setColumnNo(18);
		}
		if(input.getColumnNo()==9 && input.getSort().equals("desc"))
		{
			input.setColumnNo(19);
		}
		return asnHeaderDao.getPoShipmentDataTableByVendorId(input,vendorId);
	}
	
	@Override
	public ResponseEntity<Status> saveShipmentSource(List<ShipmenntSources> shipmenntSources) throws BusinessException {
		Status status = new Status();
		Long sourcesId = asnHeaderDao.getShipmentSourceIds();
//		int sourcesId = counter.incrementAndGet();
		System.out.println("sourcesId = "+sourcesId);
		List<ShipmenntSources> shipmenntSourcesUpdated = new ArrayList<>();
		
		for (Iterator iterator = shipmenntSources.iterator(); iterator.hasNext();) {
			ShipmenntSources shipmenntSources2 = (ShipmenntSources) iterator.next();
			
			shipmenntSources2.setShipmentSourceId(sourcesId);
			shipmenntSources2.setCreatedBy(shipmenntSources2.getLastUpdateLogin());
			shipmenntSources2.setLastUpdateLogin(shipmenntSources2.getLastUpdateLogin());
			shipmenntSources2.setLastUpdatedBy(shipmenntSources2.getLastUpdateLogin());
			shipmenntSources2.setCreationDate(new Date());
			shipmenntSources2.setLastUpdateDate(new Date());
			shipmenntSourcesUpdated.add(shipmenntSources2);
		}
		
		if(shipmenntSourcesUpdated.size() > 0) { 
		saveShipmentSourceData(shipmenntSourcesUpdated);
//		List<ShipmenntSources> savedSources = saveShipmentSourceData(shipmenntSourcesUpdated);
			        // Extract shipment_source_id from the saved entities
//			        Long sourcesId = savedSources.get(0).getShipmentSourceId();
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
		try {
			status.setCode(1);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			//status.setMessage("Data inserted successfuly ");
		}
		
		//call procedre for asn
		//lt_vpal_asn_pkg.create_asn(x_status ,x_message ,p_shipment_source_id);
		
		ProcedureCall procedureCall = asnHeaderDao.saveAsnHeaderAndLineData(sourcesId);
		status.setData(procedureCall.getShipmentHeaderId());
		if(procedureCall.getStatusCode().equals("SUCCESS")) {
		status.setCode(1);
		status.setMessage(procedureCall.getStatusMessage());
		}else {
			status.setCode(0);
			status.setMessage(procedureCall.getStatusMessage());
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Transactional
	private void saveShipmentSourceData(List<ShipmenntSources> shipmenntSourcesUpdated) {
		shipmenntSourcesRepository.save(shipmenntSourcesUpdated);
	}
//	@Transactional
//	private List<ShipmenntSources> saveShipmentSourceData(List<ShipmenntSources> shipmenntSourcesUpdated) {
//	    return shipmenntSourcesRepository.saveAll(shipmenntSourcesUpdated);
//	}

	@Override
	public ResponseEntity<Status> deleteAsnLineAttachment(Long id) throws BusinessException 
	{
		Status status = new Status();
		if(asnHeaderDao.deleteAsnLineAttachment(id)) {
//		status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if( status.getMessage()==null) {
		status.setCode(1);
		status.setMessage("Error in finding message! The action is completed successfully.");
		}
		}else {
//		status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if( status.getMessage()==null) {
		status.setCode(0);
		status.setMessage("Error in finding message! The action is completed unsuccessfully.");
		}
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> createInvoiceFromAsn(LtShipmentHeaders ltShipmentHeaders)
			throws BusinessException {
		Status status = new Status();
		ProcedureCall procedureCall = asnHeaderDao.createInvoiceFromAsn(ltShipmentHeaders.getShipmentHeaderId(),
				ltShipmentHeaders.getCreatedBy());
		if(procedureCall.getStatusCode().equals("SUCCESS")) {
			
			if(!asnHeaderDao.loadInvoiceApprovers(procedureCall.getShipmentHeaderId())){
				status.setCode(1);
				status.setMessage(procedureCall.getStatusMessage());
				status.setData(procedureCall.getShipmentHeaderId());
			}else {
				status.setCode(1);
				status.setMessage(procedureCall.getStatusMessage());
				status.setData(procedureCall.getShipmentHeaderId());
			}
		}else {
			status.setCode(0);
			status.setMessage(procedureCall.getStatusMessage());
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}



	@Override
	public List<AsnApproval> getShipmentApprovalByShipmentId(Long shipmentHeaderId) throws BusinessException {
		return asnHeaderDao.getShipmentApprovalByShipmentId(shipmentHeaderId);
	}

	@Override
	public Status updateShipmentStatusApproval(LtShipmentApprovalHistory approvalHistory) throws BusinessException {
		Status status=new Status();
		//DecimalFormat twoDForm = new DecimalFormat("0.00");
		approvalHistory.setLastUpdateDate(new Date());
		if(approvalHistory.getStatus().equals(ASN_APPROVED))
		{
			if(asnHeaderDao.updateStatusApproval(approvalHistory) ){
//				status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_APPROVED);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_APPROVED").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(status==null) {
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully");
				}
			}else {
//				status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_APPROVAL_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_APPROVAL_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(status==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully");
				}
			}
		}
		else if(approvalHistory.getStatus().equals(ASN_FEEDBACK_AWAITED) )
		{
			if(asnHeaderDao.updateStatusApproval(approvalHistory))
			{
				if(asnHeaderDao.submitForApproval(null,approvalHistory.getShipmentHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin())) {
				
					LtShipmentHeaders ltShipmentHeaders= asnHeaderDao.getByAsnHeaderId(approvalHistory.getShipmentHeaderId());
					if(ltShipmentHeaders !=null)
					{		
						LtMastUsers ltMastUsers = ltMastUsersDao.getLtMastUsersByID(ltShipmentHeaders.getCreatedBy());
						List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(approvalHistory.getEmployeeId());
						LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
						ltMastEmailtoken.setEmailObject(
							"#$#$shipmentNumber="+ltShipmentHeaders.getShipmentNum()+
							"#$#$name="+ltMastUsers.getEmployeeName()+
							"#$#$approverName="+ltMastEmployees.get(0).getEmpName()+
							"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
					ltMastEmailtoken.setSendTo(ltMastUsers.getEmail());
					ltMastEmailtoken.setEmailStatus("SENDING");
					ltMastEmailtoken.setEmailTitle("Shipment Number : "+ltShipmentHeaders.getShipmentNum() +
							"has been sent for feedback.");
					
					ltMastEmailtoken.setEmailTemplate("shipmentFeedback");
					ltMastEmailtoken.setExpiredWithin(1296000L);
					ltMastEmailtoken.setSendDate(new Date());
					ltMastEmailtoken.setEmailStatus("SENDING");
					
					ltMastEmailtoken.setExpiredWithin(1296000L);
					ltMastEmailtoken.setSendDate(new Date());
					/*if(ltInvoiceHeaders.getBuyerId()!=null) {
						List<LtMastEmployees> buyer = ltMastEmployeesDao.getByEmpIdForEmail(ltInvoiceHeaders.getBuyerId());
					ltMastEmailtoken.setSendCc(buyer.get(0).getOfficialEmail()+","+ltMastEmployees.get(0).getOfficialEmail());
					}*/
					//ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(ltMastVendors.getInitiatorId());
					//ltMastEmailtoken.setSendCc(ltMastEmployees.get(0).getOfficialEmail());
					ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
					
//					status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_FEEDBACK_AWAITED);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_FEEDBACK_AWAITED").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
				String currentLevel=null;
				if(approvalHistory.getShipmentApprovalId()!=null	)
				{
					currentLevel=asnHeaderDao.getCurrLevelByShipmentApprovalId(approvalHistory.getShipmentApprovalId());
				}
				asnHeaderDao.upDateStatus(approvalHistory.getShipmentHeaderId(), NO_ACTION, currentLevel);	
			}	
		  }
		}
		else if(approvalHistory.getStatus().equals(ASN_REJECTED))
		{
			if(asnHeaderDao.updateStatusApproval(approvalHistory)) 
			{
				if(asnHeaderDao.submitForApproval(null,approvalHistory.getShipmentHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin()))
				{
					LtShipmentHeaders ltShipmentHeaders= asnHeaderDao.getByAsnHeaderId(approvalHistory.getShipmentHeaderId());
					LtMastUsers ltMastUsers = ltMastUsersDao.getLtMastUsersByID(ltShipmentHeaders.getCreatedBy());
					if(ltMastUsers !=null)
					{
						List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(approvalHistory.getEmployeeId());
							LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
							if(ltMastUsers.getEmployeeName()==null) {
								ltMastUsers.setEmployeeName(ltMastUsers.getVendorName());
							}
							ltMastEmailtoken.setEmailObject(
									"#$#$shipmentNumber="+ltShipmentHeaders.getShipmentNum()+
									"#$#$name="+ltMastUsers.getEmployeeName()+
									"#$#$approverName="+ltMastEmployees.get(0).getEmpName()+
									"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
							ltMastEmailtoken.setSendTo(ltMastUsers.getEmail());
							ltMastEmailtoken.setEmailStatus("SENDING");
							//ltMastEmailtoken.setEmailTitle("Vendor reject mail");
							ltMastEmailtoken.setEmailTitle("Shipment Number : "+ltShipmentHeaders.getShipmentNum() 
									 + "has been rejected");
							ltMastEmailtoken.setEmailTemplate("shipmentReject");
							
							ltMastEmailtoken.setExpiredWithin(1296000L);
							ltMastEmailtoken.setSendDate(new Date());
							/*if(ltInvoiceHeaders.getBuyerId()!=null) {
								List<LtMastEmployees> buyer = ltMastEmployeesDao.getByEmpIdForEmail(ltInvoiceHeaders.getBuyerId());
							ltMastEmailtoken.setSendCc(buyer.get(0).getOfficialEmail()+","+ltMastEmployees.get(0).getOfficialEmail());
							}*/
							ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
		
//							status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_REJECTED);
							try {
								status.setCode(0);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_REJECTED").getMessageName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					asnHeaderDao.upDateStatus(approvalHistory.getShipmentHeaderId(), NO_ACTION, null);	
			
				}
			}
		}
		ltShipmentApprovalHistoryDao.save(approvalHistory);
		return status;
	}

	@Override
	public boolean checkStatusIsPending(Long shipmentId, Long approvalId) throws BusinessException {
		return asnHeaderDao.checkStatusIsPending(shipmentId, approvalId);
	}

	@Override
	public AsnApproval getShipmentApproval(Long shipmentId, Long apprId) throws BusinessException {
		return asnHeaderDao.getShipmentApproval(shipmentId,apprId);
	}

	@Override
	public List<LtShipmentApprovalHistory> getApprovalHistoryByShipmentId(Long shipmentHeaderId)
			throws BusinessException {
		return asnHeaderDao.getApprovalHistoryByShipmentId(shipmentHeaderId);
	}

	@Override
	public ResponseEntity<Status> saveRemark(LtShipmentApprovalHistory ltShipmentApprovalHistory)
			throws BusinessException {
		Status status = new Status();
		ltShipmentApprovalHistory.setLastUpdateDate(new Date());
		if (asnHeaderDao.saveRemark(ltShipmentApprovalHistory)) 
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(SHIPMENT_REMARK_SAVED);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SHIPMENT_REMARK_SAVED").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		 return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public Status getAsnApprovalByAsnHeaderId(Long asnHeaderId) throws BusinessException {
		Status status = asnHeaderDao.getAsnApprovalByAsnHeaderId(asnHeaderId);
		return status;
	}

}
