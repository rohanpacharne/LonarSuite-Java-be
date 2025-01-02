package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtRentalAgreementAttachmentDao;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtRentalAgreementAttachmentServiceImpl implements LtRentalAgreementAttachmentService{
	
	@Autowired
	LtRentalAgreementAttachmentDao ltRentalAgreementAttachmentDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Override
	public List<LtRentalAgreementAttachments> getAllFilesByAgreementHeaderId(Long agreementHeaderId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return ltRentalAgreementAttachmentDao.getAllFilesByAgreementHeaderId(agreementHeaderId);
	}

	@Override
	public Status deleteLtAgreementAttachmentFile(Long agreementAttachmentId) {
		Status status = new Status();
		if(ltRentalAgreementAttachmentDao.deleteLtAgreementAttachmentFile(agreementAttachmentId))
		{
//			System.out.println("In if of deleteLtMastInvoiceAttachmentFile");
//			status=ltMastCommonMessageService.getCodeAndMessage(ATTACHEMENT_DELETED_SUCCESS);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_SUCCESS").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(ATTACHEMENT_DELETED_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed UnSuccessfully.");
			}
		}
		return status;
	}

}
