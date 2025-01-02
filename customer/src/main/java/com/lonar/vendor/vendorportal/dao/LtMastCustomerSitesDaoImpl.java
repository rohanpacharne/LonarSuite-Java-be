package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lonar.vendor.vendorportal.model.LtMastCustomerSites;
import com.lonar.vendor.vendorportal.repository.LtMastCustomerSitesRepository;

@Repository
@PropertySource(value = "classpath:queries/customerSitesQueries.properties", ignoreResourceNotFound = true)
public class LtMastCustomerSitesDaoImpl implements LtMastCustomerSitesDao {

	@Autowired
	private LtMastCustomerSitesRepository ltMastCustomerSitesRepository;

	@Autowired
	private Environment env;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<LtMastCustomerSites> getAllCustomerSites() {

		String query = env.getProperty("getAllCustomerSites");
		List<LtMastCustomerSites> list = jdbcTemplate.query(query, new Object[] {},
				new BeanPropertyRowMapper<LtMastCustomerSites>(LtMastCustomerSites.class));

		return list;
	}

	@Override
	public Long getCustomerSitesCount(Long customerId,LtMastCustomerSites input) {
		String query = env.getProperty("getCustomerSitesCount");

		String siteNumber = null;
		if (input.getSiteNumber() != null && !input.getSiteNumber().equals("")) {
			siteNumber = "%" + input.getSiteNumber().toUpperCase() + "%";
		}

		String city = null;
		if (input.getCity() != null && !input.getCity().equals("")) {
			city = "%" + input.getCity().toUpperCase() + "%";
		}
		
		String state = null;
		if (input.getStateName() != null && !input.getStateName().equals("")) {
			state = "%" + input.getStateName().toUpperCase() + "%";
		}
		
		String country = null;
		if (input.getCountry() != null && !input.getCountry().equals("")) {
			country = "%" + input.getCountry().toUpperCase() + "%";
		}
		
		String addr = null;
		if (input.getAddress() != null && !input.getAddress().equals("")) {
			addr = "%" + input.getAddress().toUpperCase() + "%";
		}
		
		String status = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			status = "%" + input.getStatus().toUpperCase() + "%";
		}

		String count = (String) jdbcTemplate.queryForObject(query, new Object[] { customerId,siteNumber,city, state,country,addr,  status}, 
				String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtMastCustomerSites> getCustomerSitesDataTable(Long customerId,LtMastCustomerSites input) {
		String query = env.getProperty("getCustomerSitesDataTable");

		String siteNumber = null;
		if (input.getSiteNumber() != null && !input.getSiteNumber().equals("")) {
			siteNumber = "%" + input.getSiteNumber().toUpperCase() + "%";
		}

		String city = null;
		if (input.getCity() != null && !input.getCity().equals("")) {
			city = "%" + input.getCity().toUpperCase() + "%";
		}
		
		String state = null;
		if (input.getStateName() != null && !input.getStateName().equals("")) {
			state = "%" + input.getStateName().toUpperCase().trim() + "%";
		}
		
		String country = null;
		if (input.getCountry() != null && !input.getCountry().equals("")) {
			country = "%" + input.getCountry().toUpperCase() + "%";
		}
		
		String addr = null;
		if (input.getAddress() != null && !input.getAddress().equals("")) {
			addr = "%" + input.getAddress().toUpperCase() + "%";
		}
		
		String status = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			status = "%" + input.getStatus().toUpperCase() + "%";
		}
		
		List<LtMastCustomerSites> list = (List<LtMastCustomerSites>) jdbcTemplate.query(query,
				new Object[] { customerId,siteNumber,city, state,country,addr,  status, 
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), 
						input.getColumnNo(), input.getColumnNo(), 
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), 
						input.getColumnNo(), input.getColumnNo(), 
						input.getStart() + input.getLength(), input.getStart()+1 },
				new BeanPropertyRowMapper<LtMastCustomerSites>(LtMastCustomerSites.class));
		return list;
	}

	@Override
	public LtMastCustomerSites getCustomerSiteById(Long customerSiteId) {
		String query = env.getProperty("getCustomerSiteById");
		List<LtMastCustomerSites> ltMastCustomerList = jdbcTemplate.query(query, new Object[] { customerSiteId },
				new BeanPropertyRowMapper<LtMastCustomerSites>(LtMastCustomerSites.class));

		if (!ltMastCustomerList.isEmpty())
			return ltMastCustomerList.get(0);
		else
			return null;
	}

	@Override
	public Long save(LtMastCustomerSites ltMastCustomerSites) {
		ltMastCustomerSites.setCreationDate(new Date());
		ltMastCustomerSites.setLastUpdateDate(new Date());
		ltMastCustomerSites = ltMastCustomerSitesRepository.save(ltMastCustomerSites);
		return ltMastCustomerSites.getCustomerSiteId();
	}

	@Override
	public boolean update(LtMastCustomerSites ltMastCustomerSites) throws ServiceException {
		ltMastCustomerSites.setLastUpdateDate(new Date());
		ltMastCustomerSites = ltMastCustomerSitesRepository.save(ltMastCustomerSites);
		if (ltMastCustomerSites.getCustomerId() != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Long customerSiteId) throws ServiceException {
		String query = env.getProperty("deleteCustomerInfo");
		int res = jdbcTemplate.update(query, customerSiteId);
		if (res == 1)
			return true;
		return false;
	}

	@Override
	public List<LtMastCustomerSites> getCustomerSiteByCustomerId(Long customerSiteId) throws ServiceException {
		String query = env.getProperty("getCustomerSiteByCustomerId");
		List<LtMastCustomerSites> ltMastCustomerList = jdbcTemplate.query(query, new Object[] { customerSiteId },
				new BeanPropertyRowMapper<LtMastCustomerSites>(LtMastCustomerSites.class));
		return ltMastCustomerList;
	}

	@Override
	public boolean deleteByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("deleteLtMastCustomerSitesByCustomerId");
		int res = 0;
		jdbcTemplate.update(query, customerId);
		if (res != 0)
			return true;
		return false;
	}

}
