package com.codewithdurgesh.sk.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.sk.entities.Category;
import com.codewithdurgesh.sk.entities.Post;
import com.codewithdurgesh.sk.entities.User;


public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

}
