package com.bora;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bora.model.Profile;
import com.bora.repository.ProfileRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class ProfileRepositoryTests {

	@Autowired
	private ProfileRepository userRepository;
	
	@Test
	public void shouldCreateProfile() {
		Profile profile = new Profile("Email teste", "Nome teste", true, true, true, false, false, false);
		userRepository.save(profile);
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
		Profile profile = new Profile("Email teste", "Nome teste", true, true, true, false, false, false);
		userRepository.save(profile);
		
		profile.setNome("Novo nome");
		profile.setSolteiro(false);
		profile.setAventureiro(false);
		profile.setDescanso(true);
		profile.setFilho(false);
		profile.setPet(false);
		profile.setSairAnoite(true);
		userRepository.save(profile);
		
		profile = userRepository.findById(profile.getEmail()).get();
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
		userRepository.save(profile);
		userRepository.delete(profile);
		Assertions.assertThat(userRepository.findById(profile.getEmail())).isEmpty();
	}
}
