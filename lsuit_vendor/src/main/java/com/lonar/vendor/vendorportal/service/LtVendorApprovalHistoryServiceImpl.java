package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtInvoiceApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtInvoiceApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtPoApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtPoApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtPrApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtPrApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtRentalAgreementApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtRentalAgreementApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtVendorApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtVendorApprovalHistoryDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.LtVendorApproval;
import com.lonar.vendor.vendorportal.model.LtVendorApprovalHistory;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtVendorApprovalHistoryServiceImpl implements LtVendorApprovalHistoryService,CodeMaster{

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtVendorApprovalDao ltVendorApprovalDao;
	
	@Autowired
	LtVendorApprovalHistoryDao ltVendorApprovalHistoryDao;
	
	@Autowired
	LtInvoiceApprovalDao invoiceApprovalDao;
	
	@Autowired
	LtRentalAgreementApprovalDao ltRentalAgreementApprovalDao;
	
	@Autowired
	LtInvoiceApprovalHistoryDao ltInvoiceApprovalHistoryDao;
	
	@Autowired
	LtRentalAgreementApprovalHistoryDao ltRentalAgreementApprovalHistoryDao;
	
	@Autowired
	LtPrApprovalDao ltPrApprovalDao;
	
	@Autowired
	LtPrApprovalHistoryDao ltPrApprovalHistoryDao;
	
	@Autowired
	LtPoApprovalDao ltPoApprovalDao;
	
	@Autowired
	LtPoApprovalHistoryDao ltPoApprovalHistoryDao;
	
	@Override
	public void saveApprovalHistory(LtVendorApprovalHistory ltExpenseApprovalHistory) throws ServiceException 
	{
		Status status=new Status();
		ltExpenseApprovalHistory.setLastUpdateDate(new Date());
		List<LtVendorApproval> ltExpenseApprovalList =ltVendorApprovalDao.chkEmpApproval(ltExpenseApprovalHistory.getEmployeeId()
				,ltExpenseApprovalHistory.getVendorId());
		if(ltExpenseApprovalList.size()>0)
		{
			ltExpenseApprovalHistory.setStatus(APPROVED);
		}
		
		
		if (ltVendorApprovalHistoryDao.save(ltExpenseApprovalHistory))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (status.getMessage() == null) 
			{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		
	}

	@Override
	public List<LtVendorApprovalHistory> getVendorApprovalHistoryByVendorId(Long vendorId) throws ServiceException {
		return ltVendorApprovalHistoryDao.getVendorApprovalHistoryByVendorId(vendorId);
	}

	@Override
	public ResponseEntity<Status> save(LtVendorApprovalHistory ltVendorApprovalHistory) throws ServiceException {
		Status status = new Status();
		ltVendorApprovalHistory.setLastUpdateDate(new Date());
		if (ltVendorApprovalHistoryDao.save(ltVendorApprovalHistory)) 
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(REMARK_SAVED);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("REMARK_SAVED").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (status.getMessage() == null) 
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
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

			if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}

		 return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public void saveInvoiceApprovalHistory(LtInvoiceApprovalHistory ltInvoiceApprovalHistory) throws ServiceException {
		Status status=new Status();
		ltInvoiceApprovalHistory.setLastUpdateDate(new Date());
		List<InvoiceApproval> invoiceApprovalList =invoiceApprovalDao.chkInvoiceEmpApproval(ltInvoiceApprovalHistory.getEmployeeId()
				,ltInvoiceApprovalHistory.getInvoiceHeaderId());
		
		if(invoiceApprovalList.size()>0)
		{
			ltInvoiceApprovalHistory.setStatus(APPROVED);
		}
		
		if (ltInvoiceApprovalHistoryDao.save(ltInvoiceApprovalHistory))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null) 
			{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		
	}

	@Override
	public List<LtInvoiceApprovalHistory> getInvoiceApprovalHistoryByInvoiceId(Long invoiceHeaderId)
			throws ServiceException {
		return ltInvoiceApprovalHistoryDao.getInvoiceApprovalHistoryByInvoiceHeaderId(invoiceHeaderId);
	}

	@Override
	public void saveAgreementApprovalHistory(LtRentalAgrApprovalHistory ltRentalAgrApprovalHistory)
			throws ServiceException {
		
		Status status=new Status();
		
		ltRentalAgrApprovalHistory.setLastUpdateDate(new Date());
		List<LtRentalAgreementApproval> agreementApprovalList =ltRentalAgreementApprovalDao.chkAgreementEmpApproval(ltRentalAgrApprovalHistory.getEmployeeId()
				,ltRentalAgrApprovalHistory.getAgreementHeaderId());
		
		if(agreementApprovalList.size()>0)
		{
			ltRentalAgrApprovalHistory.setStatus(APPROVED);
		}
		
		if (ltRentalAgreementApprovalHistoryDao.save(ltRentalAgrApprovalHistory))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null) 
			{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		
	}

	@Override
	public void savePrApprovalHistory(LtPrApprovalHistory ltPrApprovalHistory) throws ServiceException {
		
		Status status=new Status();
		ltPrApprovalHistory.setLastUpdateDate(new Date());
		List<PrApproval> prApprovalList = ltPrApprovalDao.chkPrEmpApproval(ltPrApprovalHistory.getEmployeeId()
				,ltPrApprovalHistory.getPrHeaderId());
		
		if(prApprovalList.size()>0)
		{
			ltPrApprovalHistory.setStatus(APPROVED);
		}
		
		if (ltPrApprovalHistoryDao.save(ltPrApprovalHistory))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null) 
			{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		
	}
	
	@Override
	public void savePoApprovalHistory(LtPoApprovalHistory ltPoApprovalHistory) throws ServiceException {
		Status status=new Status();
		ltPoApprovalHistory.setLastUpdateDate(new Date());
		List<PoApproval> poApprovalList =ltPoApprovalDao.chkPoEmpApproval(ltPoApprovalHistory.getEmployeeId()
				,ltPoApprovalHistory.getPoHeaderId());
		
		if(poApprovalList.size()>0)
		{
			ltPoApprovalHistory.setStatus(APPROVED);
		}
		
		if (ltPoApprovalHistoryDao.save(ltPoApprovalHistory))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null)
			{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		
	}

}
