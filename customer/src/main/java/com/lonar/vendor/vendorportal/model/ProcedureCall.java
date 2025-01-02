package com.lonar.vendor.vendorportal.model;

public class ProcedureCall {

	private String statusCode; 
	private String statusMessage; 
	private Long customerId;
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
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "ProcedureCall [statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", customerId="
				+ customerId + "]";
	}
	
	
}
