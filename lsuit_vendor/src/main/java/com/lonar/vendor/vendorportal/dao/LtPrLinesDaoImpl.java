package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtPrLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtPrHeadersRepository;
import com.lonar.vendor.vendorportal.repository.LtPrLinesRepository;

@Repository
@PropertySource(value = "classpath:queries/prLineQueries.properties", ignoreResourceNotFound = true)
public class LtPrLinesDaoImpl implements LtPrLinesDao {
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtPrLinesRepository ltPrLinesRepository;
	
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

	@Override
	public List<LtPrLines> getLtPrLinesDataTable(LtPrLines input, Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		
		String query = env.getProperty("getLtPrLinesDataTable");
		Integer lineNum=null;
		   if(input.getLineNo()!=null && !input.getLineNo().equals(""))
		   {lineNum=input.getLineNo();}
		  
			
		   String getCategoryName=null;
		   if(input.getCategoryName()!=null &&  !input.getCategoryName().equals("")) 
		   {getCategoryName="%"+input.getCategoryName().trim().trim().toUpperCase()+"%";}
		   
		   String desc=null;
		   if(input.getProductDesc()!=null && !input.getProductDesc().equals(""))
		   {desc="%"+input.getProductDesc().trim().toUpperCase()+"%";}
			
		   Double qty = null;
		   if(input.getQuantity()!=null && !input.getQuantity().equals(""))
		   {qty=input.getQuantity();}
		   
		   Double baseAmt = null;
		   if(input.getBaseAmount()!=null && !input.getBaseAmount().equals(""))
		   {baseAmt=input.getBaseAmount();}
		   
		   
		   
		  System.out.println("input = "+input);
				
				List<LtPrLines> list = (List<LtPrLines>) 
						jdbcTemplate.query(query , new Object[]{prHeaderId,
								lineNum,getCategoryName,desc,qty,baseAmt,
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								
								input.getStart()+input.getLength(),input.getStart()+1},
					 new  BeanPropertyRowMapper<LtPrLines>(LtPrLines.class));
					System.out.println("count of invoice is "+list.size());
					
				return list;
	}

	@Override
	public Integer save(LtPrLines ltPrLines) throws ServiceException {
		// TODO Auto-generated method stub
		LtPrLines LtPrLinesSaved =  ltPrLinesRepository.save(ltPrLines);
		if(LtPrLinesSaved!=null) {
			return LtPrLinesSaved.getPrHeaderId();
		}else {
			return null;
		}
	}

	@Override
	public boolean update(LtPrLines ltPrLines) throws ServiceException {
		ltPrLines = ltPrLinesRepository.save(ltPrLines);
				if(ltPrLines.getPrLineId()!=null) {
					
					return true;
				}
				else {
					return false;
				}
			
	}

	@Override
	public boolean delete(Long prLineId) throws ServiceException {
		// TODO Auto-generated method stub
				String query = env.getProperty("deletePrLineById");
				
				int res=jdbcTemplate.update(query, prLineId);
				
				if(res!=0)
					return true;
				else
					return false;
	}

	@Override
	public LtPrLines getPrLineById(Long prLineId) {
		// TODO Auto-generated method stub
				String query = env.getProperty("getPrLineById");
				List<LtPrLines> list=   jdbcTemplate.query(query, new Object[]{prLineId  }, 
						 new BeanPropertyRowMapper<LtPrLines>(LtPrLines.class)); 
				if(list.isEmpty())
					return null;
				else
				 return list.get(0);
	}

	@Override
	public Long getLtLinesDataTableCount(LtPrLines input, Long prHeaderId) throws ServiceException {


		String query = env.getProperty("getLtPrLinesDataTableCount");
		Integer lineNum=null;
		   if(input.getLineNo()!=null && !input.getLineNo().equals(""))
		   {lineNum=input.getLineNo();}
		  
			
		   String getCategoryName=null;
		   if(input.getCategoryName()!=null &&  !input.getCategoryName().equals("")) 
		   {getCategoryName="%"+input.getCategoryName().trim().trim().toUpperCase()+"%";}
		   
		   String desc=null;
		   if(input.getProductDesc()!=null && !input.getProductDesc().equals(""))
		   {desc="%"+input.getProductDesc().trim().toUpperCase()+"%";}
			
		   Double qty = null;
		   if(input.getQuantity()!=null && !input.getQuantity().equals(""))
		   {qty=input.getQuantity();}
		   
		   Double baseAmt = null;
		   if(input.getBaseAmount()!=null && !input.getBaseAmount().equals(""))
		   {baseAmt=input.getBaseAmount();}
		  
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {prHeaderId,
						lineNum,getCategoryName,desc,qty,baseAmt}, String.class);

		return Long.parseLong(count);
	}

}
