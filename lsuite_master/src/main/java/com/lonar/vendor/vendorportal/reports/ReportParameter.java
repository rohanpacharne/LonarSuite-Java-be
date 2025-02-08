package com.lonar.vendor.vendorportal.reports;
 
public class ReportParameter {
    private Long companyId;
    private Long userId;
    private String startDate;
    private String endDate;
    private String employeeId;
    private String divisionId;
    private String employee;
    private String division;
    private String vendorId;       // Vendor name filter
    private String address;      // Vendor address filter
    private String status;       // PO Status filter
    private String buyerId;        // Buyer name filter
    private String poNumberFrom; // PO Number From filter
    private String poNumberTo;   // PO Number To filter
    private String invoiceNumberFrom;
    private String invoiceNumberTo;
    private String rentalType;
    private String agreementNumber;
    
    
    public String getAgreementNumber() {
		return agreementNumber;
	}
	public String getRentalType() {
		return rentalType;
	}
	public void setRentalType(String rentalType) {
		this.rentalType = rentalType;
	}
	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getPoNumberFrom() {
		return poNumberFrom;
	}
	public void setPoNumberFrom(String poNumberFrom) {
		this.poNumberFrom = poNumberFrom;
	}
	public String getPoNumberTo() {
		return poNumberTo;
	}
	public void setPoNumberTo(String poNumberTo) {
		this.poNumberTo = poNumberTo;
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
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getEmployeeId() {
		return employeeId;
	}
 
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
 
	public String getInvoiceNumberFrom() {
		return invoiceNumberFrom;
	}
	public void setInvoiceNumberFrom(String invoiceNumberFrom) {
		this.invoiceNumberFrom = invoiceNumberFrom;
	}
	public String getInvoiceNumberTo() {
		return invoiceNumberTo;
	}
	public void setInvoiceNumberTo(String invoiceNumberTo) {
		this.invoiceNumberTo = invoiceNumberTo;
	}
	@Override
	public String toString() {
		return "ReportParameter [companyId=" + companyId + ", userId=" + userId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", employeeId=" + employeeId + ", divisionId=" + divisionId + ", employee="
				+ employee + ", division=" + division + ", vendorId=" + vendorId + ", address=" + address + ", status="
				+ status + ", buyerId=" + buyerId + ", poNumberFrom=" + poNumberFrom + ", poNumberTo=" + poNumberTo
				+ ", invoiceNumberFrom=" + invoiceNumberFrom + ", invoiceNumberTo=" + invoiceNumberTo + ", rentalType="
				+ rentalType + ", agreementNumber=" + agreementNumber + "]";
	}
 
	
}
 