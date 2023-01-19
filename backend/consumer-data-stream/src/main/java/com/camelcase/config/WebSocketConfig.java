package com.camelcase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Ravi Panchal
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${registry.allow.origins}")
    private String origins;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*EnableSimpleBroker() to enable a simple memory-based message broker
        to carry the messages back to the client on destinations prefixed with /topic.*/
        config.enableSimpleBroker("/topic");

        /*Enabling SockJS fallback options so that alternate transports can be used if WebSocket is not available.
        The SockJS client will attempt to connect to /app and use the best available transport (websocket, xhr-streaming, xhr-polling, and so on).*/
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-change-data").setAllowedOrigins(origins).withSockJS();
    }
}