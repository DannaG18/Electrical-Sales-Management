package com.esms.category.application;

import com.esms.category.domain.entity.Category;
import com.esms.category.domain.service.CategoryService;

public class UpdateCategoryUC {
            private final CategoryService categoryService;

    public UpdateCategoryUC(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void execute(Category category) {
        categoryService.updateCategory(category);
    }
}
