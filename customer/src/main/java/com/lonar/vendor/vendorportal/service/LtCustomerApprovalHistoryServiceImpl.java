package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtCustomerApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtCustomerApprovalHistoryDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtCustomerApprovalHistoryServiceImpl implements LtCustomerApprovalHistoryService,CodeMaster{

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtCustomerApprovalDao ltVendorApprovalDao;
	
	@Autowired
	LtCustomerApprovalHistoryDao ltVendorApprovalHistoryDao;
	
	@Override
	public void saveApprovalHistory(LtCustomerApprovalHistory ltCustomerApprovalHistory) throws ServiceException 
	{
		Status status=new Status();
		ltCustomerApprovalHistory.setLastUpdateDate(new Date());
		List<LtCustomerApproval> ltExpenseApprovalList =ltVendorApprovalDao.chkEmpApproval(ltCustomerApprovalHistory.getEmployeeId()
				,ltCustomerApprovalHistory.getCustomerId());
		if(ltExpenseApprovalList.size()>0)
		{
			ltCustomerApprovalHistory.setStatus(APPROVED);
		}
		
		
		if (ltVendorApprovalHistoryDao.save(ltCustomerApprovalHistory))
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
	public List<LtCustomerApprovalHistory> getCustomerApprovalHistoryByCustomerId(Long vendorId) throws ServiceException {
		return ltVendorApprovalHistoryDao.getCustomerApprovalHistoryByCustomerId(vendorId);
	}

	@Override
	public ResponseEntity<Status> save(LtCustomerApprovalHistory ltVendorApprovalHistory) throws ServiceException {
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

	

}
