package com.lonar.vendor.vendorportal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.dao.LtMastExpenseTypesDao;
import com.lonar.vendor.vendorportal.model.LtMastExpenseTypes;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtMastExpenseTypeServiceImpl implements LtMastExpenseTypeService {

	@Autowired
	LtMastExpenseTypesDao ltMastExpenseTypeDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	
	@Override
	public List<LtMastExpenseTypes> getAllActiveExpenseTypes() throws Exception {
		List<LtMastExpenseTypes> expenseTypeList=new ArrayList<LtMastExpenseTypes>();
		
		expenseTypeList=ltMastExpenseTypeDao.getAllActiveExpenseType();
		
		return expenseTypeList;
	}
	
	@Override
	public Long getExpenseTypesCount(LtMastExpenseTypes input,Long companyId) throws Exception
	{
		// TODO Auto-generated method stub
		return ltMastExpenseTypeDao.getExpenseTypesCount(input,companyId);
	}
 
 
	@Override
	public List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input,Long companyId) throws Exception
	{
		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
		{ input.setColumnNo(11); }
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{ input.setColumnNo(12); }
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{ input.setColumnNo(13); }
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{ input.setColumnNo(14); }
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{ input.setColumnNo(15); }
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{ input.setColumnNo(16); }
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{ input.setColumnNo(17); }
		if(input.getColumnNo()==8 && input.getSort().equals("desc"))
		{ input.setColumnNo(18); }
		return ltMastExpenseTypeDao.getExpenseTypesData(input,companyId);
	}
	
	@Transactional
	@Override
	public Status saveExpense(LtMastExpenseTypes ltMastExpenseTypes) throws Exception {
		
		Status status=new Status();
		String chknull=checkNull(ltMastExpenseTypes);
		//System.out.println("chknull = "+chknull);
		if(chknull.equals("null"))
		{
			//System.out.println("in if chknull");
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			//System.out.println("below in status of if");
			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		else
		{
			//System.out.println("in else");
			String chkDuplicate=checkDuplicate(ltMastExpenseTypes);
			//System.out.println("chkDuplicate = "+chkDuplicate);
			if(chkDuplicate.equals("null"))
			{
				//System.out.println("in if");
				ltMastExpenseTypes.setCreationDate(new Date());
				ltMastExpenseTypes.setLastUpdateDate(new Date());
				if(ltMastExpenseTypeDao.saveExpense(ltMastExpenseTypes))
				{
					//System.out.println("in if of saveExpense");
//					status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
					//System.out.println("below if of saveExpense");
					//System.out.println("Status = "+status);
					if( status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action is completed successfully.");
					}
				//status.setData(ltMastExpenseTypeDao.getExpenseTypeById(expId));
				}
				else
				{
					//System.out.println("in else");
//					status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
					status.setCode(0);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
					if(status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("Error in finding message! The action was unsuccessful");
					}
				}
			}
			else
			{
				status.setCode(0);
				status.setMessage(chkDuplicate);
			}
		}
		return status;
		
	
	}
	
	public static String checkNull(LtMastExpenseTypes ltMastExpenseTypes)
	{
		if(ltMastExpenseTypes.getExpenseType()==null || ltMastExpenseTypes.getStartDate()==null ||
				ltMastExpenseTypes.getCreatedBy()==null || ltMastExpenseTypes.getCreationDate()==null || ltMastExpenseTypes.getLastUpdateLogin()==null)
		{
			return "null";
		}
		else
			return "notnull";
		
	}
	
	public String checkDuplicate(LtMastExpenseTypes ltMastExpenseTypes)  throws Exception
	{
		//System.out.println("in checkDuplicate");
		LtMastExpenseTypes expenseType =ltMastExpenseTypeDao.getByExpenseNature(ltMastExpenseTypes.getExpenseNature(),ltMastExpenseTypes.getCompanyId());
		//System.out.println("below expenseType query");
		if(expenseType!=null)
		{
			if(expenseType.getExpenseTypeId()!=ltMastExpenseTypes.getExpenseTypeId())
			{ return "Expense Nature already exists"; }
		}
		else
			return "null";
	return "null";
		 //expenseType =ltMastExpenseTypeDao.getByGlCode(ltMastExpenseTypes.getGlCode());
//	   if(expenseType!=null)
//		{
//			if(expenseType.getExpenseTypeId()!=ltMastExpenseTypes.getExpenseTypeId())
//			{ return "GL Code already exists"; }
//		}
		
	}
	
	@Transactional
	@Override
	public LtMastExpenseTypes getExpenseTypeById(Long expenseTypeId)
			throws Exception {
				
			 return ltMastExpenseTypeDao.getExpenseTypeById(expenseTypeId);
	}
	
	@Transactional
	@Override
	public Status deleteExpense(Long expenseTypeId) throws Exception {
		Status status=new Status();
		
		if(ltMastExpenseTypeDao.deleteExpense(expenseTypeId))
		{
			
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
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
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
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
	
	
	@Transactional
	@Override
	public Status updateExpense(LtMastExpenseTypes ltMastExpenseTypes)
			throws Exception {
		Status status=new Status();
		if(ltMastExpenseTypes.getExpenseTypeId()!=null)
		{
			String chknull=checkNull(ltMastExpenseTypes);
			if(chknull.equals("null"))
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
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
				String chkDuplicate=checkDuplicate(ltMastExpenseTypes);
				if(chkDuplicate.equals("null"))
				{	System.out.println(ltMastExpenseTypes);
					ltMastExpenseTypes.setLastUpdateDate(new Date());
					if(ltMastExpenseTypeDao.updateExpense(ltMastExpenseTypes))
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
						status.setCode(1);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
						if( status.getMessage()==null)
						{
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
						status.setCode(0);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
						if(status.getMessage()==null)
						{
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}
					}
				}
				else
				{
					status.setCode(0);
					status.setMessage(chkDuplicate);
				}
			}
		
		}
		else
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
		
	
	return status;
	}
	
	@Override
	public List<LtMastExpenseTypes> getExpenseTypesLikeName(String name,Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltMastExpenseTypeDao.getExpenseTypesLikeName(name,companyId);
	}
	
}


