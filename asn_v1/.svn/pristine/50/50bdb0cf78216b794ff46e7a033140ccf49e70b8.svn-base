package com.lonar.asn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@Entity
//@Table(name = "LT_PO_SHIPMENT")
@JsonInclude(Include.NON_NULL)
public class LtPoShipment {
	
	@Column(name = "PO_SHIPMENT_LINE_ID")
	private Long poShipmentLineId;
	
	@Column(name = "PO_LINE_ID")
	private Long poLineId;
	
	@Column(name = "PO_HEADER_ID")
	private Long poHeaderId;
	
	@Column(name = "SHIPMENT_NUM")
	private Long shipmentNum;
	
	@Column(name = "QUANTITY_ORDERED")
	private Long quantityOrdered;
	
	@Column(name = "QUANTITY_RECEIVED")
	private Long quantityReceived;
	
	@Column(name = "QUANTITY_SHIPPED")
	private Long quantityShipped;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
	
	@Column(name = "SHIPMENT_TYPE")
	private String shipmentType;
	
	@Column(name = "UOM")
	private String uom;
	
	@Column(name = "SHIPMENT_STATUS")
	private String shipmentStatus;
	
	@Column(name = "SHIP_TO_LOCATION")
	private String shipToLocation;
	
	@Column(name = "DUE_DATE")
	private Date dueDate;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	
	@Column(name = "BRANCH_ID")
	private Long branchId;
	
	//@Column(name = "ITEM_DESCRIPTION")
	//private String itemDescription; pl.PRODUCT_DESCRIPTION,
	@Transient 
	private String productDescription;
	
	@Transient 
	private Long lineNum;
	
	@Transient
	private Long vendorId;
	
	@Transient
	private Long vendorAddId;
	
	@Transient
	private Long draw;

	@Transient
	private Long start;

	@Transient
	private Long length;
	
	@Transient
	private int columnNo;
	
	@Transient
	private String sort;
	
	@Transient
	private String poNumber;
	

	public Long getPoShipmentLineId() {
		return poShipmentLineId;
	}

	public void setPoShipmentLineId(Long poShipmentLineId) {
		this.poShipmentLineId = poShipmentLineId;
	}

	public Long getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Long poLineId) {
		this.poLineId = poLineId;
	}

	public Long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public Long getShipmentNum() {
		return shipmentNum;
	}

	public void setShipmentNum(Long shipmentNum) {
		this.shipmentNum = shipmentNum;
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

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
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

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getShipToLocation() {
		return shipToLocation;
	}

	public void setShipToLocation(String shipToLocation) {
		this.shipToLocation = shipToLocation;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public int getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}


	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getVendorAddId() {
		return vendorAddId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setVendorAddId(Long vendorAddId) {
		this.vendorAddId = vendorAddId;
	}

	public Long getLineNum() {
		return lineNum;
	}

	public void setLineNum(Long lineNum) {
		this.lineNum = lineNum;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "LtPoShipment [poShipmentLineId=" + poShipmentLineId + ", poLineId=" + poLineId + ", poHeaderId="
				+ poHeaderId + ", shipmentNum=" + shipmentNum + ", quantityOrdered=" + quantityOrdered
				+ ", quantityReceived=" + quantityReceived + ", quantityShipped=" + quantityShipped + ", createdBy="
				+ createdBy + ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", shipmentType=" + shipmentType + ", uom=" + uom + ", shipmentStatus=" + shipmentStatus
				+ ", shipToLocation=" + shipToLocation + ", dueDate=" + dueDate + ", creationDate=" + creationDate
				+ ", lastUpdateDate=" + lastUpdateDate + ", branchId=" + branchId + ", productDescription="
				+ productDescription + ", lineNum=" + lineNum + ", vendorId=" + vendorId + ", vendorAddId="
				+ vendorAddId + ", draw=" + draw + ", start=" + start + ", length=" + length + ", columnNo=" + columnNo
				+ ", sort=" + sort + ", poNumber=" + poNumber + "]";
	}
	
	
}