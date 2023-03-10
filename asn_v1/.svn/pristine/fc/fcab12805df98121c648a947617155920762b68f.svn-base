package com.lonar.asn.dao;

import java.util.Date;
import java.util.List;

import com.lonar.asn.model.AttachmentDTO;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.LtShipmentAttachment;

public interface LtShipmentAttachmentDao 
{

	List<LtShipmentAttachment> getAllFilesByShipmentHeaderId(Long asnHeaderId) throws BusinessException;

	boolean save(Long asnHeaderId, String fileName, String saveDirectory, Long userId, Date currDate,
			Long attachmentTypeId) throws BusinessException;

	boolean saveLineAttachment(Long asnHeaderId, String fileName, String saveDirectory, Long userId, Date currDate,
			String asnLineId) throws BusinessException;

	boolean deleteByID(String id) throws BusinessException;
	
	AttachmentDTO getfilepathbylineid(String id) throws BusinessException;

	boolean deleteAttachmentByShipmentHeaderId(Long id) throws BusinessException;

}
