package org.esudarshan.configuration;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/snakes-n-ladders").setAllowedOrigins("*")
				.setHandshakeHandler(new DefaultHandshakeHandler() {

					@Override
					protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
							Map<String, Object> attributes) {
						// TODO Auto-generated method stub
						return new UsernamePasswordAuthenticationToken(UUID.randomUUID().toString(), null);
					}
				}).withSockJS();

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue");
		registry.setApplicationDestinationPrefixes("/application");
		registry.setUserDestinationPrefix("/user");
	}

}
