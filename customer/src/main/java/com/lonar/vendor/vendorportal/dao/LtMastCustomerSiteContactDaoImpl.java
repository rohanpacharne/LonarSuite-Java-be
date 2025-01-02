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

import com.lonar.vendor.vendorportal.model.LtMastCustSiteContacts;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastCustSiteContactsRepository;

@Repository
@PropertySource(value = "classpath:queries/customerSiteContactQueries.properties", ignoreResourceNotFound = true)
public class LtMastCustomerSiteContactDaoImpl implements LtMastCustomerSiteContactDao{

	@Autowired
	private Environment env;
	
	@Autowired
	LtMastCustSiteContactsRepository ltMastCustSiteContactsRepository;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<LtMastCustSiteContacts> getAllCustSiteContacts() throws ServiceException {
		String query = env.getProperty("getAllCustSiteContacts");
		List<LtMastCustSiteContacts> list=   jdbcTemplate.query(query, new Object[]{  }, 
				 new BeanPropertyRowMapper<LtMastCustSiteContacts>(LtMastCustSiteContacts.class)); 
		return list;
	}

	@Override
	public List<LtMastCustSiteContacts> getContactsByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("getContactsByCustomerId");
		List<LtMastCustSiteContacts> list=   jdbcTemplate.query(query, new Object[]{ customerId}, 
				 new BeanPropertyRowMapper<LtMastCustSiteContacts>(LtMastCustSiteContacts.class)); 
		return list;
	}

	@Override
	public List<LtMastCustSiteContacts> getContactsByCustomerSiteId(Long customerSiteId) throws ServiceException {
		String query = env.getProperty("getCustSiteContactsByCustomerSiteId");
		List<LtMastCustSiteContacts> list=   jdbcTemplate.query(query, new Object[]{ customerSiteId}, 
				 new BeanPropertyRowMapper<LtMastCustSiteContacts>(LtMastCustSiteContacts.class)); 
		return list;
	}

	@Override
	public LtMastCustSiteContacts getCustSiteContactsById(Long siteContactId) throws ServiceException {
		String query = env.getProperty("getCustSiteContactsBySiteContactId");
		List<LtMastCustSiteContacts> list=   jdbcTemplate.query(query, new Object[]{ siteContactId }, 
				 new BeanPropertyRowMapper<LtMastCustSiteContacts>(LtMastCustSiteContacts.class)); 
		if( !list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public boolean save(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException {
		ltMastCustSiteContacts.setCreationDate(new Date());
		ltMastCustSiteContacts.setLastUpdateDate(new Date());
		ltMastCustSiteContacts = ltMastCustSiteContactsRepository.save(ltMastCustSiteContacts);
		if(ltMastCustSiteContacts.getSiteContactId()!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean update(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException {
		ltMastCustSiteContacts.setLastUpdateDate(new Date());
		ltMastCustSiteContacts = ltMastCustSiteContactsRepository.save(ltMastCustSiteContacts);
		if(ltMastCustSiteContacts.getSiteContactId()!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Long siteContactId) throws ServiceException {
		ltMastCustSiteContactsRepository.delete(siteContactId);
		if(ltMastCustSiteContactsRepository.exists(siteContactId))
			return false;
		else
			return true;
	}

	@Override
	public boolean deleteByCustomerId(Long customerId) throws ServiceException {
		String query = env.getProperty("deleteLtMastCustSiteContactsByCustomerId");
		int res = 0;
		jdbcTemplate.update(query, customerId);
		if (res != 0)
			return true;
		return false;
	}

	@Override
	public Long getByCustomerSiteContactsDataTableCount(Long customerSiteId, LtMastCustSiteContacts input)
			throws ServiceException {
		String query = env.getProperty("getByCustomerSiteContactsDataTableCount");

		String firstNameStr = null;
		if (input.getFirstName() != null && !input.getFirstName().equals("")) {
			firstNameStr = "%" + input.getFirstName().toUpperCase() + "%";
		}

		String lastNameStr = null;
		if (input.getLastName() != null && !input.getLastName().equals("")) {
			lastNameStr = "%" + input.getLastName().toUpperCase() + "%";
		}

		String designationStr = null;
		if (input.getDesignationValue() != null && !input.getDesignationValue().equals("")) {
			designationStr = "%" + input.getDesignationValue().toUpperCase() + "%";
		}

		String contactNoStr = null;
		if (input.getContactNumber() != null && !input.getContactNumber().equals("")) {
			contactNoStr = "%" + input.getContactNumber().toUpperCase() + "%";
		}

		String emailStr = null;
		if (input.getEmailId() != null && !input.getEmailId().equals("")) {
			emailStr = "%" + input.getEmailId().toUpperCase() + "%";
		}
		
		String count = (String) jdbcTemplate.queryForObject(query,
				new Object[] { customerSiteId, firstNameStr, lastNameStr, designationStr, contactNoStr, emailStr }, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtMastCustSiteContacts> getByCustomerSiteContactsDataTable(Long customerSiteId,
			LtMastCustSiteContacts input) throws ServiceException {
		
		String query = env.getProperty("getByCustomerSiteContactsDataTable");

		String firstNameStr = null;
		if (input.getFirstName() != null && !input.getFirstName().equals("")) {
			firstNameStr = "%" + input.getFirstName().toUpperCase() + "%";
		}

		String lastNameStr = null;
		if (input.getLastName() != null && !input.getLastName().equals("")) {
			lastNameStr = "%" + input.getLastName().toUpperCase() + "%";
		}

		String designationStr = null;
		if (input.getDesignationValue() != null && !input.getDesignationValue().equals("")) {
			designationStr = "%" + input.getDesignationValue().toUpperCase() + "%";
		}

		String contactNoStr = null;
		if (input.getContactNumber() != null && !input.getContactNumber().equals("")) {
			contactNoStr = "%" + input.getContactNumber().toUpperCase() + "%";
		}

		String emailStr = null;
		if (input.getEmailId() != null && !input.getEmailId().equals("")) {
			emailStr = "%" + input.getEmailId().toUpperCase() + "%";
		}
		

		List<LtMastCustSiteContacts> list = (List<LtMastCustSiteContacts>) jdbcTemplate.query(query,
				new Object[] { customerSiteId, firstNameStr, lastNameStr, designationStr, contactNoStr, emailStr,
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),
						input.getStart() + input.getLength(), input.getStart()+1 },
				new BeanPropertyRowMapper<LtMastCustSiteContacts>(LtMastCustSiteContacts.class));
		return list;
	}

}
