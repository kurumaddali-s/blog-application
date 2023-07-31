package com.codewithdurgesh.sk.services;

import java.util.List;

import com.codewithdurgesh.sk.payloads.CategoryDto;


public interface CategoryService {

	//create
	public CategoryDto createCategory(CategoryDto category);
	
	//update
	public CategoryDto updateCategory(CategoryDto category, Integer categoryId);
	
	//get by id
	public CategoryDto getCategoryById(Integer categoryId);
	
	//get all
	public List<CategoryDto> getAllCategories();
	
	//delete
	void deleteCategory(Integer categoryId);
	
}
