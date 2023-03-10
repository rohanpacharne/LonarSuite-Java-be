package com.lonar.asn.service;
import java.util.List;
import java.util.Map;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;

import com.lonar.asn.model.LtMastCommonMessage;
import com.lonar.asn.model.Status;



public interface LtMastCommonMessageService
{

	public Status getCodeAndMessage(Integer code) ;  // akshay 15-3-2018
	
	public LtMastCommonMessage getMsgcode(String messageCode) throws ServiceException;

	public LtMastCommonMessage getByMessageCode(String code) throws ServiceException;
	
	//public Status returnStatusMessage(Integer code) throws Exception;
	
	public  Map<Integer,String> getAllMessages() throws ServiceException;
	
	

	public ResponseEntity listLtMastCommonMessage() throws ServiceException;

	public ResponseEntity getByID(Long id) throws ServiceException;

	//public Status update(LtMastCommonMessage mastCommonMessage) throws ServiceException;

	public List<LtMastCommonMessage> getCommonMessageDataTable(LtMastCommonMessage input) throws ServiceException;

	public Long getCount(LtMastCommonMessage input) throws ServiceException;

	public void saveLog(Exception e);

	public ResponseEntity<Status> save(LtMastCommonMessage ltMastCommonMessage) throws ServiceException;

	//public ResponseEntity<Status> updateMessage(LtMastCommonMessage mastCommonMessage) throws ServiceException;

	public ResponseEntity<Status> delete(String id) throws ServiceException;

}
