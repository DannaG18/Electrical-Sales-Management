package com.esms.product.application;

import com.esms.product.domain.entity.Product;
import com.esms.product.domain.service.ProductService;

public class CreateProductUC {
    private ProductService productService;

    public CreateProductUC(ProductService productService) {
        this.productService = productService;
    }

    public  void execute(Product product) {
        productService.createProduct(product);
    }

}
