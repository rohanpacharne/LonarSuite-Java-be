package com.lonar.vendor.vendorportal.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.dao.LtMastDocumentListDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastDocumentList;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
import com.lonar.vendor.vendorportal.repository.LtMastDocumentListRepository;

@Service
public class LtMastDocumentListServiceImpl implements LtMastDocumentListService,CodeMaster{

	@Autowired
	LtMastDocumentListDao ltMastDocumentListDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastDocumentListRepository ltMastDocumentListRepository;
	
	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;
	
	@Override
	public Long getCount(Long companyId,LtMastDocumentList input) throws ServiceException {
		return ltMastDocumentListDao.getCount(input,companyId);
	}

	@Override
	public List<LtMastDocumentList> getDataTableRecords(Long companyId,LtMastDocumentList input) throws ServiceException {
		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
		{ input.setColumnNo(11); }
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{ input.setColumnNo(12); }
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{ input.setColumnNo(13); }
		if(input.getColumnNo()==4 && input.getSort().equals("asc"))
		{ input.setColumnNo(14); }
		return ltMastDocumentListDao.getDataTableRecords(input,companyId);
	}

	@Override
	public ResponseEntity<Status> saveWithfile(Long lastUpdateLogin, MultipartFile[] files)
			throws ServiceException {
		Status status = new Status();
		
		if(files.length<=0) {
			status.setCode(0);
			status.setMessage("Please upload file");
			 return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		
		Long companyId = ltMastDocumentListDao.getCompanyId(lastUpdateLogin);
		LtMastDocumentList ltMastDocumentList = new LtMastDocumentList();
		ltMastDocumentList.setCompanyId(companyId);
		ltMastDocumentList.setCreationDate(new Date());
		ltMastDocumentList.setLastUpdateDate(new Date());
		ltMastDocumentList.setCreatedBy(lastUpdateLogin);
		ltMastDocumentList.setLastUpdateLogin(lastUpdateLogin);
		ltMastDocumentList.setLastUpdatedBy(lastUpdateLogin); 
		ltMastDocumentList = ltMastDocumentListRepository.save(ltMastDocumentList);
		if(ltMastDocumentList.getDocListId()!=null)
		{
			if(files.length < 0 ) 
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
				ltMastDocumentListRepository.delete(ltMastDocumentList.getDocListId());
			}
			else
			{
				Status status1=imageUpload(files,ltMastDocumentList);
				
				if(status1!=null && status1.getCode()== 1)
				{
//					status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
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
		               status.setData(ltMastDocumentList.getDocListId());
				}
				else
				{
//						status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
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
			}
		}else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
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
	
	
	public Status imageUpload(MultipartFile[] files,LtMastDocumentList ltMastDocumentList) throws ServiceException
	{
		Status status=new Status();
		String fileName;
		String saveDirectory=null;
		SysVariableWithValues sysVariableWithValues=
				ltMastSysVariablesService.getBySysVariableName("GLOBAL_FILE_PATH",ltMastDocumentList.getCompanyId());
	
		if(sysVariableWithValues!=null)
		{
			if(sysVariableWithValues.getLtMastSysVariableValues().get(0)!=null)
			{
				saveDirectory=sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
			}
			else
			{
				saveDirectory=sysVariableWithValues.getLtMastSysVariables().getSystemValue();
			}
		}
		File dir = new File(saveDirectory);
		if (!dir.exists())
		{
			dir.mkdirs();
			if(!dir.isDirectory())
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(NO_DIRECTIVE_EXISTS);
					
				try {
					status.setCode(1);	
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("NO_DIRECTIVE_EXISTS").getMessageName());
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
	
		for(int i =0 ;i< files.length; i++)
		{
        try 
        {
            fileName = files[i].getOriginalFilename();
            byte[] bytes = files[i].getBytes();
            BufferedOutputStream buffStream = 
                    new BufferedOutputStream(new FileOutputStream(new File(saveDirectory + fileName)));
            buffStream.write(bytes);
             
            ltMastDocumentList.setFilePath(saveDirectory + fileName);
            ltMastDocumentList.setFileName(fileName);
           if( ltMastDocumentListRepository.save(ltMastDocumentList)!=null)
           {
        	   buffStream.close();
//               status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
        	   status.setCode(1);		
        	   status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
               if( status.getMessage()==null)
               {
            	status.setCode(1);
            	status.setMessage("Error in finding message! The action is completed successfully.");
               }
              
           }
           else
           {
//               status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
        	   status.setCode(0);		
        	   status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
   			   if(status.getMessage()==null)
   				{
   					status.setCode(0);
   					status.setMessage("Error in finding message! The action was unsuccessful");
   				}
            }
        	
        } 
        catch (Exception e)
        {
        	e.printStackTrace();
//        	 status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
        			
        	try {
        		status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	public ResponseEntity<Status> deleteById(Long id) throws ServiceException {
		Status status=new Status();
		
		if (ltMastDocumentListRepository.exists(id)) {
			ltMastDocumentListRepository.delete(id);
		} 
		else 
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(NO_RESULT);
					
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("NO_RESULT").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new ResponseEntity<Status>(status,HttpStatus.OK);
		}
//		status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
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
			status.setMessage("Error in finding message! The action is completed successfully.");
		}
		return new ResponseEntity<Status>(status,HttpStatus.OK);
	}

}
