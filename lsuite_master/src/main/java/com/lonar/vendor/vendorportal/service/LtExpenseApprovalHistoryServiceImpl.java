package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.dao.LtExpenseApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtExpenseApprovalHistoryDao;
import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtExpenseApprovalHistoryServiceImpl implements LtExpenseApprovalHistoryService {
	
	@Autowired
	LtExpenseApprovalHistoryDao ltExpenseApprovalHistoryDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtExpenseApprovalDao ltExpenseApprovalDao;

	@Transactional
	@Override
	public List<LtExpenseApprovalHistory> getExpenseApprovalHistoryByExpHeaderId(Long expenseHeaderId) throws Exception {
		
		//List<LtExpenseApprovalHistory> ltExpenseApprovalHistoryList = new ArrayList<LtExpenseApprovalHistory>();
 
		List<LtExpenseApprovalHistory> ltExpenseApprovalHistoryList =
				ltExpenseApprovalHistoryDao.getExpenseApprovalHistoryByExpHeaderId(expenseHeaderId);
		
		return ltExpenseApprovalHistoryList;
	}
	
	@Transactional
	@Override
	public void saveApprovalHistory(LtExpenseApprovalHistory ltExpenseApprovalHistory) throws Exception 
	{
		Status status=new Status();
		ltExpenseApprovalHistory.setLastUpdateDate(new Date());
		List<LtExpenseApproval> ltExpenseApprovalList =ltExpenseApprovalDao.chkEmpApproval(ltExpenseApprovalHistory.getEmployeeId()
				,ltExpenseApprovalHistory.getExpenseHeaderId());
		if(ltExpenseApprovalList.size()>0)
		{
			ltExpenseApprovalHistory.setStatus(APPROVED);
		}
		
		
		if (ltExpenseApprovalHistoryDao.save(ltExpenseApprovalHistory))
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
