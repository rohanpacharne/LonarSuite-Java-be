package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.ProcedureCall;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtCustomerApprovalDao 
{

	boolean updateStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException;

	boolean updateAllStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException;

	List<LtCustomerApproval> chkEmpApproval(Long employeeId, Long vendorId) throws ServiceException;

	LtCustomerApproval getCustomerApproval(Long vendorId, Long appId) throws ServiceException;

	List<LtCustomerApproval> getCustomerApprovalByCustomerId(Long customerId) throws ServiceException;

	boolean chkForApprovers(Long vendorId) throws ServiceException;

	boolean submitForApproval(Date submissionDate, Long vendorId, String status, Date approvedDate) throws ServiceException;

	String getCurrLevelByVendorApprovalId(Long vendorApprovalId) throws ServiceException;

	List<LtCustomerApproval> chkCurrentApprover(Long vendorId) throws ServiceException;

	boolean updateVendorStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException;

	List<LtMastCustomer> getInprocessCustomerList(String status) throws ServiceException;

	LtCustomerApproval getApprovalLevel(Long customerId) throws ServiceException;

	List<LtCustomerApproval> getApprovalList(Long customerId, String currentApprovalLevel) throws ServiceException;

	String getNextApprovalLevel(Long customerId, String currentApprovalLavel) throws ServiceException;

	boolean upDateStatus(Long customerId, String pending, String currentApprovalLavel) throws ServiceException;

	void updateCurrentApprovalLevel(Long customerId, String currentApprovalLavel) throws ServiceException;

	boolean loadApprovers(Long customerId, Long companyId) throws ServiceException;

	ProcedureCall customerValidationPackage(Long customerId) throws ServiceException;

	boolean deleteByCustomerId(Long customerId) throws ServiceException;



}
