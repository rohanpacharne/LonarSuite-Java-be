package com.lonar.vendor.vendorportal.fileupload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.model.LtExpenseAttachments;

@Component
@PropertySource(value = "classpath:branchMasterQueries.properties", ignoreResourceNotFound = true)
public class LtMastFileUploadDaoImpl implements LtMastFileUploadDao {
	
	@Autowired
	 private DataSource dataSource;
	
//	@PersistenceContext(name = "em")
//	private EntityManager em;
	
	@Autowired
	private Environment env;
	
//	private NamedParameterJdbcTemplate jdbcTemplate1;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Transactional
	@Override
	public List<LtExpenseAttachments> getAllFilesByHeaderId(Long expenceHeaderId) throws Exception
	{
		
		//String query = env.getProperty("getAllFilesByHeaderId");
		
		String query = " SELECT * FROM lt_exp_expense_attachments l "
				+ " WHERE l.EXPENCE_HEADER_ID = ? ORDER BY EXPENSE_ATTACHMENT_ID DESC ";
		
		 return jdbcTemplate.query(query, new Object[]{expenceHeaderId }, new RowMapper<LtExpenseAttachments>(){
				@Override
				public LtExpenseAttachments mapRow(ResultSet rs, int row)
						throws SQLException {
					LtExpenseAttachments ltExpenseAttachments=new LtExpenseAttachments();
					
					ltExpenseAttachments.setExpenseAttachmentId((rs.getLong("EXPENSE_ATTACHMENT_ID")));
					ltExpenseAttachments.setExpenceHeaderId(rs.getLong("EXPENCE_HEADER_ID"));
					ltExpenseAttachments.setExpenceLineId(rs.getLong("EXPENCE_LINE_ID"));
					ltExpenseAttachments.setFileName(rs.getString("FILE_NAME"));
					ltExpenseAttachments.setFilePath(rs.getString("FILE_PATH"));
					ltExpenseAttachments.setAttachmentType(rs.getString("ATTACHMENT_TYPE"));
					ltExpenseAttachments.setCreatedBy(rs.getLong("CREATED_BY"));
					ltExpenseAttachments.setCreationDate(rs.getDate("CREATION_DATE"));
					ltExpenseAttachments.setLastUpdateLogin(rs.getLong("LAST_UPDATE_LOGIN"));
					ltExpenseAttachments.setLastUpdatedBy(rs.getLong("LAST_UPDATED_BY"));
					ltExpenseAttachments.setLastUpdateDate(rs.getDate("LAST_UPDATE_DATE"));
					ltExpenseAttachments.setThumbnailFilePath(rs.getString("THUMBNAIL_FILE_PATH"));
					ltExpenseAttachments.setFileText(rs.getString("FILE_TEXT"));
					
					    
					    
						return ltExpenseAttachments;
					}
					
			   });

		/*List<LtMastFileUpload> list = jdbcTemplate.query(query, new Object[] { expenceHeaderId },
				new BeanPropertyRowMapper<LtMastFileUpload>(LtMastFileUpload.class));*/

		
	}

}
