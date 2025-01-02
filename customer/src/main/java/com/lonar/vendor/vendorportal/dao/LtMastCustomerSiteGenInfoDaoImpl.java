package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.LtMastCustSiteGenInfo;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastCustomerRepository;
import com.lonar.vendor.vendorportal.repository.LtMastCustomerSitesGenInfoRepository;


@Component
@PropertySource(value= "classpath:queries/siteGenInfoQuery.properties",ignoreResourceNotFound = true)
public class LtMastCustomerSiteGenInfoDaoImpl implements LtMastCustomerSiteGenInfoDao{

	@Autowired
	private Environment env;

	@Autowired
	LtMastCustomerRepository ltMastCustomerRepository;
	
	@Autowired
	private LtMastCustomerSitesGenInfoRepository ltMastCustomerSitesGenInfoRepository;

	private JdbcTemplate jdbcTemplate;
 
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public LtMastCustSiteGenInfo getBySiteGenInfoId(Long siteGenInfoId) throws ServiceException  {

		String query = env.getProperty("getBySiteGenInfoId");

		List<LtMastCustSiteGenInfo> ltMastCustSiteGenInfo = jdbcTemplate.query(query, new Object[] { siteGenInfoId },
				new BeanPropertyRowMapper<LtMastCustSiteGenInfo>(LtMastCustSiteGenInfo.class));

		if (!ltMastCustSiteGenInfo.isEmpty())
			return ltMastCustSiteGenInfo.get(0);
		else
			return null;
	}

	@Override
	public List<LtMastCustSiteGenInfo> getBycustomerSiteId(Long customerSiteId) throws ServiceException  {
		String query = env.getProperty("getBycustomerSiteId");

		List<LtMastCustSiteGenInfo> ltMastCustSiteGenInfoList = jdbcTemplate.query(query, new Object[] { customerSiteId },
				new BeanPropertyRowMapper<LtMastCustSiteGenInfo>(LtMastCustSiteGenInfo.class));
		return ltMastCustSiteGenInfoList;

		
	}

	@Override
	public List<LtMastCustSiteGenInfo> getBycustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("getBycustomerId");

		List<LtMastCustSiteGenInfo> ltMastCustSiteGenInfoList = jdbcTemplate.query(query, new Object[] { customerId },
				new BeanPropertyRowMapper<LtMastCustSiteGenInfo>(LtMastCustSiteGenInfo.class));
		return ltMastCustSiteGenInfoList;

	}

	@Override
	public Long save(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException {
		ltMastCustSiteGenInfo.setCreationDate(new Date());
		ltMastCustSiteGenInfo.setLastUpdateDate(new Date());
		ltMastCustSiteGenInfo = ltMastCustomerSitesGenInfoRepository.save(ltMastCustSiteGenInfo);
		return ltMastCustSiteGenInfo.getSiteGenInfoId();
	}

	@Override
	public boolean update(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException {
		ltMastCustSiteGenInfo.setLastUpdateDate(new Date());
		ltMastCustSiteGenInfo = ltMastCustomerSitesGenInfoRepository.save(ltMastCustSiteGenInfo);

		if (ltMastCustSiteGenInfo.getSiteGenInfoId() != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Long customerId) throws ServiceException{
		String query = env.getProperty("deleteCustomerSiteGenInfo");
		int res = jdbcTemplate.update(query, customerId);
		if (res == 1)
			return true;
		return false;
	}

	@Override
	public boolean deleteByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("deleteLtMastCustSiteGenInfoByCustomerId");
		int res = 0;
		res = jdbcTemplate.update(query, customerId);
		if (res != 0)
			return true;
		return false;
	}

	@Override
	public Long getByCustomerSiteDataTableCount(Long customerSiteId, LtMastCustSiteGenInfo input)
			throws ServiceException {
		String query = env.getProperty("getByCustomerSiteDataTableCount");

		String cseCodeStr = null;
		if (input.getCseCode() != null && !input.getCseCode().equals("")) {
			cseCodeStr = "%" + input.getCseCode().toUpperCase() + "%";
		}

		String transitDays = null;
		if (input.getTransitDays() != null) {
			String doubleAsString = String.valueOf(input.getTransitDays());
			int indexOfDecimal = doubleAsString.indexOf(".");
			transitDays = "%" + doubleAsString.substring(0, indexOfDecimal)+ "%";
		}

		String defaultOrderType = null;
		if (input.getDefaultOrderType() != null && !input.getDefaultOrderType().equals("")) {
			defaultOrderType = "%" + input.getDefaultOrderType().toUpperCase() + "%";
		}

		String freightTerms = null;
		if (input.getFreightTerms() != null && !input.getFreightTerms().equals("")) {
			freightTerms = "%" + input.getFreightTerms().toUpperCase() + "%";
		}

		String count = (String) jdbcTemplate.queryForObject(query,
				new Object[] { customerSiteId, cseCodeStr, transitDays, defaultOrderType, freightTerms }, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtMastCustSiteGenInfo> getByCustomerSiteDataTable(Long customerSiteId, LtMastCustSiteGenInfo input) {
		String query = env.getProperty("getByCustomerSiteDataTable");

		String cseCodeStr = null;
		if (input.getCseCode() != null && !input.getCseCode().equals("")) {
			cseCodeStr = "%" + input.getCseCode().toUpperCase() + "%";
		}

		String transitDays = null;
		if (input.getTransitDays() != null) {
			String doubleAsString = String.valueOf(input.getTransitDays());
			int indexOfDecimal = doubleAsString.indexOf(".");
			transitDays = "%" + doubleAsString.substring(0, indexOfDecimal) + "%";
		}

		String defaultOrderType = null;
		if (input.getDefaultOrderType() != null && !input.getDefaultOrderType().equals("")) {
			defaultOrderType = "%" + input.getDefaultOrderType().toUpperCase() + "%";
		}

		String freightTerms = null;
		if (input.getFreightTerms() != null && !input.getFreightTerms().equals("")) {
			freightTerms = "%" + input.getFreightTerms().toUpperCase() + "%";
		}

		List<LtMastCustSiteGenInfo> list = (List<LtMastCustSiteGenInfo>) jdbcTemplate.query(query,
				new Object[] { customerSiteId, cseCodeStr, transitDays, defaultOrderType, freightTerms,
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getStart() + input.getLength(), input.getStart()+1 },
				new BeanPropertyRowMapper<LtMastCustSiteGenInfo>(LtMastCustSiteGenInfo.class));
		return list;
	}
	 
}

