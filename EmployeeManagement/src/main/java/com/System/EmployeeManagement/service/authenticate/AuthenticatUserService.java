package com.System.EmployeeManagement.service.authenticate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.System.EmployeeManagement.entities.authenticate.User;
import com.System.EmployeeManagement.entities.authenticate.UserPrincipal;
import com.System.EmployeeManagement.repository.authenticate.dao.AuthenticateUserRepository;

@Service
public class AuthenticatUserService implements UserDetailsService {

	@Autowired
	private AuthenticateUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Optional<User> authUser = userRepository.findByUserName(username);
	  if (!authUser.isPresent()) {
	    throw new UsernameNotFoundException("User Not Found");
	  }
	  System.out.println(authUser.get());
	  return new UserPrincipal(authUser.get());
	}
}
