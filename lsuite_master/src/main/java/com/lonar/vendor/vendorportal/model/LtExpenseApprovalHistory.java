package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Transient;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
 
@JsonInclude(Include.NON_NULL)
public class LtExpenseApprovalHistory {
 
	private Long expenseApprovalHistoryId;
	private Long expenseApprovalId;
	private String status;
	private String note;
	private Date lastUpdateDate;
	private Long expenseHeaderId;
	private String remark;
	private Long employeeId;

	@Transient
	private String approvalName;
 
	
	public String getApprovalName() {
		return approvalName;
	}
 
	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}
 
	public Long getExpenseApprovalHistoryId() {
		return expenseApprovalHistoryId;
	}
 
	public void setExpenseApprovalHistoryId(Long expenseApprovalHistoryId) {
		this.expenseApprovalHistoryId = expenseApprovalHistoryId;
	}
 
	public Long getExpenseApprovalId() {
		return expenseApprovalId;
	}
 
	public void setExpenseApprovalId(Long expenseApprovalId) {
		this.expenseApprovalId = expenseApprovalId;
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
 
	public Long getExpenseHeaderId() {
		return expenseHeaderId;
	}
 
	public void setExpenseHeaderId(Long expenseHeaderId) {
		this.expenseHeaderId = expenseHeaderId;
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
 
	@Override
	public String toString() {
		return "LtExpenseApprovalHistory [expenseApprovalHistoryId=" + expenseApprovalHistoryId + ", expenseApprovalId="
				+ expenseApprovalId + ", status=" + status + ", note=" + note + ", lastUpdateDate=" + lastUpdateDate
				+ ", expenseHeaderId=" + expenseHeaderId + ", remark=" + remark + ", employeeId=" + employeeId
				+ ", approvalName=" + approvalName + "]";
	}
 
	
}
