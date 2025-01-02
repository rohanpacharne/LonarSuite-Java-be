package com.lonar.vendor.vendorportal.model;

public class BulkIdWithName {
	private String name;
	private Long expenseHeaderId;
	private Long expenseApprovalId;
	private String approvalLevel;
	private String headerStatus;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getExpenseHeaderId() {
		return expenseHeaderId;
	}
	public void setExpenseHeaderId(Long expenseHeaderId) {
		this.expenseHeaderId = expenseHeaderId;
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
	public Long getExpenseApprovalId() {
		return expenseApprovalId;
	}
	public void setExpenseApprovalId(Long expenseApprovalId) {
		this.expenseApprovalId = expenseApprovalId;
	}
	@Override
	public String toString() {
		return "BulkIdWithName [name=" + name + ", expenseHeaderId=" + expenseHeaderId + ", expenseApprovalId="
				+ expenseApprovalId + ", approvalLevel=" + approvalLevel + ", headerStatus=" + headerStatus + "]";
	}
	
	
	
}

