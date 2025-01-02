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
@Table(name = "LT_MAST_CUST_SITE_GEN_INFO")
@JsonInclude(Include.NON_NULL)
public class LtMastCustSiteGenInfo {
	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_site_gen_info_seq")
//	@SequenceGenerator(name = "customer_site_gen_info_seq", sequenceName = "LT_MAST_CUST_SITE_GEN_INFO_S", allocationSize = 1)
	@Column(name = "SITE_GEN_INFO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long siteGenInfoId;

	@Column(name = "CUSTOMER_SITE_ID")
	private Long customerSiteId;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "CSE_CODE")
	private String cseCode;

	@Column(name = "TRANSIT_DAYS")
	private Double transitDays;

	@Column(name = "DEFAULT_ORDER_TYPE")
	private String defaultOrderType;

	@Column(name = "FREIGHT_TERMS")
	private String freightTerms;

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
	private String employeeName;

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
	private String transitDayStr;
	
	public Long getSiteGenInfoId() {
		return siteGenInfoId;
	}

	public void setSiteGenInfoId(Long siteGenInfoId) {
		this.siteGenInfoId = siteGenInfoId;
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

	public String getCseCode() {
		return cseCode;
	}

	public void setCseCode(String cseCode) {
		this.cseCode = cseCode;
	}

	public Double getTransitDays() {
		return transitDays;
	}

	public void setTransitDays(Double transitDays) {
		this.transitDays = transitDays;
	}

	public String getDefaultOrderType() {
		return defaultOrderType;
	}

	public void setDefaultOrderType(String defaultOrderType) {
		this.defaultOrderType = defaultOrderType;
	}

	public String getFreightTerms() {
		return freightTerms;
	}

	public void setFreightTerms(String freightTerms) {
		this.freightTerms = freightTerms;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public String getTransitDayStr() {
		return transitDayStr;
	}

	public void setTransitDayStr(String transitDayStr) {
		this.transitDayStr = transitDayStr;
	}

	@Override
	public String toString() {
		return "LtMastCustSiteGenInfo [siteGenInfoId=" + siteGenInfoId + ", customerSiteId=" + customerSiteId
				+ ", customerId=" + customerId + ", cseCode=" + cseCode + ", transitDays=" + transitDays
				+ ", defaultOrderType=" + defaultOrderType + ", freightTerms=" + freightTerms + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", employeeName="
				+ employeeName + "]";
	}

}
