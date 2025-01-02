package com.lonar.vendor.vendorportal.model;

public class BusinessException extends RuntimeException
{

	private int code;
	private String message;
	private Exception exception;
	
	public BusinessException(int code, String message, Exception e) {
		super();
		this.code = code;
		this.message = message;
		this.exception = e;
		
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	


}
