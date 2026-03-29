package com.lelegspears.simple_world_chat.resources;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.lelegspears.simple_world_chat.entities.chat.ChatMessage;
import com.lelegspears.simple_world_chat.services.ChatMessageService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/messages")
public class ChatMessageResource {

		private final ChatMessageService service;
		
		public ChatMessageResource(ChatMessageService repository) {
		    this.service = repository;
		}
		
		@GetMapping
		public ResponseEntity<List<ChatMessage>> findAll(){
			List<ChatMessage> messages = service.findAll();
			return ResponseEntity.ok().body(messages);
		}
		
		@PostMapping
		public ResponseEntity<ChatMessage> insert(@RequestBody ChatMessage message) {
			ChatMessage msg = service.insert(message);
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(msg.getId())
					.toUri();
			return ResponseEntity.created(uri).body(msg);
			
		}
		
		@DeleteMapping(value="/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id){
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		@PutMapping(value="/{id}")
		public ResponseEntity<ChatMessage> update(@PathVariable Long id, @RequestBody ChatMessage obj){
			obj = service.update(id, obj);
			return ResponseEntity.ok().body(obj);
			
		}
}
