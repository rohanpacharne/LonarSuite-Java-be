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
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.controller.VendorBuyerDetails;
import com.lonar.vendor.vendorportal.model.Approval;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtMastEmployeeDelegation;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.LtMastVendors;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.SendBroadCastEmail;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.VendorApproval;
import com.lonar.vendor.vendorportal.reports.ReportParameters;
import com.lonar.vendor.vendorportal.repository.LtMastVendorsRepository;

@Repository
@PropertySource(value = "classpath:queries/vendorQueries.properties", ignoreResourceNotFound = true)
public class LtMastVendorsDaoImpl implements LtMastVendorsDao,CodeMaster
{
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtMastVendorsRepository ltMastVendorsRepository;
	
	@Autowired
	LtMastEmployeeDelegationDao ltMastEmployeeDelegationDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtMastVendorCocDao ltMastVendorCocDao;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) throws SQLException {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println(dataSource.getConnection().getSchema());
	}
	
	@Override
	public List<LtMastVendors> getAllVendors() throws ServiceException {
		String query = env.getProperty("getAllVendors");
		
			List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ }, 
					 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
			 
			
			return list;
	}

	@Override
	public List<LtMastVendors> getAllActiveVendors() throws ServiceException 
	{
		String query = env.getProperty("getAllActiveVendors");
			List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{  }, 
					 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
			return list;
	}
	
	@Override
	public List<LtMastVendors> getActiveVendorByName(Long companyId,String vendorName) throws ServiceException 
	{
		String query = env.getProperty("getActiveVendorByName");
			
			List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ companyId,"%"+vendorName.trim().toUpperCase()+"%",
					"%"+vendorName.trim().toUpperCase()+"%" }, 
					 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
			return list;
	}
	
	@Override
	public LtMastVendors getVendorById(long vendorId) throws ServiceException 
	{
		String query = env.getProperty("getVendorByIdVendors");
		
	List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ vendorId }, 
			 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
	System.out.println("list = "+list);
	 if(!list.isEmpty())
		 return list.get(0);
	 else
		 return null;
	 }

	@Override
	public Long save(LtMastVendors vendors) throws ServiceException 
	{
		vendors = ltMastVendorsRepository.save(vendors);
		return vendors.getVendorId();
		/*
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator()
			{
			    @Override
			    public PreparedStatement createPreparedStatement(Connection con) throws SQLException 
			    {
			    	String query = env.getProperty("saveVendor");
			    	
			        PreparedStatement statement = con.prepareStatement
			        (" INSERT INTO LT_MAST_VENDORS "
			       + " (Vendor_Code,Vendor_Name,Status,PAN_No,Vendor_Type,Proprietor_Name,Business_Nature, "
			       + "Division_Id,Initiator_Id, Remark, Primary_Email, Start_Date, End_Date, Created_by, "
			       + " Creation_date, Last_update_login, Last_updated_by, Last_update_date ) "
			       + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
			        query, Statement.RETURN_GENERATED_KEYS);
			        
			        
			        
			        //==========================================
			        
			        statement.setLong(1, vendors.getVendorId());
			        statement.setString(2, vendors.getVendorCode());
			        statement.setString(3, vendors.getVendorName());
			        statement.setString(4, vendors.getStatus());
			        statement.setString(5, vendors.getPanNo());
			        statement.setString(6, vendors.getVendorType());
			        statement.setString(7, vendors.getProprietorName());
			        statement.setString(8, vendors.getBusinessNature());
			        statement.setLong(9, vendors.getDivisionId());
			       
			        //==========================================
			         	
			        statement.setLong(0, vendors.getVendorId());
			        statement.setString(1, vendors.getVendorCode());
			        statement.setString(2, vendors.getVendorName());
			        statement.setString(3, vendors.getStatus());
			        statement.setString(4, vendors.getPanNo());
			        statement.setString(5, vendors.getVendorType());
			        statement.setString(6, vendors.getProprietorName());
			        statement.setString(7, vendors.getBusinessNature());
			        statement.setLong(8, vendors.getDivisionId());
			        
			        //===============================================
			        
			        if(vendors.getInitiatorId()!=null)
			        {
			        	 statement.setLong(9, vendors.getInitiatorId());
			        }
			        else
			        {
			        	 statement.setLong(9,0);
			        }
			       
			        statement.setString(10, vendors.getRemark());
			        statement.setString(11, vendors.getPrimaryEmail());
			        statement.setTimestamp(12, new Timestamp(vendors.getStartDate().getTime()));
			        
			        if(vendors.getEndDate()!=null) {
			        	statement.setTimestamp(13, new Timestamp(vendors.getEndDate().getTime()));
			        } else {
			        	statement.setTimestamp(13,null);
			        }
			        statement.setLong(14, vendors.getCreatedBy());
			        statement.setTimestamp(15, new Timestamp(vendors.getCreationDate().getTime()));
			        statement.setLong(16, vendors.getLastUpdateLogin());
			        statement.setLong(17, vendors.getLastUpdatedBy());       
			        statement.setTimestamp(18, new Timestamp(vendors.getLastUpdateDate().getTime()));
			   
			        return statement;
			    }
			}, holder);

			Long primaryKey = holder.getKey().longValue();
			return primaryKey;*/
	}
	
	
	@Override
	public boolean update(LtMastVendors vendors) throws ServiceException 
	{
		vendors = ltMastVendorsRepository.save(vendors);
		if(vendors.getVendorId()!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Long vendorId) throws ServiceException 
	{
		String query = env.getProperty("deleteVendor");
		int res=jdbcTemplate.update(query,vendorId);
		if(res==1)
			return true;
		else
			return false;
	}

	@Override
	public boolean getByName(LtMastVendors ltMastVendors) throws ServiceException
	{
		String query = env.getProperty("getByNameVendor");
	
	List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ ltMastVendors.getVendorName().toUpperCase(),ltMastVendors.getCompanyId()}, 
			 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
	 boolean flag=false;
		if(!list.isEmpty() && list.size() >0 )
		{
			 if(!list.get(0).getVendorId().equals(ltMastVendors.getVendorId()))
			 {	 flag= true; }
		}
		else
		{	 flag= false; }
		return flag;
	}

	@Override
	public boolean getByVendorCode(LtMastVendors ltMastVendors) throws ServiceException {
		
		String query = env.getProperty("getByVendorCodeVendor");
		
			List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ ltMastVendors.getVendorCode().toUpperCase(),ltMastVendors.getCompanyId()}, 
					 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
			 boolean flag=false;
		
			if(!list.isEmpty() && list.size() >0 )
			{
				
				 if(!list.get(0).getVendorId().equals(ltMastVendors.getVendorId()))
				 {	 flag= true; }
			}
			else
			{	 flag= false; }
			return flag;
	}

	@Override
	public boolean getByPanNo(LtMastVendors ltMastVendors) throws ServiceException
	{
		String query = env.getProperty("getByPanNoVendor");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ltMastVendors.getPanNo().toUpperCase(),ltMastVendors.getCompanyId()}, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 boolean flag=false;
			if(!list.isEmpty() && list.size() >0 )
			{
				 if(!list.get(0).getVendorId().equals(ltMastVendors.getVendorId()))
				 {	 flag= true; }
			}
			else
			{	 flag= false; }
			return flag;
	}

	@Override
	public Long getLtMastVendorsCount(Long companyId,LtMastVendors input) throws ServiceException {
		String query = env.getProperty("getLtMastVendorsCount");
		 
		String venName=null;
		   if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		   {venName="%"+input.getVendorName().trim().toUpperCase() + "%";}
		   
		   String venCode=null;
		   if(input.getVendorCode()!=null && !input.getVendorCode().equals(""))
		   {venCode="%"+input.getVendorCode().trim().toUpperCase()+"%";}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals("")) 
		   {status="%"+input.getStatus().trim().toUpperCase()+"%";}
		   
		   String pan=null;
		   if(input.getPanNo()!=null &&  !input.getPanNo().equals("")) 
		   {pan="%"+input.getPanNo().trim().trim().toUpperCase()+"%";}
		   
		   
		   String email=null;
		   if(input.getPrimaryEmail()!=null &&  !input.getPrimaryEmail().equals("")) 
		   {email="%"+input.getPrimaryEmail().trim().trim().toUpperCase()+"%";}
		   
		   
			if(input.getStDate() == null || input.getStDate().trim().equals(""))
			{
				input.setStDate(null);
			}
			if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
			{
				input.setEnDate(null);
			}
			
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {companyId,venCode,venName,status,pan,email,
						input.getStDate(),input.getEnDate()}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtMastVendors> getLtMastVendorsDataTable(Long companyId,LtMastVendors input) throws ServiceException 
	{
		String query = env.getProperty("getLtMastVendorsDataTable");
		
		   String venName=null;
		   if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		   {venName="%"+input.getVendorName().trim().toUpperCase() + "%";}
		   
		   String venCode=null;
		   if(input.getVendorCode()!=null && !input.getVendorCode().equals(""))
		   {venCode="%"+input.getVendorCode().trim().toUpperCase()+"%";}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals("")) 
		   {status="%"+input.getStatus().trim().toUpperCase()+"%";}
		   
		   String pan=null;
		   if(input.getPanNo()!=null &&  !input.getPanNo().equals("")) 
		   {pan="%"+input.getPanNo().trim().trim().toUpperCase()+"%";}
		   
		   String email=null;
		   if(input.getPrimaryEmail()!=null &&  !input.getPrimaryEmail().equals("")) 
		   {email="%"+input.getPrimaryEmail().trim().trim().toUpperCase()+"%";}
		   
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
			
			 
			List<LtMastVendors> list = (List<LtMastVendors>) 
					jdbcTemplate.query(query , new Object[]{companyId,venCode,venName,status,pan,email,
							input.getStDate(),input.getEnDate(),
							
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							
							input.getStart()+input.getLength(),input.getStart()+1},
				 new  BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class));
				return list;
		
	}

	@Override
	public List<LtMastVendors> getActiveVendorByNameAndDivId(String vendorName, Long divId) throws ServiceException {
		String query = env.getProperty("getActiveVendorByNameAndDivId");
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ "%"+vendorName.toUpperCase()+"%",
				"%"+vendorName.toUpperCase()+"%",divId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 
		
		return list;
	}

	@Override
	public List<LtMastVendors> getVendorByDivId(Long divId) throws ServiceException {
		String query = env.getProperty("getVendorByDivId");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ divId}, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 
		
		return list;
	}

	@Override
	public boolean submitForApproval(Date date, Long vendorId, String state, Object object) throws ServiceException {
		String query = env.getProperty("submitVendorForApproval");
		
		int res=jdbcTemplate.update(query,state,new Date(),vendorId);
		
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public List<LtMastVendors> getInprocessVendorList(String inprogress) throws ServiceException 
	{
		//String query = env.getProperty("getInprocessVendorList");
		String query = "SELECT  distinct(e.VENDOR_ID) as VENDOR_ID, e.VENDOR_CODE,e.VENDOR_NAME,e.STATUS,e.START_DATE, "
				+ " e.VENDOR_TYPE,e.DIVISION_ID,e.INITIATOR_ID,e.PRIMARY_EMAIL,e.COMPANY_ID,apr.APPROVAL_LEVEL "
				+ " FROM LT_MAST_VENDORS e, LT_VENDOR_APPROVAL apr "
				+ " WHERE apr.VENDOR_ID = e.VENDOR_ID AND e.Status= 'INPROCESS' "
				+ " AND ((apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'APPROVED') "
				+ " OR (apr.CURRENT_APPROVAL_LEVEL IS NULL AND apr.STATUS = 'NO_ACTION') "
				+ " OR  (apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'NO_ACTION'))";
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{  }, 
			 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		return list;
	}

	@Override
	public VendorApproval getApprovalLevel(Long vendorId) throws ServiceException {
		String query = env.getProperty("getApprovalLevel");
		
		List<VendorApproval> expenseApprovalList = jdbcTemplate.query(query, new Object[] {vendorId},
				
				new RowMapper<VendorApproval>() {
					public VendorApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						VendorApproval expenseApproval = new VendorApproval();

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
	public List<VendorApproval> getApprovalList(Long vendorId, String currentApprovalLevel) throws ServiceException {
		String query = env.getProperty("getApprovalList");
		
		List<VendorApproval> list=   jdbcTemplate.query(query, new Object[]{ vendorId,currentApprovalLevel}, 
			 new BeanPropertyRowMapper<VendorApproval>(VendorApproval.class));
		return list;
	}

	@Override
	public String getNextApprovalLevel(Long vendorId, String currentApprovalLevel) throws ServiceException {
		String query = env.getProperty("getNextApprovalLevel");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { vendorId, currentApprovalLevel}, String.class);

		return nextlavel;
	}

	@Override
	public boolean upDateStatus(Long vendorId, String status, String currentApprovalLevel) throws ServiceException {
		int res=0;
			if(currentApprovalLevel!=null)
			{
				String query = env.getProperty("upDateVStatus1");
				 res=jdbcTemplate.update(query,
					        status,new Date(),vendorId,currentApprovalLevel,"APPROVED");
			}
			else
			{
				String query = env.getProperty("upDateVStatus2");
				
				res=jdbcTemplate.update(query,
				        status,new Date(),currentApprovalLevel,vendorId);
			}
			if(res!=0)
				return true;
			else
				return false;
		
	}

	@Override
	public void updateCurrentApprovalLevel(Long vendorId, String currentApprovalLavel) throws ServiceException {
		String query = env.getProperty("updateCurrentApprovalLevelV");
		int res=jdbcTemplate.update(query,
				currentApprovalLavel, vendorId );
	}

	@Override
	public boolean loadApprovers(Long vendorId,Long companyId) throws ServiceException
	{
		String query1 = env.getProperty("getVendorByIdVendors");
		List<LtMastVendors> list=   jdbcTemplate.query(query1, new Object[]{ vendorId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 if(list.isEmpty())
		 {	
			 return false;
		 }
			 
		 String query = " SELECT a.module_app_employees_id,a.employees_id,b.approval_level,b.module, "
				+ " a.MODULE_APPROVAL_ID ,a.START_DATE,a.END_DATE  "
				+ " FROM lt_mast_module_app_emp a,lt_mast_module_approvals b "
				+ " WHERE a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID "
				+ " AND DIVISION_ID= ( SELECT DIVISION_ID FROM LT_MAST_EMPLOYEES e WHERE e.EMPLOYEE_ID = ? ) "
				+ " AND MODULE= 'VENDOR'  "
				+ " AND STATUS= 'DRAFT' AND b.COMPANY_ID = ? "
				+ " AND ( a.START_DATE <= SYSDATE() AND (a.END_DATE is null or a.END_DATE > SYSDATE()) ) ";
		
		List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]{ list.get(0).getInitiatorId(),companyId}, 
			 new BeanPropertyRowMapper<Approval>(Approval.class)); 
	 
		//List<LtMastEmployees>  empList=ltMastEmployeesDao.getByEmpId(list.get(0).getInitiatorId());
		
			Approval superviserApproval = new Approval();
			superviserApproval.setEmployeesId(list.get(0).getInitiatorId());
			superviserApproval.setApprovalLevel("00");
			superviserApproval.setModuleApprovalId(00L);
		
			approvalList.add(superviserApproval);
		
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
				
				res=jdbcTemplate.update(" INSERT INTO lt_vendor_approval "
						+ " ( MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
						+ " VENDOR_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
						+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
		 		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
		 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
		 		null,approval.getDelegationId(),list.get(0).getVendorId(),NO_ACTION,list.get(0).getStartDate(),
		 		list.get(0).getEndDate(),list.get(0).getCreatedBy(),new Date(),
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
	public LtMastVendors getStatusVendorById(Long vendorId) throws ServiceException {
		String query1 = env.getProperty("getStatusVendorById");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query1, new Object[]{ vendorId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 if(list.isEmpty())
		 {	
			 return null;
		 }else
			 return list.get(0);
	}

	@Override
	public List<LtMastVendors> getAllVendorsByCompanyId(Long companyId) throws ServiceException {
		String query = env.getProperty("getAllVendorsByCompanyId");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{companyId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 
		return list;
	}

	@Override
	public LtMastVendors getByEMailId(String primaryEmail,Long companyId) throws ServiceException {
		String query = env.getProperty("getLtMastVendorsByEMailId");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{primaryEmail.toUpperCase(),companyId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 if(list.isEmpty())
			 return null;
		 else
		return list.get(0);
	}

	@Override
	public Long getLtMastVendorsCountByInitiatorId(LtMastVendors input, Long initiatorId) throws ServiceException {
		String query = env.getProperty("getLtMastVendorsCountByInitiatorId");
		 
		String venName=null;
		   if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		   {venName="%"+input.getVendorName().trim().toUpperCase() + "%";}
		   
		   String venCode=null;
		   if(input.getVendorCode()!=null && !input.getVendorCode().equals(""))
		   {venCode="%"+input.getVendorCode().trim().toUpperCase()+"%";}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals("")) 
		   {status="%"+input.getStatus().trim().toUpperCase()+"%";}
		   
		   String pan=null;
		   if(input.getPanNo()!=null &&  !input.getPanNo().equals("")) 
		   {pan="%"+input.getPanNo().trim().trim().toUpperCase()+"%";}
		   
		   
		   String email=null;
		   if(input.getPrimaryEmail()!=null &&  !input.getPrimaryEmail().equals("")) 
		   {email="%"+input.getPrimaryEmail().trim().trim().toUpperCase()+"%";}
		   
		   
			if(input.getStDate() == null || input.getStDate().trim().equals(""))
			{
				input.setStDate(null);
			}
			if(input.getEnDate() == null || input.getEnDate().trim().equals(""))
			{
				input.setEnDate(null);
			}
			
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {initiatorId,venCode,venName,status,pan,email,
						input.getStDate(),input.getEnDate()}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtMastVendors> getLtMastVendorsDataTableByInitiatorId(LtMastVendors input, Long initiatorId)
			throws ServiceException {
		String query = env.getProperty("getLtMastVendorsDataTableByInitiatorId");
		
		   String venName=null;
		   if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		   {venName="%"+input.getVendorName().trim().toUpperCase() + "%";}
		   
		   String venCode=null;
		   if(input.getVendorCode()!=null && !input.getVendorCode().equals(""))
		   {venCode="%"+input.getVendorCode().trim().toUpperCase()+"%";}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals("")) 
		   {status="%"+input.getStatus().trim().toUpperCase()+"%";}
		   
		   String pan=null;
		   if(input.getPanNo()!=null &&  !input.getPanNo().equals("")) 
		   {pan="%"+input.getPanNo().trim().trim().toUpperCase()+"%";}
		   
		   String email=null;
		   if(input.getPrimaryEmail()!=null &&  !input.getPrimaryEmail().equals("")) 
		   {email="%"+input.getPrimaryEmail().trim().trim().toUpperCase()+"%";}
		   
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
			List<LtMastVendors> list = (List<LtMastVendors>) 
					jdbcTemplate.query(query , new Object[]{initiatorId,venCode,venName,status,pan,email,
							input.getStDate(),input.getEnDate(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getStart()+input.getLength(),input.getStart()},
				 new  BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class));
				return list;
	}

	@Override
	public List<LtMastVendors> getAllVendorsByInitiator(Long initiatorId) throws ServiceException {
		String query = env.getProperty("getAllVendorsByInitiator");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{initiatorId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 
		
		return list;
	}

	@Override
	public boolean loadInvoiceApprovers(LtInvoiceHeaders ltInvoiceHeaders) throws ServiceException {
		 String query = " SELECT a.module_app_employees_id,a.employees_id,b.approval_level,b.module, "
				+ " a.MODULE_APPROVAL_ID ,a.START_DATE,a.END_DATE  "
				+ " FROM lt_mast_module_app_emp a,lt_mast_module_approvals b "
				+ " WHERE a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID "
				+ " AND DIVISION_ID= ? "
				+ " AND MODULE= 'INVOICE'  "
				+ " AND STATUS= 'DRAFT' "
				+ " AND ( a.START_DATE <= SYSDATE() AND (a.END_DATE is null or a.END_DATE > SYSDATE()) ) ";
		
		List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]{ ltInvoiceHeaders.getDivisionId()}, 
			 new BeanPropertyRowMapper<Approval>(Approval.class)); 
	 
		List<LtMastEmployees>  empList=ltMastEmployeesDao.getByEmpId(ltInvoiceHeaders.getBuyerId());
		
			Approval superviserApproval = new Approval();
			superviserApproval.setEmployeesId(ltInvoiceHeaders.getBuyerId());
			superviserApproval.setApprovalLevel("00");
			superviserApproval.setModuleApprovalId(00L);
		
			approvalList.add(superviserApproval);
		
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
				res=jdbcTemplate.update(" INSERT INTO lt_invoice_approval "
						+ " (MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
						+ " INVOICE_HEADER_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
						+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
		 		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
		 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
		 		null,approval.getDelegationId(),ltInvoiceHeaders.getInvoiceHeaderId(),NO_ACTION,new Date(),
		 		null,ltInvoiceHeaders.getCreatedBy(),new Date(),
		 		ltInvoiceHeaders.getLastUpdateLogin(),ltInvoiceHeaders.getLastUpdatedBy(),
		 		new Date(),approval.getModuleAppEmployeesId());
				if(res!=0)
					flag=true;
			}
			
		}
	}
	
	return flag;
	}

	@Override
	public LtMastVendors getPanByvendorbyid(Long vendorId) throws ServiceException {
		String query = env.getProperty("getPanByvendorbyid");
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ vendorId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 if(!list.isEmpty())
			 return list.get(0);
		 else
			 return null;
	}

	@Override
	public boolean checkDuplicateUser(String primaryEmail,Long companyId) throws ServiceException {
		String query = env.getProperty("checkDuplicateUserForVendor");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{ primaryEmail.trim().toUpperCase(),
				primaryEmail.trim().toUpperCase(),companyId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 
		if(!list.isEmpty())
		{	return true; }
		else
		{	return false; }
	}

	@Override
	public List<LtMastVendors> getAllVendorInfoByCompany(SendBroadCastEmail emailList) throws ServiceException {
		String query = env.getProperty("getAllVendorInfoByCompany");
		
		String category = null;
		if(emailList.getCompanyCategory()!=null) {
			category = emailList.getCompanyCategory().toUpperCase();
			if(category.equals("NULL")) {
				category = null;
			}
		}
		
		String status = null;
		if(emailList.getStatus()!=null) {
			status = emailList.getStatus().toUpperCase();
			if(status.equals("NULL")) {
				status = null;
			}
		}
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{emailList.getDivisionId(),category,
				status,emailList.getCompanyId()}, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		return list;
	}

	@Override
	public Status getCompanyByVendor(Long venId) throws ServiceException {
		Status status = new Status();
		String query = env.getProperty("getCompanyIdByVendorId");
		String companyId  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {venId}, String.class);

		status.setData(companyId);
		return status;
	}

	@Override
	public Status getVendorNameById(Long vendorId) throws ServiceException {
		Status status = new Status();
		String query = env.getProperty("getVendorNameById");
		
		List<VendorBuyerDetails> list = jdbcTemplate.query(query, new Object[]{ vendorId }, 
				 new BeanPropertyRowMapper<VendorBuyerDetails>(VendorBuyerDetails.class)); 
		if(!list.isEmpty()) {
		 status.setData(list.get(0));
		}
		return status;
	}

	@Override
	public List<LtMastVendors> getDataForReport(ReportParameters reportParameters) {
		String query = env.getProperty("getLtMastVendorsDataForReport");
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{reportParameters.getCompanyId(),reportParameters.getVendorId(),reportParameters.getStatus(),
				reportParameters.getInitiatorId(),reportParameters.getCompanyCategory(),reportParameters.getDivisionId()  }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		return list;
	}

	@Override
	public List<LtMastVendors> getVendorByName(Long companyId, String vendorName) throws ServiceException {
		String query = env.getProperty("getVendorByName");
		
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{companyId , "%"+vendorName.toUpperCase()+"%","%"+vendorName.toUpperCase()+"%"}, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 
		return list;
	}

	@Override
	public boolean updatePath(LtMastVendors ltMastVendor) throws ServiceException {
		ltMastVendor = ltMastVendorsRepository.save(ltMastVendor);
		if(ltMastVendor!=null)
			return true;
		else
			return false;
	}

	@Override
	public LtMastVendors getByRegistrationMailId(String registrationEmail, Long companyId) throws ServiceException {
		String query = env.getProperty("getLtMastVendorsByRegistrationMailId");
		List<LtMastVendors> list=   jdbcTemplate.query(query, new Object[]{registrationEmail.toUpperCase(),companyId }, 
				 new BeanPropertyRowMapper<LtMastVendors>(LtMastVendors.class)); 
		 if(list.isEmpty())
			 return null;
		 else
		return list.get(0);
	}

	@Override
	public boolean loadRentalAgreementApprovers(LtRentalAgreementHeaders ltRentalAgreementHeaders)
			throws ServiceException {
		String query = " SELECT a.module_app_employees_id,a.employees_id,b.approval_level,b.module, "
				+ " a.MODULE_APPROVAL_ID ,a.START_DATE,a.END_DATE  "
				+ " FROM lt_mast_module_app_emp a,lt_mast_module_approvals b "
				+ " WHERE a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID "
				+ " AND DIVISION_ID= ? "
				+ " AND MODULE= 'RENTAL_AGREEMENT'  "
				+ " AND STATUS= 'DRAFT' "
				+ " AND ( a.START_DATE <= SYSDATE() AND (a.END_DATE is null or a.END_DATE > SYSDATE()) ) ";
		
		List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]{ ltRentalAgreementHeaders.getDivisionId()}, 
			 new BeanPropertyRowMapper<Approval>(Approval.class)); 
	 
		List<LtMastEmployees>  empList=ltMastEmployeesDao.getByEmpId(ltRentalAgreementHeaders.getInitiatorId());
		
			Approval superviserApproval = new Approval();
			superviserApproval.setEmployeesId(ltRentalAgreementHeaders.getInitiatorId());
			superviserApproval.setApprovalLevel("00");
			superviserApproval.setModuleApprovalId(00L);
		
			approvalList.add(superviserApproval);
		
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
				res=jdbcTemplate.update(" INSERT INTO lt_rental_agreement_approval "
						+ " (MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
						+ " AGREEMENT_HEADER_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
						+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
		 		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
		 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
		 		null,approval.getDelegationId(),ltRentalAgreementHeaders.getAgreementHeaderId(),NO_ACTION,new Date(),
		 		null,ltRentalAgreementHeaders.getCreatedBy(),new Date(),
		 		ltRentalAgreementHeaders.getLastUpdateLogin(),ltRentalAgreementHeaders.getLastUpdatedBy(),
		 		new Date(),approval.getModuleAppEmployeesId());
				if(res!=0)
					flag=true;
			}
			
		}
	}
	
	return flag;
	}

}
