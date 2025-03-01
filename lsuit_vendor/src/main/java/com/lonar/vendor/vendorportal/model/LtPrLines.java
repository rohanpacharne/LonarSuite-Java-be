package com.lonar.vendor.vendorportal.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lt_pr_lines")
public class LtPrLines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_line_id")
    private Integer prLineId;

    @Column(name = "pr_header_id")
    private Integer prHeaderId;

    @Column(name = "line_no")
    private Integer lineNo;

    @Column(name = "line_type")
    private String lineType;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "uom")
    private String uom;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "base_amount")
    private Double baseAmount;

    @Column(name = "charge_account")
    private Integer chargeAccount;

    @Column(name = "need_by_date")
    @Temporal(TemporalType.DATE)
    private Date needByDate;

    @Column(name = "note_to_buyer")
    private String noteToBuyer;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Column(name = "last_update_login")
    private Integer lastUpdateLogin;

    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;

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
	private String categoryName;
	
	@Transient
	private String productName;

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





	public Integer getPrLineId() {
		return prLineId;
	}





	public void setPrLineId(Integer prLineId) {
		this.prLineId = prLineId;
	}





	public Integer getPrHeaderId() {
		return prHeaderId;
	}





	public void setPrHeaderId(Integer prHeaderId) {
		this.prHeaderId = prHeaderId;
	}





	public Integer getLineNo() {
		return lineNo;
	}





	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}





	public String getLineType() {
		return lineType;
	}





	public void setLineType(String lineType) {
		this.lineType = lineType;
	}





	public Integer getCategoryId() {
		return categoryId;
	}





	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}





	public Integer getProductId() {
		return productId;
	}





	public void setProductId(Integer productId) {
		this.productId = productId;
	}





	public String getProductDesc() {
		return productDesc;
	}





	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}





	public String getUom() {
		return uom;
	}





	public void setUom(String uom) {
		this.uom = uom;
	}





	public Double getQuantity() {
		return quantity;
	}





	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}





	public Double getRate() {
		return rate;
	}





	public void setRate(Double rate) {
		this.rate = rate;
	}





	public Double getBaseAmount() {
		return baseAmount;
	}





	public void setBaseAmount(Double baseAmount) {
		this.baseAmount = baseAmount;
	}





	public Integer getChargeAccount() {
		return chargeAccount;
	}





	public void setChargeAccount(Integer chargeAccount) {
		this.chargeAccount = chargeAccount;
	}





	public Date getNeedByDate() {
		return needByDate;
	}





	public void setNeedByDate(Date needByDate) {
		this.needByDate = needByDate;
	}





	public String getNoteToBuyer() {
		return noteToBuyer;
	}





	public void setNoteToBuyer(String noteToBuyer) {
		this.noteToBuyer = noteToBuyer;
	}





	public Integer getCreatedBy() {
		return createdBy;
	}





	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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





	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}





	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}





	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}





	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
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

	@Override
	public String toString() {
		return "LtPrLines [prLineId=" + prLineId + ", prHeaderId=" + prHeaderId + ", lineNo=" + lineNo + ", lineType="
				+ lineType + ", categoryId=" + categoryId + ", productId=" + productId + ", productDesc=" + productDesc
				+ ", uom=" + uom + ", quantity=" + quantity + ", rate=" + rate + ", baseAmount=" + baseAmount
				+ ", chargeAccount=" + chargeAccount + ", needByDate=" + needByDate + ", noteToBuyer=" + noteToBuyer
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", draw=" + draw
				+ ", start=" + start + ", length=" + length + ", columnNo=" + columnNo + ", sort=" + sort
				+ ", categoryName=" + categoryName + ", productName=" + productName + "]";
	}





	// Optionally, override toString() method to print out object details
    
}

