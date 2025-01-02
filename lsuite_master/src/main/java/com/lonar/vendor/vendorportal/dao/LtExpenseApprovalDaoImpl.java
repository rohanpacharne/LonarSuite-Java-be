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

import com.lonar.vendor.vendorportal.model.Approval;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpModuleApprovals;
import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtMastEmployeeDelegation;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;

@Component
@PropertySource(value = "classpath:branchMasterQueries.properties", ignoreResourceNotFound = true)
public  class LtExpenseApprovalDaoImpl  implements LtExpenseApprovalDao{
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private LtMastEmployeeDelegationDao ltMastEmployeeDelegationDao;
 
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
 
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

//	@Override
//	public boolean loadApprovers(LtExpExpenseHeaders ltExpExpenseHeaders) throws Exception
//	{
//		String query = env.getProperty("loadApprovers");
//	
//		List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]
//				{ltExpExpenseHeaders.getDivisionId(),ltExpExpenseHeaders.getExpenseCategory(),ltExpExpenseHeaders.getStatus() },
//			 new BeanPropertyRowMapper<Approval>(Approval.class));
//	
//		List<LtMastEmployees>  empList=ltMastEmployeesDao.getByEmpId(ltExpExpenseHeaders.getEmployeeId());
//		if(empList!=null)
//		{
//			Approval superviserApproval = new Approval();
//			superviserApproval.setEmployeesId(empList.get(0).getSupervisorEmpId());
//			superviserApproval.setApprovalLevel("00");
//			superviserApproval.setModuleApprovalId(00L);
//		
//			approvalList.add(superviserApproval);
//		}
//		
//		boolean flag=false;
//	if(approvalList.size()>0)
//	{
//		for(Approval approvalObj:approvalList)
//		{
//			Approval approval=approvalObj;
//			System.out.println(approvalObj.getEmployeesId());
//			List<LtMastEmployeeDelegation> ltMastEmployeeDelegation = ltMastEmployeeDelegationDao
//					.findForDelegation(approvalObj.getEmployeesId());
//			if(ltMastEmployeeDelegation!= null && ltMastEmployeeDelegation.size()>0)
//			{
//				approval.setDelegationId(ltMastEmployeeDelegation.get(0).getDelegationId());
//			}
//			
//			
//			
//			int res=0;
//			if(approval.getEmployeesId()!=null && approval.getModuleApprovalId()!=null && approval.getApprovalLevel()!=null)
//			{
//				String query1 = env.getProperty("loadApproversInsert");
//				
//				res=jdbcTemplate.update(" INSERT INTO lt_expense_approval "
//						+ " ( EXPENSE_APPROVAL_ID,MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
//						+ " EXPENSE_HEADER_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
//						+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
//		 		+ " VALUES(lt_expense_approval_s.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
//		 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
//		 		null,approval.getDelegationId(),ltExpExpenseHeaders.getExpHeaderId(),NO_ACTION,ltExpExpenseHeaders.getStartDate(),
//		 		ltExpExpenseHeaders.getEndDate(),ltExpExpenseHeaders.getCreatedBy(),ltExpExpenseHeaders.getCreationDate(),
//		 		ltExpExpenseHeaders.getLastUpdateLogin(),ltExpExpenseHeaders.getLastUpdatedBy(),
//		 		ltExpExpenseHeaders.getLastUpdateDate(),approval.getModuleAppEmployeesId());
//				
//				
//				/*res=jdbcTemplate.update(query1,
//		 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
//		 		null,null,ltExpExpenseHeaders.getExpHeaderId(),NO_ACTION,ltExpExpenseHeaders.getStartDate(),
//		 		ltExpExpenseHeaders.getEndDate(),ltExpExpenseHeaders.getCreatedBy(),ltExpExpenseHeaders.getCreationDate(),
//		 		ltExpExpenseHeaders.getLastUpdateLogin(),ltExpExpenseHeaders.getLastUpdatedBy(),
//		 		ltExpExpenseHeaders.getLastUpdateDate(),approval.getModuleAppEmployeesId());*/
//				
//				if(res!=0)
//					flag=true;
//				
//			
//			}
//			
//		}
//	}
//	
//	return flag;
//	
// 
//	}
	
	@Override
	public List<LtExpenseApproval> getExpenseApprovalByExpHeaderId(Long expenseHeaderId) throws Exception {
		
		String query = env.getProperty("getExpenseApprovalByExpHeaderId");
		
		List<LtExpenseApproval> ltExpenseApprovalList = jdbcTemplate.query(query, new Object[] { expenseHeaderId },
				new RowMapper<LtExpenseApproval>() {
					public LtExpenseApproval mapRow(ResultSet rs, int arg1) throws SQLException {
 
						LtExpenseApproval ltExpenseApproval = new LtExpenseApproval();
						
						ltExpenseApproval.setExpApprovalId(rs.getLong("EXP_APPROVAL_ID"));
						ltExpenseApproval.setDelegationId(rs.getLong("DELEGATION_ID"));
						ltExpenseApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltExpenseApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltExpenseApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltExpenseApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						ltExpenseApproval.setExpHeaderId(rs.getLong("EXP_HEADER_ID"));
						ltExpenseApproval.setStatus(rs.getString("STATUS"));
						ltExpenseApproval.setStartDate(rs.getDate("START_DATE"));
						ltExpenseApproval.setEndDate(rs.getDate("END_DATE"));
						ltExpenseApproval.setCreatedBy(rs.getLong("CREATED_BY"));
						ltExpenseApproval.setCreationDate(rs.getDate("CREATION_DATE"));
						ltExpenseApproval.setLastUpdateDate(rs.getDate("LAST_UPDATE_DATE"));
						ltExpenseApproval.setLastUpdateLogin(rs.getLong("LAST_UPDATE_LOGIN"));
						ltExpenseApproval.setLastUpdatedBy(rs.getLong("LAST_UPDATED_BY"));
						ltExpenseApproval.setModuleAppEmployeesId(rs.getLong("MODULE_APP_EMPLOYEES_ID"));
						ltExpenseApproval.setApprovalName(rs.getString("APPROVAL_NAME"));
						ltExpenseApproval.setApprovalLevelName(rs.getString("APPROVAL_LEVEL_NAME"));
						return ltExpenseApproval;
 
					}
				});
		return ltExpenseApprovalList;
 
	}
	
	@Override
	public String checkforApprovals(Long expHeaderId) throws Exception
	{
		String query = env.getProperty("checkforApprovals");
		
		List<LtExpModuleApprovals> ltExpModuleApprovalsList = jdbcTemplate.query(query, new Object[]
				{expHeaderId},
				new RowMapper<LtExpModuleApprovals>() {
					public LtExpModuleApprovals mapRow(ResultSet rs, int arg1) throws SQLException {
 
						LtExpModuleApprovals ltExpModuleApprovals = new LtExpModuleApprovals();
						ltExpModuleApprovals.setApprovalRoleName(rs.getString("APPROVAL_ROLE_NAME"));
						
						return ltExpModuleApprovals;
					}
				});
	if(ltExpModuleApprovalsList.size()>0)
		return ltExpModuleApprovalsList.get(0).getApprovalRoleName();
	else
		return null;
		/* String level  = (String)getJdbcTemplate().queryForObject(
					query, new Object[] {expHeaderId}, String.class);
			return level;*/
	}
	
	@Override
	public boolean submitForApproval(Date submissionDate,Long expHeaderId,String status,Date approvedDate) throws Exception
	{	
		int res=0;
		if(submissionDate!=null)
		{
			String query = env.getProperty("submitForApproval1");
			res=jdbcTemplate.update(query,submissionDate,status,approvedDate,new Date(),expHeaderId);
			
		}
		else
		{
			String query = env.getProperty("submitExpenseForApproval2");
			 res=jdbcTemplate.update(query,status,approvedDate,new Date(),expHeaderId);
			 System.out.println("res "+res);
		}
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public List<LtExpenseApproval> chkEmpApproval(Long empId, Long expHeaderId) 
	{
		String query = env.getProperty("chkLtExpenseApprovalEmpApproval");

		List<LtExpenseApproval> ltExpenseApprovalList = jdbcTemplate.query(query, new Object[]
				{empId,expHeaderId,APPROVED},
				new RowMapper<LtExpenseApproval>() {
					public LtExpenseApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtExpenseApproval ltExpenseApproval = new LtExpenseApproval();
						
						ltExpenseApproval.setExpApprovalId(rs.getLong("EXP_APPROVAL_ID"));
						
						return ltExpenseApproval;
					}
				});
	
		return ltExpenseApprovalList;
	}
	
	@Override
	public boolean updateStatusApproval(LtExpenseApprovalHistory approvalHistory) throws Exception  
	{
		int res=0;	
		if(approvalHistory.getEmployeeId()!=null && !approvalHistory.getStatus().equals(SELF_REJECTED)) 
		{
			String query = env.getProperty("updateStatusApproval");
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getExpenseApprovalId(),
					approvalHistory.getExpenseHeaderId(), approvalHistory.getEmployeeId(),approvalHistory.getEmployeeId());
		}
		else{
			approvalHistory.setStatus(REJECTED);
			String query = env.getProperty("updateStatusApprovalSelfReject");
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getEmployeeId(),
					approvalHistory.getExpenseHeaderId());
		}
			
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean updateAllStatusApproval(LtExpenseApprovalHistory approvalHistory) throws Exception
	{
		String query = env.getProperty("updateAllStatusApproval");
		int res=0;	
			res = jdbcTemplate.update(query,
						approvalHistory.getStatus(),new Date(),approvalHistory.getExpenseApprovalId(),
						approvalHistory.getExpenseHeaderId());
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public String getCurrLevelByexpenseApprovalId(Long expenseApprovalId) throws Exception 
	{
		String query = env.getProperty("getCurrLevelByexpenseApprovalId");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { expenseApprovalId}, String.class);

		return nextlavel;
	}
	
	@Override
	public List<LtExpenseApproval> chkCurrentApprover(Long expHeaderId) throws Exception {
		String query = env.getProperty("chkCurrentApprover");

		List<LtExpenseApproval> ltExpenseApprovalList = jdbcTemplate.query(query, new Object[] {expHeaderId},
				new RowMapper<LtExpenseApproval>() {
					public LtExpenseApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtExpenseApproval ltExpenseApproval = new LtExpenseApproval();
						
						ltExpenseApproval.setExpApprovalId(rs.getLong("EXP_APPROVAL_ID"));
						ltExpenseApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltExpenseApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltExpenseApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltExpenseApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						
						ltExpenseApproval.setExpHeaderId(rs.getLong("EXP_HEADER_ID"));
						
						return ltExpenseApproval;
					}
				});
	
		return ltExpenseApprovalList;
	}
	
	@Override
	public LtExpenseApproval getExpenseApproval(Long expId, Long apprId) throws Exception
	{
		String query = env.getProperty("getExpenseApproval");

		List<LtExpenseApproval> ltExpenseApprovalList = jdbcTemplate.query(query, new Object[] {expId,apprId,apprId},
				new RowMapper<LtExpenseApproval>() {
					public LtExpenseApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtExpenseApproval ltExpenseApproval = new LtExpenseApproval();
						
						ltExpenseApproval.setExpApprovalId(rs.getLong("EXP_APPROVAL_ID"));
						ltExpenseApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltExpenseApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltExpenseApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltExpenseApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						
						ltExpenseApproval.setExpHeaderId(rs.getLong("EXP_HEADER_ID"));
						
						return ltExpenseApproval;
					}
				});
		
		
		if(ltExpenseApprovalList.size() > 0){
			return ltExpenseApprovalList.get(0);
		}
		return null;
	}
}
