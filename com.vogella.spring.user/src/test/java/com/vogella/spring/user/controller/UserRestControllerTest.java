package com.vogella.spring.user.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

import com.vogella.spring.user.domain.User;
import com.vogella.spring.user.service.UserService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(UserRestController.class)
public class UserRestControllerTest {

	@Autowired
	private ApplicationContext context;
	private WebTestClient webTestClient;
	@MockBean
	private UserService userService;

	@Before
	public void setUp() {
		this.webTestClient = WebTestClient.bindToApplicationContext(context).configureClient().baseUrl("/").build();
	}

	@Test
	public void getUserById_userIdFromInitialDataModel_returnsUser() throws Exception {
		int id = 1;
		String name = "Fabian Pfaff";
		when(userService.findUserById(id)).thenReturn(Mono.just(User.builder().name(name).build()));

		ResponseSpec rs = webTestClient.get().uri("/user/" + id).exchange();

		rs.expectStatus().isOk().expectBody(User.class).consumeWith(result -> {
			User user = result.getResponseBody();
			assertThat(user).isNotNull();
			assertThat(user.getName()).isEqualTo(name);
		});
	}

	@Test
	public void getUserById_invalidId_404() throws Exception {
		int invalidId = -1;
		when(userService.findUserById(invalidId)).thenReturn(Mono.empty());

		ResponseSpec rs = webTestClient.get().uri("/user/" + invalidId).exchange();

		rs.expectStatus().isNotFound();
	}

	@Test
	public void createUser_validUserInput_userCreated() throws Exception {
		long id = 42;
		when(userService.newUser(ArgumentMatchers.any()))
			.thenReturn(Mono.just(User.builder().id(id).build()));

		ResponseSpec rs = webTestClient.post().uri("/user")
				.body(BodyInserters.fromObject(
						User.builder().name("Jonas Hungershausen").email("jonas.hungershausen@vogella.com").build()))
				.exchange();

		rs.expectStatus().isCreated().expectHeader().valueEquals("LOCATION", "/user/" + id);
	}

}
