package com.lonar.UserManagement.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.UserManagement.common.BusinessException;
import com.lonar.UserManagement.web.model.CodeMaster;
import com.lonar.UserManagement.web.model.LtMastAuditRecords;
import com.lonar.UserManagement.web.model.LtMastRoles;
import com.lonar.UserManagement.web.model.LtMastSysVariableValues;
import com.lonar.UserManagement.web.model.LtMastSysVariables;
import com.lonar.UserManagement.web.model.LtMastUserRoles;
import com.lonar.UserManagement.web.model.LtMastUsers;
import com.lonar.UserManagement.web.model.Menu;
import com.lonar.UserManagement.web.model.SysVariableWithValues;
import com.lonar.UserManagement.web.repository.LtMastUsersRepository;


@Repository
@PropertySource(value = "classpath:queries/userMasterQueries.properties", ignoreResourceNotFound = true)
public class LtMastUsersDaoImpl implements LtMastUsersDao, CodeMaster
{
	@Autowired
	private LtMastUsersRepository ltMastUsersRepository;
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Environment env;
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<LtMastUsers> findByAdId(String adId) {
		String query =  "SELECT * FROM LT_MAST_USERS WHERE UPPER(AD_ID) = ? ";
		return  jdbcTemplate.query(query, new Object[]{ adId.toUpperCase() }, 
				new BeanPropertyRowMapper(LtMastUsers.class));
	}
	

	
	@Override
	public List<LtMastUsers> findByUserName(String userName) {
		String sqlQuery = env.getProperty("findByUserName");
		
			List<LtMastUsers> list=  jdbcTemplate.query(sqlQuery, new Object[]{userName.toLowerCase(), userName.toUpperCase()}, 
			 new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class)); 
			return list;
		
	}
	
	
	@Override
	public Long getLtMastUsersCount(LtMastUsers input,Long companyId)
	{
		String query = env.getProperty("getLtMastUsersCount");
		
		String uname = null;
		 if(input.getUserName()!=null)
		   { uname="%"+input.getUserName().trim().toUpperCase()+"%";}
		 
		 if(input.getStDate() == null || input.getStDate().trim().equals(""))
		   {input.setStDate(null);}
		   
		 if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
		   {input.setEnDate(null);}
		   
		 String status=null;
		   if(input.getStatus()!=null) 
		   {status="%"+input.getStatus().trim().toUpperCase()+"%";}
		   
		   String email = null;
		   if(input.getEmail()!=null)
		   { email="%"+input.getEmail().trim().toUpperCase()+"%";}
		   			
		  
		   Long count  = getJdbcTemplate().queryForObject(
					query, new Object[] {companyId,uname,input.getStDate(),input.getEnDate(),status,email}, Long.class);
		
			return count;
	}

	@Override
	public List<LtMastUsers> getLtMastUsersDatatableRecords(LtMastUsers input,Long companyId) {
		String query = env.getProperty("getLtMastUsersDataTableRecords");
		
		
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{
			input.setColumnNo(16);
		}
		if(input.getColumnNo()==8 && input.getSort().equals("asc"))
		{
			input.setColumnNo(18);
		}
		
		String uname = null;
		 if(input.getUserName()!=null && !input.getUserName().equals("")){ 
			 uname="%"+input.getUserName().trim().toUpperCase()+"%";
		 }
		 if(input.getStDate() == null || input.getStDate().trim().equals("")) {
		    input.setStDate(null);
		 }
		 if(input.getEnDate() == null || input.getEnDate().trim().equals("")){ 
			 input.setEnDate(null);
		 }
		 String status=null;
		 if(input.getStatus()!=null && !input.getStatus().equals("")){ 
		   status="%"+input.getStatus().trim().toUpperCase()+"%";
		 }
		 String email = null;
		 if(input.getEmail()!=null && !input.getEmail().equals("")) {
		    email="%"+input.getEmail().trim().toUpperCase()+"%";
		 }  
		 if(input.getColumnNo()==0) {
			   input.setColumnNo(8);
		 }
	
		List<LtMastUsers> list = (List<LtMastUsers>) 
				jdbcTemplate.query(query , new Object[]{companyId,uname,input.getStDate(),input.getEnDate(),
						   status,email,
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							(input.getStart()+input.getLength()),(input.getStart()+1)},
			 new  BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class));
			return list;
		
	}
	@Override
	  public List<LtMastUsers> findByActiveLikeUserName(String userName,long companyId){
			String sqlQuery = env.getProperty("findByLikeActiveUserName");
			List<LtMastUsers> list=   jdbcTemplate.query(sqlQuery, new Object[]{ "%"+userName.toUpperCase()+"%",companyId }, 
					 new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class)); 
			System.out.println(list.toString());
			return list;
			
		}
	
	@Override
	  public List<LtMastUsers> findByActiveLikeUserName1(String userName){
			String sqlQuery = env.getProperty("findByLikeActiveUserName1");
			List<LtMastUsers> list=   jdbcTemplate.query(sqlQuery, new Object[]{ "%"+userName.toUpperCase()+"%"}, 
					 new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class)); 
			System.out.println(list.toString());
			return list;
			
		}

	  @Override
	  public LtMastUsers getLtMastUsersByID(Long id) {
		  String sqlQuery = env.getProperty("get_user_details_by_id");
		  LtMastUsers user =  jdbcTemplate.queryForObject(sqlQuery, new Object[]{ id}, 
					 new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class)); 
			return user;
	  }

	@Override
	public List<LtMastRoles> getActiveRoles() {
		String sqlQuery = env.getProperty("get_active_role");
		List<LtMastRoles> roles =  jdbcTemplate.query(sqlQuery, new Object[]{ STATUS_ACTIVE }, 
					 new BeanPropertyRowMapper<LtMastRoles>(LtMastRoles.class)); 
		return roles;
	}

	@Override
	public Long getUserRoleCount(LtMastUserRoles input) {
		input.getStartDate();
		
		String  roleName = null;
		if(input.getRoleName() !=  null && !input.getRoleName().trim().equals("")) {
			roleName = "%"+input.getRoleName()+"%";
		}
		
		String query = env.getProperty("user_role_count");
		Long count  = getJdbcTemplate().queryForObject(query, new Object[] { input.getUserId() , roleName ,input.getStartDate(), input.getStrEndDate() }, Long.class);
		
		return count;
	}

	@Override
	public List<LtMastUserRoles> getUserRoleList(LtMastUserRoles input) {
		input.getStartDate();
		
		String  roleName = null;
		if(input.getRoleName() !=  null && !input.getRoleName().trim().equals("")) {
			roleName = "%"+input.getRoleName()+"%";
		}
		
		String query = env.getProperty("user_role_details_list");
		List<LtMastUserRoles> userRoles =  jdbcTemplate.query(query, new Object[]{ 
				 input.getUserId(), roleName, input.getStartDate(), input.getStrEndDate(), (input.getStart()+input.getLength()),(input.getStart()+1)  }, 
				 new BeanPropertyRowMapper<LtMastUserRoles>(LtMastUserRoles.class)); 
		return userRoles;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<Menu> findMenu(Long userId,Long companyId) {
		 
		 String sqlQuery = env.getProperty("findMenu");
		 
		 System.out.println(sqlQuery);
		
		   return jdbcTemplate.query(sqlQuery,new Object[]{userId,companyId}, new RowMapper<Menu>(){
			@Override
			public Menu mapRow(ResultSet rs, int row)
					throws SQLException {
				System.out.println(rs.getFetchSize());
					Menu menu=new Menu();
					menu.setModuleId(rs.getLong("MODULE_ID"));
					menu.setModuleName(rs.getString("MODULE_NAME"));
					menu.setModuleCode(rs.getString("MODULE_CODE"));
					menu.setModuleUrl(rs.getString("MODULE_URL"));
					menu.setModuleGroup(rs.getString("MODULE_GROUP"));
					menu.setCreate(rs.getString("CREATE_FLAG"));
					menu.setRead(rs.getString("READ_FLAG"));
					menu.setEdit(rs.getString("EDIT_FLAG"));
					menu.setDelete(rs.getString("DELETE_FLAG"));
					menu.setUpdate(rs.getString("UPDATE_FLAG"));
					return menu;
				}
				
		   });
		   
	}

	@Override
	public List<LtMastUsers> findVendorByUserName(String userName) {
		
		//String query = env.getProperty("getAllLtMastCompany");
//		String query = " SELECT mu.*, nvl(em.DIVISION_ID,vm.DIVISION_ID) AS division_Id, d.DIVISION_NAME||' ( '||d.DIVISION_CODE||' )' as DIVISION_NAME, " + 
//				"				 vm.VENDOR_NAME||' ( '||vm.VENDOR_CODE||' )'  as employee_name,vm.COMPANY_ID ,cm.COMPANY_NAME  " + 
//				"				 FROM LT_MAST_USERS mu,LT_MAST_VENDORS vm, LT_MAST_EMPLOYEES em , LT_MAST_DIVISIONS d,LT_VEND_COMPANY_MASTER cm " + 
//				"				 WHERE upper(USER_NAME) = ?  " + 
//				"				 AND mu.VENDOR_ID = vm.VENDOR_ID(+) AND mu.COMPANY_ID = cm.COMPANY_ID(+) " + 
//				"                 AND vm.INITIATOR_ID = em.EMPLOYEE_ID(+)  AND vm.DIVISION_ID = d.DIVISION_ID(+)  ";
		
		String query = "SELECT mu.*, " + 
	               "COALESCE(em.DIVISION_ID, vm.DIVISION_ID) AS division_Id, " + 
	               "CONCAT(d.DIVISION_NAME, ' (', d.DIVISION_CODE, ')') AS DIVISION_NAME, " + 
	               "CONCAT(vm.VENDOR_NAME, ' (', vm.VENDOR_CODE, ')') AS employee_name, " + 
	               "vm.COMPANY_ID, cm.COMPANY_NAME " + 
	               "FROM LT_MAST_USERS mu " + 
	               "LEFT JOIN LT_MAST_VENDORS vm ON mu.VENDOR_ID = vm.VENDOR_ID " + 
	               "LEFT JOIN LT_MAST_EMPLOYEES em ON vm.INITIATOR_ID = em.EMPLOYEE_ID " + 
	               "LEFT JOIN LT_MAST_DIVISIONS d ON vm.DIVISION_ID = d.DIVISION_ID " + 
	               "LEFT JOIN LT_VEND_COMPANY_MASTER cm ON mu.COMPANY_ID = cm.COMPANY_ID " + 
	               "WHERE UPPER(mu.USER_NAME) = ?";

		
		List<LtMastUsers> list = (List<LtMastUsers>) 
				jdbcTemplate.query(query , new Object[]{userName.toUpperCase() },
			 new  BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class));
		return list;
	}

	@Override
	public List<LtMastUsers> findActiveLikeEmail(String email,long companyId) throws Exception {
		String sqlQuery = env.getProperty("findActiveLikeEmail");
		 
			List<LtMastUsers> list=  jdbcTemplate.query(sqlQuery, new Object[]{ email.toUpperCase(),companyId}, 
			 new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class)); 
			return list;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public LtMastUsers getForAuditByID(Long userId) throws BusinessException {
		String query = env.getProperty("getUserForAuditByID");
		
		List<LtMastUsers> list= new ArrayList<LtMastUsers>();
		list = jdbcTemplate.query(query, new Object[] { userId },
				new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class));
		if(!list.isEmpty()) {
			return list.get(0);
		}
			 else return null;
	}

	@Override
	public void updateAuditId(float auditId, Long userId) throws BusinessException {
		int res=0;
		res=jdbcTemplate.update(" UPDATE LT_MAST_USERS SET AUDIT_ID = ?  WHERE USER_ID = ? ",auditId,userId);
		
		
	}

	@Override
	public List<LtMastAuditRecords> getLtMastUserAudit(Long userId) throws BusinessException {
		String query = env.getProperty("getLtMastUserAudit");
		
		List<LtMastAuditRecords> list=   jdbcTemplate.query(query, new Object[]{userId }, 
				 new BeanPropertyRowMapper<LtMastAuditRecords>(LtMastAuditRecords.class));
		return list;
	}

	@Override
	public LtMastUsers getForAuditByID1(Long userId) throws BusinessException {
		String query = env.getProperty("getLtMastUsersForAuditByID");
		
		List<LtMastUsers> list=jdbcTemplate.query(query, new Object[] { userId },
				new BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class));
		if(!list.isEmpty())
			return list.get(0);
			 else return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public LtMastUsers update(LtMastUsers user) throws BusinessException {
		// TODO Auto-generated method stub
		return ltMastUsersRepository.save(user);
	}

	@Override
	public Long getLtMastUsersUtilityCount(LtMastUsers input) throws BusinessException {
		
		String query = env.getProperty("getLtMastUsersUtilityCount");
		
		String uname = null;
		 if(input.getUserName()!=null && !input.getUserName().equals("")){ 
			 uname="%"+input.getUserName().trim().toUpperCase()+"%";
		 }
		 String email = null;
		 if(input.getEmail()!=null && !input.getEmail().equals("")) {
		    email="%"+input.getEmail().trim().toUpperCase()+"%";
		 } 
		 String role = null;
		 if(input.getRoleName()!=null && !input.getRoleName().equals("")) {
			 role="%"+input.getRoleName().trim().toUpperCase()+"%";
		 } 
		 if(input.getStDate() == null || input.getStDate().trim().equals("")) {
		    input.setStDate(null);
		 }
		 if(input.getEnDate() == null || input.getEnDate().trim().equals("")){ 
			 input.setEnDate(null);
		 }
		 String status=null;
		 if(input.getStatus()!=null && !input.getStatus().equals("")){ 
		   status="%"+input.getStatus().trim().toUpperCase()+"%";
		 }
		 
		 Long count  = getJdbcTemplate().queryForObject(
					query, new Object[] {uname,email,status,input.getStDate(),input.getEnDate(),
							   role}, Long.class);
		
			return count;
	}

	@Override
	public List<LtMastUsers> getLtMastUsersUtilityDataTable(LtMastUsers input) throws BusinessException {
		String query = env.getProperty("getLtMastUsersUtilityDataTable");
		
		
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("asc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{
			input.setColumnNo(16);
		}
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{
			input.setColumnNo(17);
		}
		if(input.getColumnNo()==8 && input.getSort().equals("asc"))
		{
			input.setColumnNo(18);
		}
		
		String uname = null;
		 if(input.getUserName()!=null && !input.getUserName().equals("")){ 
			 uname="%"+input.getUserName().trim().toUpperCase()+"%";
		 }
		 String email = null;
		 if(input.getEmail()!=null && !input.getEmail().equals("")) {
		    email="%"+input.getEmail().trim().toUpperCase()+"%";
		 } 
		 String role = null;
		 if(input.getRoleName()!=null && !input.getRoleName().equals("")) {
			 role="%"+input.getRoleName().trim().toUpperCase()+"%";
		 } 
		 if(input.getStDate() == null || input.getStDate().trim().equals("")) {
		    input.setStDate(null);
		 }
		 if(input.getEnDate() == null || input.getEnDate().trim().equals("")){ 
			 input.setEnDate(null);
		 }
		 String status=null;
		 if(input.getStatus()!=null && !input.getStatus().equals("")){ 
		   status="%"+input.getStatus().trim().toUpperCase()+"%";
		 }
		 
		 if(input.getColumnNo()==0) {
			   input.setColumnNo(5);
		 }
	
		List<LtMastUsers> list = (List<LtMastUsers>) 
				jdbcTemplate.query(query , new Object[]{uname,email,status,input.getStDate(),input.getEnDate(),role,
						   
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							(input.getStart()+input.getLength()),(input.getStart()+1)},
			 new  BeanPropertyRowMapper<LtMastUsers>(LtMastUsers.class));
			return list;
		
	}

	@Override
	public String paginationEntries(String sysVar, Long userId, Long companyId) {
		String sql = "SELECT get_sys_variable_value(?, ?, ?)";  // MySQL function call

        // Call the function and get the result as an Integer
        return jdbcTemplate.queryForObject(sql, new Object[]{sysVar, userId, companyId}, String.class);
	}

	@Override
	public List<Menu> getModulesByUserId(Long userId, Long companyId,String moduleType,String searchTerm) {
		  String query = env.getProperty("findMenuQuery");
		    
		  return jdbcTemplate.query(query,new Object[]{userId,companyId,moduleType,searchTerm}, new RowMapper<Menu>(){
				@Override
				public Menu mapRow(ResultSet rs, int row)
						throws SQLException {
					System.out.println(rs.getFetchSize());
						Menu menu=new Menu();
						menu.setModuleId(rs.getLong("MODULE_ID"));
						menu.setModuleName(rs.getString("MODULE_NAME"));
						menu.setModuleCode(rs.getString("MODULE_CODE"));
						menu.setModuleUrl(rs.getString("MODULE_URL"));
						menu.setModuleGroup(rs.getString("MODULE_GROUP"));
						menu.setCreate(rs.getString("CREATE_FLAG"));
						menu.setRead(rs.getString("READ_FLAG"));
						menu.setEdit(rs.getString("EDIT_FLAG"));
						menu.setDelete(rs.getString("DELETE_FLAG"));
						menu.setUpdate(rs.getString("UPDATE_FLAG"));
						return menu;
					}
					
			   });
			   
		}
}
	
	
	  
	
 

