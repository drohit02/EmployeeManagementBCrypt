package com.System.EmployeeManagement.globalExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.System.EmployeeManagement.customException.department.DepartmentAlreadyExistsException;
import com.System.EmployeeManagement.customException.department.DepartmentNotFoundException;
import com.System.EmployeeManagement.customException.employee.EmployeeNotFoudException;
import com.System.EmployeeManagement.customException.employee.EmployeeNotSavedException;
import com.System.EmployeeManagement.customException.employee.InputIllegalDataTypeException;
import com.System.EmployeeManagement.customException.employee.NoEmployeeListFoundException;
import com.System.EmployeeManagement.customException.employee.NoUserFoundException;
import com.System.EmployeeManagement.exceptionResponseObject.ExceptionEnum;
import com.System.EmployeeManagement.exceptionResponseObject.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<ExceptionResponse> handleRuntimeException(NoUserFoundException ex) {
		ExceptionResponse responseObj = new ExceptionResponse(new Date(),ExceptionEnum.EMPLOYEE_NOT_FOUND_EXCEPTION,"No user with id",ex.getMessage());
		return new ResponseEntity<>(responseObj,HttpStatusCode.valueOf(400));
	}
	
	@ExceptionHandler(EmployeeNotFoudException.class)
	public ResponseEntity<ExceptionResponse> handleEmployeeNotFoundException(EmployeeNotFoudException ex) {
		ExceptionResponse responseObj = new ExceptionResponse(new Date(),ExceptionEnum.EMPLOYEE_NOT_FOUND_EXCEPTION,"No Employee found",ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(responseObj,HttpStatusCode.valueOf(400));
	}
	@ExceptionHandler(NoEmployeeListFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNoEmployeeListFoundException(NoEmployeeListFoundException ex) {
		ExceptionResponse responseObj = new ExceptionResponse(new Date(),ExceptionEnum.EMPLOYEELIST_NULL_POINTER_EXCEPTION,"Employee Table must empty",ex.getMessage());;
		return new ResponseEntity<ExceptionResponse>(responseObj, HttpStatusCode.valueOf(400));
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public void throwInputValidationException(MethodArgumentNotValidException ex) {
//		Map<String,String> validationError = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error)->{
//			String fieldName = ((FieldError)validationError).getField();
//			String errMsg = error.getDefaultMessage();
//			validationError.put(fieldName,errMsg);
//		});
//		throw new InputIllegalDataTypeException("Data Type Mismatch");
//	}
	
	@ExceptionHandler(EmployeeNotSavedException.class)
	public ResponseEntity<ExceptionResponse> handleEmployeeNotSavedException(EmployeeNotSavedException ex) {
		ExceptionResponse responseObj = new ExceptionResponse(new Date(),ExceptionEnum.EMPLOYEE_NOT_SAVED_EXCEPTION,"Employee not saved into database",ex.getMessage());;
		return new ResponseEntity<ExceptionResponse>(responseObj,HttpStatusCode.valueOf(400));
	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(DepartmentAlreadyExistsException.class)
	public ResponseEntity<String> handleDepartmentAlreadyExistsException(DepartmentAlreadyExistsException ex) {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
}
