package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteContacts;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastCustomerSiteContactService {

	List<LtMastCustSiteContacts> getAllCustSiteContacts() throws ServiceException;

	List<LtMastCustSiteContacts> getContactsByCustomerId(Long customerId) throws ServiceException;

	List<LtMastCustSiteContacts> getContactsByCustomerSiteId(Long customerSiteId) throws ServiceException;

	LtMastCustSiteContacts getCustSiteContactsById(Long siteContactId) throws ServiceException;

	Status save(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException;

	Status update(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException;

	Status delete(Long siteContactId) throws ServiceException;

	Long getByCustomerSiteContactsDataTableCount(Long customerSiteId, LtMastCustSiteContacts input)
			throws ServiceException;

	List<LtMastCustSiteContacts> getByCustomerSiteContactsDataTable(Long customerSiteId, LtMastCustSiteContacts input)
			throws ServiceException;

}
