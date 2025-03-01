package com.lonar.UserManagement.web.service;

import com.lonar.UserManagement.web.model.CustomeDataTable;
import com.lonar.UserManagement.web.model.LtMastAmountwiseApprovals;
import com.lonar.UserManagement.web.model.Status;

public interface LtMastAmountwiseApprovalsService {

	CustomeDataTable getLtMastAmountwiseApprovalsDataTable(Long companyId, String transactionCode, String transactionType, LtMastAmountwiseApprovals input);
	
	Status save(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) throws Exception;

	Status save1(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) throws Exception;

	Status update(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	Status delete(Long amtApprovalId);

	LtMastAmountwiseApprovals getByAmtApprovalId(Long amtApprovalId);

	

}
