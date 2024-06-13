package com.System.EmployeeManagement.customException.department;

public class DepartmentNotFoundException extends RuntimeException {
	
	public DepartmentNotFoundException() {
		super("Department is not present");
	}
	
	public DepartmentNotFoundException(String msg){
		super(msg);
	}
	

}
