package com.esms.product.application;

import java.util.Optional;

import com.esms.product.domain.entity.Product;
import com.esms.product.domain.service.ProductService;

public class FindProductUC {
    private ProductService productService;

    public FindProductUC(ProductService productService) {
        this.productService = productService;
    }

    public Optional<Product> execute (int id) {
        return productService.findProduct(id);
    }
}
