package com.lonar.vendor.vendorportal.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastProjectTasks;
import com.lonar.vendor.vendorportal.service.LtMastProjectTaskService;

@RestController
@RequestMapping("/API/tasks")
public class LtMastProjectTaskRestController implements CodeMaster {
	
	final String restBaseUrl = "/API/tasks";
	static final Logger logger = Logger.getLogger(LtMastProjectTaskRestController.class);
	
	@Autowired
	private LtMastProjectTaskService ltMastProjectTaskService;
	
	//@PreAuthorize("hasPermission(null, '#/project/project', 'View')")
		@RequestMapping(value = "/getTaskByProjectID/{id}/{logTime}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<LtMastProjectTasks>> getTaskByProjectID(@PathVariable("id") Long id,@PathVariable("logTime") String logTime)
		{
				List<LtMastProjectTasks> ltMastProjectTasks = new ArrayList<LtMastProjectTasks>();
				try {
						
						ltMastProjectTasks =ltMastProjectTaskService.findByProjectId(id);
					} catch (NumberFormatException e) {
						logger.error("ERROR "+ e );
						e.printStackTrace();
						return new ResponseEntity<List<LtMastProjectTasks>>(ltMastProjectTasks, HttpStatus.OK);
					} catch (Exception e) {
						/*e.printStackTrace();
						logger.error("ERROR "+ e );*/
						throw new BusinessException(0, null, e);
						}
					return new ResponseEntity<List<LtMastProjectTasks>>(ltMastProjectTasks, HttpStatus.OK);
				}

}
