package com.lonar.vendor.vendorportal.model;

import java.util.Date;

public class LtMastCustomersDto {
    private int customerId;
    private String customerNumber;
    private String customerName;
    private String status;
    private String customerClassCode;
    private String legalStatus;
    private String panNo;
    private String tanNo;
    private String customerType;
    private String relatedParty;
    private String customerGlClass;
    private String accountType;
    private String accountDesc;
//    private int divisionId;
    private int companyId;
    private int createdBy;
    private Date creationDate;
    private int lastUpdateLogin;
    private int lastUpdatedBy;
    private Date lastUpdateDate;
    private int initiatorId;

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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

//    public int getDivisionId() {
//        return divisionId;
//    }
//
//    public void setDivisionId(int divisionId) {
//        this.divisionId = divisionId;
//    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(int lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    public int getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(int lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(int initiatorId) {
        this.initiatorId = initiatorId;
    }

	@Override
	public String toString() {
		return "LtMastCustomersDto [customerId=" + customerId + ", customerNumber=" + customerNumber + ", customerName="
				+ customerName + ", status=" + status + ", customerClassCode=" + customerClassCode + ", legalStatus="
				+ legalStatus + ", panNo=" + panNo + ", tanNo=" + tanNo + ", customerType=" + customerType
				+ ", relatedParty=" + relatedParty + ", customerGlClass=" + customerGlClass + ", accountType="
				+ accountType + ", accountDesc=" + accountDesc + ", divisionId=" + ", companyId="
				+ companyId + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin="
				+ lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", initiatorId=" + initiatorId + "]";
	}
}

