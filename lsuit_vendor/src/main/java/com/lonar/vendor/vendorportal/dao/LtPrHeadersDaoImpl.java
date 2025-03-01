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

import com.lonar.vendor.vendorportal.model.Approval;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtMastEmployeeDelegation;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.LtMastModuleApprovals;
import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.LtPrHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtPrHeadersRepository;

@Repository
@PropertySource(value = "classpath:queries/prHeaderQueries.properties", ignoreResourceNotFound = true)
public class LtPrHeadersDaoImpl implements LtPrHeadersDao,CodeMaster {
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtPrHeadersRepository ltPrHeadersRepository;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	LtMastEmployeeDelegationDao ltMastEmployeeDelegationDao;
	
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
		if(loadApprovers(LtPrHeadersSaved)) {
			return LtPrHeadersSaved.getPrHeaderId();
		}else {
			return null;
		}
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

	@Override
	public LtPrHeaders getPrHeaderById(Long prHeaderId) {
		// TODO Auto-generated method stub
		String query = env.getProperty("getPrById");
		List<LtPrHeaders> list=   jdbcTemplate.query(query, new Object[]{prHeaderId  }, 
				 new BeanPropertyRowMapper<LtPrHeaders>(LtPrHeaders.class)); 
		if(list.isEmpty())
			return null;
		else
		 return list.get(0);
	}

	@Override
	public boolean update(LtPrHeaders ltPrHeaders) throws ServiceException {
		// TODO Auto-generated method stub
		ltPrHeaders = ltPrHeadersRepository.save(ltPrHeaders);
		if(ltPrHeaders.getPrHeaderId()!=null) {
			
			return true;
		}
		else
		return false;
	}

	@Override
	public LtPrHeaders getPrStatusById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			String query = env.getProperty("getPrStatusById");
			LtPrHeaders status=   jdbcTemplate.queryForObject(query, new Object[]{id}, 
					new BeanPropertyRowMapper<LtPrHeaders>(LtPrHeaders.class)); 
			//status.getStatus();
			if(status!= null) 
			{
				return status;
			}else {
				 return null;
				 }
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public List<PrApproval> getApprovalList(Integer prHeaderId, String currentApprovalLevel)
			throws ServiceException {
		
		String query = " SELECT a.*,'N' as APPROVED_BY_ANYONE " + 
				" FROM LT_PR_APPROVAL a left outer join lt_mast_module_approvals b " + 
				" on a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID  " + 
				" WHERE a.PR_HEADER_ID = ? AND a.APPROVAL_LEVEL = ifnull(?,a.APPROVAL_LEVEL) ORDER BY a.APPROVAL_LEVEL ASC ";
		List<PrApproval> list=   jdbcTemplate.query(query, new Object[]{ prHeaderId,currentApprovalLevel}, 
				 new BeanPropertyRowMapper<PrApproval>(PrApproval.class));
			return list;
	}
	
	@Override
	public LtMastEmployees getSupervisorIdByRequesterId(Long requesterId) {
		// TODO Auto-generated method stub
//		String query = env.getProperty("getPrById");
		String query = "Select * from lt_mast_employees where employee_id = ?";
		List<LtMastEmployees> list=   jdbcTemplate.query(query, new Object[]{requesterId  }, 
				 new BeanPropertyRowMapper<LtMastEmployees>(LtMastEmployees.class)); 
		if(list.isEmpty())
			return null;
		else
		 return list.get(0);
	}
	
	@Override
	public boolean loadApprovers(LtPrHeaders ltPrHeaders) throws ServiceException {
		List<PrApproval> prApprovalsList = getApprovalList(ltPrHeaders.getPrHeaderId(),null);
		if(prApprovalsList.isEmpty()) {
		 String query = " SELECT a.module_app_employees_id,a.employees_id,b.approval_level,b.module, "
					+ " a.MODULE_APPROVAL_ID ,a.START_DATE,a.END_DATE  "
					+ " FROM lt_mast_module_app_emp a,lt_mast_module_approvals b "
					+ " WHERE a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID "
					+ " AND DIVISION_ID= ? "
					+ " AND MODULE= 'PURCHASE_REQUEST'  "
					+ " AND STATUS= 'ACTIVE' "
					+ " AND ( a.START_DATE <= SYSDATE() AND (a.END_DATE is null or a.END_DATE > SYSDATE()) ) ";
			
			List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]{ ltPrHeaders.getDivisionId()}, 
				 new BeanPropertyRowMapper<Approval>(Approval.class)); 
		 
			//List<LtMastEmployees>  empList=ltMastEmployeesDao.getByEmpId(ltInvoiceHeaders.getBuyerId());
			
				Approval superviserApproval = new Approval();
				superviserApproval.setEmployeesId(getSupervisorIdByRequesterId(ltPrHeaders.getRequesterId().longValue()).getSupervisorEmpId());
				superviserApproval.setApprovalLevel("00");
				superviserApproval.setModuleApprovalId(00L);
				
				System.out.println("sup id : "+superviserApproval.getEmployeesId());
			
				approvalList.add(superviserApproval);
			
			boolean flag=false;
		if(approvalList.size()>0)
		{
			
			for(Approval approvalObj:approvalList)
			{
				System.out.println("approvalObj = "+approvalObj);
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
					res=jdbcTemplate.update(" INSERT INTO lt_pr_approval "
							+ " (MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
							+ " PR_HEADER_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
							+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
			 		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
			 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
			 		null,approval.getDelegationId(),ltPrHeaders.getPrHeaderId(),NO_ACTION,new Date(),
			 		null,ltPrHeaders.getCreatedBy(),new Date(),
			 		ltPrHeaders.getLastUpdateLogin(),ltPrHeaders.getLastUpdatedBy(),
			 		new Date(),approval.getModuleAppEmployeesId());
					if(res!=0)
						flag=true;
				}
				
			}
		}
		
		return flag;
		}else {
			boolean flag=false;
			prApprovalsList.get(0).setApprovalId(getSupervisorIdByRequesterId(ltPrHeaders.getRequesterId().longValue()).getSupervisorEmpId());
			//for(InvoiceApproval invoiceApproval : invoiceApprovalsList ) {
				int res=0;
				res=jdbcTemplate.update(" UPDATE lt_pr_approval SET APPROVAL_ID = ?,LAST_UPDATE_DATE = ? "
						+ "  WHERE PR_APPROVAL_ID = ? AND APPROVAL_LEVEL = ? ",
						prApprovalsList.get(0).getApprovalId(),new Date(),prApprovalsList.get(0).getPrApprovalId(),"00");
				if(res!=0) {
					return flag=true;
				}
			//}
			return flag;
		}
	}

	@Override
	public boolean checkStatusIsPending(Long prHeaderId, Long approvalId) throws ServiceException {
		// TODO Auto-generated method stub
			String query = " select * from lt_pr_approval "
			 		+" where PR_HEADER_ID = ? "
					 +" AND (APPROVAL_ID = ? OR DELEGATION_ID = ? ) "
					 +" AND APPROVAL_LEVEL = CURRENT_APPROVAL_LEVEL "
					 +" AND STATUS = 'PENDING' ";
			 
			List<LtPrApproval> list=   jdbcTemplate.query(query, new Object[]{prHeaderId, approvalId,approvalId}, 
				 new BeanPropertyRowMapper<LtPrApproval>(LtPrApproval.class)); 

			if(list.size() > 0)
				return true;
			else
				return false;
		}

	@Override
	public String checkforApprovals(Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		String query = " SELECT ma.*  "
				+" FROM LT_MAST_MODULE_APPROVALS ma "
				+" WHERE ma.DIVISION_ID = "
				+"  (SELECT e.DIVISION_ID FROM LT_MAST_EMPLOYEES e,LT_PR_HEADERS rah WHERE e.EMPLOYEE_ID = rah.initiator_id and rah.PR_header_id = ? ) ";
				 
		List<LtMastModuleApprovals> list=   jdbcTemplate.query(query, new Object[]{prHeaderId}, 
					 new BeanPropertyRowMapper<LtMastModuleApprovals>(LtMastModuleApprovals.class)); 
		
		if(list.isEmpty())
			return "null";
		else
		return "present";
	}
	
	@Override
	public boolean submitForApproval(Date date, Long prHeaderId, String status, Object object)
			throws ServiceException {
		String query ="UPDATE LT_PR_HEADERS SET Status = ? , Last_update_date =? WHERE PR_HEADER_ID = ? ";
		
		int res=jdbcTemplate.update(query,status ,new Date(), prHeaderId);
		
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean upDateStatus(Long prHeaderId, String status, String currentApprovalLavel)
			throws ServiceException {
		int res=0;
		if(currentApprovalLavel!=null)
		{
			//String query = env.getProperty("upDateStatus1");
			String query = " UPDATE lt_pr_approval SET STATUS=?,LAST_UPDATE_DATE=? " + 
					" WHERE PR_HEADER_ID=? AND APPROVAL_LEVEL =? AND STATUS <> ? ";
			 res=jdbcTemplate.update(query,
				        status,new Date(),prHeaderId,currentApprovalLavel,APPROVED);
		}
		else
		{
			//String query = env.getProperty("upDateStatus2");
			String query =" UPDATE lt_pr_approval SET " + 
					"STATUS=? ,LAST_UPDATE_DATE= ?, CURRENT_APPROVAL_LEVEL=? WHERE PR_HEADER_ID=?";
			
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLavel,prHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean chkForApprovers(Long prHeaderId) throws ServiceException {
		String query = "SELECT ea.*, \n" +
	               "       COALESCE(CONCAT( \n" +
	               "           COALESCE(CONCAT(em.FIRST_NAME, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')'), ''), \n" +
	               "           COALESCE(ea.DELEGATION_ID, CONCAT(' (', CONCAT(emm.FIRST_NAME, ' ', emm.LAST_NAME, ' (', emm.EMPLOYEE_NUMBER, ')')), '') \n" +
	               "       )) AS approval_Name, \n" +
	               "       CASE \n" +
	               "           WHEN ea.MODULE_APPROVAL_ID = 0 THEN 'Invitor' \n" +
	               "           ELSE ema.approval_role_name \n" +
	               "       END AS approval_level_name \n" +
	               "FROM lt_pr_approval ea \n" +
	               "LEFT JOIN lt_mast_employees em ON ea.APPROVAL_ID = em.EMPLOYEE_ID \n" +
	               "LEFT JOIN lt_mast_employees emm ON ea.DELEGATION_ID = emm.EMPLOYEE_ID \n" +
	               "LEFT JOIN lt_mast_module_approvals ema ON ea.APPROVAL_LEVEL = ema.APPROVAL_LEVEL \n" +
	               "    AND ea.MODULE_APPROVAL_ID = ema.MODULE_APPROVAL_ID \n" +
	               "WHERE ea.pr_header_id = ? \n" +
	               "ORDER BY ea.APPROVAL_LEVEL";


		List<LtPrApproval> ltPrApprovalList = jdbcTemplate.query(query, new Object[] {prHeaderId},
				new RowMapper<LtPrApproval>() {
					public LtPrApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtPrApproval ltPrApproval = new LtPrApproval();
						
						ltPrApproval.setPrApprovalId(rs.getLong("PR_APPROVAL_ID"));
						ltPrApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltPrApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltPrApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltPrApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						
						ltPrApproval.setPrHeaderId(rs.getLong("PR_HEADER_ID"));
						
						return ltPrApproval;
					}
				});
		
		
		if(ltPrApprovalList.size() > 0){
			return true;
		}else
			return false;
	}

	@Override
	public List<LtPrHeaders> getInprocessPrList(String inprogressStr) throws ServiceException {
		String query = " SELECT  inv.*, apr.APPROVAL_LEVEL  " + 
				" FROM LT_PR_HEADERS inv, LT_PR_APPROVAL apr " + 
				" WHERE apr.PR_HEADER_ID = inv.PR_HEADER_ID" + 
				" AND inv.Status= 'INPROCESS' " + 
				" AND ((apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'APPROVED') " + 
				" OR (apr.CURRENT_APPROVAL_LEVEL IS NULL AND apr.STATUS = 'NO_ACTION') " + 
				" OR  (apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'NO_ACTION'))";
		List<LtPrHeaders> list=   jdbcTemplate.query(query, new Object[]{  }, 
			 new BeanPropertyRowMapper<LtPrHeaders>(LtPrHeaders.class)); 
		return list;
	}

	@Override
	public PrApproval getApprovalLevel(Long prHeaderId) throws ServiceException {
		//String query = env.getProperty("getApprovalLevel");
		
				String query = "select   MIN( APPROVAL_LEVEL) as MIN_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID " + 
						" from LT_PR_APPROVAL where PR_HEADER_ID = ? " + 
						" group by APPROVAL_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID order by MIN_LEVEL";
				
				List<PrApproval> prApprovalList = jdbcTemplate.query(query, new Object[] {prHeaderId},
						
						new RowMapper<PrApproval>() {
							public PrApproval mapRow(ResultSet rs, int arg1) throws SQLException {

								PrApproval prApproval = new PrApproval();

								prApproval.setApprovalLevel(rs.getString("MIN_LEVEL"));
								prApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
								prApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
								
								
								return prApproval;
							}
						});
				if(prApprovalList.size()>0)
					return prApprovalList.get(0); 
				else 
					return null;
			}

	@Override
	public String getNextApprovalLevel(Long prHeaderId, String currentApprovalLavel) throws ServiceException {
		String query = "select MIN(APPROVAL_LEVEL) AS  CURRENT_APPROVAL_LEVEL " + 
				" from LT_PR_APPROVAL where PR_HEADER_ID = ? AND APPROVAL_LEVEL > ? AND STATUS <> ? ";
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { prHeaderId, currentApprovalLavel,APPROVED}, String.class);

		return nextlavel;
	}

	@Override
	public void updateCurrentApprovalLevel(Long prHeaderId, String currentApprovalLavel) throws ServiceException {
		String query = "UPDATE LT_PR_APPROVAL SET " + 
				"CURRENT_APPROVAL_LEVEL = ?  WHERE PR_HEADER_ID=? ";
		
		int res=jdbcTemplate.update(query,
				currentApprovalLavel, prHeaderId );
		
	}
		

}
