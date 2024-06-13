package com.System.EmployeeManagement.exceptionResponseObject;

import java.util.Date;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private ExceptionEnum statusCode;
	private String message;
	private String details;

	public ExceptionResponse() {
		// TODO Auto-generated constructor stub
	}

	public ExceptionResponse(Date timestamp, ExceptionEnum statusCode, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public ExceptionEnum getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(ExceptionEnum statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [timestamp=" + timestamp + ", statusCode=" + statusCode + ", message=" + message
				+ ", details=" + details + "]";
	}
	
	
}
