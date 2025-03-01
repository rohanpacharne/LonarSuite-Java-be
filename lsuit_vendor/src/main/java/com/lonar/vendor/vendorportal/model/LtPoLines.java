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
import javax.persistence.Transient;

@Entity
@Table(name = " LT_PO_LINES")
public class LtPoLines {

	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "po_line_seq")
//	@SequenceGenerator(name = "po_line_seq", sequenceName = "LT_PO_LINES_S", allocationSize = 1)
	@Column(name = "PO_LINE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long poLineId;
	
	@Column(name = "PO_HEADER_ID")
	private Long poHeaderId;
	
	@Column(name = "LINE_NUM")
	private Long lineNum;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "PRODUCT_CODE")
	private String productCode;
	
	@Column(name = "PRODUCT_DESCRIPTION")
	private String productDescription;
	
	@Column(name = "QUANTITY")
	private Long quantity;
	
	@Column(name = "NOTE_TO_VENDOR")
	private String noteToVendor;

	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	
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
	
	@Column(name = "UNIT_PRICE")
	private Double unitPrice;
	
	@Column(name = "LINE_TYPE")
	private String lineType;
	
	@Column(name = "LINE_AMOUNT")
	private Double lineAmount;
	
	@Column(name = "SUB_CATEGORY_ID")
	private Long subCategoryId;
	
	@Column(name = "TAX_AMOUNT")
	private Double taxAmount;
	
	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount;
 

	@Transient
	private Long draw;

	@Transient
	private Long start;

	@Transient
	private Long length;

	@Transient
	private String pDate;
	
	@Transient
	private String revDate;
	
	@Transient
	private int columnNo;
	
	@Transient
	private String sort;
	
	@Transient
	private Long subcategoryId;
	
	@Transient
	private String uom;
	
	@Transient
	private String poNumber ;
	
	@Transient
	private String taxName;
	
	@Transient
	private String productName;
	
	@Transient
	private String categoryName;
	
	@Transient
	private String productCategory;
	
	@Transient
	private String subCategory;
	
	
	
	
	
	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
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

	public Long getLineNum() {
		return lineNum;
	}

	public void setLineNum(Long lineNum) {
		this.lineNum = lineNum;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getNoteToVendor() {
		return noteToVendor;
	}

	public void setNoteToVendor(String noteToVendor) {
		this.noteToVendor = noteToVendor;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public Double getLineAmount() {
		return lineAmount;
	}

	public void setLineAmount(Double lineAmount) {
		this.lineAmount = lineAmount;
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

	public String getpDate() {
		return pDate;
	}

	public void setpDate(String pDate) {
		this.pDate = pDate;
	}

	public String getRevDate() {
		return revDate;
	}

	public void setRevDate(String revDate) {
		this.revDate = revDate;
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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "LtPoLines [poLineId=" + poLineId + ", poHeaderId=" + poHeaderId + ", lineNum=" + lineNum
				+ ", productId=" + productId + ", productCode=" + productCode + ", productDescription="
				+ productDescription + ", quantity=" + quantity + ", noteToVendor=" + noteToVendor + ", categoryId="
				+ categoryId + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin="
				+ lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", unitPrice=" + unitPrice + ", lineType=" + lineType + ", lineAmount=" + lineAmount
				+ ", subCategoryId=" + subCategoryId + ", draw=" + draw + ", start=" + start + ", length=" + length
				+ ", pDate=" + pDate + ", revDate=" + revDate + ", columnNo=" + columnNo + ", sort=" + sort
				+ ", subcategoryId=" + subcategoryId + ", uom=" + uom + ", poNumber=" + poNumber + ", taxName="
				+ taxName + ", productName=" + productName + ", categoryName=" + categoryName + ", taxAmount="
				+ taxAmount + ", totalAmount=" + totalAmount + ", productCategory=" + productCategory + ", subCategory="
				+ subCategory + "]";
	}

	

	

	

	
	
	
	
}
