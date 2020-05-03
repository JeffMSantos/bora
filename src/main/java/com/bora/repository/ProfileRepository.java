package com.bora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bora.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String>{

}
