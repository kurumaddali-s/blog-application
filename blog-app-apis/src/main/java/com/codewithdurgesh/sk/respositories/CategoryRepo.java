package com.codewithdurgesh.sk.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.sk.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}