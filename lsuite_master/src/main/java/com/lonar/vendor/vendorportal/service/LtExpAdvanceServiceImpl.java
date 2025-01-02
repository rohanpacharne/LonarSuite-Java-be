package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.dao.LtExpAdvanceDao;
import com.lonar.vendor.vendorportal.model.ExpenseApprovalV;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;

@Service
public class LtExpAdvanceServiceImpl implements LtExpAdvanceService{
	
	@Autowired
	LtExpAdvanceDao ltExpAdvanceDao;
	
	@Transactional
	@Override
	public Long getCount(String status1, String approvalId, ExpenseApprovalV input) throws Exception
	{
		return ltExpAdvanceDao.getCount(status1,approvalId,input);
	}
	
	@Transactional
	@Override
	public List<ExpenseApprovalV> getByStatus(String status,String approvalId,ExpenseApprovalV input) throws Exception
	{
		List<ExpenseApprovalV> list= ltExpAdvanceDao.getByStatus(status,approvalId,input);
		
		for(ExpenseApprovalV obj : list) {
			if(obj.getStatus()==null) {
				obj.setStatus("");
			}
		}
		
		for(ExpenseApprovalV obj : list) {
			if(obj.getStatus().toUpperCase().equals("PENDING")) {
				obj.setAction("Approve/Reject");
			}else {
				obj.setAction("View");
			}
		}
		
		return list;
	}

}
