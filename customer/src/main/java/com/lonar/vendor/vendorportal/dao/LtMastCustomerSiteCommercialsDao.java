package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteCommercials;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastCustomerSiteCommercialsDao {

	List<LtMastCustSiteCommercials> getAllCustSiteCommercials() throws ServiceException;

	List<LtMastCustSiteCommercials> getByCustomerId(Long customerId) throws ServiceException;

	List<LtMastCustSiteCommercials> getByCustomerSiteId(Long customerSiteId) throws ServiceException;

	LtMastCustSiteCommercials getBySiteCommercialId(Long siteCommercialId) throws ServiceException;

	boolean save(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException;

	boolean update(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException;

	boolean delete(Long siteCommercialId) throws ServiceException;

	boolean deleteByCustomerId(Long customerId) throws ServiceException;
	
	Long getCustomerCommercialsDataTableCount(Long customerSiteId, LtMastCustSiteCommercials input)
			throws ServiceException;

	List<LtMastCustSiteCommercials> getCustomerCommercialsDataTable(Long customerSiteId, LtMastCustSiteCommercials input)
			throws ServiceException;

}
