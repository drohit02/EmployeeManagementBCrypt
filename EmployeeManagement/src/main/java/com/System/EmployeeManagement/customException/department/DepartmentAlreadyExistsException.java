package com.System.EmployeeManagement.customException.department;

public class DepartmentAlreadyExistsException extends RuntimeException{

	public DepartmentAlreadyExistsException() {
		super();
	}
	public DepartmentAlreadyExistsException(String msg) {
		super(msg);
	}

	
	

}
