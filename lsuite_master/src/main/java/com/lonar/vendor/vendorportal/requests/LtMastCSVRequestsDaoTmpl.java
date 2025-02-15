package com.lonar.vendor.vendorportal.requests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.reports.LtMastMasterReportMap;
import com.lonar.vendor.vendorportal.reports.LtMastReportRequestRepository;
@Repository
@PropertySource(value = "classpath:queries/requestQueries.properties", ignoreResourceNotFound = true)
public class LtMastCSVRequestsDaoTmpl implements LtMastCSVRequestsDao{

	


	@Autowired
	private Environment env;
	
	@Autowired
	private EntityManager entityManager;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	LtMastReportRequestRepository ltMastReportRequestRepository;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<RequestField> getVendorData(RequestParameters requestParameters) throws ServiceException {
	    try {
	    	String query = env.getProperty("exportVendorToErp");
	        
	        return jdbcTemplate.query(query, new Object[]{requestParameters.getCompanyId(),requestParameters.getStartDate()
	        		,requestParameters.getEndDate(),requestParameters.getSuppilerName()}, 
	                new BeanPropertyRowMapper<>(RequestField.class));

	    } catch (Exception e) {
	        throw new ServiceException(0, "Error fetching vendor data", e);
	    }
	}


 }
	
	
