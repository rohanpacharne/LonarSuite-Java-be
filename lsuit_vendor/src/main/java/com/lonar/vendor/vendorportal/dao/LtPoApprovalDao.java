package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPoApprovalDao {

	boolean updateStatusApproval(LtPoApprovalHistory approvalHistory) throws ServiceException;

	boolean updateAllStatusApproval(LtPoApprovalHistory approvalHistory) throws ServiceException;

	boolean submitForApproval(Date submissionDate, Long poHeaderId, String status, Date approvedDate, Long lastLogin)
			throws ServiceException;

	String getCurrLevelByPoApprovalId(Long poApprovalId) throws ServiceException;

	boolean upDateStatus(Long poHeaderId, String status, String currentApprovalLevel) throws ServiceException;

	PoApproval getPoApproval(Long poId, Long apprId) throws ServiceException;

	List<PoApproval> chkPoEmpApproval(Long employeeId, Long poId) throws ServiceException;

}
