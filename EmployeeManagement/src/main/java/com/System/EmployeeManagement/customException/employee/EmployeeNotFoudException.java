package com.System.EmployeeManagement.customException.employee;

public class EmployeeNotFoudException extends RuntimeException {
	
	public EmployeeNotFoudException() {
		super("employee may not be present");
	}
	public EmployeeNotFoudException(String msg) {
		super(msg);
	}

}
