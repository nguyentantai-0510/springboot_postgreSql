package com.example.PostgreSql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PostgreSql.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{

}
