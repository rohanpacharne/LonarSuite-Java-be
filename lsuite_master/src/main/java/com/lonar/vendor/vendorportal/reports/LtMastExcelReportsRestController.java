package com.lonar.vendor.vendorportal.reports;
import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@RestController
@RequestMapping("/API/reports")
public class LtMastExcelReportsRestController implements CodeMaster{
	
	@Autowired
	LtMastExcelReportsService ltMastExcelReportsService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(value = "/excelReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> createExcelReport(@RequestBody ReportParameters reportParameters) throws ServiceException, IOException{
		Status status =  ltMastExcelReportsService.createExcelReport(reportParameters);
		return new ResponseEntity<Status>(status,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/expenseReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportExpenseToExcel(@RequestBody ReportParameter reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateExpenseExcelReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }
	
	
	
	@RequestMapping(value = "/travelReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportTravelToExcel(@RequestBody ReportParameter reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateTravelExcelReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }
	
	
	@RequestMapping(value = "/purchaseReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportPurchaseToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generatePurchaseExcelReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }
	
	@RequestMapping(value = "/invoicevspaymentReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportInvoiceToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateInvoicevspaymentExcelReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }
	@RequestMapping(value = "/vendorpaymentReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportVendorPaymentToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateVendorPaymentReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }
	

	@RequestMapping(value = "/vendorProgressReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportVendorProgressToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateVendorProgressReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }	
	@RequestMapping(value = "/vendorRegisterReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportVendorRegisterToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateVendorRegisterReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }

	@RequestMapping(value = "/rentalAgreementReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportRentalAgreementToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateRentalAgreementReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }
	//Expiry rental agreements
	@RequestMapping(value = "/expiryRentalAgreementReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportExpiryRentalAgreementToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateExpiryRentalAgreementReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }
	
	//rental invoice headers Data
	@RequestMapping(value = "/rentalInvoiceSummeryReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportRentalInvoiceSummeryToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateRentalInvoiceSummeryReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }	

   // rental invoice headers with 
	@RequestMapping(value = "/rentalInvoiceDetailsReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportRentalInvoiceDetailsToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateRentalInvoiceDetailsReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }	
	

	@RequestMapping(value = "/rentalAgreementInvoiceReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportRentalAgreementInvoiceToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateRentalAgreementInvoiceReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
 }	
		
	
	@RequestMapping("/getAllParameter/{requestId}")
	public ResponseEntity<?> getAllParameters(@PathVariable Long requestId) {
	    try {
	        // Fetch report request from service
	        LtMastReportRequest reportRequest = ltMastExcelReportsService.getAllParameters(requestId);

	        if (reportRequest != null && reportRequest.getFilterData() != null) {
	            ReportParameter reportParameter = new ReportParameter();
	            reportParameter.setFilterData(reportRequest.getFilterData());

	            // Fetch additional details based on IDs
	            reportParameter.setEmployeeName(ltMastExcelReportsService.getEmployeeName(reportParameter.getEmployeeId()));
	            reportParameter.setDivisionName(ltMastExcelReportsService.getDivisionName(reportParameter.getDivisionId()));
	            reportParameter.setVendorName(ltMastExcelReportsService.getVendorName(reportParameter.getVendorId()));
	            reportParameter.setBuyerName(ltMastExcelReportsService.getBuyerName(reportParameter.getBuyerId()));

	            return ResponseEntity.ok(reportParameter);
	        } else {
	            return ResponseEntity.status(404).body("{\"message\": \"No data found for requestId: " + requestId + "\"}");
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return ResponseEntity.status(500).body("{\"error\": \"Error processing request: " + e.getMessage() + "\"}");
	    }
	}

	

	 @RequestMapping(value = "/getReports/{companyId}/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<ModuleDTO>> getModulesByUserId(@PathVariable Long userId,@PathVariable Long companyId) {
	        List<ModuleDTO> response = ltMastExcelReportsService.getModulesByUserId(userId, companyId);
	        if (response.isEmpty()) {
	            return ResponseEntity.notFound().build();  
	        }
	        return ResponseEntity.ok(response);  
	    }
	

	
	
//	@RequestMapping(value = "/dataTable/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public CustomeDataTable DataTable(@PathVariable("companyId") Long companyId, LtMastReportRequest input,@PathVariable("logTime") String logTime) {
//		CustomeDataTable customeDataTable = new CustomeDataTable();
//		try {
//			Long count = ltMastExcelReportsService.getCount(input,companyId);
//			customeDataTable.setRecordsTotal(count);
//			customeDataTable.setRecordsFiltered(count);
//			List<LtMastReportRequest> ltMastReportRequestList = ltMastExcelReportsService.getReportRequestDataTableRecords(input,companyId);
//			customeDataTable.setData(ltMastReportRequestList);
//
//		} catch (Exception e) {
//			throw new BusinessException(0, null, e);
//		}
//		return customeDataTable;
//
//	}
	
	@RequestMapping(value = "/dataTable/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable DataTable(@PathVariable("companyId") Long companyId, LtMastSysRequests input,@PathVariable("logTime") String logTime) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastExcelReportsService.getCount(input,companyId);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastSysRequests> LtMastSysRequestsList = ltMastExcelReportsService.getReportRequestDataTableRecords(input,companyId);
			customeDataTable.setData(LtMastSysRequestsList);

		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;

	}

}
