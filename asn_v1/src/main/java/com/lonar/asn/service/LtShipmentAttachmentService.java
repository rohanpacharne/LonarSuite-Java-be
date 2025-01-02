package com.lonar.asn.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lonar.asn.model.AttachmentDTO;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.LtShipmentAttachment;
import com.lonar.asn.model.Status;

public interface LtShipmentAttachmentService
{

	Status saveAsnAttachments(MultipartFile[] files, Long asnHeaderId, Long userId, Long attachmentTypeId,Long companyId) throws BusinessException, FileNotFoundException, IOException;

	List<LtShipmentAttachment> getAllByHeaderId(Long id) throws BusinessException;

	Status saveAsnLineAttachments(MultipartFile[] files, Long asnHeaderId, Long userId, String asnLineId) throws BusinessException, FileNotFoundException, IOException;

	Status deleteByID(String id) throws BusinessException;
	
	AttachmentDTO getfilepathbylineid (String id) throws BusinessException;

}
