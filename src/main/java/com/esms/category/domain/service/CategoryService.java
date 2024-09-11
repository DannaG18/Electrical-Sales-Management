package com.esms.category.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.category.domain.entity.Category;

public interface CategoryService {
    void createCategory (Category Category);
    void deleteCategory (int id);
    Optional <Category> findCategory (int id);
    void updateCategory (Category Category);
    List <Category> findAllCategory();
}
