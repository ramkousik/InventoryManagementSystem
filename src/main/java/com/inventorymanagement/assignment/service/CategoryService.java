package com.inventorymanagement.assignment.service;

import com.inventorymanagement.assignment.entity.Category;
import com.inventorymanagement.assignment.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long categoryId);

    Category getCategoryEntityById(Long categoryId);
    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);

}
