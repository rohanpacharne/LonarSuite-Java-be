package com.lonar.vendor.vendorportal.requests;

public class RequestParameters {

	
	  private Long companyId;
	  private Long userId;
	  private String startDate;
	  private String endDate;
	  private String suppilerName;
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSuppilerName() {
		return suppilerName;
	}
	public void setSuppilerName(String suppilerName) {
		this.suppilerName = suppilerName;
	}
	@Override
	public String toString() {
		return "RequestParameters [companyId=" + companyId + ", userId=" + userId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", suppilerName=" + suppilerName + "]";
	}

	
	
}
