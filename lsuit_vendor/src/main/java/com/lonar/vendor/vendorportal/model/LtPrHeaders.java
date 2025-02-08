package com.lonar.vendor.vendorportal.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lt_pr_headers")
public class LtPrHeaders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pr_header_id")
    private Integer prHeaderId;

    @Column(name = "pr_number")
    private String prNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "pr_type")
    private String prType;

    @Column(name = "status")
    private String status;

    @Column(name = "entered_by_id")
    private Integer enteredById;

    @Column(name = "requester_id")
    private Integer requesterId;

    @Column(name = "note_to_approver")
    private String noteToApprover;

    @Column(name = "division_id")
    private int divisionId;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "pr_amount")
    private Double prAmount;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Column(name = "last_update_login")
    private Integer lastUpdateLogin;

    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;
    
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
	private String requesterName;

	public Integer getPrHeaderId() {
		return prHeaderId;
	}

	public void setPrHeaderId(Integer prHeaderId) {
		this.prHeaderId = prHeaderId;
	}

	public String getPrNumber() {
		return prNumber;
	}

	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrType() {
		return prType;
	}

	public void setPrType(String prType) {
		this.prType = prType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEnteredById() {
		return enteredById;
	}

	public void setEnteredById(Integer enteredById) {
		this.enteredById = enteredById;
	}

	public Integer getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Integer requesterId) {
		this.requesterId = requesterId;
	}

	public String getNoteToApprover() {
		return noteToApprover;
	}

	public void setNoteToApprover(String noteToApprover) {
		this.noteToApprover = noteToApprover;
	}

	public int getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(int divisionId) {
		this.divisionId = divisionId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getPrAmount() {
		return prAmount;
	}

	public void setPrAmount(Double prAmount) {
		this.prAmount = prAmount;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Integer getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Integer lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
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

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	@Override
	public String toString() {
		return "LtPrHeaders [prHeaderId=" + prHeaderId + ", prNumber=" + prNumber + ", description=" + description
				+ ", prType=" + prType + ", status=" + status + ", enteredById=" + enteredById + ", requesterId="
				+ requesterId + ", noteToApprover=" + noteToApprover + ", divisionId=" + divisionId + ", companyId="
				+ companyId + ", currency=" + currency + ", prAmount=" + prAmount + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateLogin="
				+ lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", draw=" + draw + ", start=" + start
				+ ", length=" + length + ", columnNo=" + columnNo + ", sort=" + sort + ", requesterName="
				+ requesterName + "]";
	}
	
	
    
}

