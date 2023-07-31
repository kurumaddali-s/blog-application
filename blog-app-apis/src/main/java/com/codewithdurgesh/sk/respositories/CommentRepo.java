package com.codewithdurgesh.sk.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithdurgesh.sk.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {


}
