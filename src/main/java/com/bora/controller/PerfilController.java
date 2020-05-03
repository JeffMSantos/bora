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

import com.bora.model.Perfil;
import com.bora.repository.PerfilRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Perfil")
@RestController
@RequestMapping("/perfis")
public class PerfilController {

	private final PerfilRepository repository;
	
	PerfilController(PerfilRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation("Listar perfis")
	@GetMapping
	List<Perfil> getAll() {
		return repository.findAll();
	}

	@PostMapping
	Perfil create(@RequestBody Perfil perfil) {
		return repository.save(perfil);
	}

	@ApiOperation("Buscar perfil por ID")
	@GetMapping("/{id}")
	Optional<Perfil> getUserById(@PathVariable String id) {
		return repository.findById(id);
	}

	@PutMapping("/{id}")
	Perfil update(@RequestBody Perfil perfil, @PathVariable String id) {
		
		return repository.findById(id).map(newPerfil -> {
			newPerfil.setEmail(perfil.getEmail());
			newPerfil.setAventureiro(perfil.getAventureiro());
			newPerfil.setDescanso(perfil.getDescanso());
			newPerfil.setFilho(perfil.getFilho());
			newPerfil.setNome(perfil.getNome());
			newPerfil.setPet(perfil.getPet());
			newPerfil.setSairAnoite(perfil.getSairAnoite());
			newPerfil.setSolteiro(perfil.getSolteiro());
			return repository.save(newPerfil);
		}).orElseGet(() -> {
			return repository.save(perfil);
		});
	}

	@ApiOperation("Deletar perfil por ID")
	@DeleteMapping("/{id}")
	void deleteById(@PathVariable String id) {
		repository.deleteById(id);
	}
}
	