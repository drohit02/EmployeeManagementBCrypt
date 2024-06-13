package com.System.EmployeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.System.EmployeeManagement.entities.authenticate.User;
import com.System.EmployeeManagement.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
    public ResponseEntity<String> initiaBlock(){
    	return ResponseEntity.ok().body("Welcome to the EmployeeManageMentSystem");
    }
	
	@GetMapping("logout")
	public void logoutSession(HttpServletRequest req,HttpServletResponse resp) {
		new SecurityContextLogoutHandler().logout(req, resp, SecurityContextHolder.getContext().getAuthentication());
	}
	
	@PostMapping("saveUser")
	public void saveUser(@RequestBody User userObj) {
		User saveObj = this.userService.persistUser(userObj);
		System.out.println(saveObj);
		
	}
	

}
