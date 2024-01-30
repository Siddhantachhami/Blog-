package com.siddhant.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siddhant.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
