package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/customerApproverQueries.properties", ignoreResourceNotFound = true)
public class LtCustomerApprovalHistoryDaoImpl implements LtCustomerApprovalHistoryDao {

	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean save(LtCustomerApprovalHistory ltCustomerApprovalHistory) throws ServiceException {
		
		int res = jdbcTemplate
		.update(" INSERT INTO LT_MAST_CUSTOMER_APPR_HISTORY "
				+ " (CUSTOMER_APPROVAL_ID,EMPLOYEE_ID, "
				+ " STATUS,NOTE,LAST_UPDATE_DATE,CUSTOMER_ID,REMARK,USER_TYPE ) "
				+ " VALUES(?,?,?,?,?,?,?,?)  ",

				//ltExpenseApprovalHistory.getVendorApprovalHistoryId(),
				ltCustomerApprovalHistory.getCustomerApprovalId(),
				ltCustomerApprovalHistory.getEmployeeId(),
				ltCustomerApprovalHistory.getStatus(),
				ltCustomerApprovalHistory.getNote(),
				new Date(),
				ltCustomerApprovalHistory.getCustomerId(),
				ltCustomerApprovalHistory.getRemark(),
				ltCustomerApprovalHistory.getUserType());
				
			if (res != 0)
					return true;
			else
					return false;
	}

	@Override
	public List<LtCustomerApprovalHistory> getCustomerApprovalHistoryByCustomerId(Long customerId) throws ServiceException
	{
		String query = env.getProperty("getCustomerApprovalHistoryByCustomerId");
		List<LtCustomerApprovalHistory> list=   jdbcTemplate.query(query, new Object[]{ customerId }, 
				 new BeanPropertyRowMapper<LtCustomerApprovalHistory>(LtCustomerApprovalHistory.class)); 
		return list;
	}

}
