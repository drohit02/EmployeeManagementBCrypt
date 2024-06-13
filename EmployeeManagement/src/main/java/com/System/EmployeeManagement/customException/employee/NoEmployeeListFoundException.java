package com.System.EmployeeManagement.customException.employee;

public class NoEmployeeListFoundException extends RuntimeException {
	
	public NoEmployeeListFoundException() {
		super("Employee list must be empty");
	}
	public NoEmployeeListFoundException(String msg) {
		super(msg);
	}

}
