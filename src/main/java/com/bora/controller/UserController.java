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

import com.bora.model.User;
import com.bora.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("User")
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserRepository repository;
	
	UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation("Listar usuários")
	@GetMapping
	List<User> getAll() {
		return repository.findAll();
	}

	@PostMapping
	User create(@RequestBody User user) {
		return repository.save(user);
	}

	@ApiOperation("Buscar usuário por ID")
	@GetMapping("/{id}")
	Optional<User> getUserById(@PathVariable String id) {
		return repository.findById(id);
	}

	@PutMapping("/{id}")
	User update(@RequestBody User user, @PathVariable String id) {
		
		return repository.findById(id).map(newUser -> {
			newUser.setEmail(user.getEmail());
			newUser.setAventureiro(user.getAventureiro());
			newUser.setDescanso(user.getDescanso());
			newUser.setFilho(user.getFilho());
			newUser.setNome(user.getNome());
			newUser.setPet(user.getPet());
			newUser.setSairAnoite(user.getSairAnoite());
			newUser.setSolteiro(user.getSolteiro());
			return repository.save(newUser);
		}).orElseGet(() -> {
			return repository.save(user);
		});
	}

	@ApiOperation("Deletar usuário por ID")
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable String id) {
		repository.deleteById(id);
	}
}
	