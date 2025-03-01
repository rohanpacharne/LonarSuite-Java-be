package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPrHeadersDao {
	
	List<LtPrHeaders> getLtPrHeadersDataTable(LtPrHeaders input, Long companyId) throws ServiceException;
	
	Integer save(LtPrHeaders ltPrHeaders) throws ServiceException;
	
	Long getLtPrHeadersDataTableCount(LtPrHeaders input, Long companyId) throws ServiceException;
	
	boolean delete(Long prHeaderId) throws ServiceException;
	
	LtPrHeaders getPrHeaderById(Long prHeaderId);
	
	boolean update(LtPrHeaders ltPrHeaders) throws ServiceException;
	
	LtPrHeaders getPrStatusById(Long id)throws ServiceException;
	
	boolean loadApprovers(LtPrHeaders ltPrHeaders) throws ServiceException;
	
	public List<PrApproval> getApprovalList(Integer prHeaderId, String currentApprovalLevel)
			throws ServiceException;
	
	boolean checkStatusIsPending(Long prHeaderId, Long approvalId) throws ServiceException;
	
	String checkforApprovals(Long prHeaderId) throws ServiceException;
	
	boolean submitForApproval(Date date, Long prHeaderId, String state, Object object) throws ServiceException;
	
	boolean upDateStatus(Long prHeaderId, String pending, String currentApprovalLavel) throws ServiceException;
	
	boolean chkForApprovers(Long prHeaderId) throws ServiceException;
	
	List<LtPrHeaders> getInprocessPrList(String inprogressStr) throws ServiceException;
	
	PrApproval getApprovalLevel(Long prHeaderId) throws ServiceException;
	
	String getNextApprovalLevel(Long prHeaderId, String currentApprovalLavel) throws ServiceException;
	
	void updateCurrentApprovalLevel(Long prHeaderId, String currentApprovalLavel) throws ServiceException;

	LtMastEmployees getSupervisorIdByRequesterId(Long requesterId);
















}
