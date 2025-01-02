package com.lonar.vendor.vendorportal.dao;

import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtRentalAgreementApprovalHistoryDao {
	
	boolean save(LtRentalAgrApprovalHistory ltRentalAgrApprovalHistory) throws ServiceException;



}
