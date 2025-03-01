package com.lonar.vendor.vendorportal.service;

import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPoApprovalService {

	PoApproval getPoApproval(Long poId, Long apprId) throws ServiceException;

	Status updatePoStatusApproval(LtPoApprovalHistory approvalHistory) throws ServiceException;

}
