package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastStatesDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastStates;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtMastStatesRepository;

@Service
public class LtMastStatesServiceImpl implements LtMastStatesService,CodeMaster
{
	@Autowired
	LtMastStatesDao LtMastStatesDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastStatesRepository ltMastStatesRepository;
	
	@Override
	public Long getCount(LtMastStates input) throws ServiceException 
	{
		return LtMastStatesDao.getCount(input);
	}

	@Override
	public List<LtMastStates> getStateDataTableRecords(LtMastStates input) throws ServiceException 
	{
		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
		{
			input.setColumnNo(11);
		}
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{
			input.setColumnNo(16);
		}
		
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{
			input.setColumnNo(17);
		}
		if(input.getColumnNo()==8 && input.getSort().equals("desc"))
		{
			input.setColumnNo(18);
		}
		
		return LtMastStatesDao.getStateDataTableRecords(input);
	}

	@Override
	public List<LtMastStates> findAll() throws ServiceException 
	{
		return LtMastStatesDao.findAll();
	}

	@Override
	public List<LtMastStates> findAllActive() throws ServiceException 
	{
		return LtMastStatesDao.findAllActive();
	}

	@Override
	public LtMastStates getByID(Long id) throws ServiceException 
	{
		return LtMastStatesDao.getByID(id);
	}

	@Override
	public List<LtMastStates> findActiveLikeStateName(String state) throws ServiceException 
	{
		return LtMastStatesDao.findActiveLikeStateName(state);
	}

	@Override
	public ResponseEntity<Status> save(LtMastStates ltMastStates) throws ServiceException 
	{
		Status status = new Status();
		LtMastStates states = LtMastStatesDao.getByStateCode(ltMastStates.getStateCode().trim());
		if(states!=null && !(states.getStateId().equals(ltMastStates.getStateId())))
		{
			status.setCode(0);
			status.setMessage("State code already exists.");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		
		LtMastStates state = LtMastStatesDao.getByStateName(ltMastStates.getStateName().trim());
		if(state!=null && !(state.getStateId().equals(ltMastStates.getStateId())))
		{
			status.setCode(0);
			status.setMessage("State name already exists.");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		
		LtMastStates gstState = LtMastStatesDao.getByGstStateCode(ltMastStates.getGstStateCode());
		if(gstState!=null && !(gstState.getStateId().equals(ltMastStates.getStateId())))
		{
			status.setCode(0);
			status.setMessage("GST state code already exists.");
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		ltMastStates.setCreatedBy(ltMastStates.getLastUpdateLogin());
		ltMastStates.setLastUpdateLogin(ltMastStates.getLastUpdateLogin());
		ltMastStates.setLastUpdatedBy(ltMastStates.getLastUpdateLogin()); 
		ltMastStates.setCreationDate(new Date());
		ltMastStates.setLastUpdateDate(new Date());
		ltMastStates = ltMastStatesRepository.save(ltMastStates);
		if(ltMastStates.getStateId()!=null)
		{
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
					
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
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> update(LtMastStates ltMastStates) throws ServiceException 
	{
		Status status = new Status();
		if(ltMastStates.getStateId()!=null) {
			
			LtMastStates states = LtMastStatesDao.getByStateCode(ltMastStates.getStateCode());			
			if(states!=null && !(states.getStateId().equals(ltMastStates.getStateId())))
			{
				status.setCode(0);
				status.setMessage("Stat code already exists.");
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}
			LtMastStates state = LtMastStatesDao.getByStateName(ltMastStates.getStateName().trim());
			if(state!=null && !(state.getStateId().equals(ltMastStates.getStateId())))
			{
				status.setCode(0);
				status.setMessage("Stat name already exists.");
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}
			LtMastStates gstState = LtMastStatesDao.getByGstStateCode(ltMastStates.getGstStateCode());
			if(gstState!=null && !(gstState.getStateId().equals(ltMastStates.getStateId())))
			{
				status.setCode(0);
				status.setMessage("GST state code already exists.");
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}
			
			ltMastStates.setLastUpdateLogin(ltMastStates.getLastUpdateLogin());
			ltMastStates.setLastUpdatedBy(ltMastStates.getLastUpdateLogin()); 
			ltMastStates.setLastUpdateDate(new Date());
			ltMastStates = ltMastStatesRepository.save(ltMastStates);
		if(ltMastStates.getStateId()!=null)
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
	    return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> delete(Long id) throws ServiceException 
	{
		Status status = new Status();
		ltMastStatesRepository.delete(id);
		if(ltMastStatesRepository.exists(id)) {
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
		 return new ResponseEntity<Status>(status, HttpStatus.OK);	
	}

}
