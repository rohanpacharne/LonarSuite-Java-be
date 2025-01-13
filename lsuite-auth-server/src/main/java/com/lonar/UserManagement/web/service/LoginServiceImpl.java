package com.lonar.UserManagement.web.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.lonar.UserManagement.common.BusinessException;
import com.lonar.UserManagement.common.CommonMethod;
import com.lonar.UserManagement.common.ServiceException;
import com.lonar.UserManagement.web.controller.Oath2Request;
import com.lonar.UserManagement.web.dao.LtMastEmailtokenDao;
import com.lonar.UserManagement.web.dao.LtMastSysVariablesDao;
import com.lonar.UserManagement.web.dao.LtMastUserRolesDao;
import com.lonar.UserManagement.web.dao.LtMastUsersDao;
import com.lonar.UserManagement.web.dao.UserDao;
import com.lonar.UserManagement.web.model.AuthTokenInfo;
import com.lonar.UserManagement.web.model.CodeMaster;
import com.lonar.UserManagement.web.model.LtMastEmailtoken;
import com.lonar.UserManagement.web.model.LtMastPasswords;
import com.lonar.UserManagement.web.model.LtMastUsers;
import com.lonar.UserManagement.web.model.Menu;
import com.lonar.UserManagement.web.model.ResponceEntity;
import com.lonar.UserManagement.web.model.Status;
import com.lonar.UserManagement.web.model.SysVariableWithValues;
import com.lonar.UserManagement.web.repository.LtMastEmailtokenRepository;
import com.lonar.UserManagement.web.repository.LtMastPasswordsRepository;
import com.lonar.UserManagement.web.repository.LtMastUsersRepository;

@Service
@PropertySource(value = "classpath:serverurl.properties", ignoreResourceNotFound = true)
public class LoginServiceImpl implements LoginService, CodeMaster {

	@Autowired
	private Environment env;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	LtMastUserRolesDao ltMastUserRolesDao;
	
	@Autowired
	private Oath2Request oath2Request;
	
	@Autowired
	LtMastUsersDao ltMastUsersDao;
	
	@Autowired
	LtMastUsersRepository ltMastUsersRepository;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastPasswordsRepository ltMastPasswordsRepository;
	
	@Autowired
	LtMastEmailtokenRepository ltMastEmailtokenRepository;
	
	@Autowired
	LtMastPasswordsService ltMastPasswordsService;
	
	@Autowired
	LtMastEmailtokenDao ltMastEmailtokenDao;
	
	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;
	
	private static final String HEADER_AUTHORIZATION = "Authorization";
	
	@Override
	public ResponceEntity loginUser(LtMastUsers user, HttpServletResponse response) {
		try{
			ResponceEntity entity = null;
		List<LtMastUsers> ltP2pUsers = ltMastUsersDao.findByUserName(user.getUserName());
		System.out.println("line no 97 = "+ltP2pUsers);
		System.out.println("size is "+ltP2pUsers.size());
		if(!ltP2pUsers.isEmpty()) {
			if(ltP2pUsers.get(0).getStatus().equals("DRAFTCODE") || 
					ltP2pUsers.get(0).getStatus().toUpperCase().equals("INACTIVE")) {
				entity = new ResponceEntity();
				entity.setMessage("User is inactive");
				return  entity;
			}else if(ltP2pUsers.get(0).getStatus().equals("UNVERIFYEMAIL")){
				entity = new ResponceEntity();
				entity.setMessage("User status is unverifyemail, Please set the password!");
				} else {
                       System.out.println(user);
					if ((user.getLoginCheck() != null) && (!user.getLoginCheck().trim().isEmpty())
							&& (user.getLoginCheck().equals("SHA-256"))) {
						
						System.out.println(" GRN login ");
						String password = user.getPassword().substring(0, user.getPassword().length() - 5);
						String salt = BCrypt.gensalt(12);
						String serverBcryptedPwd = BCrypt.hashpw(password, salt);
						user.setPassword(serverBcryptedPwd);
						System.out.println(serverBcryptedPwd);
						user.setPassword(password);
						System.out.println(serverBcryptedPwd);
						
					}else{
						System.out.println(" vpal login "+user.getPassword()+"\t");
					}
							//if (BCrypt.checkpw(ltP2pUsers.get(0).getPassword().trim(), user.getPassword().trim())) {
					//if (BCrypt.checkpw(ltP2pUsers.get(0).getP, user.getPassword().trim())) {

					
					if (ltP2pUsers.get(0).getPassword().trim().equalsIgnoreCase(user.getPassword().trim())){
						entity = new ResponceEntity();
						response.setHeader("userId", ltP2pUsers.get(0).getUserId() + "");
						response.setHeader("employeeId", ltP2pUsers.get(0).getEmployeeId() + "");
						response.setHeader("employeeName", ltP2pUsers.get(0).getEmployeeName());
						response.setHeader("companyId", ltP2pUsers.get(0).getCompanyId() + "");
						AuthTokenInfo tokenInfo = oath2Request.sendTokenRequest(user.getUserName(), user.getPassword());
						//response.setHeader("access_token", tokenInfo.getAccess_token());
						response.setHeader("access_token","c2FtcGxlQ2xpZW50SWQ6c2VjcmV0");
						
						SysVariableWithValues sysVariableWithValues = new SysVariableWithValues();
						if(ltP2pUsers.get(0).getCompanyId()!=null) {
							sysVariableWithValues=
									ltMastSysVariablesService.getBySysVariableName1("GLOBAL_FILE_PATH", ltP2pUsers.get(0).getCompanyId());
						}else {
							sysVariableWithValues=
									ltMastSysVariablesService.getBySysVariableName1("GLOBAL_FILE_PATH", 1L);
						}
						
						
						System.out.println("sysVariableWithValues 1 = "+sysVariableWithValues);
						
						String globalFilePath = "";
						if(sysVariableWithValues!=null)
						{
							if(sysVariableWithValues.getLtMastSysVariableValues().get(0)!=null)
							{
								System.out.println("Above file path");
								globalFilePath=sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
								System.out.println("file path = "+globalFilePath);
							}
							else
							{
								globalFilePath=sysVariableWithValues.getLtMastSysVariables().getSystemValue();
							}
						}
						
						String entries = ltMastUsersDao.paginationEntries("PEGINATION_ENTRIES", ltP2pUsers.get(0).getUserId(), ltP2pUsers.get(0).getCompanyId());
						System.out.println("userId = "+ltP2pUsers.get(0).getUserId());
						System.out.println("Company id = "+ltP2pUsers.get(0).getCompanyId());
						
						entity.setPaginationEntries(entries);
						entity.setGlobalFilePath(globalFilePath);
						entity.setMessage("Login Success " + user.getUserName());
						entity.setUserId(ltP2pUsers.get(0).getUserId());
						entity.setEmployeeId(ltP2pUsers.get(0).getEmployeeId());
						entity.setEmployeeName(ltP2pUsers.get(0).getEmployeeName());
						entity.setDivisionId(ltP2pUsers.get(0).getDivisionId());
						entity.setLocationId(ltP2pUsers.get(0).getLocationId());
						entity.setLocationCode(ltP2pUsers.get(0).getLocationCode());
						entity.setDivisionName(ltP2pUsers.get(0).getDivisionName());
						entity.setCompanyId(ltP2pUsers.get(0).getCompanyId());
						entity.setIsBuyer(ltP2pUsers.get(0).getIsBuyer());
						entity.setCompanyName(ltP2pUsers.get(0).getCompanyName());
						if (ltP2pUsers.get(0).getVendorId() != null && !ltP2pUsers.get(0).getVendorId().equals(0)) {
							List<LtMastUsers> ltP2pUsers1 = ltMastUsersDao
									.findVendorByUserName(user.getUserName().toUpperCase());
							System.out.println("list p2pUser1 = "+ltP2pUsers1);
							// authTokenInfo.setEmpName(ltP2pUsers1.get(0).getEmpName());
							entity.setEmployeeName(ltP2pUsers1.get(0).getEmployeeName());
							entity.setVendorId(ltP2pUsers.get(0).getVendorId());
							entity.setDivisionId(ltP2pUsers1.get(0).getDivisionId());
							entity.setDivisionName(ltP2pUsers1.get(0).getDivisionName());
							entity.setCompanyId(ltP2pUsers1.get(0).getCompanyId());
							entity.setCompanyName(ltP2pUsers1.get(0).getCompanyName());
							entity.setPaginationEntries(ltMastUsersDao.paginationEntries("PEGINATION_ENTRIES", ltP2pUsers.get(0).getUserId(), ltP2pUsers1.get(0).getCompanyId()));
						}

						List<String> roles = ltMastUserRolesDao.findAllActiveRoleName(ltP2pUsers.get(0).getUserId());
						if (!roles.isEmpty()) {
							entity.setRole(roles.get(0));
						}

						List<Menu> menuList = ltMastUsersDao.findMenu(ltP2pUsers.get(0).getUserId(),entity.getCompanyId());
						System.out.println(menuList.toString()+ltP2pUsers.get(0).getUserId()+entity.getCompanyId());
						entity.setData(menuList);
						entity.setCode(1);
						System.out.println("Menu = "+menuList);
						userDao.saveLoginToken(tokenInfo);
						//entity.setData(tokenInfo);
						//userDao.setPackageVariables(ltP2pUsers.get(0).getUserId(), entity.getCompanyId());

					} else {
						System.out.println("Password is wrong");
						entity = new ResponceEntity();
						entity.setCode(0);
						entity.setMessage("Invalid Username or Password");
					}
				}
		}else {
			System.out.println("in second else");
			entity = new ResponceEntity();
			entity.setCode(0);
			entity.setMessage("Invalid Username or Password");
		}
		return  entity;
		}catch (Exception e) {
			System.out.println("in exception");
			e.printStackTrace();
			ResponceEntity entity = new ResponceEntity();
			entity.setCode(0);
			entity.setMessage("Exception occured while login into the app, please contact system administrator");
			throw new BusinessException(0,"Invalid Username or Password",e);
		}
		
		//final LtMastUsers user1 = userDao.findByUserName(user.getUserName());
	}
	
	@Override
	public ResponceEntity loginUserForMobile(LtMastUsers user, HttpServletResponse response) {
		try{
			ResponceEntity entity = null;
		List<LtMastUsers> ltP2pUsers = ltMastUsersDao.findByUserName(user.getUserName());
		System.out.println("line no 97 = "+ltP2pUsers);
		System.out.println("size is "+ltP2pUsers.size());
		if(!ltP2pUsers.isEmpty()) {
			if(ltP2pUsers.get(0).getStatus().equals("DRAFTCODE") || 
					ltP2pUsers.get(0).getStatus().toUpperCase().equals("INACTIVE")) {
				entity = new ResponceEntity();
				entity.setMessage("User is inactive");
				return  entity;
			}else if(ltP2pUsers.get(0).getStatus().equals("UNVERIFYEMAIL")){
				entity = new ResponceEntity();
				entity.setMessage("User status is unverifyemail, Please set the password!");
				} else {
                       System.out.println(user);
					if ((user.getLoginCheck() != null) && (!user.getLoginCheck().trim().isEmpty())
							&& (user.getLoginCheck().equals("SHA-256"))) {
						
						System.out.println(" GRN login ");
						String password = user.getPassword().substring(0, user.getPassword().length() - 5);
						String salt = BCrypt.gensalt(12);
						String serverBcryptedPwd = BCrypt.hashpw(password, salt);
						user.setPassword(serverBcryptedPwd);
						System.out.println(serverBcryptedPwd);
						user.setPassword(password);
						System.out.println(serverBcryptedPwd);
						
					}else{
						System.out.println(" vpal login "+user.getPassword()+"\t");
					}
							//if (BCrypt.checkpw(ltP2pUsers.get(0).getPassword().trim(), user.getPassword().trim())) {
					//if (BCrypt.checkpw(ltP2pUsers.get(0).getP, user.getPassword().trim())) {

					
					if (ltP2pUsers.get(0).getPassword().trim().equalsIgnoreCase(user.getPassword().trim())){
						entity = new ResponceEntity();
						response.setHeader("userId", ltP2pUsers.get(0).getUserId() + "");
						response.setHeader("employeeId", ltP2pUsers.get(0).getEmployeeId() + "");
						response.setHeader("employeeName", ltP2pUsers.get(0).getEmployeeName());
						response.setHeader("companyId", ltP2pUsers.get(0).getCompanyId() + "");
						AuthTokenInfo tokenInfo = oath2Request.sendTokenRequest(user.getUserName(), user.getPassword());
						//response.setHeader("access_token", tokenInfo.getAccess_token());
						response.setHeader("access_token","c2FtcGxlQ2xpZW50SWQ6c2VjcmV0");
						
						SysVariableWithValues sysVariableWithValues = new SysVariableWithValues();
						if(ltP2pUsers.get(0).getCompanyId()!=null) {
							sysVariableWithValues=
									ltMastSysVariablesService.getBySysVariableName1("GLOBAL_FILE_PATH", ltP2pUsers.get(0).getCompanyId());
						}else {
							sysVariableWithValues=
									ltMastSysVariablesService.getBySysVariableName1("GLOBAL_FILE_PATH", 1L);
						}
						
						
						System.out.println("sysVariableWithValues 1 = "+sysVariableWithValues);
						
						String globalFilePath = "";
						if(sysVariableWithValues!=null)
						{
							if(sysVariableWithValues.getLtMastSysVariableValues().get(0)!=null)
							{
								System.out.println("Above file path");
								globalFilePath=sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
								System.out.println("file path = "+globalFilePath);
							}
							else
							{
								globalFilePath=sysVariableWithValues.getLtMastSysVariables().getSystemValue();
							}
						}
						
						entity.setGlobalFilePath(globalFilePath);
						entity.setMessage("Login Success " + user.getUserName());
						entity.setUserId(ltP2pUsers.get(0).getUserId());
						entity.setEmployeeId(ltP2pUsers.get(0).getEmployeeId());
						entity.setEmployeeName(ltP2pUsers.get(0).getEmployeeName());
						entity.setDivisionId(ltP2pUsers.get(0).getDivisionId());
						entity.setLocationId(ltP2pUsers.get(0).getLocationId());
						entity.setLocationCode(ltP2pUsers.get(0).getLocationCode());
						entity.setDivisionName(ltP2pUsers.get(0).getDivisionName());
						entity.setCompanyId(ltP2pUsers.get(0).getCompanyId());
						entity.setIsBuyer(ltP2pUsers.get(0).getIsBuyer());
						entity.setCompanyName(ltP2pUsers.get(0).getCompanyName());
						if (ltP2pUsers.get(0).getVendorId() != null && !ltP2pUsers.get(0).getVendorId().equals(0)) {
							List<LtMastUsers> ltP2pUsers1 = ltMastUsersDao
									.findVendorByUserName(user.getUserName().toUpperCase());
							System.out.println("list p2pUser1 = "+ltP2pUsers1);
							// authTokenInfo.setEmpName(ltP2pUsers1.get(0).getEmpName());
							entity.setEmployeeName(ltP2pUsers1.get(0).getEmployeeName());
							entity.setVendorId(ltP2pUsers.get(0).getVendorId());
							entity.setDivisionId(ltP2pUsers1.get(0).getDivisionId());
							entity.setDivisionName(ltP2pUsers1.get(0).getDivisionName());
							entity.setCompanyId(ltP2pUsers1.get(0).getCompanyId());
							entity.setCompanyName(ltP2pUsers1.get(0).getCompanyName());
						}

						List<String> roles = ltMastUserRolesDao.findAllActiveRoleName(ltP2pUsers.get(0).getUserId());
						if (!roles.isEmpty()) {
							entity.setRole(roles.get(0));
						}

//						List<Menu> menuList = ltMastUsersDao.findMenu(ltP2pUsers.get(0).getUserId(),entity.getCompanyId());
//						System.out.println(menuList.toString()+ltP2pUsers.get(0).getUserId()+entity.getCompanyId());
						entity.setData(null);
						entity.setCode(1);
//						System.out.println("Menu = "+menuList);
						userDao.saveLoginToken(tokenInfo);
						//entity.setData(tokenInfo);
						//userDao.setPackageVariables(ltP2pUsers.get(0).getUserId(), entity.getCompanyId());

					} else {
						System.out.println("Password is wrong");
						entity = new ResponceEntity();
						entity.setCode(0);
						entity.setMessage("Invalid Username or Password");
					}
				}
		}else {
			System.out.println("in second else");
			entity = new ResponceEntity();
			entity.setCode(0);
			entity.setMessage("Invalid Username or Password");
		}
		return  entity;
		}catch (Exception e) {
			System.out.println("in exception");
			e.printStackTrace();
			ResponceEntity entity = new ResponceEntity();
			entity.setCode(0);
			entity.setMessage("Exception occured while login into the app, please contact system administrator");
			throw new BusinessException(0,"Invalid Username or Password",e);
		}
		
		//final LtMastUsers user1 = userDao.findByUserName(user.getUserName());
	}
	

	@Override
	public ResponceEntity logoutUser(HttpServletRequest request, HttpServletResponse response) throws BusinessException{
		ResponceEntity entity = new ResponceEntity();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){
	    	String token = request.getHeader(HEADER_AUTHORIZATION);
	    	//String token2 = request.getHeader("access_token");
	    	OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
	    	//OAuth2RefreshToken refreshToken =  tokenStore.readRefreshToken(token2);
	    	if (oAuth2AccessToken != null) {
	    		tokenStore.removeAccessToken(oAuth2AccessToken);
	        }
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    entity.setMessage("Logout Success");
	    entity.setCode(200);
	    return  entity;
	}

	@Override
	public String tokenExtend(String token) throws BusinessException{
		AuthTokenInfo tokenTime = userDao.getTokenTimeDifferance(token);
		if(tokenTime != null && tokenTime.getExpires_in() > 100 )  {
			AuthTokenInfo tokenInfo =  oath2Request.sendRefreshTokenRequest(tokenTime.getRefresh_token());
			return tokenInfo.getAccess_token();
		}
		return token;
	}
	
	@Override
	public AuthTokenInfo getTokenTimeDifferance(String token) throws BusinessException{
		return userDao.getTokenTimeDifferance(token);
	}


	@Override
	public Status changePassword(String jsonInputString) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		Status status = new Status();
		LtMastUsers dbUser = null;
		
			if (jsonInputString.length() == 0) {
				status.setCode(0);
				status.setMessage("Input empty.");
				new ResponseEntity<Status>(status, HttpStatus.OK);
			}
			
			newJObject = (JSONObject) parser.parse(jsonInputString);
			
			if (ltMastUsersRepository.exists(Long.parseLong(newJObject.get("userid").toString()))){
				dbUser = ltMastUsersRepository.findOne(Long.parseLong(newJObject.get("userid").toString()));
			}
			else
			{
				status.setMessage("no result");
				status.setCode(0);
				return status;
			}
			//ltMastUsers = ltMastUsersList.get(0);
			if(dbUser.getChangePwd().equals("N")) {
				status.setCode(0);
				status.setMessage("You can not change the password.");
				return status;
			}
			
			//if (!BCrypt.checkpw(dbUser.getPassword(), newJObject.get("password").toString())) {
			if (!(dbUser.getPassword().equalsIgnoreCase(newJObject.get("password").toString()))) {
//				status = ltMastCommonMessageService.getCodeAndMessage(OLD_PASS_NOT_MATCHED);
				status.setCode(0);		
				try {
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("OLD_PASS_NOT_MATCHED").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if( status.getMessage()==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
				return status;
			}
			
//			if (BCrypt.checkpw(dbUser.getPassword(), 
//					BCrypt.hashpw(newJObject.get("changepassword").toString(), BCrypt.gensalt(12)))) {				
			if(dbUser.getPassword().equalsIgnoreCase(newJObject.get("changepassword").toString())) {	
			status.setMessage("Old password and new password are same.");
				status.setCode(0);
				return status;
			}

			List<LtMastPasswords> ltMastPasswordsLastPasswords=null;
			try {
				ltMastPasswordsLastPasswords = ltMastPasswordsService.findByUserId(dbUser.getUserId());
				
				if(ltMastPasswordsLastPasswords!=null) {
				for (LtMastPasswords ltMastPasswords : ltMastPasswordsLastPasswords) {					
					if (ltMastPasswords.getPassword().equals(newJObject.get("changepassword").toString())) {
						status.setMessage("Password is used");
						return status;
					}
				}

				dbUser.setPassword(newJObject.get("changepassword").toString());
				dbUser.setChangePwd("Y");
				dbUser.setPasswordDate(new CommonMethod().convertStringtoSqlDate(new CommonMethod().getTodayDate()));
				dbUser.setLastUpdateLogin(Long.parseLong(newJObject.get("userid").toString()));//akshay
				dbUser.setLastUpdatedBy(Long.parseLong(newJObject.get("userid").toString()));
				dbUser.setLastUpdateDate( new CommonMethod().convertStringtoSqlDateTime(new CommonMethod().getTodayDate()));
				dbUser = ltMastUsersRepository.save(dbUser);
				
				if(dbUser !=null){
//					status=ltMastCommonMessageService.getCodeAndMessage(PASS_CHANGE_SUCCESSFULLY);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PASS_CHANGE_SUCCESSFULLY").getMessageName());
					if( status.getMessage()==null){
						status.setCode(1);
						status.setMessage("Error in finding message! The action is completed successfully.");
					}
				}

				if (ltMastPasswordsLastPasswords.size() >= 5 && !ltMastPasswordsLastPasswords.isEmpty())
					ltMastPasswordsRepository.delete(ltMastPasswordsLastPasswords.get(0).getPasswordid());

				LtMastPasswords ltMastPasswords = new LtMastPasswords();
				ltMastPasswords.setUserId(dbUser.getUserId());
				ltMastPasswords.setPassword(newJObject.get("changepassword").toString());
				ltMastPasswords.setPwdSequence((long) newJObject.get("changepassword").toString().length());
				ltMastPasswords.setCreatedBy(dbUser.getUserId());
				ltMastPasswords.setCreationDate(new CommonMethod().convertStringtoSqlDate(new CommonMethod().getTodayDate()));
				ltMastPasswords = ltMastPasswordsRepository.save(ltMastPasswords);
				return status;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
			return status;
		
	}


	@Override
	public Status emailValidater(String id) 
	{
		Status status = new Status();
		List<LtMastEmailtoken> ltMastEmailtokenList = new ArrayList<>();
		LtMastEmailtoken ltMastEmailtoken = null;
		LtMastUsers ltMastUsers;
		if (id == null || id.length() == 0) {
			status.setMessage("Invalid token");
			status.setCode(0);
			return status;
		}
		try {
			ltMastEmailtokenList = ltMastEmailtokenDao.findByTokenid(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (ltMastEmailtokenList.isEmpty()) {
			
			status.setMessage("Invalid token");
			status.setCode(0);
			return status;
		}
		
		ltMastEmailtoken = ltMastEmailtokenList.get(0);
		java.util.Date tokenTime = new Date(ltMastEmailtoken.getSendDate().getTime());
		try {
			if (!new CommonMethod().linkExpiredChecker(tokenTime, (long) ltMastEmailtoken.getExpiredWithin())) {
				status.setMessage("Link expired");
				status.setCode(0);
				return status;
			}
			Long userid = ltMastEmailtoken.getEmailUserId();
			if (!ltMastUsersRepository.exists(userid)) {
				
				status.setMessage("No result");
				status.setCode(0);
				return status;
			}
			ltMastUsers = ltMastUsersRepository.findOne(userid);
			
			if (!ltMastUsers.getStatus().equals("UNVERIFYEMAIL")) {
				
				
				
				 if(!ltMastUsers.getStatus().toUpperCase().equals("ACTIVE") && ltMastUsers.getVendorId()==null){
					ltMastUsers.setStatus("ACTIVE");
				}
				
				ltMastUsers.setLastUpdateLogin(ltMastUsers.getUserId());
				ltMastUsers.setLastUpdatedBy(ltMastUsers.getUserId());
				ltMastUsers.setLastUpdateDate(
						new CommonMethod().convertStringtoSqlDateTime(new CommonMethod().getTodayDate()));
				ltMastUsers = ltMastUsersRepository.save(ltMastUsers);
				if(ltMastUsers.getUserId()!=null) {
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
					status.setCode(1);		
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
				status.setData(ltMastUsers.getUserId());
				}
				return status;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
					
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if( status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
			return status;

		}
		status.setData(ltMastUsers.getUserId());
		return status;
	}


	@Override
	public Status resetPassword(String jsonInputString) {
		Status status = new Status();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		List<LtMastUsers> ltMastUsersList = new ArrayList<>();
		LtMastUsers ltMastUsers = new LtMastUsers();
		try {
			if (jsonInputString.length() == 0) {
//				status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				status.setCode(0);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				if( status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
				return status;
			}
			
			
			newJObject = (JSONObject) parser.parse(jsonInputString);
			ltMastUsersList = userDao.findByUserNameAndEmailId(
					newJObject.get("username").toString().toUpperCase(),
					newJObject.get("emailid").toString().toLowerCase());
			
			if (ltMastUsersList.isEmpty()) {
				
				status.setCode(0);
				status.setMessage("Invalid username or emailId.");
				return status;

			}
			newJObject = (JSONObject) parser.parse(jsonInputString);
			ltMastUsers = ltMastUsersList.get(0);
			/*newJObject = (JSONObject) parser.parse(jsonInputString);
			ltMastUsers = userDao.getUserById(Long.parseLong(newJObject.get("userid").toString()));*/
			if(ltMastUsers.getChangePwd().equals("N")) {
				status.setCode(0);
				status.setMessage("You can not change the password.");
				return status;
			}
			if ( (!ltMastUsers.getStatus().toUpperCase().equals("INVITED")) && ltMastUsers.getVendorId()!=null) {
				
				status.setCode(0);
				status.setMessage("User record is not active.");
				return status;
			}else if(!ltMastUsers.getStatus().toUpperCase().equals("ACTIVE") && ltMastUsers.getVendorId()==null){
				status.setCode(0);
				status.setMessage("User record is not active.");
				return status;
			}
			
			//ltMastUsers.setChangePwd("N");
			ltMastUsers.setLoginFailureAttempt(0L);

			ltMastUsers = ltMastUsersRepository.save(ltMastUsers);
			String token = new CommonMethod().getToken();

			final String emailID = ltMastUsers.getEmail();
			final String userName = ltMastUsers.getUserName();
			final String userID = ltMastUsers.getUserId().toString();

			LtMastEmailtoken ltMastEmailtoken = new LtMastEmailtoken();
			ltMastEmailtoken.setEmailTitle("Reset your Vendor portal Application Password.");
			ltMastEmailtoken.setEmailTemplate("Forgotpassword");
			ltMastEmailtoken.setSendTo(emailID);
			ltMastEmailtoken.setEmailObject("Name=" + userName + "," + "userId=" + userID + "," +"#$#$" + "action_url="
					+ env.getProperty("CLIENT_URL_REST_PASSWORDURL"));
			ltMastEmailtoken.setExpiredWithin(1800L);
			ltMastEmailtoken
					.setSendDate(new CommonMethod().convertStringtoSqlDateTime(new CommonMethod().getTodayDate()));
			ltMastEmailtoken.setTokenId(token);
			ltMastEmailtoken.setFailureCount(0L);
			ltMastEmailtoken.setEmailStatus("SENDING");
			ltMastEmailtoken.setEmailUserId(Long.parseLong(userID));
			ltMastEmailtoken = ltMastEmailtokenRepository.save(ltMastEmailtoken);

//			status=ltMastCommonMessageService.getCodeAndMessage(PASS_LINK_SEND);
			status.setCode(1);		
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PASS_LINK_SEND").getMessageName());
			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
					
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if( status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
			return status;

		}
		return status;
	}


	@Override
	public Status changePasswordUtility(String jsonInputString) throws ServiceException {
		Status status = new Status();
		JSONParser parser = new JSONParser();
		JSONObject newJObject = null;
		LtMastUsers ltMastUsers = null;
		try {
			if (jsonInputString.length() == 0) {
//				status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				status.setCode(0);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				if( status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
				return status;
			}
			
			
			newJObject = (JSONObject) parser.parse(jsonInputString);
			String password = newJObject.get("changepassword").toString();
			
			newJObject = (JSONObject) parser.parse(jsonInputString);
			Long userId = Long.parseLong(newJObject.get("userId").toString());
			ltMastUsers = userDao.getUserById(userId);
			
			String encryptedPwd = getSHA(password);
			
			if(userDao.changePasswordUtility(userId,encryptedPwd)){
				
				List<LtMastPasswords> ltMastPasswordsLastPasswords=null;
				
				ltMastPasswordsLastPasswords = ltMastPasswordsService.findByUserId(userId);
				
				if (ltMastPasswordsLastPasswords.size() >= 5 && !ltMastPasswordsLastPasswords.isEmpty()) {
						ltMastPasswordsRepository.delete(ltMastPasswordsLastPasswords.get(0).getPasswordid());
				}

				LtMastPasswords ltMastPasswords = new LtMastPasswords();
				ltMastPasswords.setUserId(userId);
				ltMastPasswords.setPassword(newJObject.get("changepassword").toString());
				ltMastPasswords.setPwdSequence((long) newJObject.get("changepassword").toString().length());
				ltMastPasswords.setCreatedBy(userId);
				ltMastPasswords.setCreationDate(new CommonMethod().convertStringtoSqlDate(new CommonMethod().getTodayDate()));
				ltMastPasswords = ltMastPasswordsRepository.save(ltMastPasswords);
				
				String token = new CommonMethod().getToken();

				final String emailID = ltMastUsers.getEmail();
				final String userName = ltMastUsers.getUserName();
				

				LtMastEmailtoken ltMastEmailtoken = new LtMastEmailtoken();
				ltMastEmailtoken.setEmailTitle("Reset your Vendor portal Application Password.");
				ltMastEmailtoken.setEmailTemplate("SetPassword");
				ltMastEmailtoken.setSendTo(emailID);
				ltMastEmailtoken.setEmailObject( "#$#$username=" + userName 
				+ "#$#$password=" + password + "," + "#$#$action_url="+ env.getProperty("LOGIN_URL"));
				
				
				ltMastEmailtoken
						.setSendDate(new CommonMethod().convertStringtoSqlDateTime(new CommonMethod().getTodayDate()));
				ltMastEmailtoken.setTokenId(token);
				ltMastEmailtoken.setFailureCount(0L);
				ltMastEmailtoken.setEmailStatus("SENDING");
				ltMastEmailtoken.setSendTo(ltMastUsers.getEmail());
				ltMastEmailtoken.setEmailUserId(ltMastUsers.getUserId());
				ltMastEmailtoken.setExpiredWithin(1296000L);
				ltMastEmailtoken = ltMastEmailtokenRepository.save(ltMastEmailtoken);
				
//				status=ltMastCommonMessageService.getCodeAndMessage(PASS_CHANGE_SUCCESSFULLY);
				status.setCode(1);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("PASS_CHANGE_SUCCESSFULLY").getMessageName());
				if( status.getMessage()==null){
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
//			status=ltMastCommonMessageService.getCodeAndMessage(INTERNAL_SERVER_ERROR);
				
			try {
				status.setCode(0);	
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INTERNAL_SERVER_ERROR").getMessageName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if( status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
			return status;

		}
		return status;
	}
	
	
	 public static String getSHA(String input) 
	    { 
	        try { 
	            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
	            byte[] messageDigest = md.digest(input.getBytes()); 
	            BigInteger no = new BigInteger(1, messageDigest); 
	            String hashtext = no.toString(16); 
	            while (hashtext.length() < 32) { 
	            } 
	            return hashtext; 
	        } 
	        catch (NoSuchAlgorithmException e) { 
	           e.printStackTrace();
	            return null; 
	        } 
	    }



	
}
