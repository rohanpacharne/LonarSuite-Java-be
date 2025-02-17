package com.lonar.UserManagement.web.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.UserManagement.web.dao.LtMastAmountwiseApprovalsDao;
import com.lonar.UserManagement.web.model.CodeMaster;
import com.lonar.UserManagement.web.model.CustomeDataTable;
import com.lonar.UserManagement.web.model.LtMastAmountwiseApprovals;
import com.lonar.UserManagement.web.model.LtMastEmployees;
import com.lonar.UserManagement.web.model.LtMastModuleAppEmployees;
import com.lonar.UserManagement.web.model.LtMastModules;
import com.lonar.UserManagement.web.model.LtMastUsers;
import com.lonar.UserManagement.web.model.LtModuleApprovals;
import com.lonar.UserManagement.web.model.Status;
import com.lonar.UserManagement.web.repository.LtMastAmountwiseApprovalsRepository;
import com.lonar.UserManagement.web.service.LtMastCommonMessageService;
@Service
public class LtMastAmountwiseApprovalsServiceImpl implements LtMastAmountwiseApprovalsService,CodeMaster{

	
	@Autowired
	LtMastAmountwiseApprovalsDao ltMastAmountwiseApprovalsDao;
	@Autowired 
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastAmountwiseApprovalsRepository ltMastAmountwiseApprovalsRepository;
	
	@Override
	public CustomeDataTable getLtMastAmountwiseApprovalsDataTable(LtMastAmountwiseApprovals input, Long companyId) {
	    CustomeDataTable customeDataTable = new CustomeDataTable();

	    // Step 1: Get total records count
	    Long count = ltMastAmountwiseApprovalsDao.getLtMastUsersCount(input, companyId);
	    customeDataTable.setRecordsTotal(count);
	    customeDataTable.setRecordsFiltered(count);
	    // Step 2: Fetch the records
	    List<LtMastAmountwiseApprovals> ltMastAmountwiseApprovals = ltMastAmountwiseApprovalsDao.getLtMastUsersDatatableRecords(input, companyId);
	    
	    // Step 3: Set data ensuring non-null values
	    customeDataTable.setData(ltMastAmountwiseApprovals);

	    return customeDataTable;
	}

	
	@Override
	public Status save1(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) throws Exception
	{
		Status status=new Status();
		
		
			if(ltMastAmountwiseApprovals.getLastUpdateLogin()==null || ltMastAmountwiseApprovals.getFromAmount()==null  
					|| ltMastAmountwiseApprovals.getToAmount()==null )
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				status.setCode(0);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				if(status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
						
			}
			
			ltMastAmountwiseApprovals.setCreatedBy(ltMastAmountwiseApprovals.getLastUpdateLogin());
			ltMastAmountwiseApprovals.setLastUpdateDate(new Date());
			ltMastAmountwiseApprovals.setLastUpdatedBy(ltMastAmountwiseApprovals.getLastUpdateLogin());
			ltMastAmountwiseApprovals.setCreationDate(new Date());
			
		
			if( ltMastAmountwiseApprovalsDao.checkForDuplicate(ltMastAmountwiseApprovals))
			{
			LtMastAmountwiseApprovals amtApprovalId=ltMastAmountwiseApprovalsRepository.save(ltMastAmountwiseApprovals);
			if(amtApprovalId!=null) {
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
				status.setCode(1);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
				status.setData(amtApprovalId);
				
			}	else
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
				status.setCode(0);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
				if( status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
				return status;
			}
		}
		else
		{
			status.setCode(0);
			status.setMessage("Record for same division and level already exists.");
		}
		return status;
			
		
	}

	@Override
	public Status save(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) {
	    Status status = new Status();
	    ltMastAmountwiseApprovals.setCreationDate(new Date());
	    ltMastAmountwiseApprovals.setLastUpdateDate(new Date());
	    ltMastAmountwiseApprovals = ltMastAmountwiseApprovalsRepository.save(ltMastAmountwiseApprovals);
	    if (ltMastAmountwiseApprovals.getAmtApprovalId() != null) {
	        status.setCode(1);
	        try {
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if (status.getMessage() == null) {
	            status.setCode(1);
	            status.setMessage("Error in finding message! The action is completed successfully.");
	        }
	        status.setData(ltMastAmountwiseApprovals.getAmtApprovalId());
	    } else {
	        status.setCode(0);
	        try {
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if (status.getMessage() == null) {
	            status.setCode(0);
	            status.setMessage("Error in finding message! The action is completed unsuccessfully.");
	        }
	    }
	    return status;
	}

	@Transactional
	@Override
	public Status update(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) {
	    Status status = new Status();

	    try {
	        // Validate input fields
	        if (ltMastAmountwiseApprovals == null || 
	            ltMastAmountwiseApprovals.getLastUpdateLogin() == null || 
	            ltMastAmountwiseApprovals.getFromAmount() == null || 
	            ltMastAmountwiseApprovals.getToAmount() == null || 
	            ltMastAmountwiseApprovals.getAmtApprovalId() == null) {

	            status.setCode(0);
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY") != null ? 
	                              ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName() : 
	                              "Error: Missing required fields.");
	            return status;
	        }

	        // Set update details
	        ltMastAmountwiseApprovals.setLastUpdateDate(new Date());
	        ltMastAmountwiseApprovals.setLastUpdatedBy(ltMastAmountwiseApprovals.getLastUpdateLogin());

	        // Attempt to update the record
	        boolean updateSuccess = ltMastAmountwiseApprovalsDao.update(ltMastAmountwiseApprovals);

	        if (updateSuccess) {
	            status.setCode(1);
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY") != null ? 
	                              ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName() : 
	                              "Update completed successfully.");
	        } else {
	            status.setCode(0);
	            status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL") != null ? 
	                              ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName() : 
	                              "Update failed.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();  // Log the exception
	        status.setCode(0);
	        status.setMessage("An unexpected error occurred: " + e.getMessage());
	    }

	    return status;
	}


	@Transactional
	@Override
	public Status delete(Long amtApprovalId) {
	    Status status = new Status();
	    
	    try {
	        if (amtApprovalId == null) {
	            status.setCode(0);
	            status.setMessage("Approver ID cannot be null.");
	            return status;
	        }

	        int rowsAffected = ltMastAmountwiseApprovalsDao.delete(amtApprovalId);
	        
	        if (rowsAffected > 0) {
	            status.setCode(1);
	            status.setMessage("Record deleted successfully.");
	        } else {
	            status.setCode(0);
	            status.setMessage("No record found with the given Approver ID.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        status.setCode(0);
	        status.setMessage("Error while deleting record: " + e.getMessage());
	    }

	    return status;
	}

	@Override
	public LtMastAmountwiseApprovals getByAmtApprovalId(Long amtApprovalId) {
		 return ltMastAmountwiseApprovalsDao.getByAmtApprovalId(amtApprovalId);
	}


}