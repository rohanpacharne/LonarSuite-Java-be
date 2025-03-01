package com.lonar.vendor.vendorportal.reports;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastExcelReportsDao {

	List<LtMastMasterReportMap> getHeader(String masterName) throws ServiceException;

	Long getCount(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	Long getCount(LtMastSysRequests input, Long companyId,Long count) throws ServiceException;

	List<LtMastReportRequest> getReportRequestDataTableRecords(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	List<LtMastSysRequests> getReportRequestDataTableRecords(LtMastSysRequests input, Long companyId, Long userId) throws ServiceException;
	
	List<LtMastReportRequest> getLtMastReportRequestDataTable(LtMastReportRequest input, Long companyId,Long userId) throws ServiceException;
	
	Long getCountForLtMastReportRequestDataTable(LtMastReportRequest input, Long companyId,Long userId) throws ServiceException;
	
	List<ReportField> getExpenseReportData(ReportParameter reportParameter) throws ServiceException;
	
	List<ReportField> getTravelReportData(ReportParameter reportParameter) throws ServiceException;
	
	List<ReportField> getpurchaseReportData(ReportParameter reportParameter)throws ServiceException;
	
	List<ReportField> getinvoiceReportData(ReportParameter reportParameter)throws ServiceException;
	
	List<ReportField> getVendorRegisterData(ReportParameter reportParameter)throws ServiceException;
	
	List<ReportField> getVendorProgressData(ReportParameter reportParameter)throws ServiceException;
	
	LtMastReportRequest findById(Long requestId)throws ServiceException;
		 
	String findBuyerNameById(String buyerId);
 
	String findVendorNameById(String vendorId);
 
	String findDivisionNameById(String divisionId);
 
	String findEmployeeNameById(String employeeId);
	 
	 






}
