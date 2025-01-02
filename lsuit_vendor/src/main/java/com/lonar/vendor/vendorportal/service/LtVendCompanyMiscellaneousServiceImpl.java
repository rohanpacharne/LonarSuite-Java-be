package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtVendCompanyMiscellaneousDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtVendCompanyMiscellaneous;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtVendCompanyMiscellaneousRepository;

@Service
public class LtVendCompanyMiscellaneousServiceImpl implements LtVendCompanyMiscellaneousService , CodeMaster{

	@Autowired
	LtVendCompanyMiscellaneousDao ltVendCompanyMiscellaneousDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtVendCompanyMiscellaneousRepository ltVendCompanyMiscellaneousRepository;
	
	@Override
	public LtVendCompanyMiscellaneous getBycompanyId(Long companyId) throws ServiceException {
		return ltVendCompanyMiscellaneousDao.getBycompanyId(companyId);
	}

	@Override
	public LtVendCompanyMiscellaneous getById(Long id) throws ServiceException {
		return ltVendCompanyMiscellaneousDao.getById(id);
	}
	
	@Override
	public List<LtVendCompanyMiscellaneous> getAll() throws ServiceException {
		return ltVendCompanyMiscellaneousDao.getAll();
	}

	@Override
	public List<LtVendCompanyMiscellaneous> getAllActive() throws ServiceException {
		return ltVendCompanyMiscellaneousDao.getAllActive();
	}

	@Override
	public ResponseEntity<Status> save(LtVendCompanyMiscellaneous ltVendCompanyMiscellaneous) throws ServiceException {
		Status status = new Status();
		if(ltVendCompanyMiscellaneous.getStartDate()==null || ltVendCompanyMiscellaneous.getLastUpdateLogin() == null )
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		ltVendCompanyMiscellaneous.setCreationDate(new Date());
		ltVendCompanyMiscellaneous.setLastUpdateDate(new Date());
		ltVendCompanyMiscellaneous.setCreatedBy(ltVendCompanyMiscellaneous.getLastUpdateLogin());
		ltVendCompanyMiscellaneous.setLastUpdateLogin(ltVendCompanyMiscellaneous.getLastUpdateLogin());
		ltVendCompanyMiscellaneous.setLastUpdatedBy(ltVendCompanyMiscellaneous.getLastUpdateLogin()); 
		ltVendCompanyMiscellaneous = ltVendCompanyMiscellaneousRepository.save(ltVendCompanyMiscellaneous);
			if(ltVendCompanyMiscellaneous.getCompMiscellaneousId()!=null)
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
				status.setData(ltVendCompanyMiscellaneous.getCompMiscellaneousId());
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
				if( status.getMessage()==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
			}
		    return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> update(LtVendCompanyMiscellaneous ltVendCompanyMiscellaneous)
			throws ServiceException {
		Status status = new Status();
		if(ltVendCompanyMiscellaneous.getCompMiscellaneousId()!=null) {
		
			ltVendCompanyMiscellaneous.setLastUpdateDate(new Date());
			ltVendCompanyMiscellaneous.setLastUpdateLogin(ltVendCompanyMiscellaneous.getLastUpdateLogin());
			ltVendCompanyMiscellaneous.setLastUpdatedBy(ltVendCompanyMiscellaneous.getLastUpdateLogin()); 
			ltVendCompanyMiscellaneous = ltVendCompanyMiscellaneousRepository.save(ltVendCompanyMiscellaneous);
			if(ltVendCompanyMiscellaneous.getCompMiscellaneousId()!=null)
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
			}
			else
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
			}
		}else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		    return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> delete(Long id) throws ServiceException {
		Status status = new Status();
		ltVendCompanyMiscellaneousRepository.delete(id);
		if (!ltVendCompanyMiscellaneousRepository.exists(id))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null) {
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		} 
		else 
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		} 
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}



}
