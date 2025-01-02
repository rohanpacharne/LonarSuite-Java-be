package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtVendCompanyAttachmentsDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtVendCompanyAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtVendCompanyAttachmentsRepository;

@Service
public class LtVendCompanyAttachmentsServiceImpl implements LtVendCompanyAttachmentsService, CodeMaster{

	@Autowired
	LtVendCompanyAttachmentsDao ltVendCompanyAttachmentsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtVendCompanyAttachmentsRepository LtVendCompanyAttachmentsRepository;
	
	@Override
	public List<LtVendCompanyAttachments> getBycompanyId(Long companyId) throws ServiceException {
		return ltVendCompanyAttachmentsDao.getBycompanyId( companyId);
	}

	@Override
	public LtVendCompanyAttachments getById(Long id) throws ServiceException {
		return ltVendCompanyAttachmentsDao.getById( id);
	}

	@Override
	public List<LtVendCompanyAttachments> getAll() throws ServiceException {
		return ltVendCompanyAttachmentsDao.getAll();
	}

	@Override
	public List<LtVendCompanyAttachments> getAllActive() throws ServiceException {
		return ltVendCompanyAttachmentsDao.getAllActive();
	}

	@Override
	public ResponseEntity<Status> save(LtVendCompanyAttachments ltVendCompanyAttachments) throws ServiceException {
		Status status = new Status();
		if( ltVendCompanyAttachments.getLastUpdateLogin() == null )
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
		ltVendCompanyAttachments.setCreationDate(new Date());
		ltVendCompanyAttachments.setLastUpdateDate(new Date());
		ltVendCompanyAttachments.setCreatedBy(ltVendCompanyAttachments.getLastUpdateLogin());
		ltVendCompanyAttachments.setLastUpdateLogin(ltVendCompanyAttachments.getLastUpdateLogin());
		ltVendCompanyAttachments.setLastUpdatedBy(ltVendCompanyAttachments.getLastUpdateLogin()); 
		ltVendCompanyAttachments = LtVendCompanyAttachmentsRepository.save(ltVendCompanyAttachments);
			if(ltVendCompanyAttachments.getCompAttachmentId()!=null)
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
				status.setData(ltVendCompanyAttachments.getCompAttachmentId());
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
	public ResponseEntity<Status> update(LtVendCompanyAttachments ltVendCompanyAttachments) throws ServiceException {
		Status status = new Status();
		if(ltVendCompanyAttachments.getCompAttachmentId()!=null) {
		
			ltVendCompanyAttachments.setLastUpdateDate(new Date());
			ltVendCompanyAttachments.setLastUpdateLogin(ltVendCompanyAttachments.getLastUpdateLogin());
			ltVendCompanyAttachments.setLastUpdatedBy(ltVendCompanyAttachments.getLastUpdateLogin()); 
			ltVendCompanyAttachments = LtVendCompanyAttachmentsRepository.save(ltVendCompanyAttachments);
			if(ltVendCompanyAttachments.getCompAttachmentId()!=null)
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
		LtVendCompanyAttachmentsRepository.delete(id);
		if (!LtVendCompanyAttachmentsRepository.exists(id))
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
