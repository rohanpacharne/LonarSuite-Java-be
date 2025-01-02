package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtRentalAgreementApprovalInboxDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;

@Service
public class LtRentalAgreementApprovalInboxServiceImpl implements LtRentalAgreementApprovalInboxService,CodeMaster{
	
	@Autowired
	LtRentalAgreementApprovalInboxDao ltRentalAgreementApprovalInboxDao;

	@Override
	public Long getCount(String status1, String approvalId, RentalAgreementApproval input) throws Exception {
		// TODO Auto-generated method stub
		return ltRentalAgreementApprovalInboxDao.getCount(status1, approvalId, input);
	}

	@Override
	public List<RentalAgreementApproval> getByStatus(String status, String approvalId, RentalAgreementApproval input)
			throws Exception {
		List<RentalAgreementApproval> list = ltRentalAgreementApprovalInboxDao.getByStatus(status, approvalId, input);
		
		// TODO Auto-generated method stub
		for(RentalAgreementApproval obj : list) {
			if(obj.getStatus()==null) {
				obj.setStatus("");
			}
		}
		
		for(RentalAgreementApproval obj : list) {
			if(obj.getStatus().toUpperCase().equals("PENDING")) {
				obj.setAction("Approve/Reject");
			}else {
				obj.setAction("View");
			}
		}
		return list;
		
		
	}

}
