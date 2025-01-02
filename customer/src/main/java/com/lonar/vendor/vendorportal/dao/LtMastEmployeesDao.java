package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.ServiceException;


public interface LtMastEmployeesDao {

	public List<LtMastEmployees> findBySupervisorEmpId(Long supervisorEmpId) throws ServiceException;

	public List<LtMastEmployees> findByEmployeeNumber(String employeeNumber) throws ServiceException;

	public List<LtMastEmployees> findByOfficialEmail(String officialEmail) throws ServiceException;

	public List<LtMastEmployees> findByPosition(String position) throws ServiceException;

	public List<LtMastEmployees> findAllActive() throws ServiceException;

	public List<LtMastEmployees> findLikeFirstName(String firstName) throws ServiceException;

	public List<LtMastEmployees> findLikeName(String name) throws ServiceException;
	
	public  List<LtMastEmployees> findByActiveLikeName(String name) throws ServiceException;

	public List<LtMastEmployees> findUserNotEmployeeId() throws ServiceException;

	public List<LtMastEmployees> findByCostCenterId(Long costCenterId) throws ServiceException;

	public List<LtMastEmployees> findByEmployeeName(String name) throws ServiceException;

	public List<LtMastEmployees> checkDetails(LtMastEmployees ltMastEmployees) throws ServiceException;

	public List<LtMastEmployees> getEmployeeDetailsByUserID(Long userId) throws ServiceException;

	public List<LtMastEmployees> getByEmpId(Long no) throws ServiceException;

	public List<LtMastEmployees> getLikeNameByDivisionId(String name, String divisionId) throws ServiceException;

	public LtMastEmployees getByEmployeeNumber(String employeeNumber) throws ServiceException;

	public LtMastEmployees getByofficialEmail(String officialEmail) throws ServiceException;

	public LtMastEmployees getByPersonalEmail(String personalEmail) throws ServiceException;

	public LtMastEmployees getByPassportNumber(String passportNo) throws ServiceException;

	public LtMastEmployees getByPanNumber(String panNo) throws ServiceException;

	//public Long save(LtMastEmployees ltMastEmployees) throws Exception;

	public boolean updatePath(LtMastEmployees ltMastEmployees) throws ServiceException;

	public boolean delete(Long employeeId) throws ServiceException;

	public boolean update(LtMastEmployees ltMastEmployees)  throws ServiceException;

	public List<LtMastEmployees> findAll()  throws ServiceException;

	public LtMastEmployees getLtMastEmployeesByID(Long empId) throws ServiceException;

	public Long getCount(LtMastEmployees input) throws ServiceException;

	public List<LtMastEmployees> getDatatableRecords(LtMastEmployees input) throws ServiceException;

	public List<LtMastEmployees> getLtMastEmployeesBySuperWID(long employeeId)throws ServiceException;

	public List<LtMastEmployees> getByEmpIdV1(Long employeeId) throws ServiceException;

	public List<LtMastEmployees> getByEmpIdForEmail(Long longValue) throws ServiceException;

	public String getRoleByEmpId(Long id) throws ServiceException;

	public String getCompanyIDByEmpId(Long id) throws ServiceException;

}
