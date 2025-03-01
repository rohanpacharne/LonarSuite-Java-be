package com.lonar.vendor.vendorportal.model;

import java.util.Date;


import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LtPoApprovalHistory {

	private Long poApprovalHistoryId;
	private Long poApprovalId;
	private String status;
	private String note;
	private Date lastUpdateDate;
	
	private Long poHeaderId;
	private String remark;
	private Long employeeId;
	private String userType;
	private Long vendorId;
	private Long lastUpdateLogin;
	
	@Transient
	private String approvalName;

	public Long getPoApprovalHistoryId() {
		return poApprovalHistoryId;
	}

	public void setPoApprovalHistoryId(Long poApprovalHistoryId) {
		this.poApprovalHistoryId = poApprovalHistoryId;
	}

	public Long getPoApprovalId() {
		return poApprovalId;
	}

	public void setPoApprovalId(Long poApprovalId) {
		this.poApprovalId = poApprovalId;
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

	public Long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
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

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Long lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	@Override
	public String toString() {
		return "LtPoApprovalHistory [poApprovalHistoryId=" + poApprovalHistoryId + ", poApprovalId=" + poApprovalId
				+ ", status=" + status + ", note=" + note + ", lastUpdateDate=" + lastUpdateDate + ", poHeaderId="
				+ poHeaderId + ", remark=" + remark + ", employeeId=" + employeeId + ", userType=" + userType
				+ ", vendorId=" + vendorId + ", lastUpdateLogin=" + lastUpdateLogin + ", approvalName=" + approvalName
				+ "]";
	}
	
	
}
