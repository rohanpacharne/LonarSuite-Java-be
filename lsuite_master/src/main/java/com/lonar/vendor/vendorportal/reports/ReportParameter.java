package com.lonar.vendor.vendorportal.reports;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
	private String masterName;
	private Long lastUpdateLogin;

  


	public ReportParameter(Long companyId, Long userId, String startDate, String endDate, String employeeId,
			String employeeName, String divisionId, String divisionName, String vendorId, String vendorName,
			String buyerId, String buyerName, String address, String status, String poNumberFrom, String poNumberTo,
			String invoiceNumberFrom, String invoiceNumberTo, String rentalType, String agreementNumber,
			String masterName, Long lastUpdateLogin) {
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
		this.masterName = masterName;
		this.lastUpdateLogin = lastUpdateLogin;
	}


	// ✅ Default Constructor (Required for Jackson)
    public ReportParameter() {}


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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
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

	public String getMasterName() {
		return masterName;
	}


	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}


	public Long getLastUpdateLogin() {
		return lastUpdateLogin;
	}


	public void setLastUpdateLogin(Long lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}


	public void setFilterData(String filterData) throws NoSuchFieldException, IllegalAccessException {
	    if (filterData == null || filterData.isEmpty()) {
	        System.out.println("Warning: filterData is empty or null. No fields will be updated.");
	        return;
	    }

	    int startIdx = filterData.indexOf('[');
	    int endIdx = filterData.lastIndexOf(']');

	    if (startIdx == -1 || endIdx == -1 || startIdx >= endIdx) {
	        System.out.println("Warning: filterData does not contain brackets. Using full string.");
	        startIdx = -1;
	        endIdx = filterData.length();
	    }

	    String extractedData = filterData.substring(startIdx + 1, endIdx).trim();

	    Pattern pattern = Pattern.compile("([^=,]+)=([^,]*)");
	    Matcher matcher = pattern.matcher(extractedData);

	    Map<String, String> fieldValues = new HashMap<>();

	    while (matcher.find()) {
	        String fieldName = matcher.group(1).trim();
	        String fieldValue = matcher.group(2).trim();
	        fieldValues.put(fieldName, fieldValue);
	    }


	    List<String> dateFormats = Arrays.asList(
	        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", 
	        "dd-MM-yyyy" ,                   
	        "yyyy-MM-dd",                   
	        "MM/dd/yyyy",                    
	        "yyyy/MM/dd" 
	       
	    );

	  
	    for (Field field : this.getClass().getDeclaredFields()) {
	        field.setAccessible(true);  
	        String fieldName = field.getName();

	        if (fieldValues.containsKey(fieldName)) {
	            String fieldValue = fieldValues.get(fieldName);

	            try {
	                // Handling for String fields
	                if (field.getType() == String.class) {
	                    if ("startDate".equals(fieldName) || "endDate".equals(fieldName)) {
	                        LocalDate parsedDate = null;

	                        // Try parsing with multiple date formats
	                        for (String format : dateFormats) {
	                            try {
	                                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(format);
	                                parsedDate = LocalDate.parse(fieldValue, inputFormatter);
	                                break;  
	                            } catch (Exception e) {
	                                
	                            }
	                        }

	                        
	                        if (parsedDate != null) {
	                            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	                            field.set(this, parsedDate.format(outputFormatter));  // Set the date in the required format
	                        } else {
	                            System.out.println("Error: Invalid date format for field '" + fieldName + "' with value: " + fieldValue);
	                        }
	                    } else {
	                        field.set(this, fieldValue);  // For non-date string fields
	                    }
	                } 
	           
	                else if (field.getType() == Long.class) {
	                    field.set(this, (fieldValue != null && !fieldValue.equals("null")) ? Long.valueOf(fieldValue) : null);
	                }
	            } catch (Exception e) {
	                System.out.println("Error setting field '" + fieldName + "': " + e.getMessage());
	            }
	        } else {
	       
	            if (field.getType() == Long.class) {
	                field.set(this, null);
	            } else if (field.getType() == String.class) {
	                field.set(this, null);
	            }
	        }
	    }
	}



	@Override
	public String toString() {
		return "ReportParameter {companyId=" + companyId + ", userId=" + userId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", divisionId=" + divisionId + ", divisionName=" + divisionName + ", vendorId=" + vendorId
				+ ", vendorName=" + vendorName + ", buyerId=" + buyerId + ", buyerName=" + buyerName + ", address="
				+ address + ", status=" + status + ", poNumberFrom=" + poNumberFrom + ", poNumberTo=" + poNumberTo
				+ ", invoiceNumberFrom=" + invoiceNumberFrom + ", invoiceNumberTo=" + invoiceNumberTo + ", rentalType="
				+ rentalType + ", agreementNumber=" + agreementNumber + ", masterName=" + masterName
				+ ", lastUpdateLogin=" + lastUpdateLogin + "}";
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
