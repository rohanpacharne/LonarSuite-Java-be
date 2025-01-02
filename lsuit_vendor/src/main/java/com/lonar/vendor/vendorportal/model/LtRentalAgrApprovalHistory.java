package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LtRentalAgrApprovalHistory {
	
	 	
	    private Long agreementApprovalHistoryId;
	    private Long agreementApprovalId;
	    private String status;
	    private String note;
	    private Date lastUpdateDate;
	    private Long agreementHeaderId;
	    private String remark;
	    private Long employeeId;
	    private Long lastUpdateLogin;
	    private String approvalName;
	    

		public LtRentalAgrApprovalHistory() {
			super();
		}

		public LtRentalAgrApprovalHistory(Long agreementApprovalHistoryId, Long agreementApprovalId, String status,
				String note, Date lastUpdateDate, Long agreementHeaderId, String remark, Long employeeId,
				Long lastUpdateLogin, String approvalName) {
			super();
			this.agreementApprovalHistoryId = agreementApprovalHistoryId;
			this.agreementApprovalId = agreementApprovalId;
			this.status = status;
			this.note = note;
			this.lastUpdateDate = lastUpdateDate;
			this.agreementHeaderId = agreementHeaderId;
			this.remark = remark;
			this.employeeId = employeeId;
			this.lastUpdateLogin = lastUpdateLogin;
			this.approvalName = approvalName;
		}



		public String getApprovalName() {
			return approvalName;
		}

		public void setApprovalName(String approvalName) {
			this.approvalName = approvalName;
		}

		public Long getAgreementApprovalHistoryId() {
			return agreementApprovalHistoryId;
		}

		public void setAgreementApprovalHistoryId(Long agreementApprovalHistoryId) {
			this.agreementApprovalHistoryId = agreementApprovalHistoryId;
		}

		public Long getAgreementApprovalId() {
			return agreementApprovalId;
		}

		public void setAgreementApprovalId(Long agreementApprovalId) {
			this.agreementApprovalId = agreementApprovalId;
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

		@Override
		public String toString() {
			return "LtRentalAgrApprovalHistory [agreementApprovalHistoryId=" + agreementApprovalHistoryId
					+ ", agreementApprovalId=" + agreementApprovalId + ", status=" + status + ", note=" + note
					+ ", lastUpdateDate=" + lastUpdateDate + ", agreementHeaderId=" + agreementHeaderId + ", remark="
					+ remark + ", employeeId=" + employeeId + ", lastUpdateLogin=" + lastUpdateLogin + ", approvalName="
					+ approvalName + "]";
		}

		

		


		
	    
	    

}
