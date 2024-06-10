package com.ChattApp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.ChattApp.model.ChatMessage;

// define the message handling methods 
//These methods will be responsible for receiving messages from one client and then broadcasting it to others

@Controller
public class ChatController {
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) 
	{
		return chatMessage;
	}

	
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor)
	{
		System.out.println("Received a new user");
		headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
		return chatMessage;
	}
}
