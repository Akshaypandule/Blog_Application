package com.blogApplication.service;

import com.blogApplication.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    // Create Category
   public CategoryDto createCategory(CategoryDto categoryDto);

    // Update Category
   public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    // Get Single Category
    public CategoryDto getCategory (Integer id);

    // Get All Category

   public List<CategoryDto> getAllCategory();

   // Delete Category

    void deleteCategory(Integer categoryId);
}
