package com.lonar.vendor.vendorportal.model;

public class Customer {
	
	private long valueCode;
	private String valueName;
	private long customerCode;
	
	public Customer() {
		super();
	}
	public Customer(long valueCode, String valueName, long customerCode) {
		super();
		this.valueCode = valueCode;
		this.valueName = valueName;
		this.customerCode = customerCode;
	}
	public long getValueCode() {
		return valueCode;
	}
	public void setValueCode(long valueCode) {
		this.valueCode = valueCode;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	public long getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(long customerCode) {
		this.customerCode = customerCode;
	}
	@Override
	public String toString() {
		return "Customer [valueCode=" + valueCode + ", valueName=" + valueName + ", customerCode=" + customerCode + "]";
	}
	
	

}
