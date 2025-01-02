package com.lonar.mast.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lonar.mast.model.BusinessException;

import com.lonar.mast.model.LtMastExpenseTypes;

@Controller
@RequestMapping("/API/expenseType")
public class LtMastExpenseTypeRestController {
	
	@RequestMapping(value = "/getactiveexpensetypes", method= RequestMethod.GET, produces = "application/json")
	 public ResponseEntity<List<LtMastExpenseTypes>> getAllActiveExpenseTypes()
	{
			List<LtMastExpenseTypes> expensetypeList=null;
			try
			{
				//expensetypeList =  ltMastExpenseTypeService.getAllActiveExpenseTypes();
			}
			catch(Exception e)
			{
				//throw new BusinessException(INTERNAL_SERVER_ERROR, null, e);
			}
			return new ResponseEntity<List<LtMastExpenseTypes>>(expensetypeList, HttpStatus.OK);
	}

}
