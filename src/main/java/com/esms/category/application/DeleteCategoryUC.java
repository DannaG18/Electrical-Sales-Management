package com.esms.category.application;

import com.esms.category.domain.service.CategoryService;

public class DeleteCategoryUC {
    private final CategoryService categoryService;

    public DeleteCategoryUC(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void execute(int id) {
        categoryService.deleteCategory(id);
    }
}
