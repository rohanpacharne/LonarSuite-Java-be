package com.lonar.vendor.vendorportal.reports;

import java.math.BigDecimal;
import java.util.Date;

public class ReportField {
    
    // Expense fields
    private String expenseNumber;
    private Date expenseDate;
    private Long employeeId;
    private String employeeName;
    private Date hdrStartDate;
    private Date hdrEndDate;
    private Long divisionId;
    private String divisionName;
    private String branchName;
    private Long duration;
    private String status;
    private String lineNumber;
    private Long lineNo;
    private Date lineStartDate;
    private Date lineEndDate;
    private String expenseNature;

    // Purchase fields
    private String poNumber;
    private String poType;
    private Long revisionNum;
    private Date poDate;
    private Date revisionDate;
    private String description;
    private String noteToApprover;
    private String vendorName;
    private String vendorAddress;
    private String vendorContact;
    private String buyerName;
    private String billingAddress;
    private String poCurrency;
    private String lineType;
    private String productCode;
    private String productName;
    private Long quantity;
    private String taxName;
    private Double lineAmount;
    private Double taxAmount;
    private Double totalAmount;
    private String termName;
    private Long unitPrice;
    
    // Invoice fields
    private String invoiceNumber;
    private String invoiceType;
    private String internalInvoiceNumber;
    private String buyer;
    private BigDecimal poAmount;
    private String invoiceDate;
    private String invoiceRate;
    private String invoiceReceivedDate;
    private String address;
    private String division;
    private String initiator;
    private String shippingAddress;
    private String invoiceCurrency;
    private String exchangeRate;
    private BigDecimal invoiceAmount;
    private BigDecimal baseAmount;
    private String terms;
	private String productDescription;
    private int invoiceQty;
    
    //Rental field 

    private String agreementNumber;
    private String propertyCity;
    private String rentFrequency;
    private double baseRent;
    private String propertyTitle;
    private String propertyType;
    private String state;
    private String propertyAddress;
    private String fromDate;
    private String toDate;
    private String locationCode;
    private double securityDeposit;
    private String sdPaymentRef;
    private String sdPaymentDate;
    private String rentCurrency;
    private int noticePeriod;
    private int rentDueDay;
    private int lockInPeriod;
    private String rentalType;
    private String rentFrequencyValue;
    private String terminationDate;
    private String remark;
    private double lineBaseRent;
    private String rentEscalationPercentage;
    private String lineFromDate;
    private String lineToDate;
    private double rentAmount;
    private String panNo;
    private String Email;
    private String startDate;
    private String endDate;
    private String approverName;
    private String approvalStatus;
    private String dated;
    private String source;
    private String sourceRefNumber;
    
    
    //vendor fields

    private String vendorCode;
    private String vendorType;
    private String vendorLocationType;
    private String proprietorName;
    private String registrationEmail;
    private String transactionEmail;
    private String msmSupplier;
    private String msmeCategory;
    private String msmeRegistrationNo;
    private String companyCategory;
    private String businessNature;
    public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getVendorLocationType() {
		return vendorLocationType;
	}

	public void setVendorLocationType(String vendorLocationType) {
		this.vendorLocationType = vendorLocationType;
	}

	public String getProprietorName() {
		return proprietorName;
	}

	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}

	public String getRegistrationEmail() {
		return registrationEmail;
	}

	public void setRegistrationEmail(String registrationEmail) {
		this.registrationEmail = registrationEmail;
	}

	public String getTransactionEmail() {
		return transactionEmail;
	}

	public void setTransactionEmail(String transactionEmail) {
		this.transactionEmail = transactionEmail;
	}

	public String getMsmSupplier() {
		return msmSupplier;
	}

	public void setMsmSupplier(String msmSupplier) {
		this.msmSupplier = msmSupplier;
	}

	public String getMsmeCategory() {
		return msmeCategory;
	}

	public void setMsmeCategory(String msmeCategory) {
		this.msmeCategory = msmeCategory;
	}

	public String getMsmeRegistrationNo() {
		return msmeRegistrationNo;
	}

	public void setMsmeRegistrationNo(String msmeRegistrationNo) {
		this.msmeRegistrationNo = msmeRegistrationNo;
	}

	public String getCompanyCategory() {
		return companyCategory;
	}

	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}

	public String getBusinessNature() {
		return businessNature;
	}

	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getVaStartDate() {
		return vaStartDate;
	}

	public void setVaStartDate(Date vaStartDate) {
		this.vaStartDate = vaStartDate;
	}

	public Date getVaEndDate() {
		return vaEndDate;
	}

	public void setVaEndDate(Date vaEndDate) {
		this.vaEndDate = vaEndDate;
	}

	private String addressCode;
    private String addressLine;
    private String city;
    private String stateName;
    private String primaryAddress;
    private String stateCode;
    private String country;
    private Date vaStartDate;
    private Date vaEndDate;
  
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceRefNumber() {
		return sourceRefNumber;
	}

	public void setSourceRefNumber(String sourceRefNumber) {
		this.sourceRefNumber = sourceRefNumber;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}



	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public String getDated() {
		return dated;
	}

	public void setDated(String dated) {
		this.dated = dated;
	}

	public String getPropertyCity() {
		return propertyCity;
	}

	public void setPropertyCity(String propertyCity) {
		this.propertyCity = propertyCity;
	}

	public String getRentFrequency() {
		return rentFrequency;
	}

	public void setRentFrequency(String rentFrequency) {
		this.rentFrequency = rentFrequency;
	}

	public double getBaseRent() {
		return baseRent;
	}

	public void setBaseRent(double baseRent) {
		this.baseRent = baseRent;
	}

	public String getPropertyTitle() {
		return propertyTitle;
	}

	public void setPropertyTitle(String propertyTitle) {
		this.propertyTitle = propertyTitle;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getSdPaymentRef() {
		return sdPaymentRef;
	}

	public void setSdPaymentRef(String sdPaymentRef) {
		this.sdPaymentRef = sdPaymentRef;
	}

	public String getSdPaymentDate() {
		return sdPaymentDate;
	}

	public void setSdPaymentDate(String sdPaymentDate) {
		this.sdPaymentDate = sdPaymentDate;
	}

	public String getRentCurrency() {
		return rentCurrency;
	}

	public void setRentCurrency(String rentCurrency) {
		this.rentCurrency = rentCurrency;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public int getRentDueDay() {
		return rentDueDay;
	}

	public void setRentDueDay(int rentDueDay) {
		this.rentDueDay = rentDueDay;
	}

	public int getLockInPeriod() {
		return lockInPeriod;
	}

	public void setLockInPeriod(int lockInPeriod) {
		this.lockInPeriod = lockInPeriod;
	}

	public String getRentalType() {
		return rentalType;
	}

	public void setRentalType(String rentalType) {
		this.rentalType = rentalType;
	}

	public String getRentFrequencyValue() {
		return rentFrequencyValue;
	}

	public void setRentFrequencyValue(String rentFrequencyValue) {
		this.rentFrequencyValue = rentFrequencyValue;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public double getLineBaseRent() {
		return lineBaseRent;
	}

	public void setLineBaseRent(double lineBaseRent) {
		this.lineBaseRent = lineBaseRent;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}

	// Default Constructor
    public ReportField() {
        super();
    }

    // Getters and setters for all fields
    public String getExpenseNumber() {
        return expenseNumber;
    }

    public String getLineFromDate() {
		return lineFromDate;
	}

	public void setLineFromDate(String lineFromDate) {
		this.lineFromDate = lineFromDate;
	}

	public String getLineToDate() {
		return lineToDate;
	}

	public void setLineToDate(String lineToDate) {
		this.lineToDate = lineToDate;
	}

	public void setExpenseNumber(String expenseNumber) {
        this.expenseNumber = expenseNumber;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getHdrStartDate() {
        return hdrStartDate;
    }

    public void setHdrStartDate(Date hdrStartDate) {
        this.hdrStartDate = hdrStartDate;
    }

    public Date getHdrEndDate() {
        return hdrEndDate;
    }

    public void setHdrEndDate(Date hdrEndDate) {
        this.hdrEndDate = hdrEndDate;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Long divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLineNo() {
        return lineNo;
    }

    public void setLineNo(Long lineNo) {
        this.lineNo = lineNo;
    }

    public Date getLineStartDate() {
        return lineStartDate;
    }

    public void setLineStartDate(Date lineStartDate) {
        this.lineStartDate = lineStartDate;
    }

    public Date getLineEndDate() {
        return lineEndDate;
    }

    public void setLineEndDate(Date lineEndDate) {
        this.lineEndDate = lineEndDate;
    }

    public String getExpenseNature() {
        return expenseNature;
    }

    public void setExpenseNature(String expenseNature) {
        this.expenseNature = expenseNature;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoType() {
        return poType;
    }

    public void setPoType(String poType) {
        this.poType = poType;
    }

    public Long getRevisionNum() {
        return revisionNum;
    }

    public void setRevisionNum(Long revisionNum) {
        this.revisionNum = revisionNum;
    }

    public Date getPoDate() {
        return poDate;
    }

    public void setPoDate(Date poDate) {
        this.poDate = poDate;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoteToApprover() {
        return noteToApprover;
    }

    public void setNoteToApprover(String noteToApprover) {
        this.noteToApprover = noteToApprover;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorContact() {
        return vendorContact;
    }

    public void setVendorContact(String vendorContact) {
        this.vendorContact = vendorContact;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPoCurrency() {
        return poCurrency;
    }

    public void setPoCurrency(String poCurrency) {
        this.poCurrency = poCurrency;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }

    public Double getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(Double lineAmount) {
        this.lineAmount = lineAmount;
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

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInternalInvoiceNumber() {
        return internalInvoiceNumber;
    }

    public void setInternalInvoiceNumber(String internalInvoiceNumber) {
        this.internalInvoiceNumber = internalInvoiceNumber;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceReceivedDate() {
		return invoiceReceivedDate;
	}

	public void setInvoiceReceivedDate(String invoiceReceivedDate) {
		this.invoiceReceivedDate = invoiceReceivedDate;
	}

	public BigDecimal getPoAmount() {
        return poAmount;
    }

    public void setPoAmount(BigDecimal poAmount) {
        this.poAmount = poAmount;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getInvoiceCurrency() {
        return invoiceCurrency;
    }

    public void setInvoiceCurrency(String invoiceCurrency) {
        this.invoiceCurrency = invoiceCurrency;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getInvoiceQty() {
        return invoiceQty;
    }

    public void setInvoiceQty(int invoiceQty) {
        this.invoiceQty = invoiceQty;
    }
    
    public String getRemark() {
		return remark;
	}
    public String getRentEscalationPercentage() {
		return rentEscalationPercentage;
	}

	public void setRentEscalationPercentage(String rentEscalationPercentage) {
		this.rentEscalationPercentage = rentEscalationPercentage;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInvoiceRate() {
		return invoiceRate;
	}

	public void setInvoiceRate(String invoiceRate) {
		this.invoiceRate = invoiceRate;
	}

	public ReportField(String expenseNumber, Date expenseDate, Long employeeId, String employeeName, Date hdrStartDate,
			Date hdrEndDate, Long divisionId, String divisionName, String branchName, Long duration, String status,
			Long lineNo, Date lineStartDate, Date lineEndDate, String expenseNature, String poNumber, String poType,
			Long revisionNum, Date poDate, Date revisionDate, String description, String noteToApprover,
			String vendorName, String vendorAddress, String vendorContact, String buyerName, String billingAddress,
			String poCurrency, String lineType, String productCode, String productName, Long quantity, String taxName,
			Double lineAmount, Double taxAmount, Double totalAmount, String termName, String invoiceNumber,
			String invoiceType, String internalInvoiceNumber, String buyer, BigDecimal poAmount, String invoiceDate,
			String invoiceRate, String invoiceReceivedDate, String address, String division, String initiator,
			String shippingAddress, String invoiceCurrency, String exchangeRate, BigDecimal invoiceAmount,
			BigDecimal baseAmount, String terms, String productDescription, int invoiceQty, String agreementNumber,
			String propertyCity, String rentFrequency, double baseRent, String propertyTitle, String propertyType,
			String state, String propertyAddress, String fromDate, String toDate, String locationCode,
			double securityDeposit, String sdPaymentRef, String sdPaymentDate, String rentCurrency, int noticePeriod,
			int rentDueDay, int lockInPeriod, String rentalType, String rentFrequencyValue, String terminationDate,
			String remark, double lineBaseRent, String rentEscalationPercentage, String lineFromDate, String lineToDate,
			double rentAmount, String panNo, String email, String startDate, String endDate, String approverName,
			String approvalStatus, String dated, String source, String sourceRefNumber, String vendorCode,
			String vendorType, String vendorLocationType, String proprietorName, String registrationEmail,
			String transactionEmail, String msmSupplier, String msmeCategory, String msmeRegistrationNo,
			String companyCategory, String businessNature, String addressCode, String addressLine, String city,
			String stateName, String primaryAddress, String stateCode, String country, Date vaStartDate,
			Date vaEndDate) {
		super();
		this.expenseNumber = expenseNumber;
		this.expenseDate = expenseDate;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.hdrStartDate = hdrStartDate;
		this.hdrEndDate = hdrEndDate;
		this.divisionId = divisionId;
		this.divisionName = divisionName;
		this.branchName = branchName;
		this.duration = duration;
		this.status = status;
		this.lineNo = lineNo;
		this.lineStartDate = lineStartDate;
		this.lineEndDate = lineEndDate;
		this.expenseNature = expenseNature;
		this.poNumber = poNumber;
		this.poType = poType;
		this.revisionNum = revisionNum;
		this.poDate = poDate;
		this.revisionDate = revisionDate;
		this.description = description;
		this.noteToApprover = noteToApprover;
		this.vendorName = vendorName;
		this.vendorAddress = vendorAddress;
		this.vendorContact = vendorContact;
		this.buyerName = buyerName;
		this.billingAddress = billingAddress;
		this.poCurrency = poCurrency;
		this.lineType = lineType;
		this.productCode = productCode;
		this.productName = productName;
		this.quantity = quantity;
		this.taxName = taxName;
		this.lineAmount = lineAmount;
		this.taxAmount = taxAmount;
		this.totalAmount = totalAmount;
		this.termName = termName;
		this.invoiceNumber = invoiceNumber;
		this.invoiceType = invoiceType;
		this.internalInvoiceNumber = internalInvoiceNumber;
		this.buyer = buyer;
		this.poAmount = poAmount;
		this.invoiceDate = invoiceDate;
		this.invoiceRate = invoiceRate;
		this.invoiceReceivedDate = invoiceReceivedDate;
		this.address = address;
		this.division = division;
		this.initiator = initiator;
		this.shippingAddress = shippingAddress;
		this.invoiceCurrency = invoiceCurrency;
		this.exchangeRate = exchangeRate;
		this.invoiceAmount = invoiceAmount;
		this.baseAmount = baseAmount;
		this.terms = terms;
		this.productDescription = productDescription;
		this.invoiceQty = invoiceQty;
		this.agreementNumber = agreementNumber;
		this.propertyCity = propertyCity;
		this.rentFrequency = rentFrequency;
		this.baseRent = baseRent;
		this.propertyTitle = propertyTitle;
		this.propertyType = propertyType;
		this.state = state;
		this.propertyAddress = propertyAddress;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.locationCode = locationCode;
		this.securityDeposit = securityDeposit;
		this.sdPaymentRef = sdPaymentRef;
		this.sdPaymentDate = sdPaymentDate;
		this.rentCurrency = rentCurrency;
		this.noticePeriod = noticePeriod;
		this.rentDueDay = rentDueDay;
		this.lockInPeriod = lockInPeriod;
		this.rentalType = rentalType;
		this.rentFrequencyValue = rentFrequencyValue;
		this.terminationDate = terminationDate;
		this.remark = remark;
		this.lineBaseRent = lineBaseRent;
		this.rentEscalationPercentage = rentEscalationPercentage;
		this.lineFromDate = lineFromDate;
		this.lineToDate = lineToDate;
		this.rentAmount = rentAmount;
		this.panNo = panNo;
		Email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.approverName = approverName;
		this.approvalStatus = approvalStatus;
		this.dated = dated;
		this.source = source;
		this.sourceRefNumber = sourceRefNumber;
		this.vendorCode = vendorCode;
		this.vendorType = vendorType;
		this.vendorLocationType = vendorLocationType;
		this.proprietorName = proprietorName;
		this.registrationEmail = registrationEmail;
		this.transactionEmail = transactionEmail;
		this.msmSupplier = msmSupplier;
		this.msmeCategory = msmeCategory;
		this.msmeRegistrationNo = msmeRegistrationNo;
		this.companyCategory = companyCategory;
		this.businessNature = businessNature;
		this.addressCode = addressCode;
		this.addressLine = addressLine;
		this.city = city;
		this.stateName = stateName;
		this.primaryAddress = primaryAddress;
		this.stateCode = stateCode;
		this.country = country;
		this.vaStartDate = vaStartDate;
		this.vaEndDate = vaEndDate;
	}

	@Override
	public String toString() {
		return "ReportField [expenseNumber=" + expenseNumber + ", expenseDate=" + expenseDate + ", employeeId="
				+ employeeId + ", employeeName=" + employeeName + ", hdrStartDate=" + hdrStartDate + ", hdrEndDate="
				+ hdrEndDate + ", divisionId=" + divisionId + ", divisionName=" + divisionName + ", branchName="
				+ branchName + ", duration=" + duration + ", status=" + status + ", lineNo=" + lineNo
				+ ", lineStartDate=" + lineStartDate + ", lineEndDate=" + lineEndDate + ", expenseNature="
				+ expenseNature + ", poNumber=" + poNumber + ", poType=" + poType + ", revisionNum=" + revisionNum
				+ ", poDate=" + poDate + ", revisionDate=" + revisionDate + ", description=" + description
				+ ", noteToApprover=" + noteToApprover + ", vendorName=" + vendorName + ", vendorAddress="
				+ vendorAddress + ", vendorContact=" + vendorContact + ", buyerName=" + buyerName + ", billingAddress="
				+ billingAddress + ", poCurrency=" + poCurrency + ", lineType=" + lineType + ", productCode="
				+ productCode + ", productName=" + productName + ", quantity=" + quantity + ", taxName=" + taxName
				+ ", lineAmount=" + lineAmount + ", taxAmount=" + taxAmount + ", totalAmount=" + totalAmount
				+ ", termName=" + termName + ", invoiceNumber=" + invoiceNumber + ", invoiceType=" + invoiceType
				+ ", internalInvoiceNumber=" + internalInvoiceNumber + ", buyer=" + buyer + ", poAmount=" + poAmount
				+ ", invoiceDate=" + invoiceDate + ", invoiceRate=" + invoiceRate + ", invoiceReceivedDate="
				+ invoiceReceivedDate + ", address=" + address + ", division=" + division + ", initiator=" + initiator
				+ ", shippingAddress=" + shippingAddress + ", invoiceCurrency=" + invoiceCurrency + ", exchangeRate="
				+ exchangeRate + ", invoiceAmount=" + invoiceAmount + ", baseAmount=" + baseAmount + ", terms=" + terms
				+ ", productDescription=" + productDescription + ", invoiceQty=" + invoiceQty + ", agreementNumber="
				+ agreementNumber + ", propertyCity=" + propertyCity + ", rentFrequency=" + rentFrequency
				+ ", baseRent=" + baseRent + ", propertyTitle=" + propertyTitle + ", propertyType=" + propertyType
				+ ", state=" + state + ", propertyAddress=" + propertyAddress + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", locationCode=" + locationCode + ", securityDeposit=" + securityDeposit + ", sdPaymentRef="
				+ sdPaymentRef + ", sdPaymentDate=" + sdPaymentDate + ", rentCurrency=" + rentCurrency
				+ ", noticePeriod=" + noticePeriod + ", rentDueDay=" + rentDueDay + ", lockInPeriod=" + lockInPeriod
				+ ", rentalType=" + rentalType + ", rentFrequencyValue=" + rentFrequencyValue + ", terminationDate="
				+ terminationDate + ", remark=" + remark + ", lineBaseRent=" + lineBaseRent
				+ ", rentEscalationPercentage=" + rentEscalationPercentage + ", lineFromDate=" + lineFromDate
				+ ", lineToDate=" + lineToDate + ", rentAmount=" + rentAmount + ", panNo=" + panNo + ", Email=" + Email
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", approverName=" + approverName
				+ ", approvalStatus=" + approvalStatus + ", dated=" + dated + ", source=" + source
				+ ", sourceRefNumber=" + sourceRefNumber + ", vendorCode=" + vendorCode + ", vendorType=" + vendorType
				+ ", vendorLocationType=" + vendorLocationType + ", proprietorName=" + proprietorName
				+ ", registrationEmail=" + registrationEmail + ", transactionEmail=" + transactionEmail
				+ ", msmSupplier=" + msmSupplier + ", msmeCategory=" + msmeCategory + ", msmeRegistrationNo="
				+ msmeRegistrationNo + ", companyCategory=" + companyCategory + ", businessNature=" + businessNature
				+ ", addressCode=" + addressCode + ", addressLine=" + addressLine + ", city=" + city + ", stateName="
				+ stateName + ", primaryAddress=" + primaryAddress + ", stateCode=" + stateCode + ", country=" + country
				+ ", vaStartDate=" + vaStartDate + ", vaEndDate=" + vaEndDate + ", getVendorCode()=" + getVendorCode()
				+ ", getVendorType()=" + getVendorType() + ", getVendorLocationType()=" + getVendorLocationType()
				+ ", getProprietorName()=" + getProprietorName() + ", getRegistrationEmail()=" + getRegistrationEmail()
				+ ", getTransactionEmail()=" + getTransactionEmail() + ", getMsmSupplier()=" + getMsmSupplier()
				+ ", getMsmeCategory()=" + getMsmeCategory() + ", getMsmeRegistrationNo()=" + getMsmeRegistrationNo()
				+ ", getCompanyCategory()=" + getCompanyCategory() + ", getBusinessNature()=" + getBusinessNature()
				+ ", getAddressCode()=" + getAddressCode() + ", getAddressLine()=" + getAddressLine() + ", getCity()="
				+ getCity() + ", getStateName()=" + getStateName() + ", getPrimaryAddress()=" + getPrimaryAddress()
				+ ", getStateCode()=" + getStateCode() + ", getCountry()=" + getCountry() + ", getVaStartDate()="
				+ getVaStartDate() + ", getVaEndDate()=" + getVaEndDate() + ", getSource()=" + getSource()
				+ ", getSourceRefNumber()=" + getSourceRefNumber() + ", getPanNo()=" + getPanNo() + ", getEmail()="
				+ getEmail() + ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate()
				+ ", getApproverName()=" + getApproverName() + ", getApprovalStatus()=" + getApprovalStatus()
				+ ", getAgreementNumber()=" + getAgreementNumber() + ", getDated()=" + getDated()
				+ ", getPropertyCity()=" + getPropertyCity() + ", getRentFrequency()=" + getRentFrequency()
				+ ", getBaseRent()=" + getBaseRent() + ", getPropertyTitle()=" + getPropertyTitle()
				+ ", getPropertyType()=" + getPropertyType() + ", getState()=" + getState() + ", getPropertyAddress()="
				+ getPropertyAddress() + ", getFromDate()=" + getFromDate() + ", getToDate()=" + getToDate()
				+ ", getLocationCode()=" + getLocationCode() + ", getSecurityDeposit()=" + getSecurityDeposit()
				+ ", getSdPaymentRef()=" + getSdPaymentRef() + ", getSdPaymentDate()=" + getSdPaymentDate()
				+ ", getRentCurrency()=" + getRentCurrency() + ", getNoticePeriod()=" + getNoticePeriod()
				+ ", getRentDueDay()=" + getRentDueDay() + ", getLockInPeriod()=" + getLockInPeriod()
				+ ", getRentalType()=" + getRentalType() + ", getRentFrequencyValue()=" + getRentFrequencyValue()
				+ ", getTerminationDate()=" + getTerminationDate() + ", getLineBaseRent()=" + getLineBaseRent()
				+ ", getRentAmount()=" + getRentAmount() + ", getExpenseNumber()=" + getExpenseNumber()
				+ ", getLineFromDate()=" + getLineFromDate() + ", getLineToDate()=" + getLineToDate()
				+ ", getExpenseDate()=" + getExpenseDate() + ", getEmployeeId()=" + getEmployeeId()
				+ ", getEmployeeName()=" + getEmployeeName() + ", getHdrStartDate()=" + getHdrStartDate()
				+ ", getHdrEndDate()=" + getHdrEndDate() + ", getDivisionId()=" + getDivisionId()
				+ ", getDivisionName()=" + getDivisionName() + ", getBranchName()=" + getBranchName()
				+ ", getDuration()=" + getDuration() + ", getStatus()=" + getStatus() + ", getLineNo()=" + getLineNo()
				+ ", getLineStartDate()=" + getLineStartDate() + ", getLineEndDate()=" + getLineEndDate()
				+ ", getExpenseNature()=" + getExpenseNature() + ", getPoNumber()=" + getPoNumber() + ", getPoType()="
				+ getPoType() + ", getRevisionNum()=" + getRevisionNum() + ", getPoDate()=" + getPoDate()
				+ ", getRevisionDate()=" + getRevisionDate() + ", getDescription()=" + getDescription()
				+ ", getNoteToApprover()=" + getNoteToApprover() + ", getVendorName()=" + getVendorName()
				+ ", getVendorAddress()=" + getVendorAddress() + ", getVendorContact()=" + getVendorContact()
				+ ", getBuyerName()=" + getBuyerName() + ", getBillingAddress()=" + getBillingAddress()
				+ ", getPoCurrency()=" + getPoCurrency() + ", getLineType()=" + getLineType() + ", getProductCode()="
				+ getProductCode() + ", getProductName()=" + getProductName() + ", getQuantity()=" + getQuantity()
				+ ", getTaxName()=" + getTaxName() + ", getLineAmount()=" + getLineAmount() + ", getTaxAmount()="
				+ getTaxAmount() + ", getTotalAmount()=" + getTotalAmount() + ", getTermName()=" + getTermName()
				+ ", getInvoiceNumber()=" + getInvoiceNumber() + ", getInvoiceType()=" + getInvoiceType()
				+ ", getInternalInvoiceNumber()=" + getInternalInvoiceNumber() + ", getBuyer()=" + getBuyer()
				+ ", getInvoiceDate()=" + getInvoiceDate() + ", getInvoiceReceivedDate()=" + getInvoiceReceivedDate()
				+ ", getPoAmount()=" + getPoAmount() + ", getAddress()=" + getAddress() + ", getDivision()="
				+ getDivision() + ", getInitiator()=" + getInitiator() + ", getShippingAddress()="
				+ getShippingAddress() + ", getInvoiceCurrency()=" + getInvoiceCurrency() + ", getExchangeRate()="
				+ getExchangeRate() + ", getInvoiceAmount()=" + getInvoiceAmount() + ", getBaseAmount()="
				+ getBaseAmount() + ", getTerms()=" + getTerms() + ", getProductDescription()="
				+ getProductDescription() + ", getInvoiceQty()=" + getInvoiceQty() + ", getRemark()=" + getRemark()
				+ ", getInvoiceRate()=" + getInvoiceRate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}



	
}
