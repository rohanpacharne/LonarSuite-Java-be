package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RentalAgreementApproval {
	
	
	private Long agreementApprovalId;
	private Long moduleApprovalId;
	private Long approvalId;
	private String approvalLevel;
	private String currentApprovalLevel;
	private Long agreementHeaderId;
	private String status;
	private Long delegationId;
	private Date startDate;
	private Date endDate;
	private Long createdBy;
	private Date creationDate;
	private Date lastUpdateDate;
	private String lstDate;
	private Long lastUpdateLogin;
	private Long lastUpdatedBy;
	private Long employeeId;
	private String title;
	private String firstName;
	private String lastName;
	private String agreementNumber;
	private Long divisionId;
	private String initiatorName;
	
	@Transient
	private Long draw;
	@Transient
	private Long start;
	@Transient
	private Long length;
	@Transient
	private int columnNo;
	@Transient
	private String sort;
	
	@Transient
	private String action;
	
	public RentalAgreementApproval(Long agreementApprovalId, Long moduleApprovalId, Long approvalId,
			String approvalLevel, String currentApprovalLevel, Long agreementHeaderId, String status, Long delegationId,
			Date startDate, Date endDate, Long createdBy, Date creationDate, Date lastUpdateDate, String lstDate,
			Long lastUpdateLogin, Long lastUpdatedBy, Long employeeId, String title, String firstName, String lastName,
			String agreementNumber, Long divisionId) {
		super();
		this.agreementApprovalId = agreementApprovalId;
		this.moduleApprovalId = moduleApprovalId;
		this.approvalId = approvalId;
		this.approvalLevel = approvalLevel;
		this.currentApprovalLevel = currentApprovalLevel;
		this.agreementHeaderId = agreementHeaderId;
		this.status = status;
		this.delegationId = delegationId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdateDate = lastUpdateDate;
		this.lstDate = lstDate;
		this.lastUpdateLogin = lastUpdateLogin;
		this.lastUpdatedBy = lastUpdatedBy;
		this.employeeId = employeeId;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.agreementNumber = agreementNumber;
		this.divisionId = divisionId;
	}

	public RentalAgreementApproval() {
		super();
	}
	
	

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public int getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getAgreementApprovalId() {
		return agreementApprovalId;
	}

	public void setAgreementApprovalId(Long agreementApprovalId) {
		this.agreementApprovalId = agreementApprovalId;
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

	public Long getAgreementHeaderId() {
		return agreementHeaderId;
	}

	public void setAgreementHeaderId(Long agreementHeaderId) {
		this.agreementHeaderId = agreementHeaderId;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLstDate() {
		return lstDate;
	}

	public void setLstDate(String lstDate) {
		this.lstDate = lstDate;
	}

	public Long getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Long lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	@Override
	public String toString() {
		return "RentalAgreementApproval [agreementApprovalId=" + agreementApprovalId + ", moduleApprovalId="
				+ moduleApprovalId + ", approvalId=" + approvalId + ", approvalLevel=" + approvalLevel
				+ ", currentApprovalLevel=" + currentApprovalLevel + ", agreementHeaderId=" + agreementHeaderId
				+ ", status=" + status + ", delegationId=" + delegationId + ", startDate=" + startDate + ", endDate="
				+ endDate + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateDate="
				+ lastUpdateDate + ", lstDate=" + lstDate + ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy="
				+ lastUpdatedBy + ", employeeId=" + employeeId + ", title=" + title + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", agreementNumber=" + agreementNumber + ", divisionId=" + divisionId
				+ "]";
	}
	
	

}
