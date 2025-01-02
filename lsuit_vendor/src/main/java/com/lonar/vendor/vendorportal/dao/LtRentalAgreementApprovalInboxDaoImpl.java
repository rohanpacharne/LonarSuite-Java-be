package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;

@Component
@PropertySource(value = "classpath:invoiceHeaderQueries.properties", ignoreResourceNotFound = true)
public class LtRentalAgreementApprovalInboxDaoImpl implements LtRentalAgreementApprovalInboxDao,CodeMaster {
	

	@Autowired
	private Environment env;
	private JdbcTemplate jdbcTemplate;
 
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Override
	public Long getCount(String status1, String approvalId, RentalAgreementApproval input) throws Exception {
		String query = env.getProperty("getCountInboxForRentalAgr");
		
//		String amount=null;
//		if(input.getExpenseAmount()!=null)
//		{
//			amount= "%"+input.getExpenseAmount()+"%";
//		}
//		String number=null;
//		if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
//		{
//			number="%"+input.getExpenseNumber().trim().toUpperCase()+"%";
//		}
//		String status2=null;
//		if(input.getStatus()!=null && !input.getStatus().equals(""))
//		{
//			status2="%"+input.getStatus().trim().toUpperCase()+"%";
//		}
//		String initiatorName=null;
//		if(input.getInitiatorName()!=null && !input.getInitiatorName().equals(""))
//		{
//			initiatorName="%"+input.getInitiatorName().trim().toUpperCase()+"%";
//		}
		
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { status1,RA_APPROVED, approvalId,approvalId
						}, String.class);
 
		return Long.parseLong(count);
		
	}

	@Override
	public List<RentalAgreementApproval> getByStatus(String status, String approvalId, RentalAgreementApproval input)
			throws Exception {
		
		String number=null;
		if(input.getAgreementNumber()!=null && !input.getAgreementNumber().equals(""))
		{
			number="%"+input.getAgreementNumber().trim().toUpperCase()+"%";
		}
		
		String status1=null;
		if(input.getStatus()!=null && !input.getStatus().equals("")) {
			status1="%"+input.getStatus().toUpperCase()+"%";
		}
		
		String initiatorName=null;
		if(input.getInitiatorName()!=null && !input.getInitiatorName().equals("")) {
			initiatorName="%"+input.getInitiatorName().toUpperCase()+"%";
		}
		
		if(input.getSort()==null)
		{
			input.setSort("desc");
		}
		
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(11);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		
		String query = env.getProperty("getByStatusInboxViewForRentalAgr");
		
		return (List<RentalAgreementApproval>)
				jdbcTemplate.query(query , new Object[]{ status,RA_APPROVED, approvalId,approvalId, number,
						status1,initiatorName,
					
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),	
						(input.getStart()+input.getLength()),input.getStart()+1
						},
			 new  BeanPropertyRowMapper<RentalAgreementApproval>(RentalAgreementApproval.class));  
	}

}
