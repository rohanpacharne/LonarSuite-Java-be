package com.lonar.asn.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.CodeMaster;

@Repository
@PropertySource(value = "classpath:queries/asnQueries.properties", ignoreResourceNotFound = true)
public class LtShipmentInboxDaoImpl implements LtShipmentInboxDao,CodeMaster{

	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	} 
	
	@Override
	public Long getShipmentCount(String status, String approvalId, AsnApproval input) throws BusinessException {
		String query = env.getProperty("getShipmentCountInbox");
		
		String shipmentNum=null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{
			shipmentNum="%"+input.getShipmentNum().trim().toUpperCase()+"%";
		}
		
		String status2=null;
		if(input.getStatus()!=null && !input.getStatus().equals(""))
		{
			status2="%"+input.getStatus().trim().toUpperCase()+"%";
		}
		String vendorName=null;
		if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		{
			vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";
		}else {
			vendorName="%"+"%";
		}
		
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { status,ASN_APPROVED, approvalId,approvalId,status2, shipmentNum,
						vendorName}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<AsnApproval> getShipmentByStatus(String status, String approvalId, AsnApproval input)
			throws BusinessException {
		String shipmentNum=null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{
			shipmentNum="%"+input.getShipmentNum().trim().toUpperCase()+"%";
		}
		
		String status2=null;
		if(input.getStatus()!=null && !input.getStatus().equals(""))
		{
			status2="%"+input.getStatus().trim().toUpperCase()+"%";
		}
		String vendorName=null;
		if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		{
			vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";
		}else {
			vendorName="%"+"%";
		}
		
		if(input.getSort()==null)
		{
			input.setSort("desc");
		}
		
		
		String query = env.getProperty("getShipmentInbox");
		
		return (List<AsnApproval>) 
				jdbcTemplate.query(query , new Object[]{ status,ASN_APPROVED, approvalId,approvalId,status2, shipmentNum,
						vendorName,
						input.getColumnNo(),input.getColumnNo(),	
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),	
						
						(input.getStart()+input.getLength()),input.getStart()+1  },
			 new  BeanPropertyRowMapper<AsnApproval>(AsnApproval.class));
	}

}
