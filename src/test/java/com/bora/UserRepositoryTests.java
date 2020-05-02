package com.bora;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.bora.model.User;
import com.bora.repository.UserRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void shouldCreateUser() {
		User user = new User("Email teste", "Nome teste", true, true, true, false, false, false);
		this.userRepository.save(user);
		Assertions.assertThat(user.getEmail()).isNotNull();
		Assertions.assertThat(user.getNome()).isNotNull();
		Assertions.assertThat(user.getSolteiro()).isTrue();
		Assertions.assertThat(user.getFilho()).isTrue();
		Assertions.assertThat(user.getPet()).isTrue();
		Assertions.assertThat(user.getSairAnoite()).isFalse();
		Assertions.assertThat(user.getDescanso()).isFalse();
		Assertions.assertThat(user.getAventureiro()).isFalse();
	}
}
