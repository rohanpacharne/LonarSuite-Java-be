package com.lonar.UserManagement.web.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LT_MAST_AMOUNTWISE_APPROVALS")
public class LtMastAmountwiseApprovals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amt_approval_id")
    private Long amtApprovalId;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "from_amount")
    private Double fromAmount;

    @Column(name = "to_amount")
    private Double toAmount;

    @Column(name = "approver_id")
    private Long approverId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "last_update_login")
    private String lastUpdateLogin;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @Column(name = "company_id")
    private Long companyId;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

	
}
