package com.lonar.vendor.vendorportal.model;

import java.util.Date;

public class LtPrApprovalHistory {
	
	private Long prApprovalHistoryId;
    private Long prApprovalId;
    private String status;
    private String note;
    private Date lastUpdateDate;
    private Long agreementHeaderId;
    private String remark;
    private Long employeeId;
    private Long lastUpdateLogin;
    private String approvalName;
    private Long prHeaderId;
    
    
    
	public Long getPrHeaderId() {
		return prHeaderId;
	}
	public void setPrHeaderId(Long prHeaderId) {
		this.prHeaderId = prHeaderId;
	}
	public Long getPrApprovalHistoryId() {
		return prApprovalHistoryId;
	}
	public void setPrApprovalHistoryId(Long prApprovalHistoryId) {
		this.prApprovalHistoryId = prApprovalHistoryId;
	}
	public Long getPrApprovalId() {
		return prApprovalId;
	}
	public void setPrApprovalId(Long prApprovalId) {
		this.prApprovalId = prApprovalId;
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
	public Long getAgreementHeaderId() {
		return agreementHeaderId;
	}
	public void setAgreementHeaderId(Long agreementHeaderId) {
		this.agreementHeaderId = agreementHeaderId;
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
		return "LtPrApprovalHistory [prApprovalHistoryId=" + prApprovalHistoryId + ", prApprovalId=" + prApprovalId
				+ ", status=" + status + ", note=" + note + ", lastUpdateDate=" + lastUpdateDate
				+ ", agreementHeaderId=" + agreementHeaderId + ", remark=" + remark + ", employeeId=" + employeeId
				+ ", lastUpdateLogin=" + lastUpdateLogin + ", approvalName=" + approvalName + ", prHeaderId="
				+ prHeaderId + "]";
	}
	

}
