package com.lonar.vendor.vendorportal.model;
 
import java.util.Date;
 
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
 
@Entity
@Table(name = "LT_PO_LINE_TAXES")
@JsonInclude(Include.NON_NULL)
public class LtPoLineTaxes {
		@Id
		@Basic(optional = false)
//		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "po_line_tax_seq")
//		@SequenceGenerator(name = "po_line_tax_seq", sequenceName = "LT_PO_LINE_TAXES_S", allocationSize = 1)
		@Column(name = "Po_Line_Tax_Id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long poLineTaxId;
		@Column(name = "Po_HEADER_ID")
		private Long poHeaderId;
		@Column(name = "Po_LINE_ID")
		private Long poLineId;
		@Column(name = "TAX_ID")
		private Long taxId;
		@Column(name = "TAX_RATE")
		private Double taxRate;
		@Column(name = "TAX_AMOUNT")
		private Double taxAmount;
		@Column(name = "CREATED_BY")
		private Long createdBy;
		@Basic(optional = false)
		@Column(name = "CREATION_DATE")
		private Date creationDate;
		@Basic(optional = false)
		@NotNull
		@Column(name = "LAST_UPDATE_LOGIN")
		private Long lastUpdateLogin;
		@Column(name = "LAST_UPDATED_BY")
		private Long lastUpdatedBy;
		@Column(name = "LAST_UPDATE_DATE")
		private Date lastUpdateDate;
		@Transient
		private String taxName;
		@Transient 
		private String taxDesc;
		@Transient 
		private Double totalTaxAmount;
		@Transient 
		private Double totalAmount;

 
		public Double getTotalAmount() {
			return totalAmount;
		}
 
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
 
		public String getTaxName() {
			return taxName;
		}
 
		public void setTaxName(String taxName) {
			this.taxName = taxName;
		}
 
		public String getTaxDesc() {
			return taxDesc;
		}
 
		public void setTaxDesc(String taxDesc) {
			this.taxDesc = taxDesc;
		}
 
		public Long getPoLineTaxId() {
			return poLineTaxId;
		}
 
		public void setPoLineTaxId(Long poLineTaxId) {
			this.poLineTaxId = poLineTaxId;
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
 
		public Long getTaxId() {
			return taxId;
		}
 
		public void setTaxId(Long taxId) {
			this.taxId = taxId;
		}
 
		public Double getTaxRate() {
			return taxRate;
		}
 
		public void setTaxRate(Double taxRate) {
			this.taxRate = taxRate;
		}
 
		public Double getTaxAmount() {
			return taxAmount;
		}
 
		public void setTaxAmount(Double taxAmount) {
			this.taxAmount = taxAmount;
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
 
		public Double getTotalTaxAmount() {
			return totalTaxAmount;
		}
 
		public void setTotalTaxAmount(Double totalTaxAmount) {
			this.totalTaxAmount = totalTaxAmount;
		}
 
		@Override
		public String toString() {
			return "LtPoLineTaxes [poLineTaxId=" + poLineTaxId + ", poHeaderId=" + poHeaderId + ", poLineId=" + poLineId
					+ ", taxId=" + taxId + ", taxRate=" + taxRate + ", taxAmount=" + taxAmount + ", createdBy="
					+ createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
					+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", taxName=" + taxName
					+ ", taxDesc=" + taxDesc + ", totalTaxAmount=" + totalTaxAmount + ", totalAmount=" + totalAmount
					+ "]";
		}
 
	
 
}