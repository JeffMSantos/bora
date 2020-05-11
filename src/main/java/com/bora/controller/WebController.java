package com.bora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bora.model.Profile;
import com.bora.service.impl.ProfileServiceImpl;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/")
public class WebController {

	private final ProfileServiceImpl profileService;

	WebController(ProfileServiceImpl profileService) {
		this.profileService = profileService;
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("profiles", profileService.findAll());
		return "index";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editProfileById(Model model, @PathVariable("id") String id) {
		if (!id.isEmpty()) {
			Profile entity = profileService.findById(id);
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
		profileService.update(profile, profile.getEmail());
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String deleteById(Model model, @PathVariable String id) {
		profileService.deleteById(id);
		return "redirect:/";
	}

}
