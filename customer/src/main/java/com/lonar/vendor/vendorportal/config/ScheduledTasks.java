package com.lonar.vendor.vendorportal.config;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.lonar.vendor.vendorportal.dao.LtCustomerApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerInfoDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastEmailtokenRepository;
import com.lonar.vendor.vendorportal.service.LtCustomerApprovalHistoryService;
import com.lonar.vendor.vendorportal.service.LtCustomerApprovalService;
import com.lonar.vendor.vendorportal.service.LtMastEmailtokenService;

@Component
@EnableScheduling
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class ScheduledTasks implements CodeMaster{

	@Autowired
	LtMastEmailtokenService ltMastEmailtokenService;
	
	@Autowired
	LtMastEmailtokenRepository ltMastEmailtokenRepository;
	
	@Autowired
	LtMastCustomerInfoDao   ltMastCustomerInfoDao ;
	
	@Autowired
	LtCustomerApprovalDao ltCustomerApprovalDao;
	
	@Autowired
	LtCustomerApprovalHistoryService ltCustomerApprovalHistoryService;
	
	@Autowired
	LtCustomerApprovalService ltCustomerApprovalService;
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() throws ServiceException {
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    	
//		customerChronJob();
    }

	private void customerChronJob() 
	{
//		System.out.println("======================= customerChronJob ============"+new Date());
		try {
			List<LtMastCustomer> customerInprocessList = ltCustomerApprovalDao.getInprocessCustomerList(INPROCESS);	
			String currentApprovalLavel = null ;
			List<LtCustomerApproval> ltCustomerApprovalList = null;
			for ( LtMastCustomer ltMastCustomer :  customerInprocessList ) 
			{ 
				LtCustomerApproval approvalLavel =  ltCustomerApprovalDao.getApprovalLevel(ltMastCustomer.getCustomerId());
				if(approvalLavel != null){
				if( approvalLavel.getCurrentApprovalLevel() != null &&  !approvalLavel.getCurrentApprovalLevel().trim().equals("")){ 
					 currentApprovalLavel = approvalLavel.getCurrentApprovalLevel();
					 ltCustomerApprovalList = ltCustomerApprovalDao.getApprovalList(ltMastCustomer.getCustomerId(), approvalLavel.getCurrentApprovalLevel());
				}
				else { 
					currentApprovalLavel = approvalLavel.getApprovalLevel();
					ltCustomerApprovalList = ltCustomerApprovalDao.getApprovalList(ltMastCustomer.getCustomerId(), approvalLavel.getApprovalLevel());
				}
				}
				boolean isApproved = false;
				boolean isNoAction = false;
			
				if(ltCustomerApprovalList!=null) {
				for (LtCustomerApproval ltCustomerApproval : ltCustomerApprovalList) {
					
					if(ltCustomerApproval.getStatus().equals(NO_ACTION)) {	
						isNoAction = true;
						break;
					}else if(ltCustomerApproval.getStatus().equals(APPROVED) && 
							( ltCustomerApproval.getApprovedByAnyone() != null && ltCustomerApproval.getApprovedByAnyone().equals("N"))){
						isApproved = true;						
					} else if( !ltCustomerApproval.getStatus().equals(APPROVED) && 
							( ltCustomerApproval.getApprovedByAnyone() != null && ltCustomerApproval.getApprovedByAnyone().equals("N") )){
						isApproved = false;
						break;
					}
					else if (ltCustomerApproval.getStatus().equals(APPROVED) &&
							( ltCustomerApproval.getApprovedByAnyone() == null || ltCustomerApproval.getApprovedByAnyone().equals("Y"))){
						isApproved = true;						
						break;
					}
				} 
				}
			if(isNoAction || isApproved ){
			
					if(isApproved) {
						currentApprovalLavel = ltCustomerApprovalDao.getNextApprovalLevel(ltMastCustomer.getCustomerId() , currentApprovalLavel);
						
						if(currentApprovalLavel != null && !currentApprovalLavel.trim().equals("") ){		
							ltCustomerApprovalList = ltCustomerApprovalDao.getApprovalList(ltMastCustomer.getCustomerId(), currentApprovalLavel);
						}else {
					
							ltCustomerApprovalDao.submitForApproval(new Date(),
									ltMastCustomer.getCustomerId(), "ACTIVE", new Date());
						
							ltCustomerApprovalList.get(0).setApprovalId(ltMastCustomer.getCreatedBy());
							ltCustomerApprovalList.get(0).setLastUpdateDate(new Date());
							saveEmailTokan(ltCustomerApprovalList,"customerApproval",ltMastCustomer); 
							
							//ltExpenseApprovalDao.callProceduere(ltExpExpenseHeader);
						}
					}  
					
					if(currentApprovalLavel != null && !currentApprovalLavel.trim().equals("") )
					{	
						ltCustomerApprovalDao.upDateStatus(ltMastCustomer.getCustomerId(), PENDING, currentApprovalLavel);
						ltCustomerApprovalDao.updateCurrentApprovalLevel(ltMastCustomer.getCustomerId(), currentApprovalLavel);
								
						/*//--------------------------chk for delegation here
						VendorApproval obj = new VendorApproval();
						for(VendorApproval expApproval : expenseApprovals)
						{
							if(expApproval.getDelegationId()!=null)
							{
								obj.setApprovalId(expApproval.getDelegationId());
								obj.setApprovalLevel(expApproval.getApprovalLevel());
								obj.setApprovedByAnyone(expApproval.getApprovedByAnyone());
								obj.setCurrentApprovalLevel(expApproval.getCurrentApprovalLevel());
								obj.setVendorId(expApproval.getVendorId());
								obj.setModuleApprovalId(expApproval.getModuleApprovalId());
								obj.setStatus(expApproval.getStatus());
							}
							
						}
						if(obj!=null && obj.getVendorId()!=null)
						{ expenseApprovals.add(obj); }*/
		
						//---------------------------------------------------
						saveApprovalHistoryData(ltCustomerApprovalList, PENDING);
						
						saveEmailTokan(ltCustomerApprovalList,"customerApprovalNotification",ltMastCustomer); 
						
					}
					
					//---------------------- for the self approval ---------------------------
					/*for(VendorApproval expApproval:expenseApprovals)
					{
						
						if(ltMastVendors.getInitiatorId().equals(expApproval.getApprovalId())
								&& currentApprovalLavel != null)
						{
							LtVendorApprovalHistory approvalHistory= new LtVendorApprovalHistory();
							
							approvalHistory.setLastUpdateDate(new Date());
							approvalHistory.setVendorId(ltMastVendors.getVendorId());
							approvalHistory.setEmployeeId(ltMastVendors.getInitiatorId());
							approvalHistory.setStatus(APPROVED);
							ltVendorApprovalService.updateStatusApproval(approvalHistory);	
						}
					}*/
					//---------------------end for the self approval--------------------------
				} 
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void saveApprovalHistoryData(List<LtCustomerApproval> customerApprovalsList,String status) throws Exception
	{
		for(LtCustomerApproval ltCustomerApproval:customerApprovalsList)
		{
			LtCustomerApprovalHistory ltCustomerApprovalHistory=new LtCustomerApprovalHistory();
			ltCustomerApprovalHistory.setStatus(status);
			ltCustomerApprovalHistory.setCustomerId(ltCustomerApproval.getCustomerId());
			ltCustomerApprovalHistory.setCustomerApprovalId(ltCustomerApproval.getCustomerApprovalId());
			ltCustomerApprovalHistory.setEmployeeId(ltCustomerApproval.getApprovalId());
			ltCustomerApprovalHistory.setLastUpdateDate(ltCustomerApproval.getLastUpdateDate());
		try
		{
			ltCustomerApprovalHistoryService.saveApprovalHistory(ltCustomerApprovalHistory);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	}
	
	private void saveEmailTokan(List<LtCustomerApproval> customerApprovals,String emailTemplate,LtMastCustomer ltMastCustomer)
	{
		try
		{
			ltMastEmailtokenService.makeEntryInEmailToken(customerApprovals,emailTemplate,ltMastCustomer,"MAIL");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
		
	
}