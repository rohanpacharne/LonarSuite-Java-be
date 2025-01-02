package com.lonar.vendor.vendorportal.model;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
 
@Entity
@Table(name = "LT_EXP_EXPENSE_ATTACHMENTS")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class LtExpenseAttachments implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Id
	@Basic(optional=false)
	@JsonView(DataTablesOutput.View.class)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fileUpload_seq")
//	@SequenceGenerator(name = "fileUpload_seq", sequenceName = "lt_mast_exp_file_upload_s", allocationSize = 1)
	@Column(name = "EXPENSE_ATTACHMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseAttachmentId;
//	private Long expenseFileUploadId;
 
	// @Basic(optional = false)
	// @JsonView(DataTablesOutput.View.class)
/*	@Column(name = "user_id")
	private Long userId;*/
 
	// @JsonView(DataTablesOutput.View.class)
	@Column(name = "EXPENCE_HEADER_ID")
	private Long expenceHeaderId;
 
	// @JsonView(DataTablesOutput.View.class)
	@Column(name = "EXPENCE_LINE_ID")
	private Long expenceLineId;
 
	// @JsonView(DataTablesOutput.View.class)
	@Column(name = "FILE_NAME")
	private String fileName;
 
	// @JsonView(DataTablesOutput.View.class)
	@Column(name = "FILE_PATH")
	private String filePath;
 
	@Column(name = "ATTACHMENT_TYPE")
	private String attachmentType;
 
	@Basic(optional = false)
//	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CREATED_BY")
	private Long createdBy;
 
	@Basic(optional = false)
//	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
 
	@Basic(optional = false)
//	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
 
	
	@Column(name = "THUMBNAIL_FILE_PATH")
	private String thumbnailFilePath;
	
	@Column(name = "FILE_TEXT")
	private String fileText;
 
 
	public String getFileText() {
		return fileText;
	}


	public void setFileText(String fileText) {
		this.fileText = fileText;
	}


	public LtExpenseAttachments() {
		super();
	}


//	public Long getExpenseFileUploadId() {
//		return expenseFileUploadId;
//	}
// 
// 
//	public void setExpenseFileUploadId(Long expenseFileUploadId) {
//		this.expenseFileUploadId = expenseFileUploadId;
//	}
	
	
 
 
	
 
 
	public Long getExpenceHeaderId() {
		return expenceHeaderId;
	}
 
 
	public Long getExpenseAttachmentId() {
		return expenseAttachmentId;
	}


	public void setExpenseAttachmentId(Long expenseAttachmentId) {
		this.expenseAttachmentId = expenseAttachmentId;
	}


	public void setExpenceHeaderId(Long expenceHeaderId) {
		this.expenceHeaderId = expenceHeaderId;
	}
 
 
	public Long getExpenceLineId() {
		return expenceLineId;
	}
 
 
	public void setExpenceLineId(Long expenceLineId) {
		this.expenceLineId = expenceLineId;
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


	@Override
	public String toString() {
		return "LtExpenseAttachments [expenseAttachmentId=" + expenseAttachmentId + ", expenceHeaderId="
				+ expenceHeaderId + ", expenceLineId=" + expenceLineId + ", fileName=" + fileName + ", filePath="
				+ filePath + ", attachmentType=" + attachmentType + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", thumbnailFilePath=" + thumbnailFilePath + ", fileText="
				+ fileText + "]";
	}


	
 
 
	
 
 
	
}
