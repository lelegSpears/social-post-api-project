package com.lelegspears.simple_world_chat.resources;

import com.lelegspears.simple_world_chat.entities.post.Post;
import com.lelegspears.simple_world_chat.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/post/comments")
public class PostCommentResource {
    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        List<Post> messages = service.findAll();
        return ResponseEntity.ok().body(messages);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping
    public ResponseEntity<Post> insert(@RequestBody Post pst) {
        Post post = service.insert(pst);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).body(post);
    }
}
