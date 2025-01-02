package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastVendorsDao;
import com.lonar.vendor.vendorportal.dao.LtRentalAgreementHeadersDao;
import com.lonar.vendor.vendorportal.dao.LtRentalAgreementLinesDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtInvoiceLines;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtRentalAgreementHeadersServiceImpl implements LtRentalAgreementHeadersService,CodeMaster {
	
	@Autowired
	LtRentalAgreementHeadersDao ltRentalAgreementHeadersDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtRentalAgreementLinesDao ltRentalAgreementLinesDao;
	
	@Autowired
	LtMastVendorsDao ltMastVendorsDao;

	@Override
	public List<LtRentalAgreementHeaders> getLtRentalAgreementHeadersDataTable(LtRentalAgreementHeaders input,Long companyId) throws ServiceException {
		// TODO Auto-generated method stub 
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
		return ltRentalAgreementHeadersDao.getLtRentalAgreementHeadersDataTable(input,companyId);
	}

	@Override
	public Long getLtRentalAgreementHeadersCount(LtRentalAgreementHeaders input,Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltRentalAgreementHeadersDao.getLtRentalAgreementHeadersCount(input,companyId);
	}

	@Override
	public Status save(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException {
		// TODO Auto-generated method stub
		Status status=new Status();
		String chknull=checkNull(ltRentalAgreementHeaders);
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
		}else {
			ltRentalAgreementHeaders.setCreatedBy(ltRentalAgreementHeaders.getLastUpdateLogin());
			ltRentalAgreementHeaders.setLastUpdatedBy(ltRentalAgreementHeaders.getLastUpdateLogin());
			ltRentalAgreementHeaders.setCreationDate(new Date());
			ltRentalAgreementHeaders.setLastUpdateDate(new Date());
			Long invoiceHeaderId=ltRentalAgreementHeadersDao.save(ltRentalAgreementHeaders);
			
			if(invoiceHeaderId!=null) {
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
			}else {
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
		return status;
	}
	
	private String checkNull(LtRentalAgreementHeaders ltRentalAgreementHeaders)
	{
		if(ltRentalAgreementHeaders.getCreatedBy()==null || ltRentalAgreementHeaders.getCreationDate()==null || 
				ltRentalAgreementHeaders.getLastUpdateLogin()==null)
		{
			return "null";
		}
		else
			return "notnull";
	}

	@Override
	public LtRentalAgreementHeaders getRentalAgreemenById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		System.out.println("id = "+id);
		return ltRentalAgreementHeadersDao.getRentalAgreementById(id);
	}

	@Override
	public Status update(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException {
//		System.out.println("ltRentalAgreementHeaders = "+ltRentalAgreementHeaders);
		Status status=new Status();
		if(ltRentalAgreementHeaders.getAgreementHeaderId()!=null)
		{
			String chknull=checkNull(ltRentalAgreementHeaders);
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
			}else {
				ltRentalAgreementHeaders.setLastUpdatedBy(ltRentalAgreementHeaders.getLastUpdateLogin());
				ltRentalAgreementHeaders.setCreationDate(new Date());
				ltRentalAgreementHeaders.setLastUpdateDate(new Date());
				
				if(ltRentalAgreementHeadersDao.update(ltRentalAgreementHeaders)) {

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
					status.setData(ltRentalAgreementHeaders.getAgreementHeaderId());
				}else {
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
		}
		return status;
	}

	@Override
	public LtRentalAgreementHeaders getRentalAgreementStatusById(Long id) throws ServiceException {
		// TODO Auto-generated method stub	
			return ltRentalAgreementHeadersDao.getRentalAgreementStatusById(id);
	}

	@Override
	public Status delete(Long agreementHeaderId) throws ServiceException {
		Status status = new Status();
		List<LtRentalAgreementLines> list = ltRentalAgreementLinesDao.getAllRentalAgreementLinesByHeaderId(agreementHeaderId);
		if(list.isEmpty()) {
			if(ltRentalAgreementHeadersDao.delete(agreementHeaderId)) {
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
			if(ltRentalAgreementLinesDao.deleteByAgreementHeaderId(agreementHeaderId)) {
				if(ltRentalAgreementHeadersDao.delete(agreementHeaderId)) {
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
	public boolean checkStatusIsPending(Long agreementHeaderId, Long approvalId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltRentalAgreementHeadersDao.checkStatusIsPending(agreementHeaderId, approvalId);
	}

	@Override
	public String checkforApprovals(Long agreementHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltRentalAgreementHeadersDao.checkforApprovals(agreementHeaderId);
	}

	@Override
	public Status submitForApproval(Date date, Long agreementHeaderId, String statusStr, Object object, Long userId,
			Long companyId) throws ServiceException {
		System.out.println("in submit for approval method");
		// TODO Auto-generated method stub
		
		Status status = new Status();

		LtRentalAgreementHeaders ltRentalAgreementHeaders = ltRentalAgreementHeadersDao.getRentalAgreementById(agreementHeaderId);
		System.out.println("ltRentalAgreementHeaders = "+ltRentalAgreementHeaders);
		if(ltRentalAgreementHeaders.getStatus().equals("ACTIVE"))
		{
			ltRentalAgreementHeadersDao.upDateStatus(agreementHeaderId, NO_ACTION, null);
			System.out.println("in first if submit for approval method");
		}
		if(!ltRentalAgreementHeadersDao.chkForApprovers(agreementHeaderId)) {
			System.out.println("in second if submit for approval method");
			System.out.println(!ltRentalAgreementHeadersDao.chkForApprovers(agreementHeaderId));
			if(!ltMastVendorsDao.loadRentalAgreementApprovers(ltRentalAgreementHeaders)){
				System.out.println(!ltMastVendorsDao.loadRentalAgreementApprovers(ltRentalAgreementHeaders));
				
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
		
	if(ltRentalAgreementHeadersDao.submitForApproval(date,agreementHeaderId,statusStr,object)) {
		try {
			status.setCode(1);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("AGREEMENT_SUBMIT_FOR_APPROVAL").getMessageName());
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
		return status;
	}

}
