package com.lonar.asn.model;

public class ProcedureCall {

	private String statusCode; 
	private String statusMessage; 
	private Long shipmentHeaderId;
	
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
	public Long getShipmentHeaderId() {
		return shipmentHeaderId;
	}
	public void setShipmentHeaderId(Long shipmentHeaderId) {
		this.shipmentHeaderId = shipmentHeaderId;
	}
	@Override
	public String toString() {
		return "ProcedureCall [statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", shipmentHeaderId="
				+ shipmentHeaderId + "]";
	}  
	
	
	//(x_status OUT VARCHAR2,x_message OUT VARCHAR2,x_shipment_header_id OUT NUMBER,p_shipment_source_id IN NUMBER)
	
}
