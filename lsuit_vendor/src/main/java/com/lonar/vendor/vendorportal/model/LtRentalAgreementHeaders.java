package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "LT_RENTAL_AGREEMENT_HEADERS" )
@JsonInclude(Include.NON_NULL)
public class LtRentalAgreementHeaders {

	@Id
	@Column(name = "AGREEMENT_HEADER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agreementHeaderId;
	
	@Column(name = "AGREEMENT_NUMBER")
    private String agreementNumber;
	
	@Column(name = "PROPERTY_TITLE")
    private String propertyTitle;
	
	@Column(name = "VENDOR_ID")
    private Long vendorId;
	
	@Column(name = "PROPERTY_ADDRESS")
    private String propertyAddress;
	
	@Column(name = "PROPERTY_CITY")
    private String propertyCity;
	
	@Column(name = "PROPERTY_STATE_ID")
    private Long propertyStateId;
	
	@Column(name = "PROPERTY_TYPE")
    private String propertyType;
	
	@Column(name = "FROM_DATE")
    private Date fromDate;
	
	@Column(name = "TO_DATE")
    private Date toDate;
	
	@Column(name = "SECURITY_DEPOSIT")
    private Double securityDeposit;
	
	@Column(name = "SD_PAYMENT_REF")
    private String sdPaymentRef;
	
	@Column(name = "SD_PAYMENT_DATE")
    private Date sdPaymentDate;
	
	@Column(name = "BASE_RENT")
    private Double baseRent;
	
	@Column(name = "RENTAL_TYPE")
    private String rentalType;
	
	@Column(name = "RENT_FREQUENCY")
    private String rentFrequency;
	
	@Column(name = "LOCK_IN_PERIOD")
    private Long lockInPeriod;
	
	@Column(name = "NOTICE_PERIOD")
    private Long noticePeriod;
	
	@Column(name = "RENT_CURRENCY")
    private String rentCurrency;
	
	@Column(name = "RENT_DUE_DAY")
    private Long rentDueDay;
	
	@Column(name = "BRANCH_ID")
    private Long branchId;
	
	@Column(name = "AGREEMENT_FILE_PATH")
    private String agreementFilePath;
	
	@Column(name = "TERMINATION_DATE")
    private Date terminationDate;
	
	@Column(name = "TERMINATION_REMARK")
    private String terminationRemark;
	
	@Column(name = "STATUS")
    private String status;
	
	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	@Column(name = "Created_by")
	private Long createdBy;
	
	@Basic(optional = false)
	@Column(name = "Creation_date")
	private Date creationDate;
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	
	@Column(name = "Last_updated_by")
	private Long lastUpdatedBy;
	
	@Column(name = "Last_update_date")
	private Date lastUpdateDate;
	
	@Column(name = "division_id")
	private Long divisionId;
	
	@Column(name = "initiator_id")
	private Long initiatorId;
	
	@Column(name = "vendor_add_id")
	private Long vendorAddId;
	
	@Column(name = "billing_add_id")
	private Long billingAddId;
	
	@Transient
	private String divisionName;
	
	@Transient
	private String initiatorName;
	
	@Transient
	private String branchName;
	
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
	private String vendorName;
	
	@Transient
	private String propertyTypeName;
	
	@Transient
	private String rentFrequencyName;
	
	@Transient
	private String valueCode;
	
	@Transient
	private String valueName;

	public LtRentalAgreementHeaders() {
		super();
	}

	public LtRentalAgreementHeaders(Long agreementHeaderId, String agreementNumber, String propertyTitle, Long vendorId,
			String propertyAddress, String propertyCity, Long propertyStateId, String propertyType, Date fromDate,
			Date toDate, Double securityDeposit, String sdPaymentRef, Date sdPaymentDate, Double baseRent,
			String rentalType, String rentFrequency, Long lockInPeriod, Long noticePeriod, String rentCurrency,
			Long rentDueDay, Long branchId, String agreementFilePath, Date terminationDate, String terminationRemark,
			String status, Long companyId, Long createdBy, Date creationDate, Long lastUpdateLogin, Long lastUpdatedBy,
			Date lastUpdateDate, Long divisionId, Long initiatorId, Long vendorAddId, Long billingAddId,
			String divisionName, String initiatorName, String branchName, Long draw, Long start, Long length,
			int columnNo, String sort, String vendorName, String propertyTypeName, String rentFrequencyName,
			String valueCode, String valueName) {
		super();
		this.agreementHeaderId = agreementHeaderId;
		this.agreementNumber = agreementNumber;
		this.propertyTitle = propertyTitle;
		this.vendorId = vendorId;
		this.propertyAddress = propertyAddress;
		this.propertyCity = propertyCity;
		this.propertyStateId = propertyStateId;
		this.propertyType = propertyType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.securityDeposit = securityDeposit;
		this.sdPaymentRef = sdPaymentRef;
		this.sdPaymentDate = sdPaymentDate;
		this.baseRent = baseRent;
		this.rentalType = rentalType;
		this.rentFrequency = rentFrequency;
		this.lockInPeriod = lockInPeriod;
		this.noticePeriod = noticePeriod;
		this.rentCurrency = rentCurrency;
		this.rentDueDay = rentDueDay;
		this.branchId = branchId;
		this.agreementFilePath = agreementFilePath;
		this.terminationDate = terminationDate;
		this.terminationRemark = terminationRemark;
		this.status = status;
		this.companyId = companyId;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdateLogin = lastUpdateLogin;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.divisionId = divisionId;
		this.initiatorId = initiatorId;
		this.vendorAddId = vendorAddId;
		this.billingAddId = billingAddId;
		this.divisionName = divisionName;
		this.initiatorName = initiatorName;
		this.branchName = branchName;
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.columnNo = columnNo;
		this.sort = sort;
		this.vendorName = vendorName;
		this.propertyTypeName = propertyTypeName;
		this.rentFrequencyName = rentFrequencyName;
		this.valueCode = valueCode;
		this.valueName = valueName;
	}

	public String getDivisionName() {
		return divisionName;
	}


	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}


	public String getInitiatorName() {
		return initiatorName;
	}


	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public Long getDivisionId() {
		return divisionId;
	}


	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}


	public Long getInitiatorId() {
		return initiatorId;
	}


	public void setInitiatorId(Long initiatorId) {
		this.initiatorId = initiatorId;
	}


	public String getValueCode() {
		return valueCode;
	}



	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}



	public String getValueName() {
		return valueName;
	}



	public void setValueName(String valueName) {
		this.valueName = valueName;
	}



	public Long getAgreementHeaderId() {
		return agreementHeaderId;
	}


	public void setAgreementHeaderId(Long agreementHeaderId) {
		this.agreementHeaderId = agreementHeaderId;
	}


	public String getAgreementNumber() {
		return agreementNumber;
	}


	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}


	public String getPropertyTitle() {
		return propertyTitle;
	}


	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}


	public Long getVendorId() {
		return vendorId;
	}


	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}


	public String getPropertyAddress() {
		return propertyAddress;
	}


	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}


	public String getPropertyCity() {
		return propertyCity;
	}


	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}


	public Long getPropertyStateId() {
		return propertyStateId;
	}


	public void setPropertyStateId(Long propertyStateId) {
		this.propertyStateId = propertyStateId;
	}


	public String getPropertyType() {
		return propertyType;
	}


	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
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


	public Double getSecurityDeposit() {
		return securityDeposit;
	}


	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}


	public String getSdPaymentRef() {
		return sdPaymentRef;
	}


	public void setSdPaymentRef(String sdPaymentRef) {
		this.sdPaymentRef = sdPaymentRef;
	}


	public Date getSdPaymentDate() {
		return sdPaymentDate;
	}


	public void setSdPaymentDate(Date sdPaymentDate) {
		this.sdPaymentDate = sdPaymentDate;
	}


	public Double getBaseRent() {
		return baseRent;
	}


	public void setBaseRent(Double baseRent) {
		this.baseRent = baseRent;
	}


	public String getRentalType() {
		return rentalType;
	}


	public void setRentalType(String rentalType) {
		this.rentalType = rentalType;
	}


	public String getRentFrequency() {
		return rentFrequency;
	}


	public void setRentFrequency(String rentFrequency) {
		this.rentFrequency = rentFrequency;
	}


	public Long getLockInPeriod() {
		return lockInPeriod;
	}


	public void setLockInPeriod(Long lockInPeriod) {
		this.lockInPeriod = lockInPeriod;
	}


	public Long getNoticePeriod() {
		return noticePeriod;
	}


	public void setNoticePeriod(Long noticePeriod) {
		this.noticePeriod = noticePeriod;
	}


	public String getRentCurrency() {
		return rentCurrency;
	}


	public void setRentCurrency(String rentCurrency) {
		this.rentCurrency = rentCurrency;
	}


	public Long getRentDueDay() {
		return rentDueDay;
	}


	public void setRentDueDay(Long rentDueDay) {
		this.rentDueDay = rentDueDay;
	}


	public Long getBranchId() {
		return branchId;
	}


	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}


	public String getAgreementFilePath() {
		return agreementFilePath;
	}


	public void setAgreementFilePath(String agreementFilePath) {
		this.agreementFilePath = agreementFilePath;
	}


	public Date getTerminationDate() {
		return terminationDate;
	}


	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}


	public String getTerminationRemark() {
		return terminationRemark;
	}


	public void setTerminationRemark(String terminationRemark) {
		this.terminationRemark = terminationRemark;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getVendorName() {
		return vendorName;
	}


	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	public String getPropertyTypeName() {
		return propertyTypeName;
	}


	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}


	public String getRentFrequencyName() {
		return rentFrequencyName;
	}


	public void setRentFrequencyName(String rentFrequencyName) {
		this.rentFrequencyName = rentFrequencyName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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


	public Long getVendorAddId() {
		return vendorAddId;
	}

	public void setVendorAddId(Long vendorAddId) {
		this.vendorAddId = vendorAddId;
	}

	public Long getBillingAddId() {
		return billingAddId;
	}

	public void setBillingAddId(Long billingAddId) {
		this.billingAddId = billingAddId;
	}

	@Override
	public String toString() {
		return "LtRentalAgreementHeaders [agreementHeaderId=" + agreementHeaderId + ", agreementNumber="
				+ agreementNumber + ", propertyTitle=" + propertyTitle + ", vendorId=" + vendorId + ", propertyAddress="
				+ propertyAddress + ", propertyCity=" + propertyCity + ", propertyStateId=" + propertyStateId
				+ ", propertyType=" + propertyType + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", securityDeposit=" + securityDeposit + ", sdPaymentRef=" + sdPaymentRef + ", sdPaymentDate="
				+ sdPaymentDate + ", baseRent=" + baseRent + ", rentalType=" + rentalType + ", rentFrequency="
				+ rentFrequency + ", lockInPeriod=" + lockInPeriod + ", noticePeriod=" + noticePeriod
				+ ", rentCurrency=" + rentCurrency + ", rentDueDay=" + rentDueDay + ", branchId=" + branchId
				+ ", agreementFilePath=" + agreementFilePath + ", terminationDate=" + terminationDate
				+ ", terminationRemark=" + terminationRemark + ", status=" + status + ", companyId=" + companyId
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", divisionId="
				+ divisionId + ", initiatorId=" + initiatorId + ", vendorAddId=" + vendorAddId + ", billingAddId="
				+ billingAddId + ", divisionName=" + divisionName + ", initiatorName=" + initiatorName + ", branchName="
				+ branchName + ", draw=" + draw + ", start=" + start + ", length=" + length + ", columnNo=" + columnNo
				+ ", sort=" + sort + ", vendorName=" + vendorName + ", propertyTypeName=" + propertyTypeName
				+ ", rentFrequencyName=" + rentFrequencyName + ", valueCode=" + valueCode + ", valueName=" + valueName
				+ "]";
	}
	
	
}
