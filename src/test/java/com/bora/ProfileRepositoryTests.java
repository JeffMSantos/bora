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
		this.userRepository.save(profile);
		Assertions.assertThat(profile.getEmail()).isNotNull();
		Assertions.assertThat(profile.getNome()).isNotNull();
		Assertions.assertThat(profile.getSolteiro()).isTrue();
		Assertions.assertThat(profile.getFilho()).isTrue();
		Assertions.assertThat(profile.getPet()).isTrue();
		Assertions.assertThat(profile.getSairAnoite()).isFalse();
		Assertions.assertThat(profile.getDescanso()).isFalse();
		Assertions.assertThat(profile.getAventureiro()).isFalse();
	}
}
