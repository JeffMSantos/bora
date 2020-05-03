package com.bora.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bora.model.Profile;
import com.bora.repository.ProfileRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Profile")
@RestController
@RequestMapping("/profiles")
public class ProfileController {

	private final ProfileRepository repository;
	
	ProfileController(ProfileRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation("Listar perfis")
	@GetMapping
	List<Profile> getAll() {
		return repository.findAll();
	}

	@PostMapping
	Profile create(@RequestBody Profile profile) {
		return repository.save(profile);
	}

	@ApiOperation("Buscar perfil por ID")
	@GetMapping("/{id}")
	Optional<Profile> getUserById(@PathVariable String id) {
		return repository.findById(id);
	}

	@PutMapping("/{id}")
	Profile update(@RequestBody Profile profile, @PathVariable String id) {
		
		return repository.findById(id).map(newProfile -> {
			newProfile.setEmail(profile.getEmail());
			newProfile.setAventureiro(profile.getAventureiro());
			newProfile.setDescanso(profile.getDescanso());
			newProfile.setFilho(profile.getFilho());
			newProfile.setNome(profile.getNome());
			newProfile.setPet(profile.getPet());
			newProfile.setSairAnoite(profile.getSairAnoite());
			newProfile.setSolteiro(profile.getSolteiro());
			return repository.save(newProfile);
		}).orElseGet(() -> {
			return repository.save(profile);
		});
	}

	@ApiOperation("Deletar perfil por ID")
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable String id) {
		repository.deleteById(id);
	}
}
	