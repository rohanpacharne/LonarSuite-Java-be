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

import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/poApprovalQueries.properties", ignoreResourceNotFound = true)
public class LtPoApprovalDaoImpl implements LtPoApprovalDao {

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
	public boolean updateStatusApproval(LtPoApprovalHistory approvalHistory) throws ServiceException {
		int res=0;	
		if(approvalHistory.getEmployeeId()!=null ) 
		{
			String query = " UPDATE LT_PO_APPROVAL "
			+" SET  Status= ? , LAST_UPDATE_DATE = ?,LAST_UPDATED_BY=? " 
			+" WHERE PO_HEADER_ID = ?  AND (APPROVAL_ID = ? OR DELEGATION_ID = ?) ";
			
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getPoApprovalId(),
					approvalHistory.getPoHeaderId(), approvalHistory.getEmployeeId(),approvalHistory.getEmployeeId());
		}
			
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean updateAllStatusApproval(LtPoApprovalHistory approvalHistory) throws ServiceException {
		//String query = env.getProperty("updateAllStatusApproval");
		
		String query = "UPDATE LT_PO_APPROVAL "
		+" SET  Status= ? ,LAST_UPDATE_DATE=?,LAST_UPDATED_BY=? "
		+" WHERE PO_HEADER_ID = ?  AND CURRENT_APPROVAL_LEVEL =  APPROVAL_LEVEL ";
		
		int res=0;	
			res = jdbcTemplate.update(query,
						approvalHistory.getStatus(),new Date(),approvalHistory.getPoApprovalId(),
						approvalHistory.getPoHeaderId());
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public List<PoApproval> chkPoEmpApproval(Long employeeId, Long poId) throws ServiceException {
		String query =   " SELECT * FROM LT_PO_APPROVAL po  "+
				" WHERE po.APPROVAL_ID = ? and po.PO_HEADER_ID = ?  and po.STATUS = ? ";

		List<PoApproval> poApprovalList = jdbcTemplate.query(query, new Object[]
				{employeeId,poId,"APPROVED"},
				new RowMapper<PoApproval>() {
					public PoApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						PoApproval poApproval = new PoApproval();
						
						poApproval.setPoApprovalId(rs.getLong("PO_APPROVAL_ID"));
						
						return poApproval;
					}
				});
	
		return poApprovalList;
	}

	@Override
	public boolean submitForApproval(Date submissionDate, Long poHeaderId, String status, Date approvedDate,Long lastLogin)
			throws ServiceException {
		int res=0;
		if(submissionDate!=null)
		{
			String query = env.getProperty("submitPoForApproval1");
			res=jdbcTemplate.update(query,status,new Date(),lastLogin,poHeaderId);
			
		}
		else
		{
			String query = env.getProperty("submitPoForApproval2");
			 res=jdbcTemplate.update(query,status,new Date(),lastLogin,poHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public String getCurrLevelByPoApprovalId(Long poApprovalId) throws ServiceException {
		String query = env.getProperty("getCurrLevelByPoApprovalId");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { poApprovalId}, String.class);

		return nextlavel;
	}

	@Override
	public boolean upDateStatus(Long poHeaderId, String status, String currentApprovalLevel) throws ServiceException {
		int res=0;
		
		if(currentApprovalLevel!=null)
		{
			String query = env.getProperty("upDatePoStatus1");
			 res=jdbcTemplate.update(query,
				        status,new Date(),poHeaderId,currentApprovalLevel,"APPROVED");
		}
		else
		{
			String query = env.getProperty("upDatePoStatus2");
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLevel,poHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public PoApproval getPoApproval(Long poId, Long apprId) throws ServiceException {
		String query = env.getProperty("getPoApprovalByPoIdAndApproverId");
		List<PoApproval> list=   jdbcTemplate.query(query, new Object[]{ poId,apprId }, 
				 new BeanPropertyRowMapper<PoApproval>(PoApproval.class)); 
		if(list.isEmpty())
			return null;
		else 
		 return list.get(0);
	}

	
}
