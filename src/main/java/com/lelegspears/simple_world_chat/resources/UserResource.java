package com.lelegspears.simple_world_chat.resources;

import com.lelegspears.simple_world_chat.entities.user.User;
import com.lelegspears.simple_world_chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserResource {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> messages = service.findAll();
        return ResponseEntity.ok().body(messages);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User usr) {
        User post = service.insert(usr);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).body(post);
    }
}
