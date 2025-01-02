package com.lonar.vendor.vendorportal.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ExpenseApproval extends WhoColumns
{
	
	private Long expApprovalId;
	private Long moduleApprovalId;
	private Long approvalId;
	private String approvalLevel;
	private String currentApprovalLevel;
	private Long expHeaderId;
	private String status;
	private Long delegationId;
	private Long moduleAppEmployeesId;
	private String approvedByAnyone;
	public Long getExpApprovalId() {
		return expApprovalId;
	}
	public void setExpApprovalId(Long expApprovalId) {
		this.expApprovalId = expApprovalId;
	}
	public Long getModuleApprovalId() {
		return moduleApprovalId;
	}
	public void setModuleApprovalId(Long moduleApprovalId) {
		this.moduleApprovalId = moduleApprovalId;
	}
	public Long getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}
	public String getApprovalLevel() {
		return approvalLevel;
	}
	public void setApprovalLevel(String approvalLevel) {
		this.approvalLevel = approvalLevel;
	}
	public String getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}
	public void setCurrentApprovalLevel(String currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}
	public Long getExpHeaderId() {
		return expHeaderId;
	}
	public void setExpHeaderId(Long expHeaderId) {
		this.expHeaderId = expHeaderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getDelegationId() {
		return delegationId;
	}
	public void setDelegationId(Long delegationId) {
		this.delegationId = delegationId;
	}
	public Long getModuleAppEmployeesId() {
		return moduleAppEmployeesId;
	}
	public void setModuleAppEmployeesId(Long moduleAppEmployeesId) {
		this.moduleAppEmployeesId = moduleAppEmployeesId;
	}
	public String getApprovedByAnyone() {
		return approvedByAnyone;
	}
	public void setApprovedByAnyone(String approvedByAnyone) {
		this.approvedByAnyone = approvedByAnyone;
	}
	@Override
	public String toString() {
		return "ExpenseApproval [expApprovalId=" + expApprovalId + ", moduleApprovalId=" + moduleApprovalId
				+ ", approvalId=" + approvalId + ", approvalLevel=" + approvalLevel + ", currentApprovalLevel="
				+ currentApprovalLevel + ", expHeaderId=" + expHeaderId + ", status=" + status + ", delegationId="
				+ delegationId + ", moduleAppEmployeesId=" + moduleAppEmployeesId + ", approvedByAnyone="
				+ approvedByAnyone + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}

