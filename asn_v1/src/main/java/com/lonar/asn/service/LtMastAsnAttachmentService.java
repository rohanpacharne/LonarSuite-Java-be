package com.lonar.asn.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.LtMastAsnAttachment;
import com.lonar.asn.model.Status;

public interface LtMastAsnAttachmentService extends CodeMaster
{
	
	public List<LtMastAsnAttachment> getAllFilesByInvoiceHeaderId(Long invoiceId) throws ServiceException;
	
	public LtMastAsnAttachment getAttachmentByInvoiceIdAndType(Long invoiceId) throws ServiceException;
	
	public Status deleteLtMastVendorAttachmentFile(Long invoiceAttachmentId);

}
