package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtExpMileageExpenseLines;

public interface LtExpMileageExpenseLinesDao {
	
	List<LtExpMileageExpenseLines> getExpMileageLinesByExpHeaderId(Long headerId) throws Exception;
	
	boolean deleteByExpHeaderId(Long expHeaderId) throws Exception;

}
