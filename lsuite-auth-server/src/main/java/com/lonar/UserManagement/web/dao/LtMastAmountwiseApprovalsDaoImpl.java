package com.lonar.UserManagement.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lonar.UserManagement.web.model.LtMastAmountwiseApprovals;
import com.lonar.UserManagement.web.model.LtMastModules;
import com.lonar.UserManagement.web.model.LtModuleApprovals;

@Repository
@PropertySource(value = "classpath:queries/amountwiseApprovalsQueries.properties", ignoreResourceNotFound = true)
public class LtMastAmountwiseApprovalsDaoImpl implements LtMastAmountwiseApprovalsDao{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	@Override
	public Long getLtMastUsersCount(LtMastAmountwiseApprovals input, Long companyId) {

		String query = env.getProperty("getModuleAmountwiseApprovalsCount");
		   String transactionCode=null;
		   if(input.getTransactionCode()!=null)
		   {transactionCode="%"+input.getTransactionCode().toUpperCase()+"%";}
		   
		   String transactionType=null;
		   if(input.getTransactionType()!=null)
		   {transactionType="%"+input.getTransactionType().trim().toUpperCase()+"%";}
		   
		   String approverId=null;
		   if(input.getApproverId()!=null)
		   {approverId="%"+input.getApproverId()+"%";}
		   
			if(input.getFromAmount() == null || input.getFromAmount().equals(""))
			{
				input.setFromAmount(null);
			}
			if(input.getToAmount() == null || input.getToAmount().equals(""))
			{
				input.setToAmount(null);
			}
		
		   Long count  = getJdbcTemplate().queryForObject(
					query, new Object[] {companyId,transactionCode,transactionType,
							input.getFromAmount(),input.getToAmount(),approverId,
							}, Long.class);
		
			return count;
	}
	

	@Override
	public List<LtMastAmountwiseApprovals> getLtMastUsersDatatableRecords(LtMastAmountwiseApprovals input,
			Long companyId) {
		        if(input.getColumnNo()==2 && "desc".equals(input.getSort())){ input.setColumnNo(12); }
		        if(input.getColumnNo()==3 && "desc".equals(input.getSort())){ input.setColumnNo(13); }
		        if(input.getColumnNo()==4 && "desc".equals(input.getSort())){ input.setColumnNo(14); }
		        if(input.getColumnNo()==5 && "desc".equals(input.getSort())){ input.setColumnNo(15); }
		        if(input.getColumnNo()==6 && "desc".equals(input.getSort())){ input.setColumnNo(16); }
		        if(input.getColumnNo()==7 && "desc".equals(input.getSort())){ input.setColumnNo(17); }
		        if(input.getColumnNo()==8 && "desc".equals(input.getSort())){ input.setColumnNo(18); }
		        if(input.getColumnNo()==9 && "desc".equals(input.getSort())){ input.setColumnNo(19); }
		        if(input.getColumnNo()==1 && "asc".equals(input.getSort())){ input.setColumnNo(20); }
		        if(input.getColumnNo()==0){ input.setColumnNo(0); }
		        String query = env.getProperty("getModuleAmountwiseApprovals");

		        String transactionCode = (input.getTransactionCode() != null && !input.getTransactionCode().isEmpty()) 
		            ? "%" + input.getTransactionCode().trim().toUpperCase() + "%" : null;

		        String transactionType = (input.getTransactionType() != null && !input.getTransactionType().isEmpty()) 
		            ? "%" + input.getTransactionType().trim().toUpperCase() + "%" : null;

		        Double fromAmount = (input.getFromAmount() != null) ? input.getFromAmount() : null;
		        Double toAmount = (input.getToAmount() != null) ? input.getToAmount() : null;
		        Long approverId = (input.getApproverId() != null) ? input.getApproverId() : null;

		        // Debug query parameters
		        System.out.println("Executing Query: " + query);
		        System.out.println("Parameters: companyId=" + companyId + ", transactionCode=" + transactionCode + 
		                           ", transactionType=" + transactionType + ", fromAmount=" + fromAmount + 
		                           ", toAmount=" + toAmount + ", approverId=" + approverId +
		                           ", startDate=" + input.getStartDate() + ", endDate=" + input.getEndDate() +
		                           ", columnNo=" + input.getColumnNo() + ", limit=" + (input.getLength() + input.getStart()) +
		                           ", offset=" + (input.getStart() + 1));

		        // Step 3: Execute query and map results
		        List<LtMastAmountwiseApprovals> result = jdbcTemplate.query(
		            query,
		            new Object[]{companyId, transactionCode, transactionType, fromAmount, toAmount, approverId,
		                         input.getStartDate(), input.getEndDate(), input.getColumnNo(), input.getColumnNo(),
		                         input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
		                         input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),input.getStart() + 1,
		                         input.getLength() + input.getStart()},
		            new BeanPropertyRowMapper<>(LtMastAmountwiseApprovals.class)
		        );

		        // Step 4: Debug output
		        System.out.println("Result Size: " + (result != null ? result.size() : "NULL"));
		        
		        return result != null ? result : Collections.emptyList();
		    }
	@Override
	public List<LtMastAmountwiseApprovals> findByTransactionCode(String transactionCode, Long companyId) {
		String sqlQuery = env.getProperty("findLtMastApprovalssActiveLikeTransactionCode");
		return jdbcTemplate.query(sqlQuery,new Object[]{ "%"+transactionCode.trim().toUpperCase()+"%",companyId }, 
				 new RowMapper<LtMastAmountwiseApprovals>(){
					@Override
					public LtMastAmountwiseApprovals mapRow(ResultSet rs, int row)
							throws SQLException {
						LtMastAmountwiseApprovals modules=new LtMastAmountwiseApprovals();
						modules.setAmtApprovalId(rs.getLong("AMT_APPROVER_ID"));
						return modules;
					}
		   
	   });
	}

	@Override
	public boolean checkForDuplicate(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) {
		String query = env.getProperty("checkForDuplicateLtAmountwiseApprovals");
		
		 List<LtMastAmountwiseApprovals> ltMastAmountwiseApprovalsList = jdbcTemplate.query(query,  new Object[] 
				 {ltMastAmountwiseApprovals.getTransactionCode(),ltMastAmountwiseApprovals.getTransactionType(),
					 ltMastAmountwiseApprovals.getFromAmount(),ltMastAmountwiseApprovals.getToAmount(),ltMastAmountwiseApprovals.getApproverId()},
				 new BeanPropertyRowMapper<LtMastAmountwiseApprovals> (LtMastAmountwiseApprovals.class));
		 if(  ltMastAmountwiseApprovalsList.isEmpty()) {
			 return true; 
		}else if(ltMastAmountwiseApprovalsList.get(0).getAmtApprovalId().equals(ltMastAmountwiseApprovals.getAmtApprovalId()))
		{ return true;
		 }
		else return false;
}

	@Override
	public boolean update(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) {

	    String query = env.getProperty("updateLtAmountwiseApprovals");

	    int res = jdbcTemplate.update(query, ltMastAmountwiseApprovals.getTransactionCode(),
	            ltMastAmountwiseApprovals.getTransactionType(),
	            ltMastAmountwiseApprovals.getFromAmount(),
	            ltMastAmountwiseApprovals.getToAmount(),
	            ltMastAmountwiseApprovals.getApproverId(),
	            ltMastAmountwiseApprovals.getAmtApprovalId());

	    return res != 0;
	}

	@Override
    public int delete(Long amtApprovalId) {
        String sql = env.getProperty("deleteLtAmountwiseApproval");
        return jdbcTemplate.update(sql, amtApprovalId);
    }

	@Override
	public LtMastAmountwiseApprovals save(LtMastAmountwiseApprovals ltMastAmountwiseApprovals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LtMastAmountwiseApprovals getByAmtApprovalId(Long amtApprovalId) {
	    String query = env.getProperty("viewLtAmountwiseApproval");

	    try {
	        // Query might include logTime, if needed, update accordingly
	        return jdbcTemplate.queryForObject(query, new Object[]{amtApprovalId}, new RowMapper<LtMastAmountwiseApprovals>() {
	            @Override
	            public LtMastAmountwiseApprovals mapRow(ResultSet rs, int rowNum) throws SQLException {
	                LtMastAmountwiseApprovals ltMastAmountwiseApprovals = new LtMastAmountwiseApprovals();
	                ltMastAmountwiseApprovals.setAmtApprovalId(rs.getLong("amt_approval_id"));
	                ltMastAmountwiseApprovals.setTransactionCode(rs.getString("transaction_code"));
	                ltMastAmountwiseApprovals.setTransactionType(rs.getString("transaction_type"));
	                ltMastAmountwiseApprovals.setFromAmount(rs.getDouble("from_amount"));
	                ltMastAmountwiseApprovals.setToAmount(rs.getDouble("to_amount"));
	                ltMastAmountwiseApprovals.setApproverId(rs.getLong("approver_id"));
	                ltMastAmountwiseApprovals.setCreationDate(rs.getTimestamp("creation_date"));
	                ltMastAmountwiseApprovals.setCreatedBy(rs.getString("created_by"));
	                ltMastAmountwiseApprovals.setLastUpdateDate(rs.getTimestamp("last_update_date"));
	                ltMastAmountwiseApprovals.setLastUpdateLogin(rs.getString("last_update_login"));
	                ltMastAmountwiseApprovals.setLastUpdatedBy(rs.getString("last_updated_by"));
	                ltMastAmountwiseApprovals.setCompanyId(rs.getLong("company_id"));
	                return ltMastAmountwiseApprovals;
	            }
	        });
	    } catch (EmptyResultDataAccessException e) {
	        return null;  // No data found, return null or handle as needed
	    } 
	}
}
		


