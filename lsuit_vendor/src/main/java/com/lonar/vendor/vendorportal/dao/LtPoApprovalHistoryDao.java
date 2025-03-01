package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPoApprovalHistoryDao {

	boolean save(LtPoApprovalHistory poApprovalHistory) throws ServiceException;

	List<LtPoApprovalHistory> getPoApprovalHistoryByPoHeaderId(Long poHeaderId);

}
