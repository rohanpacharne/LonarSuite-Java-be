package com.lonar.vendor.vendorportal.reports;

import java.util.Date;

public class LtMastReportRequest {
	
	private Long requestId;
    private String requestUrl;
    private Date startDate;
    private Date endDate;
    private String filterData;
    private String filePath;
    private String status;
    private Long userId;
    private Date requestDate;
    private String fileName;
    private Date completedDate;
    private Date submittedDate;
    private String requestName;
    private String phase;
    private String logDetails;

    // Default Constructor
    public LtMastReportRequest() {}

    // Constructor with parameters for easier instantiation
    public LtMastReportRequest(Long requestId, String requestUrl, Date startDate, Date endDate, String filterData,
                         String filePath, String status, Long userId, Date requestDate, String fileName,
                         Date completedDate, Date submittedDate, String requestName, String phase, String logDetails) {
        this.requestId = requestId;
        this.requestUrl = requestUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.filterData = filterData;
        this.filePath = filePath;
        this.status = status;
        this.userId = userId;
        this.requestDate = requestDate;
        this.fileName = fileName;
        this.completedDate = completedDate;
        this.submittedDate = submittedDate;
        this.requestName = requestName;
        this.phase = phase;
        this.logDetails = logDetails;
    }

    // Getters and Setters

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
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

    public String getFilterData() {
        return filterData;
    }

    public void setFilterData(String filterData) {
        this.filterData = filterData;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getLogDetails() {
        return logDetails;
    }

    public void setLogDetails(String logDetails) {
        this.logDetails = logDetails;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "requestId=" + requestId +
                ", requestUrl='" + requestUrl + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", filterData='" + filterData + '\'' +
                ", filePath='" + filePath + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", requestDate=" + requestDate +
                ", fileName='" + fileName + '\'' +
                ", completedDate=" + completedDate +
                ", submittedDate=" + submittedDate +
                ", requestName='" + requestName + '\'' +
                ", phase='" + phase + '\'' +
                ", logDetails='" + logDetails + '\'' +
                '}';
    }


}