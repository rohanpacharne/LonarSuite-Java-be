package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LtCustomerApprovalHistory {

	private Long customerApprovalHistoryId;
	private Long customerApprovalId;
	private String status;
	private String note;
	private Date lastUpdateDate;
	
	private Long customerId;
	private String remark;
	private Long employeeId;
	private String userType;
	
	@Transient
	private String approvalName;

	public Long getCustomerApprovalHistoryId() {
		return customerApprovalHistoryId;
	}

	public void setCustomerApprovalHistoryId(Long customerApprovalHistoryId) {
		this.customerApprovalHistoryId = customerApprovalHistoryId;
	}

	public Long getCustomerApprovalId() {
		return customerApprovalId;
	}

	public void setCustomerApprovalId(Long customerApprovalId) {
		this.customerApprovalId = customerApprovalId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	@Override
	public String toString() {
		return "LtCustomerApprovalHistory [customerApprovalHistoryId=" + customerApprovalHistoryId
				+ ", customerApprovalId=" + customerApprovalId + ", status=" + status + ", note=" + note
				+ ", lastUpdateDate=" + lastUpdateDate + ", vendorId=" + customerId + ", remark=" + remark
				+ ", employeeId=" + employeeId + ", userType=" + userType + ", approvalName=" + approvalName + "]";
	}

	
}
