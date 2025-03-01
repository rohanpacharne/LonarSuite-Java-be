package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtPrApprovalInboxDao;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;

@Service
public class LtPrApprovalInboxServiceImpl implements LtPrApprovalInboxService {
	
	@Autowired
	LtPrApprovalInboxDao ltPrApprovalInboxDao;

	@Override
	public List<PrApproval> getByStatus(String status, String approvalId, PrApproval input) throws Exception {
		List<PrApproval> list = ltPrApprovalInboxDao.getByStatus(status, approvalId, input);
		
		// TODO Auto-generated method stub
		for(PrApproval obj : list) {
			if(obj.getStatus()==null) {
				obj.setStatus("");
			}
		}
		
		for(PrApproval obj : list) {
			if(obj.getStatus().toUpperCase().equals("PENDING")) {
				obj.setAction("Approve/Reject");
			}else {
				obj.setAction("View");
			}
		}
		return list;
	}

}
