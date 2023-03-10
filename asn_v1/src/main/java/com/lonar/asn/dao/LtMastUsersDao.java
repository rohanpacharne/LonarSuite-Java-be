package com.lonar.asn.dao;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.LtMastUsers;


public interface LtMastUsersDao {

/*	List<LtMastUsers> getAllUsers();
	List<LtMastUsers> getUserNamePassword(String userName, String password);*/
	List<LtMastUsers> findByUserName(String userName);
	/*	List<LtMastUsers> findByEmail(String emailID);
	List<LtMastUsers> findAllActive();
	List<LtMastUsers> findByUserNameAndEmailId(String username, String emailID);
//	UserWithRolePojo findUserWithRoleDetails(Long userID);
	List<LtMastUsers> findByEmployeeId(Long employeeId);
	List<LtMastUsers> getByUserId(Long userId);*/
	
	Long getLtMastUsersCount(LtMastUsers input);
	List<LtMastUsers> getLtMastUsersDatatableRecords(LtMastUsers input);
	List<LtMastUsers> findByActiveLikeUserName(String userName);
	LtMastUsers getLtMastUsersByID(Long id);
	
	List<LtMastUsers> findByAdId(String userName);
	
	//List<LtMastUsers> findByUserNameForOauth(String userName);
	
	
	List<LtMastUsers> findVendorByUserName(String upperCase);

	List<LtMastUsers> findActiveLikeEmail(String email) throws Exception;

	boolean updateUser(Long vendorId) throws ServiceException;
}
