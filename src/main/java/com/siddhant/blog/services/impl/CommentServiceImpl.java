package com.siddhant.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhant.blog.entities.Comment;
import com.siddhant.blog.entities.Post;
import com.siddhant.blog.entities.User;
import com.siddhant.blog.exceptions.ResourceNotFoundException;
import com.siddhant.blog.payloads.CommentDto;
import com.siddhant.blog.repositories.CommentRepo;
import com.siddhant.blog.repositories.PostRepo;
import com.siddhant.blog.repositories.UserRepo;
import com.siddhant.blog.services.CommentService;
@Service
public class CommentServiceImpl  implements CommentService{
	@Autowired 
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired 
	private CommentRepo commentRepo;
	@Autowired 
	private UserRepo userRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class) ;
	
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->
		new ResourceNotFoundException("Comment","comment id",commentId));
		this.commentRepo.delete(comment);

	}
		
	

}
