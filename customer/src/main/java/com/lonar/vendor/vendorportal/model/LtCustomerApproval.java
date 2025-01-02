package com.lonar.vendor.vendorportal.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "LT_CUSTOMER_APPROVAL")
@JsonInclude(Include.NON_NULL)
public class LtCustomerApproval extends BaseClass{
	
	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerApproval_seq")
//	@SequenceGenerator(name = "customerApproval_seq", sequenceName = "LT_CUSTOMER_APPROVAL_S", allocationSize = 1)
	@Column(name = "CUSTOMER_APPROVAL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerApprovalId;
	
	@Column(name = "MODULE_APPROVAL_ID")
	private Long moduleApprovalId;
	
	@Column(name = "APPROVAL_ID")
	private Long approvalId;
	
	@Column(name = "APPROVAL_LEVEL")
	private String approvalLevel;
	
	@Column(name = "CURRENT_APPROVAL_LEVEL")
	private String currentApprovalLevel;
	
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DELEGATION_ID")
	private Long delegationId;
	
	@Column(name = "MODULE_APP_EMPLOYEES_ID")
	private Long moduleAppEmployeesId;
	
	@Transient
	private String approvalName;
	
	@Transient
	private String approvalLevelName;
	
	@Transient
	private String initiatorName;

	@Transient
	private Long employeesId;

	@Transient
	private String statusValue;
	
	@Transient
	private String action;
	
	@Transient
	private String approvedByAnyone; 
	
	@Transient
	private String customerName;
	
	public Long getCustomerApprovalId() {
		return customerApprovalId;
	}

	public void setCustomerApprovalId(Long customerApprovalId) {
		this.customerApprovalId = customerApprovalId;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public Long getModuleAppEmployeesId() {
		return moduleAppEmployeesId;
	}

	public void setModuleAppEmployeesId(Long moduleAppEmployeesId) {
		this.moduleAppEmployeesId = moduleAppEmployeesId;
	}

	public String getApprovalName() {
		return approvalName;
	}

	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}

	public String getApprovalLevelName() {
		return approvalLevelName;
	}

	public void setApprovalLevelName(String approvalLevelName) {
		this.approvalLevelName = approvalLevelName;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public Long getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(Long employeesId) {
		this.employeesId = employeesId;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getApprovedByAnyone() {
		return approvedByAnyone;
	}

	public void setApprovedByAnyone(String approvedByAnyone) {
		this.approvedByAnyone = approvedByAnyone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "LtCustomerApproval [customerApprovalId=" + customerApprovalId + ", moduleApprovalId=" + moduleApprovalId
				+ ", approvalId=" + approvalId + ", approvalLevel=" + approvalLevel + ", currentApprovalLevel="
				+ currentApprovalLevel + ", customerId=" + customerId + ", status=" + status + ", delegationId="
				+ delegationId + ", moduleAppEmployeesId=" + moduleAppEmployeesId + ", approvalName=" + approvalName
				+ ", approvalLevelName=" + approvalLevelName + ", initiatorName=" + initiatorName + ", employeesId="
				+ employeesId + ", statusValue=" + statusValue + ", action=" + action + ", approvedByAnyone="
				+ approvedByAnyone + ", customerName=" + customerName + "]";
	}

	

	
	
}
