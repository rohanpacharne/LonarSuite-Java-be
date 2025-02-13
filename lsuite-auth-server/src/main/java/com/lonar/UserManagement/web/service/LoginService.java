package com.lonar.UserManagement.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.lonar.UserManagement.common.ServiceException;
import com.lonar.UserManagement.web.model.AuthTokenInfo;
import com.lonar.UserManagement.web.model.LtMastUsers;
import com.lonar.UserManagement.web.model.Menu;
import com.lonar.UserManagement.web.model.ResponceEntity;
import com.lonar.UserManagement.web.model.Status;

public interface LoginService {

	ResponceEntity loginUser(LtMastUsers user,  HttpServletResponse response) ;
	ResponceEntity loginUserForMobile(LtMastUsers user,  HttpServletResponse response) ;

	ResponceEntity logoutUser (HttpServletRequest request, HttpServletResponse response);
	String tokenExtend(String token);
	
	AuthTokenInfo getTokenTimeDifferance(String token);
	Status changePassword(String jsonInputString) throws ParseException;
	Status emailValidater(String id);
	Status resetPassword(String jsonInputString);
	Status changePasswordUtility(String jsonInputString) throws ServiceException;
	List<Menu> getModulesByUserId(Long userId, Long companyId,String moduleType, String searchTerm);
	
}
