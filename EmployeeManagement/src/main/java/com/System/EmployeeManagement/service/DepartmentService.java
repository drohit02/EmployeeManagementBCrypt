package com.System.EmployeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.System.EmployeeManagement.customException.department.DepartmentAlreadyExistsException;
import com.System.EmployeeManagement.entities.Department;
import com.System.EmployeeManagement.entities.Employee;
import com.System.EmployeeManagement.repository.DepartmentRepository;
import com.System.EmployeeManagement.repository.EmployeeRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<Department> getAllDeparmentData() {
		return this.departmentRepository.findAll();	
	}

	public Department saveDepartmentData(Department deptObj) {
	    Department saveObj = null;
	    try {
	        // Check if a department with the same name already exists
	        Optional<Department> existingDepartment = departmentRepository.findByDeptName(deptObj.getDeptName());
	        if (existingDepartment.isPresent()) {
	            throw new DepartmentAlreadyExistsException("Department with name '" + deptObj.getDeptName() + "' already exists");
	        }
	        saveObj = this.departmentRepository.save(deptObj);
	        if (saveObj != null && deptObj.getEmpList() != null) {
	            for (Employee emp : deptObj.getEmpList()) {
	                if (emp != null) {
	                    if (emp.getDeptList() == null) {
	                        emp.setDeptList(new ArrayList<>(getAllDeparmentData()));
	                    }
	                    if (!emp.getDeptList().contains(saveObj)) {
	                        emp.getDeptList().add(saveObj);
	                    }
	                    this.empRepo.save(emp);
	                }
	            }
	        }
	    } catch (DataIntegrityViolationException e) {
	        // Handle data integrity violation (e.g., duplicate department name)
	        System.out.println("Data integrity violation: " + e.getMessage());
	        throw e;
	    } catch (Exception e) {
	        // Handle other exceptions
	        throw e;
	    }

	    return saveObj;
	}

	public Optional<Department> findDepatemtWithID(int deptId) {
		// TODO Auto-generated method stub
		return this.departmentRepository.findById(deptId);
		
	}

	public Optional<Department> findDepartmentByName(String deptName) {
		return this.departmentRepository.findByDeptName(deptName);
		
	}

	public boolean removeDepartmentData(String deptName) {
		boolean deleteStatus = false;
		Optional<Department> deptObj  = this.departmentRepository.findByDeptName(deptName);
		if(deptObj.isPresent()) {
			int deptId = deptObj.get().getDeptId();
			this.departmentRepository.deleteById(deptId);
			deleteStatus = true;
			return deleteStatus;
		}
		else {
			return deleteStatus;
		}	
	}

	public boolean removeDepartmentId(int deptId) {
		Optional<Department> deptObj = this.departmentRepository.findById(deptId); 
		if(deptObj.isPresent()) {
			this.departmentRepository.deleteById(deptId);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void updateDepartmentData() {
		
	}

}
