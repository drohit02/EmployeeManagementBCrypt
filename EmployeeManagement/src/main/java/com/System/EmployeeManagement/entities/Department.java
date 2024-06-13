package com.System.EmployeeManagement.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="department",uniqueConstraints = @UniqueConstraint(columnNames = { "deptName         " }))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {

	@Id
	@Column(name="dept_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptId;
	
	@NotBlank
	@NotNull
	@Column(name="dept_name")
	private String deptName;
	
	@NotBlank
	@NotNull
	@Column(name="description") 
	private String description;
	
	@JsonIgnoreProperties("deptList")
	@ManyToMany(mappedBy = "deptList",cascade = CascadeType.ALL)
	private List<Employee> empList;
	
	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Department(int deptId, @NotBlank @NotNull String deptName, @NotBlank @NotNull String description,
			List<Employee> empList) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.description = description;
		this.empList = empList;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
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
