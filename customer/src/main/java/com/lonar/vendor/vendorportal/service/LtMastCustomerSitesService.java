package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustomerSites;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastCustomerSitesService {

	public List<LtMastCustomerSites> getAllCustomerSites();

	public Long getCustomerSitesCount(Long customerId, LtMastCustomerSites input);

	public List<LtMastCustomerSites> getCustomerSitesDataTable(Long customerId, LtMastCustomerSites input);

	public LtMastCustomerSites getCustomerSiteById(Long customerSiteId);

	public Status save(LtMastCustomerSites ltMastCustomerSites)throws ServiceException;

	public Status update(LtMastCustomerSites ltMastCustomerSites) throws ServiceException;

	public Status delete(Long customerSiteId) throws ServiceException ;

	public List<LtMastCustomerSites> getCustomerSiteByCustomerId(Long customerSiteId) throws ServiceException;

}
