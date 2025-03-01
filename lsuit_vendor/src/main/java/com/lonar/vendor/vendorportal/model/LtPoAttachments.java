package com.lonar.vendor.vendorportal.model;
 
import java.util.Date;
 
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
@Table(name = "LT_PO_ATTACHMENTS")
@JsonInclude(Include.NON_NULL)
public class LtPoAttachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PO_ATTACHMENT_ID")
    private Long poAttachmentId;
    @Column(name = "PO_HEADER_ID")
    private Long poHeaderId;
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
    @Column(name = "THUMBNAIL_FILE_PATH")
    private String thumbnailFilePath;
    @Column(name = "FILE_TEXT")
    private String fileText;
    @Column(name = "ATTACHMENT_TYPE_ID")
	private Long attachmentTypeId;
    
    @Transient
    private String attachmentType;
 
 
	public LtPoAttachments() {
		super();
	}
 
	public LtPoAttachments(Long poAttachmentId, Long poHeaderId, String fileName, String filePath,
			String attachmentType, Long createdBy, Date creationDate, Long lastUpdateLogin, Long lastUpdatedBy,
			Date lastUpdateDate, String thumbnailFilePath, String fileText) {
		super();
		this.poAttachmentId = poAttachmentId;
		this.poHeaderId = poHeaderId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.attachmentType = attachmentType;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdateLogin = lastUpdateLogin;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.thumbnailFilePath = thumbnailFilePath;
		this.fileText = fileText;
	}
 
	public Long getAttachmentTypeId() {
		return attachmentTypeId;
	}

	public void setAttachmentTypeId(Long attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}

	public Long getPoAttachmentId() {
		return poAttachmentId;
	}
 
	public void setPoAttachmentId(Long poAttachmentId) {
		this.poAttachmentId = poAttachmentId;
	}
 
	public Long getPoHeaderId() {
		return poHeaderId;
	}
 
	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
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
 
	public String getAttachmentType() {
		return attachmentType;
	}
 
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
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
 
	public String getThumbnailFilePath() {
		return thumbnailFilePath;
	}
 
	public void setThumbnailFilePath(String thumbnailFilePath) {
		this.thumbnailFilePath = thumbnailFilePath;
	}
 
	public String getFileText() {
		return fileText;
	}
 
	public void setFileText(String fileText) {
		this.fileText = fileText;
	}

	@Override
	public String toString() {
		return "LtPoAttachments [poAttachmentId=" + poAttachmentId + ", poHeaderId=" + poHeaderId + ", fileName="
				+ fileName + ", filePath=" + filePath + ", attachmentType=" + attachmentType + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", thumbnailFilePath="
				+ thumbnailFilePath + ", fileText=" + fileText + ", attachmentTypeId=" + attachmentTypeId + "]";
	}
 
	

}