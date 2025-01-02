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
import com.lonar.vendor.vendorportal.model.ExpenseApprovalV;

@Component
@PropertySource(value = "classpath:branchMasterQueries.properties", ignoreResourceNotFound = true)
public class ExpenseApprovalVDaoImpl implements ExpenseApprovalVDao,CodeMaster{
	
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
	public Long getCount(String status1, String approvalId, ExpenseApprovalV input) throws Exception
	{
		String query = env.getProperty("getCountInbox");
		
		String amount=null;
		if(input.getExpenseAmount()!=null)
		{
			amount= "%"+input.getExpenseAmount()+"%";
		}
		String number=null;
		if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
		{
			number="%"+input.getExpenseNumber().trim().toUpperCase()+"%";
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
				query, new Object[] { status1,APPROVED, approvalId,approvalId, number,
						amount,status2,input.getLstDate(),initiatorName}, String.class);
 
		return Long.parseLong(count);
		
	}
	@Override
	public List<ExpenseApprovalV> getByStatus(String status,String approvalId,ExpenseApprovalV input) throws Exception
	{
		
		String amount=null;
		if(input.getExpenseAmount()!=null && !input.getExpenseAmount().equals(""))
		{
			amount= "%"+input.getExpenseAmount()+"%";
		}
		String number=null;
		if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
		{
			number="%"+input.getExpenseNumber().trim().toUpperCase()+"%";
		}
		
		String status1=null;
		if(input.getStatus()!=null && !input.getStatus().equals("")) {
			status1="%"+input.getStatus().toUpperCase()+"%";
		}
		String initiatorName=null;
		if(input.getInitiatorName()!=null && !input.getInitiatorName().equals("")) {
			initiatorName="%"+input.getInitiatorName().toUpperCase()+"%";
		}
		
		if(input.getLstDate() == null || input.getLstDate().trim().equals(""))
		{
			input.setLstDate(null);
			
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
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("asc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==0 || input.getColumnNo()==1)
		{
			input.setColumnNo(5);
		}
		String query = env.getProperty("getByStatusInboxView");
		
		return (List<ExpenseApprovalV>)
				jdbcTemplate.query(query , new Object[]{ status,APPROVED, approvalId,approvalId, number,
						amount,status1, input.getLstDate(), initiatorName,
					
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),	
						(input.getStart()+input.getLength()),input.getStart()+1
						},
			 new  BeanPropertyRowMapper<ExpenseApprovalV>(ExpenseApprovalV.class));
		
			  
	}

}
