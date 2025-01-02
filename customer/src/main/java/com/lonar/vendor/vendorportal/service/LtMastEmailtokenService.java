package com.lonar.vendor.vendorportal.service;

import java.text.ParseException;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastEmailtokenService 
{

	void makeEntryInEmailToken(List<LtCustomerApproval> expenseApprovals, String emailTemplate,
			LtMastCustomer ltMastCustomer, String string) throws ServiceException,ParseException;

	List<LtMastEmailtoken> findAllActive() throws ServiceException;

	void updateStatus(Long emailTokenId, String string, Integer i) throws ServiceException;
}
