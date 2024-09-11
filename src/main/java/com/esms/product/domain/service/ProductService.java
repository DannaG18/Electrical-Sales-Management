package com.esms.product.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.product.domain.entity.Product;

public interface ProductService {
    void createProduct (Product product);
    void deleteProduct (int id);
    Optional <Product> findProduct (int id);
    void updateProduct (Product product);
    List <Product> findAllProduct();
}
