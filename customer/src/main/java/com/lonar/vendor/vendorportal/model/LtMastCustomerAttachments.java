package com.lonar.vendor.vendorportal.model;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "LT_MAST_CUSTOMER_ATTACHMENTS")
@JsonInclude(Include.NON_NULL)
public class LtMastCustomerAttachments extends BaseClass{

	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_attachments_seq")
//	@SequenceGenerator(name = "customer_attachments_seq", sequenceName = "customer_attachments_seq_s", allocationSize = 1)
	@Column(name = "CUST_ATTACHMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long custAttachmentId;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "CUST_ATTACHMENT_TYPE")
	private String custAttachmentType;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_PATH")
	private String filePath;

	public void setCustAttachmentId(Long custAttachmentId) {
		this.custAttachmentId = custAttachmentId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustAttachmentType() {
		return custAttachmentType;
	}

	public void setCustAttachmentType(String custAttachmentType) {
		this.custAttachmentType = custAttachmentType;
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

	@Override
	public String toString() {
		return "LtMastCustomerAttachments [custAttachmentId=" + custAttachmentId + ", customerId=" + customerId
				+ ", custAttachmentType=" + custAttachmentType + ", fileName=" + fileName + ", filePath=" + filePath
				+ "]";
	}

}
