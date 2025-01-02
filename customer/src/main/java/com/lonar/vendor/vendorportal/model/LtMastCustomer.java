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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "LT_MAST_CUSTOMERS")
@JsonInclude(Include.NON_NULL)
public class LtMastCustomer  {

	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
//	@SequenceGenerator(name = "customer_seq", sequenceName = "LT_MAST_CUSTOMERS_S", allocationSize = 1)
	@Column(name = "CUSTOMER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "CUSTOMER_NUMBER")
	private String customerNumber;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "STATUS")
	private String status;
					
	@Column(name = "CUSTOMER_CLASS_CODE")
	private String customerClassCode;

	@Column(name = "LEGAL_STATUS")
	private String legalStatus;

	@Column(name = "PAN_NO")
	private String panNo;

	@Column(name = "TAN_NO")
	private String tanNo;

	@Column(name = "CUSTOMER_TYPE")
	private String customerType;

	@Column(name = "RELATED_PARTY")
	private String relatedParty;

	@Column(name = "CUSTOMER_GL_Class")
	private String customerGlClass;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "ACCOUNT_DESC")
	private String accountDesc;

	@Column(name = "DIVISION_ID")
	private Long divisionId;

	@Column(name = "COMPANY_ID")
	private Long companyId;

	@Column(name = "INITIATOR_ID")
	private Long initiatorId;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Basic(optional = false)
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Basic(optional = false)
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;

	@Basic(optional = false)
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;

	@Basic(optional = false)
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
	private String initiator;
	
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerClassCode() {
		return customerClassCode;
	}
	public void setCustomerClassCode(String customerClassCode) {
		this.customerClassCode = customerClassCode;
	}
	public String getLegalStatus() {
		return legalStatus;
	}
	public void setLegalStatus(String legalStatus) {
		this.legalStatus = legalStatus;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getTanNo() {
		return tanNo;
	}
	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getRelatedParty() {
		return relatedParty;
	}
	public void setRelatedParty(String relatedParty) {
		this.relatedParty = relatedParty;
	}
	public String getCustomerGlClass() {
		return customerGlClass;
	}
	public void setCustomerGlClass(String customerGlClass) {
		this.customerGlClass = customerGlClass;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountDesc() {
		return accountDesc;
	}
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}
	public Long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getInitiatorId() {
		return initiatorId;
	}
	public void setInitiatorId(Long initiatorId) {
		this.initiatorId = initiatorId;
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
	public String getInitiator() {
		return initiator;
	}
	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}
	@Override
	public String toString() {
		return "LtMastCustomer [customerId=" + customerId + ", customerNumber=" + customerNumber + ", customerName="
				+ customerName + ", status=" + status + ", customerClassCode=" + customerClassCode + ", legalStatus="
				+ legalStatus + ", panNo=" + panNo + ", tanNo=" + tanNo + ", customerType=" + customerType
				+ ", relatedParty=" + relatedParty + ", customerGlClass=" + customerGlClass + ", accountType="
				+ accountType + ", accountDesc=" + accountDesc + ", divisionId=" + divisionId + ", companyId="
				+ companyId + ", initiatorId=" + initiatorId + ", createdBy=" + createdBy + ", creationDate="
				+ creationDate + ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", draw=" + draw + ", start=" + start + ", length=" + length
				+ ", stDate=" + stDate + ", enDate=" + enDate + ", columnNo=" + columnNo + ", sort=" + sort
				+ ", initiator=" + initiator + "]";
	}
	

	
	 
}
