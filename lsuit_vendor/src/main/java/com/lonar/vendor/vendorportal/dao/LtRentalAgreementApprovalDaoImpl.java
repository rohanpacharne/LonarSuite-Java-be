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

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/invoiceApprovalQueries.properties", ignoreResourceNotFound = true)
public class LtRentalAgreementApprovalDaoImpl implements LtRentalAgreementApprovalDao, CodeMaster {
	
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
	public boolean updateStatusApproval(LtRentalAgrApprovalHistory approvalHistory) throws ServiceException {
		int res=0;	
		if(approvalHistory.getEmployeeId()!=null ) 
		{
			String query = " UPDATE lt_rental_agreement_approval "
			+" SET  Status= ? , LAST_UPDATE_DATE = ?,LAST_UPDATED_BY=? " 
			+" WHERE AGREEMENT_HEADER_ID = ?  AND (APPROVAL_ID = ? OR DELEGATION_ID = ?) ";
			
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getAgreementApprovalId(),
					approvalHistory.getAgreementHeaderId(), approvalHistory.getEmployeeId(),approvalHistory.getEmployeeId());
		}
			
		if(res!=0)
			return true;
		else
			return false;
	
	}

	@Override
	public List<LtRentalAgrApprovalHistory> getRentalAgrApprovalHistoryByAgreementHeaderId(Long agreementHeaderId)
			throws ServiceException {
		String query = "SELECT ih.AGREEMENT_APPROVAL_HISTORY_ID, \n" +
	               "       ih.AGREEMENT_APPROVAL_ID, \n" +
	               "	   ih.NOTE, \n" +
	               "       ih.LAST_UPDATE_DATE, \n" +
	               "       ih.AGREEMENT_HEADER_ID, \n" +
	               "       ih.EMPLOYEE_ID, \n" +
	               "       ih.REMARK, \n" +
	               "       get_comn_value_name('RENTAL_AGREEMENT_STATUES', ih.status) AS STATUS, \n" +
	               "           CONCAT(cmv.value_name, ' ', em.first_name, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')') \n" +
	               "       approval_Name \n" +
	               "FROM lt_rental_agr_approval_history ih \n" +
	               "LEFT JOIN LT_RENTAL_AGREEMENT_HEADERS ven ON ih.AGREEMENT_HEADER_ID = ven.AGREEMENT_HEADER_ID \n" +
	               "LEFT JOIN LT_MAST_EMPLOYEES em ON ih.EMPLOYEE_ID = em.EMPLOYEE_ID \n" +
	               "LEFT JOIN LT_MAST_COMN_MASTER_VALUES cmv ON em.title = cmv.value_code \n" +
	               "WHERE ih.AGREEMENT_HEADER_ID = ? \n" +
	               "ORDER BY ih.AGREEMENT_APPROVAL_HISTORY_ID DESC";

		
		List<LtRentalAgrApprovalHistory> list=   jdbcTemplate.query(query, new Object[]{ agreementHeaderId }, 
				 new BeanPropertyRowMapper<LtRentalAgrApprovalHistory>(LtRentalAgrApprovalHistory.class)); 
		 
		return list;
	}

	@Override
	public LtRentalAgreementApproval getRentalAgreementApproval(Long agreementHeaderId, Long apprId)
			throws ServiceException {
		String query = env.getProperty("getAgreementApprovalByApprovalAgrIdAndApproverId");
		List<LtRentalAgreementApproval> list=   jdbcTemplate.query(query, new Object[]{ agreementHeaderId,apprId }, 
				 new BeanPropertyRowMapper<LtRentalAgreementApproval>(LtRentalAgreementApproval.class)); 
		if(list.isEmpty())
			return null;
		else 
		 return list.get(0);
	}

	@Override
	public boolean updateAllStatusApproval(LtRentalAgrApprovalHistory approvalHistory) throws ServiceException {
		//String query = env.getProperty("updateAllStatusApproval");
		
				String query = "UPDATE lt_rental_agreement_approval "
				+" SET  Status= ? ,LAST_UPDATE_DATE=?,LAST_UPDATED_BY=? "
				+" WHERE AGREEMENT_HEADER_ID = ?  AND CURRENT_APPROVAL_LEVEL =  APPROVAL_LEVEL ";
				
				int res=0;	
					res = jdbcTemplate.update(query,
								approvalHistory.getStatus(),new Date(),approvalHistory.getAgreementApprovalId(),
								approvalHistory.getAgreementHeaderId());
				if(res!=0)
					return true;
				else
					return false;
	}

	@Override
	public List<LtRentalAgreementApproval> chkAgreementEmpApproval(Long employeeId, Long agreementId)
			throws ServiceException {
		String query =   " SELECT * FROM lt_rental_agreement_approval inv  "+
				" WHERE inv.APPROVAL_ID = ? and inv.AGREEMENT_HEADER_ID = ?  and inv.STATUS = ? ";

		List<LtRentalAgreementApproval> agreementApprovalList = jdbcTemplate.query(query, new Object[]
				{employeeId,agreementId,"APPROVED"},
				new RowMapper<LtRentalAgreementApproval>() {
					public LtRentalAgreementApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtRentalAgreementApproval ltRentalAgreementApproval = new LtRentalAgreementApproval();
						
						ltRentalAgreementApproval.setAgreementApprovalId(rs.getLong("AGREEMENT_APPROVAL_ID"));
						
						return ltRentalAgreementApproval;
					}
				});
	
		return agreementApprovalList;
	}

	@Override
	public boolean submitForApproval(Date submissionDate, Long agreementHeaderId, String status, Date approvedDate,
			Long lastLogin) throws ServiceException {
		int res=0;
		if(submissionDate!=null)
		{
			String query = env.getProperty("submitRentalAgreementForApproval1");
			res=jdbcTemplate.update(query,status,new Date(),lastLogin,agreementHeaderId);
			
		}
		else
		{
			String query = env.getProperty("submitRentalAgreementForApproval2");
			 res=jdbcTemplate.update(query,status,new Date(),lastLogin,agreementHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public String getCurrLevelByAgreementApprovalId(Long agreementApprovalId) throws ServiceException {
		String query = env.getProperty("getCurrLevelByAgreementApprovalId");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { agreementApprovalId}, String.class);

		return nextlavel;
	}

	@Override
	public boolean upDateStatus(Long agreementHeaderId, String status, String currentApprovalLevel) throws ServiceException {
		int res=0;
		
		if(currentApprovalLevel!=null)
		{
			String query = env.getProperty("upDateAgreementStatus1");
			 res=jdbcTemplate.update(query,
				        status,new Date(),agreementHeaderId,currentApprovalLevel,"APPROVED");
		}
		else
		{
			String query = env.getProperty("upDateAgreementStatus2");
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLevel,agreementHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	

}
