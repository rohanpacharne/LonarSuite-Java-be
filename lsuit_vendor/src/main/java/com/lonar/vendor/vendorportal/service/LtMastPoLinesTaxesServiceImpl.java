package com.lonar.vendor.vendorportal.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.lonar.vendor.vendorportal.dao.LtMastPoLinesTaxesDao;
import com.lonar.vendor.vendorportal.model.LtPoLineTaxes;
import com.lonar.vendor.vendorportal.model.ServiceException;
 
@Service
public class LtMastPoLinesTaxesServiceImpl implements LtMastPoLinesTaxesService {
	@Autowired
	LtMastPoLinesTaxesDao ltmastPolineTaxesDao;

	@Override
	public List<LtPoLineTaxes> getAllPoLinesByLineId(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return ltmastPolineTaxesDao.getAllPoLinesByLineId(id);
	}

 
}