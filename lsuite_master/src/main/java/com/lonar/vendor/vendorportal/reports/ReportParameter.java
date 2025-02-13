package com.lonar.vendor.vendorportal.reports;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportParameter {

    private Long companyId;
    private Long userId;
    private String startDate;
    private String endDate;
    private String employeeId;
    private String employee;
    private String divisionId;
    private String division;
    private String vendorId;
    private String vendorName;
    private String buyerId;
    private String buyerName;
    private String address;
    private String status;
    private String poNumberFrom;
    private String poNumberTo;
    private String invoiceNumberFrom;
    private String invoiceNumberTo;
    private String rentalType;
    private String agreementNumber;
  
  

	public ReportParameter(Long companyId, Long userId, String startDate, String endDate, String employeeId,
			String employee, String divisionId, String division, String vendorId, String vendorName, String buyerId,
			String buyerName, String address, String status, String poNumberFrom, String poNumberTo,
			String invoiceNumberFrom, String invoiceNumberTo, String rentalType, String agreementNumber) {
		super();
		this.companyId = companyId;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employeeId = employeeId;
		this.employee = employee;
		this.divisionId = divisionId;
		this.division = division;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.address = address;
		this.status = status;
		this.poNumberFrom = poNumberFrom;
		this.poNumberTo = poNumberTo;
		this.invoiceNumberFrom = invoiceNumberFrom;
		this.invoiceNumberTo = invoiceNumberTo;
		this.rentalType = rentalType;
		this.agreementNumber = agreementNumber;
	}

	// ✅ Default Constructor (Required for Jackson)
    public ReportParameter() {}

    // ✅ Getters and Setters
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

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }
    
    public String getEmployee() { return employee; }
    public void setEmployee(String employee) { this.employee = employee; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }
    

    public void setFilterData(String filterData) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        if (filterData == null || filterData.isEmpty()) {
            return; // Avoid processing null or empty input
        }

        filterData = filterData.substring(filterData.indexOf('[') + 1, filterData.lastIndexOf(']')); // Extract key-value pairs
        String[] properties = filterData.split(",");

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (String item : properties) {
            String[] keyValue = item.split("=");

            if (keyValue.length != 2) {
                continue; // Skip if key=value pair is not valid
            }

            String fieldName = keyValue[0].trim();
            String fieldValue = keyValue[1].trim();

            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true); // Allow modification of private fields

            // Convert types correctly
            if (field.getType() == Long.class) {
                field.set(this, Long.valueOf(fieldValue));
            } else if (field.getType() == String.class && (fieldName.equals("startDate") || fieldName.equals("endDate"))) {
                // Convert ISO Date format to "yyyy-MM-dd"
                LocalDate localDate = LocalDate.parse(fieldValue, inputFormatter);
                field.set(this, localDate.format(outputFormatter));
            } else {
                field.set(this, fieldValue);
            }
        }
    }

    
  
   

    // ✅ Override toString() for Debugging
    @Override
    public String toString() {
    	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Correct format
    	    String formattedStartDate = (startDate != null) ? dateFormat.format(startDate) : "null";
    	    String formattedEndDate = (endDate != null) ? dateFormat.format(endDate) : "null";
        return "ReportParameter{" +
                "companyId=" + companyId +
                ", userId=" + userId +
                ", startDate='" +  formattedStartDate  + '\'' +
                ", endDate='" + formattedEndDate + '\'' +
                ", employeeId='" + employeeId + '\'' +
                 ", employeeName='" + employee + '\'' +
                ", divisionId='" + divisionId + '\'' +
                ", divisionName='" + division + '\'' +
                ", vendorId='" + vendorId + '\'' +
                 ", vendorName='" + vendorName + '\'' +
                 ", buyerId='" + buyerId + '\'' +
                 ", buyerName='" + buyerName + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", poNumberFrom='" + poNumberFrom + '\'' +
                ", poNumberTo='" + poNumberTo + '\'' +
                ", invoiceNumberFrom='" + invoiceNumberFrom + '\'' +
                ", invoiceNumberTo='" + invoiceNumberTo + '\'' +
                ", rentalType='" + rentalType + '\'' +
                ", agreementNumber='" + agreementNumber + '\'' +
                '}';
    }
//    @Override
//    public String toString() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        String formattedStartDate = (startDate != null) ? 
//            convertDateToLocalDate(startDate).format(formatter) : null;
//
//        String formattedEndDate = (endDate != null) ? 
//            convertDateToLocalDate(endDate).format(formatter) : "null";
//
//        return "ReportParameter{" +
//                "companyId=" + companyId +
//                ", userId=" + userId +
//                ", startDate='" + formattedStartDate + '\'' +
//                ", endDate='" + formattedEndDate + '\'' +
//                ", employeeId='" + employeeId + '\'' +
//                ", divisionId='" + divisionId + '\'' +
//                ", employeeName='" + employee + '\'' +
//                ", divisionName='" + division + '\'' +
//                ", vendorId='" + vendorId + '\'' +
//                ", vendorName='" + vendorName + '\'' +
//                ", buyerName='" + buyerName + '\'' +
//                ", address='" + address + '\'' +
//                ", status='" + status + '\'' +
//                ", buyerId='" + buyerId + '\'' +
//                ", poNumberFrom='" + poNumberFrom + '\'' +
//                ", poNumberTo='" + poNumberTo + '\'' +
//                ", invoiceNumberFrom='" + invoiceNumberFrom + '\'' +
//                ", invoiceNumberTo='" + invoiceNumberTo + '\'' +
//                ", rentalType='" + rentalType + '\'' +
//                ", agreementNumber='" + agreementNumber + '\'' +
//                '}';
//    }
//
//    private LocalDate convertDateToLocalDate(Date date) {
//        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//    }
}
