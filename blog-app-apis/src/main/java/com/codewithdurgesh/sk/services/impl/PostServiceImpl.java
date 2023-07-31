package com.codewithdurgesh.sk.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.sk.entities.Post;
import com.codewithdurgesh.sk.entities.User;
import com.codewithdurgesh.sk.entities.Category;
import com.codewithdurgesh.sk.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.sk.payloads.*;
import com.codewithdurgesh.sk.respositories.CategoryRepo;
import com.codewithdurgesh.sk.respositories.PostRepo;
import com.codewithdurgesh.sk.respositories.UserRepo;
import com.codewithdurgesh.sk.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		
		User user = this.userRepo.findById(userId).orElseThrow(
				(() -> new ResourceNotFoundException("User", "Id", userId)));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				(() -> new ResourceNotFoundException("category", "Id", categoryId)));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date(0));
		
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId).orElseThrow(
				(() -> new ResourceNotFoundException("post", "Id", postId)));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(() -> 
		new ResourceNotFoundException("post", "id", postId));
		
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(() -> 
		new ResourceNotFoundException("post", "id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : 
			Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(pageable);
		List<Post> posts = pagePost.getContent();
		
		List<PostDto> postDto = posts.stream().map(post ->
		this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setContent(postDto);
		
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				(() -> new ResourceNotFoundException("category", "Id", categoryId)));
		List<Post> listOfPostsByCat = this.postRepo.findByCategory(category);
		
		List<PostDto> listOfPostDtosByCat = listOfPostsByCat.stream().map(postByCat ->
				this.modelMapper.map(postByCat, PostDto.class)).collect(Collectors.toList());
		return listOfPostDtosByCat;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(() -> new
				ResourceNotFoundException("user", "Id", userId));
		List<Post> listOfPostsByUser = this.postRepo.findByUser(user);
		
		List<PostDto> listOfPostDtosByUser = listOfPostsByUser.stream().map(postByUser ->
		this.modelMapper.map(postByUser, PostDto.class)).collect(Collectors.toList());
		return listOfPostDtosByUser;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> postsByTitle = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtosContainingTitle = postsByTitle.stream().map(post ->
		this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtosContainingTitle;
	}

}
