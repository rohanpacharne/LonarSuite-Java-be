package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.model.LtMastCostCenters;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.reports.ReportParameters;

@Component
@PropertySource(value = "classpath:queries/costCenterMasterQueries.properties", ignoreResourceNotFound = true)
public class LtMastCostCentersDaoImpl implements LtMastCostCentersDao
{
	@Autowired
	private Environment env;

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	} 
	@Transactional
	@Override
	public List<LtMastCostCenters> findByCostCenterCode(String costCenterCode) throws ServiceException{
		String query = env.getProperty("findByCostCenterCode");
	
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{"%"+costCenterCode+"%" }, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		return list;
	}

	@Transactional
	@Override
	public List<LtMastCostCenters> findByCostCenterName(String costCenterName) throws ServiceException{
		
		String query = env.getProperty("findByCostCenterName");
	
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{"%"+costCenterName+"%" }, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		return list;
	}
	
	@Transactional
	@Override
	public List<LtMastCostCenters> findAllActive(Long companyId) throws ServiceException
	{		
		String query = env.getProperty("findCostCentersAllActive");
		
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{companyId}, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		return list;
	}

	@Transactional
	@Override
	public List<LtMastCostCenters> findByActiveLikeCostCentersName(Long companyId, String costCentersName) throws ServiceException
	{
		String query = env.getProperty("findByActiveLikeCostCentersName");
		
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{companyId, "%"+costCentersName.trim().toUpperCase()+"%", "%"+costCentersName.trim().toUpperCase()+"%" }, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		return list;
		//return em.createNamedQuery("LtMastCostCenters.findByActiveLikeCostCentersName").setParameter("costCenterName", "%"+costCentersName.toLowerCase()+"%").getResultList();
	}

	@Transactional
	@Override
	public List<LtMastCostCenters> findByLikeCostCentersName(String costCentersName, Long companyId) throws ServiceException
	{
		String query = env.getProperty("findByLikeCostCentersName");
		
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{"%"+costCentersName.trim().toUpperCase()+"%",
				"%"+costCentersName.trim().toUpperCase()+"%", companyId }, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		return list;
		//return em.createNamedQuery("LtMastCostCenters.findByLikeCostCentersName").setParameter("costCenterName", "%"+costCentersName.toLowerCase()+"%").getResultList();
	}

	@Override
	public List<LtMastCostCenters> checkDetails(LtMastCostCenters ltMastCostCenters) throws ServiceException 
	{
		
		String query = env.getProperty("checkDetails");
		
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{ltMastCostCenters.getCostCenterName(), 
				ltMastCostCenters.getCostCenterCode(),ltMastCostCenters.getCompanyId()}, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		return list;
	}

	@Override
	public LtMastCostCenters findByCostCenterId(Long costCenterId) throws ServiceException 
	{
		
		String query = env.getProperty("findByCostCenterId");
		
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{costCenterId}, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		if(!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<LtMastCostCenters> getDataTable(Long companyId, LtMastCostCenters input) throws ServiceException 
	{
		String query = env.getProperty("getDataTableLtMastCostCenters");
		
				String costCenterCode=null;
				if(input.getCostCenterCode()!=null)
				{costCenterCode="%"+input.getCostCenterCode().toUpperCase()+"%";}
			
				String costCenterName=null;
				if(input.getCostCenterName()!=null)
				{costCenterName="%"+input.getCostCenterName().toUpperCase()+"%";}
			  
				String status=null;
			    if(input.getStatus()!=null) 
			   {status="%"+input.getStatus().toUpperCase()+"%";}
			   
			    String name=null;
			    if(input.getApproverName()!=null) 
			   {name="%"+input.getApproverName().toUpperCase()+"%";}
			   
				if(input.getStDate() == null || input.getStDate().trim().equals(""))
				{
					input.setStDate(null);
				}
				if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
				{
					input.setEnDate(null);
				}
			if(input.getColumnNo()==0)
			{
				input.setColumnNo(8);
			}
				
			
		return (List<LtMastCostCenters>) 
				jdbcTemplate.query(query , new Object[]{companyId, costCenterCode,costCenterName,status,
						input.getStDate(),input.getEnDate(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getStart()+input.getLength(),input.getStart()+1},
			 new  BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class));
	}

	@Override
	public Long getCount(Long companyId, LtMastCostCenters input) throws ServiceException 
	{
		String query = env.getProperty("getCountLtMastCostCenters");
				
				String costCenterCode=null;
				if(input.getCostCenterCode()!=null)
				{costCenterCode="%"+input.getCostCenterCode().toUpperCase()+"%";}
				
				String costCenterName=null;
				if(input.getCostCenterName()!=null)
				{costCenterName="%"+input.getCostCenterName().toUpperCase()+"%";}
			  
				String status=null;
			    if(input.getStatus()!=null) 
			   {status="%"+input.getStatus().toUpperCase()+"%";}
			   
			    String name=null;
			    if(input.getApproverName()!=null) 
			   {status="%"+input.getApproverName().toUpperCase()+"%";}
			   
				if(input.getStDate() == null || input.getStDate().trim().equals(""))
				{
					input.setStDate(null);
				}
				if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
				{
					input.setEnDate(null);
				}
			
				String count  = (String)getJdbcTemplate().queryForObject(
						query, new Object[] {companyId, costCenterCode,costCenterName,status,
								input.getStDate(),input.getEnDate()}, String.class);

				
				return Long.parseLong(count);
	}

	@Override
	public LtMastCostCenters getLtMastCostCentersByID(Long costCentersID) throws ServiceException
	{
		String query = env.getProperty("getLtMastCostCentersByID");
		
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{costCentersID}, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		if(!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<LtMastCostCenters> getDataForReport(ReportParameters reportParameters) throws ServiceException {
		String query = env.getProperty("getLtMastCostCentersDataForReport");
		
		List<LtMastCostCenters> list=   jdbcTemplate.query(query, new Object[]{ reportParameters.getStatus(),
				reportParameters.getStartDate(),reportParameters.getEndDate(),reportParameters.getCompanyId() }, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		return list;
	}

	@Override
	public List<LtMastCostCenters> getAll(Long companyId) throws ServiceException {
		String query = env.getProperty("ltMastCostCentersgetAllQuery");
		
		List<LtMastCostCenters> List = jdbcTemplate.query(query, new Object[] { companyId }, 
		 new BeanPropertyRowMapper<LtMastCostCenters>(LtMastCostCenters.class)); 
		
		return List;
	}

}


