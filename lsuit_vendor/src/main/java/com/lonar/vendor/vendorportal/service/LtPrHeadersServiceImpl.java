package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastVendorsDao;
import com.lonar.vendor.vendorportal.dao.LtPrHeadersDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtPrHeadersServiceImpl implements LtPrHeadersService,CodeMaster{
	
	@Autowired
	LtPrHeadersDao ltPrHeadersDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastVendorsDao ltMastVendorsDao;

	@Override
	public List<LtPrHeaders> getLtPrHeadersDataTable(LtPrHeaders input, Long companyId) throws ServiceException {
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
		if(input.getColumnNo()==9 && input.getSort().equals("asc"))
		{
			input.setColumnNo(19);
		}
		if(input.getColumnNo()==10 && input.getSort().equals("asc"))
		{
			input.setColumnNo(20);
		}
		
		return ltPrHeadersDao.getLtPrHeadersDataTable(input, companyId);
	}

	@Override
	public Status save(LtPrHeaders input) throws ServiceException {
		// TODO Auto-generated method stub
		Status status = new Status();
		if(ltPrHeadersDao.save(input)!=null) {
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
			status.setData(ltPrHeadersDao.save(input));
		}else {
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
			status.setData(ltPrHeadersDao.save(input));
		}
		
		return status;
	}

	@Override
	public Long getLtPrHeadersDataTableCount(LtPrHeaders input, Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltPrHeadersDao.getLtPrHeadersDataTableCount(input, companyId);
	}

	@Override
	public Status delete(Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		Status status = new Status();
		if(ltPrHeadersDao.delete(prHeaderId)) {
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
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
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
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
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		
		return status;
	}

	@Override
	public LtPrHeaders getPrById(Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltPrHeadersDao.getPrHeaderById(prHeaderId);
	}

	@Override
	public Status update(LtPrHeaders ltPrHeaders) throws ServiceException {
		// TODO Auto-generated method stub
		Status status = new Status();
		if(ltPrHeadersDao.update(ltPrHeaders)) {
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
		}else {
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null)
			{
			status.setCode(1);
			status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		return status;
	}

	@Override
	public LtPrHeaders getPrStatusById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return ltPrHeadersDao.getPrStatusById(id);
	}

	@Override
	public boolean checkStatusIsPending(Long prHeaderId, Long approvalId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltPrHeadersDao.checkStatusIsPending(prHeaderId, approvalId);
	}

	@Override
	public String checkforApprovals(Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltPrHeadersDao.checkforApprovals(prHeaderId);
	}
	
	@Override
	public Status submitForApproval(Date date, Long prHeaderId, String statusStr, Object object, Long userId,
			Long companyId) throws ServiceException {
		System.out.println("in submit for approval method");
		// TODO Auto-generated method stub
		
		Status status = new Status();

		LtPrHeaders ltPrHeaders = ltPrHeadersDao.getPrHeaderById(prHeaderId);
		System.out.println("ltPrHeaders = "+ltPrHeaders);
		if(ltPrHeaders.getStatus().equals("ACTIVE"))
		{
			ltPrHeadersDao.upDateStatus(prHeaderId, NO_ACTION, null);
			System.out.println("in first if submit for approval method");
		}
		if(!ltPrHeadersDao.chkForApprovers(prHeaderId)) {
			System.out.println("in second if submit for approval method");
			System.out.println(!ltPrHeadersDao.chkForApprovers(prHeaderId));
			if(!ltMastVendorsDao.loadPrApprovers(ltPrHeaders)){
				System.out.println(!ltMastVendorsDao.loadPrApprovers(ltPrHeaders));
				
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
		
	if(ltPrHeadersDao.submitForApproval(date,prHeaderId,statusStr,object)) {
		try {
			status.setCode(1);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PR_SUBMIT_FOR_APPROVAL").getMessageName());
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
