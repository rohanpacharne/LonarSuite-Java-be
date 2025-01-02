package com.lonar.vendor.vendorportal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.Approval;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtCustomerApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.LtMastEmployeeDelegation;
import com.lonar.vendor.vendorportal.model.ProcedureCall;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/customerApproverQueries.properties", ignoreResourceNotFound = true)
public class LtCustomerApprovalDaoImpl implements LtCustomerApprovalDao{

	@Autowired
	private Environment env;

	@PersistenceContext(name = "em")
	private EntityManager em;
	
	@Autowired
	LtMastEmployeeDelegationDao ltMastEmployeeDelegationDao;
	
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
	public boolean updateStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException {
		int res=0;	
		if(approvalHistory.getEmployeeId()!=null ) 
		{
			String query = env.getProperty("updateStatusApproval");
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getCustomerApprovalId(),
					approvalHistory.getCustomerId(), approvalHistory.getEmployeeId(),approvalHistory.getEmployeeId());
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean updateAllStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException
	{
		String query = env.getProperty("updateAllStatusApproval");
		int res=0;	
			res = jdbcTemplate.update(query,
						approvalHistory.getStatus(),new Date(),approvalHistory.getCustomerApprovalId(),
						approvalHistory.getCustomerId());
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public List<LtCustomerApproval> chkEmpApproval(Long employeeId, Long vendorId) throws ServiceException {
		String query = env.getProperty("chkLtCustomerApprovalEmpApproval");

		List<LtCustomerApproval> ltCustomerApprovalList = jdbcTemplate.query(query, new Object[]
				{employeeId,vendorId,"APPROVED"},
				new RowMapper<LtCustomerApproval>() {
					public LtCustomerApproval mapRow(ResultSet rs, int arg1) throws SQLException {
						LtCustomerApproval ltCustomerApproval = new LtCustomerApproval();
						ltCustomerApproval.setCustomerApprovalId(rs.getLong("CUSTOMER_APPROVAL_ID"));
						return ltCustomerApproval;
					}
				});
	
		return ltCustomerApprovalList;
	}

	@Override
	public LtCustomerApproval getCustomerApproval(Long customerId, Long apprId) throws ServiceException 
	{
		String query = env.getProperty("getCustomerApproval");

		List<LtCustomerApproval> ltExpenseApprovalList = jdbcTemplate.query(query, new Object[] {customerId,apprId,apprId},
				new RowMapper<LtCustomerApproval>() {
					public LtCustomerApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtCustomerApproval ltExpenseApproval = new LtCustomerApproval();
						ltExpenseApproval.setCustomerApprovalId(rs.getLong("CUSTOMER_APPROVAL_ID"));
						ltExpenseApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltExpenseApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltExpenseApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltExpenseApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						ltExpenseApproval.setCustomerId(rs.getLong("CUSTOMER_ID"));
						return ltExpenseApproval;
					}
				});
		
		
		if(ltExpenseApprovalList.size() > 0){
			return ltExpenseApprovalList.get(0);
		}
		return null;
	}

	@Override
	public List<LtCustomerApproval> getCustomerApprovalByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("getCustomerApprovalByCustomerId");
		
		List<LtCustomerApproval> ltCustomerApprovalList = jdbcTemplate.query(query, new Object[] { customerId },
				new RowMapper<LtCustomerApproval>() {
					public LtCustomerApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtCustomerApproval ltCustomerApproval = new LtCustomerApproval();
						
						ltCustomerApproval.setCustomerApprovalId(rs.getLong("CUSTOMER_APPROVAL_ID"));
						ltCustomerApproval.setDelegationId(rs.getLong("DELEGATION_ID"));
						ltCustomerApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltCustomerApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltCustomerApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltCustomerApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						ltCustomerApproval.setCustomerId(rs.getLong("CUSTOMER_ID"));
						ltCustomerApproval.setStatus(rs.getString("STATUS"));
						ltCustomerApproval.setStartDate(rs.getDate("START_DATE"));
						ltCustomerApproval.setEndDate(rs.getDate("END_DATE"));
						ltCustomerApproval.setCreatedBy(rs.getLong("CREATED_BY"));
						ltCustomerApproval.setCreationDate(rs.getDate("CREATION_DATE"));
						ltCustomerApproval.setLastUpdateDate(rs.getDate("LAST_UPDATE_DATE"));
						ltCustomerApproval.setLastUpdateLogin(rs.getLong("LAST_UPDATE_LOGIN"));
						ltCustomerApproval.setLastUpdatedBy(rs.getLong("LAST_UPDATED_BY"));
						ltCustomerApproval.setModuleAppEmployeesId(rs.getLong("MODULE_APP_EMPLOYEES_ID"));
						ltCustomerApproval.setApprovalName(rs.getString("approval_Name"));
						ltCustomerApproval.setApprovalLevelName(rs.getString("approval_level_name"));
						return ltCustomerApproval;

					}
				});
		return ltCustomerApprovalList;
	}

	@Override
	public boolean chkForApprovers(Long vendorId) throws ServiceException {
		String query = env.getProperty("chkForApproversForClient");

		List<LtCustomerApproval> ltCustomerApprovalList = jdbcTemplate.query(query, new Object[] {vendorId},
				new RowMapper<LtCustomerApproval>() {
					public LtCustomerApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtCustomerApproval ltCustomerApproval = new LtCustomerApproval();
						ltCustomerApproval.setCustomerApprovalId(rs.getLong("CUSTOMER_APPROVAL_ID"));
						ltCustomerApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltCustomerApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltCustomerApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltCustomerApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						ltCustomerApproval.setCustomerId(rs.getLong("CUSTOMER_ID"));
						return ltCustomerApproval;
					}
				});
		
		
		if(ltCustomerApprovalList.size() > 0 && !ltCustomerApprovalList.isEmpty() ){
			return true;
		}else
			return false;
	}

	@Override
	public boolean submitForApproval(Date submissionDate, Long customerId, String status, Date approvedDate)
			throws ServiceException {
		int res=0;
		if(submissionDate!=null)
		{
			String query = env.getProperty("submitForApproval1");
			res=jdbcTemplate.update(query,status,new Date(),customerId);
		}
		else
		{
			String query = env.getProperty("submitCustomerForApproval2");
			 res=jdbcTemplate.update(query,status,new Date(),customerId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public String getCurrLevelByVendorApprovalId(Long vendorApprovalId) throws ServiceException {
		String query = env.getProperty("getCurrLevelByCustomerApprovalId");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { vendorApprovalId}, String.class);

		return nextlavel;
	}

	@Override
	public List<LtCustomerApproval> chkCurrentApprover(Long vendorId) throws ServiceException {
		String query = env.getProperty("chkCurrentApprover");

		List<LtCustomerApproval> ltCustomerApprovalList = jdbcTemplate.query(query, new Object[] {vendorId},
				new RowMapper<LtCustomerApproval>() {
					public LtCustomerApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtCustomerApproval ltCustomerApproval = new LtCustomerApproval();
						
						ltCustomerApproval.setCustomerApprovalId(rs.getLong("CUSTOMER_APPROVAL_ID"));
						ltCustomerApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltCustomerApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltCustomerApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltCustomerApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						
						ltCustomerApproval.setCustomerId(rs.getLong("CUSTOMER_ID"));
						
						return ltCustomerApproval;
					}
				});
	
		return ltCustomerApprovalList;
	}

	@Override
	public boolean updateVendorStatusApproval(LtCustomerApprovalHistory approvalHistory) throws ServiceException {
		String query = env.getProperty("updateCustomerStatusApproval");
		int res=0;	
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getCustomerApprovalId(),
					approvalHistory.getCustomerId());
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public List<LtMastCustomer> getInprocessCustomerList(String status) {
		String query = env.getProperty("getInprocessCustomerList");
		
		List<LtMastCustomer> ltMastCustomerList = jdbcTemplate.query(query,  new Object[] {status},
				new BeanPropertyRowMapper<LtMastCustomer> (LtMastCustomer.class));
		return ltMastCustomerList;
	}

	@Override
	public LtCustomerApproval getApprovalLevel(Long customerId) throws ServiceException {
		String query = "select   MIN(APPROVAL_LEVEL) as MIN_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID " + 
				" from LT_MAST_CUSTOMER_APPROVAL where CUSTOMER_ID = ? " + 
				" group by APPROVAL_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID order by MIN_LEVEL";
		
		List<LtCustomerApproval> customerApprovalList = jdbcTemplate.query(query, new Object[] {customerId},
				
				new RowMapper<LtCustomerApproval>() {
					public LtCustomerApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtCustomerApproval customerApproval = new LtCustomerApproval();
						customerApproval.setApprovalLevel(rs.getString("MIN_LEVEL"));
						customerApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						customerApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						return customerApproval;
					}
				});
		if(customerApprovalList.size()>0)
			return customerApprovalList.get(0); 
		else 
			return null;
	}

	@Override
	public List<LtCustomerApproval> getApprovalList(Long customerId, String currentApprovalLevel)
			throws ServiceException {
		String query = env.getProperty("getCustomerApprovalList");
		List<LtCustomerApproval> list=   jdbcTemplate.query(query, new Object[]{ customerId,currentApprovalLevel}, 
				 new BeanPropertyRowMapper<LtCustomerApproval>(LtCustomerApproval.class));
			return list;
	}

	@Override
	public String getNextApprovalLevel(Long customerId, String currentApprovalLevel) throws ServiceException {
		String query = env.getProperty("getNextApprovalLevelOfCustomer");
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { customerId, currentApprovalLevel}, String.class);
		return nextlavel;
	}

	@Override
	public boolean upDateStatus(Long customerId, String status, String currentApprovalLevel) throws ServiceException {
		int res=0;
		if(currentApprovalLevel!=null)
		{
			String query = env.getProperty("upDateCustomerStatus1");
			 res=jdbcTemplate.update(query,
				        status,new Date(),customerId,currentApprovalLevel,"APPROVED");
		}
		else
		{
			String query = env.getProperty("upDateCustomerStatus2");
			
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLevel,customerId);
		}
		if(res!=0)
			return true;
		else
			return false;
		
	}

	@Override
	public void updateCurrentApprovalLevel(Long customerId, String currentApprovalLavel) throws ServiceException {
		String query = env.getProperty("updateCurrentApprovalLevelCustomer");
		int res=jdbcTemplate.update(query,currentApprovalLavel, customerId );
		
	}

	@Override
	public boolean loadApprovers(Long customerId, Long companyId) throws ServiceException {
		String query1 = env.getProperty("getCustomerByCustomerId");
		List<LtMastCustomer> list=   jdbcTemplate.query(query1, new Object[]{ customerId }, 
				 new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class)); 
		System.out.println("list = "+list);
		 if(list.isEmpty())
		 {	
			 return false;
		 }
		 
//		 System.out.println();
			 
		 String query = env.getProperty("getCustomerApproversByDivision");
		
		List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]{ list.get(0).getInitiatorId(),"CUSTOMER",
				"ACTIVE",companyId}, 
			 new BeanPropertyRowMapper<Approval>(Approval.class)); 
		System.out.println(list.get(0).getInitiatorId());
		System.out.println(companyId);
		System.out.println("approvalList = "+approvalList);
		/* System.out.println("approvalList "+approvalList);
			Approval initiatorApproval = new Approval();
			initiatorApproval.setEmployeesId(list.get(0).getInitiatorId());
			initiatorApproval.setApprovalLevel("00");
			initiatorApproval.setModuleApprovalId(00L);
		
			approvalList.add(initiatorApproval);*/
		boolean flag=false;
	if(approvalList.size()>0)
	{
		for(Approval approvalObj:approvalList)
		{
			Approval approval=approvalObj;
			List<LtMastEmployeeDelegation> ltMastEmployeeDelegation = ltMastEmployeeDelegationDao
					.findForDelegation(approvalObj.getEmployeesId());
			if(ltMastEmployeeDelegation!= null && ltMastEmployeeDelegation.size()>0)
			{
				approval.setDelegationId(ltMastEmployeeDelegation.get(0).getDelegationId());
			}
			
			
			
			int res=0;
			if(approval.getEmployeesId()!=null && approval.getModuleApprovalId()!=null && approval.getApprovalLevel()!=null)
			{
				
				res=jdbcTemplate.update(" INSERT INTO LT_MAST_CUSTOMER_APPROVAL "
						+ " (MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
						+ " CUSTOMER_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
						+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
		 		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
		 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
		 		null,approval.getDelegationId(),list.get(0).getCustomerId(),"NO_ACTION",new Date(),
		 		null,list.get(0).getCreatedBy(),new Date(),
		 		list.get(0).getLastUpdateLogin(),list.get(0).getLastUpdatedBy(),
		 		new Date(),approval.getModuleAppEmployeesId());
				if(res!=0)
					flag=true;
			}
		}
	}
	return flag;
	}

	@Override
	@Transactional
	public ProcedureCall customerValidationPackage(Long customerId) throws ServiceException {
		ProcedureCall procedureCall= new ProcedureCall();
		StoredProcedureQuery query = em
			    .createStoredProcedureQuery("validate_customer")//LT_VPAL_CUST_VALIDATIONS_PKG.validate_customer
			    .registerStoredProcedureParameter(1, Long.class,
			    		ParameterMode.IN)
			    .setParameter(1, customerId)
			    .registerStoredProcedureParameter(2, String.class, 
				         ParameterMode.OUT)
			    .registerStoredProcedureParameter(3, String.class, 
			         ParameterMode.OUT);
			query.execute();
			
			 // Check the output parameters after execution
		    String statusCode = (String) query.getOutputParameterValue(2); // Output parameter for statusCode
		    String statusMessage = (String) query.getOutputParameterValue(3); // Output parameter for statusMessage
		    
		    System.out.println("statusCode = "+statusCode);
		    System.out.println("statusMessage = "+statusMessage);

			if(query.getOutputParameterValue(2).toString().trim().equals("ERROR")){
				procedureCall.setStatusCode(query.getOutputParameterValue(2).toString().trim());
				procedureCall.setStatusMessage(query.getOutputParameterValue(3).toString().trim());
			}
			else if(query.getOutputParameterValue(2).toString().trim().equals("SUCCESS")){
				procedureCall.setStatusCode("SUCCESS");
				if(query.getOutputParameterValue(2)!=null) {
				procedureCall.setStatusMessage(query.getOutputParameterValue(3).toString());
				}
				//procedureCall.setShipmentHeaderId((Long) query.getOutputParameterValue(3));
			}
			System.out.println("procedureCall = "+procedureCall);
			return procedureCall;
	}

	@Override
	public boolean deleteByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("deleteLtCustomerApprovalByCustomerId");
		int res = 0;
		jdbcTemplate.update(query, customerId);
		if (res != 0)
			return true;
		return false;
	}
}
