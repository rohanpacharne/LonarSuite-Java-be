package com.lonar.asn.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.asn.model.LtShipmentApprovalHistory;

@Repository
public class LtShipmentApprovalHistoryDaoImpl implements LtShipmentApprovalHistoryDao{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean save(LtShipmentApprovalHistory ltShipmentApprovalHistory) {
		int res = jdbcTemplate
				.update("INSERT INTO LT_SHIPMENT_APPROVAL_HISTORY "
						+ " (SHIPMENT_APPROVAL_HISTORY_ID,SHIPMENT_APPROVAL_ID,EMPLOYEE_ID, "
						+ " STATUS,NOTE,LAST_UPDATE_DATE,SHIPMENT_HEADER_ID,REMARK,USER_TYPE,VENDOR_ID ) "
						+ " VALUES(LT_SHIPMENT_APPROVAL_HISTORY_S.NEXTVAL,?,?,?,?,?,?,?,?,?) ",

				//ltExpenseApprovalHistory.getVendorApprovalHistoryId(),
						ltShipmentApprovalHistory.getShipmentApprovalId(),
						ltShipmentApprovalHistory.getEmployeeId(),
						ltShipmentApprovalHistory.getStatus(),
						ltShipmentApprovalHistory.getNote(),
						new Date(),
						ltShipmentApprovalHistory.getShipmentHeaderId(),
						ltShipmentApprovalHistory.getRemark(),
						ltShipmentApprovalHistory.getUserType(),
						ltShipmentApprovalHistory.getVendorId());
				
				if (res != 0)
					return true;
				else
					return false;
	}

}
