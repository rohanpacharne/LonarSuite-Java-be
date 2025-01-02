package com.lonar.asn.model;

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
@Table(name = "LT_MAST_ASN_ATTACHMENT")
@JsonInclude(Include.NON_NULL)
public class LtMastAsnAttachment {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asnAttachment_seq")
//	@SequenceGenerator(name = "asnAttachment_seq", sequenceName = "LT_MAST_ASN_ATTACHMENT_S", allocationSize = 1)
	@Column(name = "ASN_ATTACHMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long asnAttachmentId;

	@Column(name = "ASN_HEADER_ID")
	private Long asnHeaderId;

	@Column(name = "ASN_ATTACHMENT_TYPE")
	private String asnAttachmentType;

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

	public Long getAsnAttachmentId() {
		return asnAttachmentId;
	}

	public void setAsnAttachmentId(Long asnAttachmentId) {
		this.asnAttachmentId = asnAttachmentId;
	}

	public Long getAsnHeaderId() {
		return asnHeaderId;
	}

	public void setAsnHeaderId(Long asnHeaderId) {
		this.asnHeaderId = asnHeaderId;
	}

	public String getAsnAttachmentType() {
		return asnAttachmentType;
	}

	public void setAsnAttachmentType(String asnAttachmentType) {
		this.asnAttachmentType = asnAttachmentType;
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
	
}
