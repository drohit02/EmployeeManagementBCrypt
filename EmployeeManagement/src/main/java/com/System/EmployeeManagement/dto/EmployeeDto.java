package com.System.EmployeeManagement.dto;

import java.util.List;

import com.System.EmployeeManagement.entities.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class EmployeeDto {

	private String empName;
	private String empCity;
	private int empSalary;
	private List<Department> deptList;
	
	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public EmployeeDto(String empName, String empCity, int empSalary, List<Department> deptList) {
		super();
		this.empName = empName;
		this.empCity = empCity;
		this.empSalary = empSalary;
		this.deptList = deptList;
	}



	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpCity() {
		return empCity;
	}

	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}

	public int getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}

	public List<Department> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}

	
}
