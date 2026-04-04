package com.lelegspears.simple_world_chat.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.lelegspears.simple_world_chat.entities.chat.ChatMessage;
import com.lelegspears.simple_world_chat.services.ChatMessageService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat/messages")
public class ChatMessageResource {

	@Autowired
	private ChatMessageService service;

	@GetMapping
	public ResponseEntity<List<ChatMessage>> findAll(){
		List<ChatMessage> messages = service.findAll();
		return ResponseEntity.ok().body(messages);
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<ChatMessage> findById(@PathVariable Long id) {
		ChatMessage post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}

	@PostMapping
	public ResponseEntity<ChatMessage> insert(@RequestBody ChatMessage chatMessage) {
		ChatMessage post = service.insert(chatMessage);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(uri).body(post);
	}
}
