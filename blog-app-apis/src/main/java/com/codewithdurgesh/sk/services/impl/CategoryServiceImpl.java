package com.codewithdurgesh.sk.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.sk.entities.Category;
import com.codewithdurgesh.sk.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.sk.payloads.CategoryDto;
import com.codewithdurgesh.sk.respositories.CategoryRepo;
import com.codewithdurgesh.sk.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = dtoToCategory(categoryDto);
		Category savedCategory = this.categoryRepo.save(category);
		
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				(() -> new ResourceNotFoundException("category", "Id", categoryId)));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatecategory = this.categoryRepo.save(category);
		
		return this.categoryToDto(updatecategory);
	
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				(() -> new ResourceNotFoundException("category", "Id", categoryId)));
				
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		
		List<Category> categoriesList = this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtoList = categoriesList.stream().map
				(categoryOne -> this.categoryToDto(categoryOne)).collect(Collectors.toList());
		
		return categoryDtoList;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				(() -> new ResourceNotFoundException("category", "Id", categoryId)));
		
		this.categoryRepo.delete(category);
	}
	
	private Category dtoToCategory(CategoryDto categoryDto)
	{
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	private CategoryDto categoryToDto(Category category)
	{
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
