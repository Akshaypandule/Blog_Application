package com.blogApplication.serviceimpl;

import com.blogApplication.dto.CategoryDto;
import com.blogApplication.entity.Category;
import com.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.repository.CategoryRepo;
import com.blogApplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto)
    {
        Category cat = modelMapper.map(categoryDto, Category.class);
        Category savecategory = categoryRepo.save(cat);
        return modelMapper.map(savecategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId)
    {
        Category category = categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());

        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updateCategory = categoryRepo.save(category);

        return modelMapper.map(updateCategory,CategoryDto.class);

    }
    @Override
    public CategoryDto getCategory(Integer id) {

        Category category = categoryRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));
        
        return modelMapper.map(category,CategoryDto.class);
    }
    @Override
    public List<CategoryDto> getAllCategory()
    {
        List<Category> category = categoryRepo.findAll();
        List<CategoryDto> collect = category.stream()
                        .map((cat) -> modelMapper.map(cat, CategoryDto.class))
                         .collect(Collectors.toList());

        return collect;
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category category = categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

        categoryRepo.delete(category);


    }
    
}
