package com.lonar.vendor.vendorportal.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.dao.LtExpExpenseHeadersDao;
import com.lonar.vendor.vendorportal.dao.LtExpExpenseLinesDao;
import com.lonar.vendor.vendorportal.dao.LtExpMileageExpenseLinesDao;
import com.lonar.vendor.vendorportal.dao.LtExpenseApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtMastDivisionsDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpExpenseLines;
import com.lonar.vendor.vendorportal.model.LtExpMileageExpenseLines;
import com.lonar.vendor.vendorportal.model.LtMastDivisions;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtExpExpenseHeadersServiceImpl implements LtExpExpenseHeadersService{
	
	Status status=new Status();
	@Autowired
	LtExpExpenseHeadersDao ltExpExpenseHeadersDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtMastDivisionsDao ltMastDivisionsDao;
	
	@Autowired
	LtExpenseApprovalDao ltExpenseApprovalDao;
	
	@Autowired
	LtExpExpenseLinesDao ltExpExpenseLinesDao;
	
	@Autowired
	LtExpMileageExpenseLinesDao ltExpMileageExpenseLinesDao;
	
	@Transactional
	@Override
	public Long getCount(LtExpExpenseHeaders input,Long empId,String category,String role) throws Exception
	{
		if(role.toUpperCase().equals(MY))
		{
			return ltExpExpenseHeadersDao.getCount(input,empId,category);
		}
		else if(role.toUpperCase().equals(EMP))
		{
			return ltExpExpenseHeadersDao.getEmployeeDataTableCount(input,empId,category);
		}
		else
		{
			return ltExpExpenseHeadersDao.getAllDataTableCount(input,empId,category);
		}
	}
	
	@Transactional
	@Override
	public List<LtExpExpenseHeaders> getExpenseRecords(LtExpExpenseHeaders input,Long empId,String category,String role) throws Exception
	{
		List<LtExpExpenseHeaders> ltExpExpenseHeadersList = null;
		if(role.toUpperCase().equals(MY))
		{
		System.out.println("input.getColumnNo() = "+input.getColumnNo());
		System.out.println("input.getSort() = "+input.getSort());
		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
		{ input.setColumnNo(11); }
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{ input.setColumnNo(12); }
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{ input.setColumnNo(13); }
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{ input.setColumnNo(14); }
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{ input.setColumnNo(25); }
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{ input.setColumnNo(16); }
		if(input.getColumnNo()==15 && input.getSort().equals("desc"))
		{ input.setColumnNo(35); }
		ltExpExpenseHeadersList= ltExpExpenseHeadersDao.getExpenseRecords(input,empId,category);
		}
		else if(role.toUpperCase().equals(EMP))
		{
		ltExpExpenseHeadersList=
					ltExpExpenseHeadersDao.getEmployeeDataTableExpenseRecords(input,empId,category);
		}
		else
		{
			ltExpExpenseHeadersList=
					ltExpExpenseHeadersDao.getAllExpenseRecords(input,empId,category);
		
		}
		return ltExpExpenseHeadersList;
	}

	@Transactional
	@Override
	public Status save(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception
	{
		if(ltExpExpenseHeaders.getLastUpdateLogin()==null  || ltExpExpenseHeaders.getEmployeeId()==null
		|| ltExpExpenseHeaders.getStatus()==null || ltExpExpenseHeaders.getCreationDate()==null)
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
			
		}
		else
		{
			System.out.println("in else "+ltExpExpenseHeaders);
			ltExpExpenseHeaders.setInitiatorId(ltExpExpenseHeaders.getEmployeeId());
			ltExpExpenseHeaders.setCreatedBy(ltExpExpenseHeaders.getLastUpdateLogin());
			ltExpExpenseHeaders.setLastUpdateDate(new Date());
			ltExpExpenseHeaders.setLastUpdatedBy(ltExpExpenseHeaders.getLastUpdateLogin());
			ltExpExpenseHeaders.setCreationDate(new Date());
			ltExpExpenseHeaders.setLastUpdateDate(new Date());
			
			System.out.println("Above save");
			Long expId=ltExpExpenseHeadersDao.save(ltExpExpenseHeaders);
			System.out.println("Below save");
			ltExpExpenseHeaders.setExpHeaderId(expId);
				
			String expenceNumber=null;
			//------------------------------------- 16-5-2018
			List<LtMastEmployees> list = ltMastEmployeesDao.getByEmpIdV1(ltExpExpenseHeaders.getEmployeeId());
			System.out.println("list "+list);
			if(!list.isEmpty()|| list.size()>0)
			{
				
				LtMastEmployees ltMastEmployees=list.get(0);
				
				List<LtMastDivisions> ltMastDivisionslist=ltMastDivisionsDao.findByDivisionId(ltExpExpenseHeaders.getDivisionId());
				if(!ltMastDivisionslist.isEmpty()|| ltMastDivisionslist.size()>0)
				{
					LtMastDivisions divisions=ltMastDivisionslist.get(0);
					if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)
							||ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
					{
					expenceNumber="EXP/"+ltMastEmployees.getEmployeeNumber()+"/"+divisions.getDivisionCode()+
						"/"+ltExpExpenseHeaders.getExpHeaderId()+"/"+getFinancialYear();
					}
					else if(ltExpExpenseHeaders.getExpenseCategory().equals(ADVANCE))
					{
						expenceNumber="ADV/"+ltMastEmployees.getEmployeeNumber()+"/"+divisions.getDivisionCode()+
							"/"+ltExpExpenseHeaders.getExpHeaderId()+"/"+getFinancialYear();
					}
				}
			}
				if(expenceNumber!=null)
				{
					
					ltExpExpenseHeaders.setExpenseNumber(expenceNumber);
					ltExpExpenseHeaders.setLastUpdateDate(new Date());
					
					if(ltExpExpenseHeadersDao.update(ltExpExpenseHeaders))
					{
					System.out.println("loading approvers-----------------------");
						//if(ltExpenseApprovalDao.loadApprovers(ltExpExpenseHeaders)){
						
//						status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
						if( status.getMessage()==null)
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}
						//status.setData(ltExpExpenseHeaders);as per discussion wth gautam
						status.setData(ltExpExpenseHeaders.getExpHeaderId());
						//}
//						else
//						{
//							status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
//							if(status.getMessage()==null)
//							{
//								status.setCode(EXCEPTION);
//								status.setMessage("Error in finding message! The action was unsuccessful");
//							}
//						}
					}	
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
						status.setCode(0);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
						if(status.getMessage()==null)
						{
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}
					}
				
				}
				
		}
	
		return status;
	}
	
	public static String getFinancialYear() throws Exception
	{
		String financialYear;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
	    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
	    if (month < 3)
	    {
	    	financialYear=(year - 1) + "-" + year;       
	    }
	    else
	    {
	    	financialYear=year + "-" + (year + 1);
	    }
	    return financialYear;
	}
	
	@Transactional
	@Override
	public LtExpExpenseHeaders findOne(Long headerId) throws Exception 
	{
		LtExpExpenseHeaders ltExpExpenseHeaders=ltExpExpenseHeadersDao.findOne(headerId);
		return ltExpExpenseHeaders;
	}
	
	@Transactional
	@Override
	public Status delete(Long expHeaderId) throws Exception
	{
		Status status=new Status();
		
		LtExpExpenseHeaders ltExpExpenseHeaders=ltExpExpenseHeadersDao.findOne(expHeaderId);
		if(ltExpExpenseHeaders!=null)
		{
			if(ltExpExpenseHeaders.getStatus().equals(INPROGRESS) || ltExpExpenseHeaders.getStatus().equals(APPROVED)
					|| ltExpExpenseHeaders.getStatus().equals(FEEDBACK_AWAITED) || ltExpExpenseHeaders.getStatus().equals(REJECTED) )
			{
				if(ltExpExpenseHeaders.getExpenseCategory().equals(EXPENSE)||
						ltExpExpenseHeaders.getExpenseCategory().equals(MILEAGE))
				{
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
					status.setMessage("Expenses which are in draft status can only be deleted.");
				}
				else
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
					status.setMessage("TRF/Advances which are in draft status can only be deleted.");
				}
				return status;
			}
			else
			{
				List<LtExpExpenseLines> expLineList=ltExpExpenseLinesDao.getByExpenseHeaderId(expHeaderId);
				if(expLineList.size()>0 || !expLineList.isEmpty())
				{
					for(LtExpExpenseLines expenseLines:expLineList)
					{
						if(ltExpExpenseLinesDao.delete(expenseLines.getExpLineId(),ltExpExpenseHeaders.getExpenseCategory())==false)
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
							status.setCode(0);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
							if(status.getMessage()==null)
							{
								status.setCode(0);
								status.setMessage("Error in finding message! The action was unsuccessful");
							}
							return status;
						}
					}
					if(ltExpExpenseHeadersDao.delete(expHeaderId))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
						status.setCode(1);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
						if( status.getMessage()==null)
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
						status.setCode(0);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
						if(status.getMessage()==null)
						{
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}
					}
					return status;
					
				}
				List<LtExpMileageExpenseLines> expMilLines =ltExpMileageExpenseLinesDao.getExpMileageLinesByExpHeaderId(expHeaderId);
				if(!expMilLines.isEmpty())
				{
					if(ltExpMileageExpenseLinesDao.deleteByExpHeaderId(expHeaderId))
					{
						if(ltExpExpenseHeadersDao.delete(expHeaderId))
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
							try {
								status.setCode(1);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
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
//							status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
							try {
								status.setCode(0);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
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
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
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
					return status;
				}
				if(ltExpExpenseHeadersDao.delete(expHeaderId))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
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
//					status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
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
				return status;
				
			}
			
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_NOT_FOUND);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_NOT_FOUND").getMessageName());
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
	
	return status;
	}
	
	@Override
	public LtExpExpenseHeaders getExpenseStatusById(Long expenseHeaderId) throws ServiceException {
		return ltExpExpenseHeadersDao.getExpenseStatusById(expenseHeaderId);
	}
	

	@Override
	public boolean checkStatusIsPending(Long expHeaderId, Long approvalId) throws ServiceException {
		return ltExpExpenseHeadersDao.checkStatusIsPending(expHeaderId, approvalId);
	}
	
	@Override
	public Status deleteExpenseHeaderAttachments(Long expAttachId) {
		// TODO Auto-generated method stub
		Status status = new Status();
		System.out.println("expLineId = "+expAttachId);
		try {
			if(ltExpExpenseHeadersDao.deleteExpenseHeaderAttachments(expAttachId)) {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_SUCCESS").getMessageName());
				status.setData(null);
			}
			else {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_FAIL").getMessageName());
				status.setData(null);
			}
		}catch(Exception ex) {
			
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
				status.setData(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return status;
	}

}
