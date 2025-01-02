package com.lonar.vendor.vendorportal.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;

public class ExceptionMessage implements CodeMaster    ///akshay expense dnt delete file in used
{
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	public Status getExceptionMessage()
	{
		Status status=new Status();
		try
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
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
		catch(Exception o)
		{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
				o.printStackTrace();
		}
		return status;
	}
}
