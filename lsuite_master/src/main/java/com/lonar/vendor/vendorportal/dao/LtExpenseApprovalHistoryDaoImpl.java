package com.lonar.vendor.vendorportal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.model.LtExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;

@Component
public class LtExpenseApprovalHistoryDaoImpl implements LtExpenseApprovalHistoryDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment env;
	 
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	@Override
	public List<LtExpenseApprovalHistory> getExpenseApprovalHistoryByExpHeaderId(Long expenseHeaderId) throws Exception {
		
//		String query = " SELECT v.*,"
//				+ " ( cmv.value_name||' ' || em.first_name || ' '|| em.LAST_NAME||' ' || '(' ||em.EMPLOYEE_NUMBER||')' ) AS approval_Name  "
//				+ " FROM lt_expense_approval_history v,LT_MAST_EMPLOYEES em,LT_MAST_COMN_MASTER_VALUES cmv "
//				+ " WHERE v.EXPENSE_HEADER_ID = ? "
//				+ " and em.employee_id = v.employee_id "
//				+ " and em.title = cmv.value_code(+) order by v.EXPENSE_APPROVAL_HISTORY_ID desc ";
				
		String query = "SELECT v.*, " +
			    "CONCAT(em.first_name, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')') AS approval_Name " +
			    "FROM lt_expense_approval_history v " +
			    "LEFT JOIN LT_MAST_EMPLOYEES em ON em.employee_id = v.employee_id " +
			    "WHERE v.EXPENSE_HEADER_ID = ? " +
			    "ORDER BY v.EXPENSE_APPROVAL_HISTORY_ID DESC";
		
		List<LtExpenseApprovalHistory> list=   jdbcTemplate.query(query, new Object[]{ expenseHeaderId },
				 new BeanPropertyRowMapper<LtExpenseApprovalHistory>(LtExpenseApprovalHistory.class));
		
		return list;
	}
	
	@Override
	public boolean save(LtExpenseApprovalHistory ltExpenseApprovalHistory) throws Exception {
		
//		if(ltExpenseApprovalHistory.getExpenseApprovalHistoryId()==null) {
//			ltExpenseApprovalHistory.setExpenseApprovalHistoryId(1L);
//		}
		
		System.out.println(ltExpenseApprovalHistory.getExpenseApprovalId()+"  "+
						ltExpenseApprovalHistory.getEmployeeId());
//		int res = jdbcTemplate
//				.update(" INSERT INTO lt_expense_approval_history "
//						+ " (EXPENSE_APPROVAL_HISTORY_ID,EXPENSE_APPROVAL_ID,EMPLOYEE_ID, "
//						+ " STATUS,NOTE,LAST_UPDATE_DATE,EXPENSE_HEADER_ID,REMARK ) "
//						+ " VALUES(?,?,?,?,?,?,?,?)  ",
		
		int res = jdbcTemplate
				.update(" INSERT INTO lt_expense_approval_history "
						+ " (EXPENSE_APPROVAL_ID,EMPLOYEE_ID, "
						+ " STATUS,NOTE,LAST_UPDATE_DATE,EXPENSE_HEADER_ID,REMARK ) "
						+ " VALUES(?,?,?,?,?,?,?)  ",

						//ltExpenseApprovalHistory.getExpenseApprovalHistoryId(),
						ltExpenseApprovalHistory.getExpenseApprovalId(),
						ltExpenseApprovalHistory.getEmployeeId(),
						ltExpenseApprovalHistory.getStatus(),
						ltExpenseApprovalHistory.getNote(),
						ltExpenseApprovalHistory.getLastUpdateDate(),
						ltExpenseApprovalHistory.getExpenseHeaderId(),
						ltExpenseApprovalHistory.getRemark());
						
		if (res != 0)
			return true;
		else
			return false;
	}
}
