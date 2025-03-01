package com.lonar.vendor.vendorportal.model;
 
import javax.persistence.Transient;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
 
@JsonInclude(Include.NON_NULL)
public class PoApproval extends BaseClass{
	private Long poApprovalId;
	private Long moduleApprovalId;
	private Long approvalId;
	private String approvalLevel;
	private String currentApprovalLevel;
	private Long poHeaderId;
	private String status;
	private Long delegationId;
	private String approvedByAnyone;
	@Transient
	private String poNum;
	@Transient
	private String action;
	@Transient
	private String initiatorName;
 
	public Long getPoApprovalId() {
		return poApprovalId;
	}
 
	public void setPoApprovalId(Long poApprovalId) {
		this.poApprovalId = poApprovalId;
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
 
	public Long getPoHeaderId() {
		return poHeaderId;
	}
 
	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
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
 
	public String getPoNum() {
		return poNum;
	}
 
	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}
 
	public String getAction() {
		return action;
	}
 
	public void setAction(String action) {
		this.action = action;
	}
 
	public String getInitiatorName() {
		return initiatorName;
	}
 
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}
 
	@Override
	public String toString() {
		return "PoApproval [poApprovalId=" + poApprovalId + ", moduleApprovalId=" + moduleApprovalId + ", approvalId="
				+ approvalId + ", approvalLevel=" + approvalLevel + ", currentApprovalLevel=" + currentApprovalLevel
				+ ", poHeaderId=" + poHeaderId + ", status=" + status + ", delegationId=" + delegationId
				+ ", approvedByAnyone=" + approvedByAnyone + ", poNum=" + poNum + ", action=" + action
				+ ", initiatorName=" + initiatorName + "]";
	}
 
	
}