package com.lelegspears.simple_world_chat.entities.post;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.lelegspears.simple_world_chat.entities.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post implements Serializable{

	@Serial
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String content;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@CreationTimestamp
	private Instant date;
	
	@JsonManagedReference
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("date ASC")
	private List<PostComment> comments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Post() {
	}
	
	public Post(Long id, String content) {
		this.id = id;
		this.content = content;
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

	public List<PostComment> getComments() {
		return comments;
	}
	public void addComment(PostComment msg) {
		msg.setPost(this);
		this.comments.add(msg);
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
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", content=" + content + ", date=" + date + ", messages=" + comments + "]";
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void removeComment(PostComment comment) {
		comments.remove(comment);
		comment.setPost(null);
	}
}
