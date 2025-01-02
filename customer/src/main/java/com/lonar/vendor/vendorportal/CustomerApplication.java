package com.lonar.vendor.vendorportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.lonar.vendor.vendorportal.config.AppConfig;

@SpringBootApplication
public class CustomerApplication extends SpringBootServletInitializer{

	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(new Class[] { CustomerApplication.class,  AppConfig.class });
	    	// return application.sources( VendorportalApplication.class);
	    }
	 
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
