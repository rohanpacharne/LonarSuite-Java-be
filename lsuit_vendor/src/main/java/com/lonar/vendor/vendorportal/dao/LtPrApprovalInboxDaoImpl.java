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
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.RentalAgreementApproval;

@Component
@PropertySource(value = "classpath:prHeaderQueries.properties", ignoreResourceNotFound = true)
public class LtPrApprovalInboxDaoImpl implements LtPrApprovalInboxDao, CodeMaster{
	
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
	public List<PrApproval> getByStatus(String status, String approvalId, PrApproval input) throws Exception {
		String number=null;
		if(input.getPrNumber()!=null && !input.getPrNumber().equals(""))
		{
			number="%"+input.getPrNumber().trim().toUpperCase()+"%";
		}
		
		String status1=null;
		if(input.getStatus()!=null && !input.getStatus().equals("")) {
			status1="%"+input.getStatus().toUpperCase()+"%";
		}
		
		String initiatorName=null;
		if(input.getRequesterName()!=null && !input.getRequesterName().equals("")) {
			initiatorName="%"+input.getRequesterName().toUpperCase()+"%";
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
		
		String query = env.getProperty("getPrInboxDataTable");
		
		return (List<PrApproval>)
				jdbcTemplate.query(query , new Object[]{ status,APPROVED, approvalId,approvalId, number,
						status1,initiatorName,
					
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),	
						(input.getStart()+input.getLength()),input.getStart()+1
						},
			 new  BeanPropertyRowMapper<PrApproval>(PrApproval.class));  

	}

}
