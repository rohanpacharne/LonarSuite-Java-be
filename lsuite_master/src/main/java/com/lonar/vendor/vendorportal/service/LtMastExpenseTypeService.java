package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastExpenseTypes;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastExpenseTypeService extends CodeMaster {
	
	List<LtMastExpenseTypes> getAllActiveExpenseTypes() throws Exception;
	
	Long getExpenseTypesCount(LtMastExpenseTypes input,Long companyId) throws Exception;
	 
	List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input,Long companyId) throws Exception;
	
	Status saveExpense(LtMastExpenseTypes ltMastExpenseTypes) throws Exception;
	
	LtMastExpenseTypes getExpenseTypeById(Long expenseTypeId ) throws Exception;
	
	Status deleteExpense(Long expenseTypeId) throws Exception;
	
	Status updateExpense(LtMastExpenseTypes ltMastExpenseTypes) throws Exception;
	
	public List<LtMastExpenseTypes> getExpenseTypesLikeName(String name,Long companyId) throws ServiceException;

	
}
