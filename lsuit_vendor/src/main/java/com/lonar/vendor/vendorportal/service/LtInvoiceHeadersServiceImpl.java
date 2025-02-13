package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.dao.LtInvoiceHeadersDao;
import com.lonar.vendor.vendorportal.dao.LtInvoiceLinesDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.dao.LtMastVendorsDao;
import com.lonar.vendor.vendorportal.dao.LtVendorApprovalDao;
import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.DashboardDetails;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtInvoiceLines;
import com.lonar.vendor.vendorportal.model.ProcedureCall;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtInvoiceHeadersServiceImpl implements LtInvoiceHeadersService,CodeMaster
{

	@Autowired
	LtInvoiceHeadersDao ltInvoiceHeadersDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtVendorApprovalDao ltVendorApprovalDao; 
	
	@Autowired
	LtMastVendorsDao ltMastVendorsDao;
	
	@Autowired
	LtInvoiceLinesDao ltInvoiceLinesDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Override
	public Long getLtInvoiceHeadersCount(LtInvoiceHeaders input) throws ServiceException 
	{
		return ltInvoiceHeadersDao.getLtInvoiceHeadersCount(input,null);
	}

	@Override
	public List<LtInvoiceHeaders> getLtInvoiceHeadersDataTable(LtInvoiceHeaders input) throws ServiceException 
	{
		if(input.getColumnNo()==1 && input.getSort().equals("asc"))
		{
			input.setColumnNo(11);
		}
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
		if(input.getColumnNo()==6 && input.getSort().equals("asc"))
		{
			input.setColumnNo(16);
		}
		if(input.getColumnNo()==7 && input.getSort().equals("asc"))
		{
			input.setColumnNo(17);
		}
		if(input.getColumnNo()==8 && input.getSort().equals("asc"))
		{
			input.setColumnNo(18);
		}
		if(input.getColumnNo()==9 && input.getSort().equals("asc"))
		{
			input.setColumnNo(19);
		}
		if(input.getColumnNo()==10 && input.getSort().equals("asc"))
		{
			input.setColumnNo(20);
		}
		return ltInvoiceHeadersDao.getLtInvoiceHeadersDataTable(input,null);
	}

	@Override
	public Long getLtInvoiceHeadersCountByVendorId(LtInvoiceHeaders input, Long venId) throws ServiceException {
		return ltInvoiceHeadersDao.getLtInvoiceHeadersCountByVendorId(input,venId);
	}

	@Override
	public List<LtInvoiceHeaders> getLtInvoiceHeadersDataTableByVendorId(LtInvoiceHeaders input, Long venId)
			throws ServiceException {
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
		return ltInvoiceHeadersDao.getLtInvoiceHeadersDataTableByVendorId(input,venId);
	}

	@Override
	public List<DashboardDetails> getCountAndStatusByVendorId(Long vendorId) throws ServiceException {
		return ltInvoiceHeadersDao.getCountAndStatusByVendorId(vendorId);
	}

//	@Override
//	public List<LtInvoiceHeaders> getAllInvoice() throws ServiceException{
//		return ltInvoiceHeadersDao.getAllInvoice();
//	}

	@Override
	public List<LtInvoiceHeaders> getAllInvoiceByInitiator(Long initiatorId) throws ServiceException {
		return ltInvoiceHeadersDao.getAllInvoiceByInitiator(initiatorId);
	}

	@Override
	public LtInvoiceHeaders getInvoiceById(Long id) throws ServiceException {
		return ltInvoiceHeadersDao.getInvoiceById(id);
	}
	
	@Override
	public Status save(LtInvoiceHeaders ltInvoiceHeaders) throws ServiceException{
		Status status=new Status();
		String chknull=checkNull(ltInvoiceHeaders);
		if(chknull.equals("null"))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		else
		{
			String chkDuplicate= checkDuplicate(ltInvoiceHeaders,"save");
			if(chkDuplicate.equals("null"))
			{
				ltInvoiceHeaders.setCreatedBy(ltInvoiceHeaders.getLastUpdateLogin());
				ltInvoiceHeaders.setLastUpdatedBy(ltInvoiceHeaders.getLastUpdateLogin());
				ltInvoiceHeaders.setCreationDate(new Date());
				ltInvoiceHeaders.setLastUpdateDate(new Date());
				Long invoiceHeaderId=ltInvoiceHeadersDao.save(ltInvoiceHeaders);
				if(invoiceHeaderId!=null)
				{
					//ltInvoiceHeaders.setInternalInvoiceNumber("IN||"+invoiceHeaderId);
					ltInvoiceHeaders.setInvoiceHeaderId(invoiceHeaderId);
					ltInvoiceHeadersDao.save(ltInvoiceHeaders);   
					
					if(ltInvoiceHeaders.getPoHeaderId()!=null) {
						//ltInvoiceHeadersDao.loadLines(ltInvoiceHeaders);
					}
					//if(!ltVendorApprovalDao.chkForApprovers(invoiceHeaderId)) {
					if(!ltInvoiceHeadersDao.loadApprovers(ltInvoiceHeaders)){
					
//						status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if( status.getMessage()==null)
						{
							status.setCode(0);
							status.setMessage("Error in finding message! The action is completed unsuccessfully.");
						}
						return status;
					 }
					//}
					
//					status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if( status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action is completed successfully.");
					}
					status.setData(invoiceHeaderId);
				}
				else
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("Error in finding message! The action was unsuccessful");
					}
				}
			}
			else {
				status.setCode(0);
				status.setMessage(chkDuplicate);
			}
			
		}
		
		return status;
	}

	private String checkDuplicate(LtInvoiceHeaders ltInvoiceHeaders, String flag) throws ServiceException
	{
		LtInvoiceHeaders invoiceHeaders = ltInvoiceHeadersDao.getByInvNumVendAndAddr(ltInvoiceHeaders.getInvoiceNum(),ltInvoiceHeaders.getVendorId(),
				ltInvoiceHeaders.getVendorAddId());
		if(invoiceHeaders!=null) {
			if(!invoiceHeaders.getInvoiceHeaderId().equals(ltInvoiceHeaders.getInvoiceHeaderId()) 
					&& !invoiceHeaders.getInvoiceHeaderId().equals(ltInvoiceHeaders.getInvoiceHeaderId())) {
				return "Invoice number already exists.";	
			}else {
				return "null";
			}
		}			
		else
			return "null";
	}

	private String checkNull(LtInvoiceHeaders ltInvoiceHeaders)
	{
		if(ltInvoiceHeaders.getCreatedBy()==null || ltInvoiceHeaders.getCreationDate()==null || 
				ltInvoiceHeaders.getLastUpdateLogin()==null)
		{
			return "null";
		}
		else
			return "notnull";
	}

	@Override
	public Status update(LtInvoiceHeaders ltInvoiceHeaders) throws ServiceException{
		Status status=new Status();
		if(ltInvoiceHeaders.getInvoiceHeaderId()!=null)
		{
			String chknull=checkNull(ltInvoiceHeaders);
			if(chknull.equals("null"))
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
			}
			else
			{
				String chkDuplicate= checkDuplicate(ltInvoiceHeaders,"update");
				if(chkDuplicate.equals("null"))
				{
					ltInvoiceHeaders.setLastUpdatedBy(ltInvoiceHeaders.getLastUpdateLogin());
					ltInvoiceHeaders.setCreationDate(new Date());
					ltInvoiceHeaders.setLastUpdateDate(new Date());
					if(ltInvoiceHeadersDao.update(ltInvoiceHeaders))
					{
						if(!ltInvoiceHeadersDao.loadApprovers(ltInvoiceHeaders)){
							
//							status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
							try {
								status.setCode(0);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if( status.getMessage()==null)
							{
								status.setCode(0);
								status.setMessage("Error in finding message! The action is completed unsuccessfully.");
							}
							return status;
						 }
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if( status.getMessage()==null)
						{
						status.setCode(1);
						status.setMessage("Error in finding message! The action is completed successfully.");
						}
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if(status.getMessage()==null)
						{
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}
					}
				}
				else {
					status.setCode(0);
					status.setMessage(chkDuplicate);
				}
				
			}	
		
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		
	
	return status;
	}

	@Override
	public Status delete(Long invoiceHeaderId) throws ServiceException {
		Status status = new Status();
		List<LtInvoiceLines> list = ltInvoiceLinesDao.getAllInvoiceLinesByHeaderId(invoiceHeaderId);
		if(list.isEmpty()) {
			if(ltInvoiceHeadersDao.delete(invoiceHeaderId)) {
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
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
				if( status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
			}
		}else{
			if(ltInvoiceLinesDao.deleteByHeaderId(invoiceHeaderId)) {
				if(ltInvoiceHeadersDao.delete(invoiceHeaderId)) {
//					status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if( status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action is completed successfully.");
					}
				}else {
//					status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if( status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("Error in finding message! The action is completed successfully.");
					}
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
				if( status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
			}
		}
		return status;
	}

	@Override
	public String checkforApprovals(Long invoiceHeaderId) throws ServiceException {
		return ltInvoiceHeadersDao.checkforApprovals(invoiceHeaderId);
	}

	@Override
	public Status submitForApproval(Date date, Long invoiceHeaderId, String statusStr, Object object,Long userId, Long companyId)
			throws ServiceException {
		Status status = new Status();
		LtInvoiceHeaders invoiceHeaders = ltInvoiceHeadersDao.getInvoiceById(invoiceHeaderId);
		System.out.println("invoiceHeaders = "+invoiceHeaders);
		status = checkInvoiceMandatory(invoiceHeaders);
		System.out.println("in submit for approval = "+status);
		if(status.getCode() !=null) {
			return status;
		}
		
		if(invoiceHeaders.getStatus().equals("INVOICE_ACTIVE"))
		{
			ltInvoiceHeadersDao.upDateStatus(invoiceHeaderId, NO_ACTION, null);
		}
		if(invoiceHeaders.getInvoiceAmount()== null || invoiceHeaders.getInvoiceAmount()<=0) {
			status.setCode(0);
			status.setMessage("Invoice with 0 amount can not be sent for approval.");
		}
		
		
		if(!ltInvoiceHeadersDao.chkForApprovers(invoiceHeaderId)) {
			System.out.println(!ltInvoiceHeadersDao.chkForApprovers(invoiceHeaderId));
		if(!ltMastVendorsDao.loadInvoiceApprovers(invoiceHeaders)){
		System.out.println(!ltMastVendorsDao.loadInvoiceApprovers(invoiceHeaders));
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
		try {
			status.setCode(0);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			if( status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		
		 }
		}
		//----------------------------call proc here --------------------
		status = ltInvoiceHeadersDao.callInvoiceValidationProc(invoiceHeaderId);
		System.out.println("prcedure status code = "+status.getCode());
		if(status.getCode().equals(1)) {
			if(ltInvoiceHeadersDao.submitForApproval(date,invoiceHeaderId,statusStr,object)) {
//				status=ltMastCommonMessageService.getCodeAndMessage(INVOICE_SUBMIT_FOR_APPROVAL);
//				Status updatePoShipmentQuantitiesProcstatus = ltInvoiceHeadersDao.callUpdatePoShipmentQuantitiesProc(companyId, userId, invoiceHeaderId);
//				if(updatePoShipmentQuantitiesProcstatus.getCode().equals(1)) {
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INVOICE_SUBMIT_FOR_APPROVAL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("in if procedure");
				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
//			}else {
//				try {
//					status.setCode(0);
//					status.setMessage(updatePoShipmentQuantitiesProcstatus.getMessage());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				if( status.getMessage()==null)
//				{
//					status.setCode(0);
//					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
//				} 
//			}
			}
			else {
				System.out.println("in else procedure");
//				status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if( status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				} 
			}
		}
		return status;
	}

	private Status checkInvoiceMandatory(LtInvoiceHeaders invoiceHeaders) {
		Status status = new Status();
		if(invoiceHeaders.getInvoiceNum()==null) {
			status.setCode(0);
			status.setMessage("Please insert Invoice Number");
		}else if(invoiceHeaders.getInvoiceDate() == null) {
			status.setCode(0);
			status.setMessage("Please insert Invoice Date");
		}else if(invoiceHeaders.getBuyerId() == null) {
			status.setCode(0);
			status.setMessage("Please select Buyer");
		}else if(invoiceHeaders.getVendorAddId() == null) {
			status.setCode(0);
			status.setMessage("Please select Vendor Address");
		}else if(invoiceHeaders.getBillingAddId() == null) {
			status.setCode(0);
			status.setMessage("Please select Billing Address");
		}else if(invoiceHeaders.getDescription() == null) {
			status.setCode(0);
			status.setMessage("Please insert Description");
		}
		return status;
	}

	@Override
	public LtInvoiceHeaders getInvoiceStatusById(Long invoiceHeaderId) throws ServiceException {
		return ltInvoiceHeadersDao.getInvoiceStatusById(invoiceHeaderId);
	}

	@Override
	public boolean checkStatusIsPending(Long invoiceHeaderId, Long approvalId) throws ServiceException {
		return ltInvoiceHeadersDao.checkStatusIsPending(invoiceHeaderId, approvalId);
	}

	@Override
	public List<DashboardDetails> getInvoiceAmtByUserId(Long userId) throws ServiceException {
		return ltInvoiceHeadersDao.getInvoiceAmtByUserId(userId);
	}

	@Override
	public Long getLtInvoiceHeadersCountById(LtInvoiceHeaders input, Long id) throws ServiceException {
		String role  = ltMastEmployeesDao.getRoleByEmpId(id);
		if(!role.toUpperCase().equals("ADMIN")) {
			input.setBuyerId(id);
			
		}
		String companyId  = ltMastEmployeesDao.getCompanyIDByEmpId(id);
		return ltInvoiceHeadersDao.getLtInvoiceHeadersCount(input,Long.parseLong(companyId));
	}

	@Override
	public List<LtInvoiceHeaders> getLtInvoiceHeadersDataTableById(LtInvoiceHeaders input, Long id)
			throws ServiceException {
		System.out.println("input.sort = "+input.getSort());
		String role  = ltMastEmployeesDao.getRoleByEmpId(id);
		if(!role.toUpperCase().equals("ADMIN")) {
			input.setBuyerId(id);
		}
		
		if(input.getColumnNo()==1 && input.getSort().equals("asc"))
		{
			input.setColumnNo(11);
		}
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
		if(input.getColumnNo()==6 && input.getSort().equals("asc"))
		{
			input.setColumnNo(16);
		}
		if(input.getColumnNo()==7 && input.getSort().equals("asc"))
		{
			input.setColumnNo(17);
		}
		if(input.getColumnNo()==8 && input.getSort().equals("asc"))
		{
			input.setColumnNo(18);
		}
		if(input.getColumnNo()==9 && input.getSort().equals("asc"))
		{
			input.setColumnNo(19);
		}
		if(input.getColumnNo()==10 && input.getSort().equals("asc"))
		{
			input.setColumnNo(20);
		}
		
		String companyId  = ltMastEmployeesDao.getCompanyIDByEmpId(id);
		return ltInvoiceHeadersDao.getLtInvoiceHeadersDataTable(input,Long.parseLong(companyId));
	}

	@Override
	public List<LtInvoiceHeaders> getAllInvoice(Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Status save1(LtInvoiceHeaders ltInvoiceHeaders) throws ServiceException {
	    Status status = new Status();
	    String chknull = checkNull(ltInvoiceHeaders);

	    if ("null".equals(chknull)) {
	        try {
	            status.setCode(0);
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
	        } catch (Exception e) {
	            e.printStackTrace();
	            status.setCode(0);
	            status.setMessage("Error in finding message! The action was unsuccessful");
	        }
	        return status;
	    }

	    try {
	        // Call stored procedure to check for duplicate invoices
	        ProcedureCall procedureCall = ltInvoiceHeadersDao.checkDuplicateInvoice(
	            ltInvoiceHeaders.getCompanyId(),
	            ltInvoiceHeaders.getInvoiceNum(),
	            ltInvoiceHeaders.getVendorId(),
	            ltInvoiceHeaders.getVendorAddId(),
	            ltInvoiceHeaders.getInvoiceDate()
	        );

	        // Set data from procedure call
	        status.setData(procedureCall);

	        if ("ERROR".equalsIgnoreCase(procedureCall.getStatusCode())) {
	            status.setCode(0);
	            status.setMessage("Duplicate invoice found: " + procedureCall.getStatusMessage());
	            return status;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        status.setCode(0);
	        status.setMessage("Error checking duplicate invoice: " + e.getMessage());
	        return status;
	    }

	    // Set metadata for new invoice
	    ltInvoiceHeaders.setCreatedBy(ltInvoiceHeaders.getLastUpdateLogin());
	    ltInvoiceHeaders.setLastUpdatedBy(ltInvoiceHeaders.getLastUpdateLogin());
	    ltInvoiceHeaders.setCreationDate(new Date());
	    ltInvoiceHeaders.setLastUpdateDate(new Date());

	    Long invoiceHeaderId = ltInvoiceHeadersDao.save(ltInvoiceHeaders);

	    if (invoiceHeaderId != null) {
	        ltInvoiceHeaders.setInvoiceHeaderId(invoiceHeaderId);
	        ltInvoiceHeadersDao.save(ltInvoiceHeaders);

	        if (ltInvoiceHeaders.getPoHeaderId() != null) {
	            // ltInvoiceHeadersDao.loadLines(ltInvoiceHeaders);
	        }

	        if (!ltInvoiceHeadersDao.loadApprovers(ltInvoiceHeaders)) {
	            try {
	                status.setCode(0);
	                status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
	            } catch (Exception e) {
	                e.printStackTrace();
	                status.setCode(0);
	                status.setMessage("Error in finding message! The action was unsuccessful.");
	            }
	            return status;
	        }

	        try {
	            status.setCode(1);
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
	        } catch (Exception e) {
	            e.printStackTrace();
	            status.setCode(1);
	            status.setMessage("Error in finding message! The action was completed successfully.");
	        }

	        // Set inserted invoice header ID as data
	        status.setData(invoiceHeaderId);
	    } else {
	        try {
	            status.setCode(0);
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
	        } catch (Exception e) {
	            e.printStackTrace();
	            status.setCode(0);
	            status.setMessage("Error in finding message! The action was unsuccessful");
	        }
	    }

	    return status;
	}

	
	
}
