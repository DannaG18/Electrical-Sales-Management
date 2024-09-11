package com.esms.category.application;

import com.esms.category.domain.entity.Category;
import com.esms.category.domain.service.CategoryService;

public class CreateCategoryUC {
    private final CategoryService categoryService;

    public CreateCategoryUC(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void execute(Category category) {
        categoryService.createCategory(category);
    }
}
