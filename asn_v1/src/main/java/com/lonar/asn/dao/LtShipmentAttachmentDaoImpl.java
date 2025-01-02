package com.lonar.asn.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.asn.model.AttachmentDTO;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.LtShipmentAttachment;

@Repository
public class LtShipmentAttachmentDaoImpl implements LtShipmentAttachmentDao{

	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public List<LtShipmentAttachment> getAllFilesByShipmentHeaderId(Long asnHeaderId) throws BusinessException {
//		String query = " SELECT sha.SHIPMENT_ATTACHMENT_ID,sha.SHIPMENT_HEADER_ID,sha.ATTACHMENT_TYPE_ID,sha.FILE_NAME,at.ATTACHMENT_NAME,\r\n" + 
//				" sha.CREATED_BY,sha.CREATION_DATE ,nvl2(sha.FILE_NAME,svm.SYSTEM_VALUE||sha.FILE_NAME,'') AS FILE_PATH\r\n" + 
//				" FROM LT_SHIPMENT_ATTACHMENT sha,LT_MAST_SYS_VARIABLES svm,LT_MAST_ATTACHMENT_TYPE at\r\n" + 
//				" WHERE sha.SHIPMENT_HEADER_ID = ? AND sha.ATTACHMENT_TYPE_ID = at.ATTACHMENT_TYPE_ID(+) \r\n" + 
//				" AND sha.ATTACHMENT_TYPE_ID IS NOT NULL\r\n" + 
//				" AND svm.VARIABLE_NAME = 'FILE_OPEN_PATH' \r\n" + 
//				" AND  svm.COMPANY_ID = ( SELECT mv.COMPANY_ID FROM LT_MAST_VENDORS mv \r\n" + 
//				"                WHERE VENDOR_ID = (SELECT VENDOR_ID FROM LT_SHIPMENT_HEADERS WHERE SHIPMENT_HEADER_ID = ?) ) ";
		String query = " SELECT sha.SHIPMENT_ATTACHMENT_ID, sha.SHIPMENT_HEADER_ID, sha.ATTACHMENT_TYPE_ID, sha.FILE_NAME, at.ATTACHMENT_NAME, \r\n" + 
	               " sha.CREATED_BY, sha.CREATION_DATE, IFNULL(CONCAT(svm.SYSTEM_VALUE, sha.FILE_NAME), '') AS FILE_PATH \r\n" + 
	               " FROM LT_SHIPMENT_ATTACHMENT sha \r\n" + 
	               " LEFT JOIN LT_MAST_ATTACHMENT_TYPE at ON sha.ATTACHMENT_TYPE_ID = at.ATTACHMENT_TYPE_ID \r\n" + 
	               " JOIN LT_MAST_SYS_VARIABLES svm ON svm.VARIABLE_NAME = 'FILE_OPEN_PATH' \r\n" + 
	               " WHERE sha.SHIPMENT_HEADER_ID = ? \r\n" + 
	               " AND sha.ATTACHMENT_TYPE_ID IS NOT NULL \r\n" + 
	               " AND svm.COMPANY_ID = ( SELECT mv.COMPANY_ID FROM LT_MAST_VENDORS mv \r\n" + 
	               " WHERE mv.VENDOR_ID = (SELECT VENDOR_ID FROM LT_SHIPMENT_HEADERS WHERE SHIPMENT_HEADER_ID = ?) )";

		List<LtShipmentAttachment> list=   jdbcTemplate.query(query, new Object[]{asnHeaderId,asnHeaderId}, 
					 new BeanPropertyRowMapper<LtShipmentAttachment>(LtShipmentAttachment.class)); 
		
			 return list;
	}

	@Override
	public boolean save(Long asnHeaderId, String fileName, String saveDirectory, Long userId, Date currDate,
			Long attachmentTypeId) throws BusinessException {
		int res=jdbcTemplate.update("INSERT INTO LT_SHIPMENT_ATTACHMENT  "
				+ "  (SHIPMENT_HEADER_ID, FILE_NAME, FILE_PATH, LAST_UPDATED_BY,LAST_UPDATE_DATE, ATTACHMENT_TYPE_ID)  "
				+ " VALUES (?, ? ,?, ?, ?, ?) ",
				+  asnHeaderId,fileName,saveDirectory,userId, currDate, attachmentTypeId );		
		
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean saveLineAttachment(Long asnHeaderId, String fileName, String saveDirectory, Long userId,
			Date currDate, String asnLineId) throws BusinessException {
		
		String query = " SELECT LT_SHIPMENT_ATTACHMENT_S.nextval FROM DUAL";
		String attachmentId  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {}, String.class);
		
		int res=jdbcTemplate.update("INSERT INTO LT_SHIPMENT_ATTACHMENT  "
				+ "  (SHIPMENT_ATTACHMENT_ID, SHIPMENT_HEADER_ID, FILE_NAME, FILE_PATH, LAST_UPDATED_BY,LAST_UPDATE_DATE, ATTACHMENT_TYPE_ID)  "
				+ " VALUES (?, ?, ? ,?, ?, ?, ?) ",attachmentId,
				+  asnHeaderId,fileName,saveDirectory,userId, currDate, null );		
		
		if(res!=0) {
			int res1=jdbcTemplate.update("UPDATE LT_SHIPMENT_LINES SET ATTACHMENT = ? WHERE SHIPMENT_LINE_ID = ? AND  SHIPMENT_HEADER_ID = ? "
					, attachmentId, asnLineId,asnHeaderId);	
			if(res1!=0) {
				return true;
				
			}else {
				return false;
			}
		}
		return false;
			
	}

	@Override
	public boolean deleteByID(String id) throws BusinessException {
		int res1=jdbcTemplate.update("DELETE FROM LT_SHIPMENT_ATTACHMENT  WHERE SHIPMENT_ATTACHMENT_ID = ? ", id);	
		if(res1!=0) {
			
			return true;
			
		}else {
			return false;
		}
	}

	@Override
	public AttachmentDTO getfilepathbylineid(String id) throws BusinessException {
//		String sql =   " select  nvl2(sa.FILE_NAME,svm.SYSTEM_VALUE||sa.FILE_NAME,'') AS FILE_PATH, FILE_NAME   " + 
//				"    FROM LT_SHIPMENT_ATTACHMENT sa,LT_MAST_SYS_VARIABLES svm,LT_MAST_VENDORS vm,LT_SHIPMENT_HEADERS sh " + 
//				"    where  SHIPMENT_ATTACHMENT_ID =  " + 
//				"				 ( SELECT ATTACHMENT FROM LT_SHIPMENT_LINES WHERE SHIPMENT_LINE_ID = ? ) " + 
//				"    AND svm.VARIABLE_NAME = 'FILE_OPEN_PATH' " + 
//				"    AND  svm.COMPANY_ID = vm.COMPANY_ID(+) " + 
//				"    AND sa.SHIPMENT_HEADER_ID = sh.SHIPMENT_HEADER_ID(+) " + 
//				"    AND sh.VENDOR_ID = vm.VENDOR_ID ";
		
		String sql = " SELECT IFNULL(CONCAT(svm.SYSTEM_VALUE, sa.FILE_NAME), '') AS FILE_PATH, FILE_NAME \r\n" + 
	             " FROM LT_SHIPMENT_ATTACHMENT sa \r\n" + 
	             " JOIN LT_MAST_SYS_VARIABLES svm ON svm.VARIABLE_NAME = 'FILE_OPEN_PATH' \r\n" + 
	             " LEFT JOIN LT_MAST_VENDORS vm ON svm.COMPANY_ID = vm.COMPANY_ID \r\n" + 
	             " LEFT JOIN LT_SHIPMENT_HEADERS sh ON sa.SHIPMENT_HEADER_ID = sh.SHIPMENT_HEADER_ID \r\n" + 
	             " WHERE sa.SHIPMENT_ATTACHMENT_ID = \r\n" + 
	             " ( SELECT ATTACHMENT FROM LT_SHIPMENT_LINES WHERE SHIPMENT_LINE_ID = ? ) \r\n" + 
	             " AND sh.VENDOR_ID = vm.VENDOR_ID ";

		
		List<AttachmentDTO> list=   jdbcTemplate.query(sql, new Object[]{ id}, 
				 new BeanPropertyRowMapper<AttachmentDTO>(AttachmentDTO.class));
		if(list.isEmpty()) {
			AttachmentDTO attachmentDTO = new AttachmentDTO();
			attachmentDTO.setFilePath("XX");
			return attachmentDTO;
		}
		else
			return list.get(0);
		
	}

	@Override
	public boolean deleteAttachmentByShipmentHeaderId(Long id) throws BusinessException {
		int res1= 0 ;
		res1 = jdbcTemplate.update("DELETE FROM LT_SHIPMENT_ATTACHMENT  WHERE SHIPMENT_HEADER_ID = ? ", id);	
		if(res1!=0) {
			return true;
		}else {
			return false;
		}
		
	}

}
