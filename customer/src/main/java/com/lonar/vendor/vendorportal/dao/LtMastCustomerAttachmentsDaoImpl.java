package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtMastCustomerAttachment;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
public class LtMastCustomerAttachmentsDaoImpl implements LtMastCustomerAttachmentsDao{

	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	@Override
	public List<LtMastCustomerAttachment> getAllFilesByCustomerId(Long customerId) throws ServiceException {
//		String query  = " SELECT  nvl2(l.FILE_NAME,svm.SYSTEM_VALUE||l.FILE_NAME,'') AS FILE_PATH,\r\n" + 
//				"				l.CUSTOMER_ATTACHMENT_ID,l.CUSTOMER_ID,l.CUST_ATTACHMENT_TYPE,l.FILE_NAME, atm.ATTACHMENT_NAME as ATTACHMENT_TYPE  \r\n" + 
//				"				FROM LT_MAST_CUSTOMER_ATTACHMENT l, \r\n" + 
//				"				    LT_MAST_ATTACHMENT_TYPE atm  , \r\n" + 
//				"				    LT_MAST_SYS_VARIABLES svm \r\n" + 
//				"				     WHERE l.CUSTOMER_ID = ? \r\n" + 
//				"                     AND svm.VARIABLE_NAME = 'FILE_OPEN_PATH'   \r\n" + 
//				"				     AND svm.COMPANY_ID = ( SELECT COMPANY_ID FROM LT_MAST_EMPLOYEES WHERE EMPLOYEE_ID =  \r\n" + 
//				"				                            (SELECT INITIATOR_ID FROM LT_MAST_CUSTOMERS ih WHERE ih.CUSTOMER_ID = l.CUSTOMER_ID ) ) \r\n" + 
//				"				    AND l.CUST_ATTACHMENT_TYPE = atm.ATTACHMENT_TYPE_ID(+) ";
		String query = "SELECT " +
			    "IF(l.FILE_NAME IS NOT NULL, CONCAT(svm.SYSTEM_VALUE, l.FILE_NAME), '') AS FILE_PATH, " +
			    "l.CUSTOMER_ATTACHMENT_ID, " +
			    "l.CUSTOMER_ID, " +
			    "l.CUST_ATTACHMENT_TYPE, " +
			    "l.FILE_NAME, " +
			    "atm.ATTACHMENT_NAME AS ATTACHMENT_TYPE " +
			    "FROM " +
			    "LT_MAST_CUSTOMER_ATTACHMENT l " +
			    "LEFT JOIN LT_MAST_ATTACHMENT_TYPE atm ON l.CUST_ATTACHMENT_TYPE = atm.ATTACHMENT_TYPE_ID " +
			    "INNER JOIN LT_MAST_SYS_VARIABLES svm ON svm.VARIABLE_NAME = 'FILE_OPEN_PATH' " +
			    "WHERE " +
			    "l.CUSTOMER_ID = ? " +
			    "AND svm.COMPANY_ID = ( " +
			    "    SELECT COMPANY_ID " +
			    "    FROM LT_MAST_EMPLOYEES " +
			    "    WHERE EMPLOYEE_ID = ( " +
			    "        SELECT INITIATOR_ID " +
			    "        FROM LT_MAST_CUSTOMERS ih " +
			    "        WHERE ih.CUSTOMER_ID = l.CUSTOMER_ID " +
			    "    ) " +
			    ") ";

		List<LtMastCustomerAttachment> list=   jdbcTemplate.query(query, new Object[]{customerId }, 
				 new BeanPropertyRowMapper<LtMastCustomerAttachment>(LtMastCustomerAttachment.class)); 
		
		return list;
	}

	@Override
	public boolean saveCustomerFile(Long customerId, String fileName, String saveDirectory, Long userId, Date date,
			Long attachmentTypeId) throws ServiceException {
		int res = 0;
		res=jdbcTemplate.update("INSERT INTO LT_MAST_CUSTOMER_ATTACHMENT  "
				+ "  (CUSTOMER_ID, FILE_NAME, FILE_PATH,CREATED_BY,LAST_UPDATE_LOGIN, LAST_UPDATED_BY,CREATION_DATE,LAST_UPDATE_DATE, CUST_ATTACHMENT_TYPE)  "
				+ " VALUES (?, ? ,?,?, ?, ?, ?,?,?) ",
				+  customerId,fileName,saveDirectory,userId, userId,userId,date,date, attachmentTypeId );		
		if(res!=0) {
			return true;
		}else
		return false;
	}

	@Override
	public boolean deleteLtMastCustomerAttachmentFile(Long customerAttachmentId) throws ServiceException {
		String query = 	"delete from LT_MAST_CUSTOMER_ATTACHMENT where CUSTOMER_ATTACHMENT_ID = ?";
		int res= 0;
		res= jdbcTemplate.update(query,customerAttachmentId);
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteByCustomerId(Long customerId) throws ServiceException {
		String query = 	"delete from LT_MAST_CUSTOMER_ATTACHMENT where CUSTOMER_ID = ?";
		int res = 0;
		jdbcTemplate.update(query, customerId);
		if (res != 0)
			return true;
		return false;
		
	}

}
