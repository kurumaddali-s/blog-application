package com.codewithdurgesh.sk.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.sk.entities.Comment;
import com.codewithdurgesh.sk.entities.Post;
import com.codewithdurgesh.sk.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.sk.payloads.CommentDto;
import com.codewithdurgesh.sk.respositories.CommentRepo;
import com.codewithdurgesh.sk.respositories.PostRepo;
import com.codewithdurgesh.sk.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(
				(() -> new ResourceNotFoundException("post", "Id", postId)));

		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(
				(() -> new ResourceNotFoundException("comment", "Id", commentId)));
		this.commentRepo.delete(comment);
	}

}
