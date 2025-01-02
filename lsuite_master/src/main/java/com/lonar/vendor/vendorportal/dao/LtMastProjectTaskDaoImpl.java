package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;

@Component
@PropertySource(value = "classpath:branchMasterQueries.properties", ignoreResourceNotFound = true)
public class LtMastProjectTaskDaoImpl implements LtMastProjectTaskDao {
	
	@Autowired
	private Environment env;
	
	@PersistenceContext(name = "em")
	private EntityManager em;
 
	private JdbcTemplate jdbcTemplate;
 
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<LtMastProjectTasks> checkProjectTaskValuesDetails(LtMastProjectTasks ltMastProjectTasks) {
		String query = env.getProperty("checkProjectTaskValuesDetails");
		List<LtMastProjectTasks> list=   jdbcTemplate.query(query, new Object[]{ ltMastProjectTasks.getTaskCode() },
				 new BeanPropertyRowMapper<LtMastProjectTasks>(LtMastProjectTasks.class));
		return list;
	}
	
	@Override
	public List<LtMastProjectTasks> findByProjectId(Long projectId) throws Exception {
		String query = env.getProperty("findLtMastProjectTasksByProjectId");
		
		List<LtMastProjectTasks> list=   jdbcTemplate.query(query, new Object[]{ projectId },
				 new BeanPropertyRowMapper<LtMastProjectTasks>(LtMastProjectTasks.class));
	
		return list;
		
	}

}
