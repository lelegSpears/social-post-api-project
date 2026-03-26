package com.lelegspears.simple_world_chat.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelegspears.simple_world_chat.entities.enums.MessageStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="message")
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String content;
	@CreationTimestamp
	private Instant date;
	
	@JsonBackReference
	@ManyToOne
	private Post post;
	
	private Integer messageStatus;
	
	public Message(){
	}

	public Message(Long id, String content, MessageStatus messageStatus, Instant date) {
		this.id = id;
		this.content = content;
		setMessageStatus(messageStatus);
		this.date = date;
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

	public void setDate(Instant date) {
		this.date = date;
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
		if (messageStatus == null) {
	        return null;
	    }
		return MessageStatus.valueOf(messageStatus);
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		if(messageStatus != null) {
			this.messageStatus = messageStatus.getCode();
		}
	}

	
}
