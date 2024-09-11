package com.esms.product.application;

import java.util.List;

import com.esms.product.domain.entity.Product;
import com.esms.product.domain.service.ProductService;

public class FindAllProductUC {
    private ProductService productService;

    public FindAllProductUC(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> execute() {
        return productService.findAllProduct();
    }
}
