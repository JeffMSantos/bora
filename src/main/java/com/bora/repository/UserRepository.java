package com.bora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bora.model.User;

public interface UserRepository extends JpaRepository<User, String>{

}
