package com.esms.category.application;

import java.util.List;

import com.esms.category.domain.entity.Category;
import com.esms.category.domain.service.CategoryService;

public class FindAllCategoryUC {
        private final CategoryService categoryService;

    public FindAllCategoryUC(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> execute() {
        return categoryService.findAllCategory();
    }
}
