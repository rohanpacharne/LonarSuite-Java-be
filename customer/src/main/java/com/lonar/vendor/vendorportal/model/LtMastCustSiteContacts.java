package com.lonar.vendor.vendorportal.model;

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
@Table(name = "LT_MAST_CUST_SITE_CONTACTS")
@JsonInclude(Include.NON_NULL)
public class LtMastCustSiteContacts {

	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_site_contacts_seq")
//	@SequenceGenerator(name = "customer_site_contacts_seq", sequenceName = "LT_MAST_CUST_SITE_CONTACTS_S", allocationSize = 1)
	@Column(name = "SITE_CONTACT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long siteContactId;

	@Column(name = "CUSTOMER_SITE_ID")
	private Long customerSiteId;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Basic(optional = false)
	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Basic(optional = false)
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Basic(optional = false)
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;

	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;

	@Column(name = "LAST_UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;

	@Transient
	private String designationValue;

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

	public Long getSiteContactId() {
		return siteContactId;
	}

	public void setSiteContactId(Long siteContactId) {
		this.siteContactId = siteContactId;
	}

	public Long getCustomerSiteId() {
		return customerSiteId;
	}

	public void setCustomerSiteId(Long customerSiteId) {
		this.customerSiteId = customerSiteId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public String getDesignationValue() {
		return designationValue;
	}

	public void setDesignationValue(String designationValue) {
		this.designationValue = designationValue;
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

	@Override
	public String toString() {
		return "LtMastCustSiteContacts [siteContactId=" + siteContactId + ", customerSiteId=" + customerSiteId
				+ ", customerId=" + customerId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", designation=" + designation + ", contactNumber=" + contactNumber
				+ ", emailId=" + emailId + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", designationValue=" + designationValue + "]";
	}

}
