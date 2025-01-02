package com.lonar.vendor.vendorportal.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastVendorManagementsDao;
import com.lonar.vendor.vendorportal.model.LtMastValidation;
import com.lonar.vendor.vendorportal.model.LtMastVendorManagements;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtMastVendorManagementsServiceImpl implements LtMastVendorManagementsService
{
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Autowired
	LtMastVendorManagementsDao ltMastVendorManagementsDao;
	
	@Override
	public List<LtMastVendorManagements> getAllVendorManagements() throws ServiceException 
	{
		List<LtMastVendorManagements> vendorManagementList=new ArrayList<LtMastVendorManagements>();
		
		vendorManagementList=ltMastVendorManagementsDao.getAllVendorManagements();
		return vendorManagementList;
	}

	@Transactional
	@Override
	public List<LtMastVendorManagements> getVendorManagementByVenId(Long vendorId) throws ServiceException
	{
		List<LtMastVendorManagements> vendorManagementList=new ArrayList<LtMastVendorManagements>();
		
		vendorManagementList=ltMastVendorManagementsDao.getVendorManagementByVenId(vendorId);
		return vendorManagementList;
	}

	@Transactional
	@Override
	public LtMastVendorManagements getVendorManagementById(Long vendorManagementId) throws ServiceException
	{
		LtMastVendorManagements vendorManagementList=ltMastVendorManagementsDao.getVendorManagementById(vendorManagementId);
		return vendorManagementList;
	}

	@Transactional
	@Override
	public Status save(LtMastVendorManagements ltMastVendorManagements) throws ServiceException 
	{
		Status status=new Status();
		
		String chknull=checkNull(ltMastVendorManagements);
		if(chknull.equals("null"))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
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
		else
		{
			if(ltMastVendorManagementsDao.save(ltMastVendorManagements))
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
				status.setData(ltMastVendorManagements.getVendorManagementId());
			}
			else
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
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
		
	
	return status;
	}

	@Transactional
	@Override
	public Status update(LtMastVendorManagements ltMastVendorManagements) throws ServiceException 
	{
		Status status=new Status();
		
		if(ltMastVendorManagements.getVendorManagementId()!=null)
		{
			String chknull=checkNull(ltMastVendorManagements);
			if(chknull.equals("null"))
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
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
			else
			{
				if(ltMastVendorManagementsDao.update(ltMastVendorManagements))
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
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
//					status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
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
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
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

	@Transactional
	@Override
	public Status delete(Long vendorManagementId) throws ServiceException 
	{
		Status status=new Status();
		
		if(ltMastVendorManagementsDao.delete(vendorManagementId))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
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
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
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
	
	
	public static String checkNull(LtMastVendorManagements ltMastVendorManagements)
	{
		if( ltMastVendorManagements.getName()==null || ltMastVendorManagements.getVenManDesgCode()==null ||
				ltMastVendorManagements.getMobileNo()==null || ltMastVendorManagements.getLastUpdateLogin()==null)
		{
			return "null";
		}
		else
			return "notnull";
		
	}

	@Override
	public List<LtMastValidation> getValidationByTableName(String name) throws ServiceException {

		return ltMastVendorManagementsDao.getValidationByTableName(name);
		
	}

	@Override
	public Status checkRecord(Long venId) throws ServiceException {
		Status status = new Status();
		List<LtMastVendorManagements> list=ltMastVendorManagementsDao.getVendorManagementByVenId(venId);
		if(list.isEmpty()) {
			status.setCode(0);
			status.setMessage("Please fill vendor management details");
		}
		else {
			status.setCode(1);
		}
		return status;
	}

	@Override
	public Long getLtMastVendorManagementsCount(Long vendorId, LtMastVendorManagements input) throws ServiceException {
		return ltMastVendorManagementsDao.getLtMastVendorManagementsCount(vendorId,input);
	}

	@Override
	public List<LtMastVendorManagements> getLtMastVendorManagementsDataTable(Long vendorId,
			LtMastVendorManagements input) throws ServiceException {
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
		
		if(input.getColumnNo()==0) {
			input.setColumnNo(1);
		}
		return ltMastVendorManagementsDao.getLtMastVendorManagementsDataTable(vendorId,input);
	}

}
