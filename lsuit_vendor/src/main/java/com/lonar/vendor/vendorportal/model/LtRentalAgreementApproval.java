package com.lonar.vendor.vendorportal.model;

public class LtRentalAgreementApproval extends BaseClass{
	
	private Long agreementApprovalId;
	private Long moduleApprovalId;
	private Long approvalId;
	private String approvalLevel;
	private String currentApprovalLevel;
	private Long agreementHeaderId;
	private String status;
	private Long delegationId;
	private String approvedByAnyone;
	
	public LtRentalAgreementApproval() {
		super();
	}

	public LtRentalAgreementApproval(Long agreementApprovalId, Long moduleApprovalId, Long approvalId,
			String approvalLevel, String currentApprovalLevel, Long agreementHeaderId, String status, Long delegationId,
			String approvedByAnyone) {
		super();
		this.agreementApprovalId = agreementApprovalId;
		this.moduleApprovalId = moduleApprovalId;
		this.approvalId = approvalId;
		this.approvalLevel = approvalLevel;
		this.currentApprovalLevel = currentApprovalLevel;
		this.agreementHeaderId = agreementHeaderId;
		this.status = status;
		this.delegationId = delegationId;
		this.approvedByAnyone = approvedByAnyone;
	}

	public Long getAgreementApprovalId() {
		return agreementApprovalId;
	}

	public void setAgreementApprovalId(Long agreementApprovalId) {
		this.agreementApprovalId = agreementApprovalId;
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

	public Long getAgreementHeaderId() {
		return agreementHeaderId;
	}

	public void setAgreementHeaderId(Long agreementHeaderId) {
		this.agreementHeaderId = agreementHeaderId;
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
		return "LtRentalAgreementApproval [agreementApprovalId=" + agreementApprovalId + ", moduleApprovalId="
				+ moduleApprovalId + ", approvalId=" + approvalId + ", approvalLevel=" + approvalLevel
				+ ", currentApprovalLevel=" + currentApprovalLevel + ", agreementHeaderId=" + agreementHeaderId
				+ ", status=" + status + ", delegationId=" + delegationId + ", approvedByAnyone=" + approvedByAnyone
				+ "]";
	}
	
	
	

}
