package com.vogella.spring.user.controller;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.vogella.spring.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestDocControllerTest {

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private ApplicationContext context;

	private WebTestClient webTestClient;

	@Before
	public void setUp() {
		this.webTestClient = WebTestClient.bindToApplicationContext(context).configureClient().baseUrl("/")
				.filter(documentationConfiguration(restDocumentation)).build();
	}

	@Test
	@WithMockUser
	public void shouldReturnUser() throws Exception {
		ResponseSpec rs = webTestClient.get().uri("/user/{id}", 1).exchange();
		
		rs.expectStatus().isOk().expectBody(User.class)
				.consumeWith(document("sample",
						pathParameters(parameterWithName("id").description("The id of the User entity"))));
	}
}
