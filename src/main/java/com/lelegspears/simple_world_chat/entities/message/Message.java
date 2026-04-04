package com.lelegspears.simple_world_chat.entities.message;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import org.hibernate.annotations.CreationTimestamp;
import com.lelegspears.simple_world_chat.entities.enums.MessageStatus;
import com.lelegspears.simple_world_chat.entities.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="message")
public abstract class Message implements Serializable{
	@Serial
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String content;
	@CreationTimestamp
	private Instant date;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;

	@Enumerated(EnumType.STRING)
	private MessageStatus messageStatus;

	public Message(){
	}

	public Message(Long id, String content, MessageStatus messageStatus) {
		this.id = id;
		this.content = content;
		setMessageStatus(messageStatus);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", date=" + date + "]";
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
		Message other = (Message) obj;
		return Objects.equals(id, other.id);
	}

	public MessageStatus getMessageStatus() {
	    return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
	    this.messageStatus = messageStatus;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
}
