package com.lonar.vendor.vendorportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.lonar.vendor.vendorportal.model.DatatableData;
import com.lonar.vendor.vendorportal.model.LtNotificationsPojo;
import com.lonar.vendor.vendorportal.service.LtExpDashboardService;

@RestController
@RequestMapping("/API/expenseDashboard")
public class LtExpDashboardRestcontroller {
	
	@Autowired
	LtExpDashboardService ltExpDashboardService;
	
	@RequestMapping(value = "/getExpenseCountByEmpId/{empId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DatatableData>> getExpenseCountByEmpId(@PathVariable("empId") Long empId,@PathVariable("logTime") String logTime) 
	{
		List<DatatableData> datatableDataList=new ArrayList<DatatableData>();
		try 
		{
			if(empId != null)
			{
				datatableDataList=ltExpDashboardService.getExpenseCountByHeaderId((empId));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<List<DatatableData>>(datatableDataList, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<DatatableData>>(datatableDataList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getExpenseTypeByEmpId/{empId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DatatableData>> getExpenseTypeByEmpId(@PathVariable("empId") Long empId,@PathVariable("logTime") String logTime) 
	{
		List<DatatableData> datatableDataList=new ArrayList<DatatableData>();
		try 
		{
			if(empId != null)
			{
				datatableDataList=ltExpDashboardService.getExpenseTypeByEmpId((empId));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<List<DatatableData>>(datatableDataList, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<DatatableData>>(datatableDataList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getNotificatioByEmpId/{empId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LtNotificationsPojo> getNotificatioByEmpId(@PathVariable("empId") Long empId,@PathVariable("logTime") String logTime) 
	{
		LtNotificationsPojo ltNotifications=new LtNotificationsPojo();
		try 
		{
			if(empId != null)
			{
				ltNotifications=ltExpDashboardService.getNotificatioByEmpId((empId));
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ResponseEntity<LtNotificationsPojo>(ltNotifications, HttpStatus.OK);
		}
		
		return new ResponseEntity<LtNotificationsPojo>(ltNotifications, HttpStatus.OK);
	}
}
