package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastSysVariableValuesDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastSysVariableValues;
import com.lonar.vendor.vendorportal.model.LtMastSysVariables;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtMastSysVariableValuesRepository;



@Service
public class LtMastSysVariableValuesServiceImpl implements LtMastSysVariableValuesService,CodeMaster
{

	@Autowired
	LtMastSysVariableValuesDao ltMastSysVariableValuesDao;
	
	@Autowired
	LtMastSysVariableValuesRepository ltMastSysVariableValuesRepository;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	
	@Transactional
	@Override
	public List<LtMastSysVariableValues> getByVariableId(Long parseLong) throws Exception 
	{
		// TODO Auto-generated method stub
		return ltMastSysVariableValuesDao.getByVariableId(parseLong);
	}

	@Override
	public ResponseEntity<Status> saveSysVariableValues(LtMastSysVariableValues ltMastSysVariableValues)
			throws ServiceException {
		Status status = new  Status();
		String stat = checkForDuplicate(ltMastSysVariableValues);
		if (stat.equals("null"))
		{
			ltMastSysVariableValues.setCreatedBy(ltMastSysVariableValues.getLastUpdateLogin());
			ltMastSysVariableValues.setLastUpdatedBy(ltMastSysVariableValues.getLastUpdateLogin());
			ltMastSysVariableValues.setLastUpdateLogin(ltMastSysVariableValues.getLastUpdateLogin());
			ltMastSysVariableValues.setLastUpdateDate(new Date());
			ltMastSysVariableValues = ltMastSysVariableValuesRepository.save(ltMastSysVariableValues);
			if(ltMastSysVariableValues.getVariableValuesId()!=null) {
//				status = ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
						
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (status.getMessage() == null) {
					status.setCode(1);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
			}else {
//				status = ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			status.setMessage(stat);
			status.setCode(0);
		}
		return new ResponseEntity(status, HttpStatus.OK);
		
	}

	private String checkForDuplicate(LtMastSysVariableValues ltMastSysVariableValues) throws ServiceException {
		LtMastSysVariableValues sysVariableValues = ltMastSysVariableValuesDao.getByUserValue(ltMastSysVariableValues);
		if(sysVariableValues!=null)
		{
			/*if(!sysVariableValues.getVariableValuesId().equals(ltMastSysVariableValues.getVariableValuesId()))
			{
				return "User value exists for the sysvariable";
			}*/
			if(sysVariableValues.getUserId().equals(ltMastSysVariableValues.getUserId())) {
				return "Sysvariable value already exists for user.";
			}
		}
		
	return "null";
	}

	@Override
	public ResponseEntity<Status> updateSysVariableValues(LtMastSysVariableValues ltMastSysVariableValues)
			throws ServiceException {
		Status status = new  Status();
		
		ltMastSysVariableValues.setLastUpdatedBy(ltMastSysVariableValues.getLastUpdateLogin());
		ltMastSysVariableValues.setLastUpdateLogin(ltMastSysVariableValues.getLastUpdateLogin());
		ltMastSysVariableValues.setLastUpdateDate(new Date());
		ltMastSysVariableValues = ltMastSysVariableValuesRepository.save(ltMastSysVariableValues);
		if(ltMastSysVariableValues.getVariableValuesId()!=null) {
//		status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
					
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (status.getMessage() == null) {
			status.setCode(1);
			status.setMessage("Error in finding message! The action was unsuccessful");
		}
		}else {
//			status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<LtMastSysVariableValues>> getAllValuesBySysVariableId(Long id) throws ServiceException
	{
		List<LtMastSysVariableValues> list = ltMastSysVariableValuesDao.getByVariableId(id);
		return new ResponseEntity<List<LtMastSysVariableValues>>(list, HttpStatus.OK) ;
	}

	@Override
	public ResponseEntity<LtMastSysVariableValues> getBySysVariableValuesId(Long id,Long userId) throws ServiceException {
		LtMastSysVariableValues list = ltMastSysVariableValuesDao.getBySysVariableValuesId(id,userId);
		return new ResponseEntity<LtMastSysVariableValues>(list, HttpStatus.OK) ;
	}

}
