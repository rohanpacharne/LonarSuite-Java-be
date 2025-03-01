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
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/poInboxQueries.properties", ignoreResourceNotFound = true)
public class LtPoInboxDaoImpl implements LtPoInboxDao,CodeMaster {

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
	public Long getPoCount(String status1, String approvalId, PoApproval input) throws ServiceException {
    String query = env.getProperty("getPoCountInbox");
		
		String poNum=null;
		if(input.getPoNum()!=null && !input.getPoNum().equals(""))
		{
			poNum="%"+input.getPoNum().trim().toUpperCase()+"%";
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
		}else {
			initiatorName="%"+"%";
		}
		
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { status1,STATUS_APPROVED, approvalId,approvalId,status2, poNum,
						  initiatorName}, String.class);

		return Long.parseLong(count);
	}
	

	@Override
	public List<PoApproval> getPoByStatus(String status1, String approvalId, PoApproval input) throws ServiceException {
		String poNum=null;
		if(input.getPoNum()!=null && !input.getPoNum().equals(""))
		{
			poNum="%"+input.getPoNum().trim().toUpperCase()+"%";
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
		}else {
			initiatorName="%"+"%";
		}
		
		if(input.getSort()==null)
		{
			input.setSort("desc");
		}
		
		
		String query = env.getProperty("getPoInbox");
		
		return (List<PoApproval>) 
				jdbcTemplate.query(query , new Object[]{ status1,STATUS_APPROVED, approvalId,approvalId,status2, poNum,
						  initiatorName,
						input.getColumnNo(),input.getColumnNo(),	
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),	
						
						(input.getStart()+input.getLength()),input.getStart()+1  },
			 new  BeanPropertyRowMapper<PoApproval>(PoApproval.class));
	}



}
