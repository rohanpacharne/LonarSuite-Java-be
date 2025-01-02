
package com.lonar.UserManagement.web.controller;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.UserManagement.common.BusinessException;
import com.lonar.UserManagement.web.model.CodeMaster;
import com.lonar.UserManagement.web.model.CustomeDataTable;
import com.lonar.UserManagement.web.model.LtMastModules;
import com.lonar.UserManagement.web.model.LtMastRoleModules;
import com.lonar.UserManagement.web.model.LtMastRoles;
import com.lonar.UserManagement.web.model.RoleModule;
import com.lonar.UserManagement.web.model.RoleWithModule;
import com.lonar.UserManagement.web.model.Status;
import com.lonar.UserManagement.web.repository.LtMastModulesRepository;
import com.lonar.UserManagement.web.repository.LtMastRoleModulesRepository;
import com.lonar.UserManagement.web.repository.LtMastRolesRepository;
import com.lonar.UserManagement.web.service.LtMastCommonMessageService;
import com.lonar.UserManagement.web.service.LtMastRoleModulesService;
import com.lonar.UserManagement.web.service.LtMastRolesService;


@RestController
@RequestMapping("/api/roles")
public class LtMastRolesRestController implements CodeMaster {
	final String restBaseUrl = "/API/roles";
	// #/role/roles
	static final Logger logger = Logger.getLogger(LtMastRolesRestController.class);
	@Autowired
	LtMastRolesRepository ltMastRolesRepository;
	@Autowired
	LtMastRolesService ltMastRolesService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	@Autowired
	LtMastRoleModulesRepository ltMastRoleModulesRepository;
	@Autowired
	LtMastRoleModulesService ltMastRoleModulesService;
	@Autowired
	LtMastModulesRepository ltMastModulesRepository;

//-------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/datatable/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomeDataTable getDataTable(@PathVariable("companyId") Long companyId, LtMastRoles input,@PathVariable("logTime") String logTime){
		
		return ltMastRolesService.getDataTable(input,companyId) ;
	}

	@RequestMapping(value = "/getAll/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastRoles>> listLtMastRolesAll(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServerException {
		List<LtMastRoles> ltMastRoles =  ltMastRolesService.findAll(companyId);
		
		return new ResponseEntity<List<LtMastRoles>>(ltMastRoles, HttpStatus.OK);
	}

	// -------------------Retrieve All Role Details-----------------------------
	@RequestMapping(value = "/getAllActive/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastRoles>> listLtP2pRolesAllActive(@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServerException {
		List<LtMastRoles> ltMastRoles =  ltMastRolesService.findAllActive(companyId);
		return new ResponseEntity<List<LtMastRoles>>(ltMastRoles, HttpStatus.OK);
	}

	// -------------------Retrieve All Like RoleName-----------------------------

	@RequestMapping(value = "/getLikeRoleName/{roleName}/{companyId}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastRoles>> listLikeRoleName(@PathVariable("roleName") String roleName,@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServerException {
		List<LtMastRoles> ltMastRoles =   ltMastRolesService.findByLikeRoleName(roleName.trim(),companyId);
		return new ResponseEntity<List<LtMastRoles>>(ltMastRoles, HttpStatus.OK);
	}

	// -------------------Retrieve All Active Like Role Name-----------------------------
	@RequestMapping(value = "/getActiveLikeRoleName/{companyId}/{roleName}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LtMastRoles>> listActiveLikeRoleName(@PathVariable("roleName") String roleName,@PathVariable("companyId") Long companyId,@PathVariable("logTime") String logTime) throws ServerException {
			List<LtMastRoles> ltMastRoles = (List<LtMastRoles>) ltMastRolesService.findByActiveLikeRoleName(roleName.trim(),companyId);
			return new ResponseEntity<List<LtMastRoles>>(ltMastRoles, HttpStatus.OK);
	}

	// -------------------Retrieve Single Role----------------------------
	@RequestMapping(value = "/getByID/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LtMastRoles> getRolesByID(@PathVariable("id") Long id,@PathVariable("logTime") String logTime) throws ServerException {
		LtMastRoles ltP2pRoles =  ltMastRolesService.getRolesByID(id);
		return new ResponseEntity<LtMastRoles>(ltP2pRoles, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllModule/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleModule>> getAllRole(@PathVariable("id") String id,@PathVariable("logTime") String logTime) throws NumberFormatException, Exception {
		List<RoleModule> ltP2pModules = ltMastRoleModulesService.findByRoleIdGetModuleName(Long.parseLong(id));
		return new ResponseEntity<List<RoleModule>>(ltP2pModules, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllRoleWithModule/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoleWithModule> getAllRoleWithModule(@PathVariable("id") String id,@PathVariable("logTime") String logTime) {
		RoleWithModule roleWithModule = new RoleWithModule();
		try {
				roleWithModule.setLtMastRoles(ltMastRolesRepository.findOne(Long.parseLong(id)));
				roleWithModule.setLtMastRoleModules(
						(List<LtMastRoleModules>) ltMastRoleModulesService.findByRoleId(Long.parseLong(id)));
				LtMastModules ltMastModules = null;
				for (LtMastRoleModules ltMastRoleModules : roleWithModule.getLtMastRoleModules()) {
					ltMastModules = ltMastModulesRepository.findOne(ltMastRoleModules.getModuleId());
					if (ltMastModules != null) {
						ltMastRoleModules.setModuleName(ltMastModules.getModuleName());
						ltMastRoleModules.setModuleDescription(ltMastModules.getModuleDesc());
					} else {
						ltMastRoleModules.setModuleName("");
						ltMastRoleModules.setModuleDescription("");
					}
				}
		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
		return new ResponseEntity<RoleWithModule>(roleWithModule, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveRole( @RequestBody LtMastRoles ltMastRoles) throws ServerException {
		try {
		return ltMastRolesService.saveRole(ltMastRoles);
		}catch(Exception e) {
			throw new BusinessException(0, null, e);
		}
	}
	

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateRole( @RequestBody LtMastRoles ltMastRoles) throws ServerException {
		try {
			return ltMastRolesService.updateRole(ltMastRoles);
			}catch(Exception e) {
				throw new BusinessException(0, null, e);
			}
		
	}
	
	@RequestMapping(value = "/saveRoleWithModule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> saveRoleWithModule(@Valid @RequestBody RoleWithModule roleWithModule) throws ServerException {
		try {
			return ltMastRolesService.saveRoleWithModule(roleWithModule);
		}catch(Exception e) {
				throw new BusinessException(0, null, e);
		}
		
	}

	// -------------------Delete Single Role Details----------------------------
	@RequestMapping(value = "/deleteById/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteLtP2pRolesByID(@PathVariable("id") String id,@PathVariable("logTime") String logTime) {
		Status status = new Status();
		try {
			if (ltMastRolesRepository.exists(Long.parseLong(id))) {
				ltMastRolesRepository.delete(Long.parseLong(id));
		} else {
				return new ResponseEntity<Status>(
						new Status(0,
								ltMastCommonMessageService.getMessageNameByCode("NO_RESULT").getMessageName()),
						HttpStatus.OK);

			}

		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
//		status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
		try {
			status.setCode(1);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/deleteRoleWithModule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> deleteUserWithRole(@RequestBody String jsonInputString) {
	
		JSONParser jsonparser = new JSONParser();
		JSONObject jsonInputObject = null;
		Status status = new Status();
		try {

			if (jsonInputString.length() == 0) {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());				
				new ResponseEntity<Status>(status, HttpStatus.OK);
			}
			jsonInputObject = (JSONObject) jsonparser.parse(jsonInputString);

			List<LtMastRoleModules> ltP2pRoleModulesGet = ltMastRoleModulesService.findByRoleIdANDModuleId(
					Long.parseLong(jsonInputObject.get("roleid").toString()),
					Long.parseLong(jsonInputObject.get("moduleid").toString()));
			if (!ltP2pRoleModulesGet.isEmpty()) {
				ltMastRoleModulesRepository.delete(ltP2pRoleModulesGet.get(0).getRoleFuncId());

			} else {
//				status.setCode(INVALID_ID);
//				status.setMessage(messageSource.getMessage("invalidid", null, "Default", Locale.getDefault()));
				
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INVALID_ID").getMessageName());
				return new ResponseEntity<Status>(status, HttpStatus.OK);
			}

		}   catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR "+ e );
			throw new BusinessException(0, null, e);

		}
//		status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
		try {
			status.setCode(1);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(status,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateRoleWithModule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> updateRoleWithModule(@Valid @RequestBody RoleWithModule roleWithModule,
			BindingResult bindingResult) {
		
		Status status = new Status();
		LtMastRoles ltMastRoles = roleWithModule.getLtMastRoles();

		try {
			if (bindingResult.hasErrors()) {
				for (ObjectError objectError : bindingResult.getAllErrors()) {
					status.setCode(0);
					if (objectError.getCode().toString().equals("SafeHtml")) {

						status.setMessage(messageSource.getMessage("UnsafeHtml", null, "Default", Locale.getDefault()));
						return new ResponseEntity<Status>(status, HttpStatus.OK);
					}
				}
			}

			List<LtMastRoles> ltMastRolesList = ltMastRolesService.findByRole(ltMastRoles.getRoleName(),ltMastRoles.getCompanyId());
			if (!ltMastRolesList.isEmpty() && (ltMastRoles.getRoleId() == null ? true
					: !ltMastRoles.getRoleId().equals(ltMastRolesList.get(0).getRoleId()))) {
				status.setCode(1);
				status.setMessage(messageSource.getMessage("rolenamepresent", null, "Default", Locale.getDefault()));
				return new ResponseEntity<Status>(status, HttpStatus.OK);

			}
			ltMastRoles.setLastUpdateDate(new Date());
			ltMastRoles = ltMastRolesRepository.save(ltMastRoles);
			List<LtMastRoleModules> ltMastRoleModulesList = new ArrayList<>();
			List<LtMastRoleModules> ltMastRoleModulesGet = roleWithModule.getLtMastRoleModules();
			for (LtMastRoleModules ltMastRoleModules : ltMastRoleModulesGet) {
				if (ltMastRoleModules.getUpdateFlag().equals("update")) {

					ltMastRoleModules.setRoleId(ltMastRoles.getRoleId());
					ltMastRoleModules.setCreatedBy(ltMastRoles.getCreatedBy());
					ltMastRoleModules.setLastUpdateLogin(ltMastRoles.getLastUpdateLogin());
					ltMastRoleModules.setLastUpdatedBy(ltMastRoles.getLastUpdatedBy());
					ltMastRoleModules.setLastUpdateDate(new Date());
					ltMastRoleModulesList.add(ltMastRoleModules);
				} else if (ltMastRoleModules.getUpdateFlag().equals("delete")) {
					List<LtMastRoleModules> ltMastRoleModulesGetRoleAndMOdule = ltMastRoleModulesService
							.findByRoleIdANDModuleId(ltMastRoles.getRoleId(), ltMastRoleModules.getModuleId());
					if (!ltMastRoleModulesGetRoleAndMOdule.isEmpty()) {
						ltMastRoleModulesRepository.delete(ltMastRoleModulesGetRoleAndMOdule.get(0).getRoleFuncId());

					}
				}
			}

			ltMastRoleModulesRepository.save(ltMastRoleModulesList);

		} catch (Exception e) {
			throw new BusinessException(0, null, e);
		}
		
//		status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
		try {
			status.setCode(1);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<Status>(status,HttpStatus.OK);
	}
	
}