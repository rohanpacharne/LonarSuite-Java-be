package com.lonar.vendor.vendorportal.reports;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private String employeeName;
    private String divisionId;
    private String divisionName;
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
  
  

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public ReportParameter(Long companyId, Long userId, String startDate, String endDate, String employeeId,
			String employeeName, String divisionId, String divisionName, String vendorId, String vendorName, String buyerId,
			String buyerName, String address, String status, String poNumberFrom, String poNumberTo,
			String invoiceNumberFrom, String invoiceNumberTo, String rentalType, String agreementNumber) {
		super();
		this.companyId = companyId;
		this.userId = userId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.divisionId = divisionId;
		this.divisionName = divisionName;
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
    

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }
    

    public void setFilterData(String filterData) throws NoSuchFieldException, IllegalAccessException {
        if (filterData == null || filterData.isEmpty()) {
            System.out.println("Warning: filterData is empty or null. No fields will be updated.");
            return;
        }

        // If filterData does not contain brackets, use entire string
        int startIdx = filterData.indexOf('[');
        int endIdx = filterData.lastIndexOf(']');

        if (startIdx == -1 || endIdx == -1 || startIdx >= endIdx) {
            System.out.println("Warning: filterData does not contain brackets. Using full string.");
            startIdx = -1; // Start from the beginning
            endIdx = filterData.length(); // Use full string
        }

        String extractedData = filterData.substring(startIdx + 1, endIdx).trim();

        // Regex pattern to correctly split key=value pairs (allows missing values)
        Pattern pattern = Pattern.compile("([^=,]+)=([^,]*)");
        Matcher matcher = pattern.matcher(extractedData);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Store parsed key-value pairs
        Map<String, String> fieldValues = new HashMap<>();

        while (matcher.find()) {
            String fieldName = matcher.group(1).trim();
            String fieldValue = matcher.group(2).trim();
            fieldValues.put(fieldName, fieldValue.isEmpty() ? null : fieldValue);
        }

        // Iterate over declared fields and set values
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();

            if (fieldValues.containsKey(fieldName)) {
                String fieldValue = fieldValues.get(fieldName);

                try {
                    if (field.getType() == Long.class) {
                        field.set(this, (fieldValue != null) ? Long.valueOf(fieldValue) : null);
                    } else if (field.getType() == String.class && (fieldName.equals("startDate") || fieldName.equals("endDate"))) {
                        if (fieldValue != null) {
                            try {
                                LocalDate localDate = LocalDate.parse(fieldValue, inputFormatter);
                                field.set(this, localDate.format(outputFormatter));
                            } catch (Exception e) {
                                System.out.println("Warning: Invalid date format for field: " + fieldName);
                            }
                        } else {
                            field.set(this, null);
                        }
                    } else {
                        field.set(this, fieldValue);
                    }
                } catch (Exception e) {
                    System.out.println("Error setting field '" + fieldName + "': " + e.getMessage());
                }
            } else {
                // If field is missing, set default null
                if (field.getType() == Long.class) {
                    field.set(this, null);
                } else if (field.getType() == String.class) {
                    field.set(this, null);
                }
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
                 ", employeeName='" + employeeName + '\'' +
                ", divisionId='" + divisionId + '\'' +
                ", divisionName='" + divisionName + '\'' +
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
