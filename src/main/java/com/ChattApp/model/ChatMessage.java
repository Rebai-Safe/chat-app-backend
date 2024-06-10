package com.ChattApp.model;


//the message payload that will be exchanged between the clients and the server

public class ChatMessage {
	
	public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

	
	private	MessageType type;
	private String content;
	private String Sender;
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	
	
	

}
