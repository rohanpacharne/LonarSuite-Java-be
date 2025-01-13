package com.lonar.vendor.vendorportal.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtExpExpenseLines;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtExpExpenseLinesService;

@RestController
@RequestMapping("/API/expExpenseLines")
public class LtExpExpenseLinesRestController implements CodeMaster {
	
	@Autowired
	LtExpExpenseLinesService ltExpExpenseLinesService;
	
	
	@RequestMapping(value = "/datatable1/{expenseHeaderId}/{userId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable datatable1(@PathVariable(value = "expenseHeaderId", required = false) String headerIdStr,@PathVariable("userId") Long userId,@PathVariable("logTime") Long logTime,LtExpExpenseLines input)
	{
		 Long headerId = null;
		System.out.println("input "+input.getColumnNo()+" "+input.getName());
		CustomeDataTable customeDataTable = new CustomeDataTable();
		
		 if (!"null".equals(headerIdStr)) {
	            try {
	                headerId = Long.valueOf(headerIdStr);
	            } catch (NumberFormatException e) {
	                throw new BusinessException(0, null, e);
	            }
	        }
		try
		{
			if(headerId != null)
			{
				Long count=ltExpExpenseLinesService.getCount(headerId,userId,input);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
				List<LtExpExpenseLines> ltExpExpenseLinesList=ltExpExpenseLinesService.getDatatableByExpenseHeaderId(headerId,userId,input);
				customeDataTable.setData(ltExpExpenseLinesList);
			
			}else {
				Long count=ltExpExpenseLinesService.getCount(headerId,userId,input);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
				List<LtExpExpenseLines> ltExpExpenseLinesList=ltExpExpenseLinesService.getDatatableByExpenseHeaderId(headerId,userId,input);
				customeDataTable.setData(ltExpExpenseLinesList);
			}
		}
		catch (Exception e)
		{		
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
	}
	
	@RequestMapping(value = "/updateV1/{companyId}", method = RequestMethod.POST )
	public ResponseEntity<Status> updateLtExpExpenseLinesV1(@PathVariable("companyId") int companyId,@RequestParam("ltExpExpenseLines") String ltExpExpenseLines,
			@RequestParam("files") MultipartFile[] files) {
		Status status = new Status();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonInputObject = null;
		try
		{
			jsonInputObject = (JSONObject) jsonparser.parse(ltExpExpenseLines.trim());
			LtExpExpenseLines ltExpExpenseLine = new ObjectMapper().readValue(ltExpExpenseLines,
					LtExpExpenseLines.class);
			System.out.println("update "+ltExpExpenseLine);
				status=ltExpExpenseLinesService.updateV1(ltExpExpenseLine,files,companyId);
		}
		catch (NullPointerException e)
		{
			throw new BusinessException(0, null, e);
		}
		catch (Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveV1/{companyId}", method = RequestMethod.POST )
	public ResponseEntity<Status> saveLtExpExpenseLinesV1(@PathVariable("companyId") int companyId,@RequestParam("ltExpExpenseLines") String ltExpExpenseLines,
			@RequestParam("files") MultipartFile[] files) {
		Status status = new Status();
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonInputObject = null;
		try
		{
			jsonInputObject = (JSONObject) jsonparser.parse(ltExpExpenseLines.trim());
			LtExpExpenseLines ltExpExpenseLine = new ObjectMapper().readValue(ltExpExpenseLines,
					LtExpExpenseLines.class);
			System.out.println("ltExpExpenseLine "+ltExpExpenseLine);
			System.out.println(ltExpExpenseLine.getStartDate()+"  "+ltExpExpenseLine.getLastUpdateLogin());
				status=ltExpExpenseLinesService.saveV1(ltExpExpenseLine,files,companyId);
				System.out.println("status "+status);
		}
		catch (NullPointerException e)
		{
			throw new BusinessException(0, null, e);
		}
		catch (Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getExpenseLinesByExpHeaderId/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtExpExpenseLines>> getExpenseLinesByExpHeaderId(@PathVariable("id") Long headerId,@PathVariable("logTime") String logTime) 
	{
		String apiUrl = "/getExpenseLinesByExpHeaderId/" + headerId;
		//logger.info("Input " + restBaseUrl + apiUrl);
		List<LtExpExpenseLines> ltExpExpenseLinesList=null;
		try 
		{
			if(headerId != null)
			{
				ltExpExpenseLinesList=ltExpExpenseLinesService.getByExpenseHeaderId((headerId));
			}
		}
		catch (Exception e) 
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<List<LtExpExpenseLines>>(ltExpExpenseLinesList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getByExpenseLineIdV1/{expLineId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LtExpExpenseLines> getExpenseLinesByLineIdV1(@PathVariable("expLineId") Long lineId,@PathVariable("logTime") Long logTime) 
	{
		LtExpExpenseLines ltExpExpenseLines=null;
		try 
		{
			if(lineId != null)
			{
				ltExpExpenseLines=ltExpExpenseLinesService.getExpenseLinesByLineIdV1(lineId);
			}
		}
		catch (Exception e) 
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<LtExpExpenseLines>(ltExpExpenseLines, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteByLineId/{expLineId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> delete(@PathVariable("expLineId") Long lineId,@PathVariable("logTime") Long logTime)
	{
			Status status=new Status();
			try
			{
			 status =  ltExpExpenseLinesService.delete(lineId);
			}
			catch (org.springframework.dao.DataIntegrityViolationException e)
			{
				throw new BusinessException(0, null, e);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteExpenseLineAttachments/{expLineId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Status> deleteExpenseLineAttachments(@PathVariable("expLineId") Long expLineId,@PathVariable("logTime") Long logTime)
	{
			Status status=new Status();
			try
			{
			 status =  ltExpExpenseLinesService.deleteExpenseLineAttachments(expLineId);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateHeader/{expHeaderId}", method = RequestMethod.POST )
	public ResponseEntity<Status> updateHeader(@PathVariable("expHeaderId") Long expHeaderId,
			@RequestBody List<Long> expLineIds) {
		Status status = new Status();
		try
		{
			
			System.out.println("updateHeader "+expHeaderId);
			status=ltExpExpenseLinesService.updateHeader(expHeaderId,expLineIds);
		}
		catch (Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}
}
