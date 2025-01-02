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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "LT_MAST_CUST_SITE_COMMERCIALS")
@JsonInclude(Include.NON_NULL)
public class LtMastCustSiteCommercials  {
	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_site_commercials_seq")
//	@SequenceGenerator(name = "customer_site_commercials_seq", sequenceName = "LT_MAST_CUST_SITE_COMMERCIAL_S", allocationSize = 1)
	@Column(name = "SITE_COMMERCIAL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long siteCommercialId;

	@Column(name = "CUSTOMER_SITE_ID")
	private Long customerSiteId;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "PAYMENT_TERMS")
	private Long paymentTerms;

	@Column(name = "COLLECTOR")
	private String collector;

	@Column(name = "CREDIT_CURRENCY")
	private String creditCurrency;

	@Column(name = "CREDIT_HOLD")
	private String creditHold;

	@Column(name = "CREDIT_LIMIT")
	private Double creditLimit;

	@Basic(optional = false)
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Basic(optional = false)
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Basic(optional = false)
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
	
	@Column(name = "LAST_UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	@Transient
	private Long draw;

	@Transient
	private Long start;

	@Transient
	private Long length;

	@Transient
	private String stDate;
	@Transient
	private String enDate;
	@Transient
	private int columnNo;
	@Transient
	private String sort;
	@Transient
	private String collectorName;
	@Transient
	private String termName;
	
	public Long getSiteCommercialId() {
		return siteCommercialId;
	}
	public void setSiteCommercialId(Long siteCommercialId) {
		this.siteCommercialId = siteCommercialId;
	}
	public Long getCustomerSiteId() {
		return customerSiteId;
	}
	public void setCustomerSiteId(Long customerSiteId) {
		this.customerSiteId = customerSiteId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getPaymentTerms() {
		return paymentTerms;
	}
	public void setPaymentTerms(Long paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	public String getCollector() {
		return collector;
	}
	public void setCollector(String collector) {
		this.collector = collector;
	}
	public String getCreditCurrency() {
		return creditCurrency;
	}
	public void setCreditCurrency(String creditCurrency) {
		this.creditCurrency = creditCurrency;
	}
	public String getCreditHold() {
		return creditHold;
	}
	public void setCreditHold(String creditHold) {
		this.creditHold = creditHold;
	}
	public Double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
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
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getEnDate() {
		return enDate;
	}
	public void setEnDate(String enDate) {
		this.enDate = enDate;
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
	public String getCollectorName() {
		return collectorName;
	}
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	@Override
	public String toString() {
		return "LtMastCustSiteCommercials [siteCommercialId=" + siteCommercialId + ", customerSiteId=" + customerSiteId
				+ ", customerId=" + customerId + ", paymentTerms=" + paymentTerms + ", collector=" + collector
				+ ", creditCurrency=" + creditCurrency + ", creditHold=" + creditHold + ", creditLimit=" + creditLimit
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", draw=" + draw
				+ ", start=" + start + ", length=" + length + ", stDate=" + stDate + ", enDate=" + enDate
				+ ", columnNo=" + columnNo + ", sort=" + sort + ", collectorName=" + collectorName + ", termName="
				+ termName + "]";
	}
	
	
	

}
