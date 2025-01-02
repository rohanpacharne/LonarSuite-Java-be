package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.dao.LtRentalAgreementAttachmentDao;
import com.lonar.vendor.vendorportal.model.LtInvoiceAttachment;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementAttachments;
import com.lonar.vendor.vendorportal.model.ServiceException;

@Component
public class LtRentalAgreementAttachmentDaoImpl implements LtRentalAgreementAttachmentDao{
	
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
	public List<LtRentalAgreementAttachments> getAllFilesByAgreementHeaderId(Long agreementHeaderId){
	String query = "SELECT IFNULL(CONCAT(svm.SYSTEM_VALUE, l.FILE_NAME), '') AS FILE_PATH, \n" +
            "       l.RENTAL_AGR_ATTACHMENT_ID, \n" +
            "       l.AGREEMENT_HEADER_ID, \n" +
            "       l.ATTACHMENT_TYPE_ID, \n" +
            "       l.FILE_NAME, \n" +
            "       atm.ATTACHMENT_NAME AS ATTACHMENT_TYPE \n" +
            "FROM lt_rental_agr_attachments l \n" +
            "LEFT JOIN LT_MAST_ATTACHMENT_TYPE atm ON l.ATTACHMENT_TYPE_ID = atm.ATTACHMENT_TYPE_ID \n" +
            "JOIN LT_MAST_SYS_VARIABLES svm ON svm.VARIABLE_NAME = 'FILE_OPEN_PATH' \n" +
            "WHERE l.AGREEMENT_HEADER_ID = ? \n" +
            "  AND svm.COMPANY_ID = ( \n" +
            "      SELECT COMPANY_ID \n" +
            "      FROM LT_MAST_EMPLOYEES \n" +
            "      WHERE EMPLOYEE_ID = ( \n" +
            "          SELECT initiator_ID \n" +
            "          FROM lt_rental_agreement_headers ih \n" +
            "          WHERE ih.AGREEMENT_HEADER_ID = l.AGREEMENT_HEADER_ID \n" +
            "      ) \n" +
            "  )";
	List<LtRentalAgreementAttachments> list=   jdbcTemplate.query(query, new Object[]{agreementHeaderId }, 
			 new BeanPropertyRowMapper<LtRentalAgreementAttachments>(LtRentalAgreementAttachments.class)); 
	
	return list;
	}

	@Override
	public boolean deleteLtAgreementAttachmentFile(Long agreementAttachmentId) {
		String query = 	"delete from lt_rental_agr_attachments where RENTAL_AGR_ATTACHMENT_ID = ?";
		int res= 0;
		res= jdbcTemplate.update(query,agreementAttachmentId);
		if(res!=0)
			return true;
		else
			return false;
	}

}
