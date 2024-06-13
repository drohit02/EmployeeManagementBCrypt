package com.System.EmployeeManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.System.EmployeeManagement.customException.department.DepartmentNotFoundException;
import com.System.EmployeeManagement.dto.DepartmentDto;
import com.System.EmployeeManagement.entities.Department;
import com.System.EmployeeManagement.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/dept")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("allDepartment")
	public ResponseEntity<List<Department>> getAllDepartment() {
		List<Department> deptList = this.departmentService.getAllDeparmentData();
		if(!deptList.isEmpty()) {
			return ResponseEntity.ok().body(deptList);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@GetMapping("fetchDeptByDeptId/{deptId}")
	public ResponseEntity<DepartmentDto> getDeptById(@PathVariable int deptId) {
		Optional<Department> deptObj = this.departmentService.findDepatemtWithID(deptId);
		DepartmentDto dtoObj = new DepartmentDto();
		if(deptObj.isPresent()) {
			dtoObj.setDeptName(deptObj.get().getDeptName());
			dtoObj.setDescription(deptObj.get().getDescription());
			dtoObj.setEmpList(deptObj.get().getEmpList());
			return ResponseEntity.status(HttpStatus.OK).body(dtoObj);
		}
		else {
			throw new DepartmentNotFoundException();
		}
	}
	
	@GetMapping("fetchDeptByName/{deptName}")
	public ResponseEntity<Department> getDeptByName(@PathVariable String deptName) {
	    Optional<Department> dept = this.departmentService.findDepartmentByName(deptName);

	    if (dept.isPresent()) {
	        return ResponseEntity.status(HttpStatus.OK).body(dept.get());
	    } else {
	        throw new DepartmentNotFoundException("Department with name "+deptName+" is not present");
	    }
	}

	
	@PostMapping("saveDepartment")
	public ResponseEntity<DepartmentDto> saveDepartment(@Valid @RequestBody Department deptObj) {
		Department obj = this.departmentService.saveDepartmentData(deptObj);
		if(obj!=null) {
			DepartmentDto saveObj = new DepartmentDto();
			saveObj.setDeptName(deptObj.getDeptName());
			saveObj.setDescription(deptObj.getDescription());
			saveObj.setEmpList(deptObj.getEmpList());
			
			return ResponseEntity.status(HttpStatus.OK).body(saveObj);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("deletDept/{deptName}")
	public ResponseEntity<String> deleteDepartment(@PathVariable String deptName) {
		boolean deleteStatus = this.departmentService.removeDepartmentData(deptName);
		if(deleteStatus) {
			return ResponseEntity.status(HttpStatus.OK).body("Department Data deleted successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department not found");
		}
	}
	
	@DeleteMapping("deleteDept/{deptId}")
	public ResponseEntity<String> deleteDepartmentId(@PathVariable int deptId) {
		boolean deleteStatus = this.departmentService.removeDepartmentId(deptId);
		if(deleteStatus) {
			return ResponseEntity.status(HttpStatus.OK).body("Departrment Deleted Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department is not delete successfully");
		}
	}
}