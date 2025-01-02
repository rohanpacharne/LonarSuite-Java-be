package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtVendCompanyMgmtDdetailsDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtVendCompanyMgmtDdetails;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtVendCompanyMgmtDdetailsRepository;

@Service
public class LtVendCompanyMgmtDdetailsServiceImpl implements LtVendCompanyMgmtDdetailsService, CodeMaster {

	@Autowired
	LtVendCompanyMgmtDdetailsDao ltVendCompanyMgmtDdetailsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtVendCompanyMgmtDdetailsRepository ltVendCompanyMgmtDdetailsRepository;
	
	@Override
	public List<LtVendCompanyMgmtDdetails> getBycompanyId(Long companyId) {
		// TODO Auto-generated method stub
		return ltVendCompanyMgmtDdetailsDao.getBycompanyId(companyId);
	}

	@Override
	public LtVendCompanyMgmtDdetails getById(Long id) {
		// TODO Auto-generated method stub
		return ltVendCompanyMgmtDdetailsDao.getById(id);
	}

	@Override
	public List<LtVendCompanyMgmtDdetails> getAll() {
		// TODO Auto-generated method stub
		return ltVendCompanyMgmtDdetailsDao.getAll();
	}

	@Override
	public List<LtVendCompanyMgmtDdetails> getAllActive() {
		// TODO Auto-generated method stub
		return ltVendCompanyMgmtDdetailsDao.getAllActive();
	}

	@Override
	public ResponseEntity<Status> save(LtVendCompanyMgmtDdetails ltVendCompanyMgmtDdetails) {
		Status status = new Status();
		if(ltVendCompanyMgmtDdetails.getLastUpdateLogin() == null )
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
		ltVendCompanyMgmtDdetails.setStartDate(new Date());
		ltVendCompanyMgmtDdetails.setCreationDate(new Date());
		ltVendCompanyMgmtDdetails.setLastUpdateDate(new Date());
		ltVendCompanyMgmtDdetails.setCreatedBy(ltVendCompanyMgmtDdetails.getLastUpdateLogin());
		ltVendCompanyMgmtDdetails.setLastUpdateLogin(ltVendCompanyMgmtDdetails.getLastUpdateLogin());
		ltVendCompanyMgmtDdetails.setLastUpdatedBy(ltVendCompanyMgmtDdetails.getLastUpdateLogin()); 
		ltVendCompanyMgmtDdetails = ltVendCompanyMgmtDdetailsRepository.save(ltVendCompanyMgmtDdetails);
			if(ltVendCompanyMgmtDdetails.getCompMgmtId()!=null)
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
				status.setData(ltVendCompanyMgmtDdetails.getCompMgmtId());
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
	public ResponseEntity<Status> update(LtVendCompanyMgmtDdetails ltVendCompanyMgmtDdetails) {
		Status status = new Status();
		if(ltVendCompanyMgmtDdetails.getCompMgmtId()!=null) {
			ltVendCompanyMgmtDdetails.setStartDate(new Date());
			ltVendCompanyMgmtDdetails.setLastUpdateDate(new Date());
			ltVendCompanyMgmtDdetails.setLastUpdateLogin(ltVendCompanyMgmtDdetails.getLastUpdateLogin());
			ltVendCompanyMgmtDdetails.setLastUpdatedBy(ltVendCompanyMgmtDdetails.getLastUpdateLogin()); 
			ltVendCompanyMgmtDdetails = ltVendCompanyMgmtDdetailsRepository.save(ltVendCompanyMgmtDdetails);
			if(ltVendCompanyMgmtDdetails.getCompMgmtId()!=null)
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
	public ResponseEntity<Status> delete(Long id) {
		Status status = new Status();
		ltVendCompanyMgmtDdetailsRepository.delete(id);
		if (!ltVendCompanyMgmtDdetailsRepository.exists(id))
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
