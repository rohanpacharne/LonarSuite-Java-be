package com.lonar.vendor.vendorportal.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtRentalAgreementApprovalHistoryDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtRentalAgreementApprovalHistoryServiceImpl implements LtRentalAgreementApprovalHistoryService,CodeMaster{

	@Autowired
	LtRentalAgreementApprovalHistoryDao ltRentalAgreementApprovalHistoryDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Override
	public ResponseEntity<Status> save(LtRentalAgrApprovalHistory ltRentalAgrApprovalHistory) throws ServiceException {
		Status status = new Status();
		ltRentalAgrApprovalHistory.setLastUpdateDate(new Date());
		if (ltRentalAgreementApprovalHistoryDao.save(ltRentalAgrApprovalHistory)) 
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

}
