package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtCompanyVenMgmtCoc;
import com.lonar.vendor.vendorportal.model.LtCompanyVenMgmtCoc1;
import com.lonar.vendor.vendorportal.model.LtVendCompanyCoc;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtVendCompanyCocDao 
{

	List<LtVendCompanyCoc> getBycompanyId(Long companyId) throws ServiceException;

	LtVendCompanyCoc getById(Long id) throws ServiceException;

	List<LtVendCompanyCoc> getAll() throws ServiceException;

	List<LtVendCompanyCoc> getAllActive() throws ServiceException;

	boolean delete(Long compConductId) throws ServiceException;

	boolean update(LtVendCompanyCoc ltVendCompanyCoc) throws ServiceException;
	
	List<LtCompanyVenMgmtCoc> getManagementBycompanyVendorId(Long companyId, Long vendorId) throws ServiceException;
	
	List<LtCompanyVenMgmtCoc> getManagementBycompanyId(Long companyId) throws ServiceException;

	boolean updateCompanyVendCoc(Long companyId) throws ServiceException;

	List<LtVendCompanyCoc> getBycompanyAndVendorId(Long companyId, Long venId) throws ServiceException;

}
