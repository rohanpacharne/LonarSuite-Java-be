package com.lonar.vendor.vendorportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.service.LtCustomerApprovalService;
import com.lonar.vendor.vendorportal.service.LtCustomerInboxService;
import com.lonar.vendor.vendorportal.service.LtMastCustomerInfoService;

@RestController
@RequestMapping("/API/Inbox")
public class LtCustomerInboxRestController implements CodeMaster {

	@Autowired
	LtCustomerInboxService ltCustomerInboxService;

	@Autowired
	LtCustomerApprovalService ltCustomerApprovalService;

	@Autowired
	LtMastCustomerInfoService ltMastCustomerInfoService;

	@RequestMapping(value = "/dataTable/{approvalId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getCustomerApprovalDataTableByApprId(LtCustomerApproval input,
			@PathVariable("approvalId") String approvalId, @PathVariable("logTime") String logTime) {
		String status1 = PENDING;
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltCustomerInboxService.getCount(status1, approvalId, input);

			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);

			List<LtCustomerApproval> expenseApprovalList = ltCustomerInboxService.getByStatus(status1, approvalId, input);
			customeDataTable.setData(expenseApprovalList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
	}

	

	@RequestMapping(value = "/approvalnotification/{empId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LtCustomerApproval> getVendorApprovalNotification(@PathVariable("empId") Long empId,
			@PathVariable("logTime") String logTime) {
		String status = PENDING;
		try {

			List<LtCustomerApproval> expenseApprovalList = ltCustomerInboxService.getVendorApprovalNotification(status,
					empId);
			return expenseApprovalList;
		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
	}

	

}
