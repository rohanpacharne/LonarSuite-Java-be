package com.lonar.asn.dao;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.LtMastEmailtoken;

public interface LtMastEmailtokenDao {

	Long makeEntryInEmailToken(LtMastEmailtoken ltMastEmailtoken) throws ServiceException;

	List<LtMastEmailtoken> findAllActive() throws ServiceException;

	void updateStatus(Long emailTokenId, String status, Integer count) throws ServiceException;

}
