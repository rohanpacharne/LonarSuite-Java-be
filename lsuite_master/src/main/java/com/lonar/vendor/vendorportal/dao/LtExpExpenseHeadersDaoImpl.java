package com.lonar.vendor.vendorportal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.ExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpExpenseLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtExpExpenseHeadersRepository;

@Component
@PropertySource(value = "classpath:branchMasterQueries.properties", ignoreResourceNotFound = true)
public class LtExpExpenseHeadersDaoImpl implements LtExpExpenseHeadersDao {
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtExpExpenseHeadersRepository ltExpExpenseHeadersRepository;
	
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
	public Long getCount(LtExpExpenseHeaders input,Long empId,String category) throws Exception {
		
		String query = env.getProperty("getCountLtExpExpenseHeaders");
		
		String expCategory=null;
		   if(input.getExpenseCategory()!=null && !input.getExpenseCategory().equals(""))
		   {expCategory= "%"+input.getExpenseCategory().trim().toUpperCase()+"%";}
			  
		String expNumber=null;
		   if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
		   {expNumber=new String(input.getExpenseNumber());
			   expNumber="%"+expNumber.trim().toUpperCase()+"%";}
		   
		   String expAmount=null;
		   if(input.getExpenseAmount()!=null)
		   {expAmount="%"+Double.valueOf(input.getExpenseAmount()).intValue()+"%";}
		   
		   
		   String status=null;
		   if(input.getStatusMessage()!=null && !input.getStatusMessage().equals(""))
		   {status="%"+input.getStatusMessage().trim().toUpperCase()+"%";}
			   
			
		   if(input.getStDate() == null || input.getStDate().trim().equals(""))
		   {input.setStDate(null);}
		   
		   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		   {input.setEnDate(null);}
		   
		   if(input.getSubDate() == null || input.getSubDate().trim().equals(""))
		   {input.setSubDate(null);}
		  
			
		   String input1=null;
		   String input2=null;
		   if(category.equals("expense"))
		   {
			   input1=EXPENSE;
			   input2 =MILEAGE;
		   }
		   else
		   {
			   input1=ADVANCE;
			   input2 =ADVANCE;
		   }	
				
		   String count  = (String)getJdbcTemplate().queryForObject(
					query, new Object[] {input1,input2,empId,expNumber,expCategory,expAmount,status,
							input.getStDate(),input.getEnDate(),input.getSubDate()
							 }, String.class);
			return Long.parseLong(count);
		
	}
	
	@Override
	public Long getEmployeeDataTableCount(LtExpExpenseHeaders input, Long empId, String category) throws Exception
	{
		String query = env.getProperty("getEmployeeDataTableCount");
		
		String empName=null;
		   if(input.getEmployeeName()!=null && !input.getEmployeeName().equals(""))
		   {empName= "%"+input.getEmployeeName().trim().toUpperCase()+"%";}
		   
	String expCategory=null;
	   if(input.getExpenseCategory()!=null && !input.getExpenseCategory().equals(""))
	   {expCategory= "%"+input.getExpenseCategory().trim().toUpperCase()+"%";}
		  
	String expNumber=null;
	   if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
	   {expNumber=new String(input.getExpenseNumber());
		   expNumber="%"+expNumber.trim().toUpperCase()+"%";}
	   
	   String expAmount=null;
	   if(input.getExpenseAmount()!=null)
	   {expAmount="%"+Double.valueOf(input.getExpenseAmount()).intValue()+"%";}
	   
	   
	   String status=null;
	   if(input.getStatusMessage()!=null && !input.getStatusMessage().equals(""))
	   {status="%"+input.getStatusMessage().trim().toUpperCase()+"%";}
		   
		
	   if(input.getStDate() == null || input.getStDate().trim().equals(""))
	   {input.setStDate(null);}
	   
	   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
	   {input.setEnDate(null);}
	   
	   if(input.getSubDate() == null || input.getSubDate().trim().equals(""))
	   {input.setSubDate(null);}
	  
	   if(expNumber!= null && expNumber.equals(" "))
	   { expNumber = null;}
	   if(expCategory!= null && expCategory.equals(" "))
	   { expCategory = null;}
	   if(status !=null && status.equals(""))
	   { status=null;}
	   if(input.getColumnNo()==0)
	   {input.setColumnNo(8);}
		
			
		   String input1=null;
		   String input2=null;
		   if(category.equals("expense"))
		   {
			   input1=EXPENSE;
			   input2 =MILEAGE;
		   }
		   else
		   {
			   input1=ADVANCE;
			   input2 =ADVANCE;
		   }	
				
		   String count  = (String)getJdbcTemplate().queryForObject(
					query, new Object[] {input1,input2,empId,expNumber,empName,expCategory,expAmount,status,
							input.getStDate(),input.getEnDate(),input.getSubDate()
							 }, String.class);
			return Long.parseLong(count);
	}
	
	@Override
	public Long getAllDataTableCount(LtExpExpenseHeaders input, Long empId, String category) throws Exception
	{
		String query = env.getProperty("getAllDataTableCount");
		
		String empName=null;
		   if(input.getEmployeeName()!=null && !input.getEmployeeName().equals(""))
		   {empName= "%"+input.getEmployeeName().trim().toUpperCase()+"%";}
		   
		String expCategory=null;
		   if(input.getExpenseCategory()!=null && !input.getExpenseCategory().equals(""))
		   {expCategory= "%"+input.getExpenseCategory()+"%";}
		   
			String expNumber=null;
		   if(input.getExpenseNumber()!=null  && !input.getExpenseNumber().equals(""))
		   {expNumber="%"+input.getExpenseNumber()+"%";}
		   
		   String expAmount=null;
		   if(input.getExpenseAmount()!=null)
		   {expAmount="%"+input.getExpenseAmount()+"%";}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals(""))
		   {status="%"+input.getStatus()+"%";}
			   
			
		   if(input.getStDate() == null || input.getStDate().trim().equals(""))
		   {input.setStDate(null);}
		   
		   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		   {input.setEnDate(null);}
		   
		   if(input.getSubDate() == null || input.getSubDate().trim().equals(""))
		   {input.setSubDate(null);}
			
		   String input1=null;
		   String input2=null;
		   if(category.equals("expense"))
		   {
			   input1=EXPENSE;
			   input2 =MILEAGE;
		   }
		   else
		   {
			   input1=ADVANCE;
			   input2 =ADVANCE;
		   }	
				
		   String count  = (String)getJdbcTemplate().queryForObject(
					query, new Object[] {input1,input2,expNumber,empName,expCategory,expAmount,status,
							input.getStDate(),input.getEnDate(),input.getSubDate()
							 }, String.class);
			return Long.parseLong(count);
	}
 
	@Override
	public List<LtExpExpenseHeaders> getExpenseRecords(LtExpExpenseHeaders input,Long empId,String category) throws Exception
	{
//		System.out.println("input = "+input);
//		System.out.println("empId = "+empId);
//		System.out.println("category = "+category);
		String query = env.getProperty("getLtExpExpenseHeadersDatatable");
		
		String expCategory=null;
		   if(input.getExpenseCategory()!=null && !input.getExpenseCategory().equals(""))
		   {expCategory= "%"+input.getExpenseCategory().trim().toUpperCase()+"%";}
			  
		String expNumber=null;
		   if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
		   {expNumber=new String(input.getExpenseNumber());
			   expNumber="%"+expNumber.trim().toUpperCase()+"%";}
		   
		   String expAmount=null;
		   if(input.getExpenseAmount()!=null)
		   {expAmount="%"+Double.valueOf(input.getExpenseAmount()).intValue()+"%";}
		   
		   
		   String status=null;
		   if(input.getStatusMessage()!=null && !input.getStatusMessage().equals(""))
		   {status="%"+input.getStatusMessage().trim().toUpperCase()+"%";}
			   
			
		   if(input.getStDate() == null || input.getStDate().trim().equals(""))
		   {input.setStDate(null);}
		   
		   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		   {input.setEnDate(null);}
		   
		   if(input.getSubDate() == null || input.getSubDate().trim().equals(""))
		   {input.setSubDate(null);}
		  
		   if(expNumber!= null && expNumber.equals(" "))
		   { expNumber = null;}
		   if(expCategory!= null && expCategory.equals(" "))
		   { expCategory = null;}
		   if(status !=null && status.equals(""))
		   { status=null;}
		   if(input.getColumnNo()==0)
		   {input.setColumnNo(8);}
		
		   Long end=input.getStart()+input.getLength();
		   Long stat= input.getStart()+1;
		   
		   String input1=null;
		   String input2=null;
		   if(category.equals("expense"))
		   {
			   input1=EXPENSE;
			   input2 =MILEAGE;
		   }
		   else
		   {
			   input1=ADVANCE;
			   input2 =ADVANCE;
		   }
			
//			System.out.println("input1 = "+input1);
//			System.out.println("input2 = "+input2);
//			System.out.println("empId = "+empId);
//			System.out.println("expNumber = "+expNumber);
//			System.out.println("expCategory = "+expCategory);
//			System.out.println("expAmount = "+expAmount);
//			System.out.println("status = "+status);
//			System.out.println("input.getStDate() = "+input.getStDate());
//			System.out.println("input.getEnDate() = "+input.getEnDate());
//			System.out.println("input.getSubDate() = "+input.getSubDate());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("input.getColumnNo() = "+input.getColumnNo());
//			System.out.println("end = "+end);
//			System.out.println("stat = "+stat);


		  return jdbcTemplate.query(query, new Object[] { input1,input2,empId,expNumber,expCategory,expAmount,status,
					input.getStDate(),input.getEnDate(),input.getSubDate(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					end,stat
					  },
 
					new RowMapper<LtExpExpenseHeaders>() {
						@Override
						public LtExpExpenseHeaders mapRow(ResultSet rs, int row) throws SQLException {
							LtExpExpenseHeaders ltExpExpenseHeaders = new LtExpExpenseHeaders();
							
							ltExpExpenseHeaders.setExpenseNumber(rs.getString("EXPENSE_NUMBER"));
				            ltExpExpenseHeaders.setExpenseAmount(rs.getDouble("EXPENSE_AMOUNT")); // Ensure case sensitivity
				            ltExpExpenseHeaders.setStatus(rs.getString("STATUS"));
				            ltExpExpenseHeaders.setStartDate(rs.getDate("START_DATE"));
				            ltExpExpenseHeaders.setEndDate(rs.getDate("END_DATE"));
				            ltExpExpenseHeaders.setExpenseSubmissionDate(rs.getDate("EXPENSE_SUBMISSION_DATE"));
				            ltExpExpenseHeaders.setVendorName(rs.getString("VENDOR_NAME"));
				            ltExpExpenseHeaders.setVendorAddr(rs.getString("VENDOR_ADDR"));
				            ltExpExpenseHeaders.setCostCenterName(rs.getString("COST_CENTER_NAME"));
				            ltExpExpenseHeaders.setDivisionName(rs.getString("DIVISION_NAME"));
				            ltExpExpenseHeaders.setApprovedDate(rs.getDate("APPROVED_DATE"));
				            ltExpExpenseHeaders.setExpHeaderId(rs.getLong("EXP_HEADER_ID")); // Ensure case sensitivity
				            ltExpExpenseHeaders.setCurrencyCode(rs.getString("CURRENCY_CODE")); // Ensure case sensitivity
				            ltExpExpenseHeaders.setExchangeRate(rs.getDouble("EXCHANGE_RATE")); // Ensure case sensitivity
				            ltExpExpenseHeaders.setLocationName(rs.getString("LOCATION_NAME"));
				            ltExpExpenseHeaders.setExpenseCategory(rs.getString("EXPENSE_CATEGORY"));
				            ltExpExpenseHeaders.setEmployeeName(rs.getString("EMPLOYEE_NAME")); // Ensure case sensitivity
				            ltExpExpenseHeaders.setValueCode(rs.getString("VALUE_CODE"));
				            ltExpExpenseHeaders.setValueName(rs.getString("VALUE_NAME"));
//				
//							ltExpExpenseHeaders.setExpenseNumber(rs.getString("EXPENSE_NUMBER"));
//							ltExpExpenseHeaders.setExpenseAmount(rs.getDouble("Expense_Amount"));
//							ltExpExpenseHeaders.setStatus(rs.getString("STATUS"));
//							ltExpExpenseHeaders.setStartDate(rs.getDate("START_DATE"));
//							ltExpExpenseHeaders.setEndDate(rs.getDate("END_DATE"));
//							ltExpExpenseHeaders.setExpenseSubmissionDate(rs.getDate("EXPENSE_SUBMISSION_DATE"));
//							ltExpExpenseHeaders.setVendorName(rs.getString("VENDOR_NAME"));
//							ltExpExpenseHeaders.setVendorAddr(rs.getString("VENDOR_ADDR"));
//							ltExpExpenseHeaders.setCostCenterName(rs.getString("COST_CENTER_NAME"));
//							ltExpExpenseHeaders.setDivisionName(rs.getString("Division_Name"));
//							ltExpExpenseHeaders.setApprovedDate(rs.getDate("APPROVED_DATE"));
//							ltExpExpenseHeaders.setExpHeaderId(rs.getLong("Exp_Header_id"));
//							ltExpExpenseHeaders.setCurrencyCode(rs.getString("Currency_Code"));
//							ltExpExpenseHeaders.setExchangeRate(rs.getDouble("Exchange_Rate"));
//							ltExpExpenseHeaders.setLocationName(rs.getString("LOCATION_NAME"));
//							//ltExpExpenseHeaders.setDeptId(rs.getString("DEPT_ID"));
//							ltExpExpenseHeaders.setExpenseCategory(rs.getString("EXPENSE_CATEGORY"));
//							ltExpExpenseHeaders.setEmployeeName(rs.getString("employee_Name"));
							
							return ltExpExpenseHeaders;
							
						}
 
					}
 
			);
		
	}
	
	@Override
	public List<LtExpExpenseHeaders> getEmployeeDataTableExpenseRecords(LtExpExpenseHeaders input, Long empId,
			String category) throws Exception
	{
		String query = env.getProperty("getEmployeeDataTableExpenseRecords");
	
		String empName=null;
		   if(input.getEmployeeName()!=null && !input.getEmployeeName().equals(""))
		   {empName= "%"+input.getEmployeeName().trim().toUpperCase()+"%";}
		   
	String expCategory=null;
	   if(input.getExpenseCategory()!=null && !input.getExpenseCategory().equals(""))
	   {expCategory= "%"+input.getExpenseCategory().trim().toUpperCase()+"%";}
		  
	String expNumber=null;
	   if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
	   {expNumber=new String(input.getExpenseNumber());
		   expNumber="%"+expNumber.trim().toUpperCase()+"%";}
	   
	   String expAmount=null;
	   if(input.getExpenseAmount()!=null)
	   {expAmount="%"+Double.valueOf(input.getExpenseAmount()).intValue()+"%";}
	   
	   
	   String status=null;
	   if(input.getStatusMessage()!=null && !input.getStatusMessage().equals(""))
	   {status="%"+input.getStatusMessage().trim().toUpperCase()+"%";}
		   
		
	   if(input.getStDate() == null || input.getStDate().trim().equals(""))
	   {input.setStDate(null);}
	   
	   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
	   {input.setEnDate(null);}
	   
	   if(input.getSubDate() == null || input.getSubDate().trim().equals(""))
	   {input.setSubDate(null);}
	  
	   if(expNumber!= null && expNumber.equals(" "))
	   { expNumber = null;}
	   if(expCategory!= null && expCategory.equals(" "))
	   { expCategory = null;}
	   if(status !=null && status.equals(""))
	   { status=null;}
	   if(input.getColumnNo()==0)
	   {input.setColumnNo(8);}
	
	   Long end=input.getStart()+input.getLength();
	   Long stat= input.getStart()+1;
	   
	   String input1=null;
	   String input2=null;
	   if(category.equals("expense"))
	   {
		   input1=EXPENSE;
		   input2 =MILEAGE;
	   }
	   else
	   {
		   input1=ADVANCE;
		   input2 =ADVANCE;
	   }
		
		System.out.println(input1+input2+empId);
	  return jdbcTemplate.query(query, new Object[] { input1,input2,empId,expNumber,empName,expCategory,expAmount,status,
				input.getStDate(),input.getEnDate(),input.getSubDate(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				end,stat
				  },
 
				new RowMapper<LtExpExpenseHeaders>() {
					@Override
					public LtExpExpenseHeaders mapRow(ResultSet rs, int row) throws SQLException {
						LtExpExpenseHeaders ltExpExpenseHeaders = new LtExpExpenseHeaders();
			
						ltExpExpenseHeaders.setExpenseNumber(rs.getString("EXPENSE_NUMBER"));
						ltExpExpenseHeaders.setExpenseAmount(rs.getDouble("Expense_Amount"));
						ltExpExpenseHeaders.setStatus(rs.getString("STATUS"));
						ltExpExpenseHeaders.setStartDate(rs.getDate("START_DATE"));
						ltExpExpenseHeaders.setEndDate(rs.getDate("END_DATE"));
						ltExpExpenseHeaders.setExpenseSubmissionDate(rs.getDate("EXPENSE_SUBMISSION_DATE"));
						ltExpExpenseHeaders.setVendorName(rs.getString("VENDOR_NAME"));
						ltExpExpenseHeaders.setVendorAddr(rs.getString("VENDOR_ADDR"));
						ltExpExpenseHeaders.setCostCenterName(rs.getString("COST_CENTER_NAME"));
						ltExpExpenseHeaders.setDivisionName(rs.getString("Division_Name"));
						ltExpExpenseHeaders.setApprovedDate(rs.getDate("APPROVED_DATE"));
						ltExpExpenseHeaders.setExpHeaderId(rs.getLong("Exp_Header_id"));
						ltExpExpenseHeaders.setCurrencyCode(rs.getString("Currency_Code"));
						ltExpExpenseHeaders.setExchangeRate(rs.getDouble("Exchange_Rate"));
						ltExpExpenseHeaders.setLocationName(rs.getString("LOCATION_NAME"));
						//ltExpExpenseHeaders.setDeptId(rs.getString("DEPT_ID"));
						ltExpExpenseHeaders.setExpenseCategory(rs.getString("EXPENSE_CATEGORY"));
						ltExpExpenseHeaders.setEmployeeName(rs.getString("employee_Name"));
						return ltExpExpenseHeaders;
						
					}
 
				}
 
		);
	}
	
	@Override
	public List<LtExpExpenseHeaders> getAllExpenseRecords(LtExpExpenseHeaders input, Long empId, String category)
			throws Exception {
		String query = env.getProperty("getAllExpenseRecords");
		
		String empName=null;
		   if(input.getEmployeeName()!=null && !input.getEmployeeName().equals(""))
		   {empName= "%"+input.getEmployeeName().trim().toUpperCase()+"%";}
		   
		String expCategory=null;
		   if(input.getExpenseCategory()!=null && !input.getExpenseCategory().equals(""))
		   {expCategory= "%"+input.getExpenseCategory().trim().toUpperCase()+"%";}
			  
		String expNumber=null;
		   if(input.getExpenseNumber()!=null && !input.getExpenseNumber().equals(""))
		   {expNumber=new String(input.getExpenseNumber());
			   expNumber="%"+expNumber.trim().toUpperCase()+"%";}
		   
		   String expAmount=null;
		   if(input.getExpenseAmount()!=null)
		   {expAmount="%"+Double.valueOf(input.getExpenseAmount()).intValue()+"%";}
		   
		   
		   String status=null;
		   if(input.getStatusMessage()!=null && !input.getStatusMessage().equals(""))
		   {status="%"+input.getStatusMessage().trim().toUpperCase()+"%";}
			   
			
		   if(input.getStDate() == null || input.getStDate().trim().equals(""))
		   {input.setStDate(null);}
		   
		   if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		   {input.setEnDate(null);}
		   
		   if(input.getSubDate() == null || input.getSubDate().trim().equals(""))
		   {input.setSubDate(null);}
		  
		   if(expNumber!= null && expNumber.equals(" "))
		   { expNumber = null;}
		   if(expCategory!= null && expCategory.equals(" "))
		   { expCategory = null;}
		   if(status !=null && status.equals(""))
		   { status=null;}
		   if(input.getColumnNo()==0)
		   {input.setColumnNo(8);}
		
		   Long end=input.getStart()+input.getLength();
		   Long stat= input.getStart()+1;
		   
		   String input1=null;
		   String input2=null;
		   if(category.equals("expense"))
		   {
			   input1=EXPENSE;
			   input2 =MILEAGE;
		   }
		   else
		   {
			   input1=ADVANCE;
			   input2 =ADVANCE;
		   }
			
			
		  return jdbcTemplate.query(query, new Object[] { input1,input2,expNumber,empName,expCategory,expAmount,status,
					input.getStDate(),input.getEnDate(),input.getSubDate(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					input.getColumnNo(),input.getColumnNo(),
					end,stat
					  },
 
					new RowMapper<LtExpExpenseHeaders>() {
						@Override
						public LtExpExpenseHeaders mapRow(ResultSet rs, int row) throws SQLException {
							LtExpExpenseHeaders ltExpExpenseHeaders = new LtExpExpenseHeaders();
				
							ltExpExpenseHeaders.setExpenseNumber(rs.getString("EXPENSE_NUMBER"));
							ltExpExpenseHeaders.setExpenseAmount(rs.getDouble("Expense_Amount"));
							ltExpExpenseHeaders.setStatus(rs.getString("STATUS"));
							ltExpExpenseHeaders.setStartDate(rs.getDate("START_DATE"));
							ltExpExpenseHeaders.setEndDate(rs.getDate("END_DATE"));
							ltExpExpenseHeaders.setExpenseSubmissionDate(rs.getDate("EXPENSE_SUBMISSION_DATE"));
							ltExpExpenseHeaders.setVendorName(rs.getString("VENDOR_NAME"));
							ltExpExpenseHeaders.setVendorAddr(rs.getString("VENDOR_ADDR"));
							ltExpExpenseHeaders.setCostCenterName(rs.getString("COST_CENTER_NAME"));
							ltExpExpenseHeaders.setDivisionName(rs.getString("Division_Name"));
							ltExpExpenseHeaders.setApprovedDate(rs.getDate("APPROVED_DATE"));
							ltExpExpenseHeaders.setExpHeaderId(rs.getLong("Exp_Header_id"));
							ltExpExpenseHeaders.setCurrencyCode(rs.getString("Currency_Code"));
							ltExpExpenseHeaders.setExchangeRate(rs.getDouble("Exchange_Rate"));
							ltExpExpenseHeaders.setLocationName(rs.getString("LOCATION_NAME"));
							//ltExpExpenseHeaders.setDeptId(rs.getString("DEPT_ID"));
							ltExpExpenseHeaders.setExpenseCategory(rs.getString("EXPENSE_CATEGORY"));
							ltExpExpenseHeaders.setEmployeeName(rs.getString("employee_Name"));
							
							return ltExpExpenseHeaders;
							
						}
 
					}
 
			);
	}
	
	@Override
	public boolean updateExpenseAmount(Long expenseHeaderId) throws Exception
	{
		System.out.println("in update Expense Amount method");
		String query = env.getProperty("updateExpenseAmount1");
		int res1=0;
		res1=jdbcTemplate.update(query,
				expenseHeaderId,new Date(),expenseHeaderId);
				if(res1!=0)
				{
					System.out.println("1");
					String query1 = env.getProperty("updateExpenseAmount2");
					List<LtExpExpenseLines> list=   jdbcTemplate.query(query1, new Object[]{ expenseHeaderId,expenseHeaderId},
							 new BeanPropertyRowMapper<LtExpExpenseLines>(LtExpExpenseLines.class));
					
					String queryStartDate = env.getProperty("queryStartDate");
					List<LtExpExpenseLines> list1=   jdbcTemplate.query(queryStartDate, new Object[]{ expenseHeaderId,expenseHeaderId},
							 new BeanPropertyRowMapper<LtExpExpenseLines>(LtExpExpenseLines.class));
					
					System.out.println("tej...."+list);
					if(list.size() > 0 && list1.size() > 0)
					{
						System.out.println("2");
						String query2 = env.getProperty("updateExpenseAmount3");
					
						int res2=0;
						res2=jdbcTemplate.update(query2,
								list.get(0).getEndDate(),list1.get(0).getStartDate(),
								new Date(),expenseHeaderId);
							
							if(res2!=0)
								return true;
							else
								return false;
					}
					else if(list.isEmpty() || list.size()<0)
					{
						System.out.println("3");
						String query2 = env.getProperty("updateExpenseAmount3");
						
						int res2=0;
						res2=jdbcTemplate.update(query2,
								null,new Date(),
								new Date(),expenseHeaderId);
							
							if(res2!=0)
								return true;
							else
								return false;
					}
					return true;
				}
				else
					return false;
	}
	
	
	@Override
	public Long save(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception
	{		
	/*	GeneratedKeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator()
			{
			    @Override
			    public PreparedStatement createPreparedStatement(Connection con) throws SQLException
			    {
			    	
			        PreparedStatement statement = con.prepareStatement
			        (" INSERT INTO LT_EXP_EXPENSE_HEADERS "
			        + " (EXP_HEADER_ID,Expense_Number,Employee_id,Start_Date,End_Date,Currency_Code,Exchange_Rate,"
			        + " Division_id,Location_Id,Cost_Center_id,Purpose,Vendor_ID,Vendor_Add_ID,Request_id,Source,Source_Ref_No,"
			        + " Invoice_Num,Voucher_Num,Set_of_books_id,Org_id,Status,"
			        + " Status_Message,Created_by,Creation_date,Last_update_login,Last_updated_by,Last_update_date ,Expense_Amount ) "
	     		+ " VALUES(lt_exp_expense_headers_s.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
			        statement.setString(1, ltExpExpenseHeaders.getExpenseNumber());
			        
			      //  statement.setTimestamp(2,  new Timestamp(ltExpExpenseHeaders.getExpenseSubmissionDate().getTime()));
			        statement.setLong(2, ltExpExpenseHeaders.getEmployeeId());
			        statement.setTimestamp(3, new Timestamp(ltExpExpenseHeaders.getStartDate().getTime()));
			        if(ltExpExpenseHeaders.getEndDate()!=null)
			        {
			        	statement.setTimestamp(4, new Timestamp(ltExpExpenseHeaders.getEndDate().getTime()));
			        }
			        else
			        {
			        	statement.setTimestamp(4,null);
			        }
			        statement.setString(5, ltExpExpenseHeaders.getCurrencyCode());
			        statement.setDouble(6, ltExpExpenseHeaders.getExchangeRate());
			        statement.setLong(7, ltExpExpenseHeaders.getDivisionId());
			        statement.setLong(8, ltExpExpenseHeaders.getLocationId());
			       
			        statement.setLong(9, ltExpExpenseHeaders.getCostCenterId());
			       
			        statement.setString(10, ltExpExpenseHeaders.getPurpose());
			       
			       if(ltExpExpenseHeaders.getVendorId() != null){
			    	   statement.setLong(11, ltExpExpenseHeaders.getVendorId());
			       }
			       else{
			        statement.setNull(11, java.sql.Types.NULL);}
			       if(ltExpExpenseHeaders.getVendorAddId()!=null){
			        statement.setLong(12, ltExpExpenseHeaders.getVendorAddId());
			       }else{
			    	   {
			    		   statement.setNull(12, java.sql.Types.NULL);
			    	   }
			       }
			        statement.setString(13, ltExpExpenseHeaders.getRequestId());
			        statement.setString(14, ltExpExpenseHeaders.getSource());
			        statement.setString(15, ltExpExpenseHeaders.getSourceRefNo());
			        statement.setString(16, ltExpExpenseHeaders.getInvoiceNum());
			   //     statement.setTimestamp(18, new Timestamp(ltExpExpenseHeaders.getInvoiceDate().getTime()));
			        statement.setString(17, ltExpExpenseHeaders.getVoucherNum());
			   //     statement.setTimestamp(20, new Timestamp(ltExpExpenseHeaders.getVoucherDate().getTime()));
			   //     statement.setTimestamp(21,new Timestamp( ltExpExpenseHeaders.getApprovedDate().getTime()));
			        statement.setLong(18, ltExpExpenseHeaders.getSetOfBooksId());
			        statement.setLong(19, ltExpExpenseHeaders.getOrgId());
			        statement.setString(20, ltExpExpenseHeaders.getStatus());
			        statement.setString(21, ltExpExpenseHeaders.getStatusMessage());
			        statement.setLong(22, ltExpExpenseHeaders.getCreatedBy());
			        statement.setTimestamp(23, new Timestamp(ltExpExpenseHeaders.getCreationDate().getTime()));
			        statement.setLong(24, ltExpExpenseHeaders.getLastUpdateLogin());
			        statement.setLong(25, ltExpExpenseHeaders.getLastUpdatedBy());
			        statement.setTimestamp(26,new Timestamp( ltExpExpenseHeaders.getLastUpdateDate().getTime()));
			        statement.setDouble(27, 0);
			        return statement;
			    }
			}, holder);*/
 
			//Long primaryKey = holder.getKey().longValue();
		ltExpExpenseHeaders=ltExpExpenseHeadersRepository.save(ltExpExpenseHeaders);
			return ltExpExpenseHeaders.getExpHeaderId();
	}
	
	@Override
	public boolean update(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception
	{
		LtExpExpenseHeaders ltExpExpenseHeader=ltExpExpenseHeadersRepository.save(ltExpExpenseHeaders);
		if(ltExpExpenseHeader.getExpenseNumber() != null)
		{	return true; }
		else
		{	return false; }
	}
	
	@Override
	public LtExpExpenseHeaders findOne(Long headerId) throws Exception 
	{
		String query = env.getProperty("findOneLtExpExpenseHeaders");
	
		List<LtExpExpenseHeaders> list = (List<LtExpExpenseHeaders>) 
				jdbcTemplate.query(query , new Object[]{ headerId},
			 new  BeanPropertyRowMapper<LtExpExpenseHeaders>(LtExpExpenseHeaders.class));

		if(!list.isEmpty())
		return list.get(0);
		else
			return null;
	}
	
	
	@Override
	public boolean delete(Long expId) throws Exception
	{
		String query = env.getProperty("deleteLtExpExpenseHeaders");
		
		int res=jdbcTemplate.update(query,expId);
//System.out.println("4");
		if(res!= 0)
			return true;
		else
			return false;
	}
	
	@Override
	public LtExpExpenseHeaders getExpenseStatusById(Long expenseHeaderId) throws ServiceException {
		
		Status status = new Status();
		String query = " select STATUS, CREATED_BY from LT_EXP_EXPENSE_HEADERS where EXP_HEADER_ID = ? ";
		
		List<LtExpExpenseHeaders> itExpenseHeadersList = jdbcTemplate.query(query, new Object[] {expenseHeaderId},
				new RowMapper<LtExpExpenseHeaders>() {
					public LtExpExpenseHeaders mapRow(ResultSet rs, int arg1) throws SQLException {

						LtExpExpenseHeaders expenseHeaders = new LtExpExpenseHeaders();
						
						expenseHeaders.setStatus(rs.getString("STATUS"));
						expenseHeaders.setCreatedBy(rs.getLong("CREATED_BY"));
						
						return expenseHeaders;
					}
				});
		
		
		if(itExpenseHeadersList.size() > 0){
			return itExpenseHeadersList.get(0);
		}else {
			return null;
		}
		
		
	}
	
	@Override
	public List<LtExpExpenseHeaders> getExpenseList(String status) throws Exception
	{
		String query = env.getProperty("getExpenseList");
	
		List<LtExpExpenseHeaders> list=   jdbcTemplate.query(query, new Object[]{ status }, 
			 new BeanPropertyRowMapper<LtExpExpenseHeaders>(LtExpExpenseHeaders.class)); 
	
		return list;
	}
	
	@Override
	public  ExpenseApproval  getApprovalLevel(Long expHeaderId) throws Exception 
	{
	
		String query = env.getProperty("getApprovalLevel");
		
		List<ExpenseApproval> expenseApprovalList = jdbcTemplate.query(query, new Object[] {expHeaderId},
				
				new RowMapper<ExpenseApproval>() {
					public ExpenseApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						ExpenseApproval expenseApproval = new ExpenseApproval();

						expenseApproval.setApprovalLevel(rs.getString("MIN_LEVEL"));
						expenseApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						expenseApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						
						
						return expenseApproval;
					}
				});
		if(expenseApprovalList.size()>0)
		return expenseApprovalList.get(0); 
		else return null;
		
	}
	
	
	@Override
	public List<ExpenseApproval> getApprovalList(Long expHeaderId, String level) throws Exception
	{		
		String query = env.getProperty("getApprovalList");
		
		List<ExpenseApproval> list=   jdbcTemplate.query(query, new Object[]{ expHeaderId,level}, 
			 new BeanPropertyRowMapper<ExpenseApproval>(ExpenseApproval.class));
		return list;
	}
	
	@Override
	public String getNextApprovalLevel(Long expHeaderId, String currentApprovalLevel) throws Exception 
	{
		String query = env.getProperty("getNextApprovalLevel");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { expHeaderId, currentApprovalLevel}, String.class);

		return nextlavel;
	}
	
	public boolean upDateStatus(Long expHeaderId, String status, String currentLevel) 
	{
		int res=0;
	System.out.println("expHeaderId "+expHeaderId+" status "+status+ " currentLevel "+currentLevel);
		if(currentLevel!=null)
		{
			String query = env.getProperty("upDateStatus1");
			 res=jdbcTemplate.update(query,
				        status,new Date(),expHeaderId,currentLevel,APPROVED);
		}
		else
		{
			String query = env.getProperty("upDateStatus2");
			
			res=jdbcTemplate.update(query,
			        status,new Date(),currentLevel,expHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public void updateCurrentApprovalLevel(Long expHeaderId, String approvalLevel) throws Exception 
	{
		String query = env.getProperty("updateCurrentApprovalLevel");
		
		int res=jdbcTemplate.update(query,
		        approvalLevel, expHeaderId );
	}
	
	@Override
	public LtExpExpenseHeaders findEmpDetails(Long expHeaderId) throws Exception {
		String query = env.getProperty("findEmpDetailsByExpHeaderId");
		
		List<LtExpExpenseHeaders> list = (List<LtExpExpenseHeaders>) 
				jdbcTemplate.query(query , new Object[]{ expHeaderId},
			 new  BeanPropertyRowMapper<LtExpExpenseHeaders>(LtExpExpenseHeaders.class));

		if(!list.isEmpty())
		return list.get(0);
		else
			return new LtExpExpenseHeaders();
	}
	
	@Override
	public boolean checkStatusIsPending(Long expHeaderId, Long approvalId) throws ServiceException {
		String query = " select * from LT_EXPENSE_APPROVAL "
				 		+" where EXP_HEADER_ID = ? "
						 +" AND (APPROVAL_ID = ? OR DELEGATION_ID = ? ) "
						 +" AND APPROVAL_LEVEL = CURRENT_APPROVAL_LEVEL "
						 +" AND STATUS = 'PENDING' ";
				 
		List<ExpenseApproval> list=   jdbcTemplate.query(query, new Object[]{expHeaderId, approvalId,approvalId}, 
					 new BeanPropertyRowMapper<ExpenseApproval>(ExpenseApproval.class)); 
		
		if(list.size() > 0)
			return true;
		else
		return false;
	}
	
	@Override
	public boolean deleteExpenseHeaderAttachments(Long expAttachId) throws Exception {
		// TODO Auto-generated method stub
		String query = env.getProperty("deleteExpenseHeaderAttachments");
		int res=jdbcTemplate.update(query,expAttachId);
		System.out.println("res in else "+res);
		if(res!=0)
			return true;
		else
			return false;
	}

}
