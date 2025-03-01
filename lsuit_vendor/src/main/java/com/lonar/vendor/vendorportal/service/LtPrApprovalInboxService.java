package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;

public interface LtPrApprovalInboxService {
	
	List<PrApproval> getByStatus(String status,String approvalId,PrApproval input) throws Exception;


}
