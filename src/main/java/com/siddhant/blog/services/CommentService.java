package com.siddhant.blog.services;

import com.siddhant.blog.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId);
	void deleteComment(Integer commentId);

}
