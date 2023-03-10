package com.lonar.asn.model;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AsnApproval extends BaseClass
{
	
	private Long asnApprovalId;
	private Long moduleApprovalId;
	private Long approvalId;
	private String approvalLevel;
	private String currentApprovalLevel;
	private Long shipmentHeaderId;
	private String status;
	private Long delegationId;
	private String approvedByAnyone;
	
	
	@Transient
	private String action;
	
	@Transient
	private String vendorName;
	
	@Transient
	private String shipmentNum;
	
	@Transient
	private String buyerName;

	public Long getAsnApprovalId() {
		return asnApprovalId;
	}

	public void setAsnApprovalId(Long asnApprovalId) {
		this.asnApprovalId = asnApprovalId;
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

	public Long getShipmentHeaderId() {
		return shipmentHeaderId;
	}

	public void setShipmentHeaderId(Long shipmentHeaderId) {
		this.shipmentHeaderId = shipmentHeaderId;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getShipmentNum() {
		return shipmentNum;
	}

	public void setShipmentNum(String shipmentNum) {
		this.shipmentNum = shipmentNum;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	
	@Override
	public String toString() {
		return "AsnApproval [asnApprovalId=" + asnApprovalId + ", moduleApprovalId=" + moduleApprovalId
				+ ", approvalId=" + approvalId + ", approvalLevel=" + approvalLevel + ", currentApprovalLevel="
				+ currentApprovalLevel + ", shipmentHeaderId=" + shipmentHeaderId + ", status=" + status
				+ ", delegationId=" + delegationId + ", approvedByAnyone=" + approvedByAnyone + ", lastupdatelogin="
				+ ", action=" + action + ", vendorName=" + vendorName + ", shipmentNum=" + shipmentNum
				+ ", buyerName=" + buyerName + "]";
	}

	
	
}
