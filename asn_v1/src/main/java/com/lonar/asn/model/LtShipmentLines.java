package com.lonar.asn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="LT_SHIPMENT_LINES")
@JsonInclude(Include.NON_NULL)
public class LtShipmentLines implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipmentLine_seq")
	//@SequenceGenerator(name = "shipmentLine_seq", sequenceName = "LT_SHIPMENT_LINES_S", allocationSize = 1)
	@Column(name = "Shipment_Line_Id")
	private Long shipmentLineId;
	
	@Column(name = "Shipment_Header_Id")
	private Long shipmentHeaderId;
	
	@Column(name = "Line_Num")
	private Long lineNum;
			
	@Column(name = "PO_Shipment_Id")
	private Long poShipmentId;

	@Column(name = "PO_Header_id")
	private Long poHeaderId;
	
	@Column(name = "PO_Line_Id")
	private Long poLineId;
	
	@Column(name = "Line_Type")
	private String lineType ;
	
	@Column(name = "Category_Id")
	private Long categoryId;
	
	@Column(name = "Sub_Category_Id")
	private Long subCategoryId;
	
	@Column(name = "Product_Id")
	private Long productId;		
			
	@Column(name = "Description")
	private String description ;
	
	@Column(name = "UOM")
	private String uom ;
	
	@Column(name = "Quantity_Ordered")
	private Long quantityOrdered;
	
	@Column(name = "Quantity_Received")
	private Long quantityReceived;
	
	@Column(name = "Quantity_Shipped")
	private Long quantityShipped;
	
	@Column(name = "Contact_Person_id")
	private Long contactPersonId;
	
	@Column(name = "Contact_Number")
	private String contactNumber;
	
	@Column(name = "Shipment_Line_Status_Code")
	private String shipmentLineStatusCode ;
	
	@Column(name = "Attachment")
	private String attachment ;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
	
	@Column(name = "LAST_UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;
	
	@Column(name = "CONTACT_PERSON")
	private String  contactPerson;

	@Transient
	private String poNumber;
	
	@Transient
	private String shipmentNumber;
	
	@Transient
	private String categoryName;
	
	@Transient
	private String subCategory;
	
	@Transient
	private String productName;
	
	@Transient
	private String filePath;
	

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Transient
	private Long poLineNum;
	
	@Transient
	private String productDescription;
	
	@Transient
	private String productCode;
	
	@Transient
	private String empName;
	
	public Long getShipmentLineId() {
		return shipmentLineId;
	}

	public void setShipmentLineId(Long shipmentLineId) {
		this.shipmentLineId = shipmentLineId;
	}

	public Long getShipmentHeaderId() {
		return shipmentHeaderId;
	}

	public void setShipmentHeaderId(Long shipmentHeaderId) {
		this.shipmentHeaderId = shipmentHeaderId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getLineNum() {
		return lineNum;
	}

	public void setLineNum(Long lineNum) {
		this.lineNum = lineNum;
	}

	public Long getPoShipmentId() {
		return poShipmentId;
	}

	public void setPoShipmentId(Long poShipmentId) {
		this.poShipmentId = poShipmentId;
	}

	public Long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public Long getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Long poLineId) {
		this.poLineId = poLineId;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Long getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Long quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public Long getQuantityReceived() {
		return quantityReceived;
	}

	public void setQuantityReceived(Long quantityReceived) {
		this.quantityReceived = quantityReceived;
	}

	public Long getQuantityShipped() {
		return quantityShipped;
	}

	public void setQuantityShipped(Long quantityShipped) {
		this.quantityShipped = quantityShipped;
	}

	public Long getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(Long contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getShipmentLineStatusCode() {
		return shipmentLineStatusCode;
	}

	public void setShipmentLineStatusCode(String shipmentLineStatusCode) {
		this.shipmentLineStatusCode = shipmentLineStatusCode;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	

	public Long getPoLineNum() {
		return poLineNum;
	}

	public void setPoLineNum(Long poLineNum) {
		this.poLineNum = poLineNum;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "LtShipmentLines [shipmentLineId=" + shipmentLineId + ", shipmentHeaderId=" + shipmentHeaderId
				+ ", lineNum=" + lineNum + ", poShipmentId=" + poShipmentId + ", poHeaderId=" + poHeaderId
				+ ", poLineId=" + poLineId + ", lineType=" + lineType + ", categoryId=" + categoryId
				+ ", subCategoryId=" + subCategoryId + ", productId=" + productId + ", description=" + description
				+ ", uom=" + uom + ", quantityOrdered=" + quantityOrdered + ", quantityReceived=" + quantityReceived
				+ ", quantityShipped=" + quantityShipped + ", contactPersonId=" + contactPersonId + ", contactNumber="
				+ contactNumber + ", shipmentLineStatusCode=" + shipmentLineStatusCode + ", attachment=" + attachment
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", poNumber=" + poNumber
				+ ", shipmentNumber=" + shipmentNumber + ", categoryName=" + categoryName + ", subCategory="
				+ subCategory + ", productName=" + productName + "]";
	}
	
	
}