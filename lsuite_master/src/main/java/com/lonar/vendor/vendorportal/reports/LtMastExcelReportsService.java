package com.lonar.vendor.vendorportal.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastExcelReportsService {

	Status createExcelReport(ReportParameters reportParameters) throws ServiceException,IOException;
	
	List<LtMastMasterReportMap> getHeader(String masterName) throws ServiceException;

	Long getCount(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	Long getCount(LtMastSysRequests input, Long companyId) throws ServiceException;

	List<LtMastReportRequest> getReportRequestDataTableRecords(LtMastReportRequest input, Long companyId) throws ServiceException;
	
	List<LtMastSysRequests> getReportRequestDataTableRecords(LtMastSysRequests input, Long companyId) throws ServiceException;

	Status generateTravelExcelReport(ReportParameter reportParameter) throws ServiceException;

	Status generateExpenseExcelReport(ReportParameter reportParameter)throws ServiceException;

	Status generatePurchaseExcelReport(ReportParameter reportParameter)throws ServiceException;

	Status generateInvoicevspaymentExcelReport(ReportParameter reportParameter)throws ServiceException;

	Status generateRentalAgreementReport(ReportParameter reportParameter)throws ServiceException;

	Status generateExpiryRentalAgreementReport(ReportParameter reportParameter)throws ServiceException;

	Status generateVendorPaymentReport(ReportParameter reportParameter)throws ServiceException;

	Status generateVendorRegisterReport(ReportParameter reportParameter)throws ServiceException;

	Status generateVendorProgressReport(ReportParameter reportParameter)throws ServiceException;

	Status generateRentalInvoiceSummeryReport(ReportParameter reportParameter)throws ServiceException;

	Status generateRentalInvoiceDetailsReport(ReportParameter reportParameter)throws ServiceException;

	Status generateRentalAgreementInvoiceReport(ReportParameter reportParameter)throws ServiceException;

	LtMastReportRequest getAllParameters(Long requestId)throws Exception;

	List<ModuleDTO> getModulesByUserId(Long userId, Long companyId);

	String getVendorName(String vendorId);

	String getBuyerName(String buyerId);

	String getEmployeeName(String employeeId);

	String getDivisionName(String divisionId);



	


	



	


}
