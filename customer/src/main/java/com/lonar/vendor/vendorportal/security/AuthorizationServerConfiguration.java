package com.lonar.vendor.vendorportal.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.lonar.vendor.vendorportal.service.MyUserDetailsService;

@Configuration
@EnableAuthorizationServer
@PropertySource({ "classpath:persistence.properties" })
public class AuthorizationServerConfiguration extends  AuthorizationServerConfigurerAdapter {
	
/*	@Autowired
	private Environment env;*/
	
	 @Autowired
	 private DataSource dataSource;
	 
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//private UserDetailsService userDetailsService;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
   
	public AuthorizationServerConfiguration() {
		super();
	}
	
	
	 
	@Bean
	public TokenStore tokenStore() {
		//return new InMemoryTokenStore();
		//return new JwtTokenStore(accessTokenConverter());//JWT
		return new JdbcTokenStore(dataSource);
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenService() {
		final DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		return tokenServices;
	}
	
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.jdbc(dataSource);
	}
	
	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
			//.accessTokenConverter(accessTokenConverter()); //JWT
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("permitAll()");
		super.configure(security);
	}
	
}
