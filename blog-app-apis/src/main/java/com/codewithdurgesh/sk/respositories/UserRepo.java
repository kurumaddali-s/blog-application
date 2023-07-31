package com.codewithdurgesh.sk.respositories;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.sk.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	//optional is return type that says there may or maynot be a
	//value instead of returning null
	Optional<User> findByEmail(String email);
}
