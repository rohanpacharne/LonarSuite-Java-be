package com.lonar.mast.dao;

import java.util.List;

import com.lonar.mast.model.LtMastExpenseTypes;

public interface LtMastExpenseTypesDao {
	
	List<LtMastExpenseTypes> getAllActiveExpenseType() throws Exception;
	
	Long getExpenseTypesCount(LtMastExpenseTypes input) throws Exception;
	 
	List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input) throws Exception;

}
