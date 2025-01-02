package com.lonar.UserManagement.web.model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "LT_MAST_BRANCHES")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class LtMastBranches extends WhoColumns implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	//@NotNull(message="notnull")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_seq")
//	@SequenceGenerator(name = "branch_seq", sequenceName = "LT_MAST_BRANCHES_S", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "BRANCH_ID")
	private Long branchId;
	
	@Basic(optional = false)
	@NotNull(message="notnull")
	@Size(min = 1, max = 30)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	
	@Basic(optional = false)
	@NotNull(message="notnull")
	@JsonView(DataTablesOutput.View.class)
	@Size(min = 1, max = 240)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "BRANCH_NAME")
	private String branchName;
	
	@Basic(optional = false)
	@NotNull(message="notnull")
	@Size(min = 1, max = 80)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "CITY")
	private String city;
	
	@Size(max = 80)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "REGION")
	private String region;
	
	@Size(max = 80)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "STATE")
	private String state;
	
	@Size(max = 80)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "COUNTRY")
	private String country;
	
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "REPORTING_BRANCH")
	private Long reportingBranch;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "OPENING_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date openingDate;
	
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "MANAGER_ID")
	private Long managerId;

	@JsonView(DataTablesOutput.View.class)
	@Size(max = 30)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "STATE_CODE")
	private String stateCode;
	
	@NotNull(message="notnull")
	@Size(max = 15)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "GST_REG_NO")
	private String gstRegNo;
	
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml(message="UnsafeHtml")
	@Column(name = "STATUS")
	private String status;
	
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "BRANCH_TYPE")
	private String branchType;
	
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "COMPANY_ID")
	private Long companyId;

	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String managerName;
	
	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String managerCode;
	
	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String managerOfficalEmailId;
	
	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String reportingBranchName;
	
	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String reportingBranchCode;
	
	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String companyName;

	public LtMastBranches() {
	}

	public LtMastBranches(Long branchId) {
		this.branchId = branchId;
	}


	@Transient
	private String stDate;
	@Transient
	private String enDate;

	/*public void setReportingBranchName(String reportingBranchName) {
		if (reportingBranchName == null || reportingBranchName.equals("null"))
			reportingBranchName = "";

		this.reportingBranchName = reportingBranchName;
	}*/

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setReportingBranchName(String reportingBranchName) {
		this.reportingBranchName = reportingBranchName;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getReportingBranch() {
		return reportingBranch;
	}

	public void setReportingBranch(Long reportingBranch) {
		this.reportingBranch = reportingBranch;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getGstRegNo() {
		return gstRegNo;
	}

	public void setGstRegNo(String gstRegNo) {
		this.gstRegNo = gstRegNo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getReportingBranchName() {
		return reportingBranchName;
	}

	public void setManagerName(String managerName) {
		if (managerName == null || managerName.equals("null null"))
			managerName = "";
		this.managerName = managerName;
	}

	public String getReportingBranchCode() {
		return reportingBranchCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setReportingBranchCode(String reportingBranchCode) {
		if (reportingBranchCode == null || reportingBranchCode.equals("null"))
			reportingBranchCode = "";
		this.reportingBranchCode = reportingBranchCode;
	}
	
	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getManagerOfficalEmailId() {
		return managerOfficalEmailId;
	}

	public void setManagerOfficalEmailId(String managerOfficalEmailId) {
		if (managerOfficalEmailId == null || managerOfficalEmailId.equals("null"))
			managerOfficalEmailId = "";
		this.managerOfficalEmailId = managerOfficalEmailId;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		if (managerCode == null || managerCode.equals("null"))
			managerCode = "";
		this.managerCode = managerCode;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (branchId != null ? branchId.hashCode() : 0);
		return hash;
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


}
