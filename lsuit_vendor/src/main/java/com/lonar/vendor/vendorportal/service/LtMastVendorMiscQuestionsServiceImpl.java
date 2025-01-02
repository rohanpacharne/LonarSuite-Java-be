package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastVendorMiscInfoDao;
import com.lonar.vendor.vendorportal.dao.LtMastVendorMiscQuestionsDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastVendorMiscInfo;
import com.lonar.vendor.vendorportal.model.LtMastVendorMiscQuestions;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtMastVendorMiscQuestionsRepository;

@Service
public class LtMastVendorMiscQuestionsServiceImpl implements LtMastVendorMiscQuestionsService,CodeMaster
{

	@Autowired
	LtMastVendorMiscQuestionsRepository ltMastVendorMiscQuestionsRepository;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastVendorMiscQuestionsDao ltMastVendorMiscQuestionsDao;
	
	@Override
	public Status save(List<LtMastVendorMiscQuestions> ltMastVendorMiscQuestionsList) throws ServiceException
	{
		Status status=new Status();
		for(LtMastVendorMiscQuestions ltMastVendorMiscQuestions : ltMastVendorMiscQuestionsList) 
		{
			ltMastVendorMiscQuestions.setCreationDate(new Date());
			ltMastVendorMiscQuestions.setCreatedBy(ltMastVendorMiscQuestions.getLastUpdateLogin());
			ltMastVendorMiscQuestions.setLastUpdateDate(new Date());
			ltMastVendorMiscQuestions.setLastUpdatedBy(ltMastVendorMiscQuestions.getLastUpdateLogin());
			if(ltMastVendorMiscQuestionsRepository.save(ltMastVendorMiscQuestions)!=null)
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
				return status;
			}
		}
	return status;
	}

	@Override
	public Status update(List<LtMastVendorMiscQuestions> ltMastVendorMiscQuestionsList) throws ServiceException {
		Status status=new Status();
		for(LtMastVendorMiscQuestions ltMastVendorMiscQuestions : ltMastVendorMiscQuestionsList) 
		{
			ltMastVendorMiscQuestions.setLastUpdateDate(new Date());
			ltMastVendorMiscQuestions.setLastUpdatedBy(ltMastVendorMiscQuestions.getLastUpdateLogin());
			if(ltMastVendorMiscQuestions.getCreationDate()==null) {
				ltMastVendorMiscQuestions.setCreationDate(new Date());
			}
			if(ltMastVendorMiscQuestionsRepository.save(ltMastVendorMiscQuestions)!=null)
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
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
//				status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
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
	
	return status;
	}

	@Override
	public List<LtMastVendorMiscQuestions> getVendorMiscQuestionByVenId(Long vendorId) throws ServiceException {
		return ltMastVendorMiscQuestionsDao.getVendorMiscQuestionByVenId(vendorId);
	}

	@Override
	public LtMastVendorMiscQuestions getVendorMiscQuesById(Long vendorMiscQuesId) throws ServiceException {
		return ltMastVendorMiscQuestionsDao.getVendorMiscQuesById(vendorMiscQuesId);
	}

	@Override
	public Status checkRecord(Long venId) throws ServiceException {
		Status status = new Status();
		List<LtMastVendorMiscQuestions> list =ltMastVendorMiscQuestionsDao.getVendorMiscQuestionByVenId(venId);
		if(list.isEmpty()) {
			status.setCode(0);
			status.setMessage("Please fill mandatory MISC questions answer");
		}
		else {
			status.setCode(1);
		}
		return status;
	}
	
	
}
