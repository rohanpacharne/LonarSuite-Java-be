package com.lonar.vendor.vendorportal.service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastEmailtokenDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.dao.LtMastModuleApprovalsDao;
import com.lonar.vendor.vendorportal.dao.LtMastUsersDao;
import com.lonar.vendor.vendorportal.dao.LtMastVendorsDao;
import com.lonar.vendor.vendorportal.dao.LtPoApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtPoHeadersDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.LtMastVendors;
import com.lonar.vendor.vendorportal.dao.LtPoApprovalDao;
import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPoHeaders;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class LtPoApprovalServiceImpl implements LtPoApprovalService,CodeMaster {
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtPoApprovalDao ltPoApprovalDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastModuleApprovalsDao ltMastModuleApprovalsDao;
	
	@Autowired
	LtPoApprovalHistoryDao poApprovalHistoryDao;

	@Autowired
	LtPoHeadersDao ltPoHeadersDao;
	
	@Autowired
	LtMastUsersDao ltMastUsersDao;
	
	@Autowired
	LtMastEmailtokenDao ltMastEmailtokenDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtMastVendorsDao ltMastVendorsDao;
	
	@Override
	public Status updatePoStatusApproval(LtPoApprovalHistory approvalHistory) throws ServiceException {
		Status status=new Status();
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		approvalHistory.setLastUpdateDate(new Date());
		if(approvalHistory.getStatus().equals(APPROVED))
		{
			if(ltPoApprovalDao.updateStatusApproval(approvalHistory) )
			{
				if(ltMastModuleApprovalsDao.chkPoIsAprovedByAnyOne(approvalHistory))
				{
					if(ltPoApprovalDao.updateAllStatusApproval(approvalHistory))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(INV_APPROVED);
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PO_APPROVED").getMessageName());
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
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(INVOICE_APPROVAL_FAIL);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PO_APPROVAL_FAIL").getMessageName());
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
			}else 
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INVOICE_APPROVAL_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PO_APPROVAL_FAIL").getMessageName());
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
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PO_APPROVED").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status.getMessage()==null)
			{
				status.setCode(1);
			}
		}
		
		else if(approvalHistory.getStatus().equals(FEEDBACK_AWAITED))
		{
			if(ltPoApprovalDao.updateStatusApproval(approvalHistory))
			{
				if(ltPoApprovalDao.submitForApproval(null,approvalHistory.getPoHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin())) {
				
				LtPoHeaders ltPoHeaders= ltPoHeadersDao.getPoHeaderById(approvalHistory.getPoHeaderId());
				if(ltPoHeaders !=null)
				{		
					//LtMastUsers ltMastUsers = ltMastUsersDao.getLtMastUsersByID(ltInvoiceHeaders.getCreatedBy()); //--commented on 26-9-19-akshay
					LtMastVendors ltMastVendors = ltMastVendorsDao.getVendorById(ltPoHeaders.getVendorId());
					List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(approvalHistory.getEmployeeId());
					LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
					ltMastEmailtoken.setEmailObject(
							"#$#$poNumber="+ltPoHeaders.getPoNumber()+
							"#$#$name="+ltMastVendors.getVendorName()+
							"#$#$approverName="+ltMastEmployees.get(0).getEmpName()+
							"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
					ltMastEmailtoken.setSendTo(ltMastVendors.getPrimaryEmail());
					ltMastEmailtoken.setEmailStatus("SENDING");
					ltMastEmailtoken.setEmailTitle("Po Number : "+ltPoHeaders.getPoNumber() +
							", Internal Po no : "+ltPoHeaders.getInternalPoNumber() + "has been sent for feedback.");
					
					ltMastEmailtoken.setEmailTemplate("poFeedback");
					
					ltMastEmailtoken.setExpiredWithin(1296000L);
					ltMastEmailtoken.setSendDate(new Date());

					ltMastEmailtoken.setEmailStatus("SENDING");
					
					ltMastEmailtoken.setExpiredWithin(1296000L);
					ltMastEmailtoken.setSendDate(new Date());
					if(ltPoHeaders.getBuyerId()!=null) {
						List<LtMastEmployees> buyer = ltMastEmployeesDao.getByEmpIdForEmail(ltPoHeaders.getBuyerId());
					ltMastEmailtoken.setSendCc(buyer.get(0).getOfficialEmail()+","+ltMastEmployees.get(0).getOfficialEmail());
					}
					//ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(ltMastVendors.getInitiatorId());
					//ltMastEmailtoken.setSendCc(ltMastEmployees.get(0).getOfficialEmail());
					ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
					
//					status=ltMastCommonMessageService.getCodeAndMessage(INV_FEEDBACK_AWAITED);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PO_FEEDBACK_AWAITED").getMessageName());
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
				if(approvalHistory.getPoApprovalId()!=null	)
				{
					currentLevel=ltPoApprovalDao.getCurrLevelByPoApprovalId(approvalHistory.getPoApprovalId());
				}
				ltPoApprovalDao.upDateStatus(approvalHistory.getPoHeaderId(), NO_ACTION, currentLevel);	
			}	
		  }
		}
		else if(approvalHistory.getStatus().equals(REJECTED))
		{
			if(ltPoApprovalDao.updateStatusApproval(approvalHistory)) 
			{
				if(ltPoApprovalDao.submitForApproval(null,approvalHistory.getPoHeaderId(),approvalHistory.getStatus(),null,approvalHistory.getLastUpdateLogin()))
				{
					LtPoHeaders ltPoHeaders= ltPoHeadersDao.getPoHeaderById(approvalHistory.getPoHeaderId());
					//LtMastUsers ltMastUsers = ltMastUsersDao.getLtMastUsersByID(ltInvoiceHeaders.getCreatedBy()); //commented on 26-9-19-akshay
					LtMastVendors ltMastVendors = ltMastVendorsDao.getVendorById(ltPoHeaders.getVendorId());
					if(ltMastVendors !=null)
					{
						List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(approvalHistory.getEmployeeId());
							LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
							/*if(ltMastUsers.getEmployeeName()==null) {
								ltMastUsers.setEmployeeName(ltMastUsers.getVendorName());
							}*/
							ltMastEmailtoken.setEmailObject(
									"#$#$poNumber="+ltPoHeaders.getPoNumber()+
									"#$#$name="+ltMastVendors.getVendorName()+
									"#$#$approverName="+ltMastEmployees.get(0).getEmpName()+
									"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
							ltMastEmailtoken.setSendTo(ltMastVendors.getPrimaryEmail());
							ltMastEmailtoken.setEmailStatus("SENDING");
							//ltMastEmailtoken.setEmailTitle("Vendor reject mail");
							ltMastEmailtoken.setEmailTitle("po Number : "+ltPoHeaders.getPoNumber() +
									", Internal Po no : "+ltPoHeaders.getInternalPoNumber() + "has been rejected");
							ltMastEmailtoken.setEmailTemplate("poReject");
							
							ltMastEmailtoken.setExpiredWithin(1296000L);
							ltMastEmailtoken.setSendDate(new Date());
							if(ltPoHeaders.getBuyerId()!=null) {
								List<LtMastEmployees> buyer = ltMastEmployeesDao.getByEmpIdForEmail(ltPoHeaders.getBuyerId());
							ltMastEmailtoken.setSendCc(buyer.get(0).getOfficialEmail()+","+ltMastEmployees.get(0).getOfficialEmail());
							}
							ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
		
//							status=ltMastCommonMessageService.getCodeAndMessage(INV_REJECTED);
							try {
								status.setCode(1);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("Po_REJECTED").getMessageName());
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
					ltPoApprovalDao.upDateStatus(approvalHistory.getPoHeaderId(), NO_ACTION, null);	
			
				}
			}
		}
		
		poApprovalHistoryDao.save(approvalHistory);
		return status;
	
	}

	@Override
	public PoApproval getPoApproval(Long poId, Long apprId) throws ServiceException {
		return ltPoApprovalDao.getPoApproval(poId,apprId);
	}

	

}
