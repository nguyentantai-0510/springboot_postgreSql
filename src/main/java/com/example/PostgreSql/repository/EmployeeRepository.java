package com.example.PostgreSql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PostgreSql.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Integer>{
	EmployeeModel findByUserName (String name);
}
