package com.lonar.vendor.vendorportal.model;

public class BulkIdWithName {
	private String name;
	private Long vendorId;
	private Long vendorApprovalId;
	private String approvalLevel;
	private String headerStatus;
	private Long invoiceHeaderId;
	private Long invoiceApprovalId;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public Long getVendorApprovalId() {
		return vendorApprovalId;
	}
	public void setVendorApprovalId(Long vendorApprovalId) {
		this.vendorApprovalId = vendorApprovalId;
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
	
	public Long getInvoiceHeaderId() {
		return invoiceHeaderId;
	}
	public void setInvoiceHeaderId(Long invoiceHeaderId) {
		this.invoiceHeaderId = invoiceHeaderId;
	}
	public Long getInvoiceApprovalId() {
		return invoiceApprovalId;
	}
	public void setInvoiceApprovalId(Long invoiceApprovalId) {
		this.invoiceApprovalId = invoiceApprovalId;
	}
	@Override
	public String toString() {
		return "BulkIdWithName [name=" + name + ", vendorId=" + vendorId + ", vendorApprovalId=" + vendorApprovalId
				+ ", approvalLevel=" + approvalLevel + ", headerStatus=" + headerStatus + ", invoiceId=" + invoiceHeaderId
				+ ", invoiceApprovalId=" + invoiceApprovalId + "]";
	}
	
	
	
}
