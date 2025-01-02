package com.lonar.vendor.vendorportal.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
 
@Entity
@Table(name = "LT_EXP_EXPENSE_HEADERS")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class LtExpExpenseHeaders extends WhoColumns implements Serializable
{
 
	//private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	//@NotNull
	//@Size(min = 1, max = 22)
	//@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expHeader_seq")
//	@SequenceGenerator(name = "expHeader_seq", sequenceName = "lt_exp_expense_headers_s", allocationSize = 1)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Exp_Header_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expHeaderId;
	
	//@Basic(optional = false)
	//@NotNull
	//@Size( max = 80)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "Expense_Number")
	private String expenseNumber;
	
	//@Basic(optional = false)
	//@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Expense_Submission_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expenseSubmissionDate;
	
	//@Basic(optional = false)
	//@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Employee_id")
	private Long employeeId;
	
	//@Basic(optional = false)
	//@NotNull
	@Size( max = 10, message = "Currancy should not be null")
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "Currency_Code")
	private String currencyCode;
	
	//@Basic(optional = false)
	//@Size( max = 22)
	//@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Exchange_Rate")
	private Double exchangeRate;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Expense_Amount")
	private Double expenseAmount;
	
	//@Basic(optional = false)
	//@Size( max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Division_id")
	private Long divisionId;
	
	//@Basic(optional = false)
	//@Size( max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Location_Id")
	private Long locationId;
	
	//@Basic(optional = false)
	//@Size( max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Cost_Center_id")
	private Long costCenterId;
	
//	@JsonView(DataTablesOutput.View.class)
//	@Column(name = "DEPT_ID")
//	private String deptId;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "EXPENSE_CATEGORY")
	private String expenseCategory;
	
	//@Basic(optional = false)
	//@NotNull
	//@Size( max = 4000)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "Purpose")
	private String purpose;
	
	//@Basic(optional = false)
	//@Size( max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Vendor_ID")
	private Long vendorId;
	
	//@Basic(optional = false)
	//@Size( max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Vendor_Add_ID")
	private Long vendorAddId;
	
	//@Basic(optional = false)
	//@Size( max = 150)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Request_id")
	private String requestId;
	
	//@Basic(optional = false)
	@Size (max = 150)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Source")
	private String source;
	
	///@Basic(optional = false)
	@Size( max = 150)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Source_Ref_No")
	private String sourceRefNo;
	
	//@Basic(optional = false)
	@Size( max = 150)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Invoice_Num")
	private String invoiceNum;
	
	//@Basic(optional = false)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Invoice_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date invoiceDate;
	
	//@Basic(optional = false)
	@Size( max = 150)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Voucher_Num")
	private String voucherNum;
	
	//@Basic(optional = false)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Voucher_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date voucherDate;
	
//	@Basic(optional = false)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Approved_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;
	
//	@Basic(optional = false)
	//@Size( max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Set_of_books_id")
	private Long setOfBooksId;
	
	//@Basic(optional = false)
	//@Size( max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Org_id")
	private Long orgId;
	
	//@Basic(optional = false)
	@Size( max = 30)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Status")
	private String status;
	
	//@Basic(optional = false)
	@Size( max = 2000)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Status_Message")
	private String statusMessage;
	
	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	
	//@Size( max = 2000)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Initiator_id")
	private long initiatorId;
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String locationCode;
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String divisionName;
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String costCenterName;
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String locationName;
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String employeeName;
	
	@Transient
	private String vendorName;
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String expenseStatus;
	
	@Transient
	private String email;
	@Transient
	private String deptName;
	@Transient
	private String stDate;
	@Transient
	private String enDate;
	@Transient
	private String subDate;
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
	private String valueCode;
	
	@Transient
	private String valueName;
	
	

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getStDate() {
		return stDate;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
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
 
	public String getSubDate() {
		return subDate;
	}
 
	public void setSubDate(String subDate) {
		this.subDate = subDate;
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
 
	public String getExpenseStatus() {
		return expenseStatus;
	}
 
	public void setExpenseStatus(String expenseStatus) {
		this.expenseStatus = expenseStatus;
	}
 
	public Double getExpenseAmount() {
		return expenseAmount;
	}
 
	public void setExpenseAmount(Double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
 
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String currency;
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String currencyCodeWithName;
	
	
	
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String vendorAddr;
	
	public LtExpExpenseHeaders()
	{
		super();
	}
	
	public long getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(long initiatorId) {
		this.initiatorId = initiatorId;
	}

	public Long getExpHeaderId() {
		return expHeaderId;
	}
 
	public void setExpHeaderId(Long expHeaderId) {
		this.expHeaderId = expHeaderId;
	}
 
	public String getExpenseNumber() {
		return expenseNumber;
	}
 
	public void setExpenseNumber(String expenseNumber) {
		this.expenseNumber = expenseNumber;
	}
 
	public Date getExpenseSubmissionDate() {
		return expenseSubmissionDate;
	}
 
	public void setExpenseSubmissionDate(Date expenseSubmissionDate) {
		this.expenseSubmissionDate = expenseSubmissionDate;
	}
 
	public Long getEmployeeId() {
		return employeeId;
	}
 
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
 
	public String getCurrencyCode() {
		return currencyCode;
	}
 
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
 
	public Double getExchangeRate() {
		return exchangeRate;
	}
 
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
 
	public Long getDivisionId() {
		return divisionId;
	}
 
	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}
 
	public Long getLocationId() {
		return locationId;
	}
 
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
 
	public Long getCostCenterId() {
		return costCenterId;
	}
 
	public void setCostCenterId(Long costCenterId) {
		this.costCenterId = costCenterId;
	}
 
	public String getPurpose() {
		return purpose;
	}
 
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
 
	public Long getVendorId() {
		return vendorId;
	}
 
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
 
	public Long getVendorAddId() {
		return vendorAddId;
	}
 
	public void setVendorAddId(Long vendorAddId) {
		this.vendorAddId = vendorAddId;
	}
 
	public String getRequestId() {
		return requestId;
	}
 
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
 
	public String getSource() {
		return source;
	}
 
	public void setSource(String source) {
		this.source = source;
	}
 
	public String getSourceRefNo() {
		return sourceRefNo;
	}
 
	public void setSourceRefNo(String sourceRefNo) {
		this.sourceRefNo = sourceRefNo;
	}
 
	public String getInvoiceNum() {
		return invoiceNum;
	}
 
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
 
	public Date getInvoiceDate() {
		return invoiceDate;
	}
 
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
 
	public String getVoucherNum() {
		return voucherNum;
	}
 
	public void setVoucherNum(String voucherNum) {
		this.voucherNum = voucherNum;
	}
 
	public Date getVoucherDate() {
		return voucherDate;
	}
 
	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}
 
	public Date getApprovedDate() {
		return approvedDate;
	}
 
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
 
	public Long getSetOfBooksId() {
		return setOfBooksId;
	}
 
	public void setSetOfBooksId(Long setOfBooksId) {
		this.setOfBooksId = setOfBooksId;
	}
 
	public Long getOrgId() {
		return orgId;
	}
 
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
 
	public String getStatusMessage() {
		return statusMessage;
	}
 
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
 
	public String getLocationCode() {
		return locationCode;
	}
 
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
 
	public String getDivisionName() {
		return divisionName;
	}
 
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
 
	public String getCostCenterName() {
		return costCenterName;
	}
 
	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}
 
	public String getLocationName() {
		return locationName;
	}
 
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
 
	public String getEmployeeName() {
		return employeeName;
	}
 
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
 
	public String getCurrency() {
		return currency;
	}
 
	public void setCurrency(String currency) {
		this.currency = currency;
	}
 
	public String getCurrencyCodeWithName() {
		return currencyCodeWithName;
	}
 
	public void setCurrencyCodeWithName(String currencyCodeWithName) {
		this.currencyCodeWithName = currencyCodeWithName;
	}
 
	public String getVendorName() {
		return vendorName;
	}
 
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
 
	public String getVendorAddr() {
		return vendorAddr;
	}
 
	public void setVendorAddr(String vendorAddr) {
		this.vendorAddr = vendorAddr;
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
 
//	public String getDeptId() {
//		return deptId;
//	}
// 
//	public void setDeptId(String deptId) {
//		this.deptId = deptId;
//	}
 
	
 
	public String getExpenseCategory() {
		return expenseCategory;
	}
 
	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
 
	
	public String getDeptName() {
		return deptName;
	}
 
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	@Override
	public String toString() {
		return "LtExpExpenseHeaders [expHeaderId=" + expHeaderId + ", expenseNumber=" + expenseNumber
				+ ", expenseSubmissionDate=" + expenseSubmissionDate + ", employeeId=" + employeeId + ", currencyCode="
				+ currencyCode + ", exchangeRate=" + exchangeRate + ", expenseAmount=" + expenseAmount + ", divisionId="
				+ divisionId + ", locationId=" + locationId + ", costCenterId=" + costCenterId + ", expenseCategory="
				+ expenseCategory + ", purpose=" + purpose + ", vendorId=" + vendorId + ", vendorAddId=" + vendorAddId
				+ ", requestId=" + requestId + ", source=" + source + ", sourceRefNo=" + sourceRefNo + ", invoiceNum="
				+ invoiceNum + ", invoiceDate=" + invoiceDate + ", voucherNum=" + voucherNum + ", voucherDate="
				+ voucherDate + ", approvedDate=" + approvedDate + ", setOfBooksId=" + setOfBooksId + ", orgId=" + orgId
				+ ", status=" + status + ", statusMessage=" + statusMessage + ", companyId=" + companyId
				+ ", initiatorId=" + initiatorId + ", locationCode=" + locationCode + ", divisionName=" + divisionName
				+ ", costCenterName=" + costCenterName + ", locationName=" + locationName + ", employeeName="
				+ employeeName + ", vendorName=" + vendorName + ", expenseStatus=" + expenseStatus + ", email=" + email
				+ ", deptName=" + deptName + ", stDate=" + stDate + ", enDate=" + enDate + ", subDate=" + subDate
				+ ", draw=" + draw + ", start=" + start + ", length=" + length + ", columnNo=" + columnNo + ", sort="
				+ sort + ", valueCode=" + valueCode + ", valueName=" + valueName + ", currency=" + currency
				+ ", currencyCodeWithName=" + currencyCodeWithName + ", vendorAddr=" + vendorAddr + "]";
	}

	
	
}
