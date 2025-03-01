package com.lonar.vendor.vendorportal.dao;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
 
import com.lonar.vendor.vendorportal.model.LtPoAttachments;
 
@Component
public class LtPoAttachmentsDaoImpl implements LtPoAttachmentsDao {
	@PersistenceContext(name = "em")
	private EntityManager em;
//	@Autowired
//	private Environment env;

 
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
 
	@Override
	public List<LtPoAttachments> getAllFilesByPoHeaderId(Long poHeaderId){
	String query = "SELECT IFNULL(CONCAT(svm.SYSTEM_VALUE, l.FILE_NAME), '') AS FILE_PATH, \n" +
            "       l.PO_ATTACHMENT_ID, \n" +
            "       l.PO_HEADER_ID, \n" +
            "       l.ATTACHMENT_TYPE_ID, \n" +
            "       l.FILE_NAME, \n" +
            "       atm.ATTACHMENT_NAME AS ATTACHMENT_TYPE \n" +
            "FROM LT_PO_ATTACHMENTS l \n" +
            "LEFT JOIN LT_MAST_ATTACHMENT_TYPE atm ON l.ATTACHMENT_TYPE_ID = atm.ATTACHMENT_TYPE_ID \n" +
            "JOIN LT_MAST_SYS_VARIABLES svm ON svm.VARIABLE_NAME = 'FILE_OPEN_PATH' \n" +
            "WHERE l.PO_HEADER_ID = ? \n" +
            "  AND svm.COMPANY_ID = ( \n" +
            "      SELECT COMPANY_ID \n" +
            "      FROM LT_MAST_EMPLOYEES \n" +
            "      WHERE EMPLOYEE_ID = ( \n" +
            "          SELECT initiator_ID \n" +
            "          FROM lt_po_headers ih \n" +
            "          WHERE ih.PO_HEADER_ID = l.PO_HEADER_ID \n" +
            "      ) \n" +
            "  )";
	List<LtPoAttachments> list=   jdbcTemplate.query(query, new Object[]{poHeaderId }, 
			 new BeanPropertyRowMapper<LtPoAttachments>(LtPoAttachments.class)); 
	return list;
	}
 
	
	@Override
	public boolean deleteLtPoAttachmentFile(Long poAttachmentId) {
		String query = 	"delete from LT_PO_ATTACHMENTS where PO_ATTACHMENT_ID = ?";
		int res= 0;
		res= jdbcTemplate.update(query,poAttachmentId);
		if(res!=0)
			return true;
		else
			return false;
	}
 
}