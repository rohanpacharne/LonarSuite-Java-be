package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtPrHeadersRepository;

@Repository
@PropertySource(value = "classpath:queries/prHeaderQueries.properties", ignoreResourceNotFound = true)
public class LtPrHeadersDaoImpl implements LtPrHeadersDao {
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtPrHeadersRepository ltPrHeadersRepository;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
//	SELECT lph.pr_number,lph.creation_date as pr_date,lph.description,concat(lme.first_name,' ',lme.last_name) as requester_name,lph.status
//	FROM lt_pr_headers lph
	@Override
	public List<LtPrHeaders> getLtPrHeadersDataTable(LtPrHeaders input, Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		String query = env.getProperty("getLtPrHeadersDataTable");
		String prNum=null;
		   if(input.getPrNumber()!=null && !input.getPrNumber().equals(""))
		   {prNum="%"+input.getPrNumber().trim().toUpperCase() + "%";}
		   
		   
			if(input.getCreationDate() == null || input.getCreationDate().equals(""))
			{
				input.setCreationDate(null);
			}
			
		   String desc=null;
		   if(input.getDescription()!=null &&  !input.getDescription().equals("")) 
		   {desc="%"+input.getDescription().trim().trim().toUpperCase()+"%";}
		   
		   String reqName=null;
		   if(input.getRequesterName()!=null && !input.getRequesterName().equals(""))
		   {reqName="%"+input.getRequesterName().trim().toUpperCase()+"%";}
			
		   
		   String status=null;
		   if(input.getStatus()!=null &&  !input.getStatus().equals("")) 
		   {status="%"+input.getStatus().trim().toUpperCase()+"%";}
		   
		  System.out.println("input = "+input);
				
				List<LtPrHeaders> list = (List<LtPrHeaders>) 
						jdbcTemplate.query(query , new Object[]{companyId,
								prNum,input.getCreationDate(),desc,reqName,status,
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								
								input.getStart()+input.getLength(),input.getStart()+1},
					 new  BeanPropertyRowMapper<LtPrHeaders>(LtPrHeaders.class));
					System.out.println("count of invoice is "+list.size());
					
				return list;
	}

	@Override
	public Integer save(LtPrHeaders ltPrHeaders) throws ServiceException {
		// TODO Auto-generated method stub
		LtPrHeaders LtPrHeadersSaved =  ltPrHeadersRepository.save(ltPrHeaders);
		if(LtPrHeadersSaved!=null) {
			return LtPrHeadersSaved.getPrHeaderId();
		}else {
			return null;
		}
		
	}

	@Override
	public Long getLtPrHeadersDataTableCount(LtPrHeaders input, Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		
		String query = env.getProperty("getLtPrHeadersDataTableCount");
		String prNum=null;
		   if(input.getPrNumber()!=null && !input.getPrNumber().equals(""))
		   {prNum="%"+input.getPrNumber().trim().toUpperCase() + "%";}
		   
		   
			if(input.getCreationDate() == null || input.getCreationDate().equals(""))
			{
				input.setCreationDate(null);
			}
			
		   String desc=null;
		   if(input.getDescription()!=null &&  !input.getDescription().equals("")) 
		   {desc="%"+input.getDescription().trim().trim().toUpperCase()+"%";}
		   
		   String reqName=null;
		   if(input.getRequesterName()!=null && !input.getRequesterName().equals(""))
		   {reqName="%"+input.getRequesterName().trim().toUpperCase()+"%";}
			
		   
		   String status=null;
		   if(input.getStatus()!=null &&  !input.getStatus().equals("")) 
		   {status="%"+input.getStatus().trim().toUpperCase()+"%";}
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {companyId,
						prNum,input.getCreationDate(),desc,reqName,status,}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public boolean delete(Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		String query = env.getProperty("deletePrHeaderById");
		
		int res=jdbcTemplate.update(query, prHeaderId);
		
		if(res!=0)
			return true;
		else
			return false;
	}

}
