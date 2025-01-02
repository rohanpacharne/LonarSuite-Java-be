package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastExpenseTypes;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastExpenseTypesDao {
	
	List<LtMastExpenseTypes> getAllActiveExpenseType() throws Exception;
	
	Long getExpenseTypesCount(LtMastExpenseTypes input,Long companyId) throws Exception;
	 
	List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input, Long companyId) throws Exception;

	LtMastExpenseTypes getByExpenseNature(String expenseNature, Long companyId)  throws Exception;
	
	LtMastExpenseTypes getByGlCode(String glCode) throws Exception;
	
	boolean saveExpense(LtMastExpenseTypes ltMastExpenseTypes) throws Exception;
	
	LtMastExpenseTypes getExpenseTypeById(Long expenseTypeId ) throws Exception;
	
	boolean deleteExpense(Long expenseTypeId) throws Exception;
	
	boolean updateExpense(LtMastExpenseTypes ltMastExpenseTypes) throws Exception;
	
	List<LtMastExpenseTypes> getExpenseTypesLikeName(String name,Long companyId) throws ServiceException;
}
