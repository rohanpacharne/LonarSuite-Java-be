package com.lonar.vendor.vendorportal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtMastBranches;
import com.lonar.vendor.vendorportal.model.LtMastExpenseTypes;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastBranchesService;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;

import com.lonar.vendor.vendorportal.service.LtMastExpenseTypeService;
import com.lonar.vendor.vendorportal.service.LtMastProjectsService;


@RestController
@RequestMapping("/API/branch")
public class LtMastBranchesRestController implements CodeMaster
{
	@Autowired
	LtMastBranchesService ltMastBranchesService;

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastExpenseTypeService ltMastExpenseTypeService;
	
	@Autowired
	 LtMastProjectsService ltMastProjectsService;
	
		
//---------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/dataTable1/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getBranchDataTable1(@PathVariable("companyId") Long companyId, LtMastBranches input,@PathVariable("logTime") String logTime) 
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try 
		{
				Long count=ltMastBranchesService.getCount(companyId, input);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtMastBranches> ltMastBranchesList= 
			    		ltMastBranchesService.getBranchDataTableRecords(companyId, input);
				customeDataTable.setData(ltMastBranchesList);	
			  
		} 
		catch (Exception e) 
		{
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
		
	}
//====================================================================================================================
	@RequestMapping(value = "/getAll/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> listLtMastBranchesAll(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws  ServiceException {
		List<LtMastBranches> list =  ltMastBranchesService.findAll(companyId);
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}

//====================================================================================================================
	@RequestMapping(value = "/getAllActive/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> listAllActiveLtMastBranches(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> list =   ltMastBranchesService.findAllActive(companyId);
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}
	
	
//====================================================================================================================
	@RequestMapping(value = "/getAllActiveBillingAddress/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> getAllActiveBillingAddress(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> list =   ltMastBranchesService.getAllActiveBillingAddress(companyId);
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}	

//====================================================================================================================
	@RequestMapping(value = "/getAllActiveBillingAddrByBuyer/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> getAllActiveBillingAddrByBuyer(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> list =   ltMastBranchesService.getAllActiveBillingAddrByBuyer(id);
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}
//====================================================================================================================
	@RequestMapping(value = "/getAllActiveShippingAddress/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> getAllActiveShippingAddress(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> list =   ltMastBranchesService.getAllActiveShippingAddress(companyId);
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}	
//====================================================================================================================
	@RequestMapping(value = "/getAllActiveShippingAddressByBuyer/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> getAllActiveShippingAddrByBuyer(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> list =   ltMastBranchesService.getAllActiveShippingAddrByBuyer(id);
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}	
//====================================================================================================================
	@RequestMapping(value = "/getByID/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LtMastBranches> getLtMastBranchesByID(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException {
		LtMastBranches ltMastBranches =  ltMastBranchesService.getLtMastBranchesByID(id);
		return new ResponseEntity<LtMastBranches>(ltMastBranches, HttpStatus.OK);
	}
//====================================================================================================================
	@RequestMapping(value = "/getByCompanyID/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> getLtMastBranchesByCompID(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> ltMastBranches =  ltMastBranchesService.getLtMastBranchesByCompID(id);
		return new ResponseEntity<List<LtMastBranches>>(ltMastBranches, HttpStatus.OK);
	}

//====================================================================================================================
	@RequestMapping(value = "/getLikeName/{branchname}/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> getLtMastLikeName(@PathVariable("branchname") String branchname, @PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> list = ltMastBranchesService.findActiveLikeBranchName(branchname.trim(), companyId);
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}
//====================================================================================================================
	@RequestMapping(value = "/getLikeNameByCompanyId/{id}/{branchname}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastBranches>> getLtMastLikeNameByCompanyId(@PathVariable("id") Long id,@PathVariable("branchname") String branchname,@PathVariable("logTime") String logTime) throws ServiceException {
		List<LtMastBranches> list = ltMastBranchesService.getLtMastLikeNameByCompanyId(id,branchname.trim());
		return new ResponseEntity<List<LtMastBranches>>(list, HttpStatus.OK);
	}

//====================================================================================================================
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveLtMastBranches(@RequestBody @Valid LtMastBranches ltMastBranches,
			BindingResult bindingResult) throws ServiceException, IOException {
		try {
			return ltMastBranchesService.save(ltMastBranches,bindingResult);	
			}catch(Exception e) {
				throw new BusinessException(0, null, e);
			}
	}

//====================================================================================================================
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateLtMastBranch(@RequestBody @Valid LtMastBranches ltMastBranches,
			BindingResult bindingResult) throws ServiceException, IOException {
		try {
			return ltMastBranchesService.update(ltMastBranches);	
			}catch(Exception e) {
				throw new BusinessException(0, null, e);
			}
	}

//====================================================================================================================
	@RequestMapping(value = "/deleteByID/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteLtMastBranchesByID(@PathVariable("id") String id,@PathVariable("logTime") String logTime) throws ServiceException {
		try {
			return ltMastBranchesService.delete(id);	
			}catch(Exception e) {
				throw new BusinessException(0, null, e);
			}
	}

//====================================================================================================================
	
	//Lexa Application Expense nature master API Date-27-June-2024
	
	@PreAuthorize("hasPermission(null, '#/expenseType/expenseType', 'View')")
	@RequestMapping(value = "/dataTableForExpenseType/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getDataTable(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime, LtMastExpenseTypes input)
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try
		{
				Long count=ltMastExpenseTypeService.getExpenseTypesCount(input,companyId);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtMastExpenseTypes> expenseTypesList= ltMastExpenseTypeService.getExpenseTypesData(input,companyId);
			    System.out.println("input = "+input);

			    System.out.println("expenseTypesList = "+expenseTypesList);
				
			    customeDataTable.setData(expenseTypesList);
		}
		catch (Exception e)
		{
		    System.out.println("in exception");
		    e.printStackTrace();
			//throw new BusinessException(INTERNAL_SERVER_ERROR, null, e);
		}
		return customeDataTable;
	}
	
	
	@PreAuthorize("hasPermission(null, '#/expenseType/expenseType', 'Add')")
	@RequestMapping(value = "/saveExpense", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> saveExpense(@RequestBody LtMastExpenseTypes expenseTypes)
	{
			Status status=new Status();
			try
			{
			 //System.out.println("In controller...");
			 status =  ltMastExpenseTypeService.saveExpense(expenseTypes);
			
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasPermission(null, '#/expenseType/expenseType', 'View')")
	@RequestMapping(value = "/getexpensetypebyid/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LtMastExpenseTypes> getExpenseTypeById(@PathVariable("id") Long expenseTypeId,@PathVariable("logTime") String logTime )
	{
			LtMastExpenseTypes expenseTypes=null;
			try
			{
				expenseTypes =  ltMastExpenseTypeService.getExpenseTypeById(expenseTypeId);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			return new ResponseEntity<LtMastExpenseTypes>(expenseTypes, HttpStatus.OK);
			
	}
	
	
	@PreAuthorize("hasPermission(null, '#/expenseType/expenseType', 'Delete')")
	@RequestMapping(value = "/deleteExpense/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> deleteExpense(@PathVariable("id") Long expenseTypeId,@PathVariable("logTime") String logTime)
	{
			Status status=new Status();
			try
			{
			 status =  ltMastExpenseTypeService.deleteExpense(expenseTypeId);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("hasPermission(null, '#/expenseType/expenseType', 'Update')")
	@RequestMapping(value = "/updateExpense", method= RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Status> updateExpense(@RequestBody LtMastExpenseTypes expenseTypes)
	{
			Status status=new Status();
			try
			{
			 status =  ltMastExpenseTypeService.updateExpense(expenseTypes);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
//_________________________________________________________________________________________	
	
	//Lexa Application Project master API Date-28-June-2024
	
//	@RequestMapping(value = "/getAllActiveLtMastProjects", method= RequestMethod.GET, produces = "application/json")
//	 public ResponseEntity<List<LtMastProjects>> getAllActiveLtMastProjects()
//	{
//		List<LtMastProjects> ltMastProjects=new ArrayList<LtMastProjects>();
//		try
//		{
//				ltMastProjects =  ltMastProjectsService.listAllActiveLtMastProjects();
//		}
//		catch(Exception e)
//		{
//			/*e.printStackTrace();
//			logger.error("ERROR "+ e );*/
//			throw new BusinessException(INTERNAL_SERVER_ERROR, null, e);
//		}
//		return new ResponseEntity<List<LtMastProjects>>(ltMastProjects, HttpStatus.OK);
//	}
//	
//	@PreAuthorize("hasPermission(null, '#/project/project', 'View')")
//	@RequestMapping(value = "/dataTable1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public CustomeDataTable getLtMastProjectsDataTable(LtMastProjects input)
//	{
//		System.out.println("input  "+input);
//		CustomeDataTable customeDataTable = new CustomeDataTable();
//		try
//		{
//				//Long count=ltMastProjectsService.getCount(input);
//				//customeDataTable.setRecordsTotal(count);
//			    //customeDataTable.setRecordsFiltered(count);
//			    //List<LtMastProjects> projectList=
//			    		//ltMastProjectsService.getLtMastProjectsDataTable(input);
//				//customeDataTable.setData(projectList);	
//		}
//		catch (Exception e)
//		{	
//			/*logger.error("ERROR "+ e );
//			 e.printStackTrace();*/
//			throw new BusinessException(INTERNAL_SERVER_ERROR, null, e);
//		}
//		return customeDataTable;
//		
//	}
	
	@RequestMapping(value = "/getExpenseTypesLikeName/{companyId}/{name}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastExpenseTypes>> getExpenseTypesLikeName(@PathVariable("companyId") Long companyId,@PathVariable("name") String name,
			@PathVariable("logTime") String logTime){
		List<LtMastExpenseTypes> ltMastExpenseTypes = null;
		try {
			ltMastExpenseTypes = ltMastExpenseTypeService.getExpenseTypesLikeName(name.trim(),companyId);
			return new ResponseEntity<List<LtMastExpenseTypes>>(ltMastExpenseTypes, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		return new ResponseEntity<List<LtMastExpenseTypes>>(ltMastExpenseTypes, HttpStatus.OK);
		}
	}
	
	

}