package com.vogella.spring.user.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.vogella.spring.user.domain.User;

@RunWith(SpringRunner.class)
@WebFluxTest(UserRestController.class)
public class UserRestControllerTest {

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
				assertThat(user.getUserName()).isEqualTo("Fabian Pfaff");
			});
	}

	@Test
	public void getUserById_invalidId_error() throws Exception {
		ResponseSpec rs = webTestClient.get().uri("/user/-1").exchange();

		rs.expectStatus().isNotFound();
	}

}
