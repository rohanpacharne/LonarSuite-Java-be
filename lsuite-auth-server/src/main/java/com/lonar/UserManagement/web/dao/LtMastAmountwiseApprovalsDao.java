package com.lonar.UserManagement.web.dao;

import java.util.List;

import com.lonar.UserManagement.web.model.LtMastAmountwiseApprovals;

public interface LtMastAmountwiseApprovalsDao {

	Long getLtMastUsersCount(LtMastAmountwiseApprovals input, Long companyId);

	List<LtMastAmountwiseApprovals> getLtMastUsersDatatableRecords(LtMastAmountwiseApprovals input, Long companyId);

	List<LtMastAmountwiseApprovals> findByTransactionCode(String transactionCode, Long companyId);

	boolean checkForDuplicate(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	boolean update(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	int delete(Long amtApprovalId);

	LtMastAmountwiseApprovals save(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	LtMastAmountwiseApprovals getByAmtApprovalId(Long amtApprovalId);

}
