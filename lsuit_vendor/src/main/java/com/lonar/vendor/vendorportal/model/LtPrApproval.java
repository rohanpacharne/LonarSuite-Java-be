package com.lonar.vendor.vendorportal.model;

public class LtPrApproval {
	
	private Long prApprovalId;
	private Long moduleApprovalId;
	private Long approvalId;
	private String approvalLevel;
	private String currentApprovalLevel;
	private Long prHeaderId;
	private String status;
	private Long delegationId;
	private String approvedByAnyone;
	
	public Long getPrApprovalId() {
		return prApprovalId;
	}
	public void setPrApprovalId(Long prApprovalId) {
		this.prApprovalId = prApprovalId;
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
	public Long getPrHeaderId() {
		return prHeaderId;
	}
	public void setPrHeaderId(Long prHeaderId) {
		this.prHeaderId = prHeaderId;
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
	public String getApprovedByAnyone() {
		return approvedByAnyone;
	}
	public void setApprovedByAnyone(String approvedByAnyone) {
		this.approvedByAnyone = approvedByAnyone;
	}
	@Override
	public String toString() {
		return "LtPrApproval [prApprovalId=" + prApprovalId + ", moduleApprovalId=" + moduleApprovalId + ", approvalId="
				+ approvalId + ", approvalLevel=" + approvalLevel + ", currentApprovalLevel=" + currentApprovalLevel
				+ ", prHeaderId=" + prHeaderId + ", status=" + status + ", delegationId=" + delegationId
				+ ", approvedByAnyone=" + approvedByAnyone + "]";
	}
	
	

}
