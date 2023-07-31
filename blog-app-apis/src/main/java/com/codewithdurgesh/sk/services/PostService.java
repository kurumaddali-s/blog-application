package com.codewithdurgesh.sk.services;

import java.util.List;

import com.codewithdurgesh.sk.entities.Category;
import com.codewithdurgesh.sk.entities.Post;
import com.codewithdurgesh.sk.entities.User;
import com.codewithdurgesh.sk.payloads.*;

public interface PostService {
	
	//create
	PostDto createPost(PostDto post, Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto post, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//getPostByID
	PostDto getPostById(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get all posts by category
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getAllPostsByUser(Integer userId);
	
	//search for particular post
	List<PostDto> searchPosts(String keyword);


}
