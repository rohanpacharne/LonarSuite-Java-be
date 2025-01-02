package com.lonar.vendor.vendorportal.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.dao.LtExpExpenseHeadersDao;
import com.lonar.vendor.vendorportal.dao.LtExpenseApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtExpenseApprovalHistoryDao;
import com.lonar.vendor.vendorportal.dao.LtExpenseModuleApprovalsDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmailtokenDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtExpenseApprovalServiceImpl implements LtExpenseApprovalService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtExpenseApprovalDao ltExpenseApprovalDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtExpExpenseHeadersDao ltExpExpenseHeadersDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtExpenseModuleApprovalsDao ltExpModuleApprovalsDao;
	
	@Autowired
	LtMastEmailtokenDao ltMastEmailtokenDao;
	
	@Autowired
	LtExpenseApprovalHistoryDao ltExpenseApprovalHistoryDao;
	
//	@Transactional
//	@Override
//	public List<LtExpenseApproval> getAllExpenseApproval() throws Exception {
//		List<LtExpenseApproval> ltExpenseApprovalList = new ArrayList<LtExpenseApproval>();
//		
//		ltExpenseApprovalList = ltExpenseApprovalDao.getAllExpenseApproval();
// 
//		return ltExpenseApprovalList;
//	}
	
	
	@Transactional
	@Override
	public List<LtExpenseApproval> getExpenseApprovalByExpHeaderId(Long expenseHeaderId) throws Exception {
 
		List<LtExpenseApproval> ltExpenseApprovalList = new ArrayList<LtExpenseApproval>();
 
		ltExpenseApprovalList = ltExpenseApprovalDao.getExpenseApprovalByExpHeaderId(expenseHeaderId);
		
		/*for (LtExpenseApproval ltExpenseApproval : ltExpenseApprovalList)
		{
			if(ltExpenseApproval.getApprovalLevel()!=null){
					if(!ltExpenseApproval.getApprovalLevel().equals("00"))
					{
						String levelName=ltExpModuleApprovalsDao.getByApprovalLevelAndModuleId(ltExpenseApproval.getApprovalLevel(),ltExpenseApproval.getModuleApprovalId());
						
						if(!levelName.isEmpty()&& levelName!=null && !ltExpenseApproval.getApprovalLevel().equals("00"))
						{
							ltExpenseApproval.setApprovalLevelName(levelName);
						}
					}
					else
					{
						ltExpenseApproval.setApprovalLevelName(SUPERVISOR);
					}
			}
 
		}*/
 
		return ltExpenseApprovalList;
	}
	
	@Override
	public String checkforApprovals(Long expHeaderId) throws Exception
	{
		String level = ltExpenseApprovalDao.checkforApprovals(expHeaderId);
		if(level!=null)
		return "Please add an approver for "+level+" , it is required.";
		else
			return null;
		
	}

	@Transactional
	@Override
	public Status submitForApproval(Date submissionDate,Long expHeaderId,String stat,Date approvedDate) throws Exception
	{
		Status status=new Status();
		if( ltExpenseApprovalDao.submitForApproval(submissionDate,expHeaderId,stat,approvedDate))
		{
			LtExpExpenseHeaders ltExpExpenseHeaders=getdetails(expHeaderId);
			if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
					ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE) )
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(SUBMIT_FOR_APPROVAL);
				status.setCode(1);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("SUBMIT_FOR_APPROVAL").getMessageName());
				if(status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action was successful");
				}
			}
			else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_SUBMIT_FOR_APPROVAL);
				status.setCode(1);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_SUBMIT_FOR_APPROVAL").getMessageName());

				if(status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action was successful");
				}
			}
			
		}
		else
		{
			status.setCode(0);
			status.setMessage("Error in finding message! The action was unsuccessful");
		}
		return status;
	}
	
	@Transactional
	public LtExpExpenseHeaders getdetails(Long expenseHeaderId) throws Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		
		LtExpExpenseHeaders ltExpExpenseHeaders=ltExpExpenseHeadersDao.findOne(expenseHeaderId);
		
		ltExpExpenseHeaders.setStDate(formatter.format(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ss").parse(ltExpExpenseHeaders.getStartDate().toString())));
		String endDate = null;
		if(ltExpExpenseHeaders.getEndDate()!=null)
		{
			endDate =formatter.format(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ss").parse(ltExpExpenseHeaders.getEndDate().toString()));
		}
		else
		{
			endDate="-";
		}
		ltExpExpenseHeaders.setEnDate(endDate);	
		
		
		List<LtMastEmployees> employeesList=ltMastEmployeesDao.
				getEmployeeDetailsByUserID(ltExpExpenseHeaders.getEmployeeId());
		
		if(!employeesList.isEmpty())
		{
			ltExpExpenseHeaders.setEmail(employeesList.get(0).getOfficialEmail());
			//ltExpExpenseHeaders.setDivisionName(employeesList.get(0).getDivisionName());
			//ltExpExpenseHeaders.setLocationName(employeesList.get(0).getLocationName());
			//ltExpExpenseHeaders.setCostCenterName(employeesList.get(0).getCostCenterName());
			//ltExpExpenseHeaders.setEmployeeName(employeesList.get(0).getTitle()+" "+employeesList.get(0).getFirstName()+" "+employeesList.get(0).getLastName());
		}
	
		
		return ltExpExpenseHeaders;
	}
	
	
	@Transactional
	@Override
	public Status updateStatusApproval(LtExpenseApprovalHistory approvalHistory) throws Exception 
	{
		Status status=new Status();
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		approvalHistory.setLastUpdateDate(new Date());
		if(approvalHistory.getStatus().equals(APPROVED))
		{
			LtExpExpenseHeaders ltExpExpenseHeaders=getdetails(approvalHistory.getExpenseHeaderId());
			if(ltExpenseApprovalDao.updateStatusApproval(approvalHistory) )
			{
				System.out.println("in updateStatusApproval...");
				if(ltExpModuleApprovalsDao.chkIsAprovedByAnyOne(approvalHistory))
				{
					System.out.println("in chkIsAprovedByAnyOne...");
					if(ltExpenseApprovalDao.updateAllStatusApproval(approvalHistory))
					{
						System.out.println("in updateAllStatusApproval");
						if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
								ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_APPROVED_REVISED);
							status.setCode(1);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_APPROVED").getMessageName());
							if(status.getMessage()==null)
							{
								status.setCode(1);
								status.setMessage("Error in finding message! The action was successful");
							}
						}
						else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_APPROVED);
							status.setCode(1);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_APPROVED").getMessageName());
							if(status.getMessage()==null)
							{
								status.setCode(1);
								status.setMessage("Error in finding message! The action was successful");
							}
						}
					}
					else
					{
						if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
								ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_APPROVAL_FAIL);
							status.setCode(0);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_APPROVAL_FAIL").getMessageName());
							if(status.getMessage()==null)
							{
								status.setCode(0);
								status.setMessage("Error in finding message! The action was successful");
							}
						}
						else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_APPROVAL_FAIL);
							status.setCode(0);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_APPROVAL_FAIL").getMessageName());

							if(status.getMessage()==null)
							{
								status.setCode(0);
								status.setMessage("Error in finding message! The action was successful");
							}
						}
						
					}	
				}
				if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
						ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_APPROVED_REVISED);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_APPROVED").getMessageName());
					if(status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action was successful");
					}
				}
				else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_APPROVED);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_APPROVED").getMessageName());
					if(status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action was successful");
					}
				}
				
			}
		}
		
		else if(approvalHistory.getStatus().equals(FEEDBACK_AWAITED) )
		{
			System.out.println("in feeddback");
			if(ltExpenseApprovalDao.updateStatusApproval(approvalHistory))
			{
				ltExpenseApprovalDao.submitForApproval(null,approvalHistory.getExpenseHeaderId(),approvalHistory.getStatus(),null);
				
				LtExpExpenseHeaders ltExpExpenseHeaders=getdetails(approvalHistory.getExpenseHeaderId());
				if(ltExpExpenseHeaders !=null)
				{			
						LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
						ltMastEmailtoken.setEmailObject("ExpNo="+ltExpExpenseHeaders.getExpenseNumber()+
								"#$#$Amount="+ltExpExpenseHeaders.getExpenseAmount()+
								"#$#$Amount="+twoDForm.format(ltExpExpenseHeaders.getExpenseAmount())+
								"#$#$EmpName="+ltExpExpenseHeaders.getEmployeeName()+
								"#$#$DivName="+ltExpExpenseHeaders.getDivisionName()+
								"#$#$CostCen="+ltExpExpenseHeaders.getCostCenterName()+
								"#$#$LocationName="+ltExpExpenseHeaders.getLocationName()+
								"#$#$Purpose="+ltExpExpenseHeaders.getPurpose()+
								"#$#$StartDate="+ltExpExpenseHeaders.getStDate()+
								"#$#$EndDate="+ltExpExpenseHeaders.getEnDate()+
								"#$#$LoginURL="+env.getProperty("LOGIN_URL"));						
						ltMastEmailtoken.setSendTo(ltExpExpenseHeaders.getEmail());
						ltMastEmailtoken.setEmailStatus("SENDING");
						ltMastEmailtoken.setEmailTitle("EXPENSE FEEDBACK MAIL");
						if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
						{
							ltMastEmailtoken.setEmailTemplate("eAdvanceFeedBack");
						}
						else
						{
							ltMastEmailtoken.setEmailTemplate("eExpenseFeedBack");
						}
						ltMastEmailtoken.setExpiredWithin(1296000L);
						ltMastEmailtoken.setSendDate(new Date());
					
					ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
					
					if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
							ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_SENT_FOR_FEEDBACK_REVISED);
						status.setCode(1);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_SENT_FOR_FEEDBACK").getMessageName());

						if(status.getMessage()==null) 
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action was successful");
						}
					}
					else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_SENT_FOR_FEEDBACK);
						status.setCode(1);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_SENT_FOR_FEEDBACK").getMessageName());
						if(status.getMessage()==null)
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action was successful");
						}
					}
			}
				String currentLevel=null;
				System.out.println("approvalHistory ==="+approvalHistory);
				if(approvalHistory.getExpenseApprovalId()!=null	)
				{
					currentLevel=ltExpenseApprovalDao.getCurrLevelByexpenseApprovalId(approvalHistory.getExpenseApprovalId());
				}
			ltExpExpenseHeadersDao.upDateStatus(approvalHistory.getExpenseHeaderId(), NO_ACTION, currentLevel);	
		  }
		}
			
			
		else if(approvalHistory.getStatus().equals(REJECTED))
		{
			if(ltExpenseApprovalDao.updateStatusApproval(approvalHistory)) 
			{
				if(ltExpenseApprovalDao.submitForApproval(null,approvalHistory.getExpenseHeaderId(),approvalHistory.getStatus(),null))
				{
					LtExpExpenseHeaders ltExpExpenseHeaders= getdetails(approvalHistory.getExpenseHeaderId());
					if(ltExpExpenseHeaders !=null)
					{
							LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
							ltMastEmailtoken.setEmailObject("ExpNo="+ltExpExpenseHeaders.getExpenseNumber()+
									"#$#$Amount="+ltExpExpenseHeaders.getExpenseAmount()+
									"#$#$Amount="+twoDForm.format(ltExpExpenseHeaders.getExpenseAmount())+
									"#$#$EmpName="+ltExpExpenseHeaders.getEmployeeName()+
									"#$#$DivName="+ltExpExpenseHeaders.getDivisionName()+
									"#$#$CostCen="+ltExpExpenseHeaders.getCostCenterName()+
									"#$#$LocationName="+ltExpExpenseHeaders.getLocationName()+
									"#$#$Purpose="+ltExpExpenseHeaders.getPurpose()+
									"#$#$StartDate="+ltExpExpenseHeaders.getStDate()+
									"#$#$EndDate="+ltExpExpenseHeaders.getEnDate()+
									"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
							ltMastEmailtoken.setSendTo(ltExpExpenseHeaders.getEmail());
							ltMastEmailtoken.setEmailStatus("SENDING");
							ltMastEmailtoken.setEmailTitle("EXPENSE REJECT MAIL");
							if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
							{
								ltMastEmailtoken.setEmailTemplate("eAdvanceReject");
							}
							else
							{
								ltMastEmailtoken.setEmailTemplate("eExpenseReject");
							}
							ltMastEmailtoken.setExpiredWithin(1296000L);
							ltMastEmailtoken.setSendDate(new Date());
						
						
							ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
							if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
									ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
							{
//								status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_REJECTED_REVISED);
								status.setCode(1);		
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_REJECTED").getMessageName());

								if(status.getMessage()==null)
								{
									status.setCode(1);
									status.setMessage("Error in finding message! The action was successful");
								}
							}
							else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
							{
//								status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_REJECTED);
								status.setCode(1);		
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_REJECTED").getMessageName());
								if(status.getMessage()==null)
								{
									status.setCode(1);
									status.setMessage("Error in finding message! The action was successful");
								}
							}
					}
					ltExpExpenseHeadersDao.upDateStatus(approvalHistory.getExpenseHeaderId(), NO_ACTION, null);
			
				}
			}
		} 
	//--------------------------------------for lonar ------------------------------------------
		else if(approvalHistory.getStatus().equals(SELF_REJECTED))
		{
			if(ltExpenseApprovalDao.updateStatusApproval(approvalHistory)) 
			{
				approvalHistory.setStatus(REJECTED);
				if(ltExpenseApprovalDao.submitForApproval(null,approvalHistory.getExpenseHeaderId(),approvalHistory.getStatus(),null))
				{
					LtExpExpenseHeaders ltExpExpenseHeaders= getdetails(approvalHistory.getExpenseHeaderId());
					if(ltExpExpenseHeaders !=null)
					{
							LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
					
							ltMastEmailtoken.setEmailObject("ExpNo="+ltExpExpenseHeaders.getExpenseNumber()+
									"#$#$Amount="+ltExpExpenseHeaders.getExpenseAmount()+
									"#$#$Amount="+twoDForm.format(ltExpExpenseHeaders.getExpenseAmount())+
									"#$#$EmpName="+ltExpExpenseHeaders.getEmployeeName()+
									"#$#$DivName="+ltExpExpenseHeaders.getDivisionName()+
									"#$#$CostCen="+ltExpExpenseHeaders.getCostCenterName()+
									"#$#$LocationName="+ltExpExpenseHeaders.getLocationName()+
									"#$#$Purpose="+ltExpExpenseHeaders.getPurpose()+
									"#$#$StartDate="+ltExpExpenseHeaders.getStDate()+
									"#$#$EndDate="+ltExpExpenseHeaders.getEnDate()+
									"#$#$LoginURL="+env.getProperty("LOGIN_URL"));	
							ltMastEmailtoken.setSendTo(ltExpExpenseHeaders.getEmail());
							ltMastEmailtoken.setEmailStatus("SENDING");
							ltMastEmailtoken.setEmailTitle("EXPENSE SELF REJECTION MAIL");
							if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
							{
								ltMastEmailtoken.setEmailTemplate("eAdvanceReject");
							}
							else
							{
								ltMastEmailtoken.setEmailTemplate("eExpenseReject");
							}
							ltMastEmailtoken.setExpiredWithin(1296000L);
							ltMastEmailtoken.setSendDate(new Date());
						
						
							ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
							//----------------------------- maill to pending approval-----------
							List<LtExpenseApproval> ltExpenseApprovalList = 
									ltExpenseApprovalDao.chkCurrentApprover(ltExpExpenseHeaders.getExpHeaderId());
							if(!ltExpenseApprovalList.isEmpty()){
								for(LtExpenseApproval ltExpenseApproval : ltExpenseApprovalList){
									LtMastEmployees ltMastEmployees = ltMastEmployeesDao.getLtMastEmployeesByID(ltExpenseApproval.getApprovalId());
									ltMastEmailtoken.setEmailTokenId(null);
									ltMastEmailtoken.setSendTo(ltMastEmployees.getOfficialEmail());
									ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
								}
							}
							
							//--------------------------------------------------
							
							if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
									ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
							{
//								status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_REJECTED_REVISED);
								status.setCode(1);		
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_REJECTED").getMessageName());

								if(status.getMessage()==null)
								{
									status.setCode(1);
									status.setMessage("Error in finding message! The action was successful");
								}
							}
							else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
							{
//								status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_REJECTED);
								status.setCode(1);		
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_REJECTED").getMessageName());

								if(status.getMessage()==null)
								{
									status.setCode(1);
									status.setMessage("Error in finding message! The action was successful");
								}
							}
					}
					ltExpExpenseHeadersDao.upDateStatus(approvalHistory.getExpenseHeaderId(), NO_ACTION, null);
			
				}
			}
		} 
		
		//--------------------------------------------------------------------------------------
		
		else if(approvalHistory.getStatus().equals(PTO))
		{
			LtExpExpenseHeaders ltExpExpenseHeaders= getdetails(approvalHistory.getExpenseHeaderId());
			//if(ltExpenseApprovalDao.callProceduere(ltExpExpenseHeaders))
			{
				if(ltExpenseApprovalDao.updateStatusApproval(approvalHistory) )
				{
					if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
									ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(EXPENSE_SENT_TO_ORACLE);
						status.setCode(1);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("EXPENSE_SENT_TO_ORACLE").getMessageName());
						if(status.getMessage()==null)
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action was successful");
						}
					}
					else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(ADVANCE_SENT_TO_ORACLE);
						status.setCode(1);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ADVANCE_SENT_TO_ORACLE").getMessageName());

						if(status.getMessage()==null)
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action was successful");
						}
					}
				}
			}
			/*else
			{
				status.setCode(EXCEPTION);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}*/
			
		} 
		
		
		ltExpenseApprovalHistoryDao.save(approvalHistory);
		
		return status;
	}
	
	@Transactional
	@Override
	public LtExpenseApproval getExpenseApproval(Long expId, Long apprId) throws Exception {
		LtExpenseApproval ltExpenseApproval= ltExpenseApprovalDao.getExpenseApproval(expId, apprId);
		return ltExpenseApproval;
	}
}
