package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtPrAttachments;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Repository
public class LtPrAttachmentDaoImpl implements LtPrAttachmentDao {
	
	@PersistenceContext(name = "em")
	private EntityManager em;
	
	@Autowired
	private Environment env;
	

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<LtPrAttachments> getAllFilesByPrHeaderId(Long prHeaderId) throws ServiceException {
		String query = "SELECT IFNULL(CONCAT(svm.SYSTEM_VALUE, l.FILE_NAME), '') AS FILE_PATH, \n" +
	            "       l.PR_ATTACHMENT_ID, \n" +
	            "       l.PR_HEADER_ID, \n" +
	            "       l.ATTACHMENT_TYPE_ID, \n" +
	            "       l.FILE_NAME, \n" +
	            "       atm.ATTACHMENT_NAME AS ATTACHMENT_TYPE \n" +
	            "FROM lt_pr_attachments l \n" +
	            "LEFT JOIN LT_MAST_ATTACHMENT_TYPE atm ON l.ATTACHMENT_TYPE_ID = atm.ATTACHMENT_TYPE_ID \n" +
	            "JOIN LT_MAST_SYS_VARIABLES svm ON svm.VARIABLE_NAME = 'FILE_OPEN_PATH' \n" +
	            "WHERE l.PR_HEADER_ID = ? \n" +
	            "  AND svm.COMPANY_ID = ( \n" +
	            "      SELECT COMPANY_ID \n" +
	            "      FROM LT_MAST_EMPLOYEES \n" +
	            "      WHERE EMPLOYEE_ID = ( \n" +
	            "          SELECT initiator_ID \n" +
	            "          FROM lt_pr_headers ih \n" +
	            "          WHERE ih.PR_HEADER_ID = l.PR_HEADER_ID \n" +
	            "      ) \n" +
	            "  )";
		List<LtPrAttachments> list=   jdbcTemplate.query(query, new Object[]{prHeaderId }, 
				 new BeanPropertyRowMapper<LtPrAttachments>(LtPrAttachments.class)); 
		
		return list;
	}

	@Override
	public boolean deletePrAttachmentFile(Long prAttachmentId) {
		// TODO Auto-generated method stub
		String query = 	"delete from lt_pr_attachments where PR_ATTACHMENT_ID = ?";
		int res= 0;
		res= jdbcTemplate.update(query,prAttachmentId);
		if(res!=0)
			return true;
		else
			return false;
	}

}
