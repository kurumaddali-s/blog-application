package com.codewithdurgesh.sk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//Create or Post user
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(
			@Valid @RequestBody CategoryDto 
			categoryDto) {
		
		 CategoryDto createdCategoryDto = this.categoryService.createCategory(categoryDto);
		 
		 return new ResponseEntity<CategoryDto>(createdCategoryDto, HttpStatus.CREATED);
		
	}
	
	//Update user
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(
			@Valid @RequestBody CategoryDto 
			categoryDto, @PathVariable("categoryId") Integer categoryId) {
		
		 CategoryDto updatedCategoryDto = this.categoryService.
				 updateCategory(categoryDto, categoryId);
		 
		 return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.OK);
		
	}
	
	//delete user
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable
			("categoryId") Integer categoryId) 
	{
		this.categoryService.deleteCategory(categoryId);
		ApiResponse apiResponse = new ApiResponse("Category deleted successfully", true);
		return new ResponseEntity<ApiResponse>(
				apiResponse, HttpStatus.OK);
		
	}
	
	//get one user
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable
			("categoryId") Integer categoryId)
	{
		return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
	}
	
	//get all users
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		return ResponseEntity.ok(this.categoryService.getAllCategories());
	}
	
}
