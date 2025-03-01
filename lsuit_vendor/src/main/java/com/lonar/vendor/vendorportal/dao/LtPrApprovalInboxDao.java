package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;

public interface LtPrApprovalInboxDao {
	
	List<PrApproval> getByStatus(String status,String approvalId,PrApproval input) throws Exception;


}
