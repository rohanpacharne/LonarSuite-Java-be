package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteContacts;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastCustomerSiteContactDao {

	List<LtMastCustSiteContacts> getAllCustSiteContacts() throws ServiceException;

	List<LtMastCustSiteContacts> getContactsByCustomerId(Long customerId) throws ServiceException;

	List<LtMastCustSiteContacts> getContactsByCustomerSiteId(Long customerSiteId) throws ServiceException;

	LtMastCustSiteContacts getCustSiteContactsById(Long siteContactId) throws ServiceException;

	boolean save(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException;

	boolean update(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException;

	boolean delete(Long siteContactId) throws ServiceException;

	boolean deleteByCustomerId(Long customerId) throws ServiceException;

	Long getByCustomerSiteContactsDataTableCount(Long customerSiteId, LtMastCustSiteContacts input)
			throws ServiceException;

	List<LtMastCustSiteContacts> getByCustomerSiteContactsDataTable(Long customerSiteId, LtMastCustSiteContacts input)
			throws ServiceException;

}
