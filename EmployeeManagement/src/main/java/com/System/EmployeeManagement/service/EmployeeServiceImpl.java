package com.System.EmployeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.System.EmployeeManagement.customException.employee.EmployeeNotSavedException;
import com.System.EmployeeManagement.customException.employee.NoEmployeeListFoundException;
import com.System.EmployeeManagement.dto.EmployeeDto;
import com.System.EmployeeManagement.entities.Employee;
import com.System.EmployeeManagement.repository.EmployeeRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = null;
		try {
			empList = this.empRepository.findAll();
			if(!empList.isEmpty()) {
				return empList;
			}
		}
		catch(NullPointerException ex) {
			throw new NoEmployeeListFoundException("Employee List maybe empty");	
		}
		
		return null;
	}

	@Override
	public Optional<Employee> getEmployeeById(int empId) {
		Optional<Employee> obj = this.empRepository.findById(empId);
		return obj;
	}

	@Override
	public Optional<EmployeeDto> saveEmployeeData(EmployeeDto obj) {
	    try {
	    	Employee empObj  = new Employee(obj.getEmpName(),obj.getEmpCity(),obj.getEmpSalary(),obj.getDeptList());
	        Employee savedEmployee = empRepository.save(empObj);
	        if (savedEmployee != null) {
	            EmployeeDto employeeDto = new EmployeeDto();
	            employeeDto.setEmpName(savedEmployee.getEmpName());
	            employeeDto.setEmpSalary(savedEmployee.getEmpSalary());
	            employeeDto.setEmpCity(savedEmployee.getEmpCity());
	            employeeDto.setDeptList(savedEmployee.getDeptList());
	            return Optional.of(employeeDto);
	        }
	    } catch (EmployeeNotSavedException ex) {
	        ex.printStackTrace();
	    }
	    return Optional.empty();
	}

	@Override
	public EmployeeDto updateEmployeeData(int empId, @Valid EmployeeDto empObj) {
		Optional<Employee> obj = this.empRepository.findById(empId);
		if(obj.isPresent()) {
			Employee updateObj = obj.get();
			updateObj.setEmpName(empObj.getEmpName());
			updateObj.setDeptList(empObj.getDeptList());
			updateObj.setEmpCity(empObj.getEmpCity());
			updateObj.setEmpSalary(empObj.getEmpSalary());
			
			this.empRepository.save(updateObj);
			return empObj;
		}
		else {
			throw new EmployeeNotSavedException("Employee Not Updated successfully");
		}
	}

	@Override
	public boolean purgeEmployeeData(int empId) {
		Optional<Employee> obj = this.empRepository.findById(empId);
		if(obj.isPresent()) {
			this.empRepository.deleteById(empId);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Employee> findAllEmployeeWithName(String empName) {
		return this.empRepository.findAllEmployeeByEmpName(empName);
		
	}


}
