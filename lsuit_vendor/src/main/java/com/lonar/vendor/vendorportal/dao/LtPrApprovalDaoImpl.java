package com.lonar.vendor.vendorportal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtPrApproval;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/invoiceApprovalQueries.properties", ignoreResourceNotFound = true)
public class LtPrApprovalDaoImpl implements LtPrApprovalDao{
	
	@Autowired
	private Environment env;

	@PersistenceContext(name = "em")
	private EntityManager em;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	} 

	@Override
	public List<LtPrApprovalHistory> getPrApprovalHistoryByPrHeaderId(Long prHeaderId) throws ServiceException {
		// TODO Auto-generated method stub
		String query = "SELECT ih.PR_APPROVAL_HISTORY_ID, \n" +
	               "       ih.PR_APPROVAL_ID, \n" +
	               "	   ih.NOTE, \n" +
	               "       ih.LAST_UPDATE_DATE, \n" +
	               "       ih.PR_HEADER_ID, \n" +
	               "       ih.EMPLOYEE_ID, \n" +
	               "       ih.REMARK, \n" +
	               "       get_comn_value_name('PR_STATUS', ih.status) AS STATUS, \n" +
	               "           CONCAT(cmv.value_name, ' ', em.first_name, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')') \n" +
	               "       approval_Name \n" +
	               "FROM lt_pr_approval_history ih \n" +
	               "LEFT JOIN LT_PR_HEADERS ven ON ih.PR_HEADER_ID = ven.PR_HEADER_ID \n" +
	               "LEFT JOIN LT_MAST_EMPLOYEES em ON ih.EMPLOYEE_ID = em.EMPLOYEE_ID \n" +
	               "LEFT JOIN LT_MAST_COMN_MASTER_VALUES cmv ON em.title = cmv.value_code \n" +
	               "WHERE ih.PR_HEADER_ID = ? \n" +
	               "ORDER BY ih.PR_APPROVAL_HISTORY_ID DESC";

		
		List<LtPrApprovalHistory> list=   jdbcTemplate.query(query, new Object[]{ prHeaderId }, 
				 new BeanPropertyRowMapper<LtPrApprovalHistory>(LtPrApprovalHistory.class)); 
		 
		return list;
	}
	
	
	@Override
	public List<PrApproval> chkPrEmpApproval(Long employeeId, Long invoiceId) throws ServiceException {
		String query =   " SELECT * FROM LT_PR_APPROVAL inv  "+
				" WHERE inv.APPROVAL_ID = ? and inv.PR_HEADER_ID = ?  and inv.STATUS = ? ";

		List<PrApproval> prApprovalList = jdbcTemplate.query(query, new Object[]
				{employeeId,invoiceId,"APPROVED"},
				new RowMapper<PrApproval>() {
					public PrApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						PrApproval prApproval = new PrApproval();
						
						prApproval.setPrApprovalId(rs.getLong("PR_APPROVAL_ID"));
						
						return prApproval;
					}
				});
	
		return prApprovalList;
	}

	@Override
	public boolean updateStatusApproval(LtPrApprovalHistory approvalHistory) throws ServiceException {
		// TODO Auto-generated method stub
		int res=0;	
		if(approvalHistory.getEmployeeId()!=null ) 
		{
			String query = " UPDATE lt_pr_approval "
			+" SET  Status= ? , LAST_UPDATE_DATE = ?,LAST_UPDATED_BY=? " 
			+" WHERE PR_HEADER_ID = ?  AND (APPROVAL_ID = ? OR DELEGATION_ID = ?) ";
			
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getPrApprovalId(),
					approvalHistory.getPrHeaderId(), approvalHistory.getEmployeeId(),approvalHistory.getEmployeeId());
		}
			
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean updateAllStatusApproval(LtPrApprovalHistory approvalHistory) throws ServiceException {
		// TODO Auto-generated method stub
		String query = "UPDATE lt_pr_approval "
				+" SET  Status= ? ,LAST_UPDATE_DATE=?,LAST_UPDATED_BY=? "
				+" WHERE PR_HEADER_ID = ?  AND CURRENT_APPROVAL_LEVEL =  APPROVAL_LEVEL ";
				
				int res=0;	
					res = jdbcTemplate.update(query,
								approvalHistory.getStatus(),new Date(),approvalHistory.getPrApprovalId(),
								approvalHistory.getPrHeaderId());
				if(res!=0)
					return true;
				else
					return false;
	}
	
	@Override
	public boolean submitForApproval(Date submissionDate, Long prHeaderId, String status, Date approvedDate,
			Long lastLogin) throws ServiceException {
		int res=0;
		if(submissionDate!=null)
		{
			String query = env.getProperty("submitPrForApproval1");
			res=jdbcTemplate.update(query,status,new Date(),lastLogin,prHeaderId);
			
		}
		else
		{
			String query = env.getProperty("submitPrForApproval2");
			 res=jdbcTemplate.update(query,status,new Date(),lastLogin,prHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public String getCurrLevelByPrApprovalId(Long prApprovalId) throws ServiceException {
		// TODO Auto-generated method stub
		String query = env.getProperty("getCurrLevelByPrApprovalId");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { prApprovalId}, String.class);

		return nextlavel;
	}

	@Override
	public boolean upDateStatus(Long prHeaderId, String status, String currentApprovalLevel) throws ServiceException {
		// TODO Auto-generated method stub
		int res=0;
		
		if(currentApprovalLevel!=null)
		{
			String query = env.getProperty("upDatePrStatus1");
			 res=jdbcTemplate.update(query,
				        status,new Date(),prHeaderId,currentApprovalLevel,"APPROVED");
		}
		else
		{
			String query = env.getProperty("upDatePrStatus2");
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLevel,prHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public LtPrApproval getPrApproval(Long prHeaderId, Long apprId) throws ServiceException {
		// TODO Auto-generated method stub
		String query = env.getProperty("getPrApprovalByApprovalPrIdAndApproverId");
		List<LtPrApproval> list=   jdbcTemplate.query(query, new Object[]{ prHeaderId,apprId }, 
				 new BeanPropertyRowMapper<LtPrApproval>(LtPrApproval.class)); 
		if(list.isEmpty())
			return null;
		else 
		 return list.get(0);
	}



}
