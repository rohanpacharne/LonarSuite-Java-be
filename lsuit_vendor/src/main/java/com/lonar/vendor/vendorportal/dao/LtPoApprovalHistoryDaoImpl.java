package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
public class LtPoApprovalHistoryDaoImpl implements LtPoApprovalHistoryDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean save(LtPoApprovalHistory poApprovalHistory) throws ServiceException {
		
				int res = jdbcTemplate
				.update("INSERT INTO LT_PO_APPROVAL_HISTORY "
						+ " (PO_APPROVAL_ID,EMPLOYEE_ID, "
						+ " STATUS,NOTE,LAST_UPDATE_DATE,PO_HEADER_ID,REMARK,USER_TYPE,VENDOR_ID ) "
						+ " VALUES(?,?,?,?,?,?,?,?,?) ",

				//ltExpenseApprovalHistory.getVendorApprovalHistoryId(),
						poApprovalHistory.getPoApprovalId(),
				poApprovalHistory.getEmployeeId(),
				poApprovalHistory.getStatus(),
				poApprovalHistory.getNote(),
				new Date(),
				poApprovalHistory.getPoHeaderId(),
				poApprovalHistory.getRemark(),
				poApprovalHistory.getUserType(),poApprovalHistory.getVendorId());
				
				if (res != 0)
					return true;
				else
					return false;
	}
	
	
	@Override
	public List<LtPoApprovalHistory> getPoApprovalHistoryByPoHeaderId(Long poHeaderId)
	{
		
		String query = "SELECT ih.PO_APPROVAL_HISTORY_ID, \n" +
	               "       ih.PO_APPROVAL_ID, \n" +
	               "       ih.NOTE, \n" +
	               "       ih.LAST_UPDATE_DATE, \n" +
	               "       ih.PO_HEADER_ID, \n" +
	               "       ih.EMPLOYEE_ID, \n" +
	               "       ih.REMARK, \n" +
	               "       get_comn_value_name('PO_STATUS', ih.status) AS STATUS, \n" +
	               "       CASE \n" +
	               "           WHEN ih.user_type = 'VENDOR' THEN vm.VENDOR_NAME \n" +
	               "           ELSE CONCAT(cmv.value_name, ' ', em.first_name, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')') \n" +
	               "       END AS approval_Name \n" +
	               "FROM LT_PO_APPROVAL_HISTORY ih \n" +
	               "LEFT JOIN LT_PO_HEADERS ven ON ih.PO_HEADER_ID = ven.PO_HEADER_ID \n" +
	               "LEFT JOIN LT_MAST_VENDORS vm ON ih.VENDOR_ID = vm.VENDOR_ID \n" +
	               "LEFT JOIN LT_MAST_EMPLOYEES em ON ih.EMPLOYEE_ID = em.EMPLOYEE_ID \n" +
	               "LEFT JOIN LT_MAST_COMN_MASTER_VALUES cmv ON em.title = cmv.value_code \n" +
	               "WHERE ih.PO_HEADER_ID = ? \n" +
	               "ORDER BY ih.PO_APPROVAL_HISTORY_ID DESC";

		
		List<LtPoApprovalHistory> list=   jdbcTemplate.query(query, new Object[]{poHeaderId }, 
				 new BeanPropertyRowMapper<LtPoApprovalHistory>(LtPoApprovalHistory.class)); 
		 
		return list;
	}

	

	
}
