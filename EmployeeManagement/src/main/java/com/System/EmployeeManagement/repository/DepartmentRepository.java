package com.System.EmployeeManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.System.EmployeeManagement.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	Optional<Department> findByDeptName(String deptName);
	
}
