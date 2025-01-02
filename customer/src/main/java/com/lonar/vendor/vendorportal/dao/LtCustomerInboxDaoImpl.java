package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/customerApproverQueries.properties", ignoreResourceNotFound = true)
public class LtCustomerInboxDaoImpl implements LtCustomerInboxDao,CodeMaster {

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
	public Long getCount(String status1, String approvalId, LtCustomerApproval input) throws ServiceException {
		String query = env.getProperty("getCustomerCountInbox");
		
		
		String customerName=null;
		if(input.getCustomerName()!=null && !input.getCustomerName().equals(""))
		{
			customerName="%"+input.getCustomerName().trim().toUpperCase()+"%";
		}
		String status2=null;
		if(input.getStatus()!=null && !input.getStatus().equals(""))
		{
			status2="%"+input.getStatus().trim().toUpperCase()+"%";
		}
		String initiatorName=null;
		if(input.getInitiatorName()!=null && !input.getInitiatorName().equals(""))
		{
			initiatorName="%"+input.getInitiatorName().trim().toUpperCase()+"%";
		}
		
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { status1,APPROVED, approvalId,approvalId, customerName,
						initiatorName,status2}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtCustomerApproval> getByStatus(String status1, String approvalId, LtCustomerApproval input)
			throws ServiceException {
		String customerName=null;
		if(input.getCustomerName()!=null && !input.getCustomerName().equals(""))
		{
			customerName="%"+input.getCustomerName().trim().toUpperCase()+"%";
		}
		String status2=null;
		if(input.getStatus()!=null && !input.getStatus().equals(""))
		{
			status2="%"+input.getStatus().trim().toUpperCase()+"%";
		}
		String initiatorName=null;
		if(input.getInitiatorName()!=null && !input.getInitiatorName().equals(""))
		{
			initiatorName="%"+input.getInitiatorName().trim().toUpperCase()+"%";
		}
		
		if(input.getSort()==null)
		{
			input.setSort("desc");
		}
		
		
		String query = env.getProperty("getCustomerInbox");
		
		return (List<LtCustomerApproval>) 
				jdbcTemplate.query(query , new Object[]{ status1,APPROVED, approvalId,approvalId, customerName,
						initiatorName,status2,
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),	
						
						(input.getStart()+input.getLength()),input.getStart()+1  },
			 new  BeanPropertyRowMapper<LtCustomerApproval>(LtCustomerApproval.class));
	}

	@Override
	public List<LtCustomerApproval> getVendorApprovalNotification(String status, Long empId) throws ServiceException {
	String query = env.getProperty("getNotification");
		
		return (List<LtCustomerApproval>) 
				jdbcTemplate.query(query , new Object[]{ status,  empId ,empId,status,  empId ,empId},
			 new  BeanPropertyRowMapper<LtCustomerApproval>(LtCustomerApproval.class));
	}

	

}
