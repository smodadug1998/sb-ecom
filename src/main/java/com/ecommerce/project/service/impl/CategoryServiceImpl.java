package com.ecommerce.project.service.impl;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
        }
        categories.remove(category);
        return "Category with categoryId " + category.getCategoryId() + " has been deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();
        if (optionalCategory.isPresent()) {
            Category updatedCategory = optionalCategory.get();
            updatedCategory.setCategoryName(category.getCategoryName());
            return updatedCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
        }
    }
}
