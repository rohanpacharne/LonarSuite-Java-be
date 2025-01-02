package com.lonar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan({"com.lonar", "com.lonar.mast.dao","com.lonar.mast.model"
	,"com.lonar.mast.service","com.lonar.mast.controller"})
public class LexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LexaApplication.class, args);
	}

}
