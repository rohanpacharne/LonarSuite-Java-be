package com.lonar.vendor.vendorportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtMastPersonType;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtMastPersonTypeService;

@RestController
@RequestMapping("/API/persontype")
public class LtMastPersonTypeRestController implements CodeMaster{
	@Autowired
	LtMastPersonTypeService ltMastPersonTypeService;
	
	
	@RequestMapping(value = "/dataTable/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getDataTable( @PathVariable("id") Long companyId,LtMastPersonType input,@PathVariable("logTime") String logTime) 
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try  {
				Long count=ltMastPersonTypeService.getCount(input,companyId);
			    customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
				List<LtMastPersonType> ltMastPersonTypeList= ltMastPersonTypeService.getDataTable(input,companyId);
				customeDataTable.setData(ltMastPersonTypeList);
			
		} 
		catch (Exception e) {		
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> save(@RequestBody LtMastPersonType ltMastPersonType) throws ServiceException 
	{
		try {
				return ltMastPersonTypeService.save(ltMastPersonType);	
		}catch(Exception e) {
			throw new BusinessException(0, null, e);
		}
	}
	//------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> update(@RequestBody LtMastPersonType ltMastPersonType) throws ServiceException 
	{
		try {
				return ltMastPersonTypeService.update(ltMastPersonType);	
		}catch(Exception e) {
			throw new BusinessException(0, null, e);
		}
	}
			
	// -------------------Delete single Product Sub Category Details-------------------------------
	@RequestMapping(value = "/deleteByID/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteByID(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException 
	{
		try {
			return ltMastPersonTypeService.delete(id);
		}catch(Exception e) {
			throw new BusinessException(0, null, e);
		}
	}	
			
	@RequestMapping(value = "/getbyid/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LtMastPersonType> getLtMastPersonTypeByID(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) {
		LtMastPersonType ltMastPersonType = null;
		try {
			ltMastPersonType = ltMastPersonTypeService.findById(id);
			return new ResponseEntity<LtMastPersonType>(ltMastPersonType, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<LtMastPersonType>(ltMastPersonType, HttpStatus.OK);
		}
	}
			
	//------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getAll/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastPersonType>> getAll(@PathVariable("logTime") String logTime){
		List<LtMastPersonType> ltMastPersonTypeList = null;
		try {
			ltMastPersonTypeList = ltMastPersonTypeService.getAll();
			return new ResponseEntity<List<LtMastPersonType>>(ltMastPersonTypeList, HttpStatus.OK);
		}  catch (Exception e)  {
			return new ResponseEntity<List<LtMastPersonType>>(ltMastPersonTypeList, HttpStatus.OK);
		}
	}
	
	//------------------------------------------------------------------------------------------------
		@RequestMapping(value = "/getAllActive/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<LtMastPersonType>> getAllActive(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime){
			List<LtMastPersonType> ltMastPersonTypeList = null;
			try {
				ltMastPersonTypeList = ltMastPersonTypeService.getAllActive(companyId);
				return new ResponseEntity<List<LtMastPersonType>>(ltMastPersonTypeList, HttpStatus.OK);
			}  catch (Exception e)  {
				e.printStackTrace();
				return new ResponseEntity<List<LtMastPersonType>>(ltMastPersonTypeList, HttpStatus.OK);
			}
		}
	//---------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/getLikeName/{persontype}/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastPersonType>> getLikePersonTypeName(@PathVariable("persontype") String personType,
			@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime){
		List<LtMastPersonType> ltMastPersonTypeList = null;
		try {
			ltMastPersonTypeList = ltMastPersonTypeService.getLikePersonTypeName(personType.trim(),companyId);
			return new ResponseEntity<List<LtMastPersonType>>(ltMastPersonTypeList, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<List<LtMastPersonType>>(ltMastPersonTypeList, HttpStatus.OK);
		}
	}
}
