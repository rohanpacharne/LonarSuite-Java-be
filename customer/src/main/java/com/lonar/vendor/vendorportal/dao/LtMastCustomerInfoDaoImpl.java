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

import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastCustomerRepository;

@Component
@PropertySource(value= "classpath:queries/customerQueries.properties",ignoreResourceNotFound = true)
public class LtMastCustomerInfoDaoImpl implements LtMastCustomerInfoDao {

	@Autowired
	private Environment env;

	@Autowired
	LtMastCustomerRepository ltMastCustomerRepository;

	private JdbcTemplate jdbcTemplate;

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<LtMastCustomer> getAllCustomerInfo() throws ServiceException {

		String query = env.getProperty("getAllCustomer");

		List<LtMastCustomer> listCustomer = jdbcTemplate.query(query, new Object[] {},
				new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class));

		return listCustomer;
	}

	@Override
	public LtMastCustomer getCustomerById(Long customerId) throws ServiceException {

		String query = env.getProperty("getCustomerById");

		List<LtMastCustomer> listCustomer = jdbcTemplate.query(query, new Object[] { customerId },
				new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class));

		if (!listCustomer.isEmpty())
			return listCustomer.get(0);
		else
			return null;
	}

	@Override
	public Long save(LtMastCustomer customer) throws ServiceException {
		customer.setCreationDate(new Date());
		customer.setLastUpdateDate(new Date());
		customer = ltMastCustomerRepository.save(customer);
		return customer.getCustomerId();
	}

	@Override
	public boolean update(LtMastCustomer customer) throws ServiceException {
		customer.setLastUpdateDate(new Date());
		customer = ltMastCustomerRepository.save(customer);
		if (customer.getCustomerId() != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Long customerId) throws ServiceException {
		/*String query = env.getProperty("deleteCustomerInfo");
		int res = 0;
		res = jdbcTemplate.update(query, customerId);
		if (res!= 0)*/
		ltMastCustomerRepository.delete(customerId);
		if(!ltMastCustomerRepository.exists(customerId)) {
			return true;
		}
		return false;
	}

	@Override
	public List<LtMastCustomer> getLtMastCustomerDataTable(Long companyId, LtMastCustomer input)
			throws ServiceException {

		String query = env.getProperty("getLtMastCustomerDataTable");

		String customerId = null;
		if (input.getCustomerId() != null && !input.getCustomerId().equals("")) {
			customerId = "%" + input.getCustomerId() + "%";
		}

		String customerName = null;
		if (input.getCustomerName() != null && !input.getCustomerName().equals("")) {
			customerName = "%" + input.getCustomerName().trim().toUpperCase() + "%";
		}

		String customerNumber = null;
		if (input.getCustomerNumber() != null && !input.getCustomerNumber().equals("")) {
			customerNumber = "%" + input.getCustomerNumber().trim().toUpperCase() + "%";
		}

		String status = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			status = "%" + input.getStatus().trim().toUpperCase() + "%";
		}

		String panNo = null;
		if (input.getPanNo() != null && !input.getPanNo().equals("")) {
			panNo = "%" + input.getPanNo().trim().toUpperCase() + "%";
		}
		
		String customerClassCode = null;
		if (input.getCustomerClassCode() != null && !input.getCustomerClassCode().equals("")) {
			customerClassCode = "%" + input.getCustomerClassCode().trim().trim().toUpperCase() + "%";
		}

		String legalStatus = null;
		if (input.getLegalStatus() != null && !input.getLegalStatus().equals("")) {
			legalStatus = "%" + input.getLegalStatus().trim().toUpperCase() + "%";
		}

		String customerType = null;
		if (input.getCustomerType() != null && !input.getCustomerType().equals("")) {
			customerType = "%" + input.getCustomerType().trim().trim().toUpperCase() + "%";
		}

		String customerGLClass = null;
		if (input.getCustomerGlClass() != null && !input.getCustomerGlClass().equals("")) {
			customerGLClass = "%" + input.getCustomerGlClass().trim().trim().toUpperCase() + "%";
		}

		String accountType = null;
		if (input.getAccountType() != null && !input.getAccountType().equals("")) {
			accountType = "%" + input.getCustomerType().trim().trim().toUpperCase() + "%";
		}
		if(input.getColumnNo()==0) {
			input.setColumnNo(9);
		}
		
		List<LtMastCustomer> list = (List<LtMastCustomer>) jdbcTemplate.query(query,
				new Object[] { companyId, customerNumber,customerName, panNo,customerClassCode,customerType,status, 
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo() , input.getColumnNo() ,
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo() , input.getColumnNo() ,
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo() , input.getColumnNo() ,
						(input.getStart() + input.getLength()), input.getStart()+1},
				new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class));

		return list;
	}

	@Override
	public Long getLtMastCustomerCountByInitiatorId(LtMastCustomer input, Long initiatorId) throws ServiceException {
		return null;
	}

	@Override
	public Long getLtMastCustomerInfoCount(Long companyId, LtMastCustomer input) throws ServiceException {
		String query = env.getProperty("getLtMastCustomerInfoCount");
		
		String customerName = null;
		if (input.getCustomerName() != null && !input.getCustomerName().equals("")) {
			customerName = "%" + input.getCustomerName().trim().toUpperCase() + "%";
		}

		String customerNumber = null;
		if (input.getCustomerNumber() != null && !input.getCustomerNumber().equals("")) {
			customerNumber = "%" + input.getCustomerNumber().trim().toUpperCase() + "%";
		}

		String status = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			status = "%" + input.getStatus().trim().toUpperCase() + "%";
		}

		String customerClassCode = null;
		if (input.getCustomerClassCode() != null && !input.getCustomerClassCode().equals("")) {
			customerClassCode = "%" + input.getCustomerClassCode().trim().trim().toUpperCase() + "%";
		}

		String legalStatus = null;
		if (input.getLegalStatus() != null && !input.getLegalStatus().equals("")) {
			legalStatus = "%" + input.getLegalStatus().trim().toUpperCase() + "%";
		}

		String customerType = null;
		if (input.getCustomerType() != null && !input.getCustomerType().equals("")) {
			customerType = "%" + input.getCustomerType().trim().trim().toUpperCase() + "%";
		}

		String customerGLClass = null;
		if (input.getCustomerGlClass() != null && !input.getCustomerGlClass().equals("")) {
			customerGLClass = "%" + input.getCustomerGlClass().trim().trim().toUpperCase() + "%";
		}

		String panNo = null;
		if (input.getPanNo() != null && !input.getPanNo().equals("")) {
			panNo = "%" + input.getPanNo().trim().toUpperCase() + "%";
		}
		
		String accountType = null;
		if (input.getAccountType() != null && !input.getAccountType().equals("")) {
			accountType = "%" + input.getCustomerType().trim().trim().toUpperCase() + "%";
		}
		
		String count = (String) getJdbcTemplate().queryForObject(query,
				new Object[] {companyId, customerNumber,customerName, panNo,customerClassCode,customerType,status},
				String.class);

		return Long.parseLong(count);
	}

	@Override
	public boolean getByCustomerNumber(LtMastCustomer ltMastCustomer) throws ServiceException {
		String query = env.getProperty("getByCustomerNumber");

		List<LtMastCustomer> list = jdbcTemplate.query(query,
				new Object[] { ltMastCustomer.getCustomerNumber(),ltMastCustomer.getCompanyId()  },
				new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class));
		boolean flag = false;

		if (!list.isEmpty() && list.size() > 0) {

			if (!list.get(0).getCustomerId().equals(ltMastCustomer.getCustomerId())) {
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean getByCustomerName(LtMastCustomer ltMastCustomer) throws ServiceException {
		String query = env.getProperty("getByCustomerName");

		List<LtMastCustomer> list = jdbcTemplate.query(query,
				new Object[] { ltMastCustomer.getCustomerName().toUpperCase(), ltMastCustomer.getCompanyId() },
				new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class));
		boolean flag = false;
		if (!list.isEmpty() && list.size() > 0) {
			if (!list.get(0).getCustomerId().equals(ltMastCustomer.getCustomerId())) {
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean getByPanNo(LtMastCustomer ltMastCustomer) throws ServiceException {
		String query = env.getProperty("getByPanNo");

		List<LtMastCustomer> list = jdbcTemplate.query(query,
				new Object[] { ltMastCustomer.getPanNo().toUpperCase(), ltMastCustomer.getCompanyId() },
				new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class));
		boolean flag = false;
		if (!list.isEmpty() && list.size() > 0) {
			if (!list.get(0).getCustomerId().equals(ltMastCustomer.getCustomerId())) {
				flag = true;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public List<LtMastCustomer> getAllActiveCustomerInfo(Long companyId) {
		String query = env.getProperty("getAllActiveCustomerInfo");

		List<LtMastCustomer> list = jdbcTemplate.query(query,new Object[] {companyId},
				new BeanPropertyRowMapper<LtMastCustomer>(LtMastCustomer.class));
		return list;
	}

}
