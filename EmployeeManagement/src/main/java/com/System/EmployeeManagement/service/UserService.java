package com.System.EmployeeManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.System.EmployeeManagement.customException.employee.NoUserFoundException;
import com.System.EmployeeManagement.entities.authenticate.User;
import com.System.EmployeeManagement.repository.authenticate.dao.AuthenticateUserRepository;

@Service
public class UserService {

	@Autowired
	private AuthenticateUserRepository userRepositoty;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public User persistUser(User userObj) {
		if(userObj==null) {
			throw new NoUserFoundException("User Data is inappropriate");
		}
		else {
			userObj.setPassword(encoder.encode(userObj.getPassword()));
			return this.userRepositoty.save(userObj);
		}
	}
}
