package com.lonar.vendor.vendorportal.service;
 
import java.util.Date;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.lonar.vendor.vendorportal.dao.LtMastPoLinesTaxesDao;
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtPoLineTaxesRepository;
 
@Service
public class LtMastPoLinesTaxesServiceImpl implements LtMastPoLinesTaxesService {
	@Autowired
	LtMastPoLinesTaxesDao ltmastPolineTaxesDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtPoLineTaxesRepository ltPoLineTaxesRepository;

	@Override
	public List<LtPoLineTaxes> getAllPoLinesByLineId(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return ltmastPolineTaxesDao.getAllPoLinesByLineId(id);
	}
	
	@Override
	public Status save(List<LtPoLineTaxes> ltPoLineTaxesList) {
    Status status = new Status();
		
		for(LtPoLineTaxes ltPoLineTaxes :ltPoLineTaxesList)
		{
			ltPoLineTaxes.setCreatedBy(ltPoLineTaxes.getLastUpdateLogin());
			ltPoLineTaxes.setLastUpdateLogin(ltPoLineTaxes.getLastUpdateLogin());
			ltPoLineTaxes.setLastUpdatedBy(ltPoLineTaxes.getLastUpdateLogin());
			ltPoLineTaxes.setCreationDate(new Date());
			ltPoLineTaxes.setLastUpdateDate(new Date());
			ltPoLineTaxes = ltPoLineTaxesRepository.save(ltPoLineTaxes);
		if(ltPoLineTaxes.getPoLineTaxId()!=null)
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
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
			ltmastPolineTaxesDao.deleteTax(ltPoLineTaxes.getPoLineId());
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
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
			return status;
		}
		}
	    return status;
	}
	
	@Override
	public Status update(LtPoLineTaxes ltPoLineTaxes) {
		Status status = new Status();
		if(ltPoLineTaxes.getPoLineTaxId()!=null) {
			ltPoLineTaxes.setLastUpdateLogin(ltPoLineTaxes.getLastUpdateLogin());
			ltPoLineTaxes.setLastUpdatedBy(ltPoLineTaxes.getLastUpdateLogin());
			ltPoLineTaxes.setLastUpdateDate(new Date());
			ltPoLineTaxes = ltPoLineTaxesRepository.save(ltPoLineTaxes);
		if(ltPoLineTaxes.getPoLineTaxId()!=null)
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
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
//			status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
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
		}else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
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
	    return status;
	}
	
	@Override
	public Status delete(Long id) {
		Status status = new Status();
		ltPoLineTaxesRepository.delete(id);
		if(ltPoLineTaxesRepository.exists(id)) {
//		status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
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
		}else {
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
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
		 return status;	
	}
 
 
 

 
}