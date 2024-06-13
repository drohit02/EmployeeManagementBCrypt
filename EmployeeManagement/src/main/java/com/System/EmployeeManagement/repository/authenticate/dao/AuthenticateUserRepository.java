package com.System.EmployeeManagement.repository.authenticate.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.System.EmployeeManagement.entities.authenticate.User;
import java.util.List;


@Repository
public interface AuthenticateUserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String userName);
}
