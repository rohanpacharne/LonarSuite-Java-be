package com.lonar.vendor.vendorportal.model;

public class Employee {
	
	private long valueCode;
	private String valueName;
	
	public Employee(long valueCode, String valueName) {
		super();
		this.valueCode = valueCode;
		this.valueName = valueName;
	}

	public Employee() {
		super();
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

	@Override
	public String toString() {
		return "Employee [valueCode=" + valueCode + ", valueName=" + valueName + "]";
	}
	
	

}
