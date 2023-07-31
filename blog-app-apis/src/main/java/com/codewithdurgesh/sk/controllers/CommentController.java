package com.codewithdurgesh.sk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.sk.entities.Comment;
import com.codewithdurgesh.sk.payloads.*;
import com.codewithdurgesh.sk.services.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/comments")
	private ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
			@PathVariable Integer postId)
	{
		CommentDto createdCommentDto = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createdCommentDto, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/comments/{commentId}")
	private ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		ApiResponse apiResponse = new ApiResponse("Comment deleted successfully", true);
		return new ResponseEntity<ApiResponse>(
				apiResponse, HttpStatus.OK);
		
	}
}
