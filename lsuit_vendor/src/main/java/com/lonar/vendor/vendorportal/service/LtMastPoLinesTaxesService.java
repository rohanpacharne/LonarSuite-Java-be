package com.lonar.vendor.vendorportal.service;
 
import java.util.List;
 
import com.lonar.vendor.vendorportal.model.LtInvoiceLineTaxes;
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
 
public interface LtMastPoLinesTaxesService {
 
	List<LtPoLineTaxes> getAllPoLinesByLineId(Long id) throws ServiceException;
 
	
 
}