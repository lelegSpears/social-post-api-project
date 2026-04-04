package com.lelegspears.simple_world_chat.entities.chat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelegspears.simple_world_chat.entities.message.Message;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;

@Entity
public class ChatMessage extends Message{

	@Serial
    private static final long serialVersionUID = 1L;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "chat_id")
	private Chat chat;

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
	
}
