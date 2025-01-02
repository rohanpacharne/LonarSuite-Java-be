package com.lonar.vendor.vendorportal.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "LT_MAST_CUSTOMER_ATTACHMENT")
@JsonInclude(Include.NON_NULL)
public class LtMastCustomerAttachment implements Serializable {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_Attachment_seq")
//	@SequenceGenerator(name = "customer_Attachment_seq", sequenceName = "LT_MAST_CUSTOMER_ATTACHMENT_S", allocationSize = 1)
	@Column(name = "CUSTOMER_ATTACHMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerAttachmentId;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "CUST_ATTACHMENT_TYPE")
	private Long customerAttachmentType;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
	
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	
	@Transient
	private String attachmentType;

	public Long getCustomerAttachmentId() {
		return customerAttachmentId;
	}

	public void setCustomerAttachmentId(Long customerAttachmentId) {
		this.customerAttachmentId = customerAttachmentId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerAttachmentType() {
		return customerAttachmentType;
	}

	public void setCustomerAttachmentType(Long customerAttachmentType) {
		this.customerAttachmentType = customerAttachmentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	@Override
	public String toString() {
		return "LtInvoiceAttachment [customerAttachmentId=" + customerAttachmentId + ", customerId=" + customerId
				+ ", customerAttachmentType=" + customerAttachmentType + ", fileName=" + fileName + ", filePath="
				+ filePath + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin="
				+ lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", attachmentType=" + attachmentType + "]";
	}

	
	
}

