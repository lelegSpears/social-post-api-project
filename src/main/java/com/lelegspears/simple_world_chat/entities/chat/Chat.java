package com.lelegspears.simple_world_chat.entities.chat;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lelegspears.simple_world_chat.entities.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
public class Chat implements Serializable{

	@Serial
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private User user1;

	@JsonIgnore
	@ManyToOne
	private User user2;
	
	@JsonManagedReference
	@OneToMany(mappedBy="chat", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("date ASC")
	private List<ChatMessage> messages = new ArrayList<>();
	
	public Chat() {
	}

	public Chat(Long id, User user1, User user2) {
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	public List<ChatMessage> getMessages() {
		return messages;
	}
	
	public void addMessage(ChatMessage message) {
		message.setChat(this);
	    messages.add(message);
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		return Objects.equals(id, other.id);
	}
}
