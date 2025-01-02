package com.lonar.vendor.vendorportal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import com.lonar.vendor.vendorportal.model.Customer;
import com.lonar.vendor.vendorportal.model.Employee;
import com.lonar.vendor.vendorportal.model.LtMastCustomersDto;
import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;
import com.lonar.vendor.vendorportal.model.LtMastProjects;
import com.lonar.vendor.vendorportal.model.ProjectWithTaskValues;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtMastProjectRepository;
import com.lonar.vendor.vendorportal.repository.LtMastProjectTasksRepository;
import com.lonar.vendor.vendorportal.service.LtMastCommonMessageService;
import com.lonar.vendor.vendorportal.service.LtMastComnMasterValuesService;
import com.lonar.vendor.vendorportal.service.LtMastProjectTaskService;
import com.lonar.vendor.vendorportal.service.LtMastProjectsService;

@RestController
@RequestMapping("/API/projects")
public class LtMastProjectsRestController implements CodeMaster {
	
	final String restBaseUrl = "/API/projects";
	static final Logger logger = Logger.getLogger(LtMastProjectsRestController.class);
	
//	@Autowired
//	LtMastProjectRepository ltMastProjectRepository;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtMastProjectsService ltMastProjectsService;
	
	@Autowired
	LtMastProjectRepository ltMastProjectRepository;
	
	@Autowired
	 LtMastProjectTaskService ltMastProjectTaskService;
	
	@Autowired
    LtMastProjectTasksRepository ltMastProjectTasksRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	LtMastComnMasterValuesService ltMastComnMasterValuesService;
	
	
	@RequestMapping(value = "/getAllActiveLtMastProjects/{logTime}", method= RequestMethod.GET, produces = "application/json")
	 public ResponseEntity<List<LtMastProjects>> getAllActiveLtMastProjects(@PathVariable("logTime") String logTime)
	{
		List<LtMastProjects> ltMastProjects=new ArrayList<LtMastProjects>();
		try
		{
				ltMastProjects =  ltMastProjectsService.listAllActiveLtMastProjects();
		}
		catch(Exception e)
		{
			/*e.printStackTrace();
			logger.error("ERROR "+ e );*/
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<List<LtMastProjects>>(ltMastProjects, HttpStatus.OK);
	}
	
	@PreAuthorize("hasPermission(null, '#/project/project', 'View')")
	@RequestMapping(value = "/dataTable1/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getLtMastProjectsDataTable(@PathVariable("logTime") String logTime,LtMastProjects input,@PathVariable("companyId") long companyId)
	{
		System.out.println("input  "+input);
		CustomeDataTable customeDataTable = new CustomeDataTable();
		try
		{
				Long count=ltMastProjectsService.getCount(input,companyId);
				customeDataTable.setRecordsTotal(count);
			    customeDataTable.setRecordsFiltered(count);
			    List<LtMastProjects> projectList=
			    		ltMastProjectsService.getLtMastProjectsDataTable(input,companyId);
				customeDataTable.setData(projectList);	
		}
		catch (Exception e)
		{	
			/*logger.error("ERROR "+ e );
			 e.printStackTrace();*/
			throw new BusinessException(0, null, e);
		}
		return customeDataTable;
		
	}
	
	@PreAuthorize("hasPermission(null, '#/project/project', 'Add')")
	@RequestMapping(value = "/saveWithTaskValues", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveLtMastProjects(@Valid @RequestBody ProjectWithTaskValues projectWithTaskValues,
			BindingResult bindingResult) {
	
		String projectObj = null;
		String code=null;
		Status status = new Status();
		LtMastProjects ltMastProjects=projectWithTaskValues.getLtMastProjects();
		System.out.println("projectWithTaskValues = "+projectWithTaskValues);
		try
		{

			if (bindingResult.hasErrors()) {
				for (ObjectError objectError : bindingResult.getAllErrors()) {
					status.setCode(0);
					if (objectError.getCode().toString().equals("SafeHtml")) {

						status.setMessage(messageSource.getMessage("UnsafeHtml", null, "Default", Locale.getDefault()));
						return new ResponseEntity<Status>(status, HttpStatus.OK);
					}
				}
			}

			ltMastProjects.setCreatedBy(ltMastProjects.getLastUpdateLogin());
			ltMastProjects.setLastUpdatedBy(ltMastProjects.getLastUpdateLogin());
			ltMastProjects.setLastUpdateLogin(ltMastProjects.getLastUpdateLogin());
			ltMastProjects.setLastUpdateDate(new Date());
			projectObj =ltMastProjectsService.isFeildsExists(ltMastProjects);

			if (projectObj == null)
			{
				ltMastProjects = ltMastProjectRepository.save(ltMastProjects);
				List<LtMastProjectTasks> ltMastProjectTaskList= projectWithTaskValues.getLtMastProjectTaskValues();
				for (LtMastProjectTasks LtMastProjectTasks : ltMastProjectTaskList)
				{

					code=ltMastProjectTaskService.checkProjectTaskValuesDetails(LtMastProjectTasks);
					if (code==null)
					{
						LtMastProjectTasks.setProjectId(ltMastProjects.getProjectId());
						LtMastProjectTasks.setCreatedBy(ltMastProjects.getCreatedBy());
						LtMastProjectTasks.setCreationDate(new Date());
						LtMastProjectTasks.setLastUpdateLogin(ltMastProjects.getLastUpdateLogin());
						LtMastProjectTasks.setLastUpdateDate(new Date());
						LtMastProjectTasks.setLastUpdatedBy(ltMastProjects.getLastUpdatedBy());
						if (LtMastProjectTasks.getProjectId() == null)
						{
//							status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
							status.setCode(0);		
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());

							return new ResponseEntity<Status>(status, HttpStatus.OK);
						}
					
						ltMastProjectTasksRepository.save(LtMastProjectTasks);
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
						status.setCode(0);		
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
						return new ResponseEntity<Status>(status, HttpStatus.OK);
						
					}
		
				}
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
				status.setCode(1);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
			}

			else
			{

//				status=ltMastCommonMessageService.getCodeAndMessage(Data_Exist);
				status.setCode(0);		
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DATA_EXIST").getMessageName());
				status.setMessage(projectObj);
				return new ResponseEntity<Status>(status, HttpStatus.OK);

			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);

		}
		catch (NullPointerException e) {
			/*e.printStackTrace();
			logger.error("ERROR "+ e );*/
			throw new BusinessException(0, null, e);
		}
		catch (Exception e)
		{/*e.printStackTrace();
			logger.error("ERROR "+ e );*/
			throw new BusinessException(0, null, e);
		}
			
	}
	
	@RequestMapping(value = "/getAllEmployee/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployee(@PathVariable("logTime") String logTime){
		try {
//			System.out.println("in try...at getAllEmployee");
			return ltMastProjectsService.getAllEmployee();
		}
		catch(Exception ex) {
			ex.printStackTrace();
//			System.out.println("in exception...at getAllEmployee");
			return new ArrayList<Employee>();
		}
	}
	
	
	@RequestMapping(value = "/getAllCustomer/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAllCustomer(@PathVariable("logTime") String logTime){
		try {
			//System.out.println("list is "+ltMastProjectsService.getAllCustomer());
			return ltMastProjectsService.getAllCustomer();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<Customer>();
		}
	}
	
	@RequestMapping(value = "/getCustomersLikeName/{companyId}/{name}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastCustomersDto>> getCustomersLikeName(@PathVariable("name") String name,
			@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime){
		System.out.println("in getCustomersLikeName");
		List<LtMastCustomersDto> ltMastCustomersDto = null;
		try {
			ltMastCustomersDto = ltMastProjectsService.getCustomersLikeName(name.trim(),companyId);
			return new ResponseEntity<List<LtMastCustomersDto>>(ltMastCustomersDto, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		return new ResponseEntity<List<LtMastCustomersDto>>(ltMastCustomersDto, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getProjectsLikeName/{companyId}/{name}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastProjects>> getProjectsLikeName(@PathVariable("companyId") long companyId,@PathVariable("name") String name,
			@PathVariable("logTime") String logTime){
//		System.out.println("in getCustomersLikeName");
		List<LtMastProjects> ltMastProjects = null;
		try {
			ltMastProjects = ltMastProjectsService.getProjectsLikeName(name.trim(),companyId);
			return new ResponseEntity<List<LtMastProjects>>(ltMastProjects, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		return new ResponseEntity<List<LtMastProjects>>(ltMastProjects, HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasPermission(null, '#/project/project', 'View')")
	@RequestMapping(value = "/getByProjectId/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ProjectWithTaskValues> listLtMastProjectTaskAll(@PathVariable("id") Long id,@PathVariable("logTime") String logTime)
		{
			ProjectWithTaskValues projectWithTaskValues = new ProjectWithTaskValues();
			try
			{
				if(id!=null)
				{
					projectWithTaskValues=ltMastProjectsService.getById(id);
				}
				else
				{
					return new ResponseEntity<ProjectWithTaskValues>(projectWithTaskValues, HttpStatus.OK);
				}
			}
			catch (Exception e)
			{
				/*e.printStackTrace();
				logger.error("ERROR "+ e );*/
				throw new BusinessException(0, null, e);
			}
			//System.out.println("projectWithTaskValues = "+projectWithTaskValues);
			return new ResponseEntity<ProjectWithTaskValues>(projectWithTaskValues, HttpStatus.OK);
		}
	
	@PreAuthorize("hasPermission(null, '#/project/project', 'Update')")
	@RequestMapping(value = "/updateProjectWithValue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateMasterWithValue(@Valid @RequestBody ProjectWithTaskValues projectWithTaskValues,
			BindingResult bindingResult)
	{
		Status status = new Status();
		try
		{
			if (bindingResult.hasErrors())
			{
				for (ObjectError objectError : bindingResult.getAllErrors())
				{
					status.setCode(0);
				
					if (objectError.getCode().toString().equals("SafeHtml"))
					{
						status.setMessage(messageSource.getMessage("UnsafeHtml", null, "Default", Locale.getDefault()));
						return new ResponseEntity<Status>(status, HttpStatus.OK);
					}
				}
			}
			status =ltMastProjectsService.updateMasterWithValue(projectWithTaskValues);
			
			return new ResponseEntity<Status>(status, HttpStatus.OK);

		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
			logger.error("ERROR "+ e );
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
					
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			return new ResponseEntity<Status>(status,HttpStatus.OK);
			

		}
		catch (Exception e)
		{
			/*e.printStackTrace();
			logger.error("ERROR "+ e );*/
			throw new BusinessException(0, null, e);
		}

	
	}
	
	
	@PreAuthorize("hasPermission(null, '#/project/project', 'Delete')")
	@RequestMapping(value = "/delete/{id}/{logTime}", method= RequestMethod.GET, produces = "application/json")
		 public ResponseEntity<Status> delete(@PathVariable("id") String projectId,@PathVariable("logTime") String logTime)
		{
			Status status=new Status();
			try
			{
				status =  ltMastProjectsService.delete(Long.parseLong(projectId));
			}
			catch (org.springframework.dao.DataIntegrityViolationException e)
			{
				logger.error("ERROR "+ e );
				e.printStackTrace();
//				status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
					
				try {
					status.setCode(0);	
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				return new ResponseEntity<Status>(status,HttpStatus.OK);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
	
	@PreAuthorize("hasPermission(null, '#/project/project', 'Delete')")
	@RequestMapping(value = "/deleteTask/{taskId}/{projectId}/{logTime}", method= RequestMethod.GET, produces = "application/json")
		 public ResponseEntity<Status> deleteTask(@PathVariable("taskId") String taskId,@PathVariable("projectId") String projectId,@PathVariable("logTime") String logTime)
		{
			Status status=new Status();
			try
			{
				status =  ltMastProjectsService.deleteTask(Long.parseLong(taskId),Long.parseLong(projectId));
			}
			catch (org.springframework.dao.DataIntegrityViolationException e)
			{
				logger.error("ERROR "+ e );
				e.printStackTrace();
//				status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
						
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				return new ResponseEntity<Status>(status,HttpStatus.OK);
			}
			catch(Exception e)
			{
				throw new BusinessException(0, null, e);
			}
			return new ResponseEntity<Status>(status, HttpStatus.OK);
		}
	

}
