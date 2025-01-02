package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtVendMiscQuestionsDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtVendMiscQuestions;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtVendMiscQuestionsRepository;

@Service
public class LtVendMiscQuestionsServiceImpl implements LtVendMiscQuestionsService , CodeMaster{

	@Autowired
	LtVendMiscQuestionsDao ltVendMiscQuestionsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtVendMiscQuestionsRepository  ltVendMiscQuestionsRepository;
	
	@Override
	public List<LtVendMiscQuestions> getBycompanyMiscId(Long companyMiscId) throws ServiceException {
		return ltVendMiscQuestionsDao.getBycompanyMiscId(companyMiscId);
	}

	@Override
	public LtVendMiscQuestions getById(Long id) throws ServiceException {
		return ltVendMiscQuestionsDao.getById(id);
	}
	
	@Override
	public List<LtVendMiscQuestions> getAll() throws ServiceException {
		return ltVendMiscQuestionsDao.getAll();
	}

	@Override
	public List<LtVendMiscQuestions> getAllActive() throws ServiceException {
		return ltVendMiscQuestionsDao.getAllActive();
	}

	@Override
	public ResponseEntity<Status> save(LtVendMiscQuestions ltVendMiscQuestions) throws ServiceException {
		Status status = new Status();
		if(ltVendMiscQuestions.getStartDate()==null || ltVendMiscQuestions.getLastUpdateLogin() == null )
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
		ltVendMiscQuestions.setCreationDate(new Date());
		ltVendMiscQuestions.setLastUpdateDate(new Date());
		ltVendMiscQuestions.setCreatedBy(ltVendMiscQuestions.getLastUpdateLogin());
		ltVendMiscQuestions.setLastUpdateLogin(ltVendMiscQuestions.getLastUpdateLogin());
		ltVendMiscQuestions.setLastUpdatedBy(ltVendMiscQuestions.getLastUpdateLogin()); 
		ltVendMiscQuestions = ltVendMiscQuestionsRepository.save(ltVendMiscQuestions);
			if(ltVendMiscQuestions.getMiscQuestionId()!=null)
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
				status.setData(ltVendMiscQuestions.getMiscQuestionId());
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
	public ResponseEntity<Status> update(LtVendMiscQuestions ltVendMiscQuestions) throws ServiceException {
		Status status = new Status();
		if(ltVendMiscQuestions.getMiscQuestionId()!=null) {
		
			ltVendMiscQuestions.setLastUpdateDate(new Date());
			ltVendMiscQuestions.setLastUpdateLogin(ltVendMiscQuestions.getLastUpdateLogin());
			ltVendMiscQuestions.setLastUpdatedBy(ltVendMiscQuestions.getLastUpdateLogin()); 
			ltVendMiscQuestions = ltVendMiscQuestionsRepository.save(ltVendMiscQuestions);
			if(ltVendMiscQuestions.getMiscQuestionId()!=null)
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
		ltVendMiscQuestionsRepository.delete(id);
		if (!ltVendMiscQuestionsRepository.exists(id))
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
