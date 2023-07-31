package com.codewithdurgesh.sk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.sk.payloads.*;
import com.codewithdurgesh.sk.services.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST - create a user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		
		 UserDto createdUserDto = this.userService.createUser(userDto);
		 
		 return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
		
	}
	
	//PUT - update a user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid
			@RequestBody UserDto userDto, @PathVariable("userId") 
			Integer userId)
	{
		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
		
		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);
	}
	
	//DELETE - delete a user
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) 
	{
		this.userService.deleteUser(userId);
		ApiResponse apiResponse = new ApiResponse("User deleted successfully", true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	//GET - retrieve all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//GET - retrieve single users
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
}
