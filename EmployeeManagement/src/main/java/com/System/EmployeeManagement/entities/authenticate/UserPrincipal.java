package com.System.EmployeeManagement.entities.authenticate;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	
	private User authUser;
	
	public UserPrincipal(User authenticatedUser) {
		this.authUser = authenticatedUser;
	}
	
	public User getAuthUser() {
		return authUser;
	}

	public void setAuthUser(User authUser) {
		this.authUser = authUser;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.authUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.authUser.getUserName();
	}

}
