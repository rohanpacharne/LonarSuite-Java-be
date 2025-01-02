package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtInvoiceApprovalHistoryDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtInvoiceApprovalHistoryServiceImpl implements LtInvoiceApprovalHistoryService,CodeMaster{

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtInvoiceApprovalHistoryDao ltInvoiceApprovalHistoryDao;
	
	@Override
	public ResponseEntity<Status> save(LtInvoiceApprovalHistory ltInvoiceApprovalHistory) throws ServiceException {
		Status status = new Status();
		ltInvoiceApprovalHistory.setLastUpdateDate(new Date());
		if (ltInvoiceApprovalHistoryDao.save(ltInvoiceApprovalHistory)) 
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
	public List<LtInvoiceApprovalHistory> getInvoiceApprovalHistoryByInvoiceId(Long invoiceHeaderId)
			throws ServiceException {
		return ltInvoiceApprovalHistoryDao.getInvoiceApprovalHistoryByInvoiceHeaderId(invoiceHeaderId);
	}

}
