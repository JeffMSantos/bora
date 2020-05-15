package com.bora.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bora.model.Profile;
import com.bora.repository.ProfileRepository;
import com.bora.service.SlackService;

@Service
public class ProfileServiceImpl {

	@Autowired
	private ProfileRepository repository;

	@Autowired
	private SlackService slackService;

	public List<Profile> findAll() {
		return repository.findAll();
	}

	public Profile create(Profile profile) {
		Profile newProfile = repository.save(profile);
		slackService.postMessage("Novo perfil criado com o email: " +
		newProfile.getEmail());
		return newProfile;
	}

	public Profile findById(String id) {
		Optional<Profile> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}

	public Profile update(Profile profile, String id) {
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
			Profile newProfile = repository.save(profile);
			slackService.postMessage("Novo perfil criado com o email: " +
			newProfile.getEmail());
			return newProfile;
		});
	}

	public void deleteById(String id) {
		repository.deleteById(id);
	}
}
