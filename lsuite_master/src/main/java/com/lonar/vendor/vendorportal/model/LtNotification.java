package com.lonar.vendor.vendorportal.model;

public class LtNotification 
{
	private String notification;
	private String expenseNumber;
	private Long expenseHeaderId;
	private String expenseCategory;
	
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getExpenseNumber() {
		return expenseNumber;
	}
	public void setExpenseNumber(String expenseNumber) {
		this.expenseNumber = expenseNumber;
	}
	public Long getExpenseHeaderId() {
		return expenseHeaderId;
	}
	public void setExpenseHeaderId(Long expenseHeaderId) {
		this.expenseHeaderId = expenseHeaderId;
	}
	public String getExpenseCategory() {
		return expenseCategory;
	}
	public void setExpenseCategory(String expenseCategory) {
		this.expenseCategory = expenseCategory;
	}
	@Override
	public String toString() {
		return "LtNotification [notification=" + notification + ", expenseNumber=" + expenseNumber
				+ ", expenseHeaderId=" + expenseHeaderId + ", expenseCategory=" + expenseCategory + "]";
	}
	
	
	
	

}

