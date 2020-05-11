package com.bora.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bora.model.Profile;
import com.bora.repository.ProfileRepository;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/")
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@Autowired
	private ProfileRepository repository;

	void ProfileController(ProfileRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("profiles", repository.findAll());
		return "index";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editProfileById(Model model, @PathVariable("id") String id) {
		if (!id.isEmpty()) {
			Profile entity = repository.findById(id).get();
			model.addAttribute("profile", entity);
			return "edit-profile";
		} else {
			return "index";
		}
	}

	@RequestMapping(path = { "/add" })
	public String add(Model model) {
		model.addAttribute("profile", new Profile());
		return "add-profile";
	}

	@PostMapping("/createProfile")
	public String createOrUpdateProfile(Profile profile) {

		logger.info("Passou aqui!" + profile.toString());

		Optional<Profile> entity = repository.findById(profile.getEmail());

		if (!entity.isPresent()) {
			logger.info("Entrou no IF!");
			repository.save(profile);
		} else {
			entity.get().setEmail(profile.getEmail());
			entity.get().setAventureiro(profile.getAventureiro());
			entity.get().setDescanso(profile.getDescanso());
			entity.get().setFilho(profile.getFilho());
			entity.get().setNome(profile.getNome());
			entity.get().setPet(profile.getPet());
			entity.get().setSairAnoite(profile.getSairAnoite());
			entity.get().setSolteiro(profile.getSolteiro());
			repository.save(entity.get());
		}
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String deleteById(Model model, @PathVariable String id) {
		repository.deleteById(id);
		return "redirect:/";
	}

}
