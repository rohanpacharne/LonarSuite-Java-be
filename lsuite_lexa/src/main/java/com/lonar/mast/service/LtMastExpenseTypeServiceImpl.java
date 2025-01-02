package com.lonar.mast.service;

import java.util.ArrayList;
import java.util.List;
//import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.mast.dao.LtMastExpenseTypesDao;
import com.lonar.mast.model.LtMastExpenseTypes;

@Service
public class LtMastExpenseTypeServiceImpl implements LtMastExpenseTypeService {

	@Autowired
	LtMastExpenseTypesDao ltMastExpenseTypeDao;
	
	
	@Override
	public List<LtMastExpenseTypes> getAllActiveExpenseTypes() throws Exception {
		List<LtMastExpenseTypes> expenseTypeList=new ArrayList<LtMastExpenseTypes>();
		
		expenseTypeList=ltMastExpenseTypeDao.getAllActiveExpenseType();
		
		return expenseTypeList;
	}
	
	@Override
	public Long getExpenseTypesCount(LtMastExpenseTypes input) throws Exception
	{
		// TODO Auto-generated method stub
		return ltMastExpenseTypeDao.getExpenseTypesCount(input);
	}
 
 
	@Override
	public List<LtMastExpenseTypes> getExpenseTypesData(LtMastExpenseTypes input) throws Exception
	{
		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
		{ input.setColumnNo(11); }
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{ input.setColumnNo(12); }
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{ input.setColumnNo(13); }
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{ input.setColumnNo(14); }
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{ input.setColumnNo(15); }
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{ input.setColumnNo(16); }
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{ input.setColumnNo(17); }
		if(input.getColumnNo()==8 && input.getSort().equals("desc"))
		{ input.setColumnNo(18); }
		return ltMastExpenseTypeDao.getExpenseTypesData(input);
	}

}
