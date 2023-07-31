package com.codewithdurgesh.sk.services;

import com.codewithdurgesh.sk.payloads.CommentDto;

public interface CommentService {

	//create
	public CommentDto createComment(CommentDto commentDto, Integer postId);
	
	
	//delete
	void deleteComment(Integer commentId);
	
}
