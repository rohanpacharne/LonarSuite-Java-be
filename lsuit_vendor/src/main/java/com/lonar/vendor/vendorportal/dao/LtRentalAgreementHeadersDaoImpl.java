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

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.InvoiceApproval;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtInvoiceHeaders;
import com.lonar.vendor.vendorportal.model.LtMastModuleApprovals;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtRentalAgreementHeadersRepository;

@Repository
@PropertySource(value = "classpath:queries/invoiceHeaderQueries.properties", ignoreResourceNotFound = true)
public class LtRentalAgreementHeadersDaoImpl implements LtRentalAgreementHeadersDao,CodeMaster{
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtRentalAgreementHeadersRepository ltRentalAgreementHeadersRepository;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<LtRentalAgreementHeaders> getLtRentalAgreementHeadersDataTable(LtRentalAgreementHeaders input,Long companyId) throws ServiceException {
		
		String query = env.getProperty("getLtRentalAgreementHeadersDatatable");
		
		String agreementNum=null;
		   if(input.getAgreementNumber()!=null && !input.getAgreementNumber().equals(""))
		   {agreementNum="%"+input.getAgreementNumber().trim().toUpperCase() + "%";}
		   
		   String prpoertyCity=null;
		   if(input.getPropertyCity()!=null && !input.getPropertyCity().equals(""))
		   {prpoertyCity="%"+input.getPropertyCity().trim().toUpperCase()+"%";}
		   
		   String vendorName=null;
		   if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		   {vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";}
		   
		   
			if(input.getFromDate() == null)
			{
				input.setFromDate(null);
			}
			
			if(input.getToDate() == null)
			{
				input.setToDate(null);
			}
			
		   String rentFreq=null;
		   if(input.getRentFrequency()!=null &&  !input.getRentFrequency().equals("")) 
		   {rentFreq="%"+input.getRentFrequency().trim().trim().toUpperCase()+"%";}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals(""))
		   {status="%"+input.getStatus()+"%";}
			
		   
			if(input.getColumnNo()==0)
			{
				input.setColumnNo(1);
			}
			
		
				List<LtRentalAgreementHeaders> list = (List<LtRentalAgreementHeaders>) 
						jdbcTemplate.query(query ,new Object[]{companyId,agreementNum,
								prpoertyCity,vendorName,input.getFromDate(),input.getToDate(),rentFreq,status,
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								input.getColumnNo(),input.getColumnNo(),
								
								input.getStart()+input.getLength(),input.getStart()+1},
					 new  BeanPropertyRowMapper<LtRentalAgreementHeaders>(LtRentalAgreementHeaders.class));
					
				return list;
	}

	@Override
	public Long getLtRentalAgreementHeadersCount(LtRentalAgreementHeaders input,Long companyId) throws ServiceException {
		// TODO Auto-generated method stub
		String query = env.getProperty("getLtRentalAgreementHeadersCount");
		
		String agreementNum=null;
		   if(input.getAgreementNumber()!=null && !input.getAgreementNumber().equals(""))
		   {agreementNum="%"+input.getAgreementNumber().trim().toUpperCase() + "%";}
		   
		   String prpoertyCity=null;
		   if(input.getPropertyCity()!=null && !input.getPropertyCity().equals(""))
		   {prpoertyCity="%"+input.getPropertyCity().trim().toUpperCase()+"%";}
		   
		   String vendorName=null;
		   if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		   {vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";}
		   
		   
			if(input.getFromDate() == null)
			{
				input.setFromDate(null);
			}
			
			if(input.getToDate() == null)
			{
				input.setToDate(null);
			}
			
		   String rentFreq=null;
		   if(input.getRentFrequency()!=null &&  !input.getRentFrequency().equals("")) 
		   {rentFreq="%"+input.getRentFrequency().trim().trim().toUpperCase()+"%";}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals(""))
		   {status="%"+input.getStatus()+"%";}
			
		   
			if(input.getColumnNo()==0)
			{
				input.setColumnNo(1);
			}
			
			String count  = (String)getJdbcTemplate().queryForObject(
					query, new Object[] {companyId,agreementNum,
							prpoertyCity,vendorName,input.getFromDate(),input.getToDate(),rentFreq,status}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public Long save(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException {
		System.out.println("company Id = "+ltRentalAgreementHeaders.getCompanyId());
		ltRentalAgreementHeaders = ltRentalAgreementHeadersRepository.save(ltRentalAgreementHeaders);
		if(ltRentalAgreementHeaders.getAgreementHeaderId()!=null) {
			
			return ltRentalAgreementHeaders.getAgreementHeaderId();
		}
		else
		return null;
	}

	@Override
	public LtRentalAgreementHeaders getRentalAgreementById(Long id) throws ServiceException {
		System.out.println("id = "+id);

		String query = env.getProperty("getRentalAgreementHeaderById");
		System.out.println("query = "+query);
		List<LtRentalAgreementHeaders> list=   jdbcTemplate.query(query, new Object[]{id  }, 
				 new BeanPropertyRowMapper<LtRentalAgreementHeaders>(LtRentalAgreementHeaders.class)); 
		System.out.println("list = "+list);
		if(list.isEmpty())
			return null;
		else
		 return list.get(0);
	}

	@Override
	public boolean update(LtRentalAgreementHeaders ltRentalAgreementHeaders) throws ServiceException {
		System.out.println("Before save = "+ltRentalAgreementHeaders);
		ltRentalAgreementHeaders = ltRentalAgreementHeadersRepository.save(ltRentalAgreementHeaders);
		System.out.println("After save = "+ltRentalAgreementHeaders);
		if(ltRentalAgreementHeaders.getAgreementHeaderId()!=null) {
			
			return true;
		}
		else
		return false;
	}

	@Override
	public LtRentalAgreementHeaders getRentalAgreementStatusById(Long id) throws ServiceException {
		try{
		String query = env.getProperty("getRentalAgreementStatusById");
		LtRentalAgreementHeaders status=   jdbcTemplate.queryForObject(query, new Object[]{id}, 
				new BeanPropertyRowMapper<LtRentalAgreementHeaders>(LtRentalAgreementHeaders.class)); 
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
	public boolean delete(Long agreementHeaderId) throws ServiceException {
		String query = env.getProperty("deleteRentalAgreementHeaderById");
		
		int res=jdbcTemplate.update(query, agreementHeaderId);
		
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean checkStatusIsPending(Long agreementHeaderId, Long approvalId) throws ServiceException {
		String query = " select * from lt_rental_agreement_approval "
		 		+" where AGREEMENT_HEADER_ID = ? "
				 +" AND (APPROVAL_ID = ? OR DELEGATION_ID = ? ) "
				 +" AND APPROVAL_LEVEL = CURRENT_APPROVAL_LEVEL "
				 +" AND STATUS = 'PENDING' ";
		 
		List<LtRentalAgreementApproval> list=   jdbcTemplate.query(query, new Object[]{agreementHeaderId, approvalId,approvalId}, 
			 new BeanPropertyRowMapper<LtRentalAgreementApproval>(LtRentalAgreementApproval.class)); 

		if(list.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public String checkforApprovals(Long agreementHeaderId) throws ServiceException {
		String query = " SELECT ma.*  "
				+" FROM LT_MAST_MODULE_APPROVALS ma "
				+" WHERE ma.DIVISION_ID = "
				+"  (SELECT e.DIVISION_ID FROM LT_MAST_EMPLOYEES e,LT_RENTAL_AGREEMENT_HEADERS rah WHERE e.EMPLOYEE_ID = rah.initiator_id and rah.agreement_header_id = ? ) ";
				 
		List<LtMastModuleApprovals> list=   jdbcTemplate.query(query, new Object[]{agreementHeaderId}, 
					 new BeanPropertyRowMapper<LtMastModuleApprovals>(LtMastModuleApprovals.class)); 
		
		if(list.isEmpty())
			return "null";
		else
		return "present";
	}

	@Override
	public boolean submitForApproval(Date date, Long agreementHeaderId, String status, Object object)
			throws ServiceException {
		String query ="UPDATE LT_RENTAL_AGREEMENT_HEADERS SET Status = ? , Last_update_date =? WHERE AGREEMENT_HEADER_ID = ? ";
		
		int res=jdbcTemplate.update(query,status ,new Date(), agreementHeaderId);
		
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean upDateStatus(Long agreementHeaderId, String status, String currentApprovalLavel)
			throws ServiceException {
		int res=0;
		if(currentApprovalLavel!=null)
		{
			//String query = env.getProperty("upDateStatus1");
			String query = " UPDATE lt_rental_agreement_approval SET STATUS=?,LAST_UPDATE_DATE=? " + 
					" WHERE AGREEMENT_HEADER_ID=? AND APPROVAL_LEVEL =? AND STATUS <> ? ";
			 res=jdbcTemplate.update(query,
				        status,new Date(),agreementHeaderId,currentApprovalLavel,RA_APPROVED);
		}
		else
		{
			//String query = env.getProperty("upDateStatus2");
			String query =" UPDATE lt_rental_agreement_approval SET " + 
					"STATUS=? ,LAST_UPDATE_DATE= ?, CURRENT_APPROVAL_LEVEL=? WHERE AGREEMENT_HEADER_ID=?";
			
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLavel,agreementHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean chkForApprovers(Long agreementHeaderId) throws ServiceException {
		String query = "SELECT ea.*, \n" +
	               "       COALESCE(CONCAT( \n" +
	               "           COALESCE(CONCAT(em.FIRST_NAME, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')'), ''), \n" +
	               "           COALESCE(ea.DELEGATION_ID, CONCAT(' (', CONCAT(emm.FIRST_NAME, ' ', emm.LAST_NAME, ' (', emm.EMPLOYEE_NUMBER, ')')), '') \n" +
	               "       )) AS approval_Name, \n" +
	               "       CASE \n" +
	               "           WHEN ea.MODULE_APPROVAL_ID = 0 THEN 'Invitor' \n" +
	               "           ELSE ema.approval_role_name \n" +
	               "       END AS approval_level_name \n" +
	               "FROM lt_rental_agreement_approval ea \n" +
	               "LEFT JOIN lt_mast_employees em ON ea.APPROVAL_ID = em.EMPLOYEE_ID \n" +
	               "LEFT JOIN lt_mast_employees emm ON ea.DELEGATION_ID = emm.EMPLOYEE_ID \n" +
	               "LEFT JOIN lt_mast_module_approvals ema ON ea.APPROVAL_LEVEL = ema.APPROVAL_LEVEL \n" +
	               "    AND ea.MODULE_APPROVAL_ID = ema.MODULE_APPROVAL_ID \n" +
	               "WHERE ea.agreement_header_id = ? \n" +
	               "ORDER BY ea.APPROVAL_LEVEL";


		List<LtRentalAgreementApproval> ltRentalAgreementApprovalList = jdbcTemplate.query(query, new Object[] {agreementHeaderId},
				new RowMapper<LtRentalAgreementApproval>() {
					public LtRentalAgreementApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtRentalAgreementApproval ltRentalAgreementApproval = new LtRentalAgreementApproval();
						
						ltRentalAgreementApproval.setAgreementApprovalId(rs.getLong("AGREEMENT_APPROVAL_ID"));
						ltRentalAgreementApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						ltRentalAgreementApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						ltRentalAgreementApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						ltRentalAgreementApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						
						ltRentalAgreementApproval.setAgreementHeaderId(rs.getLong("AGREEMENT_HEADER_ID"));
						
						return ltRentalAgreementApproval;
					}
				});
		
		
		if(ltRentalAgreementApprovalList.size() > 0){
			return true;
		}else
			return false;
	}

	@Override
	public List<LtRentalAgreementHeaders> getInprocessAgreementList(String inprogressStr) throws ServiceException {
		String query = " SELECT  inv.*, apr.APPROVAL_LEVEL  " + 
				" FROM LT_RENTAL_AGREEMENT_HEADERS inv, lt_rental_agreement_approval apr " + 
				" WHERE apr.AGREEMENT_HEADER_ID = inv.AGREEMENT_HEADER_ID" + 
				" AND inv.Status= 'RA_INPROCESS' " + 
				" AND ((apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'RA_APPROVED') " + 
				" OR (apr.CURRENT_APPROVAL_LEVEL IS NULL AND apr.STATUS = 'NO_ACTION') " + 
				" OR  (apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'NO_ACTION'))";
		List<LtRentalAgreementHeaders> list=   jdbcTemplate.query(query, new Object[]{  }, 
			 new BeanPropertyRowMapper<LtRentalAgreementHeaders>(LtRentalAgreementHeaders.class)); 
		return list;
	}

	@Override
	public LtRentalAgreementApproval getApprovalLevel(Long agreementHeaderId) throws ServiceException {
		String query = "select   MIN( APPROVAL_LEVEL) as MIN_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID " + 
				" from lt_rental_agreement_approval where AGREEMENT_HEADER_ID = ? " + 
				" group by APPROVAL_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID order by MIN_LEVEL";
		
		List<LtRentalAgreementApproval> agreementApprovalList = jdbcTemplate.query(query, new Object[] {agreementHeaderId},
				
				new RowMapper<LtRentalAgreementApproval>() {
					public LtRentalAgreementApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						LtRentalAgreementApproval agreementApproval = new LtRentalAgreementApproval();

						agreementApproval.setApprovalLevel(rs.getString("MIN_LEVEL"));
						agreementApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						agreementApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						
						
						return agreementApproval;
					}
				});
		if(agreementApprovalList.size()>0)
			return agreementApprovalList.get(0); 
		else 
			return null;
	}

	@Override
	public List<LtRentalAgreementApproval> getApprovalList(Long agreementHeaderId, String currentApprovalLevel)
			throws ServiceException {
		String query = " SELECT a.*,'N' as APPROVED_BY_ANYONE " + 
				" FROM lt_rental_agreement_approval a left outer join lt_mast_module_approvals b " + 
				" on a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID  " + 
				" WHERE a.AGREEMENT_HEADER_ID = ? AND a.APPROVAL_LEVEL = ifnull(?,a.APPROVAL_LEVEL) ORDER BY a.APPROVAL_LEVEL ASC ";
		List<LtRentalAgreementApproval> list=   jdbcTemplate.query(query, new Object[]{ agreementHeaderId,currentApprovalLevel}, 
				 new BeanPropertyRowMapper<LtRentalAgreementApproval>(LtRentalAgreementApproval.class));
			return list;
	}

	@Override
	public String getNextApprovalLevel(Long expHeaderId, String currentApprovalLevel) throws Exception {
		String query = env.getProperty("getNextApprovalLevelAgr");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { expHeaderId, currentApprovalLevel}, String.class);

		return nextlavel;
	}

	@Override
	public void updateCurrentApprovalLevel(Long agreementHeaderId, String currentApprovalLavel) throws ServiceException {
		//String query = env.getProperty("updateCurrentApprovalLevel");
				String query = "UPDATE lt_rental_agreement_approval SET " + 
						"CURRENT_APPROVAL_LEVEL = ?  WHERE AGREEMENT_HEADER_ID=? ";
				
				int res=jdbcTemplate.update(query,
						currentApprovalLavel, agreementHeaderId );
		
	}
	
	

}
