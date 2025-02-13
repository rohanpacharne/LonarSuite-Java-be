package com.lonar.vendor.vendorportal.reports;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastExcelReportsDao {

	List<LtMastMasterReportMap> getHeader(String masterName) throws ServiceException;

	Long getCount(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	Long getCount(LtMastSysRequests input, Long companyId) throws ServiceException;

	List<LtMastReportRequest> getReportRequestDataTableRecords(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	List<LtMastSysRequests> getReportRequestDataTableRecords(LtMastSysRequests input, Long companyId) throws ServiceException;

	List<ReportField> getTravelReportData(ReportParameter reportParameter) throws ServiceException;

	List<ReportField> getExpenseReportData(ReportParameter reportParameter) throws ServiceException;

	List<ReportField> getpurchaseReportData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getinvoiceReportData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getRentalAgreementReportData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getExpiryRentalAgreementReportData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getVendorPaymentData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getVendorRegisterData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getVendorProgressData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getRentalInvoiceSummeryData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getRentalInvoiceDetailsData(ReportParameter reportParameter)throws ServiceException;

	List<ReportField> getRentalAgreementInvoiceData(ReportParameter reportParameter)throws ServiceException;

	LtMastReportRequest findById(Long requestId)throws ServiceException;

	List<ModuleDTO> getModulesByUserId(Long userId, Long companyId);

	String findBuyerNameById(String buyerId);

	String findVendorNameById(String vendorId);

	String findDivisionNameById(String divisionId);

	String findEmployeeNameById(String employeeId);





}
