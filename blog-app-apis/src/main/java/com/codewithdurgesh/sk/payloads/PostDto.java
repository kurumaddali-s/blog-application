package com.codewithdurgesh.sk.payloads;

import java.util.*;
import java.sql.Date;

import com.codewithdurgesh.sk.entities.*;
import com.codewithdurgesh.sk.entities.User;

public class PostDto {
	
	private Integer postId;
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	private String title;
	private String content;
	
	private String imageName;

	private Date addedDate;
	
	private CategoryDto category;
	
	public PostDto() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
