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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
 
@Entity
@Table(name = "LT_EXP_EXPENSE_LINES")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class LtExpExpenseLines extends WhoColumns implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Id
	@Basic(optional = false)
	// @NotNull
	// @Size(min = 1, max = 22)
	//@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expLine_seq")
//	@SequenceGenerator(name = "expLine_seq", sequenceName = "lt_exp_expense_lines_s", allocationSize = 1)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "EXP_LINE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expLineId;
 
	// @Basic(optional = false)
	// @Size(min = 1, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "EXP_HEADER_ID")
	private Long expHeaderId;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "LINE_NO")
	private Long lineNo;
 
	
	@Column(name = "EXPENSE_TYPE_ID")
	private Long expenseTypeId;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "RECEIPT_AMOUNT")
	private Double receiptAmount;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 10)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CURRENCY_CODE")
	private String currencyCode;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "EXCHANGE_RATE")
	private Double exchangeRate;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "AMOUNT")
	private Double amount;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 4000)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "JUSTIFICATION")
	private String justification;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "PROJECT_ID")
	private Long projectId;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "TASK_ID")
	private Long taskId;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 80)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "TRAVEL_TYPE")
	private String travelType;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 80)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "TICKET_CLASS_CODE")
	private String ticketClassCode;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 80)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "TICKET_NUMBER")
	private String ticketNumber;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 80)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "FLIGHT_NUMBER")
	private String flightNumber;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 22)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "NUMBER_ATTENDEES")
	private Long numberAttendees;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 500)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "ATTENDEES")
	private String attendees;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 30)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "STATUS")
	private String status;
 
	// @Basic(optional = false)
	// @Size(min = 0, max = 200)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "STATUS_MESSAGE")
	private String statusMessage;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "MODE_OF_TRAVEL")
	private String modeOfTravel;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "PAID_BY_COMPANY")
	private String paidByCompany;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "HOTEL_NAME")
	private String hotelName;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "HOTEL_NO_OF_DAYS")
	private Long hotelNoOfDays;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "HOTEL_RATE")
	private Double hotelRate;
 
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "HOTEL_BILL_NUMBER")
	private String hotelBillNumber;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "FOOD_NAME_OF_HOTEL")
	private String foodNameOfHotel;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "FOOD_HOTEL_BILL_NUMBER")
	private String foodHotelBillNumber;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "PURPOSE")
	private String purpose;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "MISSING_BILL")
	private String missingBill;
 
	@JsonView(DataTablesOutput.View.class)
	@Transient
	private String currencyCodeWithName;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT1")
	private String segment1;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT2")
	private String segment2;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT3")
	private String segment3;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT4")
	private String segment4;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT5")
	private String segment5;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT6")
	private String segment6;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT7")
	private String segment7;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT8")
	private String segment8;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT9")
	private String segment9;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT10")
	private String segment10;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT11")
	private String segment11;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT12")
	private String segment12;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT13")
	private String segment13;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT14")
	private String segment14;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "SEGMENT15")
	private String segment15;
	
	@Column(name = "TOTAL_DISTANCE_TRAVELED")
	private Double totalDistanceTraveled;
	
	@Column(name = "FUEL_COST_PER_KM")
	private Double fuelCostPerKm;
	
	@Column(name = "MAINTENANCE_PER_KM")
	private Double maintenancePerKm;
	
	@Column(name = "TOTAL_FUEL_COST")
	private Double totalFuelCost;
	
	@Column(name = "TOTAL_MAINTENANCE_COST")
	private Double totalMaintenanceCost;
	
	@Column(name = "TOLL_CHARGES")
	private Double tollCharges;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Seat_Preference")
	private String seatPreference;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Carrier")
	private String carrier;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "From_City")
	private String fromCity;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "To_City")
	private String toCity;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Food_Preference")
	private String foodPreference;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Departure_Time")
	private Date departureTime;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "Arrival_Time")
	private Date arrivalTime;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "BOOKING_TYPE")
	private String bookingType;
	
	@Column(name = "EXPENSE_ATTACHMENT_ID")
	private Long expenseFileUploadId;
	
	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	@Transient
	private String projectName;
	
	@Transient
	private String expenseTypeCode;
	
	@Transient
	private String expenseTypePdf;
	
	@Transient
	private String expenseNature;
	
	@Transient
	private String travelTypeName;
	
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
	private Long rnum;
	
	@Transient
	private String receptAmountStr;
	
	@Transient
	private int columnNo;
	
	@Transient
	private String sort;
 
	@Transient
	private String name;
 
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "expense_bill_number")
	private String expenseBillNumber;
	
	@Transient
	private String filePath;
	
	@Transient
	private String attachmentType;
	
	
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Long getExpLineId() {
		return expLineId;
	}
 
	public void setExpLineId(Long expLineId) {
		this.expLineId = expLineId;
	}
 
	public Long getExpHeaderId() {
		return expHeaderId;
	}
 
	public void setExpHeaderId(Long expHeaderId) {
		this.expHeaderId = expHeaderId;
	}
 
	public Long getLineNo() {
		return lineNo;
	}
 
	public void setLineNo(Long lineNo) {
		this.lineNo = lineNo;
	}
 
	public Long getExpenseTypeId() {
		return expenseTypeId;
	}
 
	public void setExpenseTypeId(Long expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}
 
	public Double getReceiptAmount() {
		return receiptAmount;
	}
 
	public void setReceiptAmount(Double receiptAmount) {
		this.receiptAmount = receiptAmount;
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
 
	public Double getAmount() {
		return amount;
	}
 
	public void setAmount(Double amount) {
		this.amount = amount;
	}
 
	public String getJustification() {
		return justification;
	}
 
	public void setJustification(String justification) {
		this.justification = justification;
	}
 
	public Long getProjectId() {
		return projectId;
	}
 
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
 
	public Long getTaskId() {
		return taskId;
	}
 
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
 
	public String getTravelType() {
		return travelType;
	}
 
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
 
	public String getTicketClassCode() {
		return ticketClassCode;
	}
 
	public void setTicketClassCode(String ticketClassCode) {
		this.ticketClassCode = ticketClassCode;
	}
 
	public String getTicketNumber() {
		return ticketNumber;
	}
 
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
 
	public String getFlightNumber() {
		return flightNumber;
	}
 
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
 
	public Long getNumberAttendees() {
		return numberAttendees;
	}
 
	public void setNumberAttendees(Long numberAttendees) {
		this.numberAttendees = numberAttendees;
	}
 
	public String getAttendees() {
		return attendees;
	}
 
	public void setAttendees(String attendees) {
		this.attendees = attendees;
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
 
	public String getModeOfTravel() {
		return modeOfTravel;
	}
 
	public void setModeOfTravel(String modeOfTravel) {
		this.modeOfTravel = modeOfTravel;
	}
 
	public String getPaidByCompany() {
		return paidByCompany;
	}
 
	public void setPaidByCompany(String paidByCompany) {
		this.paidByCompany = paidByCompany;
	}
 
	public String getHotelName() {
		return hotelName;
	}
 
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
 
	public Long getHotelNoOfDays() {
		return hotelNoOfDays;
	}
 
	public void setHotelNoOfDays(Long hotelNoOfDays) {
		this.hotelNoOfDays = hotelNoOfDays;
	}
 
	public Double getHotelRate() {
		return hotelRate;
	}
 
	public void setHotelRate(Double hotelRate) {
		this.hotelRate = hotelRate;
	}
 
	public String getHotelBillNumber() {
		return hotelBillNumber;
	}
 
	public void setHotelBillNumber(String hotelBillNumber) {
		this.hotelBillNumber = hotelBillNumber;
	}
 
	public String getFoodNameOfHotel() {
		return foodNameOfHotel;
	}
 
	public void setFoodNameOfHotel(String foodNameOfHotel) {
		this.foodNameOfHotel = foodNameOfHotel;
	}
 
	public String getFoodHotelBillNumber() {
		return foodHotelBillNumber;
	}
 
	public void setFoodHotelBillNumber(String foodHotelBillNumber) {
		this.foodHotelBillNumber = foodHotelBillNumber;
	}
 
	public String getPurpose() {
		return purpose;
	}
 
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
 
	public String getMissingBill() {
		return missingBill;
	}
 
	public void setMissingBill(String missingBill) {
		this.missingBill = missingBill;
	}
 
	public String getCurrencyCodeWithName() {
		return currencyCodeWithName;
	}
 
	public void setCurrencyCodeWithName(String currencyCodeWithName) {
		this.currencyCodeWithName = currencyCodeWithName;
	}
 
	public String getSegment1() {
		return segment1;
	}
 
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
 
	public String getSegment2() {
		return segment2;
	}
 
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
 
	public String getSegment3() {
		return segment3;
	}
 
	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}
 
	public String getSegment4() {
		return segment4;
	}
 
	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}
 
	public String getSegment5() {
		return segment5;
	}
 
	public void setSegment5(String segment5) {
		this.segment5 = segment5;
	}
 
	public String getSegment6() {
		return segment6;
	}
 
	public void setSegment6(String segment6) {
		this.segment6 = segment6;
	}
 
	public String getSegment7() {
		return segment7;
	}
 
	public void setSegment7(String segment7) {
		this.segment7 = segment7;
	}
 
	public String getSegment8() {
		return segment8;
	}
 
	public void setSegment8(String segment8) {
		this.segment8 = segment8;
	}
 
	public String getSegment9() {
		return segment9;
	}
 
	public void setSegment9(String segment9) {
		this.segment9 = segment9;
	}
 
	public String getSegment10() {
		return segment10;
	}
 
	public void setSegment10(String segment10) {
		this.segment10 = segment10;
	}
 
	public String getSegment11() {
		return segment11;
	}
 
	public void setSegment11(String segment11) {
		this.segment11 = segment11;
	}
 
	public String getSegment12() {
		return segment12;
	}
 
	public void setSegment12(String segment12) {
		this.segment12 = segment12;
	}
 
	public String getSegment13() {
		return segment13;
	}
 
	public void setSegment13(String segment13) {
		this.segment13 = segment13;
	}
 
	public String getSegment14() {
		return segment14;
	}
 
	public void setSegment14(String segment14) {
		this.segment14 = segment14;
	}
 
	public String getSegment15() {
		return segment15;
	}
 
	public void setSegment15(String segment15) {
		this.segment15 = segment15;
	}
 
	public Double getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}
 
	public void setTotalDistanceTraveled(Double totalDistanceTraveled) {
		this.totalDistanceTraveled = totalDistanceTraveled;
	}
 
	public Double getFuelCostPerKm() {
		return fuelCostPerKm;
	}
 
	public void setFuelCostPerKm(Double fuelCostPerKm) {
		this.fuelCostPerKm = fuelCostPerKm;
	}
 
	public Double getMaintenancePerKm() {
		return maintenancePerKm;
	}
 
	public void setMaintenancePerKm(Double maintenancePerKm) {
		this.maintenancePerKm = maintenancePerKm;
	}
 
	public Double getTotalFuelCost() {
		return totalFuelCost;
	}
 
	public void setTotalFuelCost(Double totalFuelCost) {
		this.totalFuelCost = totalFuelCost;
	}
 
	public Double getTotalMaintenanceCost() {
		return totalMaintenanceCost;
	}
 
	public void setTotalMaintenanceCost(Double totalMaintenanceCost) {
		this.totalMaintenanceCost = totalMaintenanceCost;
	}
 
	public Double getTollCharges() {
		return tollCharges;
	}
 
	public void setTollCharges(Double tollCharges) {
		this.tollCharges = tollCharges;
	}
 
	public String getSeatPreference() {
		return seatPreference;
	}
 
	public void setSeatPreference(String seatPreference) {
		this.seatPreference = seatPreference;
	}
 
	public String getCarrier() {
		return carrier;
	}
 
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
 
	public String getFromCity() {
		return fromCity;
	}
 
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
 
	public String getToCity() {
		return toCity;
	}
 
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
 
	public String getFoodPreference() {
		return foodPreference;
	}
 
	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}
 
	public Date getDepartureTime() {
		return departureTime;
	}
 
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
 
	public Date getArrivalTime() {
		return arrivalTime;
	}
 
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
 
	public String getBookingType() {
		return bookingType;
	}
 
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
 
	public String getExpenseTypeCode() {
		return expenseTypeCode;
	}
 
	public void setExpenseTypeCode(String expenseTypeCode) {
		this.expenseTypeCode = expenseTypeCode;
	}
 
	public String getExpenseTypePdf() {
		return expenseTypePdf;
	}
 
	public void setExpenseTypePdf(String expenseTypePdf) {
		this.expenseTypePdf = expenseTypePdf;
	}
 
	public String getExpenseNature() {
		return expenseNature;
	}
 
	public void setExpenseNature(String expenseNature) {
		this.expenseNature = expenseNature;
	}
 
	public String getTravelTypeName() {
		return travelTypeName;
	}
 
	public void setTravelTypeName(String travelTypeName) {
		this.travelTypeName = travelTypeName;
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
 
	public String getReceptAmountStr() {
		return receptAmountStr;
	}
 
	public void setReceptAmountStr(String receptAmountStr) {
		this.receptAmountStr = receptAmountStr;
	}
 
	
	public Long getExpenseFileUploadId() {
		return expenseFileUploadId;
	}
 
	public void setExpenseFileUploadId(Long expenseFileUploadId) {
		this.expenseFileUploadId = expenseFileUploadId;
	}
 
	public Long getRnum() {
		return rnum;
	}
 
	public void setRnum(Long rnum) {
		this.rnum = rnum;
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
 
	
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getExpenseBillNumber() {
		return expenseBillNumber;
	}
 
	public void setExpenseBillNumber(String expenseBillNumber) {
		this.expenseBillNumber = expenseBillNumber;
	}
 
	public String getAddress() {
		return address;
	}
 
	public void setAddress(String address) {
		this.address = address;
	}
 
	public String getFilePath() {
		return filePath;
	}
 
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
 
	public String getAttachmentType() {
		return attachmentType;
	}
 
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	@Override
	public String toString() {
		return "LtExpExpenseLines [expLineId=" + expLineId + ", expHeaderId=" + expHeaderId + ", lineNo=" + lineNo
				+ ", expenseTypeId=" + expenseTypeId + ", receiptAmount=" + receiptAmount + ", currencyCode="
				+ currencyCode + ", exchangeRate=" + exchangeRate + ", amount=" + amount + ", justification="
				+ justification + ", projectId=" + projectId + ", taskId=" + taskId + ", travelType=" + travelType
				+ ", ticketClassCode=" + ticketClassCode + ", ticketNumber=" + ticketNumber + ", flightNumber="
				+ flightNumber + ", numberAttendees=" + numberAttendees + ", attendees=" + attendees + ", status="
				+ status + ", statusMessage=" + statusMessage + ", modeOfTravel=" + modeOfTravel + ", paidByCompany="
				+ paidByCompany + ", hotelName=" + hotelName + ", hotelNoOfDays=" + hotelNoOfDays + ", hotelRate="
				+ hotelRate + ", hotelBillNumber=" + hotelBillNumber + ", foodNameOfHotel=" + foodNameOfHotel
				+ ", foodHotelBillNumber=" + foodHotelBillNumber + ", purpose=" + purpose + ", missingBill="
				+ missingBill + ", currencyCodeWithName=" + currencyCodeWithName + ", segment1=" + segment1
				+ ", segment2=" + segment2 + ", segment3=" + segment3 + ", segment4=" + segment4 + ", segment5="
				+ segment5 + ", segment6=" + segment6 + ", segment7=" + segment7 + ", segment8=" + segment8
				+ ", segment9=" + segment9 + ", segment10=" + segment10 + ", segment11=" + segment11 + ", segment12="
				+ segment12 + ", segment13=" + segment13 + ", segment14=" + segment14 + ", segment15=" + segment15
				+ ", totalDistanceTraveled=" + totalDistanceTraveled + ", fuelCostPerKm=" + fuelCostPerKm
				+ ", maintenancePerKm=" + maintenancePerKm + ", totalFuelCost=" + totalFuelCost
				+ ", totalMaintenanceCost=" + totalMaintenanceCost + ", tollCharges=" + tollCharges
				+ ", seatPreference=" + seatPreference + ", carrier=" + carrier + ", fromCity=" + fromCity + ", toCity="
				+ toCity + ", foodPreference=" + foodPreference + ", departureTime=" + departureTime + ", arrivalTime="
				+ arrivalTime + ", bookingType=" + bookingType + ", expenseFileUploadId=" + expenseFileUploadId
				+ ", companyId=" + companyId + ", projectName=" + projectName + ", expenseTypeCode=" + expenseTypeCode
				+ ", expenseTypePdf=" + expenseTypePdf + ", expenseNature=" + expenseNature + ", travelTypeName="
				+ travelTypeName + ", draw=" + draw + ", start=" + start + ", length=" + length + ", stDate=" + stDate
				+ ", enDate=" + enDate + ", rnum=" + rnum + ", receptAmountStr=" + receptAmountStr + ", columnNo="
				+ columnNo + ", sort=" + sort + ", name=" + name + ", address=" + address + ", expenseBillNumber="
				+ expenseBillNumber + ", filePath=" + filePath + ", attachmentType=" + attachmentType + "]";
	}

	
 
	
 
	
 
	
}
