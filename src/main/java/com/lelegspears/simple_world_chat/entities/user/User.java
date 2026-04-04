package com.lelegspears.simple_world_chat.entities.user;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lelegspears.simple_world_chat.entities.message.Message;
import com.lelegspears.simple_world_chat.entities.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User implements Serializable{
	
	@Serial
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@Column(unique = true, nullable = false)
	@NotBlank
	private String username;

	@NotBlank
	@JsonIgnore
	@Column(nullable = false)
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
	private List<Message> messages = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();

	
	public User() {
	}

	public User(Long id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
