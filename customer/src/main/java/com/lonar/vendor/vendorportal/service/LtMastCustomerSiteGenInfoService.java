package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteGenInfo;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastCustomerSiteGenInfoService {

	public LtMastCustSiteGenInfo getBySiteGenInfoId(Long siteGenInfoId) throws ServiceException;

	public List<LtMastCustSiteGenInfo> getBycustomerSiteId(Long customerSiteId) throws ServiceException;

	public Long getByCustomerSiteDataTableCount(Long customerSiteId, LtMastCustSiteGenInfo input)
			throws ServiceException;

	public List<LtMastCustSiteGenInfo> getByCustomerSiteDataTable(Long customerSiteId, LtMastCustSiteGenInfo input)
			throws ServiceException;

	public List<LtMastCustSiteGenInfo> getBycustomerId(Long customerId) throws ServiceException;

	public Status save(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException;

	public Status update(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException;

	public Status delete(Long customerId) throws ServiceException;

}
