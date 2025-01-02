package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtInvoiceApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
public class LtInvoiceApprovalHistoryDaoImpl implements LtInvoiceApprovalHistoryDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean save(LtInvoiceApprovalHistory invoiceApprovalHistory) throws ServiceException {
		
				int res = jdbcTemplate
				.update("INSERT INTO LT_INVOICE_APPROVAL_HISTORY "
						+ " (INVOICE_APPROVAL_ID,EMPLOYEE_ID, "
						+ " STATUS,NOTE,LAST_UPDATE_DATE,INVOICE_HEADER_ID,REMARK,USER_TYPE,VENDOR_ID ) "
						+ " VALUES(?,?,?,?,?,?,?,?,?) ",

				//ltExpenseApprovalHistory.getVendorApprovalHistoryId(),
				invoiceApprovalHistory.getInvoiceApprovalId(),
				invoiceApprovalHistory.getEmployeeId(),
				invoiceApprovalHistory.getStatus(),
				invoiceApprovalHistory.getNote(),
				new Date(),
				invoiceApprovalHistory.getInvoiceHeaderId(),
				invoiceApprovalHistory.getRemark(),
				invoiceApprovalHistory.getUserType(),invoiceApprovalHistory.getVendorId());
				
				if (res != 0)
					return true;
				else
					return false;
	}

	@Override
	public List<LtInvoiceApprovalHistory> getInvoiceApprovalHistoryByInvoiceHeaderId(Long invoiceHeaderId)
	{
//		String query = " SELECT ih.INVOICE_APPROVAL_HISTORY_ID, ih.INVOICE_APPROVAL_ID,ih.NOTE,ih.LAST_UPDATE_DATE,ih.INVOICE_HEADER_ID,ih.EMPLOYEE_ID,   " + 
//				"			      ih.REMARK,lt_vpal_common_pkg.get_comn_value_name('INVOICE_STATUS',ih.status)  as  STATUS, " + 
//				"				  (CASE WHEN ih.user_type = 'VENDOR' then vm.VENDOR_NAME\r\n" + 
//				"				    ELSE  ( cmv.value_name||' ' || em.first_name || ' '|| em.LAST_NAME||' ' || '(' ||em.EMPLOYEE_NUMBER||')' )  " + 
//				"				    END ) AS approval_Name  " + 
//				"				    FROM LT_INVOICE_APPROVAL_HISTORY ih,LT_MAST_EMPLOYEES em,LT_MAST_COMN_MASTER_VALUES cmv ,   " + 
//				"				 LT_INVOICE_HEADERS ven ,LT_MAST_VENDORS vm " + 
//				"					 WHERE ih.INVOICE_HEADER_ID = ?  " + 
//				"				    AND  ih.INVOICE_HEADER_ID = ven.INVOICE_HEADER_ID(+) " + 
//				"            AND ih.VENDOR_ID = vm.VENDOR_ID(+) " + 
//				"				 and ih.employee_id  = em.employee_id(+) " + 
//				"					 and em.title = cmv.value_code(+) " + 
//			    "					order by ih.INVOICE_APPROVAL_HISTORY_ID desc  ";
		
		String query = "SELECT ih.INVOICE_APPROVAL_HISTORY_ID, \n" +
	               "       ih.INVOICE_APPROVAL_ID, \n" +
	               "       ih.NOTE, \n" +
	               "       ih.LAST_UPDATE_DATE, \n" +
	               "       ih.INVOICE_HEADER_ID, \n" +
	               "       ih.EMPLOYEE_ID, \n" +
	               "       ih.REMARK, \n" +
	               "       get_comn_value_name('INVOICE_STATUS', ih.status) AS STATUS, \n" +
	               "       CASE \n" +
	               "           WHEN ih.user_type = 'VENDOR' THEN vm.VENDOR_NAME \n" +
	               "           ELSE CONCAT(cmv.value_name, ' ', em.first_name, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')') \n" +
	               "       END AS approval_Name \n" +
	               "FROM LT_INVOICE_APPROVAL_HISTORY ih \n" +
	               "LEFT JOIN LT_INVOICE_HEADERS ven ON ih.INVOICE_HEADER_ID = ven.INVOICE_HEADER_ID \n" +
	               "LEFT JOIN LT_MAST_VENDORS vm ON ih.VENDOR_ID = vm.VENDOR_ID \n" +
	               "LEFT JOIN LT_MAST_EMPLOYEES em ON ih.EMPLOYEE_ID = em.EMPLOYEE_ID \n" +
	               "LEFT JOIN LT_MAST_COMN_MASTER_VALUES cmv ON em.title = cmv.value_code \n" +
	               "WHERE ih.INVOICE_HEADER_ID = ? \n" +
	               "ORDER BY ih.INVOICE_APPROVAL_HISTORY_ID DESC";

		
		List<LtInvoiceApprovalHistory> list=   jdbcTemplate.query(query, new Object[]{ invoiceHeaderId }, 
				 new BeanPropertyRowMapper<LtInvoiceApprovalHistory>(LtInvoiceApprovalHistory.class)); 
		 
		return list;
	}

	

}
