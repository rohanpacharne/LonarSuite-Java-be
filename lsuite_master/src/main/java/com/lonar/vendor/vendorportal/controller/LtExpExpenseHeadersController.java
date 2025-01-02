package com.lonar.vendor.vendorportal.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.CustomeDataTable;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.service.LtExpExpenseHeadersService;

@RestController
@RequestMapping("/API/expExpense")
public class LtExpExpenseHeadersController implements CodeMaster {
	
	@Autowired
	LtExpExpenseHeadersService ltExpExpenseHeadersService;
	
	@Autowired
	private MessageSource messageSource;
	
	@PreAuthorize("hasPermission(null, '#/expenses/expenseDemo', 'View') or hasPermission(null, '#/myAdvance/advanceDemo', 'View')")
	@RequestMapping(value = "/dataTable1/{role}/{category}/{empId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getLtMastExpenceHeaderDataTable1(LtExpExpenseHeaders input,@PathVariable("role") String role,
			@PathVariable("category") String category,@PathVariable("empId") Long empId,@PathVariable("logTime") String logTime)
	{
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try
		{
				Long count=ltExpExpenseHeadersService.getCount(input,empId,category,role);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtExpExpenseHeaders> ltExpExpenseHeadersList=
			    		ltExpExpenseHeadersService.getExpenseRecords(input,empId,category,role);
				customeDataTable.setData(ltExpExpenseHeadersList);	
		}
		catch (Exception e)
		{
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
		
	}
	
	// -------------------Create expenseHeader details-----------------
		@PreAuthorize("hasPermission(null, '#/myExpense/MYexpense', 'Add') or hasPermission(null, '#/myAdvance/advanceDemo', 'Add') ")
			@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<Status> saveLtExpExpenseHeaders( @RequestBody LtExpExpenseHeaders ltExpExpenseHeaders ,
						BindingResult bindingResult)  // @Valid
			{
				Status status = new Status();
				try
				{
					if (bindingResult.hasErrors())
					{
						for (ObjectError objectError : bindingResult.getAllErrors())
							{
								status.setCode(0);
								
								if (objectError.getCode().equals("NotNull"))
								{
									status.setMessage(messageSource.getMessage(objectError, Locale.getDefault()));
									return new ResponseEntity<Status>(status, HttpStatus.OK);
								}
								
								if (objectError.getCode().toString().equals("SafeHtml"))
								{
									status.setMessage(messageSource.getMessage("UnsafeHtml", null, "Default", Locale.getDefault()));
									return new ResponseEntity<Status>(status, HttpStatus.OK);
								}
							}
					}
					
					if(ltExpExpenseHeaders!=null)
					{
						status=ltExpExpenseHeadersService.save(ltExpExpenseHeaders);
					}
				}
				catch (NullPointerException e)
				{
					throw new BusinessException(0, null, e);
				}
				catch (Exception e) {
					throw new BusinessException(0, null, e);
				}
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}
		
		@PreAuthorize("hasPermission(null, '#/expenses/expenseDemo', 'View') or hasPermission(null, '#/myAdvance/advanceDemo', 'View')")
		@RequestMapping(value = "/getExpenseHeaderByHeaderId/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<LtExpExpenseHeaders> getExpenseHeaderByHeaderId(@PathVariable("id") Long headerId,@PathVariable("logTime") String logTime) 
			{
				LtExpExpenseHeaders ltExpExpenseHeaders= null;
				try  {
					if(headerId != null) {
						ltExpExpenseHeaders=ltExpExpenseHeadersService.findOne((headerId));
					}
				} catch (Exception e) {
					throw new BusinessException(0, null, e);
				}
				return new ResponseEntity<LtExpExpenseHeaders>(ltExpExpenseHeaders, HttpStatus.OK);
			}

		@PreAuthorize("hasPermission(null, '#/myExpense/MYexpense', 'Delete') or hasPermission(null, '#/myAdvance/advanceDemo', 'Delete')")
		@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
		 public ResponseEntity<Status> delete(@PathVariable("id") String expHeaderId,@PathVariable("logTime") String logTime)
		{
			Status status=new Status();
			try
			{
				status =  ltExpExpenseHeadersService.delete(Long.parseLong(expHeaderId));
				
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
		
		@RequestMapping(value = "/getExpenseStatusById/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
	    public ResponseEntity<LtExpExpenseHeaders> getInvoiceStatusById(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServiceException
		{
			LtExpExpenseHeaders ltExpExpenseHeaders=  ltExpExpenseHeadersService.getExpenseStatusById(id);
				return new ResponseEntity<LtExpExpenseHeaders>(ltExpExpenseHeaders, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/checkStatusIsPending/{expHeaderid}/{approvalid}/{logTime}", method= RequestMethod.GET, produces = "application/json")
	    public boolean checkStatusIsPending(@PathVariable("expHeaderid") Long expHeaderid, @PathVariable("approvalid") Long approvalid,@PathVariable("logTime") String logTime) throws ServiceException
		{
			return  ltExpExpenseHeadersService.checkStatusIsPending(expHeaderid, approvalid);
		}
		
		@RequestMapping(value = "/deleteExpenseHeaderAttachments/{expAttachId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
	    public ResponseEntity<Status> deleteExpenseLineAttachments(@PathVariable("expAttachId") Long expAttachId,@PathVariable("logTime") Long logTime)
		{
				Status status=new Status();
				try
				{
				 status =  ltExpExpenseHeadersService.deleteExpenseHeaderAttachments(expAttachId);
				}
				catch(Exception e)
				{
					throw new BusinessException(0, null, e);
				}
				return new ResponseEntity<Status>(status, HttpStatus.OK);
		}

}
