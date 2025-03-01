package com.lonar.vendor.vendorportal.dao;

import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPrApprovalHistoryDao {
	
	boolean save(LtPrApprovalHistory approvalHistory) throws ServiceException;


}
