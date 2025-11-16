package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repo.CategoryRepo;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepo.delete(category);
        return "Category with categoryId " + category.getCategoryId() + " has been deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {

        Category savedCategory = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found"));

        category.setCategoryId(categoryId);
        category.setCategoryName(category.getCategoryName());
        savedCategory = categoryRepo.save(category);
        return savedCategory;
    }
}
