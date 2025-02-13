package com.lonar.vendor.vendorportal.requests;

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
@RequestMapping("/API/requests")
public class LtMastExcelRequestRestController implements CodeMaster{
	
	@Autowired
	LtMastExcelRequestsService ltMastExcelRequestsService;

	@RequestMapping(value = "/exportVendorToErp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> exportVendorToErp(@RequestBody RequestParameters requestParameters) throws ServiceException, IOException{
		Status status =  ltMastExcelRequestsService.exportVendorToErp(requestParameters);
		return new ResponseEntity<Status>(status,HttpStatus.OK);
		
	}

}
