package com.lonar.UserManagement.web.service;

import com.lonar.UserManagement.web.model.CustomeDataTable;
import com.lonar.UserManagement.web.model.LtMastAmountwiseApprovals;
import com.lonar.UserManagement.web.model.Status;

public interface LtMastAmountwiseApprovalsService {

	CustomeDataTable getLtMastAmountwiseApprovalsDataTable(LtMastAmountwiseApprovals input, Long companyId);

	Status save(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) throws Exception;

	Status save1(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) throws Exception;

	Status update(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	Status delete(Long amtApprovalId);

	LtMastAmountwiseApprovals getByAmtApprovalId(Long amtApprovalId);

}
