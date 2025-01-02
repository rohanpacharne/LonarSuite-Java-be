package com.lonar.vendor.vendorportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.service.LtExpenseApprovalHistoryService;

@RestController
@RequestMapping("/API/ExpenseApprovalHistory")
public class LtExpenseApprovalHistoryRestController implements CodeMaster{
	
	@Autowired
	LtExpenseApprovalHistoryService ltExpenseApprovalHistoryService;
	
	@PreAuthorize("hasPermission(null, '#/expenses/expenseDemo', 'View') or hasPermission(null, '#/myAdvance/advanceDemo', 'View')")
	@RequestMapping(value = "/getApprovalHistoryByExpensehederId/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<LtExpenseApprovalHistory>> getExpenseApprovalHistoryByExpHeaderId(@PathVariable("id") Long expHeaderId,@PathVariable("logTime") String logTime)
    {
            List<LtExpenseApprovalHistory> ltExpenseApprovalHistory=new ArrayList<LtExpenseApprovalHistory>();
            try
            {
            	ltExpenseApprovalHistory =  ltExpenseApprovalHistoryService.getExpenseApprovalHistoryByExpHeaderId(expHeaderId);
            	return new ResponseEntity<List<LtExpenseApprovalHistory>>(ltExpenseApprovalHistory, HttpStatus.OK);
            }
            catch(Exception e)
            {
            	throw new BusinessException(0, null, e);
            }
          
    }

}
