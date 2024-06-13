package com.System.EmployeeManagement.controller;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.System.EmployeeManagement.customException.employee.EmployeeNotFoudException;
import com.System.EmployeeManagement.customException.employee.EmployeeNotSavedException;
import com.System.EmployeeManagement.customException.employee.NoEmployeeListFoundException;
import com.System.EmployeeManagement.dto.EmployeeDto;
import com.System.EmployeeManagement.entities.Employee;
import com.System.EmployeeManagement.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("allEmployee")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> empList = employeeService.getAllEmployees();
        if (empList.isEmpty()) {
            throw new NoEmployeeListFoundException("Employee list may be empty");
        }
        return ResponseEntity.ok().body(empList);
    }

    @GetMapping("fetchEmployeeById/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int empId) {
    	
        Optional<Employee> employee = employeeService.getEmployeeById(empId);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoudException("Employee with ID " + empId + " not found");
        }
        EmployeeDto empDtoObj = new EmployeeDto();
        empDtoObj.setEmpName(employee.get().getEmpName());
        empDtoObj.setEmpCity(employee.get().getEmpCity());
        empDtoObj.setEmpSalary(employee.get().getEmpSalary());
        empDtoObj.setDeptList(employee.get().getDeptList());
        return ResponseEntity.ok().body(empDtoObj);
    }
    
    @GetMapping("fetchEmployeeWithEmployee/{empName}")
    public ResponseEntity<List<Employee>> getEmployeeWithName(@PathVariable String empName) {
    	List<Employee> empList = this.employeeService.findAllEmployeeWithName(empName);
    	if(!empList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.OK).body(empList);
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Collections.emptyList());
    	}
    }
    
    
    @PostMapping("saveEmployee")
	public ResponseEntity<Optional<EmployeeDto>> saveEmployee(@Valid @RequestBody EmployeeDto empobj) {
    	
		Optional<EmployeeDto> dtoObj  = this.employeeService.saveEmployeeData(empobj);
		if(dtoObj.isPresent()) {
			return ResponseEntity.ok().body(dtoObj);
		}
		else {
			throw new EmployeeNotSavedException("Employee Data Not Saved int Datatbase");
		}
	}
    
    @PutMapping("updateEmployee/{empId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int empId,@Valid @RequestBody EmployeeDto empObj) {
    	 EmployeeDto empDtoObj = this.employeeService.updateEmployeeData(empId,empObj);
    	 if(empDtoObj!=null) {
    		 return ResponseEntity.ok().body(empDtoObj);
    	 }
    	 else {
    		 throw new EmployeeNotFoudException("EMployee not updated");
    	 }
    }
    @DeleteMapping("deleteEmployeeById/{empId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable int empId) {
    	boolean deleteStatus = this.employeeService.purgeEmployeeData(empId);
    	if(deleteStatus) {
    		return ResponseEntity.ok().body("Employee Data deleted successfully");    	
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee  not found with id : "+empId);
    	}
    }
   
    
}

	
