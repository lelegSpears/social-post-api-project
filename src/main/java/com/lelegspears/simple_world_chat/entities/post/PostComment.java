package com.lelegspears.simple_world_chat.entities.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lelegspears.simple_world_chat.entities.message.Message;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PostComment extends Message{

	private static final long serialVersionUID = 1L;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}

