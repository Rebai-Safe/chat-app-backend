package com.ChattApp.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.ChattApp.model.ChatMessage;


//Web Socket event listeners to listen for socket connect and disconnect events
//to log these events and  broadcast them when a user joins or leaves the chat 

@Component
public class WebSocketEventListenner {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListenner.class);
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	
	@EventListener
	public void webSocketConnectionHandler(SessionConnectedEvent event) {
		System.out.println("Received a new web socket connection");
		logger.info("Received a new web socket connection");
		
	}

	@EventListener
	public void webSocketDisconnectionHandler(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		
		String username= (String) headerAccessor.getSessionAttributes().get("username");
		if(username != null) {
			logger.info("User Disconnected : "+ username);
		}
		
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setType(ChatMessage.MessageType.LEAVE);
		chatMessage.setSender(username);
		
		messagingTemplate.convertAndSend("/topic/public",chatMessage);
	}
	
	
	
}
