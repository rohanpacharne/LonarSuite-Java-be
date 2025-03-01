package com.lonar.UserManagement.web.model;
 
import javax.persistence.*;
import java.util.Date;
 
@Entity
@Table(name = "LT_MAST_AMOUNTWISE_APPROVALS")
public class LtMastAmountwiseApprovals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AMT_APPROVAL_ID")
    private Long amtApprovalId;
 
    @Column(name = "TRANSACTION_CODE")
    private String transactionCode;
 
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
 
    @Column(name = "FROM_AMOUNT")
    private Double fromAmount;
 
    @Column(name = "TO_AMOUNT")
    private Double toAmount;
 
    @Column(name = "APPROVER_ID")
    private Long approverId;
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;
 
    @Column(name = "CREATED_BY")
    private String createdBy;
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;
 
    @Column(name = "LAST_UPDATE_LOGIN")
    private String lastUpdateLogin;
 
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
 
    @Column(name = "COMPANY_ID")
    private Long companyId;
    @Column(name = "START_DATE")
    private Date startDate;
 
    @Column(name = "END_DATE")
    private Date endDate;
 
 
    @Transient
    private String approverName; 
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
    // Getters and Setters
    public Long getAmtApprovalId() {
        return amtApprovalId;
    }
 
    public void setAmtApprovalId(Long amtApprovalId) {
        this.amtApprovalId = amtApprovalId;
    }
 
    public String getTransactionCode() {
        return transactionCode;
    }
 
    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }
 
    public String getTransactionType() {
        return transactionType;
    }
 
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
 
    public Double getFromAmount() {
        return fromAmount;
    }
 
    public void setFromAmount(Double fromAmount) {
        this.fromAmount = fromAmount;
    }
 
    public Double getToAmount() {
        return toAmount;
    }
 
    public void setToAmount(Double toAmount) {
        this.toAmount = toAmount;
    }
 
    public Long getApproverId() {
        return approverId;
    }
 
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }
 
    public Date getCreationDate() {
        return creationDate;
    }
 
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
 
    public String getCreatedBy() {
        return createdBy;
    }
 
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
 
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
 
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
 
    public String getLastUpdateLogin() {
        return lastUpdateLogin;
    }
 
    public void setLastUpdateLogin(String lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }
 
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
 
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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
 
	public Long getCompanyId() {
        return companyId;
    }
 
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

 
    public String getApproverName() {
		return approverName;
	}
 
	public void setApproverName(String approverName) {
		this.approverName = approverName;
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
 
	@Override
	public String toString() {
		return "LtMastAmountwiseApprovals [amtApprovalId=" + amtApprovalId + ", transactionCode=" + transactionCode
				+ ", transactionType=" + transactionType + ", fromAmount=" + fromAmount + ", toAmount=" + toAmount
				+ ", approverId=" + approverId + ", creationDate=" + creationDate + ", createdBy=" + createdBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy="
				+ lastUpdatedBy + ", companyId=" + companyId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", draw=" + draw + ", start=" + start + ", length=" + length + ", stDate=" + stDate + ", enDate="
				+ enDate + ", columnNo=" + columnNo + ", sort=" + sort + ", getAmtApprovalId()=" + getAmtApprovalId()
				+ ", getTransactionCode()=" + getTransactionCode() + ", getTransactionType()=" + getTransactionType()
				+ ", getFromAmount()=" + getFromAmount() + ", getToAmount()=" + getToAmount() + ", getApproverId()="
				+ getApproverId() + ", getCreationDate()=" + getCreationDate() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getLastUpdateDate()=" + getLastUpdateDate() + ", getLastUpdateLogin()=" + getLastUpdateLogin()
				+ ", getLastUpdatedBy()=" + getLastUpdatedBy() + ", getStartDate()=" + getStartDate()
				+ ", getEndDate()=" + getEndDate() + ", getCompanyId()=" + getCompanyId() + ", getDraw()=" + getDraw()
				+ ", getStart()=" + getStart() + ", getLength()=" + getLength() + ", getStDate()=" + getStDate()
				+ ", getEnDate()=" + getEnDate() + ", getColumnNo()=" + getColumnNo() + ", getSort()=" + getSort()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}