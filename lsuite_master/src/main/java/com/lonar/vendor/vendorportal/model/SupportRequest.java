package com.lonar.vendor.vendorportal.model;
 
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
 
@Entity
@Table(name = "lt_support_requests")
public class SupportRequest {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;
 
    @Column(name = "raised_by")
    private Integer raisedBy;
 
    @Column(name = "raised_date")
    private Date raisedDate;
 
    @Column(name = "issue_subject")
    private String issueSubject;
 
    @Column(name = "issue_description")
    private String issueDescription;
 
    @Column(name = "issue_type")
    private String issueType;
 
    @Column(name = "attachment_file_path")
    private String attachmentFilePath;
 
    @Column(name = "contact_number")
    private String contactNumber;
 
    @Column(name = "priority")
    private String priority;
 
    @Column(name = "assigned_to")
    private Integer assignedTo;
 
    @Column(name = "date_resolved")
    private Date dateResolved;
 
    @Column(name = "resolution_details")
    private String resolutionDetails;
 
    @Column(name = "status")
    private String status;
 
    @Column(name = "created_by")
    private Integer createdBy;
 
    @Column(name = "creation_date")
    private Date creationDate;
 
    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;
 
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
 
    @Column(name = "last_update_login")
    private Integer lastUpdateLogin;
    @Column(name = "mail_sent_status")
    private String mailSentStatus;
    @Transient
    private Long companyId;
    @Transient
    private String supportEmail;
    @Transient 
    private String supportContactNo;
    @Transient
    private String loginUserMail;
    public String getRaisedName() {
		return raisedName;
	}
 
	public void setRaisedName(String raisedName) {
		this.raisedName = raisedName;
	}
 
	@Transient
    private String employeeName;

 
    @Transient
    private String vendorName;
    @Transient
    private String raisedName;
    @Transient
	private Long draw;
 
	@Transient
	private Long start;
 
	@Transient
	private Long length;
	@Transient
	private String raisedBystr;
 
 
	public String getRaisedBystr() {
		return raisedBystr;
	}
 
	public void setRaisedBystr(String raisedBystr) {
		this.raisedBystr = raisedBystr;
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

 
	@Transient
	private int columnNo;
 
	@Transient
	private String sort;
 
    
    // Getters and Setters
 
    public String getEmployeeName() {
		return employeeName;
	}
 
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
 
	public String getVendorName() {
		return vendorName;
	}
 
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
 
	public Long getTicketId() {
        return ticketId;
    }
 
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
 
    public Integer getRaisedBy() {
        return raisedBy;
    }
 
    public void setRaisedBy(Integer raisedBy) {
        this.raisedBy = raisedBy;
    }
 
    public Date getRaisedDate() {
        return raisedDate;
    }
 
    public void setRaisedDate(Date raisedDate) {
        this.raisedDate = raisedDate;
    }
 
    public String getIssueSubject() {
        return issueSubject;
    }
 
    public void setIssueSubject(String issueSubject) {
        this.issueSubject = issueSubject;
    }
 
    public String getIssueDescription() {
        return issueDescription;
    }
 
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }
 
    public String getIssueType() {
        return issueType;
    }
 
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }
 
    public String getAttachmentFilePath() {
        return attachmentFilePath;
    }
 
    public void setAttachmentFilePath(String attachmentFilePath) {
        this.attachmentFilePath = attachmentFilePath;
    }
 
    public String getContactNumber() {
        return contactNumber;
    }
 
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
 
    public String getPriority() {
        return priority;
    }
 
    public void setPriority(String priority) {
        this.priority = priority;
    }
 
    public Integer getAssignedTo() {
        return assignedTo;
    }
 
    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }
 
    public Date getDateResolved() {
        return dateResolved;
    }
 
    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }
 
    public String getResolutionDetails() {
        return resolutionDetails;
    }
 
    public void setResolutionDetails(String resolutionDetails) {
        this.resolutionDetails = resolutionDetails;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
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
 
    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }
 
    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

 
    public String getMailSentStatus() {
		return mailSentStatus;
	}
 
	public void setMailSentStatus(String mailSentStatus) {
		this.mailSentStatus = mailSentStatus;
	}
 
	
 
	public Long getCompanyId() {
		return companyId;
	}
 
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
 
	public String getSupportEmail() {
		return supportEmail;
	}
 
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
 
	public String getSupportContactNo() {
		return supportContactNo;
	}
 
	public void setSupportContactNo(String supportContactNo) {
		this.supportContactNo = supportContactNo;
	}
 
	public String getLoginUserMail() {
		return loginUserMail;
	}
 
	public void setLoginUserMail(String loginUserMail) {
		this.loginUserMail = loginUserMail;
	}
 
	@Override
	public String toString() {
		return "SupportRequest [ticketId=" + ticketId + ", raisedBy=" + raisedBy + ", raisedDate=" + raisedDate
				+ ", issueSubject=" + issueSubject + ", issueDescription=" + issueDescription + ", issueType="
				+ issueType + ", attachmentFilePath=" + attachmentFilePath + ", contactNumber=" + contactNumber
				+ ", priority=" + priority + ", assignedTo=" + assignedTo + ", dateResolved=" + dateResolved
				+ ", resolutionDetails=" + resolutionDetails + ", status=" + status + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
				+ lastUpdateDate + ", lastUpdateLogin=" + lastUpdateLogin + ", mailSentStatus=" + mailSentStatus
				+ ", companyId=" + companyId + ", supportEmail=" + supportEmail + ", supportContactNo="
				+ supportContactNo + ", loginUserMail=" + loginUserMail + ", employeeName=" + employeeName
				+ ", vendorName=" + vendorName + ", raisedName=" + raisedName + ", draw=" + draw + ", start=" + start
				+ ", length=" + length + ", raisedBystr=" + raisedBystr + ", columnNo=" + columnNo + ", sort=" + sort
				+ "]";
	}
}