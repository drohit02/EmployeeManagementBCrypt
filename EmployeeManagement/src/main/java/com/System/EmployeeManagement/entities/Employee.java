package com.System.EmployeeManagement.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

	@Id
	@Column(name="emp_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	
	@NotNull
	@Column(name="emp_name")
	@NotBlank(message = "employee name is required")
	private String empName;
	
	@NotNull
	@NotBlank(message = "employee city is required")
	@Column(name="emp_city")
	private String empCity;
	
	@Column(name="emp_salary")
	@NotNull
	@Min(value = 1)
	private int empSalary;
	
	@JsonIgnoreProperties("empList")
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name = "emp_dept",
			joinColumns = @JoinColumn(name="emp_id"),
			inverseJoinColumns = @JoinColumn(name="dept_id")
			)
	private List<Department> deptList;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empId, @NotNull @NotBlank(message = "employee name is required") String empName,
			@NotNull @NotBlank(message = "employee city is required") String empCity, @NotNull @Min(1) int empSalary,
			List<Department> deptList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empCity = empCity;
		this.empSalary = empSalary;
		this.deptList = deptList;
	}
	
	public Employee(@NotNull @NotBlank(message = "employee name is required") String empName,
			@NotNull @NotBlank(message = "employee city is required") String empCity, @NotNull @Min(1) int empSalary,
			List<Department> deptList) {
		this.empName = empName;
		this.empCity = empCity;
		this.empSalary = empSalary;
		this.deptList = deptList;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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
