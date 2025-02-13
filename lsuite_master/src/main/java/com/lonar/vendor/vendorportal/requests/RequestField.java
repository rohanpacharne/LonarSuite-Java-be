package com.lonar.vendor.vendorportal.requests;

import java.util.Date;

public class RequestField {
    private String batchId = "Batch_A12345";
    private String importAction = "CREATE";
    private String supplierName;
    private String supplierNewName;
    private String supplierNumber;
    private String alternateName;
    private String taxOrganizationType;
    private String supplierType;
    private Date inactiveDate;
    private String businessRelationship;
    private String parentSupplier;
    private String alias;
    private String dunsNumber;
    private String oneTimeSupplier;
    private String customerNumber;
    private String sic;
    private String nationalInsuranceNumber;
    private String corporateWebSite;
    private String chiefExecutiveTitle;
    private String chiefExecutiveName;
    private String businessClassificationsNotApplicable;
    private String taxpayerCountry;
    private String taxpayerId;
    private String federalReportable;
    private String federalIncomeTaxType;
    private String stateReportable;
    private String taxReportingName;
    private String nameControl;
    private Date taxVerificationDate;
    private Boolean useWithholdingTax;
    private String withholdingTaxGroup;
    private String vatCode;
    private String taxRegistrationNumber;
    private String autoTaxCalcOverride;
    private String paymentMethod;
    private String deliveryChannel;
    private String bankInstruction1;
    private String bankInstruction2;
    private String bankInstruction;
    private String settlementPriority;
    private String paymentTextMessage1;
    private String paymentTextMessage2;
    private String paymentTextMessage3;
    private String bankChargeBearer;
    private String paymentReason;
    private String paymentReasonComments;
    private String paymentFormat;

    // Attributes (1 to 20)
    private String[] attributes = new String[20];

    // Date Attributes (1 to 10)
    private Date[] attributeDates = new Date[10];

    // Timestamp Attributes (1 to 10)
    private Date[] attributeTimestamps = new Date[10];

    // Numeric Attributes (1 to 10)
    private Double[] attributeNumbers = new Double[10];

    // Global Attributes (1 to 20)
    private String[] globalAttributes = new String[20];
    private Date[] globalAttributeDates = new Date[10];
    private Date[] globalAttributeTimestamps = new Date[10];
    private Double[] globalAttributeNumbers = new Double[10];

    // Registry Fields
    private String registryId;
    private String payeeServiceLevel;
    private Boolean payEachDocumentAlone;
    private String deliveryMethod;
    private String remittanceEmail;
    private String remittanceFax;
    private String dataFoxId;

    // Getters and Setters
    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }

    public String getImportAction() { return importAction; }
    public void setImportAction(String importAction) { this.importAction = importAction; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public String getSupplierNewName() { return supplierNewName; }
    public void setSupplierNewName(String supplierNewName) { this.supplierNewName = supplierNewName; }

    public String getSupplierNumber() { return supplierNumber; }
    public void setSupplierNumber(String supplierNumber) { this.supplierNumber = supplierNumber; }

    public String getAlternateName() { return alternateName; }
    public void setAlternateName(String alternateName) { this.alternateName = alternateName; }

    public String getTaxOrganizationType() { return taxOrganizationType; }
    public void setTaxOrganizationType(String taxOrganizationType) { this.taxOrganizationType = taxOrganizationType; }

    public String getSupplierType() { return supplierType; }
    public void setSupplierType(String supplierType) { this.supplierType = supplierType; }

    public Date getInactiveDate() { return inactiveDate; }
    public void setInactiveDate(Date inactiveDate) { this.inactiveDate = inactiveDate; }

    public String getBusinessRelationship() { return businessRelationship; }
    public void setBusinessRelationship(String businessRelationship) { this.businessRelationship = businessRelationship; }

    public String getParentSupplier() { return parentSupplier; }
    public void setParentSupplier(String parentSupplier) { this.parentSupplier = parentSupplier; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getDunsNumber() { return dunsNumber; }
    public void setDunsNumber(String dunsNumber) { this.dunsNumber = dunsNumber; }

    public String getOneTimeSupplier() { return oneTimeSupplier; }
    public void setOneTimeSupplier(String oneTimeSupplier) { this.oneTimeSupplier = oneTimeSupplier; }

    public String getCustomerNumber() { return customerNumber; }
    public void setCustomerNumber(String customerNumber) { this.customerNumber = customerNumber; }

    public String getSic() { return sic; }
    public void setSic(String sic) { this.sic = sic; }

    public String getNationalInsuranceNumber() { return nationalInsuranceNumber; }
    public void setNationalInsuranceNumber(String nationalInsuranceNumber) { this.nationalInsuranceNumber = nationalInsuranceNumber; }

    public String getCorporateWebSite() { return corporateWebSite; }
    public void setCorporateWebSite(String corporateWebSite) { this.corporateWebSite = corporateWebSite; }

    public String getChiefExecutiveTitle() { return chiefExecutiveTitle; }
    public void setChiefExecutiveTitle(String chiefExecutiveTitle) { this.chiefExecutiveTitle = chiefExecutiveTitle; }

    public String getChiefExecutiveName() { return chiefExecutiveName; }
    public void setChiefExecutiveName(String chiefExecutiveName) { this.chiefExecutiveName = chiefExecutiveName; }

    public String getBusinessClassificationsNotApplicable() { return businessClassificationsNotApplicable; }
    public void setBusinessClassificationsNotApplicable(String businessClassificationsNotApplicable) { this.businessClassificationsNotApplicable = businessClassificationsNotApplicable; }

    public String getTaxpayerCountry() { return taxpayerCountry; }
    public void setTaxpayerCountry(String taxpayerCountry) { this.taxpayerCountry = taxpayerCountry; }

    public String getTaxpayerId() { return taxpayerId; }
    public void setTaxpayerId(String taxpayerId) { this.taxpayerId = taxpayerId; }

    public String getFederalReportable() { return federalReportable; }
    public void setFederalReportable(String federalReportable) { this.federalReportable = federalReportable; }

    public String getFederalIncomeTaxType() { return federalIncomeTaxType; }
    public void setFederalIncomeTaxType(String federalIncomeTaxType) { this.federalIncomeTaxType = federalIncomeTaxType; }

    public String getStateReportable() { return stateReportable; }
    public void setStateReportable(String stateReportable) { this.stateReportable = stateReportable; }

    public String getTaxReportingName() { return taxReportingName; }
    public void setTaxReportingName(String taxReportingName) { this.taxReportingName = taxReportingName::contentReference[oaicite:0]{index=0};
    }
}
