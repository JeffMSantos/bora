package com.bora;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@RunWith(SpringRunner.class) habilitar banco de dados in runtime
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class BoraApplicationIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldSucceedWith200() throws Exception {
		ResponseEntity<String> result = testRestTemplate.getForEntity(createURLWithPort("/profiles"), String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
