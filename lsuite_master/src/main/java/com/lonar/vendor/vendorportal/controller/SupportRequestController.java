package com.lonar.vendor.vendorportal.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.SupportRequest;
import com.lonar.vendor.vendorportal.service.SupportRequestService;
 
@RestController
@RequestMapping("API/support")
public class SupportRequestController {
 
    @Autowired
    private SupportRequestService supportRequestService;
 
    @PostMapping(value = "/sendemail", consumes = "multipart/form-data")
    public ResponseEntity<String> submitSupportRequest(
    		
    		@ModelAttribute SupportRequest request,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            supportRequestService.sendSupportRequest(request, file);
            System.out.println("Request body :"+request+"\n Attachment File :"+file);
            return ResponseEntity.ok("Support request submitted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to submit the support request.");
        }
    }
 
//	    @PostMapping("/sendemail")
//	    public ResponseEntity<String> submitSupportRequest(@RequestBody SupportRequest  request) {
//	        try {
//	        	supportRequestService.sendSupportEmail(request);
//	            return ResponseEntity.ok("Support request submitted successfully!");
//	        } catch (Exception e) {
//	        	e.printStackTrace();
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                    .body("Failed to submit the support request.");
//	        }
//	    }
	    
	    
//	    public void
	    
//	    @GetMapping("/{id}")
//	    public ResponseEntity<SupportRequest> getSupportRequestById(@PathVariable Long id) {
//	        SupportRequest request = supportRequestService.getSupportRequestById(id);
//	        return ResponseEntity.ok(request);
//	    }
//
//	    @GetMapping
//	    public ResponseEntity<List<SupportRequest>> getAllSupportRequests() {
//	        List<SupportRequest> requests = supportRequestService.getAllSupportRequests();
//	        return ResponseEntity.ok(requests);
//	    }
//
//	    @PutMapping
//	    public ResponseEntity<SupportRequest> updateSupportRequest(@RequestBody SupportRequest request) {
//	        SupportRequest updatedRequest = supportRequestService.updateSupportRequest(request);
//	        return ResponseEntity.ok(updatedRequest);
//	    }
//
//	    @DeleteMapping("/{id}")
//	    public ResponseEntity<String> deleteSupportRequest(@PathVariable Long id) {
//	    	supportRequestService.deleteSupportRequest(id);
//	        return ResponseEntity.ok("Support request deleted successfully.");
//	    }
    
    @RequestMapping(value = "/dataTableForLtMastSupportRequest/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  	public CustomeDataTable getLtMastSupportRequestDataTable(@PathVariable("companyId") Long companyId,SupportRequest input,@PathVariable("logTime") String logTime) {
      	System.out.println("in getLtMastSupportRequestDataTable");
  		CustomeDataTable customeDataTable = new CustomeDataTable();
  		try {
  			Long count = supportRequestService.getCountLtMastSupportRequestDataTable(input,companyId);
  			customeDataTable.setRecordsTotal(count);
  			customeDataTable.setRecordsFiltered(count);
  			List<SupportRequest> LtMastSysRequestsList = supportRequestService.getLtMastSupportRequestDataTable(input,companyId);
  			customeDataTable.setData(LtMastSysRequestsList);
   
  		} catch (Exception e) {
  			throw new BusinessException(0, null, e);
  		}
  		return customeDataTable;
   
  	}
	}
	