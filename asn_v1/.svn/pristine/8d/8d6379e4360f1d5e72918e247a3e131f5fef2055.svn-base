package com.lonar.asn.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.LtInvoiceHeaders;
import com.lonar.asn.model.LtMastVendors;
import com.lonar.asn.model.Status;

public interface LtMastVendorsDao 
{

	List<LtMastVendors> getAllVendors() throws ServiceException;

	LtMastVendors getVendorById(long l) throws ServiceException;

	Long save(LtMastVendors vendors) throws ServiceException;

	boolean update(LtMastVendors vendors) throws ServiceException;

	boolean delete(Long vendorId) throws ServiceException;

	List<LtMastVendors> getAllActiveVendors() throws ServiceException;

	List<LtMastVendors> getActiveVendorByName(Long companyId, String vendorName) throws ServiceException;

	boolean getByName(LtMastVendors ltMastVendors) throws ServiceException;

	boolean getByVendorCode(LtMastVendors ltMastVendors) throws ServiceException;

	boolean getByPanNo(LtMastVendors ltMastVendors) throws ServiceException;

	Long getLtMastVendorsCount(Long companyId, LtMastVendors input) throws ServiceException;

	List<LtMastVendors> getLtMastVendorsDataTable(Long companyId, LtMastVendors input) throws ServiceException;

	List<LtMastVendors> getActiveVendorByNameAndDivId(String vendorName, Long divId) throws ServiceException;

	List<LtMastVendors> getVendorByDivId(Long divId) throws ServiceException;

	boolean submitForApproval(Date date, Long vendorId, String state, Object object) throws ServiceException;


	List<LtMastVendors> getInprocessVendorList(String inprogress) throws ServiceException;

	

	String getNextApprovalLevel(Long vendorId, String currentApprovalLavel) throws ServiceException;

	boolean upDateStatus(Long vendorId, String pending, String currentApprovalLavel) throws ServiceException;

	void updateCurrentApprovalLevel(Long vendorId, String currentApprovalLavel) throws ServiceException;

	boolean loadApprovers(Long vendorId, Long companyId) throws ServiceException;

	LtMastVendors getStatusVendorById(Long vendorId) throws ServiceException;

	List<LtMastVendors> getAllVendorsByCompanyId(Long companyId) throws ServiceException;

	LtMastVendors getByEMailId(String primaryEmail,Long companyId) throws ServiceException;

	Long getLtMastVendorsCountByInitiatorId(LtMastVendors input, Long initiatorId) throws ServiceException;

	List<LtMastVendors> getLtMastVendorsDataTableByInitiatorId(LtMastVendors input, Long initiatorId) throws ServiceException;

	List<LtMastVendors> getAllVendorsByInitiator(Long initiatorId) throws ServiceException;

	boolean loadInvoiceApprovers(LtInvoiceHeaders ltInvoiceHeaders) throws ServiceException;

	LtMastVendors getPanByvendorbyid(Long vendorId) throws ServiceException;

	boolean checkDuplicateUser(String primaryEmail, Long companyId) throws ServiceException;

	

	Status getCompanyByVendor(Long venId) throws ServiceException;

	Status getVendorNameById(Long vendorId) throws ServiceException;

	

	List<LtMastVendors> getVendorByName(Long companyId, String vendorName) throws ServiceException;

	


	

}
