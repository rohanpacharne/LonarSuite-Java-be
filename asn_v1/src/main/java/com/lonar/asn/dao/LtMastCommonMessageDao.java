package com.lonar.asn.dao;


import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.LtMastCommonMessage;


public interface LtMastCommonMessageDao 
{

	public List<LtMastCommonMessage> getMsgcode(String messageCode) throws ServiceException;

	public LtMastCommonMessage getByMessageCode(Integer code)throws ServiceException;
	
	public List<LtMastCommonMessage> getAllMessages() throws ServiceException;

	public List<LtMastCommonMessage> listLtMastCommonMessage() throws ServiceException;

	public LtMastCommonMessage getByID(Long id) throws ServiceException;

	public boolean update(LtMastCommonMessage mastCommonMessage) throws ServiceException;

	public List<LtMastCommonMessage> getCommonMessageDataTable(LtMastCommonMessage input) throws ServiceException;

	public Long getCount(LtMastCommonMessage input) throws ServiceException;

	public LtMastCommonMessage getByMessageName(String messageName) throws ServiceException;
	
	public LtMastCommonMessage getMessageNameByCode(String messageCode) throws Exception;

}
