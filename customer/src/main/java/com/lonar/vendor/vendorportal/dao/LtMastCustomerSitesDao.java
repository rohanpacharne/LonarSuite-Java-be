package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.vendor.vendorportal.model.LtMastCustomerSites;

public interface LtMastCustomerSitesDao {

	List<LtMastCustomerSites> getAllCustomerSites();

	Long getCustomerSitesCount(Long customerId, LtMastCustomerSites input);

	List<LtMastCustomerSites> getCustomerSitesDataTable(Long customerId, LtMastCustomerSites input);

	LtMastCustomerSites getCustomerSiteById(Long customerSiteId);

	Long save(LtMastCustomerSites ltMastCustomerSites);

	boolean update(LtMastCustomerSites ltMastCustomerSites) throws ServiceException;

	boolean delete(Long customerSiteId) throws ServiceException ;

	List<LtMastCustomerSites> getCustomerSiteByCustomerId(Long customerSiteId) throws ServiceException ;

	boolean deleteByCustomerId(Long customerId) throws ServiceException ;


}
