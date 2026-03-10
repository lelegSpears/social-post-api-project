package com.lelegspears.simple_world_chat.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private LocalDate date;
	
	public Message(){
	}

	public Message(int id, String content, LocalDate date) {
		this.id = id;
		this.content = content;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
		return id == other.id;
	}
}
