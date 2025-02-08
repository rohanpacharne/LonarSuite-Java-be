package com.lonar.UserManagement.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lonar.UserManagement.web.dao.LtMastLoginsDao;
import com.lonar.UserManagement.web.model.LtMastLogins;
import com.lonar.UserManagement.web.model.Status;

@Component
public class LtMastLoginsServiceImpl implements LtMastLoginsService {
	
	@Autowired
	LtMastLoginsDao ltMastLoginsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Override
	public Status saveLoginDetails(LtMastLogins ltMastLogins) {
		// TODO Auto-generated method stub
		Status status = new Status();
		ltMastLogins.setLoginDate(new Date());
		ltMastLogins.setStatus("ACTIVE");
		ltMastLogins.setCreationDate(new Date());
		ltMastLogins.setCreatedBy(ltMastLogins.getUserId());
		ltMastLogins.setLastUpdateDate(new Date());
		ltMastLogins.setLastUpdateLogin(ltMastLogins.getUserId());
		ltMastLogins.setLastUpdatedBy(ltMastLogins.getUserId());
		ltMastLogins.setDeviceType(ltMastLogins.getDeviceType().toUpperCase());
		
		LtMastLogins ltMastLoginsCreated = ltMastLoginsDao.saveLoginDetails(ltMastLogins);
		
		if(ltMastLoginsCreated!=null) {
			status.setCode(1);
			try {
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
		}else {
			status.setCode(0);
			try {
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			
		}
		}
		status.setData(ltMastLoginsCreated);
		return status;
	}

	@Override
	public Status updateLoginDetails(LtMastLogins ltMastLogins) {
		// TODO Auto-generated method stub
		Status status = new Status();
		if(ltMastLoginsDao.updateLoginDetails(ltMastLogins)) {
			status.setCode(1);
			try {
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
		}else {
			status.setCode(0);
			try {
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		return status;
	}

}
