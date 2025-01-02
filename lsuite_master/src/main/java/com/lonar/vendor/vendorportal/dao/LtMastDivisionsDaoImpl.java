package com.lonar.vendor.vendorportal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.model.LtMastDivisions;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.reports.DivisionSubDivision;
import com.lonar.vendor.vendorportal.reports.ReportParameters;


@Component
@PropertySource(value = "classpath:queries/divisionMasterQueries.properties", ignoreResourceNotFound = true)
public class LtMastDivisionsDaoImpl implements LtMastDivisionsDao {
	
	
	@Autowired
	private Environment env;

	/*@PersistenceContext(name = "em")
	private EntityManager em;*/
	
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
	@Transactional
	public List<LtMastDivisions> findAllActive(Long companyId) throws ServiceException 
	{
		
		String query = env.getProperty("findAllActiveLtMastDivisions");
		List<LtMastDivisions> list=   jdbcTemplate.query(query, new Object[]{companyId  }, 
				 new BeanPropertyRowMapper<LtMastDivisions>(LtMastDivisions.class)); 
		return list;
	}

	@Override
	@Transactional
	public List<LtMastDivisions> findActiveLikeName(Long companyId, String divisionName) throws ServiceException
	{
		String query = env.getProperty("findActiveDivisionsLikeName");
		
		List<LtMastDivisions> list = (List<LtMastDivisions>) 
				 jdbcTemplate.query(query , new Object[]{companyId, "%"+ divisionName.toUpperCase()+"%","%"+ divisionName.toUpperCase()+"%"},
						 new  BeanPropertyRowMapper<LtMastDivisions>(LtMastDivisions.class));
		return list;
	}

	@Transactional
	@Override
	public List<LtMastDivisions> findByDivisionId(Long divisionId) throws ServiceException
	{
		String query = env.getProperty("findByDivisionIdLtMastDivisions");
		
		List<LtMastDivisions> list = (List<LtMastDivisions>) 
				 jdbcTemplate.query(query , new Object[]{ divisionId},
						 new  BeanPropertyRowMapper<LtMastDivisions>(LtMastDivisions.class));
		
		return list;
	}


	@Transactional
	@Override
	public List<LtMastDivisions> checkDetails(LtMastDivisions ltMastDivisions) throws ServiceException 
	{
		String query = env.getProperty("divisionCheckDetails");
	
		return jdbcTemplate.query(query,
				new Object[] { ltMastDivisions.getDivisionCode(), ltMastDivisions.getDivisionName(),ltMastDivisions.getCompanyId() },
				new RowMapper<LtMastDivisions>() {
					@Override
					public LtMastDivisions mapRow(ResultSet rs, int row) throws SQLException {
						LtMastDivisions ltMastDivisions = new LtMastDivisions();
						ltMastDivisions.setDivisionId(rs.getLong("DIVISION_ID"));
						ltMastDivisions.setDivisionCode(rs.getString("DIVISION_CODE"));
						ltMastDivisions.setDivisionName(rs.getString("DIVISION_NAME"));
						return ltMastDivisions;
					}
				});
	}


	@Override
	public LtMastDivisions getDivisionByDivisionId(Long divisionId) throws ServiceException 
	{
		String query = env.getProperty("getDivisionByDivisionId");
		
		List<LtMastDivisions> list = (List<LtMastDivisions>) 
				 jdbcTemplate.query(query , new Object[]{ divisionId},
						 new  BeanPropertyRowMapper<LtMastDivisions>(LtMastDivisions.class));
		if(!list.isEmpty())
		return list.get(0);
		else
			return null;
	}
	
	@Override
	public Long getCount(Long companyId, LtMastDivisions input) throws ServiceException 
	{
		String query = env.getProperty("getLtMastDivisionsCount");
		
		String divCode = null;
		 if(input.getDivisionCode()!=null)
		   { divCode="%"+input.getDivisionCode().toUpperCase()+"%";}
		 
		
		   String divName=null;
		   if(input.getDivisionName()!=null)
		   {divName="%"+input.getDivisionName().toUpperCase()+"%";}
		   
		   
		   String status=null;
		   if(input.getStatus()!=null) 
		   {status="%"+input.getStatus().toUpperCase()+"%";}
			
		   if(input.getStDate() == null || input.getStDate().trim().equals(""))
		   {input.setStDate(null);}
		   
		   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		   {input.setEnDate(null);}
		   
		   String count  = (String)getJdbcTemplate().queryForObject(
					query, new Object[] {companyId, divCode,divName,status,input.getStDate(),input.getEnDate()}, String.class);
		
			return Long.parseLong(count);
	}


	@Override
	public List<LtMastDivisions> getDataTableRecords(Long companyId, LtMastDivisions input) throws ServiceException 
	{
		String query = env.getProperty("getLtMastDivisionsDataTable");
		
		String divCode = null;
		 if(input.getDivisionCode()!=null && !input.getDivisionCode().equals(""))
		   { divCode="%"+input.getDivisionCode().toUpperCase()+"%";}
		 
		
		   String divName=null;
		   if(input.getDivisionName()!=null && !input.getDivisionName().equals(""))
		   {divName="%"+input.getDivisionName().toUpperCase()+"%";}
		   
		   String stat=null;
		   if(input.getStatus()!=null  && !input.getStatus().equals("") && !input.getStatus().equals("null")) 
		   {stat="%"+input.getStatus().toUpperCase()+"%";}
			
		   if(input.getStDate() == null || input.getStDate().trim().equals(""))
		   {input.setStDate(null);}
		   
		   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		   {input.setEnDate(null);}
		   
		   if(input.getColumnNo()==0)
		   {
			   input.setColumnNo(7);
		   }
		List<LtMastDivisions> list=   jdbcTemplate.query(query, new Object[]{companyId, divCode,divName,
				stat,input.getStDate(),input.getEnDate(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				
				input.getStart()+1,input.getStart()+input.getLength()}, 
					 new BeanPropertyRowMapper<LtMastDivisions>(LtMastDivisions.class)); 
		 
		return list;
	}


	@Override
	public List<DivisionSubDivision> getDataForReport(ReportParameters reportParameters) throws ServiceException {
		String query = env.getProperty("getDivisionDataForReport");
		List<DivisionSubDivision> list=   jdbcTemplate.query(query, new Object[]{ reportParameters.getStatus().toUpperCase(),
				reportParameters.getStartDate(),reportParameters.getEndDate(),reportParameters.getCompanyId() }, 
				 new BeanPropertyRowMapper<DivisionSubDivision>(DivisionSubDivision.class)); 
		return list;
	}

	@Override
	public List<LtMastDivisions> getAll(Long companyId) throws ServiceException {
		String query = env.getProperty("getAllQuery");
		List<LtMastDivisions> list = jdbcTemplate.query(query, new Object[] { companyId },
				 new BeanPropertyRowMapper<LtMastDivisions>(LtMastDivisions.class)); 
		return list;
	}
}
