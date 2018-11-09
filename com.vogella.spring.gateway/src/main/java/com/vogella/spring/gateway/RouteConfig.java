package com.vogella.spring.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("user",
						r -> r.path("/user/**")
								.filters(f -> f.hystrix(c -> c.setName("fallback")
										.setFallbackUri("forward:/fallback")))
								.uri("lb://user"))
				.build();
	}
}
