package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LT_MAST_AUDIT_RECORDS")
public class LtMastAuditRecords 
{

	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_rec_seq")
//	@SequenceGenerator(name = "audit_rec_seq", sequenceName = "LT_MAST_AUDIT_REC_S", allocationSize = 1)
	@Column(name = "AUDIT_RECORD_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long auditRecordId;
	
	@Column(name = "AUDIT_ID")
	private float auditId;
	
	@Basic(optional = false)
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Basic(optional = false)
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "VALUE_NAME")
	private String valueName;
	
	@Column(name = "OLD_VALUE")
	private String oldValue;
	
	@Column(name = "NEW_VALUE")
	private String newValue;
	
	@Column(name = "REASON")
	private String reason;
	

	public Long getAuditRecordId() {
		return auditRecordId;
	}

	public void setAuditRecordId(Long auditRecordId) {
		this.auditRecordId = auditRecordId;
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

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public float getAuditId() {
		return auditId;
	}

	public void setAuditId(float auditId) {
		this.auditId = auditId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "LtMastAuditRecords [auditRecordId=" + auditRecordId + ", auditId=" + auditId + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate + ", valueName=" + valueName + ", oldValue=" + oldValue
				+ ", newValue=" + newValue + ", reason=" + reason + "]";
	}

	

	
}
