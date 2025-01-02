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

import com.lonar.vendor.vendorportal.model.Customer;
import com.lonar.vendor.vendorportal.model.Employee;
import com.lonar.vendor.vendorportal.model.LtMastCustomersDto;
import com.lonar.vendor.vendorportal.model.LtMastDepartments;
import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ProjectWithTaskValues;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Component
@PropertySource(value = "classpath:branchMasterQueries.properties", ignoreResourceNotFound = true)
public class LtMastProjectDaoImpl implements LtMastProjectDao{
	
	
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
	public List<LtMastProjects> listAllActiveLtMastProjectDao() throws Exception {
		String query = env.getProperty("listAllActiveLtMastProjectDao");
		List<LtMastProjects> list=   jdbcTemplate.query(query, new Object[]{ },
					 new BeanPropertyRowMapper<LtMastProjects>(LtMastProjects.class));
		
		return list;
	}
	
	@Override
	public Long getCount(LtMastProjects input, long companyId) throws Exception {
		String query = env.getProperty("getLtMastProjectsCount");
		
		String pNumber=null;
		if(input.getProjectNumber()!=null)
		{pNumber="%"+input.getProjectNumber().toUpperCase()+"%";}
 
		String pName=null;
		if(input.getProjectName()!=null)
		{pName="%"+input.getProjectName().toUpperCase()+"%";}
		   
		String desc=null;       
		if(input.getProjectDesc()!=null)
		{desc="%"+input.getProjectDesc()+"%";}
		
		String status=null;
		if(input.getStatus()!=null)
		{status="%"+input.getStatus().toUpperCase()+"%";}
	   
	
		if(input.getStDate() == null || input.getStDate().trim().equals(""))
		{input.setStDate(null);}
 
		if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		{input.setEnDate(null);}
		  
		  
		   String count  = (String)getJdbcTemplate().queryForObject(
					query, new Object[] {companyId,pNumber,pName,desc,status,input.getStDate(),input.getEnDate(),},
					String.class);
			return Long.parseLong(count);
	}
	
	@Override
	public List<LtMastProjects> getLtMastProjectsDataTable(LtMastProjects input, long companyId) throws Exception {
		System.out.println(input.getStart()+" "+input.getLength()+" "+input.getStart()+1);

		String query = env.getProperty("getLtMastProjectsDataTable");
		
		String pNumber=null;
		if(input.getProjectNumber()!=null)
		{pNumber="%"+input.getProjectNumber().trim().toUpperCase()+"%";}
 
		String pName=null;
		if(input.getProjectName()!=null)
		{pName="%"+input.getProjectName().trim().toUpperCase()+"%";}
		   
		String desc=null;       
		if(input.getProjectDesc()!=null)
		{desc="%"+input.getProjectDesc().trim().toUpperCase()+"%";}
		
		String status=null;
		if(input.getStatus()!=null)
		{status="%"+input.getStatus().trim().toUpperCase()+"%";}
	   
	
		if(input.getStDate() == null || input.getStDate().trim().equals(""))
		{input.setStDate(null);}
 
		if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		{input.setEnDate(null);}
 
		if(input.getColumnNo()==0)
		{
			input.setColumnNo(8);
		}
		System.out.println(input.getStart()+" "+input.getLength()+" "+input.getStart()+1);
		List<LtMastProjects> list=   jdbcTemplate.query(query, new Object[]{ companyId,pNumber,pName,desc,
				status,input.getStDate(),input.getEnDate(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getColumnNo(),input.getColumnNo(),
				input.getStart()+input.getLength(),input.getStart()+1},
					 new BeanPropertyRowMapper<LtMastProjects>(LtMastProjects.class));
		
		return list;
	}
	
	
	@Override
	public List<LtMastProjects> isFeildsExists(LtMastProjects ltMastProjects)
				throws Exception {
			String query = env.getProperty("isFeildsExistsProj");
			return jdbcTemplate.query(query, new Object[] {ltMastProjects.getProjectNumber(),ltMastProjects.getProjectName() },
	 
					new RowMapper<LtMastProjects>() {
						@Override
						public LtMastProjects mapRow(ResultSet rs, int row) throws SQLException {
							LtMastProjects projectfields = new LtMastProjects();
							projectfields.setProjectNumber(rs.getString("PROJECT_NUMBER"));
							projectfields.setProjectId(rs.getLong("PROJECT_ID"));
							projectfields.setProjectName(rs.getString("PROJECT_NAME"));
							return projectfields;
						}
	 
					}
	 
			);
		
		}

	
	@Override
	public List<Employee> getAllEmployee() {
		String query = env.getProperty("getAllEmployee");
		List<Employee> list=   jdbcTemplate.query(query, new Object[]{ },
					 new BeanPropertyRowMapper<Employee>(Employee.class));
		
		return list;
	}

	@Override
	public List<Customer> getAllCustomer() {
		String query = env.getProperty("getAllCustomer");
		List<Customer> list =   jdbcTemplate.query(query, new Object[]{ },
					 new BeanPropertyRowMapper<Customer>(Customer.class));
		
		return list;
	}
	
	@Override
	public List<LtMastCustomersDto> getCustomersLikeName(String name, Long companyId) throws ServiceException {
		String query = env.getProperty("getLtMastCustomersLikeName");
		List<LtMastCustomersDto> list=   jdbcTemplate.query(query, new Object[]{ "%"+name.toUpperCase()+"%", 
				"%"+name.toUpperCase()+"%",companyId}, 
		 new BeanPropertyRowMapper<LtMastCustomersDto>(LtMastCustomersDto.class)); 
		return list;
	}
	
	@Override
	public List<LtMastProjects> getProjectsLikeName(String name,long companyId) throws ServiceException {
		String query = env.getProperty("getLtMastProjectsLikeName");
		List<LtMastProjects> list=   jdbcTemplate.query(query, new Object[]{ "%"+name.toUpperCase()+"%", 
				"%"+name.toUpperCase()+"%",companyId}, 
		 new BeanPropertyRowMapper<LtMastProjects>(LtMastProjects.class)); 
		return list;
	}
	
	@Override
	@Transactional
	public ProjectWithTaskValues getById(Long id) throws Exception
	{
		ProjectWithTaskValues projectWithTaskValues=new ProjectWithTaskValues();
		
		String query = env.getProperty("getByIdProject");
		List<LtMastProjects> list=   jdbcTemplate.query(query, new Object[]{id},
					 new BeanPropertyRowMapper<LtMastProjects>(LtMastProjects.class));
		if(list.size()>0)
		{
			projectWithTaskValues.setLtMastProjects(list.get(0));
		
			String query1 = env.getProperty("getByIdTask");
			List<LtMastProjectTasks> valueList=   jdbcTemplate.query(query1, new Object[]{id},
					 new BeanPropertyRowMapper<LtMastProjectTasks>(LtMastProjectTasks.class));
		
			projectWithTaskValues.setLtMastProjectTaskValues(valueList);
		}
			
		return projectWithTaskValues;
	}
	
	@Override
	public boolean delete(Long projectId) throws Exception {
			int res=0,res1=0;
			 res=jdbcTemplate.update(" DELETE FROM LT_MAST_PROJECTS "
					+ " WHERE PROJECT_ID = "+projectId+" ");
			 
			 res1=jdbcTemplate.update(" DELETE FROM LT_MAST_PROJECT_TASKS "
						+ " WHERE PROJECT_ID = "+projectId+" ");
		
			if(res!=0)
				return true;
			else
				return false;
		}
	
	@Override
	public boolean deleteTask(Long taskId,Long projectId) throws Exception {
			int res=0;
			 
			 res=jdbcTemplate.update(" DELETE FROM LT_MAST_PROJECT_TASKS "
						+ " WHERE TASK_ID = "+taskId+" AND PROJECT_ID = "+projectId+" ");
		
			if(res!=0)
				return true;
			else
				return false;
		}

}
