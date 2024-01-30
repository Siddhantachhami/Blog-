package com.siddhant.blog.services;

import java.util.List;

import com.siddhant.blog.payloads.PostDto;
import com.siddhant.blog.payloads.PostResponse;

public interface PostService {
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	//delete
	void deletePost(Integer postId);
	//get all posts 
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	//get single post
	PostDto getPostById(Integer postId);
	//get all posts by Category 
	List<PostDto>getPostsByCategory(Integer categoryId);
	//get all posts by User
	List <PostDto> getPostsByUser(Integer userId);
	//search post
	List<PostDto> searchPosts(String keyword);

}
