package com.lonar.vendor.vendorportal.reports;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/API/reports")
public class LtMastExcelReportsRestController implements CodeMaster{
	
	@Autowired
	LtMastExcelReportsService ltMastExcelReportsService;
	
	@RequestMapping(value = "/excelReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> createExcelReport(@RequestBody ReportParameters reportParameters) throws ServiceException, IOException{
		Status status =  ltMastExcelReportsService.createExcelReport(reportParameters);
		return new ResponseEntity<Status>(status,HttpStatus.OK);
		
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
	
	@RequestMapping(value = "/dataTable/{companyId}/{userId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable DataTable(@PathVariable("companyId") Long companyId,@PathVariable("userId") Long userId, LtMastSysRequests input,@PathVariable("logTime") String logTime) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastExcelReportsService.getCount(input,companyId,userId);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastSysRequests> LtMastSysRequestsList = ltMastExcelReportsService.getReportRequestDataTableRecords(input,companyId,userId);
			customeDataTable.setData(LtMastSysRequestsList);

		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;

	}
	
	@RequestMapping(value = "/dataTableForLtMastReportRequest/{companyId}/{userId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable dataTableForLtMastReportRequest(@PathVariable("companyId") Long companyId, @PathVariable("userId") Long userId,LtMastReportRequest input,@PathVariable("logTime") String logTime) {
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltMastExcelReportsService.getCountForLtMastReportRequestDataTable(input,companyId,userId);
			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);
			List<LtMastReportRequest> LtMastSysRequestsList = ltMastExcelReportsService.getLtMastReportRequestDataTable(input,companyId,userId);
			customeDataTable.setData(LtMastSysRequestsList);

		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;

	}
	
	@RequestMapping(value = "/travelReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportTravelToExcel(@RequestBody ReportParameter reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateTravelExcelReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
	}
        
    @RequestMapping(value = "/expenseReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Status> exportExpenseToExcel(@RequestBody ReportParameter reportParameter) throws ServiceException, IOException {
   	    Status status = ltMastExcelReportsService.generateExpenseExcelReport(reportParameter);
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
    
    @RequestMapping(value = "/vendorRegisterReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportVendorRegisterToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
	    Status status = ltMastExcelReportsService.generateVendorRegisterReport(reportParameter);
        return new ResponseEntity<Status>(status,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/vendorProgressReport", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<Status> exportVendorProgressToExcel(@RequestBody  ReportParameter  reportParameter) throws ServiceException, IOException {
   	    Status status = ltMastExcelReportsService.generateVendorProgressReport(reportParameter);
           return new ResponseEntity<Status>(status,HttpStatus.OK);
   }
    
//    @RequestMapping("/getAllParameter/{requestId}/{logTime}")
//	public ResponseEntity<?> getAllParameters(@PathVariable Long requestId,@PathVariable String logTime) {
//	    try {
//	        // Fetch report request from service
//	        LtMastReportRequest reportRequest = ltMastExcelReportsService.getAllParameters(requestId);
// 
//	        if (reportRequest != null && reportRequest.getFilterData() != null) {
//	            ReportParameter reportParameter = new ReportParameter();
//	            reportParameter.setFilterData(reportRequest.getFilterData());
// 
//	            // Fetch additional details based on IDs
//	            reportParameter.setEmployee(ltMastExcelReportsService.getEmployeeName(reportParameter.getEmployeeId()));
//	            reportParameter.setDivision(ltMastExcelReportsService.getDivisionName(reportParameter.getDivisionId()));
//	            reportParameter.setVendorName(ltMastExcelReportsService.getVendorName(reportParameter.getVendorId()));
//	            reportParameter.setBuyerName(ltMastExcelReportsService.getBuyerName(reportParameter.getBuyerId()));
// 
//	            return ResponseEntity.ok(reportParameter);
//	        } else {
//	            return ResponseEntity.status(404).body("{\"message\": \"No data found for requestId: " + requestId + "\"}");
//	        }
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	        return ResponseEntity.status(500).body("{\"error\": \"Error processing request: " + e.getMessage() + "\"}");
//	    }
//	}
    
    @RequestMapping(value ="/getAllParameter/{requestId}/{logTime}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllParameters(@PathVariable Long requestId,@PathVariable String logTime) {
	    try {
	        LtMastReportRequest reportRequest = ltMastExcelReportsService.getAllParameters(requestId);
 
	        if (reportRequest != null && reportRequest.getFilterData() != null) {
	            ReportParameter reportParameter = new ReportParameter();
	            reportParameter.setFilterData(reportRequest.getFilterData());
	            reportParameter.setEmployeeName(ltMastExcelReportsService.getEmployeeName(reportParameter.getEmployeeId()));
	            reportParameter.setDivisionName(ltMastExcelReportsService.getDivisionName(reportParameter.getDivisionId()));
	            reportParameter.setVendorName(ltMastExcelReportsService.getVendorName(reportParameter.getVendorId()));
	            reportParameter.setBuyerName(ltMastExcelReportsService.getBuyerName(reportParameter.getBuyerId()));
 
	            return ResponseEntity.ok(reportParameter);
	        } else {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("message", "No data found for requestId: " + requestId);
 
	            return ResponseEntity.status(404).body(errorResponse); // ResponseEntity automatically converts this map to JSON
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
 
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("error", "Error processing request: " + e.getMessage());
 
	        return ResponseEntity.status(500).body(errorResponse); // ResponseEntity automatically converts this map to JSON
	    }
	}
    
	
	

}
