package com.lonar.UserManagement.web.dao;

import java.util.List;

import com.lonar.UserManagement.web.model.LtMastAmountwiseApprovals;

public interface LtMastAmountwiseApprovalsDao {

	Long getLtMastUsersCount(Long companyId, String transactionCode2, String transactionCode,
			LtMastAmountwiseApprovals input);

	List<LtMastAmountwiseApprovals> getLtMastUsersDatatableRecords(Long companyId, String transactionCode2,
			String transactionCode, LtMastAmountwiseApprovals input);

	List<LtMastAmountwiseApprovals> findByTransactionCode(String transactionCode, Long companyId);

	boolean checkForDuplicate(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	boolean update(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	int delete(Long amtApprovalId);

	LtMastAmountwiseApprovals save(LtMastAmountwiseApprovals ltMastAmountwiseApprovals);

	LtMastAmountwiseApprovals getByAmtApprovalId(Long amtApprovalId);

}
