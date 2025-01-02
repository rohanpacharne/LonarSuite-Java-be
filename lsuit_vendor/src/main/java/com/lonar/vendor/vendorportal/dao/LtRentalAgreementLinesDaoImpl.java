package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtInvoiceLines;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtRentalAgreementLinesRepository;

@Repository
@PropertySource(value = "classpath:queries/invoiceLinesQueries.properties", ignoreResourceNotFound = true)
public class LtRentalAgreementLinesDaoImpl implements LtRentalAgreementLinesDao,CodeMaster{
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtRentalAgreementLinesRepository ltRentalAgreementLinesRepository;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Long getLtRentalAgreementLinesCount(LtRentalAgreementLines input,Long id) throws ServiceException {
		// TODO Auto-generated method stub
		String query = env.getProperty("getLtRentalAgreementLinesCount");
		
		// Prepare the input parameters for query
		
	    if (input.getFromDate() == null) {
	        input.setFromDate(null);
	    }
	    
	    if (input.getToDate() == null) {
	        input.setToDate(null);
	    }

	    String baseRentAmount = null;
	    if (input.getBaseRentAmount() != null) {
	        baseRentAmount = "%"+String.valueOf(input.getBaseRentAmount()+"%");
	    }

	    String rentEscalationPer = null;
	    if (input.getRentEscalation() != null) {
	        rentEscalationPer = "%"+String.valueOf(input.getRentEscalation()+"%");
	    }

	    String rentAmount = null;
	    if (input.getRentAmount() != null) {
	        rentAmount = "%"+String.valueOf(input.getRentAmount()+"%");
	    }

	    String taxAmount = null;
	    if (input.getTaxAmount() != null) {
	        taxAmount = "%"+String.valueOf(input.getTaxAmount()+"%");
	    }

	    String totalAmount = null;
	    if (input.getTotalAmount() != null) {
	        totalAmount = "%"+String.valueOf(input.getTotalAmount()+"%");
	    }
//
//	    if (input.getColumnNo() == null || input.getColumnNo() == 0) {
//	        input.setColumnNo(18); // Default column sorting
//	    }
			String count  = (String)getJdbcTemplate().queryForObject(
					query, new Object[]{
		                    id,
		                    input.getFromDate(),
		                    input.getToDate(),
		                    baseRentAmount,
		                    rentEscalationPer,
		                    rentAmount,
		                    taxAmount,
		                    totalAmount}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtRentalAgreementLines> getLtRentalAgreementLinesDataTable(LtRentalAgreementLines input, Long id)
			throws ServiceException {
		System.out.println("in dao");
		String query = env.getProperty("getLtRentalAgreementLinesDatatable");
		
	    // Prepare the input parameters for query
		
	    if (input.getFromDate() == null) {
	        input.setFromDate(null);
	    }
	    
	    if (input.getToDate() == null) {
	        input.setToDate(null);
	    }

	    String baseRentAmount = null;
	    if (input.getBaseRentAmount() != null) {
	        baseRentAmount = "%"+String.valueOf(input.getBaseRentAmount()+"%");
	    }

	    String rentEscalationPer = null;
	    if (input.getRentEscalation() != null) {
	        rentEscalationPer = "%"+String.valueOf(input.getRentEscalation()+"%");
	    }

	    String rentAmount = null;
	    if (input.getRentAmount() != null) {
	        rentAmount = "%"+String.valueOf(input.getRentAmount()+"%");
	    }

	    String taxAmount = null;
	    if (input.getTaxAmount() != null) {
	        taxAmount = "%"+String.valueOf(input.getTaxAmount()+"%");
	    }

	    String totalAmount = null;
	    if (input.getTotalAmount() != null) {
	        totalAmount = "%"+String.valueOf(input.getTotalAmount()+"%");
	    }
//
//	    if (input.getColumnNo() == null || input.getColumnNo() == 0) {
//	        input.setColumnNo(18); // Default column sorting
//	    }
//
	    List<LtRentalAgreementLines> list = (List<LtRentalAgreementLines>) jdbcTemplate.query(
	            query,
	            new Object[]{
	                    id,
	                    input.getFromDate(),
	                    input.getToDate(),
	                    baseRentAmount,
	                    rentEscalationPer,
	                    rentAmount,
	                    taxAmount,
	                    totalAmount,
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getColumnNo(),
	                    input.getStart() + input.getLength(),
	                    input.getStart() + 1
	            },
	            new BeanPropertyRowMapper<>(LtRentalAgreementLines.class)
	    );
		return list;
	}

	@Override
	public Long save(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException {
		ltRentalAgreementLines.setLastUpdateDate(new Date());
		ltRentalAgreementLines.setLastUpdatedBy(ltRentalAgreementLines.getLastUpdateLogin());
		ltRentalAgreementLines = ltRentalAgreementLinesRepository.save(ltRentalAgreementLines);
		if(ltRentalAgreementLines.getAgreementLineId()!=null)
		return ltRentalAgreementLines.getAgreementLineId();
		else 
			return null;
	}

	@Override
	public LtRentalAgreementLines getRentalAgreementLineById(Long id) throws ServiceException {
		String query = env.getProperty("getRentalAgreementByLineId");
		List<LtRentalAgreementLines> list=   jdbcTemplate.query(query, new Object[]{ id }, 
				 new BeanPropertyRowMapper<LtRentalAgreementLines>(LtRentalAgreementLines.class)); 
		if(list.isEmpty())
			return null;
		else
		 return list.get(0);
	}

	@Override
	public List<LtRentalAgreementLines> getAllRentalAgreementLinesByHeaderId(Long id) throws ServiceException {
		String query = env.getProperty("getAllRentalAgreementLinesByHeaderId");
		List<LtRentalAgreementLines> list=   jdbcTemplate.query(query, new Object[]{ id }, 
				 new BeanPropertyRowMapper<LtRentalAgreementLines>(LtRentalAgreementLines.class)); 
		
			return list;
	}

	@Override
	public boolean deleteByAgreementHeaderId(Long agreementHeaderId) throws ServiceException {
		String query = env.getProperty("deleteLtRentalAgreementLinesByHeaderId");
		
		int res1=jdbcTemplate.update(query,agreementHeaderId);
		if(res1!=0) {
			return true;
		}else {  
			return false;
		}
	}

	@Override
	public boolean deleteByAgreementLineId(Long agreementLineId) {
		String query = env.getProperty("deleteLtRentalAgreementLinesByLineId");
		
		int res1=jdbcTemplate.update(query,agreementLineId);
		if(res1!=0) {
			return true;
		}else {  
			return false;
		}
	}

	@Override
	public boolean update(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException {
		System.out.println("Before save = "+ltRentalAgreementLines);
		ltRentalAgreementLines = ltRentalAgreementLinesRepository.save(ltRentalAgreementLines);
		System.out.println("After save = "+ltRentalAgreementLines);
		if(ltRentalAgreementLines.getAgreementHeaderId()!=null) {
			
			return true;
		}
		else
		return false;
	}

	
	
	

}
