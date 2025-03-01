package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Transient;

public class PrApproval {
	
	private long prApprovalId;
    private Long moduleApprovalId;
    private Long approvalId;
    private String approvalLevel;
    private String currentApprovalLevel;
    private Long prHeaderId;
    private String status;
    private Long delegationId;
    private Date startDate;
    private Date endDate;
    private Long createdBy;
    private Date creationDate;
    private Date lastUpdateDate;
    private Long lastUpdateLogin;
    private Long lastUpdatedBy;
    private Long moduleAppEmployeesId;
    private String approvedByAnyone;
    private String prNumber;
    private String requesterName;
    
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
	
	
    
	public String getPrNumber() {
		return prNumber;
	}
	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
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
	public String getApprovedByAnyone() {
		return approvedByAnyone;
	}
	public void setApprovedByAnyone(String approvedByAnyone) {
		this.approvedByAnyone = approvedByAnyone;
	}
	public long getPrApprovalId() {
		return prApprovalId;
	}
	public void setPrApprovalId(long prApprovalId) {
		this.prApprovalId = prApprovalId;
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
	public Long getPrHeaderId() {
		return prHeaderId;
	}
	public void setPrHeaderId(Long prHeaderId) {
		this.prHeaderId = prHeaderId;
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
	public Long getModuleAppEmployeesId() {
		return moduleAppEmployeesId;
	}
	public void setModuleAppEmployeesId(Long moduleAppEmployeesId) {
		this.moduleAppEmployeesId = moduleAppEmployeesId;
	}
	@Override
	public String toString() {
		return "PrApproval [prApprovalId=" + prApprovalId + ", moduleApprovalId=" + moduleApprovalId + ", approvalId="
				+ approvalId + ", approvalLevel=" + approvalLevel + ", currentApprovalLevel=" + currentApprovalLevel
				+ ", prHeaderId=" + prHeaderId + ", status=" + status + ", delegationId=" + delegationId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", moduleAppEmployeesId=" + moduleAppEmployeesId
				+ ", approvedByAnyone=" + approvedByAnyone + ", prNumber=" + prNumber + ", requesterName="
				+ requesterName + ", draw=" + draw + ", start=" + start + ", length=" + length + ", columnNo="
				+ columnNo + ", sort=" + sort + ", action=" + action + "]";
	}

	
	
	
	
	
    
    


}
