package com.lonar.mast.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import com.lonar.mast.model.LtMastExpenseTypes;


@Component
@PropertySource(value = "classpath:expenseTypesQueries.properties", ignoreResourceNotFound = true)
public class LtMastExpenseTypesDaoImpl implements LtMastExpenseTypesDao {
	
	@Autowired
	private Environment env;
	
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

	@Override
	public List<LtMastExpenseTypes> getAllActiveExpenseType() throws Exception {
		String query = env.getProperty("getAllActiveExpenseType");
		List<LtMastExpenseTypes> list =   jdbcTemplate.query(query, new Object[]{  },
				 new BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class));
		return list;
	}
	
	@Override
	public Long getExpenseTypesCount(LtMastExpenseTypes input) throws Exception
	{
		String query = env.getProperty("getExpenseTypesCount");
		
		String nature=null;
		if(input.getExpenseNature()!= null && !input.getExpenseNature().equals(""))
		{nature = "%"+input.getExpenseNature().trim().toUpperCase()+"%" ;}
		
		String desc=null;
		if(input.getExpenseDescription()!= null && !input.getExpenseDescription().equals(""))
		{desc = "%"+input.getExpenseDescription().trim().toUpperCase()+"%" ;}
		
		String type=null;
		if(input.getExpenseType()!= null && !input.getExpenseType().equals(""))
		{type = "%"+input.getExpenseType().trim().toUpperCase()+"%" ;}
		
		String glCode=null;
		if(input.getGlCode()!= null && !input.getGlCode().equals(""))
		{glCode = "%"+input.getGlCode().trim().toUpperCase()+"%" ;}
		
		String glDesc=null;
		if(input.getGlCodeDesc()!= null && !input.getGlCodeDesc().equals(""))
		{glDesc = "%"+input.getGlCodeDesc().trim().toUpperCase()+"%" ;}
		
		String status=null;
		if(input.getStatus()!= null && !input.getStatus().equals(""))
		{status = "%"+input.getStatus().trim().toUpperCase()+"%" ;}
		
		if(input.getStDate() == null || input.getStDate().trim().equals(""))
		{input.setStDate(null);}
		   
		if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		{input.setEnDate(null);}
		
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {nature,desc,type,glCode,glDesc,status,input.getStDate(),input.getEnDate()}, String.class);
 
		
		return Long.parseLong(count);
	}
 
 
	@Override
	public List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input) throws Exception
	{
		String nature=null;
		if(input.getExpenseNature()!= null && !input.getExpenseNature().equals(""))
		{nature = "%"+input.getExpenseNature().trim().toUpperCase()+"%" ;}
		
		String desc=null;
		if(input.getExpenseDescription()!= null && !input.getExpenseDescription().equals(""))
		{desc = "%"+input.getExpenseDescription().trim().toUpperCase()+"%" ;}
		
		String type=null;
		if(input.getExpenseType()!= null && !input.getExpenseType().equals(""))
		{type = "%"+input.getExpenseType().trim().toUpperCase()+"%" ;}
		
		String glCode=null;
		if(input.getGlCode()!= null && !input.getGlCode().equals(""))
		{glCode = "%"+input.getGlCode().trim().toUpperCase()+"%" ;}
		
		String glDesc=null;
		if(input.getGlCodeDesc()!= null && !input.getGlCodeDesc().equals(""))
		{glDesc = "%"+input.getGlCodeDesc().trim().toUpperCase()+"%" ;}
		
		String status=null;
		if(input.getStatus()!= null && !input.getStatus().equals(""))
		{status = "%"+input.getStatus().trim().toUpperCase()+"%" ;}
		
		if(input.getStDate() == null || input.getStDate().trim().equals(""))
		{input.setStDate(null);}
		   
		if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		{input.setEnDate(null);}
		   
		if(input.getColumnNo()==0)	
		{
			input.setColumnNo(9);
		}
			String query = env.getProperty("getExpenseTypesData");
			
			return (List<LtMastExpenseTypes>)
					jdbcTemplate.query(query , new Object[]{nature,desc,type,glCode,glDesc,status,
							input.getStDate(),input.getEnDate(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							
							input.getLength() + input.getStart(),input.getStart()+1
							},
				 new  BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class));
	}

}
