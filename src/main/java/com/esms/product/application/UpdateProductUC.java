package com.esms.product.application;

import com.esms.product.domain.entity.Product;
import com.esms.product.domain.service.ProductService;

public class UpdateProductUC {
    private ProductService productService;

    public UpdateProductUC(ProductService productService) {
        this.productService = productService;
    }

    public void execute(Product product) {
        productService.updateProduct(product);
    }
}
