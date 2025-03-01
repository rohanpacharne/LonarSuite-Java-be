package com.lonar.vendor.vendorportal.reports;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastBranchesDao;
import com.lonar.vendor.vendorportal.dao.LtMastCostCentersDao;
import com.lonar.vendor.vendorportal.dao.LtMastDivisionsDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.dao.LtMastGlAccountsDao;
import com.lonar.vendor.vendorportal.dao.LtMastProdSubCategoriesDao;
import com.lonar.vendor.vendorportal.dao.LtMastProductCategoriesDao;
import com.lonar.vendor.vendorportal.dao.LtMastProductsDao;
import com.lonar.vendor.vendorportal.dao.LtMastStatesDao;
import com.lonar.vendor.vendorportal.dao.LtMastTaxesDao;
import com.lonar.vendor.vendorportal.dao.LtVendCompanyDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastBranches;
import com.lonar.vendor.vendorportal.model.LtMastCostCenters;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.LtMastGlAccounts;
import com.lonar.vendor.vendorportal.model.LtMastProdSubCategories;
import com.lonar.vendor.vendorportal.model.LtMastProductCategories;
import com.lonar.vendor.vendorportal.model.LtMastProducts;
import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastStates;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.LtMastTaxes;
import com.lonar.vendor.vendorportal.model.LtVendCompany;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
import com.lonar.vendor.vendorportal.service.LtMastSysVariablesService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

 
import javax.sql.DataSource;

@Service
public class LtMastExcelReportsServiceImpl implements LtMastExcelReportsService,CodeMaster{

	@Autowired
	LtMastProductsDao ltMastProductsDao;
	
	@Autowired
	LtMastExcelReportsDao ltMastExcelReportsDao;
	
	@Autowired
	LtMastCostCentersDao ltMastCostCentersDao;
	
	@Autowired
	LtMastBranchesDao ltMastBranchesDao;
	
	@Autowired
	LtMastTaxesDao ltMastTaxesDao;
	
	@Autowired
	LtMastDivisionsDao ltMastDivisionsDao;
	
	@Autowired
	LtMastGlAccountsDao ltMastGlAccountsDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtMastStatesDao ltMastStatesDao;
	
	@Autowired
	LtMastProdSubCategoriesDao ltMastProdSubCategoriesDao;
	
	@Autowired
	LtMastProductCategoriesDao ltMastProductCategoriesDao;
	
	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;
	
	@Autowired
	LtMastReportRequestRepository ltMastReportRequestRepository;
	
	@Autowired
	LtVendCompanyDao ltVendCompanyDao;
	
	List<String> colName =new ArrayList<String>();
	List<Object> objectList=new ArrayList<Object>();
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
	
	@Override
	public Status createExcelReport(ReportParameters reportParameters) throws ServiceException, IOException {
		System.out.println("in create excel report method");
		System.out.println("master name = "+reportParameters.getMasterName());
		Status status = new Status();
		String saveDirectory=null;;
		List<LtMastMasterReportMap> colNameList = ltMastExcelReportsDao.getHeader(reportParameters.getMasterName());
		System.out.println("colNameList = "+colNameList);
		for(LtMastMasterReportMap ltMastMasterReportMap:colNameList) {
			colName.add(ltMastMasterReportMap.getReportColName());
		}
		 
		 SysVariableWithValues sysVariableWithValues=
			ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH",reportParameters.getCompanyId());

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
		 
		if(reportParameters.getMasterName().equals("COSTCENTERS")) {
			List<LtMastCostCenters> excelData=new ArrayList<LtMastCostCenters>();
			 excelData =  ltMastCostCentersDao.getDataForReport(reportParameters);
			 
			 Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			 
			CostCenterThread costCenterThread = new CostCenterThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(costCenterThread);  
			t1.start(); 
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
			
		}else if(reportParameters.getMasterName().trim().equals("BRANCH")) {
			List<LtMastBranches> excelData=new ArrayList<LtMastBranches>();
			excelData =  ltMastBranchesDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			MultiThread multiThread = new MultiThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("EMPLOYEE")) {
			List<LtMastEmployees> excelData=new ArrayList<LtMastEmployees>();
			excelData =  ltMastEmployeesDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			EmployeeThread multiThread = new EmployeeThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("STATE")) {
			List<LtMastStates> excelData=new ArrayList<LtMastStates>();
			excelData =  ltMastStatesDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			StateThread multiThread = new StateThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("PRODUCT_CATEGORY")) {
			List<LtMastProductCategories> excelData=new ArrayList<LtMastProductCategories>();
			excelData =  ltMastProductCategoriesDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			ProductCategoryThread multiThread = new ProductCategoryThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("SUB_PRODUCT_CATEGORY")) {
			List<LtMastProdSubCategories> excelData=new ArrayList<LtMastProdSubCategories>();
			excelData =  ltMastProdSubCategoriesDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			SubProductCategoryThread multiThread = new SubProductCategoryThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("TAX")) {
			List<LtMastTaxes> excelData=new ArrayList<LtMastTaxes>();
			excelData =  ltMastTaxesDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			TaxMasterThread multiThread = new TaxMasterThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("GL_ACCOUNT")) {
			List<LtMastGlAccounts> excelData=new ArrayList<LtMastGlAccounts>();
			excelData =  ltMastGlAccountsDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			GlAccountThread multiThread = new GlAccountThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("DIVISIONS")) {
			List<DivisionSubDivision> excelData=new ArrayList<DivisionSubDivision>();
			excelData =  ltMastDivisionsDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			DivisionThread multiThread = new DivisionThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("PRODUCT")) {
			List<LtMastProducts> excelData=new ArrayList<LtMastProducts>();
			excelData =  ltMastProductsDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			ProductThread multiThread = new ProductThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}else if(reportParameters.getMasterName().trim().equals("COMPANY")) {
			List<LtVendCompany> excelData=new ArrayList<LtVendCompany>();
			excelData =  ltVendCompanyDao.getDataForReport(reportParameters);
			Long requestId = makeEntryForReport(saveDirectory,reportParameters);
			
			CompanyThread multiThread = new CompanyThread(requestId,colName,saveDirectory,excelData,ltMastReportRequestRepository);
			Thread t1 =new Thread(multiThread);  
			t1.start(); 
			
			status.setMessage("Report exctracting! Request ID is "+requestId);
			status.setCode(1);
			return status;
		}
		else {
			status.setMessage("Something went wrong ");
			status.setCode(0);
			return status;
		}
		//return null;
	}


	
	
	private Long makeEntryForReport(String saveDirectory, ReportParameters reportParameters) 
	{
		LtMastReportRequest ltMastReportRequest = new LtMastReportRequest();
		ltMastReportRequest.setFilterData(reportParameters.toString());
		ltMastReportRequest.setUserId(reportParameters.getLastUpdateLogin());
		ltMastReportRequest.setRequestName(reportParameters.getMasterName());
		ltMastReportRequest.setRequestDate(new Date());
		ltMastReportRequest.setStatus("Inprogress");
		ltMastReportRequest.setSubmittedDate(new Date());
		ltMastReportRequest.setStartDate(new Date());
		ltMastReportRequest = ltMastReportRequestRepository.save(ltMastReportRequest);
		return ltMastReportRequest.getRequestId();
	}




	private String createExcel(List<String> colName, List<LtMastCostCenters> excelData) throws ServiceException, IOException {
		return null;
	
		}
	

	
	
	@Override
	public List<LtMastMasterReportMap> getHeader(String masterName) throws ServiceException {
		
		List<LtMastMasterReportMap> colName = ltMastExcelReportsDao.getHeader(masterName);
		return colName;

		
	}
	
	


	@Override
	public Long getCount(LtMastReportRequest input,Long companyId) throws ServiceException {
		return ltMastExcelReportsDao.getCount(input,companyId);
	}




//	@Override
//	public List<LtMastReportRequest> getReportRequestDataTableRecords(LtMastReportRequest input,Long companyId)
//			throws ServiceException {
//		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
//		{
//			input.setColumnNo(11);
//		}
//		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
//		{
//			input.setColumnNo(12);
//		}
//		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
//		{
//			input.setColumnNo(13);
//		}
//		if(input.getColumnNo()==4 && input.getSort().equals("asc"))
//		{
//			input.setColumnNo(14);
//		}
//		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
//		{
//			input.setColumnNo(15);
//		}
//		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
//		{
//			input.setColumnNo(16);
//		}
//		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
//		{
//			input.setColumnNo(17);
//		}
//		return ltMastExcelReportsDao.getReportRequestDataTableRecords(input,companyId);
//	}
	
	@Override
	public List<LtMastReportRequest> getReportRequestDataTableRecords(LtMastReportRequest input,Long companyId)
			throws ServiceException {
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
		if(input.getColumnNo()==4 && input.getSort().equals("asc"))
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
		return ltMastExcelReportsDao.getReportRequestDataTableRecords(input,companyId);
	}




	@Override
	public List<LtMastSysRequests> getReportRequestDataTableRecords(LtMastSysRequests input, Long companyId,Long userId)
			throws ServiceException {
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
		if(input.getColumnNo()==4 && input.getSort().equals("asc"))
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
		return ltMastExcelReportsDao.getReportRequestDataTableRecords(input,companyId,userId);
	}




	@Override
	public Long getCount(LtMastSysRequests input, Long companyId,Long userId) throws ServiceException {
		// TODO Auto-generated method stub
		return ltMastExcelReportsDao.getCount(input,companyId,userId);
	}
	
	@Override
	public ByteArrayOutputStream generateExcelReport(ReportParameter reportParameter) throws ServiceException {
		 List<ReportField> results = ltMastExcelReportsDao.getExpenseReportData(reportParameter);
 
	        if (results == null || results.isEmpty()) {
	            throw new ServiceException(0, "No data found for the given date range", null);
	        }
 
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Expense Report");
 
	        String[] headers = {
	            "Expense Number", "Expense Date", "Employee ID", "Employee Name", "Header Start Date",
	            "Header End Date", "Division ID", "Division Name", "Branch Name", "Duration", "Status",
	            "Line Number", "Line Start Date", "Line End Date", "Expense Nature"
	        };
 
	        Row headerRow = sheet.createRow(0);
	        for (int i = 0; i < headers.length; i++) {
	            headerRow.createCell(i).setCellValue(headers[i]);
	        }
 
	        int rowNum = 1;
	        for (ReportField field : results) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(field.getExpenseNumber());
	            row.createCell(1).setCellValue(field.getExpenseDate() != null ? field.getExpenseDate().toString() : "");
	            row.createCell(2).setCellValue(field.getEmployeeId());
	            row.createCell(3).setCellValue(field.getEmployeeName());
	            row.createCell(4).setCellValue(field.getHdrStartDate() != null ? field.getHdrStartDate().toString() : "");
	            row.createCell(5).setCellValue(field.getHdrEndDate() != null ? field.getHdrEndDate().toString() : "");
	            row.createCell(6).setCellValue(field.getDivisionId());
	            row.createCell(7).setCellValue(field.getDivisionName());
	            row.createCell(8).setCellValue(field.getBranchName());
	            row.createCell(9).setCellValue(field.getDuration());
	            row.createCell(10).setCellValue(field.getStatus());
	            row.createCell(11).setCellValue(field.getLineNo());
	            row.createCell(12).setCellValue(field.getLineStartDate() != null ? field.getLineStartDate().toString() : "");
	            row.createCell(13).setCellValue(field.getLineEndDate() != null ? field.getLineEndDate().toString() : "");
	            row.createCell(14).setCellValue(field.getExpenseNature());
	        }
 
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        try {
	            workbook.write(out);
	            out.close();
	        } catch (IOException e) {
	            throw new ServiceException(0, "Error writing Excel file", e);
	        }
 
	        return out;
	    }




	@Override
	public List<LtMastReportRequest> getLtMastReportRequestDataTable(LtMastReportRequest input, Long companyId,Long userId)
			throws ServiceException {
		if(input.getColumnNo()==1 && input.getSort().equals("undefined"))
		{
			input.setColumnNo(12);
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
		return ltMastExcelReportsDao.getLtMastReportRequestDataTable(input,companyId,userId);
	}




	@Override
	public Long getCountForLtMastReportRequestDataTable(LtMastReportRequest input, Long companyId,Long userId)
			throws ServiceException {
		// TODO Auto-generated method stub
		return ltMastExcelReportsDao.getCountForLtMastReportRequestDataTable(input,companyId,userId);
	}

	@Override
	public Status generateTravelExcelReport(ReportParameter reportParameter) throws ServiceException {
	    Status status = new Status();
	    String saveDirectory = null;
	    
	    String[] colNameList = {
	    	    "Advance Number", "Advance Date",  "Employee Name", "Header Start Date",
	    	    "Header End Date",  "Division Name", "Branch Name", "Duration",  "Line Number",
	    	   "Line Start Date", "Line End Date","Status", "Advance Nature"
	    	};
//	    System.out.println("colNameList = " + Arrays.toString(colNameList));
 
    	// Convert array to a list and add each element to colName
    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));
 
//    	System.out.println("colName = " + colName);
 
	      	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
	    	if (sysVariableWithValues != null) {
	    	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	    	       } else {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
	       }
	    	   }
	    List<ReportField> excelData = ltMastExcelReportsDao.getTravelReportData(reportParameter);
//		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory, reportParameter,"Travel Expense Report");
	        TravelReportThread  travelReportThread = new  TravelReportThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
	        Thread t1 = new Thread( travelReportThread);
	        t1.start();
	        System.out.println("if if");
	        status.setMessage("Report extraction started! Request ID is " + requestId);
	        status.setCode(1);
	    } else {
	        status.setMessage("No data found for the specified report.");
	        status.setCode(0);
	    }
	    return status;
	}
	
	@Override
	public Status generateExpenseExcelReport(ReportParameter reportParameter) throws ServiceException {
	    Status status = new Status();
	    String saveDirectory =null;
	    
	    String[] colNameList = {
	    	    "Expense Number", "Expense Date", "Employee Name", "Header Start Date",
	    	    "Header End Date", "Division Name", "Branch Name", "Duration", "Line Number",
	    	    "Line Start Date", "Line End Date", "Status","Expense Nature"
	    	};
	    	System.out.println("colNameList = " + Arrays.toString(colNameList));
	 
	    	// Convert array to a list and add each element to colName
	    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));
	 
//	    	System.out.println("colName = " + colName);
	 
	    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
	    	if (sysVariableWithValues != null) {
		        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	    	       } else {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
	      }
	    	   }
	    List<ReportField> excelData = ltMastExcelReportsDao.getExpenseReportData(reportParameter);
//		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory, reportParameter,"Employee Expense Report");
	        ExpenseReportThread expenseReportThread = new ExpenseReportThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
	        Thread t1 = new Thread(expenseReportThread);
	        t1.start();
//	        System.out.println("if if");
	        status.setMessage("Report extraction started! Request ID is " + requestId);
	        status.setCode(1);
	    } else {
	        status.setMessage("No data found for the specified report.");
	        status.setCode(0);
	    }
	    return status;
	}
	 
	
	@Override
	public Status generatePurchaseExcelReport(ReportParameter reportParameter) throws ServiceException {
		 Status status = new Status();
		    String saveDirectory = null;
		    String[] colNameList =  { "PO Number", "PO Type", "PO Date", "Revision Number", "Revision Date", "Description", "Status",
		    	    "Note to Approver", "Vendor Name", "Vendor Address", "Vendor Contact", "Buyer Name", "Billing Address", "PO Currency",
		    	    "Line Number", "Line Type", "Product Code", "Product Name", "Quantity","Po Rate", "Tax Name", "Line Amount", "Tax Amount",
		    	    "Total Amount", "Term Name"
	 
		    	};
	 
		    	System.out.println("colNameList = " + Arrays.toString(colNameList));
	 
		    	// Convert array to a list and add each element to colName
		    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));
	 
//		    	System.out.println("colName = " + colName);
	 
		    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
		    	if (sysVariableWithValues != null) {
	    	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
		    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
		    	       } else {
		    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
		      }
		    	   }
		    List<ReportField> excelData = ltMastExcelReportsDao.getpurchaseReportData( reportParameter);
//			System.out.println("excelData = " + excelData);
		    if (excelData != null && !excelData.isEmpty()) {
		        Long requestId = makeEntryForReport(saveDirectory, reportParameter,"Purchase Order Register");
		        PurchaseReportThread purchaseReportThread = new PurchaseReportThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
		        Thread t1 = new Thread(purchaseReportThread);
		        t1.start();
		        status.setMessage("Report extraction started! Request ID is " + requestId);
		        status.setCode(1);
		    } else {
		        status.setMessage("No data found for the specified report.");
		        status.setCode(0);
		    }
		    return status;
		}
	
	@Override
	public Status generateInvoicevspaymentExcelReport(ReportParameter reportParameter) throws ServiceException {
		 Status status = new Status();
		    String saveDirectory = null;
		    String[] colNameList =
		      { "Invoice Number","Internal Invoice Number", "Invoice Type","Invoice Date", "Invoice Received Date","Description","Status","Initiator",  "Buyer","Vendor Name", "Address","Division",
	    	 "PO Number","PO Amount", "Billing Address", "Shipping Address", "Invoice Currency", "Exchange Rate", "Invoice Amount",
	    	"Line Number",  "Invoice Quantity","Invoice Rate", "Base Amount","Tax Amount", "Total Amount","Terms" };
	 
		    	System.out.println("colNameList = " + Arrays.toString(colNameList));
	 
		    	// Convert array to a list and add each element to colName
		    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));
	 
//		    	System.out.println("colName = " + colName);
		    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
		    	if (sysVariableWithValues != null) {
	    	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
		    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
		    	       } else {
		    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
		      }
		    	   }
		    List<ReportField> excelData = ltMastExcelReportsDao.getinvoiceReportData( reportParameter);
//			System.out.println("excelData = " + excelData);
		    if (excelData != null && !excelData.isEmpty()) {
		        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Invoices vs Payment Report");
		       InvoiceReportThread  invoiceReportThread = new InvoiceReportThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
		        Thread t1 = new Thread(invoiceReportThread);
		        t1.start();
		        status.setMessage("Report extraction started! Request ID is " + requestId);
		        status.setCode(1);
		    } else {
		        status.setMessage("No data found for the specified report.");
		        status.setCode(0);
		    }
		    return status;
		}
	
	@Override
	public Status generateVendorRegisterReport(ReportParameter reportParameter) throws ServiceException {
		Status status = new Status();
	    String saveDirectory = null;
	    String[] colNameList =	{
	    		  "Vendor code", "Vendor name", "Vendor type", "Vendor location type", "Pan no",
	    		  "Status", "Proprietor name", "Registration email", "Transaction email", "Start date",
	    		  "End date", "Msm supplier", "Msme category", "Msme registration no", "Company category",
	    		  "Business nature", "Initiator", "Division name", "Remark", "Address code", "Address line",
	    		  "City", "State name", "Primary address", "State code", "Country", "Va start date", "Va end date"
	    		};
	 
	 
	    	System.out.println("colNameList = " + Arrays.toString(colNameList));
	 
	    	// Convert array to a list and add each element to colName
	    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));
	 
	    	System.out.println("colName = " + colName);
	 
	    	System.out.println("colName = " + colName);
	    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
	    	if (sysVariableWithValues != null) {
		        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	    	       } else {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
	      }
	    	   }
	    List<ReportField> excelData = ltMastExcelReportsDao.getVendorRegisterData( reportParameter);
		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Vendor Registration and Onboarding Reports");
	        VendorRegisterThread   vendorRegisterThread= new   VendorRegisterThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
	        Thread t1 = new Thread(vendorRegisterThread);
	        t1.start();
	        status.setMessage("Report extraction started! Request ID is " + requestId);
	        status.setCode(1);
	    } else {
	        status.setMessage("No data found for the specified report.");
	        status.setCode(0);
	    }
	    return status;
	}
	
	@Override
	public Status generateVendorProgressReport(ReportParameter reportParameter) throws ServiceException {
		Status status = new Status();
	    String saveDirectory = null;
	    String[] colNameList =	{ "Vendor Name", "Status", "PAN No", "Email", "Start Date", "End Date",
	    		"Approver Name", "Approval Status", "Remarks", "Dated" };
	    	System.out.println("colNameList = " + Arrays.toString(colNameList));
	 
	    	// Convert array to a list and add each element to colName
	    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));
	 
	    	System.out.println("colName = " + colName);
	 
	    	System.out.println("colName = " + colName);
	    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
	    	if (sysVariableWithValues != null) {
		        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	    	       } else {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
	      }
	    	   }
	    List<ReportField> excelData = ltMastExcelReportsDao.getVendorProgressData( reportParameter);
		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Vendor Onboarding Progress Report");
	        VendorProgressThread    vendorProgressThread = new   VendorProgressThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
	        Thread t1 = new Thread( vendorProgressThread );
	        t1.start();
	        status.setMessage("Report extraction started! Request ID is " + requestId);
	        status.setCode(1);
	    } else {
	        status.setMessage("No data found for the specified report.");
	        status.setCode(0);
	    }
	    return status;
	}
 
	private Long makeEntryForReport(String saveDirectory, ReportParameter reportParameter, String requestName) {
	    LtMastReportRequest ltMastReportRequest = new LtMastReportRequest();
	    ltMastReportRequest.setUserId(reportParameter.getUserId());
	    ltMastReportRequest.setFilterData(reportParameter.toString());
	    ltMastReportRequest.setRequestName(requestName);
	    ltMastReportRequest.setRequestDate(new Date());
	    ltMastReportRequest.setStatus("Normal");
	    ltMastReportRequest.setPhase("PENDING") ;//phase = PENDING
	    ltMastReportRequest.setType("REPORT") ;//type = REPORT
	    ltMastReportRequest.setSubmittedDate(new Date());
	    ltMastReportRequest.setStartDate(new Date());
	    ltMastReportRequest = ltMastReportRequestRepository.save(ltMastReportRequest);
	    return ltMastReportRequest.getRequestId();
	}
	
	@Override
	public LtMastReportRequest getAllParameters(Long requestId) throws Exception{
	    return ltMastExcelReportsDao.findById(requestId);
	}
	 
	@Override
	public String getEmployeeName(String employeeId) {
	    return ltMastExcelReportsDao.findEmployeeNameById(employeeId);
	}
	 
	@Override
	public String getDivisionName(String divisionId) {
	    return ltMastExcelReportsDao.findDivisionNameById(divisionId);
	}
	 
	@Override
	public String getVendorName(String vendorId) {
	    return ltMastExcelReportsDao.findVendorNameById(vendorId);
	}
	 
	@Override
	public String getBuyerName(String buyerId) {
	    return ltMastExcelReportsDao.findBuyerNameById(buyerId);
	}
	
	
	

}
