package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtPoInboxDao;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
@Service
public class LtPoInboxServiceImpl implements LtPoInboxService{

@Autowired 
LtPoInboxDao ltPoInboxDao;
	
@Override
	public Long getPoCount(String status1, String approvalId, PoApproval input) throws ServiceException {
		return ltPoInboxDao.getPoCount(status1,approvalId,input);
	}

	@Override
	public List<PoApproval> getPoByStatus(String status1, String approvalId, PoApproval input) throws ServiceException {
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
		if(input.getColumnNo()==5 && input.getSort().equals("asc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==0 || input.getColumnNo()==1)
		{
			input.setColumnNo(4);
		}
		List<PoApproval> list= ltPoInboxDao.getPoByStatus(status1,approvalId,input);
		System.out.println("PoApproval list = "+list);
		
		for(PoApproval obj : list) {
			if(obj.getStatus()==null) {
				obj.setStatus("");
			}
		}
		
		for(PoApproval obj : list) {
			if(obj.getStatus().toUpperCase().equals("PENDING")) {
				obj.setAction("Approve/Reject");
			}else {
				obj.setAction("View");
			}
		}
		return list;
	}
}
