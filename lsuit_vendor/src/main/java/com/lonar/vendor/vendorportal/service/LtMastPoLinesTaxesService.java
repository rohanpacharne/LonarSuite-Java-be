package com.lonar.vendor.vendorportal.service;
 
import java.util.List;
 
import com.lonar.vendor.vendorportal.model.LtInvoiceLineTaxes;
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
 
public interface LtMastPoLinesTaxesService {
 
	List<LtPoLineTaxes> getAllPoLinesByLineId(Long id) throws ServiceException;

	Status save(List<LtPoLineTaxes> ltPoLineTaxesList);

	Status update(LtPoLineTaxes ltPoLineTaxes);

	Status delete(Long id);
 
	
 
}