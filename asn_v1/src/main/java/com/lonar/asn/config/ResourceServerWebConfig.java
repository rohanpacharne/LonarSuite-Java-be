package com.lonar.asn.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.lonar.asn.model.LtMastCommonMessage;
import com.lonar.asn.repository.LtMastCommonMessageRepository;
import com.lonar.asn.service.LtMastCommonMessageService;


@Configuration
@EnableWebMvc
@ComponentScan({ "com.lonar.asn" })
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {
	  //LtMastCommonMessageDaoImpl   commonMessageDao = new LtMastCommonMessageDaoImpl();

	 @Autowired
	  LtMastCommonMessageRepository ltMastCommonMessageRepository;
	  
	  @Autowired
	  LtMastCommonMessageService ltMastCommonMessageService;
	 
	 
	   public static Map<Integer,String> messages = new HashMap<Integer,String>();
	  
    @Bean
    public Map getAllMessages() {
		try
		{
		  //Map<Integer,String> messages=new HashMap<Integer,String>();
			List<LtMastCommonMessage> messageList = (List<LtMastCommonMessage>) ltMastCommonMessageRepository.findAll();
			 Iterator<LtMastCommonMessage> itr=messageList.iterator();
				while(itr.hasNext())
				{
					LtMastCommonMessage ltMastCommonMessage=itr.next();
					messages.put(Integer.parseInt(ltMastCommonMessage.getMessageCode()),ltMastCommonMessage.getMessageDesc());
				}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        return messages;
    }
}
