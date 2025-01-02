package com.lonar.vendor.vendorportal.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.dao.LtExpExpenseHeadersDao;
import com.lonar.vendor.vendorportal.dao.LtExpExpenseLinesDao;
import com.lonar.vendor.vendorportal.dao.LtMastComnMasterValuesDao;
import com.lonar.vendor.vendorportal.fileupload.CreateThumbNailImg;
import com.lonar.vendor.vendorportal.model.LtExpExpenseLines;
import com.lonar.vendor.vendorportal.model.LtExpenseAttachments;
import com.lonar.vendor.vendorportal.model.LtMastComnMasterValues;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
import com.lonar.vendor.vendorportal.repository.LtMastFileUploadRepository;

@Service
public class LtExpExpenseLinesServiceImpl implements LtExpExpenseLinesService{
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;
	
	@Autowired
	LtMastFileUploadRepository ltMastFileUploadRepository;
	
	@Autowired
	LtExpExpenseLinesDao ltExpExpenseLinesDao;
	
	@Autowired
	LtExpExpenseHeadersDao ltExpExpenseHeadersDao;
	
	@Autowired
	LtMastComnMasterValuesDao ltMastComnMasterValuesDao;
	
	@Autowired
	OcrService ocrService;
	
	@Transactional
	@Override
	public Long getCount(Long headerId, LtExpExpenseLines input) throws Exception
	{
		return ltExpExpenseLinesDao.getCount(headerId,input);
	}
	
	@Transactional
	@Override
	public List<LtExpExpenseLines> getDatatableByExpenseHeaderId(Long headerId,Long userId, LtExpExpenseLines input)
			throws Exception
	{
		//this was the logic
		System.out.println("input = "+input);
		System.out.println("input.getName() = "+input.getName());
		System.out.println("input.getSort() = "+input.getSort());
//		if(input.getName()==null && (input.getSort()==null || input.getSort().equals("undefined")));
//		{ 
//			System.out.println("in if");
//			input.setName("rnum"); input.setSort("desc");
//		}
		
//		if(input.getName()==null && (input.getSort()==null || input.getSort().equals("undefined")))
//		{ input.setName("rnum"); input.setSort("asc");}
//		
//		System.out.println("After setting");
//		System.out.println("input.getName() = "+input.getName());
//		System.out.println("input.getSort() = "+input.getSort());
//		
//		if(input.getName().equals("rnum") && input.getSort().equals("asc"))
//		{ input.setColumnNo(2); }
//		else if(input.getName().equals("rnum") && input.getSort().equals("desc"))
//		{ input.setColumnNo(12); }
//		
//		if(input.getName().equals("expenseNature") && input.getSort().equals("asc"))
//		{ input.setColumnNo(3); }
//		else if(input.getName().equals("expenseNature") && input.getSort().equals("desc"))
//		{ input.setColumnNo(13); }
//		
//		if(input.getName().equals("amount") && input.getSort().equals("desc"))
//		{ input.setColumnNo(16); }
//		else if(input.getName().equals("amount") && input.getSort().equals("asc"))
//		{ input.setColumnNo(6); }
//		
//		if(input.getName().equals("justification") && input.getSort().equals("desc"))
//		{ input.setColumnNo(17); }
//		else if(input.getName().equals("justification") && input.getSort().equals("asc"))
//		{ input.setColumnNo(7); }
//		
//		if(input.getName().equals("startDate") && input.getSort().equals("desc"))
//		{ input.setColumnNo(18); }
//		else if(input.getName().equals("startDate") && input.getSort().equals("asc"))
//		{ input.setColumnNo(8); }
//		
//		if(input.getName().equals("endDate") && input.getSort().equals("desc"))
//		{ input.setColumnNo(19); }
//		else if(input.getName().equals("endDate") && input.getSort().equals("asc"))
//		{ input.setColumnNo(9); }
//		
//		if(input.getName().equals("exchangeRate") && input.getSort().equals("desc"))
//		{ input.setColumnNo(15); }
//		else if(input.getName().equals("exchangeRate") && input.getSort().equals("asc"))
//		{ input.setColumnNo(5); }
//		
//		if(input.getName().equals("currencyCode") && input.getSort().equals("desc"))
//		{ input.setColumnNo(14); }
//		else if(input.getName().equals("currencyCode") && input.getSort().equals("asc"))
//		{ input.setColumnNo(4); }
//		
//		if(input.getName().equals("purpose") && input.getSort().equals("desc"))
//		{ input.setColumnNo(10); }
//		else if(input.getName().equals("purpose") && input.getSort().equals("asc"))
//		{ input.setColumnNo(20); }
		//this was the logic
		
		
		
//		if(input.getName().equals("rnum") && input.getSort().equals("asc"))
//		{ input.setColumnNo(2); }
//		else if(input.getName().equals("rnum") && input.getSort().equals("desc"))
//		{ input.setColumnNo(12); }
		
//		if(input.getExpenseNature()!=null && input.getSort().equals("asc"))
//		{ input.setColumnNo(3); }
//		else if(input.getExpenseNature()!=null && input.getSort().equals("desc"))
//		{ input.setColumnNo(13); }
//		
//		if(input.getAmount()!=null && input.getSort().equals("desc"))
//		{ input.setColumnNo(16); }
//		else if(input.getAmount()!=null && input.getSort().equals("asc"))
//		{ input.setColumnNo(6); }
//		
//		if(input.getPurpose()!=null && input.getSort().equals("desc"))
//		{ input.setColumnNo(17); }
//		else if(input.getPurpose()!=null && input.getSort().equals("asc"))
//		{ input.setColumnNo(7); }
//		
//		if(input.getStartDate()!=null && input.getSort().equals("desc"))
//		{ input.setColumnNo(18); }
//		else if(input.getStartDate()!=null && input.getSort().equals("asc"))
//		{ input.setColumnNo(8); }
//		
//		if(input.getEndDate()!=null && input.getSort().equals("desc"))
//		{ input.setColumnNo(19); }
//		else if(input.getEndDate()!=null && input.getSort().equals("asc"))
//		{ input.setColumnNo(9); }
		
//		if(input.getName().equals("exchangeRate") && input.getSort().equals("desc"))
//		{ input.setColumnNo(15); }
//		else if(input.getName().equals("exchangeRate") && input.getSort().equals("asc"))
//		{ input.setColumnNo(5); }
		
//		if(input.getName().equals("currencyCode") && input.getSort().equals("desc"))
//		{ input.setColumnNo(14); }
//		else if(input.getName().equals("currencyCode") && input.getSort().equals("asc"))
//		{ input.setColumnNo(4); }
//		
//		if(input.getName().equals("purpose") && input.getSort().equals("desc"))
//		{ input.setColumnNo(10); }
//		else if(input.getName().equals("purpose") && input.getSort().equals("asc"))
//		{ input.setColumnNo(20); }
		
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{ input.setColumnNo(12); }
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{ input.setColumnNo(13); }
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{ input.setColumnNo(14); }
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{ input.setColumnNo(15); }
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{ input.setColumnNo(16); }
//		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
//		{ input.setColumnNo(17); }
		
		
		List<LtExpExpenseLines> list= ltExpExpenseLinesDao.getDatatableByExpenseHeaderId(headerId,userId,input);
		return list;
	}
	
	@Override
	public Status updateV1(LtExpExpenseLines ltExpExpenseLines, MultipartFile[] files, int companyId) throws Exception {
		Status status=new Status();
		
		if(ltExpExpenseLines.getLastUpdateLogin()==null || ltExpExpenseLines.getStartDate()==null  )
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());

			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
					
		}
		else
		{
				
				ltExpExpenseLines.setLastUpdateDate(new Date());
				ltExpExpenseLines.setLastUpdatedBy(ltExpExpenseLines.getLastUpdateLogin());
				ltExpExpenseLines = ltExpExpenseLinesDao.updateV1(ltExpExpenseLines);
				if(ltExpExpenseLines.getExpLineId()!=null)
				{
					if(ltExpExpenseLines.getExpHeaderId()!=null){
					if(ltExpExpenseHeadersDao.updateExpenseAmount(ltExpExpenseLines.getExpHeaderId()))
					{
					//-----------------------------------------------------------------------------
						if(files.length>0)
						{
							Status status1=imageUpload(files,ltExpExpenseLines,companyId);
							
							if(status1.getCode()== 1)
							{
//								LtMastFileUpload ltMastFileUpload = (LtMastFileUpload) status1.getData();
								LtExpenseAttachments ltExpenseAttachments = (LtExpenseAttachments) status1.getData();
								ltExpExpenseLines.setExpenseFileUploadId(ltExpenseAttachments.getExpenseAttachmentId() );
								ltExpExpenseLines = ltExpExpenseLinesDao.saveV1(ltExpExpenseLines);
//								status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
								status.setCode(1);		
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
					               if( status.getMessage()==null)
					               {
					            	status.setCode(1);
					            	status.setMessage("Error in finding message! The action is completed successfully.");
					               }
							}
							else
							{
//								status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
								status.setCode(0);		
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
								if(status.getMessage()==null)
								{
									status.setCode(0);
									status.setMessage("Error in finding message! The action was unsuccessful");
								}
								return status;
							}
							return status;
						}
					//-----------------------------------------------
						
//					 status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
						status.setCode(1);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
					 if( status.getMessage()==null)
					 {
						status.setCode(1);
						status.setMessage("Error in finding message! The action is completed successfully.");
					 }
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
						status.setCode(0);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
						if(status.getMessage()==null)
						{
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}
						return status;
					}
			  }else {
//				  status.setCode(1);		
//				  status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
//				  status.setData(ltExpExpenseLines.getExpLineId());
				  if(files.length>0)
					{
						Status status1=imageUpload(files,ltExpExpenseLines,companyId);
						
						if(status1.getCode()== 1)
						{
//							LtMastFileUpload ltMastFileUpload = (LtMastFileUpload) status1.getData();
							LtExpenseAttachments ltExpenseAttachments = (LtExpenseAttachments) status1.getData();
							ltExpExpenseLines.setExpenseFileUploadId(ltExpenseAttachments.getExpenseAttachmentId() );
							ltExpExpenseLines = ltExpExpenseLinesDao.saveV1(ltExpExpenseLines);
//							status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
							status.setCode(1);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
				               if( status.getMessage()==null)
				               {
				            	status.setCode(1);
				            	status.setMessage("Error in finding message! The action is completed successfully.");
				               }
						}
						else
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
							status.setCode(0);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
							if(status.getMessage()==null)
							{
								status.setCode(0);
								status.setMessage("Error in finding message! The action was unsuccessful");
							}
							return status;
						}
						return status;
					}
			  }
		  }
		}
		return status;
	}
	
	
	public Status imageUpload(MultipartFile[] files,LtExpExpenseLines ltExpExpenseLines,int companyId) throws Exception
	{
		System.out.println("In imageUpload method");
		Status status=new Status();
		String fileName;
		String saveDirectory=null;
		
		String filePath=null;
		System.out.println("Above sysVariableWithValues 1");
		SysVariableWithValues sysVariableWithValues=
				ltMastSysVariablesService.getBySysVariableName1("EXPENSE_ATTACHMENTS",companyId);
		
		//System.out.println("sysVariableWithValues 1 = "+sysVariableWithValues);
		
		
		if(sysVariableWithValues!=null)
		{
			if(sysVariableWithValues.getLtMastSysVariableValues().get(0)!=null)
			{
				System.out.println("Above file path");
				filePath=sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
				System.out.println("file path = "+filePath);
			}
			else
			{
				filePath=sysVariableWithValues.getLtMastSysVariables().getSystemValue();
			}
		}
		
		String openFilePath = null;
		sysVariableWithValues=
				ltMastSysVariablesService.getBySysVariableName1("EXPENSE_ATTACHMENTS",companyId);
		
		//System.out.println("sysVariableWithValues 2 = "+sysVariableWithValues);

		if(sysVariableWithValues!=null)
		{
			if(sysVariableWithValues.getLtMastSysVariableValues().get(0)!=null)
			{
				openFilePath=sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
			}
			else
			{
				openFilePath=sysVariableWithValues.getLtMastSysVariables().getSystemValue();
			}
		}
		File dir =new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		
		try {
			byte[] bytes = files[0].getBytes();

			Date cdate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
			String dateReportCreatedBy = formatter.format(cdate);
			
			String[] fileFrags = files[0].getOriginalFilename().split("\\.");
			String extension = fileFrags[fileFrags.length - 1];
			
		
			//fileName = fileFrags[0]+"-"+dateReportCreatedBy+"."+extension;
			fileName = fileFrags[0]+"."+extension;

			File serverFilePath = new File(new String(filePath));
			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+ fileName)));
							
			stream.write(bytes);
			stream.close();
			//------------------------------------- thumbnail img----------------------------------
			String thumbNailImgPath =  null;
			if(extension.equals("png")||extension.equals("PNG")||extension.equals("jpeg")||
					extension.equals("JPEG")||extension.equals("jpg")||extension.equals("JPG")){
			CreateThumbNailImg ob = new CreateThumbNailImg();
            //System.out.println("Thumbnail generation: " + filePath + "/" + fileName);
            //System.out.println("266 filePath = "+filePath);
            //System.out.println("267 fileName = "+fileName);
//			thumbNailImgPath = ob.readImg(filePath+"/"+fileName,fileName);
//			System.out.println("thumbNailImgPath "+thumbNailImgPath);
			}
			//-------------------------------------------------------------------------------------
			

//			LtMastFileUpload ltMastFileUploadObject = new LtMastFileUpload();
			LtExpenseAttachments ltExpenseAttachments = new LtExpenseAttachments();
			ltExpenseAttachments.setCreatedBy(ltExpExpenseLines.getCreatedBy());
			ltExpenseAttachments.setLastUpdateLogin(ltExpExpenseLines.getCreatedBy());
			ltExpenseAttachments.setLastUpdatedBy(ltExpExpenseLines.getCreatedBy());
			ltExpenseAttachments.setCreationDate(new Date());
			ltExpenseAttachments.setLastUpdateDate(new Date());
			ltExpenseAttachments.setFilePath(openFilePath+fileName);
	
			ltExpenseAttachments.setThumbnailFilePath(openFilePath+thumbNailImgPath);
			
			ltExpenseAttachments.setExpenceHeaderId(ltExpExpenseLines.getExpHeaderId());
			ltExpenseAttachments.setExpenceLineId(ltExpExpenseLines.getExpLineId());
			ltExpenseAttachments.setFileName(fileName);
			ltExpenseAttachments.setFileText(ocrService.extractText(files[0]));
			System.out.println("fileText = "+ocrService.extractText(files[0]));
			ltExpenseAttachments.setAttachmentType("Attachments");
			ltExpenseAttachments= ltMastFileUploadRepository.save(ltExpenseAttachments);
			if(ltExpenseAttachments.getExpenseAttachmentId()!=null){
//			status=ltMastCommonMessageService.getCodeAndMessage(FILE_UPLOADED_SUCESSFULLY);
				status.setCode(1);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("FILE_UPLOADED_SUCESSFULLY").getMessageName());
			status.setData(ltExpenseAttachments);
			}
			// }
		} catch (MaxUploadSizeExceededException e) {
			e.printStackTrace();
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
			

		} catch (Exception e) {
			e.printStackTrace();
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());

		}
		
		System.out.println("ststaus    "+status);
		return status;
	
	}
	
	@Override
	public Status saveV1(LtExpExpenseLines ltExpExpenseLines, MultipartFile[] files,int companyId) throws Exception
	{
		//System.out.println("In save v1 service");
		Status status=new Status();
		
		if(ltExpExpenseLines.getLastUpdateLogin()==null || ltExpExpenseLines.getStartDate()==null  )
		{
			System.out.println("In if condition");
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
					
		}
		else
		{
			System.out.println("in else condition");
				ltExpExpenseLines.setCreatedBy(ltExpExpenseLines.getLastUpdateLogin());
				ltExpExpenseLines.setLastUpdateDate(new Date());
				ltExpExpenseLines.setLastUpdatedBy(ltExpExpenseLines.getLastUpdateLogin());
				ltExpExpenseLines.setCreationDate(new Date());
				ltExpExpenseLines = ltExpExpenseLinesDao.saveV1(ltExpExpenseLines);
				
				System.out.println("Below ltExpExpenseLinesDao.saveV1");
				System.out.println("Line id = "+ltExpExpenseLines.getExpLineId());
				if(ltExpExpenseLines.getExpLineId()!=null)
				{
					if(ltExpExpenseLines.getExpHeaderId()!=null){
						//System.out.println("Headr id = "+ltExpExpenseLines.getExpHeaderId());
						//System.out.println("Result = "+ltExpExpenseHeadersDao.updateExpenseAmount(ltExpExpenseLines.getExpHeaderId()));
					if(ltExpExpenseHeadersDao.updateExpenseAmount(ltExpExpenseLines.getExpHeaderId()))
					{
					//-----------------------------------------------------------------------------
						//System.out.println("Files length = "+files.length);
						if(files.length>0 )
						{
							//System.out.println("Above image upload");
							Status status1=imageUpload(files,ltExpExpenseLines,companyId);
							//System.out.println("Below image upload");
							
							if(status1.getCode()== 1)
							{
								
//								LtMastFileUpload ltMastFileUpload = (LtMastFileUpload) status1.getData();
								LtExpenseAttachments ltExpenseAttachments = (LtExpenseAttachments) status1.getData();
								ltExpExpenseLines.setExpenseFileUploadId(ltExpenseAttachments.getExpenseAttachmentId() );
								ltExpExpenseLines = ltExpExpenseLinesDao.saveV1(ltExpExpenseLines);
//								status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
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
//								status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
								status.setCode(0);		
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
								if(status.getMessage()==null)
								{
									status.setCode(0);
									status.setMessage("Error in finding message! The action was unsuccessful");
								}
								return status;
							}
							return status;
						}
					//-----------------------------------------------
						
//					 status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
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
//						status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
						status.setCode(0);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());

						if(status.getMessage()==null)
						{
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}
						return status;
					}
			  }else {
//				  status.setCode(1);		
//				  status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
//				  status.setData(ltExpExpenseLines.getExpLineId());
				  if(files.length>0)
					{
						//System.out.println("Above image upload");
						Status status1=imageUpload(files,ltExpExpenseLines,companyId);
						//System.out.println("Below image upload");
						
						if(status1.getCode()== 1)
						{
							
//							LtMastFileUpload ltMastFileUpload = (LtMastFileUpload) status1.getData();
							LtExpenseAttachments ltExpenseAttachments = (LtExpenseAttachments) status1.getData();
							ltExpExpenseLines.setExpenseFileUploadId(ltExpenseAttachments.getExpenseAttachmentId() );
							ltExpExpenseLines = ltExpExpenseLinesDao.saveV1(ltExpExpenseLines);
//							status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
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
//							status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
							status.setCode(0);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
							if(status.getMessage()==null)
							{
								status.setCode(0);
								status.setMessage("Error in finding message! The action was unsuccessful");
							}
							return status;
						}
						return status;
					}
				  	status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());

				 if( status.getMessage()==null)
				 {
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				 }
			  }
		  }
		}
		return status;
	}
	
	@Transactional
	@Override
	public List<LtExpExpenseLines> getByExpenseHeaderId(Long expenseHeaderId) throws Exception 
	{
		List<LtExpExpenseLines> list= ltExpExpenseLinesDao.getByExpenseHeaderId(expenseHeaderId);
		return list;
	}
	
	@Override
	public LtExpExpenseLines getExpenseLinesByLineIdV1(Long lineId) throws Exception
	{
		LtExpExpenseLines ltExpExpenseLines= ltExpExpenseLinesDao.getExpenseLinesByLineIdV1(lineId);
		if(ltExpExpenseLines!=null)
		{
			if(ltExpExpenseLines.getCurrencyCode()!=null)
			{
				List<LtMastComnMasterValues> comnMasterValuesList=
						ltMastComnMasterValuesDao.findByValueCode(ltExpExpenseLines.getCurrencyCode());
				if(comnMasterValuesList!=null)
				{
					ltExpExpenseLines.setCurrencyCodeWithName(
							comnMasterValuesList.get(0).getValueName()+"("+ltExpExpenseLines.getCurrencyCode()+")");
				}
				
			}
		}
		return ltExpExpenseLines;
	}
	
	@Transactional
	@Override
	public Status delete(Long lineId) throws Exception
	{
		System.out.println("lineId = "+lineId);
		Status status=new Status();
		LtExpExpenseLines ltExpExpenseLines=ltExpExpenseLinesDao.getExpenseLinesByLineId(lineId);
		System.out.println("ltExpExpenseLines "+ltExpExpenseLines);
		System.out.println("Expense Line Id = "+ltExpExpenseLines.getExpLineId());
		if(ltExpExpenseLinesDao.delete(ltExpExpenseLines.getExpLineId(),null))
		{
			if(ltExpExpenseLines.getExpHeaderId()!=null)
			{
			if(ltExpExpenseHeadersDao.updateExpenseAmount(ltExpExpenseLines.getExpHeaderId()))
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
				status.setCode(1);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());

				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
			}
			else
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
				status.setCode(0);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());

				if(status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
				return status;
			}
			}else {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
				status.setData(ltExpExpenseLines.getExpLineId());
			}
			
		}
		else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
			status.setCode(0);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());

			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
	
	return status;
	}

	@Override
	public Status deleteExpenseLineAttachments(Long expLineId) {
		// TODO Auto-generated method stub
		Status status = new Status();
		System.out.println("expLineId = "+expLineId);
		try {
			if(ltExpExpenseLinesDao.deleteExpenseLineAttachments(expLineId)) {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_SUCCESS").getMessageName());
				status.setData(null);
			}
			else {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ATTACHMENT_DELETED_FAIL").getMessageName());
				status.setData(null);
			}
		}catch(Exception ex) {
			
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
				status.setData(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return status;
	}
	
	@Override
	public Status updateHeader(Long expHeaderId,List<Long> expLineIds) {
		// TODO Auto-generated method stub
		Status status = new Status();
		System.out.println("expHeaderId = "+expHeaderId);
		try {
			if(ltExpExpenseLinesDao.updateHeader(expHeaderId,expLineIds)) {
				if(ltExpExpenseHeadersDao.updateExpenseAmount(expHeaderId))
				{
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
				status.setData(null);
				}
				else {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
					status.setData(null);
				}
			}
			else {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
				status.setData(null);
			}
		}catch(Exception ex) {
			
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
				status.setData(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return status;
	}
	

}
