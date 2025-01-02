/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "LT_MAST_COMN_MASTER_VALUES")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class LtMastComnMasterValues implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonView(DataTablesOutput.View.class)
	@Column(name = "MASTER_ID")
	private Long masterId;

	@Basic(optional = false)
	// @NotNull(message="notnull")
	// @Size(min = 1, max = 80)
	@Column(name = "VALUE_CODE")
	private String valueCode;

	@Basic(optional = false)
	// @NotNull(message="notnull")
	// @Size(min = 1, max = 150)
	@Column(name = "VALUE_NAME")
	private String valueName;

	// @Size(max = 240)
	@Column(name = "VALUE_DESCRIPTION")
	private String valueDescription;

	// @Size(max = 150)
	@Column(name = "VALUE_TAG")
	private String valueTag;

	// @Size(max = 30)
	/*
	 * @JsonView(DataTablesOutput.View.class)
	 * 
	 * @SafeHtml(message="UnsafeHtml")
	 * 
	 * @Column(name = "STATUS") private String status;
	 */

	@Basic(optional = false)
	// @NotNull(message="notnull")
	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Basic(optional = false)
	// @NotNull(message="notnull")
	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Basic(optional = false)
	// @NotNull(message="notnull")
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Basic(optional = false)
	// @NotNull(message="notnull")
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;

	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;

	@Column(name = "LAST_UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comnMasterValues_seq")
//	@SequenceGenerator(name = "comnMasterValues_seq", sequenceName = "lt_mast_comn_master_values_s", allocationSize = 1)
	@Basic(optional = false)
	@Column(name = "COMN_MASTER_VALUES_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comnMasterValuesId;

	@Transient
	private String masterName;

	@Transient
	private String masterDesc;

	public LtMastComnMasterValues() {
	}

	public LtMastComnMasterValues(Long comnMasterValuesId) {
		this.comnMasterValuesId = comnMasterValuesId;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
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

	public String getValueDescription() {
		return valueDescription;
	}

	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}

	public String getValueTag() {
		return valueTag;
	}

	public void setValueTag(String valueTag) {
		this.valueTag = valueTag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public Long getComnMasterValuesId() {
		return comnMasterValuesId;
	}

	public void setComnMasterValuesId(Long comnMasterValuesId) {
		this.comnMasterValuesId = comnMasterValuesId;
	}

	public String getMasterName() {
		return masterName;
	}

	/*
	 * public String getStatus() { return status; }
	 * 
	 * public void setStatus(String status) { this.status = status; }
	 */

	public void setMasterName(String masterName) {
		if (masterName == null || masterName.equals("null"))
			masterName = "";
		this.masterName = masterName;
	}

	public String getMasterDesc() {
		return masterDesc;
	}

	/*
	 * public LtMastComnMaster getLtMastComnMasterValues() { return
	 * LtMastComnMasterValues; }
	 * 
	 * public void setLtMastComnMasterValues(LtMastComnMaster
	 * LtMastComnMasterValues) { this.LtMastComnMasterValues =
	 * LtMastComnMasterValues; }
	 */

	public void setMasterDesc(String masterDesc) {
		if (masterDesc == null || masterDesc.equals("null"))
			masterDesc = "";
		this.masterDesc = masterDesc;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (comnMasterValuesId != null ? comnMasterValuesId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof LtMastComnMasterValues)) {
			return false;
		}
		LtMastComnMasterValues other = (LtMastComnMasterValues) object;
		if ((this.comnMasterValuesId == null && other.comnMasterValuesId != null)
				|| (this.comnMasterValuesId != null && !this.comnMasterValuesId.equals(other.comnMasterValuesId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "LtMastComnMasterValues [masterId=" + masterId + ", valueCode=" + valueCode + ", valueName=" + valueName
				+ ", valueDescription=" + valueDescription + ", valueTag=" + valueTag + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", comnMasterValuesId=" + comnMasterValuesId + ", masterName=" + masterName
				+ ", masterDesc=" + masterDesc + "]";
	}

}
