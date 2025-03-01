package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtPrApprovalHistory;
import com.lonar.vendor.vendorportal.model.PrApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
public class LtPrApprovalHistoryDaoImpl implements LtPrApprovalHistoryDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(LtPrApprovalHistory approvalHistory) throws ServiceException {
		int res = jdbcTemplate
				.update("INSERT INTO LT_PR_APPROVAL_HISTORY "
						+ " (PR_APPROVAL_ID,EMPLOYEE_ID, "
						+ " STATUS,NOTE,LAST_UPDATE_DATE,PR_HEADER_ID,REMARK ) "
						+ " VALUES(?,?,?,?,?,?,?) ",

				//ltExpenseApprovalHistory.getVendorApprovalHistoryId(),
						approvalHistory.getPrApprovalId(),
						approvalHistory.getEmployeeId(),
						approvalHistory.getStatus(),
						approvalHistory.getNote(),
				new Date(),
				approvalHistory.getPrHeaderId(),
				approvalHistory.getRemark());
				
				if (res != 0)
					return true;
				else
					return false;
	}


	
	

}
