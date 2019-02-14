package com.vogella.spring.gateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route.AsyncBuilder;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user",
						new Function<PredicateSpec, AsyncBuilder>() {
							@Override
							public AsyncBuilder apply(PredicateSpec r) {
								return r.path("/user/**")
										.uri("lb://user");
							}
						})
				.build();
	}
}
