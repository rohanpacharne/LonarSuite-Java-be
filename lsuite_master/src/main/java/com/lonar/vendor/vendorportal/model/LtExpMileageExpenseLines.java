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
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
 
@Entity
@Table(name ="LT_EXP_MILEAGE_EXPENSE_LINES")
@JsonInclude(Include.NON_NULL)
public class LtExpMileageExpenseLines  implements Serializable
{
	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mileage_line_seq")
//	@SequenceGenerator(name = "mileage_line_seq", sequenceName = "LT_EXP_MILEAGE_EXPENSE_LINES_S", allocationSize = 1)
	@Column(name = "Exp_Mileage_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expMileageId;
	@Column(name = "Exp_Header_id")
	private Long expHeaderId;
	@Column(name = "Exp_Line_id")
	private Long expLineId;
	@Column(name = "mileage_line_number")
	private Long mileageLineNumber;
	@Column(name = "travel_date")
	private Date travelDate;
	@Column(name = "Destination")
	private String destination;
	@Column(name = "Opening_KM")
	private Long openingKm;
	@Column(name = "Closing_KM")
	private Long closingKm;
	@Column(name = "Distance_Travelled")
	private Long distanceTravelled;
 
	@Column(name = "Mileage_uses")
	private String mileageUses;
	@Column(name = "Travel_Purpose")
	private String travelPurpose;
 
	@Column(name = "CREATED_BY")
	private Long createdBy;
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;

	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	@Transient
	private Double officialKm;
	public Long getExpMileageId() {
		return expMileageId;
	}
 
	public void setExpMileageId(Long expMileageId) {
		this.expMileageId = expMileageId;
	}
 
	public Long getExpHeaderId() {
		return expHeaderId;
	}
 
	public void setExpHeaderId(Long expHeaderId) {
		this.expHeaderId = expHeaderId;
	}
 
	public Long getExpLineId() {
		return expLineId;
	}
 
	public void setExpLineId(Long expLineId) {
		this.expLineId = expLineId;
	}
 
	public Date getTravelDate() {
		return travelDate;
	}
 
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
 
	public String getDestination() {
		return destination;
	}
 
	public void setDestination(String destination) {
		this.destination = destination;
	}
 
	public Long getOpeningKm() {
		return openingKm;
	}
 
	public void setOpeningKm(Long openingKm) {
		this.openingKm = openingKm;
	}
 
	public Long getClosingKm() {
		return closingKm;
	}
 
	public void setClosingKm(Long closingKm) {
		this.closingKm = closingKm;
	}
 
	public Long getDistanceTravelled() {
		return distanceTravelled;
	}
 
	public void setDistanceTravelled(Long distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}
 
	public String getMileageUses() {
		return mileageUses;
	}
 
	public void setMileageUses(String mileageUses) {
		this.mileageUses = mileageUses;
	}
 
	public String getTravelPurpose() {
		return travelPurpose;
	}
 
	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
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
 
	public Double getOfficialKm() {
		return officialKm;
	}
 
	public void setOfficialKm(Double officialKm) {
		this.officialKm = officialKm;
	}
 
	public Long getMileageLineNumber() {
		return mileageLineNumber;
	}
 
	public void setMileageLineNumber(Long mileageLineNumber) {
		this.mileageLineNumber = mileageLineNumber;
	}
 
	@Override
	public String toString() {
		return "LtExpMileageExpenseLines [expMileageId=" + expMileageId + ", expHeaderId=" + expHeaderId
				+ ", expLineId=" + expLineId + ", mileageLineNumber=" + mileageLineNumber + ", travelDate=" + travelDate
				+ ", destination=" + destination + ", openingKm=" + openingKm + ", closingKm=" + closingKm
				+ ", distanceTravelled=" + distanceTravelled + ", mileageUses=" + mileageUses + ", travelPurpose="
				+ travelPurpose + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin="
				+ lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", officialKm=" + officialKm + "]";
	}
 
	
 
	
}
