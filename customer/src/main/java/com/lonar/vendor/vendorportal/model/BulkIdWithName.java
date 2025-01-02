package com.lonar.vendor.vendorportal.model;

public class BulkIdWithName {
	private String name;
	private Long customerId;
	private Long customerApprovalId;
	private String approvalLevel;
	private String headerStatus;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getCustomerApprovalId() {
		return customerApprovalId;
	}
	public void setCustomerApprovalId(Long customerApprovalId) {
		this.customerApprovalId = customerApprovalId;
	}
	public String getApprovalLevel() {
		return approvalLevel;
	}
	public void setApprovalLevel(String approvalLevel) {
		this.approvalLevel = approvalLevel;
	}
	public String getHeaderStatus() {
		return headerStatus;
	}
	public void setHeaderStatus(String headerStatus) {
		this.headerStatus = headerStatus;
	}
	@Override
	public String toString() {
		return "BulkIdWithName [name=" + name + ", customerId=" + customerId + ", customerApprovalId="
				+ customerApprovalId + ", approvalLevel=" + approvalLevel + ", headerStatus=" + headerStatus + "]";
	}
	
	
	
}
