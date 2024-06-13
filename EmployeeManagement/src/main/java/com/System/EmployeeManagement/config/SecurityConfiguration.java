package com.System.EmployeeManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.System.EmployeeManagement.service.authenticate.AuthenticatUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AuthenticatUserService userDetailsService;

	@Bean
	public AuthenticationProvider getAuthenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

		return provider;
	}

	@Bean
	public SecurityFilterChain setSecurityFilterChain(HttpSecurity httpSecurityObj) throws Exception {

		// CSRF Token Disabled
		httpSecurityObj.csrf(csrfTokenDisableObj -> csrfTokenDisableObj.disable());

		// Authentication all request coming from the web
		httpSecurityObj.authorizeHttpRequests(request -> request.anyRequest().authenticated());

//	    	Enable form authentication here
//	    	httpSecurityObj.formLogin(Customizer.withDefaults());

		// Basic Auth enable as Provider for Form Based Authentication
		httpSecurityObj.httpBasic(Customizer.withDefaults());

		// Making every request stateless(If we are making it stateless we de not need
		// formLogin)
		httpSecurityObj.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return httpSecurityObj.build();
	}

	public static DaoAuthenticationProvider getDaoAuthenticationProvider() {
		return new DaoAuthenticationProvider();
	}

	public static BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}
