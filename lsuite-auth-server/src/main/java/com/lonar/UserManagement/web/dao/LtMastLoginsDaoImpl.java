package com.lonar.UserManagement.web.dao;


import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lonar.UserManagement.web.model.LtMastLogins;
import com.lonar.UserManagement.web.repository.LtMastLoginsRepository;

@Component
public class LtMastLoginsDaoImpl implements LtMastLoginsDao {
	
	@Autowired
	LtMastLoginsRepository ltMastLoginsRepository;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public LtMastLogins saveLoginDetails(LtMastLogins ltMastLogins) {
		// TODO Auto-generated method stub
		LtMastLogins ltMastLoginsCreated = ltMastLoginsRepository.save(ltMastLogins);
		return ltMastLoginsCreated;
	}

	@Override
	public boolean updateLoginDetails(LtMastLogins ltMastLogins) {
		// TODO Auto-generated method stub
//		String query = env.getProperty("updateLoginDetails");
		
		String query = "update lt_mast_logins "+
		"set logout_date = ?, status = ?, last_update_date = ?, "+
		"last_update_login = ?, last_updated_by = ? "+
		"where login_id = ? ";
		
		int res=0;
		
		res=jdbcTemplate.update(query,new Date(),"INACTIVE",new Date(),
				ltMastLogins.getUserId(),
				ltMastLogins.getUserId(),ltMastLogins.getLoginId());
		
		if(res!=0)
			return true;
		else
			return false;
	}

}
