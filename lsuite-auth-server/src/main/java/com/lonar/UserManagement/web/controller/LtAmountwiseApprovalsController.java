package com.lonar.UserManagement.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.UserManagement.common.BusinessException;
import com.lonar.UserManagement.web.model.CustomeDataTable;
import com.lonar.UserManagement.web.model.LtMastAmountwiseApprovals;
import com.lonar.UserManagement.web.model.LtMastModules;
import com.lonar.UserManagement.web.model.LtModuleApprovals;
import com.lonar.UserManagement.web.model.Status;
import com.lonar.UserManagement.web.service.LtMastAmountwiseApprovalsService;
import com.lonar.UserManagement.web.service.LtMastCommonMessageService;

@RestController
@RequestMapping(value = "/api/amountwiseApprovals")
@PropertySource(value = "classpath:serverurl.properties", ignoreResourceNotFound = true)

public class LtAmountwiseApprovalsController {
		
		@Autowired
		LtMastAmountwiseApprovalsService  ltMastAmountwiseApprovalsService;
		
		@Autowired
		LtMastCommonMessageService ltMastCommonMessageService;
	 
		
		@RequestMapping(value = "/datatable/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public CustomeDataTable getDataTable(@PathVariable("companyId") Long companyId,LtMastAmountwiseApprovals input,@PathVariable("logTime") String logTime) {
			try {
				return ltMastAmountwiseApprovalsService.getLtMastAmountwiseApprovalsDataTable(input,companyId);
			}catch(Exception e) {
					throw new BusinessException(0, null, e);
			}
			
		}
		
		@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Status> addLtMastAmountwiseApprovals(@RequestBody LtMastAmountwiseApprovals ltMastAmountwiseApprovals) {
			Status status = new Status();
			try {
				status = ltMastAmountwiseApprovalsService.save(ltMastAmountwiseApprovals);
			} catch (Exception e) {
				throw new BusinessException(0, null, e);
			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Status> updateLtMastAmountwiseApprovals(@RequestBody LtMastAmountwiseApprovals ltMastAmountwiseApprovals) {
		    Status status = new Status();
		    try {
		        status = ltMastAmountwiseApprovalsService.update(ltMastAmountwiseApprovals);
		    } catch (Exception e) {
		        throw new BusinessException(0, null, e);
		    }
		    return new ResponseEntity<>(status, HttpStatus.OK);
		}


		@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
		 public ResponseEntity<Status> delete(@PathVariable("id") Long amtApprovalId,@PathVariable("logTime") String logTime)
		{
			Status status=new Status();
			try
			{
				status =  ltMastAmountwiseApprovalsService.delete(amtApprovalId);
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) 
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
						
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//throw new BusinessException(ENTITY_CANNOT_DELETE, null, e);
				return new ResponseEntity<Status>(status,HttpStatus.OK);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/getByAmtApprovalId/{amtApprovalId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
	    public ResponseEntity<LtMastAmountwiseApprovals> getByAmtApprovalId(@PathVariable("amtApprovalId") Long amtApprovalId,@PathVariable("logTime") String logTime)
	    { 
			LtMastAmountwiseApprovals ltMastAmountwiseApprovals=new LtMastAmountwiseApprovals();
	            try
	            {
	            	if(amtApprovalId!=null)
	            	{
	            		ltMastAmountwiseApprovals =  ltMastAmountwiseApprovalsService.getByAmtApprovalId(amtApprovalId);
	            	}
	            }
	            catch(Exception e)
	            {
	            	throw new BusinessException(0, null, e);
	            }
	           
	            return new ResponseEntity<LtMastAmountwiseApprovals>(ltMastAmountwiseApprovals, HttpStatus.OK);
	    }
		
			
}
