package com.bora;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bora.model.Perfil;
import com.bora.repository.PerfilRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class PerfilRepositoryTests {

	@Autowired
	private PerfilRepository userRepository;
	
	@Test
	public void shouldCreateUser() {
		Perfil perfil = new Perfil("Email teste", "Nome teste", true, true, true, false, false, false);
		this.userRepository.save(perfil);
		Assertions.assertThat(perfil.getEmail()).isNotNull();
		Assertions.assertThat(perfil.getNome()).isNotNull();
		Assertions.assertThat(perfil.getSolteiro()).isTrue();
		Assertions.assertThat(perfil.getFilho()).isTrue();
		Assertions.assertThat(perfil.getPet()).isTrue();
		Assertions.assertThat(perfil.getSairAnoite()).isFalse();
		Assertions.assertThat(perfil.getDescanso()).isFalse();
		Assertions.assertThat(perfil.getAventureiro()).isFalse();
	}
}
