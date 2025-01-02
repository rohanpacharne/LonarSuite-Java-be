package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.LtExpModuleApprovals;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;

@Component
@PropertySource(value = "classpath:approverModuleQueries.properties", ignoreResourceNotFound = true)
public class LtExpenseModuleApprovalsDaoImpl implements LtExpenseModuleApprovalsDao{
	

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Autowired
	private Environment env;
	
	@Override
	public boolean chkIsAprovedByAnyOne(LtExpenseApprovalHistory approvalHistory) throws Exception
	{
//		String query = env.getProperty("chkIsAprovedByAnyOne");
//		System.out.println("chkIsAprovedByAnyOne  = "+query);
		String query =  " SELECT * FROM  lt_mast_module_approvals b "
				+ "  WHERE b.MODULE_APPROVAL_ID = ( SELECT MODULE_APPROVAL_ID "
				+ " FROM lt_expense_approval WHERE EXP_APPROVAL_ID  = ? ) ";
	
				 List<LtExpModuleApprovals> ltExpModuleApprovalsList = jdbcTemplate.query(query,  new Object[] 
						 {approvalHistory.getExpenseApprovalId()},
						 new BeanPropertyRowMapper<LtExpModuleApprovals> (LtExpModuleApprovals.class));
				 System.out.println("ltExpModuleApprovalsList = "+ltExpModuleApprovalsList);
				 boolean flag=false;
				
				 if(!ltExpModuleApprovalsList.isEmpty() && ltExpModuleApprovalsList.size()>0)
				 {
					 if(ltExpModuleApprovalsList.get(0).getApprovedByAnyone().equals("Y"))
					 { flag= true; }
				 }
				 else
				 {	 flag= false; }
				return flag;
	}
}
