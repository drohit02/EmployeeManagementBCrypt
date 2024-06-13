package com.System.EmployeeManagement.service;

import java.util.List;
import java.util.Optional;

import com.System.EmployeeManagement.dto.EmployeeDto;
import com.System.EmployeeManagement.entities.Employee;

import jakarta.validation.Valid;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public Optional<Employee> getEmployeeById(int enpId);
	Optional<EmployeeDto> saveEmployeeData(@Valid EmployeeDto empobj);
	public @Valid EmployeeDto updateEmployeeData(int empId, @Valid EmployeeDto empObj);
	public boolean purgeEmployeeData(int empId);
	public List<Employee> findAllEmployeeWithName(String empName);
	
	
}
