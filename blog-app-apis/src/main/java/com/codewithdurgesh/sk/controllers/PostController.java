package com.codewithdurgesh.sk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.sk.blog.config.AppConstants;
import com.codewithdurgesh.sk.payloads.*;
import com.codewithdurgesh.sk.services.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//Create or Post post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, 
			@PathVariable Integer categoryId) {
		 
		 PostDto createdPostDto = this.postService.createPost(postDto, userId, categoryId);
		 return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
		
	}
	
	//Get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Integer categoryId)
	{
		return ResponseEntity.ok(this.postService.getAllPostsByCategory(categoryId));
	}
	
	//Get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.postService.getAllPostsByUser(userId));
	}
	
	//Get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
	{
		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return ResponseEntity.ok(allPost);
	}
	
	//Get post by Id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		return ResponseEntity.ok(this.postService.getPostById(postId));
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable
			("postId") Integer postId) 
	{
		this.postService.deletePost(postId);
		ApiResponse apiResponse = new ApiResponse("Post deleted successfully", true);
		return new ResponseEntity<ApiResponse>(
				apiResponse, HttpStatus.OK);
		
	}
	
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(
			@Valid @RequestBody PostDto 
			postDto, @PathVariable("postId") Integer postId) {
		
		 PostDto updatedPostDto = this.postService.
				 updatePost(postDto, postId);
		 
		 return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
		
	}
	
	//get by search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle(
			@PathVariable String keywords)
	{
		List<PostDto> postDtos = this.postService.searchPosts(keywords);
		return ResponseEntity.ok(postDtos);
		
	}


	
}
