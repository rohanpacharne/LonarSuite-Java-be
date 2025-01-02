package com.lonar.vendor.vendorportal.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lonar.vendor.vendorportal.dao.UserDao;


@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	Oath2Request oath2Request;
	
	static final Logger logger = Logger.getLogger(RequestInterceptor.class);
	
	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String projectName = "/UserManagement";
	 
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpResponse, Object handler)
			throws Exception {
		HttpServletRequest request = (HttpServletRequest)httpServletRequest;
		final HttpServletResponse response = (HttpServletResponse) httpResponse;
		
		String token = request.getHeader(HEADER_AUTHORIZATION);
		//logger.error("Error in  "+request.getRequestURI());
		String requestUri = request.getRequestURI();
		 
		if(token != null && !requestUri.equals(projectName+"/oauth/token") 
	    			&& !requestUri.equals(projectName+"/oauth/check_token") 
	    			&& !requestUri.equals(projectName+"/oauth/dologin")
	    			&& !requestUri.equals(projectName+"/oauth/changePassword")
	    			&& !requestUri.equals(projectName+"/oauth/changePasswordUtility")
	    			&& !requestUri.equals(projectName+"/oauth/resetPassword")
	    			&& !requestUri.contains(projectName+"/oauth/emailValidater/")
	    			&& !requestUri.equals(projectName+"/oauth/changePasswordByReset")
	    			&&	!requestUri.equals(projectName+"/oauth/tokenextend") ) {

			response.setHeader("access_token", token.split(" ")[1]);
			AuthTokenInfo tokenTime =  userDao.getTokenTimeDifferance(token.split(" ")[1]);
			if(tokenTime != null &&  tokenTime.getExpires_in() > 360000)  {
				// Token Expire
			}else if(tokenTime != null && tokenTime.getExpires_in() > 180000 )  {
				 AuthTokenInfo tokenInfo =  oath2Request.sendRefreshTokenRequest(tokenTime.getRefresh_token());
				 userDao.updateLoginToken(tokenInfo, token.split(" ")[1]);
				 response.setHeader("access_token", tokenInfo.getAccess_token());
				 
			} 
		}
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
}