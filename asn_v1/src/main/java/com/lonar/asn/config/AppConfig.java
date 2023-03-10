package com.lonar.asn.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@EntityScan("com.lonar.asn.model")
@PropertySource({ "classpath:persistence.properties" })
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "com.lonar.asn.repository")
public class AppConfig {
	
	@Autowired
	private Environment env;
	
    @Bean
  	public DataSource dataSource() {
    	final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName(env.getProperty("db.driver"));
    	dataSource.setUsername(env.getProperty("db.username"));
    	dataSource.setPassword(env.getProperty("db.password"));
    	dataSource.setUrl(env.getProperty("db.url"));  		
  		return dataSource;
  	}
}