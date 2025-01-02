package com.lonar.asn.model;

import java.io.Serializable;
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
@Table(name="LT_SHIPMENT_HEADERS")
@JsonInclude(Include.NON_NULL)
public class LtShipmentHeaders implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipmentHeaders_seq")
	//@SequenceGenerator(name = "shipmentHeaders_seq", sequenceName = "LT_SHIPMENT_HEADERS_S", allocationSize = 1)
	@Column(name = "Shipment_Header_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shipmentHeaderId;
	
	@Column(name = "Receipt_Source_Code")
	private String receiptSourceCode;
	
	@Column(name = "ASN_Type")
	private String asnType;
	
	@Column(name = "Vendor_id")
	private Long vendorId;
	
	@Column(name = "Vendor_Add_id")
	private Long vendorAddId;
	
	@Column(name = "Shipment_Num")
	private String shipmentNum;
	
	@Column(name = "Branch_id")
	private Long branchId;
	
	@Column(name = "Shipped_Date")
	private Date shippedDate;
	
	@Column(name = "Packing_Slip")
	private String packingSlip;
	
	@Column(name = "Expected_Receipt_Date")
	private Date expectedReceiptDate;
	
	@Column(name = "Waybill_Airbill_Num")
	private String waybillAirbillNum;
	
	@Column(name = "BILL_OF_LADING")
	private String billOfLading;
	
	@Column(name = "APPROVAL_STATUS")
	private String approvalStatus;//
	
	@Column(name = "FREIGHT_CARRIER_CODE")
	private String freightCarrierCode;
	
	@Column(name = "NUM_OF_CONTAINERS")
	private Long numOfContainers;
	
	@Column(name = "Comments")
	private String comments;
	
	@Column(name = "Accepted_By")
	private Long accepteBy;
	
	
	@Column(name = "Freight_Terms")
	private String freightTerms;
	
	@Column(name = "Source")
	private String source;
	
	@Column(name = "Source_Ref_No")
	private String sourceRefNo;

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
	
	@Column(name = "SUPPLIER_INVOICE_NUMBER")
	private String supplierInvoiceNumber;
	
	@Column(name = "SUPPLIER_INVOICE_DATE")
	private Date supplierInvoiceDate;
	
	@Column(name = "SUPPLIER_INVOICE_AMOUNT")
	private Double supplierInvoiceAmount;
	
	@Transient
	private String poNumber;
	
	@Transient
	private String valueName;
	
	@Transient
	private String valueCode;
	
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
	private String shippedDateStr;
	
	@Transient
	private String vendorName;
	
	@Transient
	private String vendorAdd;
	
	@Transient
	private String location;
	
	@Transient
	private String empName;;
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Long getShipmentHeaderId() {
		return shipmentHeaderId;
	}

	public void setShipmentHeaderId(Long shipmentHeaderId) {
		this.shipmentHeaderId = shipmentHeaderId;
	}

	public String getReceiptSourceCode() {
		return receiptSourceCode;
	}

	public void setReceiptSourceCode(String receiptSourceCode) {
		this.receiptSourceCode = receiptSourceCode;
	}

	public String getAsnType() {
		return asnType;
	}

	public void setAsnType(String asnType) {
		this.asnType = asnType;
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

	public void setVendorAddId(Long vendorAddId) {
		this.vendorAddId = vendorAddId;
	}

	

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getShippedDateStr() {
		return shippedDateStr;
	}

	public void setShippedDateStr(String shippedDateStr) {
		this.shippedDateStr = shippedDateStr;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorAdd() {
		return vendorAdd;
	}

	public void setVendorAdd(String vendorAdd) {
		this.vendorAdd = vendorAdd;
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

	public String getShipmentNum() {
		return shipmentNum;
	}

	public void setShipmentNum(String shipmentNum) {
		this.shipmentNum = shipmentNum;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getPackingSlip() {
		return packingSlip;
	}

	public void setPackingSlip(String packingSlip) {
		this.packingSlip = packingSlip;
	}

	public Date getExpectedReceiptDate() {
		return expectedReceiptDate;
	}

	public void setExpectedReceiptDate(Date expectedReceiptDate) {
		this.expectedReceiptDate = expectedReceiptDate;
	}

	public String getWaybillAirbillNum() {
		return waybillAirbillNum;
	}

	public void setWaybillAirbillNum(String waybillAirbillNum) {
		this.waybillAirbillNum = waybillAirbillNum;
	}

	public String getBillOfLading() {
		return billOfLading;
	}

	public void setBillOfLading(String billOfLading) {
		this.billOfLading = billOfLading;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getFreightCarrierCode() {
		return freightCarrierCode;
	}

	public void setFreightCarrierCode(String freightCarrierCode) {
		this.freightCarrierCode = freightCarrierCode;
	}

	public Long getNumOfContainers() {
		return numOfContainers;
	}

	public void setNumOfContainers(Long numOfContainers) {
		this.numOfContainers = numOfContainers;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getAccepteBy() {
		return accepteBy;
	}

	public void setAccepteBy(Long accepteBy) {
		this.accepteBy = accepteBy;
	}

	public String getFreightTerms() {
		return freightTerms;
	}

	public void setFreightTerms(String freightTerms) {
		this.freightTerms = freightTerms;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceRefNo() {
		return sourceRefNo;
	}

	public void setSourceRefNo(String sourceRefNo) {
		this.sourceRefNo = sourceRefNo;
	}

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSupplierInvoiceNumber() {
		return supplierInvoiceNumber;
	}

	public void setSupplierInvoiceNumber(String supplierInvoiceNumber) {
		this.supplierInvoiceNumber = supplierInvoiceNumber;
	}

	public Date getSupplierInvoiceDate() {
		return supplierInvoiceDate;
	}

	public void setSupplierInvoiceDate(Date supplierInvoiceDate) {
		this.supplierInvoiceDate = supplierInvoiceDate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Double getSupplierInvoiceAmount() {
		return supplierInvoiceAmount;
	}

	public void setSupplierInvoiceAmount(Double supplierInvoiceAmount) {
		this.supplierInvoiceAmount = supplierInvoiceAmount;
	}
	
	

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	@Override
	public String toString() {
		return "LtShipmentHeaders [shipmentHeaderId=" + shipmentHeaderId + ", receiptSourceCode=" + receiptSourceCode
				+ ", asnType=" + asnType + ", vendorId=" + vendorId + ", vendorAddId=" + vendorAddId + ", shipmentNum="
				+ shipmentNum + ", branchId=" + branchId + ", shippedDate=" + shippedDate + ", packingSlip="
				+ packingSlip + ", expectedReceiptDate=" + expectedReceiptDate + ", waybillAirbillNum="
				+ waybillAirbillNum + ", billOfLading=" + billOfLading + ", approvalStatus=" + approvalStatus
				+ ", freightCarrierCode=" + freightCarrierCode + ", numOfContainers=" + numOfContainers + ", comments="
				+ comments + ", accepteBy=" + accepteBy + ", freightTerms=" + freightTerms + ", source=" + source
				+ ", sourceRefNo=" + sourceRefNo + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", supplierInvoiceNumber=" + supplierInvoiceNumber + ", supplierInvoiceDate="
				+ supplierInvoiceDate + ", supplierInvoiceAmount=" + supplierInvoiceAmount + ", poNumber=" + poNumber
				+ ", valueName=" + valueName + ", valueCode=" + valueCode + ", draw=" + draw + ", start=" + start
				+ ", length=" + length + ", columnNo=" + columnNo + ", sort=" + sort + ", shippedDateStr="
				+ shippedDateStr + ", vendorName=" + vendorName + ", vendorAdd=" + vendorAdd + ", location=" + location
				+ ", empName=" + empName + "]";
	}

	

	
	
	
}