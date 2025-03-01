package com.lonar.vendor.vendorportal.reports;

import java.io.IOException;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

import java.io.ByteArrayOutputStream;

public interface LtMastExcelReportsService {

	Status createExcelReport(ReportParameters reportParameters) throws ServiceException,IOException;
	
	List<LtMastMasterReportMap> getHeader(String masterName) throws ServiceException;

	Long getCount(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	Long getCount(LtMastSysRequests input, Long companyId, Long userId) throws ServiceException;

	List<LtMastReportRequest> getReportRequestDataTableRecords(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	List<LtMastSysRequests> getReportRequestDataTableRecords(LtMastSysRequests input, Long companyId, Long userId) throws ServiceException;
	
	List<LtMastReportRequest> getLtMastReportRequestDataTable(LtMastReportRequest input, Long companyId,Long userId) throws ServiceException;

	Long getCountForLtMastReportRequestDataTable(LtMastReportRequest input, Long companyId,Long userId) throws ServiceException;
	
	ByteArrayOutputStream generateExcelReport(ReportParameter reportParameter) throws ServiceException;
	
	Status generateTravelExcelReport(ReportParameter reportParameter) throws ServiceException;
	
	Status generateExpenseExcelReport(ReportParameter reportParameter)throws ServiceException;
	
	Status generatePurchaseExcelReport(ReportParameter reportParameter)throws ServiceException;
	
	Status generateInvoicevspaymentExcelReport(ReportParameter reportParameter)throws ServiceException;
	 
	Status generateVendorRegisterReport(ReportParameter reportParameter)throws ServiceException;
	
	Status generateVendorProgressReport(ReportParameter reportParameter)throws ServiceException;
	 
	LtMastReportRequest getAllParameters(Long requestId)throws Exception;
		 
	String getVendorName(String vendorId);
 
	String getBuyerName(String buyerId);
 
	String getEmployeeName(String employeeId);
 
	String getDivisionName(String divisionId);
	 

}
