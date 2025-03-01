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
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.service.LtPoInboxService;

@RestController
@RequestMapping("/API/poInbox")
public class LtPoInboxRestController implements CodeMaster {

	@Autowired
	LtPoInboxService ltPoInboxService;
	


	@RequestMapping(value = "/podataTable/{approvalId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getPoDataTableByApprId(PoApproval input,
			@PathVariable("approvalId") String approvalId, @PathVariable("logTime") String logTime) {
		String status1 = PENDING;
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try {
			Long count = ltPoInboxService.getPoCount(status1, approvalId, input);

			customeDataTable.setRecordsTotal(count);
			customeDataTable.setRecordsFiltered(count);

			List<PoApproval> poApprovalList = ltPoInboxService.getPoByStatus(status1, approvalId,
					input);
			customeDataTable.setData(poApprovalList);

		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
	}

}
