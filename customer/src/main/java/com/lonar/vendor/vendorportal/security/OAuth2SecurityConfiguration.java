package com.lonar.vendor.vendorportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
/*import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;*/

public class OAuth2SecurityConfiguration /*extends WebSecurityConfigurerAdapter */{

	/*@Autowired
	private ClientDetailsService clientDetailsService;
	
	   @Autowired
	   private UserDetailsService userDetailsService;
	
	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
	TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
	handler.setTokenStore(tokenStore);
	handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
	handler.setClientDetailsService(clientDetailsService);
	return handler;
	}
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.anonymous().disable()
		 	.authorizeRequests()
		 	.antMatchers("/oauth/token").permitAll();
	   }
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	//final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailsService);
	authProvider.setPasswordEncoder(encoder());
	return authProvider;
	}
	
	@Bean
	public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder(11);
	}*/
}
