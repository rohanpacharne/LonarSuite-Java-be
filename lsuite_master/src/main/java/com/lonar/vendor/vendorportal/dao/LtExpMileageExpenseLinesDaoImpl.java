package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.LtExpMileageExpenseLines;
import com.lonar.vendor.vendorportal.repository.LtExpMileageExpenseLinesRepository;

@Component
@PropertySource(value = "classpath:expMileageQueries.properties", ignoreResourceNotFound = true)
public class LtExpMileageExpenseLinesDaoImpl implements LtExpMileageExpenseLinesDao{
	
	@Autowired
	LtExpMileageExpenseLinesRepository ltExpMileageExpenseLinesRepository;
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;
 
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean deleteByExpHeaderId(Long expHeaderId) throws Exception
	{
		String query = env.getProperty("deleteMileageLinesByHeaderId");
		
		int res=0;
		res=jdbcTemplate.update(query,expHeaderId);
		
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public List<LtExpMileageExpenseLines> getExpMileageLinesByExpHeaderId(Long headerId) throws Exception {
		String query = env.getProperty("getExpMileageLinesByExpHeaderId");
		
		List<LtExpMileageExpenseLines> list=   jdbcTemplate.query(query, new Object[]{headerId },
		 new BeanPropertyRowMapper<LtExpMileageExpenseLines>(LtExpMileageExpenseLines.class));
		return list;
	}

}
