package com.codewithdurgesh.sk.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.sk.entities.User;
import com.codewithdurgesh.sk.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.sk.respositories.*;
import java.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//loading user from database by username
		User user = this.userRepo.findByEmail(username).orElseThrow(
				() -> new ResourceNotFoundException("User", " email  "+username, 0));
	
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new HashSet<GrantedAuthority>());

	}

}
