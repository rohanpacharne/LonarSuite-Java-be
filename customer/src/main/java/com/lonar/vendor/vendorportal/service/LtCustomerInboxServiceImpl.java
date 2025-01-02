package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtCustomerInboxDao;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Service
public class LtCustomerInboxServiceImpl implements LtCustomerInboxService{

	@Autowired
	LtCustomerInboxDao ltCustomerInboxDao;
	
	@Override
	public Long getCount(String status1, String approvalId, LtCustomerApproval input) throws ServiceException 
	{
		return ltCustomerInboxDao.getCount(status1,approvalId,input);
	}

	@Override
	public List<LtCustomerApproval> getByStatus(String status1, String approvalId, LtCustomerApproval input)
			throws ServiceException 
	{
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
		if(input.getColumnNo()==0 || input.getColumnNo()==1)
		{
			input.setColumnNo(4);
		}
		List<LtCustomerApproval> list= ltCustomerInboxDao.getByStatus(status1,approvalId,input);
		for(LtCustomerApproval obj : list) {
			if(obj.getStatusValue().toUpperCase().equals("PENDING")) {
				obj.setAction("Approve/Reject");
			}else {
				obj.setAction("View");
			}
		}
		return list;
	}

	@Override
	public List<LtCustomerApproval> getVendorApprovalNotification(String status, Long empId) throws ServiceException {
		return ltCustomerInboxDao.getVendorApprovalNotification(status, empId);
	}

	
}
