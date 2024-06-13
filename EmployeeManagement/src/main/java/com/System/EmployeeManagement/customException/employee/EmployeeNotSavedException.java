package com.System.EmployeeManagement.customException.employee;

public class EmployeeNotSavedException extends RuntimeException {
	
	public EmployeeNotSavedException() {
		super("Error while saving the employee into db");
	}
	public EmployeeNotSavedException(String msg){
		super(msg);
	}

}
