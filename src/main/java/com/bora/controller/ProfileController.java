package com.bora.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bora.model.Profile;
import com.bora.service.impl.ProfileServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Profile")
@RestController
@RequestMapping("/profiles")
public class ProfileController {
	
	private final ProfileServiceImpl profileService;
	
	ProfileController(ProfileServiceImpl profileService) {
		this.profileService = profileService;
	}
	
	@ApiOperation("Listar perfis")
	@GetMapping
	List<Profile> findAll() {
		return profileService.findAll();
	}

	@PostMapping
	Profile create(@RequestBody Profile profile) {
		return profileService.create(profile);
	}

	@ApiOperation("Buscar perfil por ID")
	@GetMapping("/{id}")
	Profile findById(@PathVariable String id) {
		return profileService.findById(id);
	}

	@PutMapping("/{id}")
	Profile update(@RequestBody Profile profile, @PathVariable String id) {
		return profileService.update(profile, id);
	}

	@ApiOperation("Deletar perfil por ID")
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable String id) {
		profileService.deleteById(id);
	}
}
	