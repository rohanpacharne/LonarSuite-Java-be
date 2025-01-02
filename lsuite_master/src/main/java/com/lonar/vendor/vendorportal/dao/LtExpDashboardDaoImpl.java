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
import com.lonar.vendor.vendorportal.model.DatatableData;
import com.lonar.vendor.vendorportal.model.LtNotification;

@Component
@PropertySource(value = "classpath:expenseDashboardQueries.properties", ignoreResourceNotFound = true)
public class LtExpDashboardDaoImpl implements LtExpDashboardDao,CodeMaster{
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	@Override
	public List<DatatableData> getExpenseCountByHeaderId(Long empId) throws Exception 
	{
		String query = env.getProperty("getExpenseCountByHeaderId_Dashboard");
		
		List<DatatableData> list=   jdbcTemplate.query(query, new Object[]{ empId}, 
			 new BeanPropertyRowMapper<DatatableData>(DatatableData.class)); 
		return list;
	}
	
	@Override
	public List<DatatableData> getExpenseTypeByEmpId(Long empId) throws Exception {
		String query = env.getProperty("getExpenseTypeByEmpId_Dashboard");
		
		List<DatatableData> list=   jdbcTemplate.query(query, new Object[]{ empId}, 
			 new BeanPropertyRowMapper<DatatableData>(DatatableData.class)); 
		
		return list;
	}
	
	@Override
	public List<LtNotification> getNotificatioByEmpId(Long empId) throws Exception 
	{
		String query = env.getProperty("getNotificatioByEmpId_Dashboard");
		
		return (List<LtNotification>) 
				jdbcTemplate.query(query , new Object[]{ PENDING,PENDING,PENDING,empId,empId},
			 new  BeanPropertyRowMapper<LtNotification>(LtNotification.class));	
	}


}
