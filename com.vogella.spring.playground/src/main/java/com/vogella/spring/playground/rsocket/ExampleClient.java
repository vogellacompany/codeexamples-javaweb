package com.vogella.spring.playground.rsocket;

import java.net.URI;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.WebsocketClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

public class ExampleClient {
	public static void main(String[] args) {
		WebsocketClientTransport ws = WebsocketClientTransport
			.create(URI
				.create("ws://rsocket-demo.herokuapp.com/ws"));
		RSocket client = RSocketFactory
			.connect()
			.keepAlive()
			.transport(ws)
			.start()
			.block();

		try {
			Flux<Payload> s = client
				.requestStream(DefaultPayload
					.create("peace"));

			s
				.take(10)
				.doOnNext(p -> System.out
					.println(p
						.getDataUtf8()))
				.blockLast();
		} finally {
			client
				.dispose();
		}
	}
}