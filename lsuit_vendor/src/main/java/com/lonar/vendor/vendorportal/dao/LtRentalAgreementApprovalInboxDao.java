package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;

public interface LtRentalAgreementApprovalInboxDao {
	
	Long getCount(String status1, String approvalId, RentalAgreementApproval input) throws Exception;
	
	List<RentalAgreementApproval> getByStatus(String status,String approvalId,RentalAgreementApproval input) throws Exception;

}
