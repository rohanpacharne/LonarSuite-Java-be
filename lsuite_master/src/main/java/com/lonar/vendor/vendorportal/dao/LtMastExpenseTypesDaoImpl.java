package com.lonar.vendor.vendorportal.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import com.lonar.vendor.vendorportal.model.LtMastExpenseTypes;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastExpenseTypeRepository;


@Component
@PropertySource(value = "classpath:branchMasterQueries.properties", ignoreResourceNotFound = true)
public class LtMastExpenseTypesDaoImpl implements LtMastExpenseTypesDao {
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	LtMastExpenseTypeRepository ltMastExpenseTypeRepository;
	
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
	public Long getExpenseTypesCount(LtMastExpenseTypes input,Long companyId) throws Exception
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
		
//		String glCode=null;
//		if(input.getGlCode()!= null && !input.getGlCode().equals(""))
//		{glCode = "%"+input.getGlCode().trim().toUpperCase()+"%" ;}
//		
		String glDesc=null;
		if(input.getGlAccountName()!= null && !input.getGlAccountName().equals(""))
		{glDesc = "%"+input.getGlAccountName().trim().toUpperCase()+"%" ;}
		
		String status=null;
		if(input.getStatus()!= null && !input.getStatus().equals(""))
		{status = "%"+input.getStatus().trim().toUpperCase()+"%" ;}
		
		if(input.getStDate() == null || input.getStDate().trim().equals(""))
		{input.setStDate(null);}
		   
		if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		{input.setEnDate(null);}
		
//		String count  = (String)getJdbcTemplate().queryForObject(
//				query, new Object[] {nature,desc,type,glCode,glDesc,status,input.getStDate(),input.getEnDate()}, String.class);
		//String count  = (String)getJdbcTemplate().queryForObject(query,String.class);
		
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {companyId,nature,desc,type,glDesc,status,input.getStDate(),input.getEnDate()}, String.class);
		
		return Long.parseLong(count);
	}
 
 
	@Override
	public List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input,Long companyId) throws Exception
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
		
//		String glCode=null;
//		if(input.getGlCode()!= null && !input.getGlCode().equals(""))
//		{glCode = "%"+input.getGlCode().trim().toUpperCase()+"%" ;}
//		
		String glDesc=null;
		if(input.getGlAccountName()!= null && !input.getGlAccountName().equals(""))
		{glDesc = "%"+input.getGlAccountName().trim().toUpperCase()+"%" ;}
		
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
//					jdbcTemplate.query(query , new Object[]{nature,desc,type,glCode,glDesc,status,
					jdbcTemplate.query(query , new Object[]{companyId,nature,desc,type,glDesc,status,
							input.getStDate(),input.getEnDate(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							
							input.getLength() + input.getStart(),input.getStart()+1
							//10+0,0+1
							},
				 new  BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class));
		
		//String query = env.getProperty("getExpenseTypesData1");
		
//		return (List<LtMastExpenseTypes>)
//				jdbcTemplate.query(query ,new  BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class));
	}
	

	@Override
		public LtMastExpenseTypes getByExpenseNature(String expenseNature,Long companyId) throws Exception
		{
		System.out.println("in getByExpenseNature");
			String query = env.getProperty("getLtMastExpenseTypesByExpenseNature");
			System.out.println("Below query getByExpenseNature");
			System.out.println("query = "+query);
			
			List<LtMastExpenseTypes> list=   jdbcTemplate.query(query, new Object[]{expenseNature.trim().toUpperCase(),companyId },
				 new BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class));
			System.out.println("list = "+list);
			if(list.size()>0)
			return list.get(0);
			else
				return null;
		}

	@Override
		public LtMastExpenseTypes getByGlCode(String glCode) throws Exception {
			String query = env.getProperty("getLtMastExpenseTypesByGlCode");
			
			List<LtMastExpenseTypes> list=   jdbcTemplate.query(query, new Object[]{glCode.trim().toUpperCase() },
				 new BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class));
			System.out.println("l=="+list);
			if(list.size()>0)
			return list.get(0);
			else
				return null;
		}
	
	@Override
	public boolean saveExpense(LtMastExpenseTypes ltMastExpenseTypes) throws Exception {
		//System.out.println("in saveExpense method...");
		/*GeneratedKeyHolder holder = new GeneratedKeyHolder();
	
		jdbcTemplate.update(new PreparedStatementCreator()
		{
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException
		    {
		    	
		        PreparedStatement statement = con.prepareStatement
		        (" INSERT INTO lt_mast_expense_types "
		       + " (Expense_Type_Id ,Expense_Nature,Expense_Description,Expense_Type,GL_Code,GL_Code_Desc,"
		       + " STATUS,CREATION_DATE,Start_Date, End_Date, Created_by, "
		       + " Last_update_login, Last_updated_by, Last_update_date ) "
		       + " VALUES(LT_MAST_EXPENSE_TYPES_S.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
		        //statement.setLong(0,0);
		        statement.setString(1, ltMastExpenseTypes.getExpenseNature());
		        statement.setString(2, ltMastExpenseTypes.getExpenseDescription());
		        statement.setString(3, ltMastExpenseTypes.getExpenseType());
		        statement.setString(4, ltMastExpenseTypes.getGlCode());
		        statement.setString(5, ltMastExpenseTypes.getGlCodeDesc());
		        statement.setString(6, ltMastExpenseTypes.getStatus());
		        statement.setTimestamp(7, new Timestamp(ltMastExpenseTypes.getCreationDate().getTime()));
		        statement.setTimestamp(8,  new Timestamp(ltMastExpenseTypes.getStartDate().getTime()));
		        if(ltMastExpenseTypes.getEndDate()!=null)
		        {
		        	statement.setTimestamp(9, new Timestamp(ltMastExpenseTypes.getEndDate().getTime()));
		        } else
		        {
		        	statement.setTimestamp(9,null);
		        }
		        statement.setLong(10, ltMastExpenseTypes.getCreatedBy());
		        statement.setLong(11, ltMastExpenseTypes.getLastUpdateLogin());
		        statement.setLong(12, ltMastExpenseTypes.getLastUpdatedBy());       
		        statement.setTimestamp(13, new Timestamp(ltMastExpenseTypes.getLastUpdateDate().getTime()));
		   
		        return statement;
		    }
		}, holder);
 
		Long primaryKey = holder.getKey().longValue();
		return primaryKey;
*/
		String query = env.getProperty("save");
		//System.out.println("query = "+query);
		int res=0;
		/*res=jdbcTemplate.update("INSERT INTO lt_mast_expense_types "
		       + " (Expense_Type_Id ,Expense_Nature,Expense_Description,Expense_Type,GL_Code,GL_Code_Desc,"
		       + " STATUS,CREATION_DATE,Start_Date, End_Date, Created_by, "
		       + " Last_update_login, Last_updated_by, Last_update_date ) "
		       + " VALUES(LT_MAST_EXPENSE_TYPES_S.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)",
		       ltMastExpenseTypes.getExpenseNature(),ltMastExpenseTypes.getExpenseDescription(),
		       ltMastExpenseTypes.getExpenseType(),ltMastExpenseTypes.getGlCode(),
		       ltMastExpenseTypes.getGlCodeDesc(),ltMastExpenseTypes.getStatus(),
		       ltMastExpenseTypes.getCreationDate(),ltMastExpenseTypes.getStartDate(),
		       ltMastExpenseTypes.getEndDate(),ltMastExpenseTypes.getCreatedBy(),
		       ltMastExpenseTypes.getLastUpdateLogin(),ltMastExpenseTypes.getLastUpdatedBy(),
		       ltMastExpenseTypes.getLastUpdateDate());*/
		
		/*res=jdbcTemplate.update(query,
	       ltMastExpenseTypes.getExpenseNature(),ltMastExpenseTypes.getExpenseDescription(),
	       ltMastExpenseTypes.getExpenseType(),ltMastExpenseTypes.getGlCode(),
	       ltMastExpenseTypes.getGlCodeDesc(),ltMastExpenseTypes.getStatus(),
	       ltMastExpenseTypes.getCreationDate(),ltMastExpenseTypes.getStartDate(),
	       ltMastExpenseTypes.getEndDate(),ltMastExpenseTypes.getCreatedBy(),
	       ltMastExpenseTypes.getLastUpdateLogin(),ltMastExpenseTypes.getLastUpdatedBy(),
	       ltMastExpenseTypes.getLastUpdateDate());
		*/
		//System.out.println("Above ltMastExpenseType");
		//System.out.println("ltMastExpenseTypes = "+ltMastExpenseTypes);
		LtMastExpenseTypes ltMastExpenseType1 = ltMastExpenseTypeRepository.save(ltMastExpenseTypes);
		//System.out.println("Below ltMastExpenseType");
		if(ltMastExpenseType1.getExpenseTypeId() != null)
				return true;
		else
			return false;
		
	}

	@Override
	public LtMastExpenseTypes getExpenseTypeById(Long expenseTypeId)
			throws Exception {
		String query = env.getProperty("getExpenseTypeById");			
			List<LtMastExpenseTypes> list=   jdbcTemplate.query(query, new Object[]{ expenseTypeId },
					 new BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class));
			 if(!list.isEmpty())
				 return list.get(0);
			 else
				 return null;
			}
	
	@Override
	public boolean deleteExpense(Long expenseTypeId) throws Exception {
		String query = env.getProperty("delete");
		
		int res=jdbcTemplate.update(query,expenseTypeId);
 
		if(res==1)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean updateExpense(LtMastExpenseTypes ltMastExpenseTypes)
			throws Exception {
		String query = env.getProperty("update");
		
		int res=jdbcTemplate.update(query,
			ltMastExpenseTypes.getExpenseNature(),ltMastExpenseTypes.getExpenseDescription(),ltMastExpenseTypes.getExpenseType(),
			ltMastExpenseTypes.getStatus(),ltMastExpenseTypes.getCreationDate(),ltMastExpenseTypes.getStartDate(),ltMastExpenseTypes.getEndDate(),
			ltMastExpenseTypes.getCreatedBy(),ltMastExpenseTypes.getLastUpdateLogin(),ltMastExpenseTypes.getLastUpdatedBy(),ltMastExpenseTypes.getLastUpdateDate(),
			ltMastExpenseTypes.getGlAccountId(),ltMastExpenseTypes.getExpenseTypeId());
		if(res==1)
			return true;
		else
			return false;
	}
	
	@Override
	public List<LtMastExpenseTypes> getExpenseTypesLikeName(String name,Long companyId) throws ServiceException {
		String query = env.getProperty("getLtMastExpenseTypesLikeName");
		List<LtMastExpenseTypes> list=   jdbcTemplate.query(query, new Object[]{ "%"+name.toUpperCase()+"%", 
				"%"+name.toUpperCase()+"%",companyId}, 
		 new BeanPropertyRowMapper<LtMastExpenseTypes>(LtMastExpenseTypes.class)); 
		return list;
	}
	
}
