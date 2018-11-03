package com.vogella.springboot2.controller;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.vogella.springboot2.domain.Todo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestControllerTest {

	// https://docs.spring.io/spring-restdocs/docs/2.0.2.RELEASE/reference/html5/
	// https://github.com/spring-projects/spring-restdocs/tree/v2.0.2.RELEASE/samples/web-test-client
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
	public void shouldReturnDefaultMessage() throws Exception {
		this.webTestClient.get().uri("/todo/1").exchange().expectStatus().isOk().expectBodyList(Todo.class)
				.consumeWith(document("sample"));
	}
}
