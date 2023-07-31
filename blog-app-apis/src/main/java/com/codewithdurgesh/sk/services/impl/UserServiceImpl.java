package com.codewithdurgesh.sk.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.sk.entities.User;
import com.codewithdurgesh.sk.payloads.UserDto;
import com.codewithdurgesh.sk.respositories.*;
import com.codewithdurgesh.sk.services.UserService;
import com.codewithdurgesh.sk.exceptions.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = dtoToUser(userDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(
				(() -> new ResourceNotFoundException("User", "Id", userId)));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateUser = this.userRepo.save(user);
		
		return this.userToDto(updateUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(
				(() -> new ResourceNotFoundException("User", "Id", userId)));
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> allUsers = this.userRepo.findAll();
		
		List<UserDto> allUserDtos = allUsers.stream().map(
				allUser -> this.userToDto(allUser)).collect(Collectors.toList());
		
		return allUserDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(
				(() -> new ResourceNotFoundException("User", "Id", userId)));
		
		this.userRepo.delete(user);;

	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		
		return user;
		
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		
		return userDto;
	}

}
