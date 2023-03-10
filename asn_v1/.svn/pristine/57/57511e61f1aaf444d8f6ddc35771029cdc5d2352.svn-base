package com.lonar.asn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.asn.dao.LtShipmentInboxDao;
import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;

@Service
public class LtShipmentInboxServiceImpl implements LtShipmentInboxService{

	@Autowired
	LtShipmentInboxDao ltShipmentInboxDao;
	
	@Override
	public Long getShipmentCount(String status, String approvalId, AsnApproval input) throws BusinessException {
		return ltShipmentInboxDao.getShipmentCount(status,approvalId,input);
	}

	@Override
	public List<AsnApproval> getShipmentByStatus(String status, String approvalId, AsnApproval input)
			throws BusinessException {
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
		List<AsnApproval> list= ltShipmentInboxDao.getShipmentByStatus(status,approvalId,input);
		for(AsnApproval obj : list) {
			if(obj.getStatus().toUpperCase().equals("PENDING")) {
				obj.setAction("Approve/Reject");
			}else {
				obj.setAction("View");
			}
		}
		return list;
	}

}
