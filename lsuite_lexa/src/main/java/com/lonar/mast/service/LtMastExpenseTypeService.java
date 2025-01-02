package com.lonar.mast.service;

import java.util.List;

import com.lonar.mast.model.LtMastExpenseTypes;

public interface LtMastExpenseTypeService {
	
	List<LtMastExpenseTypes> getAllActiveExpenseTypes() throws Exception;
	
	Long getExpenseTypesCount(LtMastExpenseTypes input) throws Exception;
	 
	List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input) throws Exception;
	
}
