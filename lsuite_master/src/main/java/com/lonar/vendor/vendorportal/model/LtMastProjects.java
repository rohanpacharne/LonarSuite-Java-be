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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
 
@Entity
@Table(name = "LT_MAST_PROJECTS")
@XmlRootElement	
@JsonInclude(Include.NON_NULL)
public class LtMastProjects implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
//	@SequenceGenerator(name = "project_seq", sequenceName = "LT_MAST_PROJECTS_S", allocationSize = 1)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "PROJECT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	@Basic(optional = false)
	@Size( max = 30)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "PROJECT_NUMBER")
	private String projectNumber;

	@Basic(optional = false)
	@Size(min = 1, max = 240)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "PROJECT_NAME")
	private String projectName;
	@Size(max = 240)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "PROJECT_DESC")
	private String projectDesc;

	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "STATUS")
	private String status;
	@Basic(optional = false)
	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Basic(optional = false)
	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CREATED_BY")
	private Long createdBy;
	@Basic(optional = false)
	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Basic(optional = false)
	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "LAST_UPDATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateDate;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	
	@Basic(optional = false)
	@NotNull
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "COMPANY_ID")
	private Long companyId;
	
	
	@Transient
	private String customerNumber;
	@Transient
	private String customerName;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "PM_ID")
	private Long projectManagerId;
	@Transient
	private String employeeName;
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
 
	public Long getProjectId() {
		return projectId;
	}
 
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
 
	public String getProjectNumber() {
		return projectNumber;
	}
 
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
 
	public String getProjectName() {
		return projectName;
	}
 
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
 
	public String getProjectDesc() {
		return projectDesc;
	}
 
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
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
 
	public Long getCustomerId() {
		return customerId;
	}
 
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
 
	public String getCustomerNumber() {
		return customerNumber;
	}
 
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
 
	public String getCustomerName() {
		return customerName;
	}
 
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
 
	public Long getProjectManagerId() {
		return projectManagerId;
	}
 
	public void setProjectManagerId(Long projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
 
	public String getEmployeeName() {
		return employeeName;
	}
 
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
 
	
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "LtMastProjects [projectId=" + projectId + ", projectNumber=" + projectNumber + ", projectName="
				+ projectName + ", projectDesc=" + projectDesc + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", customerId=" + customerId + ", companyId=" + companyId + ", customerNumber="
				+ customerNumber + ", customerName=" + customerName + ", projectManagerId=" + projectManagerId
				+ ", employeeName=" + employeeName + ", stDate=" + stDate + ", enDate=" + enDate + ", subDate="
				+ subDate + ", draw=" + draw + ", start=" + start + ", length=" + length + ", columnNo=" + columnNo
				+ ", sort=" + sort + "]";
	}

	
 
	
 
}
