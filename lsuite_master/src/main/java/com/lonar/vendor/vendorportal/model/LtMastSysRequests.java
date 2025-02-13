package com.lonar.vendor.vendorportal.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LtMastSysRequests {
	
	private Long requestId;
    private Date requestDate;
    private Date completedDate;
    private String status;
    private String requestorName;
    private String requestName;
  
    
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
    
	public LtMastSysRequests() {
		super();
	}

	public LtMastSysRequests(Long requestId, Date requestDate, Date completedDate, String status,
			String requestorName, String requestName) {
		super();
		this.requestId = requestId;
		this.requestDate = requestDate;
		this.completedDate = completedDate;
		this.status = status;
		this.requestorName = requestorName;
		this.requestName = requestName;
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

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}

	@Override
	public String toString() {
		return "LtMastSysRequests [requestId=" + requestId + ", requestDate=" + requestDate + ", completedDate="
				+ completedDate + ", status=" + status + ", requestorName=" + requestorName + ", requestName="
				+ requestName + ", draw=" + draw + ", start=" + start + ", length=" + length + ", columnNo=" + columnNo
				+ ", sort=" + sort + "]";
	}




	
    
    

}
