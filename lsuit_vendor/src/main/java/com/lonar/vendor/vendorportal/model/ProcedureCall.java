package com.lonar.vendor.vendorportal.model;

import java.util.Date;

public class ProcedureCall {

	private String statusCode; 
	private String statusMessage; 
	private Long companyId;
	private String invoiceNum;
	private Long vendorId;
	private Long vendorAddId;
	private Date invoiceDate;
	public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Long getVendorAddId() {
		return vendorAddId;
	}
	public void setVendorAddId(Long vendorAddId) {
		this.vendorAddId = vendorAddId;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	

	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

}
