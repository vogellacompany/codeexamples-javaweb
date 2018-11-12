package com.vogella.spring.user.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

import com.vogella.spring.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestControllerIntegrationTest {

	@Autowired
	private ApplicationContext context;
	private WebTestClient webTestClient;

	@Before
	public void setUp() {
		this.webTestClient = WebTestClient.bindToApplicationContext(context).configureClient().baseUrl("/").build();
	}

	@Test
	public void getUserById_userIdFromInitialDataModel_returnsUser() throws Exception {
		ResponseSpec rs = webTestClient.get().uri("/user/1").exchange();

		rs.expectStatus().isOk()
			.expectBody(User.class)
			.consumeWith(result -> {
				User user = result.getResponseBody();
				assertThat(user).isNotNull();
				assertThat(user.getName()).isEqualTo("Fabian Pfaff");
			});
	}

	@Test
	public void getUserById_invalidId_error() throws Exception {
		ResponseSpec rs = webTestClient.get().uri("/user/-1").exchange();

		rs.expectStatus().isNotFound();
	}

	@Test
	public void createUser_validUserInput_userCreated() throws Exception {
		ResponseSpec rs = webTestClient.post().uri("/user")
				.body(BodyInserters.fromObject(
						User.builder().name("Jonas Hungershausen").email("jonas.hungershausen@vogella.com").build()))
				.exchange();

		rs.expectStatus().isCreated().expectHeader().valueMatches("LOCATION", "^/user/\\d+");
	}

}
