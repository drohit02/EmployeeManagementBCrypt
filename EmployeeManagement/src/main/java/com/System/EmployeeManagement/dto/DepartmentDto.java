package com.System.EmployeeManagement.dto;

import java.util.List;

import com.System.EmployeeManagement.entities.Employee;

public class DepartmentDto {

	private String deptName;
	private String description;
	private List<Employee> empList;
	
	public DepartmentDto() {
		// TODO Auto-generated constructor stub
	}

	public DepartmentDto(String deptName, String description, List<Employee> empList) {
		super();
		this.deptName = deptName;
		this.description = description;
		this.empList = empList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
	

}
