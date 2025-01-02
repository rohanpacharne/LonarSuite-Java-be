package com.lonar.asn.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name="LT_SHIPMENT_ATTACHMENT")
@JsonInclude(Include.NON_NULL)
public class LtShipmentAttachment 
{

	@Id
	@Basic(optional = false)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipmentHeaders_seq")
	//@SequenceGenerator(name = "shipmentHeaders_seq", sequenceName = "LT_SHIPMENT_HEADERS_S", allocationSize = 1)
	@Column(name = "SHIPMENT_ATTACHMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shipmentAttachmentId;
	
	@Column(name = "SHIPMENT_HEADER_ID")
	private Long shipmentHeaderId;
	
	@Column(name = "ATTACHMENT_TYPE_ID")
	private String attachmentTypeId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
	
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Transient
	private String attachmentName;
	
	public Long getShipmentAttachmentId() {
		return shipmentAttachmentId;
	}

	public void setShipmentAttachmentId(Long shipmentAttachmentId) {
		this.shipmentAttachmentId = shipmentAttachmentId;
	}

	public Long getShipmentHeaderId() {
		return shipmentHeaderId;
	}

	public void setShipmentHeaderId(Long shipmentHeaderId) {
		this.shipmentHeaderId = shipmentHeaderId;
	}

	public String getAttachmentTypeId() {
		return attachmentTypeId;
	}

	public void setAttachmentTypeId(String attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
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

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	@Override
	public String toString() {
		return "LtShipmentAttachment [shipmentAttachmentId=" + shipmentAttachmentId + ", shipmentHeaderId="
				+ shipmentHeaderId + ", attachmentTypeId=" + attachmentTypeId + ", fileName=" + fileName + ", filePath="
				+ filePath + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", attachmentName="
				+ attachmentName + "]";
	}

	
	
}
