package com.System.EmployeeManagement.customException.employee;

import java.io.Serializable;

public class NoUserFoundException extends RuntimeException {
	
	public NoUserFoundException() {
		super("No user founfd with given id");
	}
	public NoUserFoundException(String msg) {
		super(msg);
	}
	

}
