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

import com.lonar.vendor.vendorportal.model.LtMastCustSiteCommercials;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastCustSiteCommercialsRepository;

@Repository
@PropertySource(value = "classpath:queries/customerSiteCommercialQueries.properties", ignoreResourceNotFound = true)
public class LtMastCustomerSiteCommercialsDaoImpl implements LtMastCustomerSiteCommercialsDao {

	@Autowired
	private Environment env;
	
	@Autowired
	LtMastCustSiteCommercialsRepository ltMastCustSiteCommercialsRepository;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<LtMastCustSiteCommercials> getAllCustSiteCommercials() throws ServiceException {
		String query = env.getProperty("getAllCustSiteCommercials");
		List<LtMastCustSiteCommercials> list=   jdbcTemplate.query(query, new Object[]{  }, 
				 new BeanPropertyRowMapper<LtMastCustSiteCommercials>(LtMastCustSiteCommercials.class)); 
		return list;
	}

	@Override
	public List<LtMastCustSiteCommercials> getByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("getLtMastCustSiteCommercialsByCustomerId");
		List<LtMastCustSiteCommercials> list=   jdbcTemplate.query(query, new Object[]{ customerId}, 
				 new BeanPropertyRowMapper<LtMastCustSiteCommercials>(LtMastCustSiteCommercials.class)); 
		return list;
	}

	@Override
	public List<LtMastCustSiteCommercials> getByCustomerSiteId(Long customerSiteId) throws ServiceException {
		String query = env.getProperty("getLtMastCustSiteCommercialsByCustomerSiteId");
		List<LtMastCustSiteCommercials> list=   jdbcTemplate.query(query, new Object[]{ customerSiteId }, 
				 new BeanPropertyRowMapper<LtMastCustSiteCommercials>(LtMastCustSiteCommercials.class)); 
		return list;
	}

	@Override
	public LtMastCustSiteCommercials getBySiteCommercialId(Long siteCommercialId) throws ServiceException {
		String query = env.getProperty("getLtMastCustSiteCommercialsBySiteCommercialId");
		List<LtMastCustSiteCommercials> list=   jdbcTemplate.query(query, new Object[]{ siteCommercialId }, 
				 new BeanPropertyRowMapper<LtMastCustSiteCommercials>(LtMastCustSiteCommercials.class)); 
		if( !list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public boolean save(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException {
		ltMastCustSiteCommercials.setCreationDate(new Date());
		ltMastCustSiteCommercials.setLastUpdateDate(new Date());
		ltMastCustSiteCommercials = ltMastCustSiteCommercialsRepository.save(ltMastCustSiteCommercials);
		if(ltMastCustSiteCommercials.getSiteCommercialId()!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean update(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException {
		ltMastCustSiteCommercials.setLastUpdateDate(new Date());
		ltMastCustSiteCommercials = ltMastCustSiteCommercialsRepository.save(ltMastCustSiteCommercials);
		if(ltMastCustSiteCommercials.getSiteCommercialId()!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Long siteCommercialId) throws ServiceException {
		ltMastCustSiteCommercialsRepository.delete(siteCommercialId);
		if(ltMastCustSiteCommercialsRepository.exists(siteCommercialId))
			return false;
		else
			return true;
	}

	@Override
	public boolean deleteByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("deleteLtMastCustSiteCommercialsByCustomerId");
		int res = 0;
		jdbcTemplate.update(query, customerId);
		if (res != 0)
			return true;
		return false;
	}

	@Override
	public Long getCustomerCommercialsDataTableCount(Long customerSiteId, LtMastCustSiteCommercials input)
			throws ServiceException {
		String query = env.getProperty("getCustomerCommercialsDataTableCount");

		String paymentTermsStr = null;
		if (input.getTermName() != null && !input.getTermName().equals("") ) {
			paymentTermsStr = "%" + input.getTermName().toUpperCase() + "%";
		}

		String collectorStr = null;
		if (input.getCollectorName() != null && !input.getCollectorName().equals("")) {
			collectorStr = "%" + input.getCollectorName().toUpperCase() + "%";
		}

		String creditCurrencyStr = null;
		if (input.getCreditCurrency() != null && !input.getCreditCurrency().equals("")) {
			creditCurrencyStr = "%" + input.getCreditCurrency().toUpperCase() + "%";
		}

		String creditHoldStr = null;
		if (input.getCreditHold() != null && !input.getCreditHold().equals("")) {
			creditHoldStr = "%" + input.getCreditHold().toUpperCase() + "%";
		}

		String creditLimitStr = null;
		if (input.getCreditLimit() != null) {
			String doubleAsString = String.valueOf(input.getCreditLimit());
			int indexOfDecimal = doubleAsString.indexOf(".");
			creditLimitStr = "%" + doubleAsString.substring(0, indexOfDecimal) + "%";
		}
		
		String count = (String) jdbcTemplate.queryForObject(query,
				new Object[] { customerSiteId, paymentTermsStr, collectorStr, creditCurrencyStr, creditHoldStr, creditLimitStr }, String.class);
		
		return Long.parseLong(count);
	}

	@Override
	public List<LtMastCustSiteCommercials> getCustomerCommercialsDataTable(Long customerSiteId,
			LtMastCustSiteCommercials input) throws ServiceException {

		String query = env.getProperty("getCustomerCommercialsDataTable");

		String paymentTermsStr = null;
		if (input.getTermName() != null && !input.getTermName().equals("") ) {
			paymentTermsStr = "%" + input.getTermName().toUpperCase() + "%";
		}

		String collectorStr = null;
		if (input.getCollectorName() != null && !input.getCollectorName().equals("")) {
			collectorStr = "%" + input.getCollectorName().toUpperCase() + "%";
		}

		String creditCurrencyStr = null;
		if (input.getCreditCurrency() != null && !input.getCreditCurrency().equals("")) {
			creditCurrencyStr = "%" + input.getCreditCurrency().toUpperCase() + "%";
		}

		String creditHoldStr = null;
		if (input.getCreditHold() != null && !input.getCreditHold().equals("")) {
			creditHoldStr = "%" + input.getCreditHold().toUpperCase() + "%";
		}

		String creditLimitStr = null;
		if (input.getCreditLimit() != null) {
			String doubleAsString = String.valueOf(input.getCreditLimit());
			int indexOfDecimal = doubleAsString.indexOf(".");
			creditLimitStr = "%" + doubleAsString.substring(0, indexOfDecimal) + "%";
		}

		List<LtMastCustSiteCommercials> list = (List<LtMastCustSiteCommercials>) jdbcTemplate.query(query,
				new Object[] { customerSiteId, paymentTermsStr, collectorStr, creditCurrencyStr, creditHoldStr, creditLimitStr,
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getStart() + input.getLength(), input.getStart()+1
		},
				new BeanPropertyRowMapper<LtMastCustSiteCommercials>(LtMastCustSiteCommercials.class));
		return list;
	}

}
