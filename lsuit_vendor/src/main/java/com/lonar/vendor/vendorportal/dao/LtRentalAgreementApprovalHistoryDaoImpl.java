package com.lonar.vendor.vendorportal.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtRentalAgrApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
@PropertySource(value = "classpath:queries/invoiceApprovalQueries.properties", ignoreResourceNotFound = true)
public class LtRentalAgreementApprovalHistoryDaoImpl implements LtRentalAgreementApprovalHistoryDao, CodeMaster{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(LtRentalAgrApprovalHistory ltRentalAgrApprovalHistory) throws ServiceException {
		System.out.println("in save...");
		System.out.println("ltRentalAgrApprovalHistory = "+ltRentalAgrApprovalHistory);
		int res = jdbcTemplate
				.update("INSERT INTO lt_rental_agr_approval_history "
						+ " (AGREEMENT_APPROVAL_ID, "
						+ " STATUS,NOTE,LAST_UPDATE_DATE,AGREEMENT_HEADER_ID,REMARK,EMPLOYEE_ID) "
						+ " VALUES(?,?,?,?,?,?,?) ",

				//ltExpenseApprovalHistory.getVendorApprovalHistoryId(),
						ltRentalAgrApprovalHistory.getAgreementApprovalId(),
						ltRentalAgrApprovalHistory.getStatus(),
						ltRentalAgrApprovalHistory.getNote(),
				new Date(),
				ltRentalAgrApprovalHistory.getAgreementHeaderId(),
				ltRentalAgrApprovalHistory.getRemark(),
				ltRentalAgrApprovalHistory.getEmployeeId());
				
				if (res != 0)
					return true;
				else
					return false;
	}
	
	

}
