package com.lelegspears.simple_world_chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lelegspears.simple_world_chat.entities.Post;
import com.lelegspears.simple_world_chat.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll(){
		List<Post> Posts = repository.findAll();
		return Posts; 
	}
	
	public Post insert(Post post) {
		return repository.save(post);
	}
}
