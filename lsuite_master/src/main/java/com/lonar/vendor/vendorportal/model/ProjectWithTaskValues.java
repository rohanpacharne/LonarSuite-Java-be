package com.lonar.vendor.vendorportal.model;

import java.util.List;
 
import javax.validation.Valid;
 
public class ProjectWithTaskValues {
	
	private @Valid LtMastProjects ltMastProjects;
	private @Valid List<LtMastProjectTasks> ltMastProjectTaskValues;
	
	public LtMastProjects getLtMastProjects() {
		return ltMastProjects;
	}
	public void setLtMastProjects(LtMastProjects ltMastProjects) {
		this.ltMastProjects = ltMastProjects;
	}
	public List<LtMastProjectTasks> getLtMastProjectTaskValues() {
		return ltMastProjectTaskValues;
	}
	public void setLtMastProjectTaskValues(List<LtMastProjectTasks> ltMastProjectTaskValues) {
		this.ltMastProjectTaskValues = ltMastProjectTaskValues;
	}
	@Override
	public String toString() {
		return "ProjectWithTaskValues [ltMastProjects=" + ltMastProjects + ", ltMastProjectTaskValues="
				+ ltMastProjectTaskValues + "]";
	}
	
	
	
 
}
