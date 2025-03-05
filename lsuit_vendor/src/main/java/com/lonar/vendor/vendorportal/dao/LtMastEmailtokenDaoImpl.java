package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.LtMastUsers;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastEmailtokenRepository;

@Repository
public class LtMastEmailtokenDaoImpl implements LtMastEmailtokenDao
{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment env;
	
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
	LtMastEmailtokenRepository ltMastEmailtokenRepository;
	
	
	@Override
	public Long makeEntryInEmailToken(LtMastEmailtoken ltMastEmailtoken) throws ServiceException 
	{
		LtMastEmailtoken emailtoken =ltMastEmailtokenRepository.save(ltMastEmailtoken);
		return emailtoken.getEmailTokenId();
		
	}

	@Override
	public List<LtMastEmailtoken> findAllActive() throws ServiceException 
	{
		//String sqlQuery = env.getProperty("findAllActive");
		String query = "SELECT * FROM lt_mast_emailtoken e " + 
				" WHERE (e.EMAIL_STATUS = 'SENDING' OR e.EMAIL_STATUS = 'Fail to Send') " + 
				" AND (e.FAILURECOUNT IS NULL OR e.FAILURECOUNT <=5) ";
		
		List<LtMastEmailtoken> list=   jdbcTemplate.query(query, new Object[]{ }, 
				 new BeanPropertyRowMapper<LtMastEmailtoken>(LtMastEmailtoken.class)); 
		
		return list;
	}

	@Override
	public void updateStatus(Long emailTokenId, String status, Integer count) throws ServiceException {
//		String sqlQuery = " UPDATE lt_mast_emailtoken SET EMAIL_STATUS= ?, FAILURECOUNT= ( nvl2(FAILURECOUNT,FAILURECOUNT + ?, 1) ) where EMAIL_TOKEN_ID = ? ";
		String sqlQuery = "UPDATE lt_mast_emailtoken SET EMAIL_STATUS = ?, FAILURECOUNT = COALESCE(FAILURECOUNT, 0) + ? WHERE EMAIL_TOKEN_ID = ?";
		int res=jdbcTemplate.update(sqlQuery,status, count , emailTokenId );
		
	}
	
	@Override
	public LtInvoiceHeaders getApproverUserId(Long empId) {
		// TODO Auto-generated method stub
		String sqlQuery = "select lmu.user_id from lt_mast_users lmu where lmu.EMPLOYEE_ID = ?";
		
		List<LtInvoiceHeaders> list=   jdbcTemplate.query(sqlQuery, new Object[]{empId}, 
				 new BeanPropertyRowMapper<LtInvoiceHeaders>(LtInvoiceHeaders.class)); 
		
		if(list!=null && !list.isEmpty()) {
			return list.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public LtInvoiceHeaders getEmpName(Long userId) {
		
		String sqlQuery = "select * from lt_mast_users where USER_ID = ?;";
		
		List<LtMastUsers> list = jdbcTemplate.query(sqlQuery, new Object[]{userId }, 
				 new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class));
		
//		if(list!=null && !list.isEmpty()) {
			LtMastUsers ltMastUsers = list.get(0);
			
//			if(ltMastUsers!=null) {
				if(ltMastUsers.getVendorId()==null) {
					String sqlQuery1 = "select concat(lme.FIRST_NAME,' ',lme.LAST_NAME) as employee_name from lt_mast_employees lme where lme.EMPLOYEE_ID = ?";
					
					List<LtInvoiceHeaders> list1=   jdbcTemplate.query(sqlQuery1, new Object[]{ltMastUsers.getEmployeeId() }, 
							 new BeanPropertyRowMapper<LtInvoiceHeaders>(LtInvoiceHeaders.class)); 
					
					if(list1!=null && !list1.isEmpty()) {
						return list1.get(0);
					}else {
						return null;
					}
				}else {
					String sqlQuery2 = "select vendor_name as employee_name from lt_mast_vendors lmv where lmv.VENDOR_ID = ?";
					
					List<LtInvoiceHeaders> list2=   jdbcTemplate.query(sqlQuery2, new Object[]{ltMastUsers.getVendorId() }, 
							 new BeanPropertyRowMapper<LtInvoiceHeaders>(LtInvoiceHeaders.class)); 
					
					if(list2!=null && !list2.isEmpty()) {
						return list2.get(0);
					}else {
						return null;
					}
				}
//			}
//		}
		
	}

}
