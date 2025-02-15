package com.lonar.vendor.vendorportal.requests;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.jbig2.util.log.Logger;
import org.apache.pdfbox.jbig2.util.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.requests.LtMastCSVRequestsDao;
import com.lonar.vendor.vendorportal.requests.LtMastReportRequestRepo;
@Service
public class LtMastCSVRequestsServiceImpl implements LtMastCSVRequestsService,CodeMaster{ 
	
	
@Autowired
LtMastReportRequestRepo ltMastReportRequestRepository;

@Autowired
LtMastCSVRequestsDao ltMastCSVRequestsDao;

@Override
	public Status exportVendorToErp(RequestParameters requestParameters) throws ServiceException {
		    Status status = new Status();
		    String saveDirectory = "C:\\po\\";  

		    try {
////		         Define CSV column headers
//		        List<String> colName = Arrays.asList(
////		        		"Batch_Id", "Import_Action", "Supplier_Name", "Supplier_New_Name", "Supplier_Number",
////		        		"Alternate_Name", "Tax_Organization_Type", "Supplier_Type", "Inactive_Date",
////		        		"Business_Relationship", "Parent_Supplier", "Alias", "Duns_Number", "One_Time_Supplier",
////		        		"Customer_Number", "Sic", "National_Insurance_Number", "Corporate_Web_Site",
////		        		"Chief_Executive_Title", "Chief_Executive_Name", "Business_Classifications_Not_Applicable",
////		        		"Taxpayer_Country", "Taxpayer_Id", "Federal_Reportable", "Federal_Income_Tax_Type",
////		        		"State_Reportable", "Tax_Reporting_Name", "Name_Control", "Tax_Verification_Date",
////		        		"Use_Withholding_Tax", "Withholding_Tax_Group", "Vat_Code", "Tax_Registration_Number",
////		        		"Auto_Tax_Calc_Override", "Payment_Method", "Delivery_Channel", "Bank_Instruction_1",
////		        		"Bank_Instruction_2", "Bank_Instruction", "Settlement_Priority", "Payment_Text_Message_1",
////		        		"Payment_Text_Message_2", "Payment_Text_Message_3", "Bank_Charge_Bearer", "Payment_Reason",
////		        		"Payment_Reason_Comments", "Payment_Format", "ATTRIBUTE_CATEGORY", "ATTRIBUTE1",
////		        		"ATTRIBUTE2", "ATTRIBUTE3", "ATTRIBUTE4", "ATTRIBUTE5", "ATTRIBUTE6", "ATTRIBUTE7",
////		        		"ATTRIBUTE8", "ATTRIBUTE9", "ATTRIBUTE10", "ATTRIBUTE11", "ATTRIBUTE12", "ATTRIBUTE13",
////		        		"ATTRIBUTE14", "ATTRIBUTE15", "ATTRIBUTE16", "ATTRIBUTE17", "ATTRIBUTE18", "ATTRIBUTE19",
////		        		"ATTRIBUTE20", "ATTRIBUTE_DATE1", "ATTRIBUTE_DATE2", "ATTRIBUTE_DATE3", "ATTRIBUTE_DATE4",
////		        		"ATTRIBUTE_DATE5", "ATTRIBUTE_DATE6", "ATTRIBUTE_DATE7", "ATTRIBUTE_DATE8", "ATTRIBUTE_DATE9",
////		        		"ATTRIBUTE_DATE10", "ATTRIBUTE_TIMESTAMP1", "ATTRIBUTE_TIMESTAMP2", "ATTRIBUTE_TIMESTAMP3",
////		        		"ATTRIBUTE_TIMESTAMP4", "ATTRIBUTE_TIMESTAMP5", "ATTRIBUTE_TIMESTAMP6", "ATTRIBUTE_TIMESTAMP7",
////		        		"ATTRIBUTE_TIMESTAMP8", "ATTRIBUTE_TIMESTAMP9", "ATTRIBUTE_TIMESTAMP10", "ATTRIBUTE_NUMBER1",
////		        		"ATTRIBUTE_NUMBER2", "ATTRIBUTE_NUMBER3", "ATTRIBUTE_NUMBER4", "ATTRIBUTE_NUMBER5",
////		        		"ATTRIBUTE_NUMBER6", "ATTRIBUTE_NUMBER7", "ATTRIBUTE_NUMBER8", "ATTRIBUTE_NUMBER9",
////		        		"ATTRIBUTE_NUMBER10", "GLOBAL_ATTRIBUTE_CATEGORY", "GLOBAL_ATTRIBUTE1", "GLOBAL_ATTRIBUTE2",
////		        		"GLOBAL_ATTRIBUTE3", "GLOBAL_ATTRIBUTE4", "GLOBAL_ATTRIBUTE5", "GLOBAL_ATTRIBUTE6",
////		        		"GLOBAL_ATTRIBUTE7", "GLOBAL_ATTRIBUTE8", "GLOBAL_ATTRIBUTE9", "GLOBAL_ATTRIBUTE10",
////		        		"GLOBAL_ATTRIBUTE11", "GLOBAL_ATTRIBUTE12", "GLOBAL_ATTRIBUTE13", "GLOBAL_ATTRIBUTE14",
////		        		"GLOBAL_ATTRIBUTE15", "GLOBAL_ATTRIBUTE16", "GLOBAL_ATTRIBUTE17", "GLOBAL_ATTRIBUTE18",
////		        		"GLOBAL_ATTRIBUTE19", "GLOBAL_ATTRIBUTE20", "GLOBAL_ATTRIBUTE_DATE1", "GLOBAL_ATTRIBUTE_DATE2",
////		        		"GLOBAL_ATTRIBUTE_DATE3", "GLOBAL_ATTRIBUTE_DATE4", "GLOBAL_ATTRIBUTE_DATE5",
////		        		"GLOBAL_ATTRIBUTE_DATE6", "GLOBAL_ATTRIBUTE_DATE7", "GLOBAL_ATTRIBUTE_DATE8",
////		        		"GLOBAL_ATTRIBUTE_DATE9", "GLOBAL_ATTRIBUTE_DATE10", "GLOBAL_ATTRIBUTE_TIMESTAMP1",
////		        		"GLOBAL_ATTRIBUTE_TIMESTAMP2", "GLOBAL_ATTRIBUTE_TIMESTAMP3", "GLOBAL_ATTRIBUTE_TIMESTAMP4",
////		        		"GLOBAL_ATTRIBUTE_TIMESTAMP5", "GLOBAL_ATTRIBUTE_TIMESTAMP6", "GLOBAL_ATTRIBUTE_TIMESTAMP7",
////		        		"GLOBAL_ATTRIBUTE_TIMESTAMP8", "GLOBAL_ATTRIBUTE_TIMESTAMP9", "GLOBAL_ATTRIBUTE_TIMESTAMP10",
////		        		"GLOBAL_ATTRIBUTE_NUMBER1", "GLOBAL_ATTRIBUTE_NUMBER2", "GLOBAL_ATTRIBUTE_NUMBER3",
////		        		"GLOBAL_ATTRIBUTE_NUMBER4", "GLOBAL_ATTRIBUTE_NUMBER5", "GLOBAL_ATTRIBUTE_NUMBER6",
////		        		"GLOBAL_ATTRIBUTE_NUMBER7", "GLOBAL_ATTRIBUTE_NUMBER8", "GLOBAL_ATTRIBUTE_NUMBER9",
////		        		"GLOBAL_ATTRIBUTE_NUMBER10", "Registry_Id", "Payee_Service_Level", "Pay_Each_Document_Alone",
////		        		"Delivery_Method", "Remittance_Email", "Remittance_Fax", "Data_Fox_Id", " "
//
//		        );
//

		      
				// Fetch vendor data from the database
		        List<RequestField> vendorData = ltMastCSVRequestsDao.getVendorData(requestParameters);
		 

		        if (vendorData != null && !vendorData.isEmpty()) {
		            // Create an entry in the report request table
		            Long requestId = makeEntryForReport(saveDirectory, requestParameters, "Export Vendors to ERP");

		            // Start a new thread to generate the CSV report
		            VendorThread vendorReportThread = 
		                    new VendorThread(requestId, saveDirectory, vendorData, ltMastReportRequestRepository);

		            Thread reportThread = new Thread(vendorReportThread);
		            reportThread.start();

		            status.setMessage("Vendor report generation started! Request ID is " + requestId);
		            status.setCode(1);
		        } else {
		            status.setMessage("No vendor data found for the specified report.");
		            status.setCode(0);
		        }
		    } catch (Exception e) {
		        throw new ServiceException(0, "Error generating vendor report", e);
		    }

		    return status;
		}



private Long makeEntryForReport(String saveDirectory, RequestParameters requestParameter, String requestName) {
    LtMastReportRequest ltMastReportRequest = new LtMastReportRequest();
    ltMastReportRequest.setUserId(requestParameter.getUserId());
    ltMastReportRequest.setFilterData(requestParameter.toString());
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


}
