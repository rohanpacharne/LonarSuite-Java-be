package com.lonar.vendor.vendorportal.dao;
 
import java.util.List;
 
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
 
public interface LtMastPoLinesTaxesDao {
 
	List<LtPoLineTaxes> getAllPoLinesByLineId(Long id) throws ServiceException;
 
	
 
}