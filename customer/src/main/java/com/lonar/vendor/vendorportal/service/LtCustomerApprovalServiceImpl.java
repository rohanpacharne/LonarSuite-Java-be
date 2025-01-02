package com.lonar.vendor.vendorportal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtCustomerApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtCustomerApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerInfoDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmailtokenDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.dao.LtMastModuleApprovalsDao;
import com.lonar.vendor.vendorportal.model.BulkIdWithName;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.InboxBulkInput;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.ProcedureCall;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class LtCustomerApprovalServiceImpl implements LtCustomerApprovalService,CodeMaster{
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtCustomerApprovalHistoryDao ltCustomerApprovalHistoryDao;
	
	@Autowired
	LtCustomerApprovalDao ltCustomerApprovalDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtMastCustomerInfoDao ltMastCustomerInfoDao;
	
	@Autowired
	LtMastEmailtokenDao ltMastEmailtokenDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastModuleApprovalsDao ltMastModuleApprovalsDao;
	
	@Override
	public Status updateStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException {
		Status status=new Status();
		approvalHistory.setLastUpdateDate(new Date());
		if(approvalHistory.getStatus().equals(APPROVED))
		{
			//LtMastVendors ltMastVendors=getdetails(approvalHistory.getVendorId());
			if(ltCustomerApprovalDao.updateStatusApproval(approvalHistory) )
			{
				if(ltMastModuleApprovalsDao.chkIsAprovedByAnyOne(approvalHistory))
				{
					if(ltCustomerApprovalDao.updateAllStatusApproval(approvalHistory))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_APPROVED);
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_APPROVED").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_APPROVAL_FAIL);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_APPROVAL_FAIL").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}else 
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_APPROVAL_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_APPROVAL_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
//			status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_APPROVED);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_APPROVED").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status.getMessage()==null)
			{
				status.setCode(1);
			}
		}
		else if(approvalHistory.getStatus().equals(FEEDBACK_AWAITED) )
		{
			if(ltCustomerApprovalDao.updateStatusApproval(approvalHistory))
			{
				ltCustomerApprovalDao.submitForApproval(null,approvalHistory.getCustomerId(),approvalHistory.getStatus(),null);
				
				LtMastCustomer ltMastCustomer= ltMastCustomerInfoDao.getCustomerById(approvalHistory.getCustomerId());
				if(ltMastCustomer !=null)
				{		
					List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(ltMastCustomer.getInitiatorId());
					LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
					ltMastEmailtoken.setEmailObject(
							"#$#$customerName="+ltMastCustomer.getCustomerName()+" ( "+ltMastCustomer.getCustomerNumber()+" )"+
							"#$#$approverName="+ltMastEmployees.get(0).getEmpName()+
							"#$#$name="+ltMastCustomer.getCustomerName()+
							"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
					ltMastEmailtoken.setSendTo(ltMastEmployees.get(0).getOfficialEmail());
					ltMastEmailtoken.setEmailStatus("SENDING");
					ltMastEmailtoken.setEmailTitle("Request to register for "+ltMastCustomer.getCustomerName()+" ( "+ltMastCustomer.getCustomerNumber()+" )"+
					" has been sent for your feedback");
					
					ltMastEmailtoken.setEmailTemplate("customerFeedback");
					ltMastEmailtoken.setExpiredWithin(1296000L);
					ltMastEmailtoken.setSendDate(new Date());
					ltMastEmailtoken.setEmailStatus("SENDING");
					ltMastEmailtoken.setSendDate(new Date());
					ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(approvalHistory.getEmployeeId());
					ltMastEmailtoken.setSendCc(ltMastEmployees.get(0).getOfficialEmail());
					ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
					
//					status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_FEEDBACK);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_FEEDBACK").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
				String currentLevel=null;
				if(approvalHistory.getCustomerApprovalId()!=null	)
				{
					currentLevel=ltCustomerApprovalDao.getCurrLevelByVendorApprovalId(approvalHistory.getCustomerApprovalId());
				}
				ltCustomerApprovalDao.upDateStatus(approvalHistory.getCustomerId(), NO_ACTION, currentLevel);	
		  }
		}
		else if(approvalHistory.getStatus().equals(REJECTED))
		{
			if(ltCustomerApprovalDao.updateStatusApproval(approvalHistory)) 
			{
				if(ltCustomerApprovalDao.submitForApproval(null,approvalHistory.getCustomerId(),approvalHistory.getStatus(),null))
				{
					LtMastCustomer ltMastCustomer= ltMastCustomerInfoDao.getCustomerById(approvalHistory.getCustomerId());
					if(ltMastCustomer !=null)
					{
						List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(ltMastCustomer.getInitiatorId());
							LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
							ltMastEmailtoken.setEmailObject(
									"#$#$customerName="+ltMastCustomer.getCustomerName()+" ( "+ltMastCustomer.getCustomerNumber()+" )"+
									"#$#$approverName="+ltMastEmployees.get(0).getEmpName()+
									"#$#$name="+ltMastCustomer.getCustomerName()+
									"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
							ltMastEmailtoken.setSendTo(ltMastEmployees.get(0).getOfficialEmail());
							ltMastEmailtoken.setEmailStatus("SENDING");
							ltMastEmailtoken.setEmailTitle("Request to register for "+ltMastCustomer.getCustomerName()+" ( "+ltMastCustomer.getCustomerNumber()+" )"+
									" has been rejected");
							ltMastEmailtoken.setEmailTemplate("customerReject");
							ltMastEmailtoken.setExpiredWithin(1296000L);
							ltMastEmailtoken.setSendDate(new Date());
							ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(approvalHistory.getEmployeeId());
							ltMastEmailtoken.setSendCc(ltMastEmployees.get(0).getOfficialEmail());
							ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
//							status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_REJECTED);
							try {
								status.setCode(1);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_REJECTED").getMessageName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					ltCustomerApprovalDao.upDateStatus(approvalHistory.getCustomerId(), NO_ACTION, null);
			
				}
			}
		}
		else if(approvalHistory.getStatus().equals(WITHDRAW))
		{
			if(ltCustomerApprovalDao.updateVendorStatusApproval(approvalHistory)) 
			{
				approvalHistory.setStatus(WITHDRAW);
				if(ltCustomerApprovalDao.submitForApproval(null,approvalHistory.getCustomerId(),approvalHistory.getStatus(),null))
				{
					LtMastCustomer ltMastCustomer= ltMastCustomerInfoDao.getCustomerById(approvalHistory.getCustomerId());
					if(ltMastCustomer !=null)
					{
							String empName = null;
							LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
							List<LtMastEmployees> ltMastEmployees = null;
							if(approvalHistory.getEmployeeId()!=null) {
							ltMastEmployees = ltMastEmployeesDao.getByEmpIdForEmail(approvalHistory.getEmployeeId());
							empName  = ltMastEmployees.get(0).getEmpName(); 
							}else {
								empName = ltMastCustomer.getCustomerName()+" ( "+ltMastCustomer.getCustomerNumber()+" )";
								approvalHistory.setUserType("CUSTOMER");
							}
							ltMastEmailtoken.setEmailObject(
									"#$#$customerName="+ltMastCustomer.getCustomerName()+" ( "+ltMastCustomer.getCustomerNumber()+" )"+
									"#$#$approverName="+empName+
									"#$#$name="+ltMastCustomer.getCustomerName()+
									"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
							ltMastEmailtoken.setSendTo(ltMastEmployees.get(0).getOfficialEmail());
							ltMastEmailtoken.setEmailStatus("SENDING");
							ltMastEmailtoken.setEmailTitle("Request to register for "+ltMastCustomer.getCustomerName()+" ( "+ltMastCustomer.getCustomerNumber()+" )"+
									" has been withdraw.");
							ltMastEmailtoken.setEmailTemplate("customerWithdraw");
							ltMastEmailtoken.setExpiredWithin(1296000L);
							ltMastEmailtoken.setSendDate(new Date());
							ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
							//----------------------------- maill to pending approval-----------
							List<LtCustomerApproval> ltExpenseApprovalList = 
									ltCustomerApprovalDao.chkCurrentApprover(approvalHistory.getCustomerId());
							if(!ltExpenseApprovalList.isEmpty()){
								for(LtCustomerApproval ltExpenseApproval : ltExpenseApprovalList){
									LtMastEmployees ltMastEmployees1 = ltMastEmployeesDao.getLtMastEmployeesByID(ltExpenseApproval.getApprovalId());
									ltMastEmailtoken.setEmailTokenId(null);
									ltMastEmailtoken.setSendTo(ltMastEmployees1.getOfficialEmail());
									ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
								}
							}
//					status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_WITHDRAW);
							try {
								status.setCode(1);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_WITHDRAW").getMessageName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					ltCustomerApprovalDao.upDateStatus(approvalHistory.getCustomerId(), NO_ACTION, null);
				}
			}
		} 
		}
		ltCustomerApprovalHistoryDao.save(approvalHistory);
		return status;
	
	}

	@Override
	public LtCustomerApproval getCustomerApproval(Long customerId, Long appId) throws ServiceException {
		LtCustomerApproval ltVendorApproval= ltCustomerApprovalDao.getCustomerApproval(customerId, appId);
		return ltVendorApproval;
	}

	@Override
	public List<LtCustomerApproval> getCustomerApprovalByCustomerId(Long customerId) throws ServiceException {
		return ltCustomerApprovalDao.getCustomerApprovalByCustomerId(customerId);
	}

	@Override
	public String checkforApprovals(Long customerId) throws ServiceException {
		if( ltCustomerApprovalDao.chkForApprovers(customerId) ) {
			return "present"; 
		}else {
			return "null";
		}
	}

	@Override
	public Status submitForApproval(Date date, Long customerId, String state, Object object)
			throws ServiceException {
		Status status = new Status();
		LtMastCustomer ltMastCustomer = ltMastCustomerInfoDao.getCustomerById(customerId);
		if(ltMastCustomer.getStatus().equals("ACTIVE"))
		{
			ltCustomerApprovalDao.upDateStatus(customerId, NO_ACTION, null);
		}
		if(!ltCustomerApprovalDao.chkForApprovers(customerId)) {
		if(!ltCustomerApprovalDao.loadApprovers(customerId,ltMastCustomer.getCompanyId())){
		
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		}
		
		ProcedureCall procedureCall = ltCustomerApprovalDao.customerValidationPackage(customerId);
		status.setData(procedureCall.getCustomerId());
		if(procedureCall.getStatusCode().equals("SUCCESS")) {
		status.setCode(1);
		status.setMessage(procedureCall.getStatusMessage());
		}else {
			status.setCode(0);
			status.setMessage(procedureCall.getStatusMessage());
			return status;
		}
		
		
		if(ltCustomerApprovalDao.submitForApproval(date,customerId,state,new Date())) {
//			status=ltMastCommonMessageService.getCodeAndMessage(CUSTOMER_SUBMIT_FOR_APPROVAL);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("CUSTOMER_SUBMIT_FOR_APPROVAL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return status;
	}

	@Override
	public Status saveBulk(InboxBulkInput inboxBulkInput) throws ServiceException {
		Status status = new Status();
		List<String> responseMessage = new ArrayList<String>();
		List<BulkIdWithName> expenseIdList = inboxBulkInput.getExpenseList();

		LtCustomerApprovalHistory ltCustomerApprovalHistory = new LtCustomerApprovalHistory();
		for (BulkIdWithName bulkIdWithName : expenseIdList) {
			ltCustomerApprovalHistory.setLastUpdateDate(new Date());
			ltCustomerApprovalHistory.setStatus(inboxBulkInput.getAction());
			ltCustomerApprovalHistory.setCustomerApprovalId(bulkIdWithName.getCustomerApprovalId());
			ltCustomerApprovalHistory.setCustomerId(bulkIdWithName.getCustomerId());
			ltCustomerApprovalHistory.setRemark(inboxBulkInput.getNote());
			ltCustomerApprovalHistory.setEmployeeId(inboxBulkInput.getApprovalId());

			if (inboxBulkInput.getAction().equals(APPROVED)) {
				ltCustomerApprovalHistory.setStatus(APPROVED);
			} else if (inboxBulkInput.getAction().equals(REJECTED)) {
				ltCustomerApprovalHistory.setStatus(REJECTED);
			} else if (inboxBulkInput.getAction().equals(FEEDBACK_AWAITED)) {
				ltCustomerApprovalHistory.setStatus(FEEDBACK_AWAITED);
			}
			LtMastCustomer ltMastCustomer= ltMastCustomerInfoDao.getCustomerById(ltCustomerApprovalHistory.getCustomerId());
			status = updateStatusApproval(ltCustomerApprovalHistory);
			responseMessage.add(ltMastCustomer.getCustomerName() + " " + status.getMessage());
		}
		status.setData(responseMessage);
		return status;

	}

}
