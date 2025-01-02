package com.lonar.vendor.vendorportal.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastModuleApprovalsDao;
import com.lonar.vendor.vendorportal.dao.LtRentalAgreementApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtRentalAgreementApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtRentalAgreementHeadersDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtRentalAgreementApprovalServiceImpl implements LtRentalAgreementApprovalService, CodeMaster{
	
	@Autowired
	LtRentalAgreementApprovalDao ltRentalAgreementApprovalDao;
	
	@Autowired
	LtMastModuleApprovalsDao ltMastModuleApprovalsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtRentalAgreementHeadersDao ltRentalAgreementHeadersDao;
	
	@Autowired
	LtRentalAgreementApprovalHistoryDao ltRentalAgreementApprovalHistoryDao;

	@Override
	public Status updateStatusApproval(LtRentalAgrApprovalHistory approvalHistory) throws ServiceException {
		Status status=new Status();
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		approvalHistory.setLastUpdateDate(new Date());
		if(approvalHistory.getStatus().equals(RA_APPROVED))
		{
			System.out.println("in updateStatusApproval service");
			System.out.println("approvalHistory = "+approvalHistory);
			if(ltRentalAgreementApprovalDao.updateStatusApproval(approvalHistory) )
			{
			if(ltMastModuleApprovalsDao.chkRentalAgreementIsAprovedByAnyOne(approvalHistory))
			{
				if(ltRentalAgreementApprovalDao.updateAllStatusApproval(approvalHistory))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(INV_APPROVED);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("RENTAL_AGREEMENT_APPROVED").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action was successful");
					}
				}else
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(INVOICE_APPROVAL_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("RENTAL_AGREEMENT_APPROVAL_FAIL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("Error in finding message! The action was unsuccessful");
					}
				}
			}
		
		}
			else 
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INVOICE_APPROVAL_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("RENTAL_AGREEMENT_APPROVAL_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
			}
				
//			status=ltMastCommonMessageService.getCodeAndMessage(INV_APPROVED);
			try {
				System.out.println("in try catch approved...");
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("RENTAL_AGREEMENT_APPROVED").getMessageName());
//				status.setMessage("Invoice Approved");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status.getMessage()==null)
			{
				status.setCode(1);
			}
		}
		else if(approvalHistory.getStatus().equals(RA_FEEDBACKAWAITED)) {
			if(ltRentalAgreementApprovalDao.updateStatusApproval(approvalHistory)) {
				if(ltRentalAgreementApprovalDao.submitForApproval(null,approvalHistory.getAgreementHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin())) {
					LtRentalAgreementHeaders ltRentalAgreementHeaders= ltRentalAgreementHeadersDao.getRentalAgreementById(approvalHistory.getAgreementHeaderId());
					
					if(ltRentalAgreementHeaders!=null) {
						
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("RENTAL_AGREEMENT_FEEDBACK_AWAITED").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if(status.getMessage()==null) 
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action was successful");
						}
					}
					
					String currentLevel=null;
					if(approvalHistory.getAgreementApprovalId()!=null	)
					{
						currentLevel=ltRentalAgreementApprovalDao.getCurrLevelByAgreementApprovalId(approvalHistory.getAgreementApprovalId());
					}
					ltRentalAgreementApprovalDao.upDateStatus(approvalHistory.getAgreementHeaderId(), NO_ACTION, currentLevel);
				}
			}
		}
		else if(approvalHistory.getStatus().equals(RA_REJECTED)) {
			if(ltRentalAgreementApprovalDao.updateStatusApproval(approvalHistory)) 
			{
				if(ltRentalAgreementApprovalDao.submitForApproval(null,approvalHistory.getAgreementHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin()))
				{
					LtRentalAgreementHeaders ltRentalAgreementHeaders= ltRentalAgreementHeadersDao.getRentalAgreementById(approvalHistory.getAgreementHeaderId());
					
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("RENTAL_AGREEMENT_REJECTED").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action was successful");
					}
					
					ltRentalAgreementApprovalDao.upDateStatus(approvalHistory.getAgreementHeaderId(), NO_ACTION, null);	
				}
			}
		}
		ltRentalAgreementApprovalHistoryDao.save(approvalHistory);
		return status;
	}

	@Override
	public List<LtRentalAgrApprovalHistory> getRentalAgrApprovalHistoryByAgreementId(Long agreementHeaderId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return ltRentalAgreementApprovalDao.getRentalAgrApprovalHistoryByAgreementHeaderId(agreementHeaderId);
	}

	@Override
	public LtRentalAgreementApproval getRentalAgreementApproval(Long agreementHeaderId, Long apprId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return ltRentalAgreementApprovalDao.getRentalAgreementApproval(agreementHeaderId, apprId);
	}

}
