package com.lonar.mast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lonar.mast.model.BusinessException;
import com.lonar.mast.model.CustomeDataTable;
import com.lonar.mast.model.LtMastExpenseTypes;
import com.lonar.mast.service.LtMastExpenseTypeService;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/API/expenseType")
public class LtMastExpenseTypeRestController {
	
	@Autowired
	LtMastExpenseTypeService ltMastExpenseTypeService;
	
	@RequestMapping(value = "/getactiveexpensetypes", method= RequestMethod.GET, produces = "application/json")
	 public ResponseEntity<List<LtMastExpenseTypes>> getAllActiveExpenseTypes()
	{
			List<LtMastExpenseTypes> expensetypeList=null;
			try
			{
				expensetypeList =  ltMastExpenseTypeService.getAllActiveExpenseTypes();
			}
			catch(Exception e)
			{
				//throw new BusinessException(INTERNAL_SERVER_ERROR, null, e);
			}
			return new ResponseEntity<List<LtMastExpenseTypes>>(expensetypeList, HttpStatus.OK);
	}
	
	//@PreAuthorize("hasPermission(null, '#/expenseType/expenseType', 'View')")
	@RequestMapping(value = "/dataTable1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getDataTable(LtMastExpenseTypes input)
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try
		{
				Long count=ltMastExpenseTypeService.getExpenseTypesCount(input);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtMastExpenseTypes> expenseTypesList= ltMastExpenseTypeService.getExpenseTypesData(input);
				
			    customeDataTable.setData(expenseTypesList);
		}
		catch (Exception e)
		{
			//throw new BusinessException(INTERNAL_SERVER_ERROR, null, e);
		}
		return customeDataTable;
	}

}
