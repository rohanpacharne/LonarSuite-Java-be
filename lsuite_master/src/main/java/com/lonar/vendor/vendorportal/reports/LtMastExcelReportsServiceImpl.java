package com.lonar.vendor.vendorportal.reports;


import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Service
@PropertySource(value = "classpath:queries/reportQueries.properties", ignoreResourceNotFound = true)
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
	
	@Autowired
	private Environment env;

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	

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
		}else if(reportParameters.getMasterName().trim().equals("DIVISION")) {
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
		ltMastReportRequest.setType("Report");
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
	public List<LtMastSysRequests> getReportRequestDataTableRecords(LtMastSysRequests input, Long companyId)
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
		if(input.getColumnNo()==8 && input.getSort().equals("desc"))
		{
			input.setColumnNo(18);
		}
		if(input.getColumnNo()==9 && input.getSort().equals("desc"))
		{
			input.setColumnNo(19);
		}
		return ltMastExcelReportsDao.getReportRequestDataTableRecords(input,companyId);
	}




	@Override
	public Long getCount(LtMastSysRequests input, Long companyId) throws ServiceException {
		return ltMastExcelReportsDao.getCount(input,companyId);
	}

	@Override
	public Status generateTravelExcelReport(ReportParameter reportParameter) throws ServiceException {
	    Status status = new Status();
	    String saveDirectory = "C://po//";
	    
	    String[] colNameList = {
	    	    "Advance Number", "Advance Date",  "Employee Name", "Header Start Date", 
	    	    "Header End Date",  "Division Name", "Branch Name", "Duration",  "Line Number", 
	    	   "Line Start Date", "Line End Date","Status", "Advance Nature"
	    	};
	    System.out.println("colNameList = " + Arrays.toString(colNameList));

    	// Convert array to a list and add each element to colName
    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));

    	System.out.println("colName = " + colName);

	     // 	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
	 //   	if (sysVariableWithValues != null) {
	   // 	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	    	//           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	  //  	       } else {
	    //	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
	    //   }
	    //	   }
	    List<ReportField> excelData = ltMastExcelReportsDao.getTravelReportData(reportParameter);
		System.out.println("excelData = " + excelData);
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

    	System.out.println("colName = " + colName);

    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
    	if (sysVariableWithValues != null) {
	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
    	       } else {
    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
      }
    	   }
    List<ReportField> excelData = ltMastExcelReportsDao.getExpenseReportData(reportParameter);
	System.out.println("excelData = " + excelData);
    if (excelData != null && !excelData.isEmpty()) {
        Long requestId = makeEntryForReport(saveDirectory, reportParameter,"Employee Expense Report");
        ExpenseReportThread expenseReportThread = new ExpenseReportThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
        Thread t1 = new Thread(expenseReportThread);
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

	    	System.out.println("colName = " + colName);

	    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
	    	if (sysVariableWithValues != null) {
    	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	    	       } else {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
	      }
	    	   }
	    List<ReportField> excelData = ltMastExcelReportsDao.getpurchaseReportData( reportParameter);
		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Purchase Order Register");
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

	    	System.out.println("colName = " + colName);
	    	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
	    	if (sysVariableWithValues != null) {
    	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	    	       } else {
	    	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
	      }
	    	   }
	    List<ReportField> excelData = ltMastExcelReportsDao.getinvoiceReportData( reportParameter);
		System.out.println("excelData = " + excelData);
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
public Status generateRentalAgreementReport(ReportParameter reportParameter) throws ServiceException {
	 Status status = new Status();
	    String saveDirectory = "C:\\po\\";
	    String[] colNameList =
	    	{
    			    "Agreement Number","Initiator", "Vendor Name", "Address","Division Name","Rental Type", "Rent Frequency","Rent Frequency Value",
    			    "Rent Currency","Base Rent", "Property Title", "Property Type","Property City", "Property Address","State", 
    			    "From Date", "To Date", "Location Code", "Billing Address", 
                    "Security Deposit", "SD Payment Ref", "SD Payment Date", 
    			    "Notice Period", "Rent Due Day", "Lock-In Period",
    			   "Termination Date", "Status",  "Line Number",  "Line From Date",
    			    "Line To Date","Line Base Rent", "Rent Escalation Percentage", "Rent Amount", "Tax Amount", "Total Amount", "Remark"
    };
	    	System.out.println("colNameList = " + Arrays.toString(colNameList));

	    	// Convert array to a list and add each element to colName
	    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));

	    	System.out.println("colName = " + colName);

//	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
//	if (sysVariableWithValues != null) {
//	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	//            saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	//        } else {
//	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
 //   }
	//    }
	    List<ReportField> excelData = ltMastExcelReportsDao.getRentalAgreementReportData( reportParameter);
		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Rental Agreement Register");
	        RentalAgreementThread  rentalAgreementThread  = new    RentalAgreementThread (requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
	        Thread t1 = new Thread(rentalAgreementThread );
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
public Status generateExpiryRentalAgreementReport(ReportParameter reportParameter) throws ServiceException {
	 Status status = new Status();
	    String saveDirectory = "C:\\po\\";
	    String[] colNameList ={
			    "Agreement Number","Initiator", "Vendor Name", "Address","Division Name","Rental Type", "Rent Frequency","Rent Frequency Value",
			    "Rent Currency","Base Rent", "Property Title", "Property Type","Property City", "Property Address","State", 
			    "From Date", "To Date", "Location Code", "Billing Address", 
                "Security Deposit", "SD Payment Ref", "SD Payment Date", 
			    "Notice Period", "Rent Due Day", "Lock-In Period",
			   "Termination Date", "Status",  "Line Number",  "Line From Date",
			    "Line To Date","Line Base Rent", "Rent Escalation Percentage", "Rent Amount", "Tax Amount", "Total Amount", "Remark"
};

	    	System.out.println("colNameList = " + Arrays.toString(colNameList));

	    	// Convert array to a list and add each element to colName
	    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));

	    	System.out.println("colName = " + colName);

//	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
//	if (sysVariableWithValues != null) {
//	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	//            saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	//        } else {
//	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
//   }
	//    }
	    List<ReportField> excelData = ltMastExcelReportsDao.getExpiryRentalAgreementReportData( reportParameter);
		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Rental Agreement Expiry Report");
	        RentalAgreementExpiryThread    rentalAgreementExpiryThread = new   RentalAgreementExpiryThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
	        Thread t1 = new Thread( rentalAgreementExpiryThread);
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
public Status generateVendorPaymentReport(ReportParameter reportParameter) throws ServiceException {
	 Status status = new Status();
	    String saveDirectory = "C:\\po\\";
	    String[] colNameList =	{
			    "Agreement Number","Initiator", "Vendor Name", "Address","Division Name","Rental Type", "Rent Frequency","Rent Frequency Value",
			    "Base Rent", "Property Title", "Property Type","Property City", "Property Address","State", 
			    "From Date", "To Date", "Location Code", "Billing Address", 
                "Security Deposit", "SD Payment Ref", "SD Payment Date", "Rent Currency",
			    "Notice Period", "Rent Due Day", "Lock-In Period",
			   "Termination Date", "Status",  "Line Number",  "Line From Date",
			    "Line To Date","Line Base Rent", "Rent Escalation Percentage", "Rent Amount", "Tax Amount", "Total Amount", "Remark"
};
	    	System.out.println("colNameList = " + Arrays.toString(colNameList));

	    	// Convert array to a list and add each element to colName
	    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));

	    	System.out.println("colName = " + colName);

//	SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
//	if (sysVariableWithValues != null) {
//	        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
	//            saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
	//        } else {
//	           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
//}
	//    }
	    List<ReportField> excelData = ltMastExcelReportsDao.getVendorPaymentData( reportParameter);
		System.out.println("excelData = " + excelData);
	    if (excelData != null && !excelData.isEmpty()) {
	        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Vendor Payment History Report");
	        VendorPaymentThread   vendorPaymentThread= new   VendorPaymentThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
	        Thread t1 = new Thread(vendorPaymentThread);
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

@Override
public Status generateRentalInvoiceSummeryReport(ReportParameter reportParameter) throws ServiceException {
	Status status = new Status();
    String saveDirectory = "C:\\po\\";
    String[] colNameList =	{ "Invoice_Number", "Internal_Invoice_Number", "Invoice_Type", "Invoice_Date", 
    		"Description", "Status", "Initiator", "Vendor_Name", "Address", "Division", "Agreement_Number",
    		"Source", "Source_Ref_Number", "Billing_Address", "Shipping_Address", "Invoice_Currency", 
    		"Exchange_Rate", "Invoice_Amount" };
    	System.out.println("colNameList = " + Arrays.toString(colNameList));

    	// Convert array to a list and add each element to colName
    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));

    	System.out.println("colName = " + colName);

//SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
//if (sysVariableWithValues != null) {
//        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
//            saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
//        } else {
//           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
//}
//    }
    List<ReportField> excelData = ltMastExcelReportsDao.getRentalInvoiceSummeryData( reportParameter);
	System.out.println("excelData = " + excelData);
    if (excelData != null && !excelData.isEmpty()) {
        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Rental Invoice Summary Report");
        RentalInvoiceSummeryThread    rentalInvoiceSummeryThread = new  RentalInvoiceSummeryThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
        Thread t1 = new Thread( rentalInvoiceSummeryThread );
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
public Status generateRentalInvoiceDetailsReport(ReportParameter reportParameter) throws ServiceException {
	Status status = new Status();
    String saveDirectory = "C:\\po\\";
    String[] colNameList = { "Invoice_Number", "Internal_Invoice_Number", "Invoice_Type", "Invoice_Date", 
    		"Description", "Status", "Initiator", "Vendor_Name", "Address", "Division", "Agreement_Number",
    		"Source", "Source_Ref_Number", "Billing_Address", "Shipping_Address", "Invoice_Currency", 
    		"Exchange_Rate", "Invoice_Amount", "Line_No", "Invoice_Qty", "Invoice_Rate", "Base_Amount",
    		"Tax_Amount", "Total_Amount", "Terms" };

    	System.out.println("colNameList = " + Arrays.toString(colNameList));

    	// Convert array to a list and add each element to colName
    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));

    	System.out.println("colName = " + colName);

//SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
//if (sysVariableWithValues != null) {
//        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
//            saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
//        } else {
//           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
//}
//    }
    List<ReportField> excelData = ltMastExcelReportsDao.getRentalInvoiceDetailsData( reportParameter);
	System.out.println("excelData = " + excelData);
    if (excelData != null && !excelData.isEmpty()) {
        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Rental Invoice Details Report");
        RentalInvoiceDetailsThread   rentalInvoiceDetailsThread = new  RentalInvoiceDetailsThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
        Thread t1 = new Thread(rentalInvoiceDetailsThread);
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
public Status generateRentalAgreementInvoiceReport(ReportParameter reportParameter) throws ServiceException {
	Status status = new Status();
    String saveDirectory = "C:\\po\\";
    String[] colNameList = { "Invoice_Number", "Internal_Invoice_Number", "Invoice_Type", "Invoice_Date", 
    		"Description", "Status", "Initiator", "Vendor_Name", "Address", "Division", "Agreement_Number",
    		"Source", "Source_Ref_Number", "Billing_Address", "Shipping_Address", "Invoice_Currency", 
    		"Exchange_Rate", "Invoice_Amount", "Line_No", "Invoice_Qty", "Invoice_Rate", "Base_Amount",
    		"Tax_Amount", "Total_Amount", "Terms" };

    	System.out.println("colNameList = " + Arrays.toString(colNameList));

    	// Convert array to a list and add each element to colName
    	List<String> colName = new ArrayList<>(Arrays.asList(colNameList));

    	System.out.println("colName = " + colName);

//SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("EXCEL_FILE_UPLOAD_FOLDER_PATH", reportParameter.getCompanyId());
//if (sysVariableWithValues != null) {
//        if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
//            saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
//        } else {
//           saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
//}
//    }
    List<ReportField> excelData = ltMastExcelReportsDao.getRentalAgreementInvoiceData( reportParameter);
	System.out.println("excelData = " + excelData);
    if (excelData != null && !excelData.isEmpty()) {
        Long requestId = makeEntryForReport(saveDirectory,  reportParameter,"Venderwise Rental Agreement Invoices Report");
        RentalAgreementInvoiceThread  rentalAgreementInvoiceThread = new RentalAgreementInvoiceThread(requestId, colName, saveDirectory, excelData, ltMastReportRequestRepository);
        Thread t1 = new Thread(rentalAgreementInvoiceThread);
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
    ltMastReportRequest.setRequestName( requestName);
    ltMastReportRequest.setRequestDate(new Date());
    ltMastReportRequest.setStatus("Normal");
    ltMastReportRequest.setPhase("PENDING") ;
    ltMastReportRequest.setType("Report") ; //phase = PENDING 
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



@Override
public List<ModuleDTO> getModulesByUserId(Long userId, Long companyId) {
	 return ltMastExcelReportsDao.getModulesByUserId(userId,companyId);
	
}





}

