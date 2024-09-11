package com.esms.category.application;

import java.util.Optional;

import com.esms.category.domain.entity.Category;
import com.esms.category.domain.service.CategoryService;

public class FindCategoryUC {
    private final CategoryService categoryService;

    public FindCategoryUC(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Optional<Category> execute(int id) {
        return categoryService.findCategory(id);
    }
}
