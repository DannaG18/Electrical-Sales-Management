package com.esms.product.application;

import com.esms.product.domain.service.ProductService;

public class DeleteProductUC {
    private ProductService productService;

    public DeleteProductUC(ProductService productService) {
        this.productService = productService;
    }

    public void execute(int id) {
        productService.deleteProduct(id);
    }
}
