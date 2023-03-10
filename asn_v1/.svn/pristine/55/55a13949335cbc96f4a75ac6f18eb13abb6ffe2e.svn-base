package com.lonar.asn.service;

import java.text.ParseException;
import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.LtMastEmailtoken;
import com.lonar.asn.model.LtShipmentHeaders;

public interface LtMastEmailtokenService 
{
	List<LtMastEmailtoken> findAllActive() throws ServiceException;

	void updateStatus(Long emailTokenId, String string, Integer i) throws ServiceException;

	void makeAsnEntryInEmailToken(List<AsnApproval> invoiceApprovalsList, String emailTemplate,
			LtShipmentHeaders ltShipmentHeader, String string) throws ServiceException,ParseException;
	
}
