package com.lelegspears.simple_world_chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lelegspears.simple_world_chat.entities.post.PostComment;


public interface PostCommentRepository extends JpaRepository<PostComment,Long>{

}
