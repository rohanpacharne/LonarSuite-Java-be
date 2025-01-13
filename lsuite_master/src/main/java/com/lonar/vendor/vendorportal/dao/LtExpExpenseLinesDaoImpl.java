package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.model.LtExpExpenseLines;
import com.lonar.vendor.vendorportal.repository.LtExpExpenseLinesRepository;

@Component
//expenseLinesQueries
@PropertySource(value= "classpath:branchMasterQueries.properties",ignoreResourceNotFound = true)
public class LtExpExpenseLinesDaoImpl implements LtExpExpenseLinesDao{
	
	@Autowired
	LtExpExpenseLinesRepository ltExpExpenseLinesRepository;
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
 
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	@Override
	public Long getCount(Long headerId,Long userId, LtExpExpenseLines input) throws Exception
	{
		
		
		String query = env.getProperty("getCount");
				
			String lineId=null;
		   if(input.getExpLineId()!=null)
		   {lineId="%"+input.getExpLineId()+"%";}
		   
		   String receiptAmt=null;
		   if(input.getReceiptAmount()!=null)
		   {receiptAmt="%"+input.getReceiptAmount()+"%";}
		   
		   String justification=null;
		   if(input.getJustification()!=null && !input.getJustification().equals(""))
		   {justification="%"+input.getJustification()+"%";}
		  
		   
		   if(input.getStDate() == null || input.getStDate().trim().equals(""))
			{
				input.setStDate(null);
			}
			if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
			{
				input.setEnDate(null);
			}
			
				
				String count  = getJdbcTemplate().queryForObject(
						query, new Object[] { headerId,userId,
								}, String.class);
 
				return Long.parseLong(count);
	}
	
	@Override
	public List<LtExpExpenseLines> getDatatableByExpenseHeaderId(Long headerId,Long userId, LtExpExpenseLines input)
			throws Exception
	{
		
		String query = env.getProperty("getDatatableByExpenseHeaderId");
				
		   String lineId=null;
		   if(input.getExpLineId()!=null)
		   {lineId="%"+input.getExpLineId()+"%";}
		   
		   String receiptAmt=null;
		   if(input.getReceiptAmount()!=null)
		   {receiptAmt="%"+input.getReceiptAmount()+"%";}
		   
		   String justification=null;
		   if(input.getJustification()!=null)
		   {justification="%"+input.getJustification()+"%";}
		   
		   String expNature=null;
		   if(input.getExpenseNature()!=null)
		   {expNature="%"+input.getExpenseNature()+"%";}
		   

		   String expAmount=null;
		   if(input.getAmount()!=null)
		   {expAmount="%"+Double.valueOf(input.getAmount()).intValue()+"%";}
		   
		   String expPurpose=null;
		   if(input.getPurpose()!=null)
		   {expPurpose="%"+input.getPurpose()+"%";}
		   
		   
		  
		   
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
				input.setColumnNo(2);
			}
			
//			System.out.println("tej "+input.getColumnNo());
//			List<LtExpExpenseLines> list=   jdbcTemplate.query(query, new Object[]{ headerId ,
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getColumnNo(),input.getColumnNo(),
//					input.getStart()+input.getLength(), input.getStart()+1},
//			 new BeanPropertyRowMapper<LtExpExpenseLines>(LtExpExpenseLines.class));
			
			List<LtExpExpenseLines> list=   jdbcTemplate.query(query, new Object[]{ headerId ,userId,expNature,
					expAmount,expPurpose,input.getStDate(),input.getEnDate(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getStart()+input.getLength(), input.getStart()+1},
			 new BeanPropertyRowMapper<LtExpExpenseLines>(LtExpExpenseLines.class));
				
			
				return list;
			
			
			
	}
 
	
	
	@Override
	public LtExpExpenseLines updateV1(LtExpExpenseLines ltExpExpenseLines) throws Exception {
		ltExpExpenseLines = ltExpExpenseLinesRepository.save(ltExpExpenseLines);
		
		return ltExpExpenseLines;
	}
	
	@Override
	public LtExpExpenseLines saveV1(LtExpExpenseLines ltExpExpenseLines) throws Exception {
		ltExpExpenseLines = ltExpExpenseLinesRepository.save(ltExpExpenseLines);
	
			return ltExpExpenseLines;
		
	}
	
	@Transactional
	@Override
	public List<LtExpExpenseLines> getByExpenseHeaderId(Long expenseHeaderId) throws Exception 
	{
		
		
		String query = env.getProperty("getExpenseLinesByExpenseHeaderId");
		List<LtExpExpenseLines> list=   jdbcTemplate.query(query, new Object[]{ expenseHeaderId }, 
						 new BeanPropertyRowMapper<LtExpExpenseLines>(LtExpExpenseLines.class)); 
		return list;
	}
	
	@Override
	public LtExpExpenseLines getExpenseLinesByLineIdV1(Long lineId) throws Exception {
		String query = env.getProperty("getExpenseLinesByLineIdV1");
		List<LtExpExpenseLines> list=   jdbcTemplate.query(query, new Object[]{ lineId }, 
				 new BeanPropertyRowMapper<LtExpExpenseLines>(LtExpExpenseLines.class)); 
		 System.out.println("expense line list = "+list);
		if(!list.isEmpty())
		return list.get(0);
		else
			return null;
	}
	
	@Override
	public boolean delete(Long expLineId,String category) throws Exception
	{
		System.out.println("expLineId = "+expLineId+" "+category);
		if(category!= null )
		{
			if(category.equals("MILEAGE"))
			{
				String query = env.getProperty("deleteMileageLine");
				int res=jdbcTemplate.update(query,expLineId);
				if(res!=0)
				{
					String query1 = env.getProperty("deleteLine");
					res=jdbcTemplate.update(query1,expLineId);
		
					if(res!=0)
						return true;
					else
						return false;
				}
			}
			else
			{
				String query1 = env.getProperty("deleteLine");
				int res=jdbcTemplate.update(query1,expLineId);
			System.out.println("res = "+res);
				if(res!=0)
					return true;
				else
					return false;
			}
		}
		else
		{
			String query1 = env.getProperty("deleteLine");
			int res=jdbcTemplate.update(query1,expLineId);
			System.out.println("res in else "+res);
			if(res!=0)
				return true;
			else
				return false;
		}
		return false;
	}
	
	@Override
	public LtExpExpenseLines getExpenseLinesByLineId(Long lineId) throws Exception
	{
		String query = env.getProperty("getExpenseLinesByLineId");
		List<LtExpExpenseLines> list=   jdbcTemplate.query(query, new Object[]{ lineId },
				 new BeanPropertyRowMapper<LtExpExpenseLines>(LtExpExpenseLines.class));
		
		if(!list.isEmpty())
		return list.get(0);
		else
			return null;
	}

	@Override
	public boolean deleteExpenseLineAttachments(Long expLineId) throws Exception {
		// TODO Auto-generated method stub
		String query = env.getProperty("deleteExpenseLineAttachments");
		int res=jdbcTemplate.update(query,expLineId);
		System.out.println("res in else "+res);
		if(res!=0)
			return true;
		else
			return false;
	}
	
	public boolean updateHeader(Long expHeaderId, List<Long> expLineIds) {
	    // Ensure the query updates the header ID for a given line ID
	    String query = "UPDATE lt_exp_expense_lines SET exp_header_id = ? WHERE exp_line_id = ?"; // Replace `expense_table` with your actual table name

	    int totalUpdated = 0;

	    // Iterate over the list of line IDs and update each one
	    for (Long expLineId : expLineIds) {
	        int res = jdbcTemplate.update(query, expHeaderId, expLineId);
	        totalUpdated += res;
	        System.out.println("Updated line ID: " + expLineId + ", Result: " + res);
	    }

	    // Return true if at least one row was updated
	    return totalUpdated > 0;
	}


}
