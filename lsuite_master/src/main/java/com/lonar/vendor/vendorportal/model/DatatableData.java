package com.lonar.vendor.vendorportal.model;

public class DatatableData 
{
	private Long count;
	private String status;
	private String expenseType;
	private Double percent;
	
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "DatatableData [count=" + count + ", status=" + status + ", expenseType=" + expenseType + ", percent="
				+ percent + "]";
	}
	
	
	
	

}

