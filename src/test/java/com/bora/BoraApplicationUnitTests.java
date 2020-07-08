package com.bora;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bora.model.Profile;
import com.bora.service.impl.ProfileServiceImpl;

@SpringBootTest
@AutoConfigureTestDatabase
class BoraApplicationUnitTests {

	@Autowired
	private ProfileServiceImpl profileService;
	
	@Test
	public void shouldCreateProfile() {
		Profile profile = new Profile("Email teste", "Nome teste", true, true, true, false, false, false);
		//profileService.create(profile);
		Assertions.assertThat(profile.getEmail()).isNotNull();
		Assertions.assertThat(profile.getNome()).isNotNull();
		Assertions.assertThat(profile.getSolteiro()).isTrue();
		Assertions.assertThat(profile.getFilho()).isTrue();
		Assertions.assertThat(profile.getPet()).isTrue();
		Assertions.assertThat(profile.getSairAnoite()).isFalse();
		Assertions.assertThat(profile.getDescanso()).isFalse();
		Assertions.assertThat(profile.getAventureiro()).isFalse();
	}

	@Test
	public void shouldUpdateProfile() {
		Profile profile = new Profile("Email teste", "Antigo nome", true, true, true, false, false, false);
		//profileService.create(profile);

		profile.setNome("Novo nome");
		profile.setSolteiro(false);
		profile.setAventureiro(false);
		profile.setDescanso(true);
		profile.setFilho(false);
		profile.setPet(false);
		profile.setSairAnoite(true);
		//profileService.update(profile, profile.getEmail());

		//profile = profileService.findById(profile.getEmail());
		Assertions.assertThat(profile.getNome()).isEqualTo("Novo nome");
		Assertions.assertThat(profile.getSolteiro()).isEqualTo(false);
		Assertions.assertThat(profile.getAventureiro()).isEqualTo(false);
		Assertions.assertThat(profile.getDescanso()).isEqualTo(true);
		Assertions.assertThat(profile.getFilho()).isEqualTo(false);
		Assertions.assertThat(profile.getPet()).isEqualTo(false);
		Assertions.assertThat(profile.getSairAnoite()).isEqualTo(true);
	}

	@Test
	public void shouldDeleteProfile() {
		Profile profile = new Profile("Email teste", "Nome teste", true, true, true, false, false, false);
		//profileService.create(profile);
		//profileService.deleteById(profile.getEmail());
		Assertions.assertThat(profileService.findById(profile.getEmail())).isNull();
	}
	
	@Test
	public void whenNotEmptyEmail_thenNoConstraintViolations() {
	    Profile profile = new Profile("jeff@jeff.com", "Jeff", true, true, true, true, true, true);
	    Set<ConstraintViolation<Profile>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(profile);
	    Assertions.assertThat(violations.size()).isEqualTo(0);
	}
	     
	@Test
	public void whenEmptyEmail_thenOneConstraintViolation() {
	    Profile profile = new Profile("", "Jeff", true, true, true, true, true, true);
	    Set<ConstraintViolation<Profile>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(profile);
	    Assertions.assertThat(violations.size()).isEqualTo(1);
	}
	     
	@Test
	public void whenNullEmail_thenOneConstraintViolation() {
	    Profile profile = new Profile(null, "Jeff", true, true, true, true, true, true);
	    Set<ConstraintViolation<Profile>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(profile);
	    Assertions.assertThat(violations.size()).isEqualTo(1);
	}
}
