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
@Table(name = "LT_RENTAL_AGREEMENT_LINES")
@JsonInclude(Include.NON_NULL)
public class LtRentalAgreementLines {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreement_line_id")
    private Long agreementLineId;

    @Column(name = "agreement_header_id")
    private Long agreementHeaderId;

    @Column(name = "base_rent_amount")
    private Double baseRentAmount;

    @Column(name = "rent_escalation")
    private Double rentEscalation;

    @Column(name = "rent_escalation_amount")
    private Double rentEscalationAmount;

    @Column(name = "rent_amount")
    private Double rentAmount;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "remark")
    private String remark;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_update_login")
    private Integer lastUpdateLogin;

    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;
    
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
	private Long rnum;

	public LtRentalAgreementLines() {
		super();
	}

	public LtRentalAgreementLines(Long agreementLineId, Long agreementHeaderId, Double baseRentAmount,
			Double rentEscalation, Double rentEscalationAmount, Double rentAmount, Double taxAmount, Double totalAmount,
			Date fromDate, Date toDate, String remark, Long createdBy, Date creationDate, Integer lastUpdateLogin,
			Integer lastUpdatedBy, Date lastUpdateDate, Long draw, Long start, Long length, int columnNo, String sort,
			Long rnum) {
		super();
		this.agreementLineId = agreementLineId;
		this.agreementHeaderId = agreementHeaderId;
		this.baseRentAmount = baseRentAmount;
		this.rentEscalation = rentEscalation;
		this.rentEscalationAmount = rentEscalationAmount;
		this.rentAmount = rentAmount;
		this.taxAmount = taxAmount;
		this.totalAmount = totalAmount;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.remark = remark;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdateLogin = lastUpdateLogin;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.columnNo = columnNo;
		this.sort = sort;
		this.rnum = rnum;
	}


	

	public Long getRnum() {
		return rnum;
	}

	public void setRnum(Long rnum) {
		this.rnum = rnum;
	}

	public Long getAgreementLineId() {
		return agreementLineId;
	}

	public void setAgreementLineId(Long agreementLineId) {
		this.agreementLineId = agreementLineId;
	}

	public Long getAgreementHeaderId() {
		return agreementHeaderId;
	}

	public void setAgreementHeaderId(Long agreementHeaderId) {
		this.agreementHeaderId = agreementHeaderId;
	}

	public Double getBaseRentAmount() {
		return baseRentAmount;
	}

	public void setBaseRentAmount(Double baseRentAmount) {
		this.baseRentAmount = baseRentAmount;
	}

	public Double getRentEscalation() {
		return rentEscalation;
	}

	public void setRentEscalation(Double rentEscalation) {
		this.rentEscalation = rentEscalation;
	}

	public Double getRentEscalationAmount() {
		return rentEscalationAmount;
	}

	public void setRentEscalationAmount(Double rentEscalationAmount) {
		this.rentEscalationAmount = rentEscalationAmount;
	}

	public Double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(Double rentAmount) {
		this.rentAmount = rentAmount;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		return "LtRentalAgreementLines [agreementLineId=" + agreementLineId + ", agreementHeaderId=" + agreementHeaderId
				+ ", baseRentAmount=" + baseRentAmount + ", rentEscalation=" + rentEscalation
				+ ", rentEscalationAmount=" + rentEscalationAmount + ", rentAmount=" + rentAmount + ", taxAmount="
				+ taxAmount + ", totalAmount=" + totalAmount + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", remark=" + remark + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", draw=" + draw + ", start=" + start + ", length=" + length + ", columnNo="
				+ columnNo + ", sort=" + sort + ", rnum=" + rnum + "]";
	}
	
	

}
