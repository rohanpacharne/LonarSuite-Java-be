package com.lonar.mast.model;
 
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
 
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
 
@Entity
@Table(name = "LT_MAST_EXPENSE_TYPES")
@JsonInclude(Include.NON_NULL)
public class LtMastExpenseTypes implements Serializable  {
 
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenseType_seq")
	@SequenceGenerator(name = "expenseType_seq", sequenceName = "LT_MAST_EXPENSE_TYPES_S", allocationSize = 1)
	@Column(name = "Expense_Type_Id")
	private Long expenseTypeId;
	@Basic(optional = false)
	@Column(name = "Expense_Nature")
	private String expenseNature;
	@Basic(optional = false)
	@Column(name = "Expense_Description")
	private String expenseDescription;
	@Basic(optional = false)
	@Column(name = "Expense_Type")
	private String expenseType;
	@Basic(optional = false)
	@Column(name = "GL_Code")
	private String glCode;

	@Basic(optional = false)
	@Column(name = "GL_Code_Desc")
	private String glCodeDesc;

	@SafeHtml(message="UnsafeHtml")
	@Column(name = "STATUS")
	private String status; 
	@Basic(optional = false)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "START_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "END_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Basic(optional = false)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CREATED_BY")
	private Long createdBy;
	@Basic(optional = false)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Basic(optional = false)
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
 
	
	@Transient
	private String  expenseTypeCode;
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
	private int columnNo;
	@Transient
	private String sort;
	@Column(name = "PURPOSE_REQ")
	private String purposeReq;
	public Long getExpenseTypeId() {
		return expenseTypeId;
	}
 
	public void setExpenseTypeId(Long expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}
 
	
 
	public String getExpenseTypeCode() {
		return expenseTypeCode;
	}
 
	public void setExpenseTypeCode(String expenseTypeCode) {
		this.expenseTypeCode = expenseTypeCode;
	}
 
	public String getExpenseNature() {
		return expenseNature;
	}
 
	public void setExpenseNature(String expenseNature) {
		this.expenseNature = expenseNature;
	}
 
 
	
 
	public String getGlCode() {
		return glCode;
	}
 
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
 
	
	public String getGlCodeDesc() {
		return glCodeDesc;
	}
 
	public void setGlCodeDesc(String glCodeDesc) {
		this.glCodeDesc = glCodeDesc;
	}
 
	public String getExpenseDescription() {
		return expenseDescription;
	}
 
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
 
	public String getExpenseType() {
		return expenseType;
	}
 
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
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
 
	public String getPurposeReq() {
		return purposeReq;
	}
 
	public void setPurposeReq(String purposeReq) {
		this.purposeReq = purposeReq;
	}
 
	@Override
	public String toString() {
		return "LtMastExpenseTypes [expenseTypeId=" + expenseTypeId + ", expenseNature=" + expenseNature
				+ ", expenseDescription=" + expenseDescription + ", expenseType=" + expenseType + ", glCode=" + glCode
				+ ", glCodeDesc=" + glCodeDesc + ", status=" + status + ", startDate=" + startDate + ", endDate="
				+ endDate + ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin="
				+ lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate
				+ ", expenseTypeCode=" + expenseTypeCode + ", draw=" + draw + ", start=" + start + ", length=" + length
				+ ", stDate=" + stDate + ", enDate=" + enDate + ", columnNo=" + columnNo + ", sort=" + sort
				+ ", purposeReq=" + purposeReq + "]";
	}
 
	
 
}