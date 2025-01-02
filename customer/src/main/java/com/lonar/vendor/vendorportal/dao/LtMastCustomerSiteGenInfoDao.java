package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteGenInfo;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastCustomerSiteGenInfoDao {

	LtMastCustSiteGenInfo getBySiteGenInfoId(Long siteGenInfoId) throws ServiceException;

	List<LtMastCustSiteGenInfo> getBycustomerSiteId(Long customerSiteId) throws ServiceException;

	List<LtMastCustSiteGenInfo> getBycustomerId(Long customerSiteId) throws ServiceException;

	Long save(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException;

	boolean update(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException;

	boolean delete(Long customerId) throws ServiceException;

	boolean deleteByCustomerId(Long customerId) throws ServiceException;

	Long getByCustomerSiteDataTableCount(Long customerSiteId, LtMastCustSiteGenInfo input) throws ServiceException;

	public List<LtMastCustSiteGenInfo> getByCustomerSiteDataTable(Long customerSiteId, LtMastCustSiteGenInfo input);

}
