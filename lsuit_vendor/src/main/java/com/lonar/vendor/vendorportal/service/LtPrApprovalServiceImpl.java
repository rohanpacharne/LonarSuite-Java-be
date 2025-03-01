package com.lonar.vendor.vendorportal.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastModuleApprovalsDao;
import com.lonar.vendor.vendorportal.dao.LtPrApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtPrApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtPrHeadersDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtPrApprovalServiceImpl implements LtPrApprovalService, CodeMaster {
	
	@Autowired
	LtPrApprovalDao ltPrApprovalDao;
	
	@Autowired
	LtMastModuleApprovalsDao ltMastModuleApprovalsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtPrHeadersDao ltPrHeadersDao;
	
	@Autowired
	LtPrApprovalHistoryDao ltPrApprovalHistoryDao;
	
	

	@Override
	public List<LtPrApprovalHistory> getPrApprovalHistoryByPrHeaderId(Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltPrApprovalDao.getPrApprovalHistoryByPrHeaderId(prHeaderId);
	}

	@Override
	public Status updateStatusApproval(LtPrApprovalHistory approvalHistory) throws ServiceException {
		// TODO Auto-generated method stub
		Status status=new Status();
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		approvalHistory.setLastUpdateDate(new Date());
		if(approvalHistory.getStatus().equals(APPROVED))
		{
			System.out.println("in updateStatusApproval service");
			System.out.println("approvalHistory = "+approvalHistory);
			if(ltPrApprovalDao.updateStatusApproval(approvalHistory) )
			{
			if(ltMastModuleApprovalsDao.chkPrIsAprovedByAnyOne(approvalHistory))
			{
				if(ltPrApprovalDao.updateAllStatusApproval(approvalHistory))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(INV_APPROVED);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PR_APPROVED").getMessageName());
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
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PR_APPROVAL_FAIL").getMessageName());
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
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PR_APPROVAL_FAIL").getMessageName());
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
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PR_APPROVED").getMessageName());
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
		else if(approvalHistory.getStatus().equals(FEEDBACK_AWAITED)) {
			if(ltPrApprovalDao.updateStatusApproval(approvalHistory)) {
				if(ltPrApprovalDao.submitForApproval(null,approvalHistory.getPrHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin())) {
					LtPrHeaders ltPrHeaders= ltPrHeadersDao.getPrHeaderById(approvalHistory.getPrHeaderId());
					
					if(ltPrHeaders!=null) {
						
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PR_FEEDBACK_AWAITED").getMessageName());
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
					if(approvalHistory.getPrApprovalId()!=null	)
					{
						currentLevel=ltPrApprovalDao.getCurrLevelByPrApprovalId(approvalHistory.getPrApprovalId());
					}
					ltPrApprovalDao.upDateStatus(approvalHistory.getPrHeaderId(), NO_ACTION, currentLevel);
				}
			}
		}
		else if(approvalHistory.getStatus().equals(REJECTED)) {
			if(ltPrApprovalDao.updateStatusApproval(approvalHistory)) 
			{
				if(ltPrApprovalDao.submitForApproval(null,approvalHistory.getPrHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin()))
				{
					LtPrHeaders ltPrHeaders= ltPrHeadersDao.getPrHeaderById(approvalHistory.getPrHeaderId());
					
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PR_REJECTED").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action was successful");
					}
					
					ltPrApprovalDao.upDateStatus(approvalHistory.getPrHeaderId(), NO_ACTION, null);	
				}
			}
		}
		ltPrApprovalHistoryDao.save(approvalHistory);
		return status;
	}

	@Override
	public LtPrApproval getPrApproval(Long prHeaderId, Long apprId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltPrApprovalDao.getPrApproval(prHeaderId, apprId);
	}
	
	

}
