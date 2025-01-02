package com.lonar.asn.model;

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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="LT_SHIPMENT_SOURCES") 
@JsonInclude(Include.NON_NULL)
public class ShipmenntSources {
	
	@Id
//	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_seq")
//	//@SequenceGenerator(name = "source_seq", sequenceName = "LT_SHIPMENNT_SOURCES_S", allocationSize = 1)
//	@Basic(optional = false)
	@Column(name = "SHIP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shipId;
    
//	@Id
//	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipment_source_id")
	private Long shipmentSourceId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name = "last_update_login")
	private Long lastUpdateLogin;
	
	@Column(name = "last_updated_by")
	private Long lastUpdatedBy;
	
	@Column(name = "last_update_date")
	private Date lastUpdateDate;

	@Column(name = "PO_HEADER_ID")
	private Long poHeaderId;
	
	@Column(name = "PO_LINE_ID")
	private Long poLineId;
	
	@Column(name = "PO_SHIPMENT_ID")
	private Long poShipmentId;
	
	@Column(name = "VENDOR_ID")
	private Long vendorId;
	
	@Column(name = "VENDOR_ADD_ID")
	private Long vendorAddId;
	
	public Long getShipId() {
		return shipId;
	}

	public void setShipId(Long shipID) {
		this.shipId = shipID;
	}

	public Long getShipmentSourceId() {
		return shipmentSourceId;
	}

	public void setShipmentSourceId(Long shipmentSourceId) {
		this.shipmentSourceId = shipmentSourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public Long getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Long poLineId) {
		this.poLineId = poLineId;
	}

//	public Long getSHIP_ID() {
//		return SHIP_ID;
//	}
//
//	public void setSHIP_ID(Long sHIP_ID) {
//		SHIP_ID = sHIP_ID;
//	}

	public Long getPoShipmentId() {
		return poShipmentId;
	}

	public void setPoShipmentId(Long poShipmentId) {
		this.poShipmentId = poShipmentId;
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
	
}

