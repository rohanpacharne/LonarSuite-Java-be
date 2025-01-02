package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteCommercials;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastCustomerSiteCommercialsService {

	List<LtMastCustSiteCommercials> getAllCustSiteCommercials() throws ServiceException;

	List<LtMastCustSiteCommercials> getByCustomerId(Long customerId) throws ServiceException;

	List<LtMastCustSiteCommercials> getByCustomerSiteId(Long customerSiteId) throws ServiceException;

	LtMastCustSiteCommercials getBySiteCommercialId(Long siteCommercialId) throws ServiceException;

	Status save(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException;

	Status update(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException;

	Status delete(Long vendorBankId) throws ServiceException;
	
	Long getCustomerCommercialsDataTableCount(Long customerSiteId, LtMastCustSiteCommercials input)
			throws ServiceException;

	List<LtMastCustSiteCommercials> getCustomerCommercialsDataTable(Long customerSiteId, LtMastCustSiteCommercials input)
			throws ServiceException;

}
